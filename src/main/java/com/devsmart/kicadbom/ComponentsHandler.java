package com.devsmart.kicadbom;

import com.vseravno.solna.SolnaHandler;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.LinkedList;

public class ComponentsHandler implements SolnaHandler<Element> {

    final LinkedList<Component> components = new LinkedList<Component>();

    private static String getElementValue(String tagName, Element e) {
        return e.getElementsByTagName(tagName).item(0).getFirstChild().getNodeValue();
    }

    private static void loadFields(Node fields, Component c) {
        if(fields != null) {
            NodeList childNodes = fields.getChildNodes();

            for (int i = 0; i < childNodes.getLength(); i++) {
                Node node = childNodes.item(i);

                if (node instanceof Element) {
                    String key = node.getAttributes().getNamedItem("name").getTextContent();
                    String value = node.getLastChild().getTextContent().trim();
                    c.fields.put(key, value);
                }

            }
        }
    }

    private static LibSource parseLibSource(Node libsource) {
        NamedNodeMap attributes = libsource.getAttributes();
        return new LibSource(
                attributes.getNamedItem("lib").getTextContent(),
                attributes.getNamedItem("part").getTextContent()
        );
    }

    @Override
    public void handle(Element element) throws Exception {

        final String ref = element.getAttribute("ref");
        Component c = new Component(ref);
        c.value = getElementValue("value", element);
        loadFields(element.getElementsByTagName("fields").item(0), c);
        c.libSource = parseLibSource(element.getElementsByTagName("libsource").item(0));

        components.add(c);

    }


}
