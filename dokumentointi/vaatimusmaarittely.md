# Vaatimusmäärittely

## Sovelluksen tarkoitus

Sovellus on pelinomainen työkalu morsekoodin opetteluun. Käyttäjä yrittää tunnistaa yksittäisiä merkkejä tai sanoja,
jotka on esitetty morsekoodilla, ja saa onnistumisesta pisteitä. 

## Käyttäjät

Sovelluksella on vain yhdenlaisia käyttäjiä, joten käyttäjärooleja ei tässä tarkemmin eritellä.

## Suunnitellut toiminnallisuudet

- Käyttäjä voi käynnistää minipelejä, joissa opetellaan morsekoodia.
- Minipeli 1: tunnista yksittäisiä merkkejä kunnes määrätty virhemäärä ylittyy
- Minipeli 2: tunnista kokonaisia sanoja kunnes määrätty virhemäärä ylittyy
- Käyttäjä saa pisteitä oikeista vastauksista
- Graafinen käyttöliittymä
 - Pääruutu, josta voi klikata auki minipelin
 - Minipelissä morsekoodi esitetään vilkkuvalla valolla, äänellä tai molemmilla 
 (siis toteutetaan vähintään yksi näistä)
 - Vastaukset rekisteröidään näppäimistöltä
 - Minipelin päätyttyä voi aloittaa sen uudestaan tai palata pääikkunaan
 
## Toimintaympäristön rajoitteet

Sovelluksen tulee toimia Windows-koneilla ja HY:n laitoksen Cubbli-koneilla. Sovelluksen data säilytetään
paikallisella levyllä aluksi tiedostossa, myöhemmin ehkä tietokannassa.
