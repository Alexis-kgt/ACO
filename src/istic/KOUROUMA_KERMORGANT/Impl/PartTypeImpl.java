package istic.KOUROUMA_KERMORGANT.Impl;

import java.lang.reflect.Constructor;
import java.util.logging.Level;
import java.util.logging.Logger;

import istic.KOUROUMA_KERMORGANT.API.*;

public class PartTypeImpl implements PartType {

    private String name;
    private Category category;
    private Class<? extends Part> classRef;


    public PartTypeImpl(String name, Category category, Class<? extends Part> classRef) {
        this.name = name;
        this.category = category;
        this.classRef = classRef;
    }

    public Part newInstance() {
        Constructor<? extends Part> constructor;

        try {
            constructor = classRef.getConstructor();
            return constructor.newInstance();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            Logger.getGlobal().log(Level.SEVERE, "Constructor call failed");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Category getCategory() {
        // TODO Auto-generated method stub
        Category c = new CategoryImpl(category.getName());
        return c;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return name + ", " + category.toString();
    }


}