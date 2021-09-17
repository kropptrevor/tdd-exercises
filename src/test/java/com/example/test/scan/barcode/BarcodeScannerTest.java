package com.example.test.scan.barcode;

import static org.junit.Assert.assertEquals;

import com.example.scan.barcode.BarcodeScanner;

import org.junit.Before;
import org.junit.Test;

public class BarcodeScannerTest {

    @Before
    public void setup() {
        barcodeScanner = new BarcodeScanner();
    }

    BarcodeScanner barcodeScanner;

    @Test
    public void barcode1() {
        String price = barcodeScanner.scan("12345");
        assertEquals("$7.25", price);
    }

    @Test
    public void barcode2() {
        String price = barcodeScanner.scan("23456");
        assertEquals("$12.50", price);
    }

    @Test
    public void errorBarcode() {
        String price = barcodeScanner.scan("99999");
        assertEquals("Error: barcode not found", price);
    }

    @Test
    public void emptyBarcode() {
        String price = barcodeScanner.scan("");
        assertEquals("Error: empty barcode", price);
    }

    @Test
    public void multipleBarcodes() {
        barcodeScanner.scan("12345");
        barcodeScanner.scan("12345");
        barcodeScanner.scan("23456");
        String price = barcodeScanner.total();
        assertEquals("$27.00", price);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullBarcode() {
        barcodeScanner.scan(null);
    }

}
