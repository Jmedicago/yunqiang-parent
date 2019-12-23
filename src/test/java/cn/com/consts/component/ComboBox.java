package cn.com.consts.component;

import cn.com.consts.Component;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ComboBox extends Component {

    private String name;

    private List<Map<String, String>> options;

    @Override
    public Document build() {
        Document document = DocumentHelper.createDocument();
        document.addComment("This is a select for dom4j");
        Element selectElement = document.addElement("select");
        selectElement.addAttribute("name", name);

        for (Map<String, String> option : options) {
            Element optionElement = selectElement.addElement("option");
            optionElement.addAttribute("value", option.get("value"));
            optionElement.setText(option.get("text"));
        }

        return document;
    }

    private void create() {
        try {
            Document document = DocumentHelper.createDocument();
            document.addComment("This is a select for dom4j");
            Element selectElement = document.addElement("select");
            selectElement.addAttribute("name", name);

            for (Map<String, String> option : options) {
                Element optionElement = selectElement.addElement("option");
                optionElement.addAttribute("value", option.get("value"));
                optionElement.setText(option.get("text"));
            }

            OutputFormat format = OutputFormat.createPrettyPrint();
            XMLWriter writer = new XMLWriter(new FileOutputStream("G:/workspace/yunqiang-parent/src/main/webapp/WEB-INF/views/test/component/combobox.jsp"), format);
            writer.write(document);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOptions(List<Map<String, String>> options) {
        this.options = options;
    }

}
