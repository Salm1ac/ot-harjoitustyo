# Vaatimusmäärittely

## Sovelluksen tarkoitus

Sovellus on pelinomainen työkalu morsekoodin opetteluun. Käyttäjä yrittää tunnistaa yksittäisiä merkkejä tai sanoja,
jotka on esitetty morsekoodilla, ja saa onnistumisesta pisteitä. 

## Käyttäjät

Sovelluksella on vain yhdenlaisia käyttäjiä, joten käyttäjärooleja ei tässä tarkemmin eritellä.

## Toiminnallisuudet

- Käyttäjä voi käynnistää minipelejä, joissa opetellaan morsekoodia
- Minipeli 1: tunnista yksittäisiä merkkejä kunnes määrätty virhemäärä ylittyy
- Minipeli 2: tunnista kokonaisia sanoja kunnes määrätty virhemäärä ylittyy 
- Käyttäjä saa pisteitä oikeista vastauksista 
- Graafinen käyttöliittymä
  - Pääruutu, josta voi klikata auki minipelin 
  - Minipelissä morsekoodi esitetään vilkkuvalla valolla ja äänellä
  - Vastaukset rekisteröidään näppäimistöltä
  - Minipelin päätyttyä voi aloittaa sen uudestaan tai palata pääikkunaan
- Pisteet talletetaan tietokantaan ja huipputuloksia voi katsoa sovelluksesta
- Piipityksen voimakkuutta, nopeutta ja korkeutta voi säätää, samoin virheiden maksimimäärää
  - Näille voi asettaa myös omat oletusasetukset
 
## Toimintaympäristön rajoitteet

Sovellus toimii ainakin Windows-koneilla ja HY:n laitoksen Cubbli-koneilla (tosin pistetaulukoita varten on varmistettava, että tietokanta ei ole lukossa). Sovelluksen data on sen sisäisessä tiedostossa ja ulkoisessa tietokannassa.
