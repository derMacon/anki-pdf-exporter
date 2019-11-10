package com.dermacon.fileIO;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

public class Filehandler {

    public static void writeFile(String path, String content) throws IOException {
        FileUtils.writeStringToFile(new File(path), content);
//        System.out.println("todo write \"" + content + " to file \"" + path + "\"");
    }

    public static String read(String path) throws IOException {
        return FileUtils.readFileToString(new File(path), Charset.defaultCharset());
    }

}
