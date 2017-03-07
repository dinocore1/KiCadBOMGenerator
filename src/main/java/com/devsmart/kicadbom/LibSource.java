package com.devsmart.kicadbom;


public class LibSource {

    public static final LibSource RESISTOR = new LibSource("device", "R");


    private final String lib;
    private final String part;

    public LibSource(String lib, String part) {
        this.lib = lib;
        this.part = part;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        LibSource other = (LibSource) obj;
        return this.lib.equals(other.lib) && this.part.equals(other.part);
    }

    @Override
    public int hashCode() {
        return lib.hashCode() ^ part.hashCode();
    }

    @Override
    public String toString() {
        return lib + ":" + part;
    }
}
