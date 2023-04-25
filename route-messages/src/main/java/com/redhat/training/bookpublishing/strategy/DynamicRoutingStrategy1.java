package com.redhat.training.bookpublishing.strategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.camel.Exchange;

public class DynamicRoutingStrategy1 {

    List<String> computed = new ArrayList<>();

    public List<String> compute(
            Exchange e) {
        
        String s =(String) e.getIn().getHeader("destination");
        List<String> list = new ArrayList<>();


        String arr[] = s.split(",");

        for(int i=0 ; i < arr.length; i++) {
            if(computed.contains(arr[i].toString())) {
            } else {
                list.add(arr[i]);
            }
        }
            
        computed.addAll(list);
        if (list.size() > 0) {
            System.out.print("List Return CamelFileName == " + e.getIn().getHeader("CamelFileName"));
            System.out.println("Computed endpoint ");
            list.stream().forEach(p -> System.out.print(p+" "));
            System.out.println();
            return list;
        }
        System.out.print("List Return CamelFileName == " + e.getIn().getHeader("CamelFileName"));
        return null;

    }
}
