package com.vgit.yunqiang.service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface TestService {

    String reader(HttpServletRequest request) throws IOException;

    void writer(String content, HttpServletRequest request) throws IOException;
}
