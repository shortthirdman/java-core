package com.shortthirdman.core.common;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.Attribute;
import javax.print.attribute.AttributeSet;

import java.awt.print.PrinterJob;

public class PrinterFactoryUtil {
	public static void getDefaultPrinterAttributes() {
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
	
	public static void getAllPrinterNames() {
		PrintService[] printServices = PrinterJob.lookupPrintServices();
        for (PrintService printService : printServices) {
            String name = printService.getName();
            System.out.println("Name = " + name);
        }
	}
	
	public static void getDefaultPrinterService() {
		PrintService service = PrintServiceLookup.lookupDefaultPrintService();
        if (service != null) {
            String printServiceName = service.getName();
            System.out.println("Print Service Name = " + printServiceName);
        } else {
            System.out.println("No default print service found.");
        }
	}
}