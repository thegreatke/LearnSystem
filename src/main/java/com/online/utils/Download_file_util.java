package com.online.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class Download_file_util {
    public void downloadFile(HttpServletResponse response, String fileName) {

        File file = new File("./uploaded_files_here/", fileName);
        if (file.exists()) {

            response.setContentType("application/force-download");
            //设置头信息，filename表示前端下载时的文件名
            //response.addHeader("Content-Disposition",String.format("attachment; filename=\"%s\"", file.getName()));
            //进行读写操作

            try {
                response.addHeader("Content-Disposition",
                        " attachment;filename=" +
                                new String(fileName.getBytes("UTF8"), "ISO8859-1"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
                System.out.println("success");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
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
}

