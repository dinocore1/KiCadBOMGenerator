package com.devsmart.kicadbom;

import com.google.common.io.Resources;
import com.vseravno.solna.SolnaParser;
import org.junit.Test;

import java.io.InputStream;

import static org.junit.Assert.*;


public class ParserTest {

    @Test
    public void testParse() throws Exception {
        InputStream in = Resources.getResource("GoGrow.xml").openStream();

        ComponentsHandler handler = new ComponentsHandler();
        SolnaParser xmlParser = new SolnaParser();
        xmlParser.addHandler("/export/components/comp", handler);

        xmlParser.parse(in);
        in.close();
        
        assertTrue(handler.components.size() > 0);
    }
}
