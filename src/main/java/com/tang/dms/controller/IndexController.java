package com.tang.dms.controller;

import com.tang.dms.entity.Notice;
import com.tang.dms.entity.bo.NoticeBO;
import com.tang.dms.service.NoticeService;
import com.tang.dms.util.ImageCode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @description: 主页控制器
 * @author: DXT
 * @create: 2021-01-12 21:52
 */
@Controller
public class IndexController {

/*    @PostMapping("/user/login")
    public String login(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            Model model, HttpSession session){
        if (!StringUtils.isEmpty(username) && "123456".equals(password)){
            session.setAttribute("loginUser",username);
            return "redirect:/main.html";
        }else{
            model.addAttribute("msg","用户名或密码错误");
            return "login";
        }
    }
    @PostMapping("/user/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/main.html";
    }*/

    @Resource
    private NoticeService noticeService;

    @RequestMapping("/toError")
    public String error(){
        return "view/error";
    }

    @RequestMapping({"/","/index"})
    public String index(Model model){
        List<NoticeBO> list = noticeService.getNoticeList();
        System.out.println(list);
        model.addAttribute("noticeList",list);
        return "view/index/index";
    }
    @RequestMapping("/toLogin")
    public String toLogin(){
        return "view/login";
    }

    @GetMapping("/getNoticeById")
    public Map<String,Object> getNotices(){


        return null;
    }

    @RequestMapping("/getInfo")
    @ResponseBody
    public Authentication getInfo(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication;
    }

    @RequestMapping(value = "/images/imagecode")
    public String imagecode(HttpServletRequest request, HttpServletResponse response) throws Exception {
        OutputStream os = response.getOutputStream();
        Map<String,Object> map = ImageCode.getImageCode(60, 20, os);
        String simpleCaptcha = "simpleCaptcha";
        request.getSession().setAttribute(simpleCaptcha, map.get("strEnsure").toString().toLowerCase());
        request.getSession().setAttribute("codeTime",new Date().getTime());
        try {
            ImageIO.write((BufferedImage) map.get("image"), "JPEG", os);
        } catch (IOException e) {
            return "";
        }
        return null;
    }

    @RequestMapping(value = "/checkcode")
    @ResponseBody
    public String checkcode(HttpServletRequest request, HttpSession session) throws Exception {
        String checkCode = request.getParameter("code");
        Object cko = session.getAttribute("simpleCaptcha") ; //验证码对象
        if(cko == null){
            request.setAttribute("errorMsg", "验证码已失效，请重新输入！");
            return "验证码已失效，请重新输入！";
        }
        String captcha = cko.toString();
        Date now = new Date();
        Long codeTime = Long.valueOf(session.getAttribute("codeTime")+"");
        if(StringUtils.isEmpty(checkCode) || captcha == null ||  !(checkCode.equalsIgnoreCase(captcha))) {
            request.setAttribute("errorMsg", "验证码错误！");
            return "验证码错误！";
        } else if ((now.getTime()-codeTime)/1000/60>5) {
            //验证码有效时长为5分钟
            request.setAttribute("errorMsg", "验证码已失效，请重新输入！");
            return "验证码已失效，请重新输入！";
        }else {
            session.removeAttribute("simpleCaptcha");
            return "1";
        }
    }
}
