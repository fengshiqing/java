package com.kunning.web.controller;

import com.fengshiqing.common.model.Resp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Controller
public class UploadController {

    /**
     * 图片文件上传
     */
    @ResponseBody
    @RequestMapping(value = "/upload-photo", method = RequestMethod.POST)
    public Resp uploadPhoto(MultipartFile file, HttpServletRequest request) throws IllegalStateException, IOException {
        log.info("【uploadPhoto】【开始执行】");

        if (file != null) {
            String originalFilename = file.getOriginalFilename();// 上传的原始文件名称
            Pattern pattern = Pattern.compile(".+(.JPEG|.jpeg|.JPG|.jpg|.PNG|.png|.GIF|.gif)$");
            Matcher matcher = pattern.matcher(originalFilename);// 正则表达式验证文件名称是否符合规范
            if (matcher.find()) {
                int dotIndex = originalFilename.lastIndexOf(".");// 最后一个点号的索引
                String fileName = originalFilename.substring(0, dotIndex);// 不带后缀的文件名
                String fileType = originalFilename.substring(dotIndex);// 文件后缀类型，带点号
                String trueFileName = fileName + "_" + System.currentTimeMillis() + fileType;// 设置文件名称上传到服务器上的名称
                log.info("【文件的新名称：{}】", trueFileName);
                String realPath = request.getSession().getServletContext().getRealPath("/");// 项目在容器中实际发布运行的根路径
                String path = realPath +/*System.getProperty("file.separator")+*/trueFileName;// 设置图片文件的存放路径
                log.info("【存放图片文件的路径：{}】", path);

                file.transferTo(new File(path));// 将文件转存到指定的路径
                log.info("【文件成功上传到指定目录下】");
            } else {
                log.error("【上传文件的名称或者类型不正确，请重新上传】");
                return null;
            }
        } else {
            log.error("【上传文件为空】");
            return null;
        }

        log.info("【uploadPhoto】【开始执行】【执行成功】");
        return new Resp(200, "success");
    }

}
