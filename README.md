# ReMorse

ReMorse on morsekoodin opetteluun tarkoitettu pelinomainen työkalu. ReMorsessa tarkoitus on tunnistaa morsekoodilla esitettyjä kirjaimia ja sanoja. Kolmesta virheestä peli päättyy. 

Ohjelma on todettu toimivaksi ainakin Windows 10 -käyttöjärjestelmällä sekä etäyhteydellä laitoksen Cubbli Linux -koneella, molemmissa Javan versiolla 11.

Motivaatio ReMorse-ohjelmaan syntyi Keep Talking and Nobody Explodes -videopelistä, jossa pitää purkaa 
morsekoodia, ja alkuperäinen sanalista koostuukin juuri kyseisessä pelissä tunnistettavista sanoista. 
ReMorse yhdistää siis ohjelmoijansa näkökulmasta videopelissä kehittymisen ja pakollisen kurssisuorituksen. 
Eikös sitä sanottu että kannattaa tehdä hyötysovellus? 

## Missä mennään

Ohjelman perustoiminnallisuus on saatu kuntoon, ja jäljellä on enää lisätoiminnallisuuden kehittämistä sekä vanhan koodin hiomista. Ainakin luokat LetterGame ja WordGame voi käytännössä toteuttaa perinnällä. Jossain vaiheessa olisi kiva saada pistetaulukot mukaan. Sanoja on tulossa lisää, mahdollisesti Kotuksen kymmeniä tuhansia suomen kielen sanoja sisältävä lista. Vaatimusmäärittelyssä on jatkokehitysideana myös uudet aikaan perustuvat minipelit.

## Releaset

[Viikko 5](https://github.com/Salm1ac/ot-harjoitustyo/releases/tag/viikko5)

## Dokumentointi

[Vaatimusmäärittely](https://github.com/Salm1ac/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Työaikakirjanpito](https://github.com/Salm1ac/ot-harjoitustyo/blob/master/dokumentaatio/tuntikirjanpito.md)

[Arkkitehtuuri](https://github.com/Salm1ac/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

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

