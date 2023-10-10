package org.example;

public class bigStar implements Star {
    String name;
    @Override
    public String sing() {
        return name+"sing 只因你太美";
    }
    bigStar(String na){
        name=na;
    }
    @Override
    public void dance() {
    System.out.print(name+"dancing 篮球");
    }
}
