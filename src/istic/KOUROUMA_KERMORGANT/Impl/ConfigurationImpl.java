package istic.KOUROUMA_KERMORGANT.Impl;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import istic.KOUROUMA_KERMORGANT.API.*;
import istic.KOUROUMA_KERMORGANT.API.Observable;
import istic.KOUROUMA_KERMORGANT.API.Observer;

public class ConfigurationImpl<T> implements Configuration, Observable<T> {

    private Observer<T> registeredObserver;
    private T value;
    private CompatibilityManager compManager;
    private Part ENGINE;
    private Part TRANSMISSION;
    private Part EXTERIOR;
    private Part INTERIOR;
    private PartType PTENGINE;
    private PartType PTTRANSMISSION;
    private PartType PTEXTERIOR;
    private PartType PTINTERIOR;

    public ConfigurationImpl(CompatibilityManager compManager) {
        this.compManager = compManager;
        ViewConfiguation();
    }

    @Override
    public Boolean isValid() {
        return noIncompatibilities() && allRequirements();
    }

    private boolean noIncompatibilities() {
        //i need list of incompatibilities
        Collection<PartType> pt1 = compManager.getIncompatibilities(PTENGINE);
        Collection<PartType> pt2 = compManager.getIncompatibilities(PTTRANSMISSION);
        Collection<PartType> pt3 = compManager.getIncompatibilities(PTEXTERIOR);
        Collection<PartType> pt4 = compManager.getIncompatibilities(PTINTERIOR);
        boolean result1 = pt1.contains(PTTRANSMISSION);
        boolean result2 = pt3.contains(PTTRANSMISSION);
        boolean result3 = pt4.contains(PTTRANSMISSION);
        boolean result4 = pt2.contains(PTENGINE);
        boolean result5 = pt3.contains(PTENGINE);
        boolean result6 = pt4.contains(PTENGINE);
        boolean result7 = pt1.contains(PTEXTERIOR);
        boolean result8 = pt2.contains(PTEXTERIOR);
        boolean result9 = pt4.contains(PTEXTERIOR);
        boolean result10 = pt1.contains(PTINTERIOR);
        boolean result11 = pt2.contains(PTINTERIOR);
        boolean result12 = pt3.contains(PTINTERIOR);
        return !result1 && !result2 && !result3 && !result4 && !result5 && !result6 && !result7
                && !result8 && !result9 && !result10 && !result11 && !result12;
    }

    private boolean allRequirements() {
        Collection<PartType> pt1 = compManager.getRequirements(PTENGINE);
        Collection<PartType> pt2 = compManager.getRequirements(PTTRANSMISSION);
        Collection<PartType> pt3 = compManager.getRequirements(PTEXTERIOR);
        Collection<PartType> pt4 = compManager.getRequirements(PTINTERIOR);
        pt1.remove(PTTRANSMISSION);
        pt1.remove(PTEXTERIOR);
        pt1.remove(PTINTERIOR);
        pt2.remove(PTENGINE);
        pt2.remove(PTEXTERIOR);
        pt2.remove(PTINTERIOR);
        pt3.remove(PTENGINE);
        pt3.remove(PTTRANSMISSION);
        pt3.remove(PTINTERIOR);
        pt4.remove(PTENGINE);
        pt4.remove(PTTRANSMISSION);
        pt4.remove(PTEXTERIOR);
        return (pt1.size() == 0 && pt1.size() == 0 && pt1.size() == 0 && pt1.size() == 0);
    }

    @Override
    public Boolean isComplete() {
        return (ENGINE != null && TRANSMISSION != null && INTERIOR != null
                && EXTERIOR != null);
    }

    @Override
    public Part getSelectionForCategory(Category c) {
        if (c.getName() == "ENGINE") {
            return ENGINE;
        } else if (c.getName() == "TRANSMISSION") {
            return TRANSMISSION;
        } else if (c.getName() == "EXTERIOR") {
            return EXTERIOR;
        } else {
            return INTERIOR;
        }
    }

