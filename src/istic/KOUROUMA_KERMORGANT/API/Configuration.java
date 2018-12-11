package istic.KOUROUMA_KERMORGANT.API;


import java.io.PrintStream;

public interface Configuration {

  Boolean isValid();

  Boolean isComplete();

  Part getSelectionForCategory(Category c);

  void SelectPart(PartType pt);
  
  void removePart(PartType pt);
  
  String toString();
  
  void printDescription(PrintStream s);

}