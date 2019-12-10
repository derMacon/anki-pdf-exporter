package com.dermacon.model.data.nodes.document.headings;

import com.dermacon.model.data.nodes.sideElem.PlainText;
import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class SectionTest {

    @Test
    public void testEquals_simple() {
        Section sec1 = new Section("fst", new PlainText("hello"));
        Section sec2 = new Section("fst", new PlainText("world"));
        Assert.assertEquals(sec1, sec2);
    }

    @Test
    public void testEquals_sameParent() {
        Section sec1 = new Section("fst",
                new SubSection("subfst",
                        new PlainText("hello")
                ));
        Section sec2 = new Section("fst",
                new SubSection("subsnd",
                        new PlainText("world")
                ));
        Assert.assertNotEquals(sec1, sec2);
    }


    @Test
    public void testIterator_simple() {
        Section sec1 = new Section("fst", new PlainText("hello"));

        SubSection sub1 = new SubSection("subsnd",
                new PlainText("world")
        );

        Section sec2 = new Section("fst", sub1);

        Iterator<Section> it = sec1.headingIterator();
        Assert.assertFalse(it.hasNext());

        it = sec2.headingIterator();
        Assert.assertTrue(it.hasNext());
        Assert.assertEquals(sub1, it.next());
        Assert.assertFalse(it.hasNext());
    }


}