package Threads;

import java.io.*;
import java.util.*;

public class LogFile {

    private Writer out;

    public LogFile(File f) throws IOException {
        FileWriter fw = new FileWriter(f);
        this.out = new BufferedWriter(fw);
    }
    // 2가지 방법
    // writeEntry에 synchronized
    // LogFile 전체에 synchronized
    public void writeEntry(String message) throws IOException{
        synchronized(out) { // synchronized(this)
            Date d = new Date();
            out.write(d.toString());
            out.write("\t");
            out.write(message);
            out.write("\r\n");
        }
    }

    public void close() throws IOException {
        out.flush();
        out.close();
    }
}
