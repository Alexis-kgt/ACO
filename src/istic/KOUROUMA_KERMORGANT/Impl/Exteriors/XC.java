package istic.KOUROUMA_KERMORGANT.Impl.Exteriors;

import istic.KOUROUMA_KERMORGANT.Impl.Color;
import istic.KOUROUMA_KERMORGANT.Impl.Exterior;

import java.io.PrintStream;

public class XC extends Exterior {
    public String toString() {
        return "XC";
    }

    public Color paintColor;

    @Override
    public void printDescription(PrintStream s) {
        s.println("<h1>EXTERIOR</h1></br><h2>XC</h2>");
    }
}