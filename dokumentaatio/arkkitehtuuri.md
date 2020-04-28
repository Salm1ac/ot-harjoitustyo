# Arkkitehtuurikuvaus

## Rakenne

Ohjelmassa on kaksi pakkausta, `domain` ja `ui`. Näistä ensimmäiseen on sijoitettu sovelluslogiikan luokat ja jälkimmäiseen käyttöliittymäluokat.

Seuraavassa kuvassa on ohjelman luokkarakenne.

![arkkitehtuuri](https://github.com/Salm1ac/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/arkkitehtuuri.png)

## Käyttöliittymä

Käyttöliittymän käynnistää ReMorseUI-olio. Ohjelman käyttöliittymä on JavaFX-pohjainen ja koostuu kolmesta näkymästä: päävalikko, kirjainpeli ja sanapeli. Näkymien Scene-olioista yksi kerrallaan näytetään sovelluksen Stagella. Jokaisella näkymällä on oma FXML-tiedosto ja -ohjain. Lisäksi käyttöliittymään voidaan lukea myös Beeper-luokka, joka on ohjainten käytössä ja toistaa ääntä.

Käyttöliittymä on eriytetty sovelluslogiikasta. Näkymien ohjaimet vain kutsuvat sovelluslogiikan luokkia ja päivittävät näkymää saamillaan tiedoilla.

## Sovelluslogiikka

Ohjelman käynnistyessä AlphabetLoader-olio lukee tiedostosta morseaakkoston. Tämä aakkosto annetaan Parserille, jota PointGamet voivat sitten käyttää. PointGame on abstrakti luokka, joka sisältää minipelin logiikan. Sillä on kaksi aliluokkaa, kirjainten tunnistukseen tarkoitettu LetterGame ja sanojen tunnistukseen tarkoitettu WordGame. Lisäksi ohjelmaan liittyy MorseSequence-olioita, jotka muuntavat morsekoodia käyttöliittymän päivitykseen tarvittaviksi bittijonoiksi.

Seuraavassa kuvassa esitellään ohjelman toiminnallisuus, kun käyttäjä painaa kirjainpelissä Uusi peli -painiketta. Ajastimen ja piipittäjän toiminta on jätetty pois, koska ne eivät liity sovelluslogiikkaan. Lopun `nextBit()`-kutsut tulevat vasta uuden pelin aloituksen (kutsu `newGameButtonAction()`) jälkeen ja ajastin jatkaa tätä kutsumista lopetuskäskyyn saakka.

![newGameButtonAction](https://github.com/Salm1ac/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/newGameButtonAction().png)
