package com.studyday.study;

public class Cherry {
    public String getName() {
        System.out.println("this is Cherry， delicious！");
        return this.getClass().getSimpleName();
    }
}