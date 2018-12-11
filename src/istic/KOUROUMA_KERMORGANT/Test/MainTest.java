package istic.KOUROUMA_KERMORGANT.Test;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.PrintStream;
import java.util.Collection;
import java.util.HashSet;

import istic.KOUROUMA_KERMORGANT.API.*;
import istic.KOUROUMA_KERMORGANT.Impl.*;
import istic.KOUROUMA_KERMORGANT.Impl.Engines.*;
import istic.KOUROUMA_KERMORGANT.Impl.Exteriors.*;
import istic.KOUROUMA_KERMORGANT.Impl.Interiors.*;
import istic.KOUROUMA_KERMORGANT.Impl.Transmissions.*;


public class MainTest {
	Configurator c;
	ConfigurationImpl<Configuration> sujet;
	Observer<Configuration> obs1;
    @BeforeEach
    public void avantTest() {
    	c = new ConfiguratorImpl();
		//configuration is the observable
		sujet= (ConfigurationImpl) c.getConfiguration();
		//create observeur
		obs1=new ObserverImpl<Configuration>();
        System.out.println("------------------------");
        System.out.println("Avant MainTest");
    }
    
	public Category findCategory(String s,Collection<Category> collCat) {
		
		 for ( Category e: collCat){
		  	Category c2=e;
			if(c2.getName().equals(s)) {
				return c2;
			}
		  }
		return null;
	}
	
	public PartType findPartType(String s,Collection<PartType> collPT) {
		
		 for ( PartType e: collPT){
			 PartType pt=e;
			if(pt.getName().equals(s)) {
				return pt;
			}
		  }
		return null;
	}

    @Test
    public void Test1_1() {
		Category c2 = findCategory("ENGINE",c.getCategories());
		assertTrue(c2!=null);
    }
    
    @Test
    public void Test1_2() {
		Category c2 = findCategory("TRANSMISSION",c.getCategories());
		assertTrue(c2!=null);
    }
    @Test
    public void Test1_3() {
		Category c2 = findCategory("EXTERIOR",c.getCategories());
		assertTrue(c2!=null);
    }
    @Test
    public void Test1_4() {
		Category c2 = findCategory("INTERIOR",c.getCategories());
		assertTrue(c2!=null);
    }
    @Test
    public void Test2_1() {
    	Category c2 = findCategory("ENGINE",c.getCategories());
		Collection<PartType> collpt=c.getVariantsForCategory(c2);
		PartType pt=findPartType("EG100", collpt);
		assertTrue(pt!=null);
    }
    
    @Test
    public void Test2_2() {
		Category c2 = findCategory("TRANSMISSION",c.getCategories());
		Collection<PartType> collpt=c.getVariantsForCategory(c2);
		PartType pt=findPartType("TM5", collpt);
		assertTrue(pt!=null);
    }
    
    @Test
    public void Test2_3() {
		Category c4 = findCategory("EXTERIOR",c.getCategories());
		Collection<PartType> collpt=c.getVariantsForCategory(c4);
		PartType pt3=findPartType("XM", collpt);
		assertTrue(pt3!=null);
    }
    
    @Test
    public void Test2_4() {
    	Category c5 = findCategory("INTERIOR",c.getCategories());
    	Collection<PartType> collpt=c.getVariantsForCategory(c5);
		PartType pt4=findPartType("IN", collpt);
		assertTrue(pt4!=null);
    }
    @Test
    public void Test_3() {
		Category c2 = findCategory("ENGINE",c.getCategories());
		Collection<PartType> collpt=c.getVariantsForCategory(c2);
		PartType pt=findPartType("EG100", collpt);
		assertTrue(pt!=null);
		c.getConfiguration().SelectPart(pt);
		assertFalse(c.getConfiguration().isComplete());
		assertTrue(c.getConfiguration().isValid());
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
		c.getConfiguration().removePart(pt3);
		assertFalse(c.getConfiguration().isComplete());
    }
    
    @Test
    //test complete and valid
    public void Test_4() {
        System.out.println("MainTest 4");
		Category c2 = findCategory("ENGINE",c.getCategories());
		Collection<PartType> collpt=c.getVariantsForCategory(c2);
		PartType pt=findPartType("EG100", collpt);
		assertTrue(pt!=null);
		c.getConfiguration().SelectPart(pt);
		assertFalse(c.getConfiguration().isComplete());
		assertTrue(c.getConfiguration().isValid());
		Category c3 = findCategory("TRANSMISSION",c.getCategories());
		collpt=c.getVariantsForCategory(c3);
		PartType pt2=findPartType("TM5", collpt);
		assertTrue(pt2!=null);
		c.getConfiguration().SelectPart(pt2);
		Collection<PartType> incomp=c.getIncompatibilities(pt);
		assertFalse(incomp.contains(pt2));
    }
	
