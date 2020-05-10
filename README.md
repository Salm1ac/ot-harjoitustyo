# ReMorse

ReMorse on morsekoodin opetteluun tarkoitettu pelinomainen työkalu. ReMorsessa tarkoitus on tunnistaa morsekoodilla esitettyjä kirjaimia ja sanoja. Kolmesta virheestä (tai muusta asetetusta määrästä) peli päättyy. 

Ohjelma on todettu toimivaksi ainakin Windows 10 -käyttöjärjestelmällä sekä etäyhteydellä laitoksen Cubbli Linux -koneella, molemmissa Javan versiolla 11. Etäyhteydellä tosin ääni tulee ikävästi viiveellä, ja on varmistettava että tietokanta ei ole lukossa.

Ohjelman tietokannan sanat ovat peräisin [Kotimaisten kielten keskuksen nykysuomen sanalistasta](http://kaino.kotus.fi/sanat/nykysuomi/), joka on julkaistu [CC BY 3.0](https://creativecommons.org/licenses/by/3.0/deed.fi)-lisenssillä. Sanalistasta käytetään vain sanoja ja ei-alfanumeeriset sanat on poistettu.

Motivaatio ReMorse-ohjelmaan syntyi Keep Talking and Nobody Explodes -videopelistä, jossa pitää purkaa 
morsekoodia, ja alkuperäinen sanalista koostuukin juuri kyseisessä pelissä tunnistettavista sanoista. 
ReMorse yhdistää siis ohjelmoijansa näkökulmasta videopelissä kehittymisen ja pakollisen kurssisuorituksen. 
Eikös sitä sanottu että kannattaa tehdä hyötysovellus? 

## Releaset

[Viikko 5](https://github.com/Salm1ac/ot-harjoitustyo/releases/tag/viikko5)

[Viikko 6](https://github.com/Salm1ac/ot-harjoitustyo/releases/tag/viikko6)

[Lopullinen](https://github.com/Salm1ac/ot-harjoitustyo/releases/tag/lopullinen)

## Dokumentointi

[Vaatimusmäärittely](https://github.com/Salm1ac/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Työaikakirjanpito](https://github.com/Salm1ac/ot-harjoitustyo/blob/master/dokumentaatio/tuntikirjanpito.md)

[Arkkitehtuuri](https://github.com/Salm1ac/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

[Käyttöohje](https://github.com/Salm1ac/ot-harjoitustyo/blob/master/dokumentaatio/kayttoohje.md)

[Testaus](https://github.com/Salm1ac/ot-harjoitustyo/blob/master/dokumentaatio/testaus.md)

## Komentoriviohjeet

### Testaus

Ohjelmaa voi testata komennolla `mvn test`

Testikattavuusraportin voi luoda komennolla `mvn test jacoco:report`

Syntyvä kattavuusraportti luodaan osoitteeseen *target/site/jacoco/index.html*

### Suoritettava jar-tiedosto

Suoritettava jar-tiedosto saadaan komennolla `mvn package`

Syntyvä jar-tiedosto luodaan osoitteeseen *target/ReMorse-1.0-SNAPSHOT.jar*

### Checkstyle

Checkstyle-tarkistus suoritetaan komennolla `mvn jxr:jxr checkstyle:checkstyle`

Syntyvä raportti luodaan osoitteeseen *target/site/checkstyle.html*

### JavaDoc

JavaDoc-dokumentaatio luodaan komennolla `mvn javadoc:javadoc`

Dokumentaatio luodaan osoitteeseen *target/site/apidocs/*

