package countLine;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

/**
 * @Author Ye_Wenda
 * @Date 1/9/2017
 */
public class Logger {

    private String logPath = "\\src\\main\\resources\\log\\countLine.log";

    public Logger() {
        logPath = System.getProperty("user.dir") + logPath;
    }

    private FileWriter getWriter() throws IOException {
        File f = new File(logPath);
        if (!f.exists()) {
            f.createNewFile();
        }
        return new FileWriter(f, true);
    }

    public void info(String info) throws IOException {
        FileWriter writer = this.getWriter();
        Date date = new Date();
        System.out.println(date + ": " + info);
        writer.write(date + ": " + info + "\n");
        writer.flush();
        writer.close();
    }

}
