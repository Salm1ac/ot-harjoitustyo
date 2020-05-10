# Arkkitehtuurikuvaus

## Rakenne

Ohjelmassa on kolme pakkausta, `data`, `domain` ja `ui`. Näistä ensimmäiseen on sijoitettu tiedostojen käsittely, toiseen sovelluslogiikan luokat ja viimeiseen käyttöliittymäluokat.

## Käyttöliittymä

Käyttöliittymän käynnistää ReMorseUI-olio. Ohjelman käyttöliittymä on JavaFX-pohjainen ja koostuu viidestä näkymästä: päävalikko, kirjainpeli, sanapeli, pistetaulukot ja asetukset. Näkymien Scene-olioista yksi kerrallaan näytetään sovelluksen Stagella. Jokaisella näkymällä on oma FXML-tiedosto ja -ohjain. Lisäksi käyttöliittymään voidaan lukea myös Beeper-luokka, joka on ohjainten käytössä ja toistaa ääntä.

Käyttöliittymä on eriytetty sovelluslogiikasta. Näkymien ohjaimet vain kutsuvat sovelluslogiikan luokkia ja päivittävät näkymää saamillaan tiedoilla.

## Sovelluslogiikka ja tiedostojen käsittely

Ohjelman käynnistyessä DatabaseHandler-olio ottaa yhteyden tietokantaan ja AlphabetLoader-olio lukee tiedostosta morseaakkoston. Tämä aakkosto annetaan Parserille, jota PointGamet voivat sitten käyttää. PointGame on abstrakti luokka, joka sisältää minipelin logiikan. Sillä on kaksi aliluokkaa, kirjainten tunnistukseen tarkoitettu LetterGame ja sanojen tunnistukseen tarkoitettu WordGame. Lisäksi ohjelman logiikkaan liittyy MorseSequence-olioita, jotka muuntavat morsekoodia käyttöliittymän päivitykseen tarvittaviksi bittijonoiksi.
Käyttäjän saamat pisteet talletetaan tietokantaan, josta viisi parasta voidaan noutaa pistetaulukoihin. Tässä käytetään apuna 
yksinkertaisia HighScore-olioita. Peleillä, bittijonoilla ja piipittäjällä on tiettyjä ominaisuuksia, kuten virheraja tai aikayksikkö, 
joita voidaan säätää asetusohjaimen kautta sovelluksen ollessa käynnissä.

Seuraavassa kuvassa esitellään ohjelman toiminnallisuus, kun käyttäjä painaa kirjainpelissä Uusi peli -painiketta. Ajastimen ja piipittäjän toiminta on jätetty pois, koska ne eivät liity sovelluslogiikkaan. Lopun `nextBit()`-kutsut tulevat vasta uuden pelin aloituksen (kutsu `newGameButtonAction()`) jälkeen ja ajastin jatkaa tätä kutsumista lopetuskäskyyn saakka.

![newGameButtonAction](https://github.com/Salm1ac/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/newGameButtonAction().png)

## Heikkouksia

Koko tietokannan käsittely tapahtuu yhdellä oliolla. Parempi ratkaisu olisi ollut käyttää DAO-mallin mukaisesti useaa oliota, joista 
jokaisella on tietty vastuu.

Aluksi yksi tavoite oli käyttää muitakin aakkostoja kuin morsea. Tämä kaatui lopulta siihen, että valoon ja ääneen perustuva esitys 
ei oikein toimi muilla aakkostoilla, ja toiminnallisuus olisi pitänyt lisätä erikseen.
