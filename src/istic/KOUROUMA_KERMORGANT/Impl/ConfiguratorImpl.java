package istic.KOUROUMA_KERMORGANT.Impl;


import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import istic.KOUROUMA_KERMORGANT.Impl.Engines.*;
import istic.KOUROUMA_KERMORGANT.Impl.Exteriors.*;
import istic.KOUROUMA_KERMORGANT.Impl.Interiors.*;
import istic.KOUROUMA_KERMORGANT.Impl.Transmissions.*;
import istic.KOUROUMA_KERMORGANT.API.*;

public class ConfiguratorImpl implements Configurator, CompatibilityChecker {

    private Collection<PartType> collPart;
    private Collection<Category> collCategory;
    private CompatibilityManager compManager;
    private ConfigurationImpl<Configuration> configuration;

    public ConfiguratorImpl() {
        initialize();
    }

    private void initialize() {
        compManager = new CompatibilityManagerImpl();
        collPart = new HashSet<>();
        collCategory = new HashSet<>();
        Category ENGINE = new CategoryImpl("ENGINE");
        PartTypeImpl EG100 = new PartTypeImpl("EG100", ENGINE, EG100.class);
        PartTypeImpl EG133 = new PartTypeImpl("EG133", ENGINE, EG133.class);
        PartTypeImpl EG210 = new PartTypeImpl("EG210", ENGINE, EG210.class);
        PartTypeImpl ED110 = new PartTypeImpl("ED110", ENGINE, ED110.class);
        PartTypeImpl ED180 = new PartTypeImpl("ED180", ENGINE, ED180.class);
        PartTypeImpl EH120 = new PartTypeImpl("EH120", ENGINE, EH120.class);

        Category TRANSMISSION = new CategoryImpl("TRANSMISSION");
        PartTypeImpl TM5 = new PartTypeImpl("TM5", TRANSMISSION, TM5.class);
        PartTypeImpl TM6 = new PartTypeImpl("TM6", TRANSMISSION, TM6.class);
        PartTypeImpl TA5 = new PartTypeImpl("TA5", TRANSMISSION, TA5.class);
        PartTypeImpl TS6 = new PartTypeImpl("TS6", TRANSMISSION, TS6.class);
        PartTypeImpl TSF7 = new PartTypeImpl("TSF7", TRANSMISSION, TSF7.class);
        PartTypeImpl TC120 = new PartTypeImpl("TC120", TRANSMISSION, TC120.class);

        Category EXTERIOR = new CategoryImpl("EXTERIOR");
        PartTypeImpl XC = new PartTypeImpl("XC", EXTERIOR, XC.class);
        PartTypeImpl XM = new PartTypeImpl("XM", EXTERIOR, XM.class);
        PartTypeImpl XS = new PartTypeImpl("XS", EXTERIOR, XS.class);

        Category INTERIOR = new CategoryImpl("INTERIOR");
        PartTypeImpl IN = new PartTypeImpl("IN", INTERIOR, IN.class);
        PartTypeImpl IH = new PartTypeImpl("IH", INTERIOR, IH.class);
        PartTypeImpl IS = new PartTypeImpl("IS", INTERIOR, IS.class);


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
        compManager.addRequirements(TC120, EH120);
        compManager.addRequirements(XS, IS);
        compManager.addRequirements(IS, XS);
        compManager.addRequirements(IS, TM5);

        compManager.addIncompatibilities(TA5, EG100);
        compManager.addIncompatibilities(TSF7, EG100);
        compManager.addIncompatibilities(TSF7, EG133);
        compManager.addIncompatibilities(TSF7, ED110);
        compManager.addIncompatibilities(XS, EG100);
        compManager.addIncompatibilities(IS, EG100);
        compManager.addIncompatibilities(IS, TM5);
        compManager.addIncompatibilities(EH120, TC120);

        Logger.getGlobal().log(Level.INFO, compManager.afficheRequirements());
        Logger.getGlobal().log(Level.INFO, compManager.afficheIncompatibilities());
        configuration = new ConfigurationImpl<Configuration>(compManager);
    }


    @Override
    public Configuration getConfiguration() {
        // TODO Auto-generated method stub
        return configuration;
    }

    @Override
    public Boolean getVariants() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection<Category> getCategories() {
        // TODO Auto-generated method stub
        return new HashSet<Category>(collCategory);
    }

    public Collection<PartType> getVariantsForCategory(Category c) {
        Collection<PartType> collPT = new HashSet<PartType>();
        String view = "----------------getVariantsForCategory(" + c.toString() + ")------------\n{";
        for (PartType e : collPart) {
            if (e.getCategory().equals(c)) {
                collPT.add(e);
                view = view + e.toString() + ",";
            }
        }
        view = view + "}\n----------------getVariantsForCategory(" + c.toString() + ")------------\n";
        Logger.getGlobal().log(Level.INFO, view);
        return collPT;
    }

    @Override
    public Collection<PartType> getIncompatibilities(PartType pt) {
        return compManager.getIncompatibilities(pt);
    }

    @Override
    public Collection<PartType> getRequirements(PartType pt) {
        return compManager.getRequirements(pt);
    }

}