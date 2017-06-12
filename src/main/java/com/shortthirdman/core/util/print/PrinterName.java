package com.shortthirdman.core.util.print;

import javax.print.PrintService;
import java.awt.print.PrinterJob;

public class PrinterName {
    public static void main(String[] args) {
        PrintService[] printServices = PrinterJob.lookupPrintServices();

        for (PrintService printService : printServices) {
            String name = printService.getName();
            System.out.println("Name = " + name);
        }
    }
}