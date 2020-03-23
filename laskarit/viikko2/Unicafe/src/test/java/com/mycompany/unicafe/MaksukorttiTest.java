package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void saldoAluksiOikein() {
        assertEquals(10, kortti.saldo());
    }
    
    @Test
    public void rahanLatausToimii() {
        kortti.lataaRahaa(5);
        assertEquals("saldo: 0.15", kortti.toString());
    }
    
    @Test
    public void rahanOttoJosTarpeeksiVahentaaOikein() {
        kortti.otaRahaa(5);
        assertEquals("saldo: 0.5", kortti.toString());
    }
    
    @Test
    public void rahanOttoJosEiTarpeeksiEiVahenna() {
        kortti.otaRahaa(20);
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
    @Test
    public void rahanOttoJosTarpeeksiPalauttaaTrue() {
        assertTrue(kortti.otaRahaa(5));
    }
    
    @Test
    public void rahanOttoJosEiTarpeeksiPalauttaaFalse() {
        assertTrue(!kortti.otaRahaa(20));
    }
}
