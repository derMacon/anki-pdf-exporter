package com.dermacon.export;

import com.dermacon.model.generate.Parser;

/**
 * Only for testing
 */
public class MockExporter extends Exporter {

    private final String in;
    private final StringBuilder out;

    public MockExporter(Parser parser, String in, StringBuilder out) {
        super(parser);
        this.in = in;
        this.out = out;
    }

    @Override
    protected String read() {
        return in;
    }

    @Override
    protected void write(String content) {
        out.append(content);
    }
}
