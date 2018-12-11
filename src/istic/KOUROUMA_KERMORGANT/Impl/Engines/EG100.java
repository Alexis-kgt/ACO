package istic.KOUROUMA_KERMORGANT.Impl.Engines;

import istic.KOUROUMA_KERMORGANT.Impl.Engine;
import java.io.PrintStream;

public class EG100 extends Engine {

	public String toString() {
		return "EG100";
	}
	
	@Override
	public void printDescription(PrintStream s) {
		s.println("<h1>ENGINE</h1></br><h2>EG100</h2>");
	}
}