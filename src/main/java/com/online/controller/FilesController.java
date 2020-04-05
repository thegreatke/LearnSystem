package com.online.controller;

import com.online.utils.Download_file_util;
import com.online.utils.Upload_file_util;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

@ResponseBody
@Controller
public class FilesController {

    //文件上传
    @PostMapping("/uploadfile")
    public void upload(@RequestParam("uploadFile") MultipartFile uploadFile, HttpServletResponse response) throws Exception {
        //此处应有检查上传文件的后缀名.
        Upload_file_util upload_file_util = new Upload_file_util();
        String fileNameStus = upload_file_util.upload(uploadFile);//上传成功则返回存放文件的路径

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();

        if (fileNameStus.equals("error_01")) {
            out.print("<script>alert('上传失败，上传文件为空。');history.go(-1);</script>");
        } else {
            out.print("<script>alert('上传成功');history.go(-1);</script>");
        }
    }

    //文件下载
    @RequestMapping(value = "/downloadfile")

    public void downloadFile(HttpServletResponse response, @RequestParam("fileName") String fileName) {
        Download_file_util download_file_util = new Download_file_util();
        download_file_util.downloadFile(response, fileName);
    }


    //文件删除
    @RequestMapping(value = "/deletefile")
    public String deletefile(HttpServletResponse response, @RequestParam("fileName") String fileName) {

        File file = new File("./uploaded_files_here/", fileName);
        file.delete();
        return "delete done!";

    }


    //文件列表json数组
    @RequestMapping(value = "/listfile")
    public JSONArray listfile() {

        JSONArray jsonArray = new JSONArray();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        File dir = new File("./uploaded_files_here/");
        File[] files = dir.listFiles();
        for (int i = 0; i < files.length; i++) {

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("filename", files[i].getName());
            jsonObject.put("time", simpleDateFormat.format(files[i].lastModified())
            );
            jsonArray.add(jsonObject);
        }

        return jsonArray;

    }
}
