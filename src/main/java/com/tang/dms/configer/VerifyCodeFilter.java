package com.tang.dms.configer;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

@Component
public class VerifyCodeFilter extends GenericFilterBean {
 private String defaultFilterProcessUrl = "/login";
 
 @Override
  public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
   throws IOException, ServletException {
  HttpServletRequest request = (HttpServletRequest) req;
  HttpServletResponse response = (HttpServletResponse) res;
  HttpSession session = request.getSession();
  if ("POST".equalsIgnoreCase(request.getMethod()) && defaultFilterProcessUrl.equals(request.getServletPath())) {
   // 验证码验证
   String requestCaptcha = request.getParameter("code");
   Object cko = session.getAttribute("simpleCaptcha") ; //验证码对象
   if(cko == null){
    response.sendRedirect("toLogin?err=401");
   }
   String captcha = cko.toString();
   Date now = new Date();
   Long codeTime = Long.valueOf(session.getAttribute("codeTime")+"");
   if(StringUtils.isEmpty(requestCaptcha) || captcha == null ||  !(requestCaptcha.equalsIgnoreCase(captcha))) {
    response.sendRedirect("toLogin?err=400");
    return;
   } else if ((now.getTime()-codeTime)/1000/60>5) {
    //验证码有效时长为5分钟
    response.sendRedirect("toLogin?err=401");
    return;
   }else {
    session.removeAttribute("simpleCaptcha");
   }
  }
  chain.doFilter(request, response);
 }
}