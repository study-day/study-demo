package com.studyday.study;

public class Dinner {
	public void eat() {
		com.studyday.study.b.Fruit fruit = new com.studyday.study.b.Fruit();
		fruit.eatFruit();
		com.studyday.study.c.Fruit eatFruit = new com.studyday.study.c.Fruit();
		eatFruit.eatFruit();

	}

	public static void main(String[] args) {
		Dinner dinner = new Dinner();
		dinner.eat();
	}
}