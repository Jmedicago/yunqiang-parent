package cn.com.consts;

import cn.com.consts.component.ComboBox;
import cn.com.consts.component.DataGrid;
import cn.com.consts.component.Link;
import cn.com.consts.component.TextBox;
import cn.com.consts.template.Form;
import cn.com.consts.template.Body;
import cn.com.consts.component.Head;
import cn.com.consts.component.Html;
import org.dom4j.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App {

    public void t1() {
        Body body = new Body();

        DataGrid table = new DataGrid();
        Form form = new Form();
        form.setAction("/article/store.do");

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

    public void t5() {
        Html html = new Html();
        Head head = new Head();
        head.setTitle("云强企业管理系统");

        List<Link> links = new ArrayList<>();
        Link link1 = new Link("https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css");
        links.add(link1);
        head.addLinks(links);

        html.setHead(head);
        Body body = new Body();
        DataGrid table = new DataGrid();
        Form form = new Form();
        form.setAction("/article/store.do");
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
        System.out.println(document.getRootElement().asXML());
    }

    public static void main(String[] args) {
        new App().t5();
    }

}
