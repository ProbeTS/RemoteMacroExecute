package com.example.demo.Controller;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
public class proController {

    @ResponseBody
    @GetMapping("/download")
    public void downloadFile(@RequestParam("fileName") String fileName, HttpServletRequest request, HttpServletResponse response) {
//        String dirName = "D:\\WeChatFile\\111.dotm";
        // D:\WeChatFile\
        // "/usr/server/WeChatFile/111.dotm"
        response.setCharacterEncoding(request.getCharacterEncoding());
        response.setContentType("application/octet-stream");
        FileInputStream fis = null;
        String strName =  "/usr/server/WeChatFile/" + fileName;
        System.out.println(strName);
        try {
            File file = new File(strName);
            fis = new FileInputStream(file);
            response.setHeader("Content-Disposition", "attachment; filename="+file.getName());
            IOUtils.copy(fis,response.getOutputStream());
            response.flushBuffer();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
