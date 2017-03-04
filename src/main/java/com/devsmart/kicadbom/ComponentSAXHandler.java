package com.devsmart.kicadbom;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class ComponentSAXHandler extends DefaultHandler {

    private Component mComponent;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if("comp".equalsIgnoreCase(qName)){
            String ref = attributes.getValue("ref");
            mComponent = new Component(ref);
        }
    }


}
