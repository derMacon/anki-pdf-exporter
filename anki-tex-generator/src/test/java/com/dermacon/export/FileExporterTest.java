package com.dermacon.export;

import org.junit.Assert;
import org.junit.Test;

public class FileExporterTest {

    @Test
    public void testRemoveFileExtension() {
        Assert.assertEquals("input/", FileExporter.normalizePath("./input/"));
        Assert.assertEquals("input", FileExporter.normalizePath("home/input" +
                ".txt"));
        Assert.assertEquals("input", FileExporter.normalizePath("input.txt"));
        Assert.assertEquals("input",
                FileExporter.normalizePath("./input.txt"));
    }

    @Test
    public void testNormalize() {
        String input = "<span style=asdf sadf sdf>text</span>";
        Assert.assertEquals("test", FileExporter.normalize(input));
    }

}