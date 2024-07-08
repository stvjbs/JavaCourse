package com.andersenlab.lesson2.abstraction;

public interface Printable {
    
    default void print() {
        System.out.println("print content in console");
    }
}
