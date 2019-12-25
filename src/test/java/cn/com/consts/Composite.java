package cn.com.consts;

import org.dom4j.Document;

import java.io.IOException;

public interface   Composite {

    Document build();

    String reader() throws IOException;

    void writer(String content) throws IOException;

}
