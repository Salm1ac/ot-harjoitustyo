# Testausdokumentti

Ohjelmaa on testattu käsin (erityisesti graafisen käyttöliittymän tapauksessa) ja monipuolisen automaattisen 
yksikkö- ja integraatiotestauksen avulla.

## Automaattinen testaus

Ohjelmaa on testattu JUnitin avulla yksikkötesteillä ja integraatiotesteillä. Sovelluksen ne osat, jotka eivä liity 
puhtaasti käyttöliittymään, saavuttavat testauksessa 98 % rivikattavuuden ja 89 % haarakattavuuden. 
Itse asiassa kaikilla luokilla paitsi DatabaseHandlerillä on täysi kattavuus; tässä yhdessä luokassa käsitellään poikkeuksia, 
joita on hankala tuottaa manuaalisesti, ja siksi siinä on vain 95 % rivikattavuus ja 66 % haarakattavuus.

![kattavuus](https://github.com/Salm1ac/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/kattavuus.png)

### Dataluokat

Dataa käsittelee vain kaksi luokkaa, AlphabetLoader ja DatabaseHandler. Nämä molemmat testaantuvat enimmäkseen muiden luokkien 
testien kautta, mutta niillä on myös omia erikoistapauksiin liittyviä testejä.

### Sovelluslogiikan luokat

Sovelluslogiikan luokkiin liittyy myös tärkein ja monipuolisin testaus. Erityisesti testiluokat WordGameRandomTest
ja LetterGameRandomTest testaavat monipuolisesti sovelluksen logiikkaa. Niissä testataan itse pelien lisäksi tietokannan käsittelijää, 
parseria ja aakkoston lataajaa integroituna testauksena. Myös eri konfiguraatioita testataan vaihtamalla esimerkiksi 
virherajan arvoa. Myös MorseSequence- ja Parser-luokilla on yksinkertaista testausta, lähinnä yksikkötasolla. Vain HighScore-luokalla 
ei ole omaa testiluokkaa, koska se on hyvin yksinkertainen ja tulee testatuksi muissa testeissä.

## Järjestelmätestaus

Sovellusta on testattu Windows-tietokoneella sekä Cubbli Linuxilla etäyhteydellä. Sovelluksen on todettu toimivan 
konfiguraatiotiedoston kanssa, eri konfiguraatioilla ja ilman konfiguraatiota. Sovellus toimii myös ilman tietokantaa ja testit 
käyttävät eri tietokantoja, mutta puutteelliset tietokannat vähentävät toiminnallisuutta huomattavasti. Käyttöliittymää on 
testattu manuaalisesti, kunnes se on saatu toimimaan.

