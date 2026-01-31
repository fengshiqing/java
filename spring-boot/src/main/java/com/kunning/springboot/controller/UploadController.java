package com.kunning.springboot.controller;

import com.kunning.springboot.model.resp.Resp;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 具体看这个：https://blog.csdn.net/qq_28089993/article/details/76854112
 */
@Controller
public class UploadController {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(UploadController.class);

    /**
     * 图片文件上传
     */
    @ResponseBody
    @PostMapping (value = "/uploadPhoto")
    public Resp uploadPhoto(MultipartFile file, HttpServletRequest request) throws IllegalStateException, IOException {
        LOGGER.info("【uploadPhoto】【开始执行】");

        if (file != null) {
            String originalFilename = file.getOriginalFilename(); // 上传的原始文件名称
            if (originalFilename != null) {
                Pattern pattern = Pattern.compile(".+(.JPEG|.jpeg|.JPG|.jpg|.PNG|.png|.GIF|.gif)$");
                Matcher matcher = pattern.matcher(originalFilename); // 正则表达式验证文件名称是否符合规范
                if (matcher.find()) {
                    int dotIndex = originalFilename.lastIndexOf("."); // 最后一个点号的索引
                    String fileName = originalFilename.substring(0, dotIndex); // 不带后缀的文件名
                    String fileType = originalFilename.substring(dotIndex); // 文件后缀类型，带点号
                    String trueFileName = fileName + "_" + System.currentTimeMillis() + fileType; // 设置文件名称上传到服务器上的名称
                    LOGGER.info("【文件的新名称：{}】", trueFileName);
                    String realPath = request.getSession().getServletContext().getRealPath("/"); // 项目在容器中实际发布运行的根路径
                    String path = realPath +/*System.getProperty("file.separator")+*/trueFileName; // 设置图片文件的存放路径
                    LOGGER.info("【存放图片文件的路径：{}】", path);

                    file.transferTo(new File(path));  // 将文件转存到指定的路径
                    LOGGER.info("【文件成功上传到指定目录下】");
                } else {
                    LOGGER.error("【上传文件的名称或者类型不正确，请重新上传】");
                    return new Resp(400, "【上传文件的名称或者类型不正确，请重新上传】");
                }
            } else {
                LOGGER.error("【上传的文件名为空】");
                return new Resp(400, "【上传的文件名为空】");
            }
        } else {
            LOGGER.error("【上传的文件为空】");
            return new Resp(400, "【上传的文件为空】");
        }

        LOGGER.info("【uploadPhoto】【开始执行】【执行成功】");
        return new Resp(200, "上传成功");
    }

}