  @Test
  public void Test_5() {
		// TODO Auto-generated method stub
		Configuration c1=c.getConfiguration();
		Collection<Category> c2=c.getCategories();
		Category c3=findCategory("ENGINE", c2);
		if(c3!=null) {
			System.out.println("il existe une cat�gory ENGINE");
		}
		Collection<PartType>  pt=c.getVariantsForCategory(c3);
		PartType pt2=findPartType("EG100", pt);
		if(pt2!=null) {
			System.out.println("il existe une parttype EG100");
		}
		sujet.register(obs1);
		c1.SelectPart(pt2);
		ConfigurationImpl<Configuration> config=(ConfigurationImpl<Configuration>) obs1.getObservable();
		if(config!=null) {
			System.out.println(config.ViewConfiguation());
		}
		assertTrue(true);
  }
  
  @Test
  public void Test6_1() {
	  Configuration c1=c.getConfiguration();
	  Collection<Category> c2=c.getCategories();
	  Category c3=findCategory("ENGINE", c2);
	  Collection<PartType> collpt=c.getVariantsForCategory(c3);
	  Part pt=c1.getSelectionForCategory(c3);
	  assertTrue(pt==null);
	  PartType pt1=findPartType("EG100", collpt);
	  c1.SelectPart(pt1);
	  pt=c1.getSelectionForCategory(c3);
	  assertTrue(pt!=null);
  }
  
  @Test
  public void Test6_2() {
	  Configuration c1=c.getConfiguration();
	  Collection<Category> c2=c.getCategories();
	  Category c3=findCategory("TRANSMISSION", c2);
	  Collection<PartType> collpt=c.getVariantsForCategory(c3);
	  Part pt=c1.getSelectionForCategory(c3);
	  assertTrue(pt==null);
	  PartType pt1=findPartType("TM6", collpt);
	  c1.SelectPart(pt1);
	  pt=c1.getSelectionForCategory(c3);
	  assertTrue(pt!=null);
  }
  @Test
  public void Test6_3() {
	  Configuration c1=c.getConfiguration();
	  Collection<Category> c2=c.getCategories();
	  Category c3=findCategory("EXTERIOR", c2);
	  Collection<PartType> collpt=c.getVariantsForCategory(c3);
	  Part pt=c1.getSelectionForCategory(c3);
	  assertTrue(pt==null);
	  PartType pt1=findPartType("XS", collpt);
	  c1.SelectPart(pt1);
	  pt=c1.getSelectionForCategory(c3);
	  assertTrue(pt!=null);
  }
  @Test
  public void Test6_4() {
	  Configuration c1=c.getConfiguration();
	  Collection<Category> c2=c.getCategories();
	  Category c3=findCategory("INTERIOR", c2);
	  Collection<PartType> collpt=c.getVariantsForCategory(c3);
	  Part pt=c1.getSelectionForCategory(c3);
	  assertTrue(pt==null);
	  PartType pt1=findPartType("IS", collpt);
	  c1.SelectPart(pt1);
	  pt=c1.getSelectionForCategory(c3);
	  assertTrue(pt!=null);
  }
  
  //remove part test
  @Test
  public void Test7_1() {
	  Configuration c1=c.getConfiguration();
	  Collection<Category> c2=c.getCategories();
	  Category c3=findCategory("ENGINE", c2);
	  Collection<PartType> collpt=c.getVariantsForCategory(c3);
	  Part pt=c1.getSelectionForCategory(c3);
	  assertTrue(pt==null);
	  PartType pt1=findPartType("EG133", collpt);
	  c1.SelectPart(pt1);
	  pt=c1.getSelectionForCategory(c3);
	  assertTrue(pt!=null);
	  c1.removePart(pt1);
	  pt=c1.getSelectionForCategory(c3);
	  assertTrue(pt==null);
  }
  
