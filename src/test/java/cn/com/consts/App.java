package cn.com.consts;

import cn.com.consts.component.*;
import cn.com.consts.template.Form;
import cn.com.consts.template.Body;
import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App {

    public void t1() {
        Body body = new Body();

        DataGrid table = new DataGrid();
        Form form = new Form("/article/store.do", "post");

        TextBox title = new TextBox("title");
        title.setLabel("title");
        title.setPlaceholder("place input text..");
        form.add(title);

        TextBox subtitle = new TextBox("subtitle");
        subtitle.setLabel("subtitle");
        subtitle.setPlaceholder("place input text..");
        form.add(subtitle);

        body.add(form);


        Html html = new Html();
        html.setBody(body);

        System.out.println(html.build().getRootElement().asXML());
    }

    public void t2() {
        DataGrid dataGrid = new DataGrid();
        Document content = dataGrid.build();
        System.out.println(content);
    }

    public void t3() {
        DataGrid dataGrid = new DataGrid();
        try {
            dataGrid.writer("<h1>${title}</h1>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void t4() {
        ComboBox comboBox = new ComboBox();
        comboBox.setName("theme");
        List<Map<String, String>> options = new ArrayList<>();
        Map<String, String> black = new HashMap<>();
        black.put("value", "black");
        black.put("text", "黑色");
        options.add(black);
        Map<String, String> blue = new HashMap<>();
        blue.put("value", "blue");
        blue.put("text", "蓝色");
        options.add(blue);
        comboBox.setOptions(options);
        Document xml = comboBox.build();
        System.out.println(xml);

        //JFrame
    }

    public void t5() throws IOException {
        Html html = new Html();
        Head head = new Head();
        head.setTitle("云强企业管理系统");

        List<Link> links = new ArrayList<>();
        Link link1 = new Link("https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css");
        links.add(link1);
        head.addLinks(links);

        List<Script> scripts = new ArrayList<>();
        Script script1 = new Script("https://cdn.staticfile.org/jquery/3.4.1/jquery.min.js");
        scripts.add(script1);
        head.addScripts(scripts);

        html.setHead(head);
        Body body = new Body();
        DataGrid table = new DataGrid();
        Form form = new Form("/article/store.do", "post");
        TextBox title = new TextBox("title");
        title.setLabel("title");
        title.setPlaceholder("place input text..");
        form.add(title);
        TextBox subtitle = new TextBox("subtitle");
        subtitle.setLabel("subtitle");
        subtitle.setPlaceholder("place input text..");
        form.add(subtitle);
        body.add(form);
        html.setBody(body);
        Document document = html.build();

        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("utf-8");
        format.setSuppressDeclaration(true); //是否设置声明文档类型
        format.setIndent(true); // 是否又首行缩进
        format.setNewlines(true); // 是否设置换行

        File file = new File("G:/workspace/yunqiang-parent/src/main/webapp/WEB-INF/views/test/index.ftl");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
        XMLWriter writer = new XMLWriter(bufferedOutputStream, format);
        writer.write(document);
        writer.flush();


        //System.out.println(document.getRootElement().asXML());
    }

    public static void main(String[] args) {
        try {
            new App().t5();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
