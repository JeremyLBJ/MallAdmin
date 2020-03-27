package com.lhd.sys.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lhd.sys.common.WebUntils;
import com.lhd.sys.untils.ImageVerificationCode;

@RequestMapping("/imgCode")
@Controller
public class ImgCode {
	
	@RequestMapping("/getVerifiCode")
    @ResponseBody
    public void getVerifiCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /*
             1.生成验证码
             2.把验证码上的文本存在session中
             3.把验证码图片发送给客户端
             */
        ImageVerificationCode ivc = new ImageVerificationCode() ;     //用我们的验证码类，生成验证码类对象
        BufferedImage image = ivc.getImage() ;  //获取验证码
        request.getSession().removeAttribute("text") ;
        WebUntils.getSession().removeAttribute("code") ;
        WebUntils.getSession().setAttribute("code", ivc.getText()) ;
        request.getSession().setAttribute("text", ivc.getText()) ; //将验证码的文本存在session中
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
//        ivc.output(image, response.getOutputStream()) ;//将验证码图片响应给客户端
    }

}
