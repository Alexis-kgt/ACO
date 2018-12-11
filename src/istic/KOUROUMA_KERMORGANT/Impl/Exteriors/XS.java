package istic.KOUROUMA_KERMORGANT.Impl.Exteriors;

import istic.KOUROUMA_KERMORGANT.Impl.*;

import java.io.PrintStream;

public class XS extends Exterior {

    public XS() {
        setColorName("RED");

    }

    @Override
    protected double getPrice() {
        return 800;
    }

    @Override
    public void printDescription(PrintStream s) {
        s.println("<h1>EXTERIOR</h1></br><h2>XS</h2>");
    }
}
