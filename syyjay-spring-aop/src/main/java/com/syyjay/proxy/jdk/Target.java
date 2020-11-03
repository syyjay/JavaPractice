package com.syyjay.proxy.jdk;

public class Target implements TargetInterface{
    @Override
    public void save() {
        System.out.println("target save runing");
    }
}