    @Override
    public void SelectPart(PartType pt) {
        if (pt != null) {
            if (pt.getCategory().getName().equals("ENGINE")) {
                PTENGINE = pt;
                PartTypeImpl tmp = (PartTypeImpl) pt;
                ENGINE = tmp.newInstance();
            } else if (pt.getCategory().getName().equals("TRANSMISSION")) {
                PTTRANSMISSION = pt;
                PartTypeImpl tmp = (PartTypeImpl) pt;
                TRANSMISSION = tmp.newInstance();
            } else if (pt.getCategory().getName().equals("EXTERIOR")) {
                PTEXTERIOR = pt;
                PartTypeImpl tmp = (PartTypeImpl) pt;
                EXTERIOR = tmp.newInstance();
            } else if (pt.getCategory().getName().equals("INTERIOR")) {
                PTINTERIOR = pt;
                PartTypeImpl tmp = (PartTypeImpl) pt;
                INTERIOR = tmp.newInstance();
            }
        }
        notifyRegisteredObservers();
        ViewConfiguation();
    }

    @Override
    public void removePart(PartType pt) {
        if (pt != null) {
            if (pt.getCategory().getName().equals("ENGINE")) {
                ENGINE = null;
            } else if (pt.getCategory().getName().equals("TRANSMISSION")) {
                TRANSMISSION = null;
            } else if (pt.getCategory().getName().equals("EXTERIOR")) {
                EXTERIOR = null;
            } else if (pt.getCategory().getName().equals("INTERIOR")) {
                INTERIOR = null;
            }
        }
        notifyRegisteredObservers();
        ViewConfiguation();
    }

    @Override
    public void register(Observer<T> o) {
        //Objects.requireNonNull(o, "o cannot be null register");
        if (o != null) {
            registeredObserver = o;
        }

    }

    @Override
    public void unregister(Observer<T> o) {
        //Objects.requireNonNull(o, "o cannot be null unregister");
        if (o != null && o == registeredObserver) {
            registeredObserver = null;
        }

    }

    @Override
    public Boolean isRegistred(Observer<T> o) {
        //Objects.requireNonNull(o, "o cannot be null isRegistred");
        if (o != null) {
            return registeredObserver == o;
        }
        return false;
    }

    @Override
    public T getValue() {
        return value;
    }

    public void setValue(T v) {
        value = v;
        notifyRegisteredObservers();
    }

    public String ViewConfiguation() {
        String result = "---------------------Current Configuration----------------------\n";
        String engine = "null";
        String transmission = "null";
        String exterior = "null";
        String interior = "null";
        if (ENGINE != null) {
            engine = ENGINE.toString();
        }
        if (TRANSMISSION != null) {
            transmission = TRANSMISSION.toString();
        }
        if (EXTERIOR != null) {
            exterior = EXTERIOR.toString();
        }
        if (INTERIOR != null) {
            interior = INTERIOR.toString();
        }
        result = result + "ENGINE :" + engine + "\n";
        result = result + "TRANSMISSION :" + transmission + "\n";
        result = result + "EXTERIOR :" + exterior + "\n";
        result = result + "INTERIOR :" + interior + "\n";
        result = result + "---------------------Current Configuration----------------------\n";
        Logger.getGlobal().log(Level.INFO, result);
        return result;
    }

    private void notifyRegisteredObservers() {
        //Objects.requireNonNull(registeredObserver, "o cannot be null notifyRegisteredObservers");
        if (registeredObserver != null) {
            registeredObserver.update(this);
        }
    }

    @Override
    public void printDescription(PrintStream s) {
        if (isValid() && isComplete()) {
            s.println("<!DOCTYPE html>");
            s.println("<html>");
            s.println("<head>");
            s.println("<title> My Configuration</title>");
            s.println("</head>");
            s.println("<body>");
            ENGINE.printDescription(s);
            TRANSMISSION.printDescription(s);
            INTERIOR.printDescription(s);
            EXTERIOR.printDescription(s);
            s.println("</body>");
            s.println("</html>");
        }
        BufferedWriter bw = null;
        FileWriter fw;
        try {
            fw = new FileWriter("myConfiguration.html");
            bw = new BufferedWriter(fw);
            //false
            bw.write(s.toString());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
