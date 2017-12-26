package FileWriter;

import java.io.FileWriter;
import java.io.IOException;


public class FileWriterClass {
    public static java.io.FileWriter filewriter;

    static {
        try {
            String filePath = "Trace.txt";
            filewriter = new FileWriter(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
