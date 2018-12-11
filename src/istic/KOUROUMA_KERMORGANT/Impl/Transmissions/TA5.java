package istic.KOUROUMA_KERMORGANT.Impl.Transmissions;

import istic.KOUROUMA_KERMORGANT.Impl.Transmission;

import java.io.PrintStream;

public class TA5 extends Transmission {
    public String toString() {
        return "TA5";
    }

    @Override
    public void printDescription(PrintStream s) {
        s.println("<h1>TRANSMISSION</h1></br><h2>TA5</h2>");
    }
}