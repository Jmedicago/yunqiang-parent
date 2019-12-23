package com.vgit.yunqiang.service.impl;

import com.vgit.yunqiang.service.TestService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

@Service
public class TestServiceImpl implements TestService {

    @Override
    public String reader(HttpServletRequest request) throws IOException {
        String content = "";
        String path = request.getSession().getServletContext().getRealPath("");
        path += "/WEB-INF/views/test/index.jsp";

        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
        String line = null;
        while ((line = reader.readLine()) != null) {
            content += line;
        }

        System.out.println(content);
        return content;
    }

    @Override
    public void writer(String content, HttpServletRequest request) throws IOException {
        String path = request.getSession().getServletContext().getRealPath("");
        path += "/WEB-INF/views/test/index.jsp";

        FileWriter file = new FileWriter(path);
        BufferedWriter writer = new BufferedWriter(file);
        writer.write(content);
        writer.flush();
        writer.close();
    }

}
