package com.shortthirdman.core.util.print;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.Attribute;
import javax.print.attribute.AttributeSet;

public class PrinterAttribute {
    public static void main(String[] args) {
        PrintService printer = PrintServiceLookup.lookupDefaultPrintService();

        if (printer != null) {
            AttributeSet attributes = printer.getAttributes();
            for (Attribute a : attributes.toArray()) {
                String name = a.getName();
                String value = attributes.get(a.getClass()).toString();
                System.out.println(name + " : " + value);
            }
        }
    }
}