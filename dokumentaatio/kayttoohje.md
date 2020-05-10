# Käyttöohje

Viimeisin release on [tässä](https://github.com/Salm1ac/ot-harjoitustyo/releases/tag/lopullinen).

## Konfigurointi

Ohjelman voi konfiguroida laittamalla jar-tiedoston kanssa samaan kansioon `config.properties`-nimisen tiedoston. Tällä tiedostolla 
on seuraava muoto: 

`database=remorse.db` (käytettävän tietokannan nimi)

`volume=100` (äänenvoimakkuus, 0-127)

`note=69` (äänen korkeus, 50-100)

`timeUnit=16` (aikayksikkö, 1-60)

`maxErrors=2` (virheraja, 0-20)

Käyttäjä voi asettaa nämä oletusasetukset mielensä mukaan. Käyttäjä voi myös halutessaan käyttää eri tietokantaa. 
Tällöin tietokannassa tulisi olla seuraavanlaiset taulut:

`Words(row_id integer PRIMARY KEY, word_string varchar(50)`

`LetterScores(row_id integer PRIMARY KEY, points integer, time DATETIME DEFAULT (datetime('now', 'localtime')))`

`WordScores(row_id integer PRIMARY KEY, points integer, time DATETIME DEFAULT (datetime('now', 'localtime')))`

Sovelluksen mukana oleva tietokanta sisältää yli 92 000 suomen kielen sanaa. Sanat on indeksoitu, joten satunnaisten 
sanojen hakeminen on huomattavan nopeaa. Lisäksi tietokannassa on valmiina muutama huipputulos, joiden avulla on 
helpompaa huomata, onko tietokantayhteys onnistunut.

## Ohjelman käynnistäminen

Ohjelman voi käynnistää ajamalla jar-tiedoston tiedostosijainnissa komennon

`java -jar ReMorse.jar`

## Päävalikko

Ohjelma käynnistyy päävalikkoon:

![valikko](https://github.com/Salm1ac/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/paavalikko.png)

Valitsemalla "Harjoittele kirjaimia" tai "Harjoittele sanoja" saa avattua vastaavan minipelin. Pistetaulukoihin pääsee painamalla "Pistetaulukot" ja asetuksiin painikkeesta "Asetukset".
Ohjelman saa suljettua valitsemalla "Poistu".

## Minipelit

Minipelit toimivat molemmat samalla periaatteella.

![minipeli](https://github.com/Salm1ac/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/minipeli.png)

Uuden pelin saa aloitettua valitsemalla "Uusi peli". Tällöin ohjelma alkaa arpoa kirjaimia tai sanoja, joita se toistaa
vilkkuvalla valolla ja äänellä.
Pelaajan tehtävänä on tunnistaa morsekoodilla esitetty merkkijono, joka syötetään tekstikenttään ja lähetetään Enter-painikkeella.
Oikeasta arvauksesta saa pisteen, väärästä virheen. Peli näyttää pisteet ja virheet ruudulla, ja tietty määrä virheitä päättää pelin.
Uuden pelin voi aloittaa koska tahansa, ja takaisin valikkoon voi palata valitsemalla "Valikkoon".

## Pistetaulukot

Pistetaulukoissa säilötään molemmista minipeleistä viisi parasta tulosta ja niiden ajankohdat. Molempien minipelien tulokset voi tyhjentää erikseen "Tyhjennä"-painikkeista. Takaisin päävalikkoon pääsee valitsemalla "Valikkoon".

![pistetaulukot](https://github.com/Salm1ac/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/pistetaulukot.png)

## Asetukset

Asetuksista voi säätää piipityksen äänenvoimakkuutta (0-127), korkeutta (50-100) ja nopeutta (1-60). Näistä kaksi ensimmäistä ovat MIDIn `velocity`- ja `note`-arvot. Kolmas kertoo morsepisteen pituuden, yksikkönä noin sekunnin kuudeskymmenesosa. Lisäksi käyttäjä voi valita, montako virhettä sallitaan ennen häviötä (0-20). Takaisin päävalikkoon pääsee valitsemalla "Valikkoon".

![asetukset](https://github.com/Salm1ac/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/asetukset.png)
