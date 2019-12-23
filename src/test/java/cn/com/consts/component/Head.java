package cn.com.consts.component;

import cn.com.consts.Component;
import cn.com.consts.Tags;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.List;

public class Head extends Component {

    private String title;

    private List<Link> links;

    private List<Script> scripts;

    @Override
    public Document build() {
        Document document = DocumentHelper.createDocument();
        Element headElement = document.addElement(Tags.HEAD);

        Element titleElement = headElement.addElement(Tags.TITLE);
        titleElement.setText(this.title);

        if (links != null) {
            for (Link link : links) {
                headElement.add(link.build().getRootElement());
            }
        }

        if (scripts != null) {
            for (Script script : scripts) {
                headElement.add(script.build().getRootElement());
            }
        }
        return document;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void addLinks(List<Link> links) {
        this.links = links;
    }

    public void addScripts(List<Script> scripts) {
        this.scripts = scripts;
    }

}
