package com.andersenlab;

import com.andersenlab.arrayList.CustomArrayList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        list.remove(3);
        System.out.println(list.get(2));

        CustomArrayList<Integer> customArrayList = new CustomArrayList<>();
        customArrayList.add(1);
        customArrayList.add(2);
        customArrayList.add(3);
        customArrayList.add(4);
        customArrayList.add(5);
        customArrayList.add(6);
        customArrayList.add(7);
        customArrayList.add(8);
        customArrayList.add(9);
        customArrayList.add(10);
        customArrayList.add(11);
        customArrayList.add(12);
        customArrayList.add(13);
        customArrayList.add(14);
        customArrayList.add(15);
        customArrayList.add(16);
        customArrayList.add(17);
        customArrayList.add(18);
        customArrayList.add(19);
        customArrayList.add(20);
        customArrayList.remove(3);
        System.out.println(customArrayList);
    }
}