package com.devsmart.kicadbom;


import au.com.bytecode.opencsv.CSVWriter;
import com.google.common.base.Charsets;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Iterables;
import com.google.common.collect.Multimap;

import java.io.*;
import java.util.Collection;

public class BOMCSVWriter {

    private static final Joiner JOINER = Joiner.on(", ");
    private static final Function<? super Component, String> mToRefName = new Function<Component, String>() {
        @Override
        public String apply(Component input) {
            return input.ref;
        }
    };

    private Multimap<String, Component> mComponentsByMPN;
    private Writer mWriter;

    public BOMCSVWriter(Multimap<String, Component> componentByMPN, Writer writer) {
        mComponentsByMPN = componentByMPN;
        mWriter = writer;
    }

    public BOMCSVWriter(Multimap<String, Component> componentByMPN, OutputStream output) {
        mComponentsByMPN = componentByMPN;
        mWriter = new OutputStreamWriter(output, Charsets.UTF_8);
    }


    public void write() throws IOException {

        CSVWriter writer = new CSVWriter(mWriter);
        String[] line;

        //write header
        line = new String[]{
            "MPN", "Quantity", "Description", "Refs"
        };

        writer.writeNext(line);

        for(String mpn : mComponentsByMPN.keySet()){
            Collection<Component> components = mComponentsByMPN.get(mpn);
            Component example = Iterables.getFirst(components, null);

            line[0] = mpn;
            line[1] = String.valueOf(components.size());
            line[2] = example.value;
            line[3] = JOINER.join(Iterables.transform(components, mToRefName));

            writer.writeNext(line);
        }

        writer.close();
    }
}