  @Test
  public void Test7_2() {
	  Configuration c1=c.getConfiguration();
	  Collection<Category> c2=c.getCategories();
	  Category c3=findCategory("TRANSMISSION", c2);
	  Collection<PartType> collpt=c.getVariantsForCategory(c3);
	  Part pt=c1.getSelectionForCategory(c3);
	  assertTrue(pt==null);
	  PartType pt1=findPartType("TSF7", collpt);
	  c1.SelectPart(pt1);
	  pt=c1.getSelectionForCategory(c3);
	  assertTrue(pt!=null);
	  c1.removePart(pt1);
	  pt=c1.getSelectionForCategory(c3);
	  assertTrue(pt==null);
  }
  @Test
  public void Test7_3() {
	  Configuration c1=c.getConfiguration();
	  Collection<Category> c2=c.getCategories();
	  Category c3=findCategory("EXTERIOR", c2);
	  Collection<PartType> collpt=c.getVariantsForCategory(c3);
	  Part pt=c1.getSelectionForCategory(c3);
	  assertTrue(pt==null);
	  PartType pt1=findPartType("XM", collpt);
	  c1.SelectPart(pt1);
	  pt=c1.getSelectionForCategory(c3);
	  assertTrue(pt!=null);
	  c1.removePart(pt1);
	  pt=c1.getSelectionForCategory(c3);
	  assertTrue(pt==null);
  }
  @Test
  public void Test7_4() {
	  Configuration c1=c.getConfiguration();
	  Collection<Category> c2=c.getCategories();
	  Category c3=findCategory("INTERIOR", c2);
	  Collection<PartType> collpt=c.getVariantsForCategory(c3);
	  Part pt=c1.getSelectionForCategory(c3);
	  assertTrue(pt==null);
	  PartType pt1=findPartType("IN", collpt);
	  c1.SelectPart(pt1);
	  pt=c1.getSelectionForCategory(c3);
	  assertTrue(pt!=null);
	  c1.removePart(pt1);
	  pt=c1.getSelectionForCategory(c3);
	  assertTrue(pt==null);
  }
  @Test
  public void Test8() {
	    CompatibilityManager compManager= new CompatibilityManagerImpl();
		Collection<PartType> collPart;
		Collection<Category> collCategory;
	    collPart = new HashSet<PartType>();
		collCategory = new HashSet<Category>();
		Category ENGINE= new CategoryImpl("ENGINE");
		PartTypeImpl EG100= new PartTypeImpl("EG100", ENGINE, EG100.class);
		PartTypeImpl EG133= new PartTypeImpl("EG133", ENGINE, EG133.class);
		PartTypeImpl EG210= new PartTypeImpl("EG210", ENGINE, EG210.class);
		PartTypeImpl ED110= new PartTypeImpl("ED110", ENGINE, ED110.class);
		PartTypeImpl ED180= new PartTypeImpl("ED180", ENGINE, ED180.class);
		PartTypeImpl EH120= new PartTypeImpl("EH120", ENGINE, EH120.class);
		Category TRANSMISSION= new CategoryImpl("TRANSMISSION");
		PartTypeImpl TM5= new PartTypeImpl("TM5", TRANSMISSION, TM5.class);
		PartTypeImpl TM6= new PartTypeImpl("TM6", TRANSMISSION, TM6.class);
		PartTypeImpl TA5= new PartTypeImpl("TA5", TRANSMISSION, TA5.class);
		PartTypeImpl TS6= new PartTypeImpl("TS6", TRANSMISSION, TS6.class);
		PartTypeImpl TSF7= new PartTypeImpl("TSF7", TRANSMISSION, TSF7.class);
		PartTypeImpl TC120= new PartTypeImpl("TC120", TRANSMISSION, TC120.class);
		Category EXTERIOR= new CategoryImpl("EXTERIOR");
		PartTypeImpl XC= new PartTypeImpl("XC", EXTERIOR, XC.class);
		PartTypeImpl XM= new PartTypeImpl("XM", EXTERIOR, XM.class);
		PartTypeImpl XS= new PartTypeImpl("XS", EXTERIOR, XS.class);
		Category INTERIOR= new CategoryImpl("INTERIOR");
		PartTypeImpl IN= new PartTypeImpl("IN", INTERIOR, IN.class);
		PartTypeImpl IH= new PartTypeImpl("IH", INTERIOR, IH.class);
		PartTypeImpl IS= new PartTypeImpl("IS", INTERIOR, IS.class);
		collCategory.add(ENGINE);
		collCategory.add(TRANSMISSION);
		collCategory.add(EXTERIOR);
		collCategory.add(INTERIOR);
		collPart.add(EG100);
		collPart.add(EG133);
		collPart.add(EG210);
		collPart.add(ED110);
		collPart.add(ED180);
		collPart.add(EH120);
		collPart.add(TM5);
		collPart.add(TM6);
		collPart.add(TA5);
		collPart.add(TS6);
		collPart.add(TSF7);
		collPart.add(TC120);
		collPart.add(XC);
		collPart.add(XM);
		collPart.add(XS);
		collPart.add(IN);
		collPart.add(IH);
		collPart.add(IS);
		compManager.addRequirements(EH120, TC120);
		compManager.addIncompatibilities(TA5, EG100);
		compManager.addIncompatibilities(TSF7, EG100);
		compManager.addIncompatibilities(TSF7, EG133);
		compManager.addIncompatibilities(TSF7, ED110);
		compManager.addRequirements(TC120, EH120);
		compManager.addIncompatibilities(XS, EG100);
		compManager.addRequirements(XS, IS);
		compManager.addIncompatibilities(IS, EG100);
		compManager.addIncompatibilities(IS, TM5);
		compManager.addRequirements(IS, XS);
		assertTrue(compManager.getRequirements(EH120).contains(TC120));
		//assertTrue(c.getConfiguration().isValid());
		compManager.removeRequirements(EH120, TC120);
		assertFalse(compManager.getRequirements(EH120).contains(TC120));
		//assertFalse(c.getConfiguration().isValid());
		assertTrue(compManager.getIncompatibilities(TA5).contains(EG100));
		compManager.removeIncompatibilities(TA5, EG100);
		assertTrue(compManager.getIncompatibilities(TA5).size()==0);
  }
  
  // print description
  @Test
  public void Test9() {
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
