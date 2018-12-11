package istic.KOUROUMA_KERMORGANT.Impl.Engines;

import istic.KOUROUMA_KERMORGANT.Impl.Engine;
import java.io.PrintStream;

public class EG133 extends Engine {
	public String toString() {
		return "EG133";
	}

	@Override
	public void printDescription(PrintStream s) {
		s.println("<h1>ENGINE</h1></br><h2>EG133</h2>");
	}
}