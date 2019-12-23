package cn.com.consts.component;

import cn.com.consts.Component;
import cn.com.consts.Tags;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class Script extends Component {

    private String src;

    private String type;

    public Script() {
    }

    public Script(String src) {
        this.type = "text/javascript";
        this.src = src;
    }

    @Override
    public Document build() {
        Document document = DocumentHelper.createDocument();
        Element scriptElement = document.addElement(Tags.SCRIPT);
        scriptElement.addAttribute("src", this.src);
        scriptElement.addAttribute("type", this.type);
        return document;
    }

    public void setSrc(String src) {
        this.src = src;
    }

}
