package istic.KOUROUMA_KERMORGANT.Impl.Engines;

import istic.KOUROUMA_KERMORGANT.Impl.Engine;
import java.io.PrintStream;

public class EH120 extends Engine {
	public String toString() {
		return "EH120";
	}
	@Override
	public void printDescription(PrintStream s) {
		s.println("<h1>ENGINE</h1></br><h2>EH120</h2>");
	}
}