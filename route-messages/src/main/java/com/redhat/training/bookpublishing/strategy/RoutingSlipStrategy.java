package com.redhat.training.bookpublishing.strategy;

import org.apache.camel.language.XPath;

import java.util.ArrayList;

public class RoutingSlipStrategy {
    public String compute(
            @XPath(value="/book/bookinfo/productname/text()") String type
    ) {
        // TODO: Create a strategy for the review pipeline

        ArrayList<String> list = new ArrayList<>();
        if(type.equals("technical")) {
            list.add("file://data/pipeline/graphic-designer");
            list.add("file://data/pipeline/editor");
        } else if (type.equalsIgnoreCase("novel") ) {
            list.add("file://data/pipeline/editor");
        }
        String str = String.join(",", list);

        System.out.println("----------------------------------String str = "+str);
        return str;
    }
}
