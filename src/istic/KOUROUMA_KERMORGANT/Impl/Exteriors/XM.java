package istic.KOUROUMA_KERMORGANT.Impl.Exteriors;

import istic.KOUROUMA_KERMORGANT.Impl.Color;
import istic.KOUROUMA_KERMORGANT.Impl.Exterior;

import java.io.PrintStream;

public class XM extends Exterior {

    public String toString() {
        return "XM";
    }

    public Color paintColor;

    @Override
    public void printDescription(PrintStream s) {
        s.println("<h1>EXTERIOR</h1></br><h2>XM</h2>");
    }
}