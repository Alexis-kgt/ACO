package istic.KOUROUMA_KERMORGANT.Impl.Transmissions;

import istic.KOUROUMA_KERMORGANT.Impl.Transmission;

import java.io.PrintStream;

public class TC120 extends Transmission {
    public String toString() {
        return "TC120";
    }

    @Override
    public void printDescription(PrintStream s) {
        s.println("<h1>TRANSMISSION</h1></br><h2>TC120</h2>");
    }
}