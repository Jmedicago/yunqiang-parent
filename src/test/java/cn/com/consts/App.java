package cn.com.consts;

import cn.com.consts.component.DataGrid;
import cn.com.consts.component.TextBox;
import cn.com.consts.template.Form;
import cn.com.consts.template.Panel;

public class App {

    public static void main(String[] args) {
        Panel home = new Panel();

        DataGrid table = new DataGrid("table");
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

        home.add(form);
        home.add(table);

        String page = home.build();
        System.out.println(page);
    }

}
