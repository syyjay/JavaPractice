package com.syyjay.anno;

import org.springframework.stereotype.Component;

@Component("target")
public class Target implements TargetInterface {

    @Override
    public void save() {
        System.out.println("target save runing");
    }
}
