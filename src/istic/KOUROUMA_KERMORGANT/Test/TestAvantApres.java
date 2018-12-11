package istic.KOUROUMA_KERMORGANT.Test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import istic.KOUROUMA_KERMORGANT.Impl.Engines.*;
import istic.KOUROUMA_KERMORGANT.Impl.Exteriors.*;
import istic.KOUROUMA_KERMORGANT.Impl.Interiors.*;
import istic.KOUROUMA_KERMORGANT.Impl.Transmissions.*;

import istic.KOUROUMA_KERMORGANT.API.*;
import istic.KOUROUMA_KERMORGANT.Impl.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestAvantApres {
	Configurator c;
    @BeforeEach
    public void avantTest() {
    	c = new ConfiguratorImpl();
        System.out.println("------------------------");
        System.out.println("Avant MainTest");
    }
    
	public Category findCategory(String s) {
		
		 for ( Category e: c.getCategories()){
		  	Category c2=e;
			if(c2.getName().equals(s)) {
				return c2;
			}
			
		  }
		/*
		 * Iterator<Category> iter=c.getCategories().iterator();
		 * while (iter.hasNext()) {
			Category c2=iter.next();
			if(c2.getName().equals(s)) {
				return c2;
			}
		}
		*/
		return null;
	}
	public String showPartTypeName(PartType pt) {
		String result="uknown";
		if(pt instanceof Engine) {
			result="ENGINE";
			if (pt instanceof ED110) {
				return "ED110";
			}
			if (pt instanceof ED180) {
				return "ED180";
			}
			if (pt instanceof EG100) {
				return "EG100";
			}
			if (pt instanceof EG133) {
				return "EG133";
			}
			if (pt instanceof EG210) {
				return "EG210";
			}
			if (pt instanceof EH120) {
				return "EH120";
			}
		}
		if(pt instanceof Transmission) {
			result="TRANSMISSION";
			if (pt instanceof TM5) {
				return "TM5";
			}
			if (pt instanceof TM6) {
				return "TM6";
			}
			if (pt instanceof TA5) {
				return "TA5";
			}
			if (pt instanceof TC120) {
				return "TC120";
			}
			if (pt instanceof TSF7) {
				return "TSF7";
			}
		}
		if(pt instanceof Interior) {
			result="INTERIOR";
			if (pt instanceof IN) {
				return "IN";
			}
			if (pt instanceof IH) {
				return "IH";
			}
			if (pt instanceof IS) {
				return "IS";
			}
		}
		if(pt instanceof Exterior) {
			result="EXTERIOR";
			if (pt instanceof XC) {
				return "XC";
			}
			if (pt instanceof XM) {
				return "XM";
			}
			if (pt instanceof XS) {
				return "XS";
			}
		}
		return result;
	}
	
	public Collection<String> showPartTypesNames(Collection<PartType> Collpt) {
		Set<String> result=new HashSet<String>();
		for(PartType e: Collpt) {
			result.add(showPartTypeName(e));
		}
		return result;
	}
    @AfterEach
    public void apresTest() {
        System.out.println("Apr�s MainTest");
        System.out.println("------------------------");
    }
    
    @Test
    public void premierTest() {
        System.out.println("Premier MainTest");
		Category c2 = findCategory("TRANSMISSION");
		assertTrue(c2!=null);
    }
    
    @Test
    public void premierTest2() {
        System.out.println("Premier MainTest ENGINE");
        Category c2 = findCategory("ENGINE");
        Collection<PartType> c3=c.getVariantsForCategory(c2);
		Collection<String> c4=showPartTypesNames(c3);
		assertTrue(c4!=null);
    }
    @Test
    public void deuxiemeTest() {
        System.out.println("Deuxieme MainTest");
        Category c2 = findCategory("ENGINE");
		Collection<PartType> c3=c.getVariantsForCategory(c2);
		Collection<String> c4=showPartTypesNames(c3);
		assertTrue(c4.contains("EH120"));
    }
}