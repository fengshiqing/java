package com.kunning.controller;

import com.kunning.utils.VerificationCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
public class LoginController {

    @RequestMapping("/getVerifiCode")
    @ResponseBody
    public void getVerifiCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 安全方面考虑：应该限制同一用户，在一分钟内（或者三秒内）只能获取一起
        /*
         * 1.生成验证码
         * 2.把验证码上的文本存在session中
         * 3.把验证码图片发送给客户端
         *
         */
        VerificationCode verCode = new VerificationCode();     //用我们的验证码类，生成验证码类对象
        BufferedImage image = verCode.getImage();  //获取验证码
        request.getSession().setAttribute("text", verCode.getText()); //将验证码的文本存在session中
        verCode.output(image, response.getOutputStream());//将验证码图片响应给客户端
    }

}
