package istic.KOUROUMA_KERMORGANT.Impl;

import istic.KOUROUMA_KERMORGANT.Impl.Color;

import java.io.PrintStream;
import java.util.HashSet;
import java.util.Set;


public class Exterior extends PartImpl {

	private Color paintColor=Color.WHITE;


	public String getColorName() {
		return paintColor.name();
	}

	public void setColorName(String color) {
		Color newColor=Color.valueOf(color);
		if(newColor!=null) {
			paintColor=newColor;
		}
	}
	protected Set<String> getPossibleColor(){
		Set<String> setElements = new HashSet<String>();
		setElements.add(Color.RED.name());
		setElements.add(Color.BLUE.name());
		setElements.add(Color.WHITE.name());
		return new HashSet<>(setElements);
	}
	public Exterior() {
		addProperty("paintColor",()->getColorName(),(value)->setColorName(value),getPossibleColor());
	}
	@Override
	public void printDescription(PrintStream s) {
	}

	protected double getPrice() {
		switch(paintColor) {
			case BLUE:return 500;
			case WHITE:return 200;
			case RED:return 300;
			default:return 0.0;
		}


	}
}