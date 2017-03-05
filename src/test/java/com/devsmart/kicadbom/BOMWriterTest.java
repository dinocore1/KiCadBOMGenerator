package com.devsmart.kicadbom;


import com.google.common.collect.ArrayListMultimap;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.StringWriter;
import java.util.ArrayList;

public class BOMWriterTest {


    @Test
    public void writerTest() throws Exception {
        ArrayList<Component> components = new ArrayList<Component>();
        {
            Component c = new Component("C1");
            c.value = "10uF";
            c.fields.put("MPN", "C10UF");
            components.add(c);
        }
        {
            Component c = new Component("C2");
            c.value = "10uF";
            c.fields.put("MPN", "C10UF");
            components.add(c);
        }
        {
            Component c = new Component("R1");
            c.value = "1K";
            c.fields.put("MPN", "R10K");
            components.add(c);
        }

        ArrayListMultimap<String, Component> componentsByMPN = ArrayListMultimap.create();

        for(Component c : components) {
            String mpn = c.getMPN();
            if(mpn != null) {
                componentsByMPN.put(mpn, c);
            }
        }

        StringWriter writer = new StringWriter();

        BOMCSVWriter BOMWriter = new BOMCSVWriter(componentsByMPN, writer);
        BOMWriter.write();


    }
}
