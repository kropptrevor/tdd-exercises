package com.example.scan.barcode;

public class BarcodeScanner {

    long totalDollars = 0;
    int totalCents = 0;

    public String scan(String code) {
        if (code == null) {
            throw new IllegalArgumentException();
        }
        if ("".equals(code)) {
            return "Error: empty barcode";
        }
        if ("12345".equals(code)) {
            totalCents += 25;
            if (totalCents >= 100) {
                totalDollars++;
                totalCents -= 100;
            }
            totalDollars += 7;
            return "$7.25";
        }
        if ("23456".equals(code)) {
            totalCents += 50;
            if (totalCents >= 100) {
                totalDollars++;
                totalCents -= 100;
            }
            totalDollars += 12;
            return "$12.50";
        }
        return "Error: barcode not found";
    }

    public String total() {
        return String.format("$%d.%02d", totalDollars, totalCents);
    }

}
