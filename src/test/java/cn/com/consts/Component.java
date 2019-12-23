package cn.com.consts;

import java.io.*;

/**
 * 组件
 */
public abstract class Component implements Composite {

    @Override
    public String reader() throws IOException {
        String content = "";
        String path = "G:/workspace/yunqiang-parent/src/main/webapp/WEB-INF/views/test/" + this.getName() + ".jsp";

        File file = new File(path);
        File fileParent = file.getParentFile();
        if (!fileParent.exists()) {
            fileParent.mkdirs();
        }
        file.createNewFile();

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = null;
        while ((line = reader.readLine()) != null) {
            content += line;
        }
        return content;
    }

    @Override
    public void writer(String content) throws IOException {
        String path = "G:/workspace/yunqiang-parent/src/main/webapp/WEB-INF/views/test/" + this.getName() + ".jsp";
        FileWriter file = new FileWriter(path);
        BufferedWriter writer = new BufferedWriter(file);
        writer.write(content);
        writer.flush();
        writer.close();
    }

    private String getName() {
        return this.getClass().getSimpleName().toLowerCase();
    }

}
