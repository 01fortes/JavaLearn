package com.jsamkt.learn.collections;

import com.jsamkt.learn.collections.common.CommonOperationsDemo;
import com.jsamkt.learn.collections.list.ListDemo;
import com.jsamkt.learn.collections.map.MapDemo;
import com.jsamkt.learn.collections.set.SetDemo;
import com.jsamkt.learn.collections.special.SpecialCollectionDemo;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * This tutorial demonstrates various Java collections and their use cases.
 */
public class CollectionsDemo {
    public static void main(String[] args) {
        System.out.println("===== Java Collections Tutorial =====");

        ListDemo.demo();
        SetDemo.demo();
        MapDemo.demo();
        CommonOperationsDemo.demo();
        SpecialCollectionDemo.demo();
    }

}