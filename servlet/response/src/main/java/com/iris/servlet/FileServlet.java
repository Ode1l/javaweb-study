package com.iris.servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

public class FileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 1. 要获取下载文件的路径；
        // C:\Users\Ode1l\Pictures\Saved Pictures\Ahri.jpg
        String realPath = "C:\\Users\\Ode1l\\Pictures\\Saved Pictures\\Ahri.jpg";
        System.out.println("file download path: " + realPath);
        // 2. 下载的文件名是啥？
        String fileName = realPath.substring(realPath.lastIndexOf("\\") + 1);
        // 3. 设置想办法让浏览器能够支持下载我们需要的东西；
        resp.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
        // 4. 获取下载文件的输入流；
        FileInputStream in = new FileInputStream(realPath);
        // 5. 创建缓冲区；
        int len = 0;
        byte[] buffer = new byte[1024];
        // 6. 获取OutputStream对象；
        ServletOutputStream out = resp.getOutputStream();
        // 7. 将FileOutputStream流写入到buffer缓冲区；
        while ((len = in.read(buffer)) > 0) {
            // 8. 使用OutputStream将缓冲区中的数据输出到客户端！
            out.write(buffer, 0, len);
        }

        in.close();
        out.close();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
