package cn.com.consts.component;

import cn.com.consts.Component;
import cn.com.consts.Tags;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class Link extends Component {

    private String href;

    private String rel;

    private String type;

    public Link() {
    }

    public Link(String href) {
        this.rel = "stylesheet";
        this.type = "text/css";
        this.href = href;
    }

    @Override
    public Document build() {
        Document document = DocumentHelper.createDocument();
        Element linkElement = document.addElement(Tags.LINK);
        linkElement.addAttribute("href", this.href);
        linkElement.addAttribute("rel", this.rel);
        linkElement.addAttribute("type", this.type);
        return document;
    }

    public void setHref(String href) {
        this.href = href;
    }

}
