package istic.KOUROUMA_KERMORGANT.Impl.Transmissions;

import istic.KOUROUMA_KERMORGANT.Impl.Transmission;

import java.io.PrintStream;

public class TSF7 extends Transmission {
    public String toString() {
        return "TSF7";
    }

    @Override
    public void printDescription(PrintStream s) {
        s.println("<h1>TRANSMISSION</h1></br><h2>TSF7</h2>");
    }
}