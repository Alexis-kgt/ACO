package istic.KOUROUMA_KERMORGANT.Impl;

import istic.KOUROUMA_KERMORGANT.API.*;

public class CategoryImpl implements Category {

    private String name;

    public CategoryImpl(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return name;
    }

    @Override
    public boolean equals(Category c) {
        // TODO Auto-generated method stub
        return name.equals(c.getName());
    }

    public String toString() {
        return "Category :" + name;
    }

}
