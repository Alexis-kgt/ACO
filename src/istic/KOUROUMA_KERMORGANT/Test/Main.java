package istic.KOUROUMA_KERMORGANT.Test;

import static junit.framework.Assert.assertTrue;


import java.io.PrintStream;
import java.util.Collection;

import istic.KOUROUMA_KERMORGANT.API.*;
import istic.KOUROUMA_KERMORGANT.Impl.*;

public class Main {

	public static Category findCategory(String s,Collection<Category> collCat) {
		
		 for ( Category e: collCat){
		  	Category c2=e;
			if(c2.getName().equals(s)) {
				return c2;
			}
		  }
		return null;
	}
	
	public static PartType findPartType(String s,Collection<PartType> collPT) {
		
		 for ( PartType e: collPT){
			 PartType pt=e;
			if(pt.getName().equals(s)) {
				return pt;
			}
		  }
		return null;
	}
	
	public static void main(String[] args) {
		Configurator c=new ConfiguratorImpl();

		Category c2 = findCategory("ENGINE",c.getCategories());
		Collection<PartType> collpt=c.getVariantsForCategory(c2);
		PartType pt=findPartType("EG100", collpt);
		assertTrue(pt!=null);
		c.getConfiguration().SelectPart(pt);
		Category c3 = findCategory("TRANSMISSION",c.getCategories());
		collpt=c.getVariantsForCategory(c3);
		PartType pt2=findPartType("TM5", collpt);
		assertTrue(pt2!=null);
		c.getConfiguration().SelectPart(pt2);
		Category c4 = findCategory("EXTERIOR",c.getCategories());
		collpt=c.getVariantsForCategory(c4);
		PartType pt3=findPartType("XM", collpt);
		assertTrue(pt3!=null);
		c.getConfiguration().SelectPart(pt3);
		Category c5 = findCategory("INTERIOR",c.getCategories());
		collpt=c.getVariantsForCategory(c5);
		PartType pt4=findPartType("IN", collpt);
		assertTrue(pt4!=null);
		c.getConfiguration().SelectPart(pt4);
		assertTrue(c.getConfiguration().isComplete());
		assertTrue(c.getConfiguration().isValid());
		PrintStream s=System.out;
		c.getConfiguration().printDescription(s);
		s.println();

	}

}
