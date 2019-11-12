package com.dermacon.export;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class FileExporterTest {

    @Test
    public void testRemoveFileExtension() {
        Assert.assertEquals("input", FileExporter.removeExtension("home/input" +
                ".txt"));
        Assert.assertEquals("input", FileExporter.removeExtension("input.txt"));
        Assert.assertEquals("input",
                FileExporter.removeExtension("./input" +
                ".txt"));
    }

}