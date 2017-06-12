package com.shortthirdman.core.util.print;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;

public class GetPrinter {
    public static void main(String[] args) {
        PrintService service = PrintServiceLookup.lookupDefaultPrintService();
        if (service != null) {
            String printServiceName = service.getName();
            System.out.println("Print Service Name = " + printServiceName);
        } else {
            System.out.println("No default print service found.");
        }
    }
}