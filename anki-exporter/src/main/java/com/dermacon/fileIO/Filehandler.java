package com.dermacon.fileIO;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

public class Filehandler {

    public static void writeFile(String path, String content) throws IOException {
        File outputDir = new File(new File(path).getParent() + File.separator);
        System.out.println("out: " + outputDir);
        if (!outputDir.exists()) {
//            System.out.println("create dir: " + outputDir);
            FileUtils.forceMkdir(outputDir);
        }
        FileUtils.writeStringToFile(new File(path), content);
//        System.out.println("write \"" + content + " to file \"" + path + "\"");
    }

    public static String read(String path) throws IOException {
        return FileUtils.readFileToString(new File(path), Charset.defaultCharset());
    }

    public static void saveDelete(String path) {
        if (new File(path).exists()) {
            System.out.println("del: " + path);
        }

    }

}
