/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author risto
 */
public class KassapaateTest {
    
    Kassapaate paate;
    Maksukortti kortti;
    
    @Before
    public void setUp() {
        paate = new Kassapaate();
        kortti = new Maksukortti(500);
    }
    
    @Test
    public void alussaRahamaaraOikein() {
        assertTrue(paate.kassassaRahaa() == 100000);      
    }
    
    @Test
    public void alussaEdullinenLounasmaaraOikein() {
        assertTrue(paate.edullisiaLounaitaMyyty() == 0);      
    }
    
    @Test
    public void alussaMaukasLounasmaaraOikein() {
        assertTrue(paate.maukkaitaLounaitaMyyty() == 0);      
    }
    
    @Test
    public void edullinenOstoVeloittaaOikein() {
        paate.syoEdullisesti(250);
        assertTrue(paate.kassassaRahaa() == 100240);      
    }
    
    @Test
    public void edullinenOstoOikeaVaihtoraha() {
        int vaihtoraha = paate.syoEdullisesti(250);
        assertTrue(vaihtoraha == 10);      
    }
    
    @Test
    public void edullinenOstoMuistetaan() {
        paate.syoEdullisesti(250);
        assertTrue(paate.edullisiaLounaitaMyyty() == 1);      
    }
    
    @Test
    public void edullinenOstoRiittamatonVeloittaaOikein() {
        paate.syoEdullisesti(200);
        assertTrue(paate.kassassaRahaa() == 100000);      
    }
    
    @Test
    public void edullinenOstoRiittamatonOikeaVaihtoraha() {
        int vaihtoraha = paate.syoEdullisesti(200);
        assertTrue(vaihtoraha == 200);      
    }
    
    @Test
    public void edullinenOstoRiittamatonEiMuisteta() {
        paate.syoEdullisesti(200);
        assertTrue(paate.edullisiaLounaitaMyyty() == 0);      
    }
    
    @Test
    public void maukasOstoVeloittaaOikein() {
        paate.syoMaukkaasti(500);
        assertTrue(paate.kassassaRahaa() == 100400);      
    }
    
    @Test
    public void maukasOstoOikeaVaihtoraha() {
        int vaihtoraha = paate.syoMaukkaasti(500);
        assertTrue(vaihtoraha == 100);      
    }
    
    @Test
    public void maukasOstoMuistetaan() {
        paate.syoMaukkaasti(500);
        assertTrue(paate.maukkaitaLounaitaMyyty() == 1);      
    }
    
    @Test
    public void maukasOstoRiittamatonVeloittaaOikein() {
        paate.syoMaukkaasti(300);
        assertTrue(paate.kassassaRahaa() == 100000);      
    }
    
    @Test
    public void maukasOstoRiittamatonOikeaVaihtoraha() {
        int vaihtoraha = paate.syoMaukkaasti(300);
        assertTrue(vaihtoraha == 300);      
    }
    
    @Test
    public void maukasOstoRiittamatonEiMuisteta() {
        paate.syoMaukkaasti(300);
        assertTrue(paate.maukkaitaLounaitaMyyty() == 0);      
    }
    
    @Test
    public void edullinenOstoKortillaVeloittaaOikein() {
        paate.syoEdullisesti(kortti);
        assertTrue(kortti.saldo() == 260);
    }
    
    @Test
    public void edullinenOstoKortillaPalauttaaTrue() {
        assertTrue(paate.syoEdullisesti(kortti));
    }
    
    @Test
    public void edullinenOstoKortillaMuistetaan() {
        paate.syoEdullisesti(kortti);
        assertTrue(paate.edullisiaLounaitaMyyty() == 1);
    }
    
    @Test
    public void edullinenOstoKortillaRiittamatonVeloittaaOikein() {
        kortti.otaRahaa(300);
        paate.syoEdullisesti(kortti);
        assertTrue(kortti.saldo() == 200);
    }
    
    @Test
    public void edullinenOstoKortillaRiittamatonPalauttaaFalse() {
        kortti.otaRahaa(300);
        assertTrue(!paate.syoEdullisesti(kortti));
    }
    
    @Test
    public void edullinenOstoKortillaRiittamatonEiMuisteta() {
        kortti.otaRahaa(300);
        paate.syoEdullisesti(kortti);
        assertTrue(paate.edullisiaLounaitaMyyty() == 0);
    }
    
    @Test
    public void edullinenOstoKortillaEiLisaaRahamaaraa() {
        paate.syoEdullisesti(kortti);
        assertTrue(paate.kassassaRahaa() == 100000);
    }
    
    @Test
    public void maukasOstoKortillaVeloittaaOikein() {
        paate.syoMaukkaasti(kortti);
        assertTrue(kortti.saldo() == 100);
    }
    
    @Test
    public void maukasOstoKortillaPalauttaaTrue() {
        assertTrue(paate.syoMaukkaasti(kortti));
    }
    
    @Test
    public void maukasOstoKortillaMuistetaan() {
        paate.syoMaukkaasti(kortti);
        assertTrue(paate.maukkaitaLounaitaMyyty() == 1);
    }
    
    @Test
    public void maukasOstoKortillaRiittamatonVeloittaaOikein() {
        kortti.otaRahaa(300);
        paate.syoMaukkaasti(kortti);
        assertTrue(kortti.saldo() == 200);
    }
    
    @Test
    public void maukasOstoKortillaRiittamatonPalauttaaFalse() {
        kortti.otaRahaa(300);
        assertTrue(!paate.syoMaukkaasti(kortti));
    }
    
    @Test
    public void maukasOstoKortillaRiittamatonEiMuisteta() {
        kortti.otaRahaa(300);
        paate.syoMaukkaasti(kortti);
        assertTrue(paate.maukkaitaLounaitaMyyty() == 0);
    }
    
    @Test
    public void maukasOstoKortillaEiLisaaRahamaaraa() {
        paate.syoMaukkaasti(kortti);
        assertTrue(paate.kassassaRahaa() == 100000);
    }
    
    @Test
    public void rahanLatausTallentuuKortille() {
        paate.lataaRahaaKortille(kortti, 5000);
        assertTrue(kortti.saldo() == 5500);
    }
    
    @Test
    public void rahanLatausVeloitetaanOikein() {
        paate.lataaRahaaKortille(kortti, 5000);
        assertTrue(paate.kassassaRahaa() == 105000);
    }
    
     @Test
    public void rahanLatausNegatiivinenEiTallennuKortille() {
        paate.lataaRahaaKortille(kortti, -5000);
        assertTrue(kortti.saldo() == 500);
    }
    
    @Test
    public void rahanLatausNegatiivinenVeloitetaanOikein() {
        paate.lataaRahaaKortille(kortti, -5000);
        assertTrue(paate.kassassaRahaa() == 100000);
    }
    
    
}
