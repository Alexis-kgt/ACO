package istic.KOUROUMA_KERMORGANT.Impl.Transmissions;

import istic.KOUROUMA_KERMORGANT.Impl.Transmission;

import istic.KOUROUMA_KERMORGANT.Impl.Transmission;

import java.io.PrintStream;

public class TM6 extends Transmission {
    public String toString() {
        return "TM6";
    }

    @Override
    public void printDescription(PrintStream s) {
        s.println("<h1>TRANSMISSION</h1></br><h2>TM6</h2>");
    }
}
