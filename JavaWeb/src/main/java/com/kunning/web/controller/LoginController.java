package com.kunning.web.controller;

import com.fengshiqing.common.model.User;
import com.kunning.web.service.UserService;
import com.kunning.web.utils.VerificationCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

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

    /**
     * 功能描述：注册账号/创建账号
     */
    @RequestMapping(value = "/createAccount")
    public Map<String, String> createAccount(User user) {
        LOGGER.info("【createAccount】【开始执行】");
        int num = userService.addUser(user);
        Map<String, String> map = new HashMap<>(4);
        map.put("rtnCode", num+"");
        map.put("rtnMsg", "创建账户成功。");
        LOGGER.info("【createAccount】【结束执行】");
        return map;
    }

    /**
     * 功能描述：注销，退出登录
     */
    public void signout(){

    }

}
