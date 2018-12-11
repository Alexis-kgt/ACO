package istic.KOUROUMA_KERMORGANT.Impl.Engines;

import istic.KOUROUMA_KERMORGANT.Impl.Engine;
import java.io.PrintStream;

public class ED180 extends Engine {
	public String toString() {
		return "ED180";
	}
	
	@Override
	public void printDescription(PrintStream s) {
		s.println("<h1>ENGINE</h1></br><h2>ED180</h2>");
	}
}