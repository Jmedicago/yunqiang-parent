package cn.com.consts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 模板
 */
public abstract class Template implements Composite {

    protected List<Composite> children;

    public Template() {
        this.children = new ArrayList<>();
    }

    public void add(Composite component) {
        this.children.add(component);
    }

    public void remove(Composite component) {
        this.children.remove(component);
    }

    public Composite getChild(int index) {
        return this.children.get(index);
    }

    @Override
    public String reader() throws IOException {
        return null;
    }

    @Override
    public void writer(String content) throws IOException {

    }

}
