package istic.KOUROUMA_KERMORGANT.Impl.Interiors;

import istic.KOUROUMA_KERMORGANT.Impl.Interior;

import java.io.PrintStream;

public class IS extends Interior {
    public String toString() {
        return "IS";
    }

    @Override
    public void printDescription(PrintStream s) {
        s.println("<h1>INTERIOR</h1></br><h2>IS</h2>");
    }
}