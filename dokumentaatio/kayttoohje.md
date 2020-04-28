# Käyttöohje

Viimeisin release on [tässä](https://github.com/Salm1ac/ot-harjoitustyo/releases/tag/viikko6).

## Ohjelman käynnistäminen

Ohjelman voi käynnistää ajamalla jar-tiedoston tiedostosijainnissa komennon

`java -jar ReMorse-1.0-SNAPSHOT.jar`

## Päävalikko

Ohjelma käynnistyy päävalikkoon:

![valikko](https://github.com/Salm1ac/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/paavalikko.png)

Valitsemalla "Harjoittele kirjaimia" tai "Harjoittele sanoja" saa avattua vastaavan minipelin. 
Ohjelman saa suljettua valitsemalla "Poistu".

## Minipelit

Minipelit toimivat molemmat samalla periaatteella.

![minipeli](https://github.com/Salm1ac/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/minipeli.png)

Uuden pelin saa aloitettua valitsemalla "Uusi peli". Tällöin ohjelma alkaa arpoa kirjaimia tai sanoja, joita se toistaa
vilkkuvalla valolla ja äänellä.
Pelaajan tehtävänä on tunnistaa morsekoodilla esitetty merkkijono, joka syötetään tekstikenttään ja lähetetään Enter-painikkeella.
Oikeasta arvauksesta saa pisteen, väärästä virheen. Peli näyttää pisteet ja virheet ruudulla, ja kolme virhettä päättää pelin.
Uuden pelin voi aloittaa koska tahansa, ja takaisin valikkoon voi palata valitsemalla "Valikkoon".
