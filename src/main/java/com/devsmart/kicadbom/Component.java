package com.devsmart.kicadbom;


import java.util.HashMap;
import java.util.Map;

public class Component {

    public static final String KEY_MPN = "MPN";

    public final String ref;
    String value;
    LibSource libSource;
    Map<String, String> fields = new HashMap<String, String>();


    public Component(String ref) {
        this.ref = ref;
    }

    public String getMPN() {
        String mpn = fields.get(KEY_MPN);
        return mpn;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        Component other = (Component) obj;
        return this.ref.equals(other.ref);
    }

    @Override
    public int hashCode() {
        return ref.hashCode();
    }
}
