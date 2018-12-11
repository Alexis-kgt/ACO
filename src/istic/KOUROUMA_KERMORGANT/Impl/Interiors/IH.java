package istic.KOUROUMA_KERMORGANT.Impl.Interiors;

import istic.KOUROUMA_KERMORGANT.Impl.Interior;

import java.io.PrintStream;

public class IH extends Interior {
    public String toString() {
        return "IH";
    }

    @Override
    public void printDescription(PrintStream s) {
        s.println("<h1>INTERIOR</h1></br><h2>IH</h2>");
    }
}