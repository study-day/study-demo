package com.studyday.study;

/**
 * Banana2由 Banana重命名。模拟删除了Banana类。同事修改版本为0.0.2-SNAPSHOT
 * Hello world!
 */
public class Banana2 {
    public String getName() {
        System.out.println("this is Banana， delicious！");
        return this.getClass().getSimpleName();
    }
}