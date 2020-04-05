package com.online.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;

public class Upload_file_util {
    public String upload(MultipartFile uploadFile) throws Exception {
        if (null == uploadFile) {
            return "error_01";//空值
        }

        String fileName = uploadFile.getOriginalFilename();
        String fileType = fileName.substring(fileName.lastIndexOf(".") + 1);

        File fileDir = new File("uploaded_files_here");
        if (!fileDir.exists()) {
            fileDir.mkdir();
        }
        String path = fileDir.getAbsolutePath();
        File file_new = new File(fileDir.getAbsolutePath(), fileName);
        uploadFile.transferTo(file_new);

        long millisec = new Date().getTime();//原文出自【易百教程】，商业转载请联系作者获得授权，非商业请保留原文链接：https://www.yiibai.com/java_io/file.setlastmodified_long_time.html


        file_new.setLastModified(millisec);

        String fileNameStus = file_new.getAbsolutePath();
        return fileNameStus;
    }
}



