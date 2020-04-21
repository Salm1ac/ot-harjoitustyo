Seuraavassa kuvassa on ohjelman pakkausrakenne.

![arkkitehtuuri](https://github.com/Salm1ac/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/arkkitehtuuri.png)

Seuraavassa kuvassa esitellään ohjelman toiminnallisuus, kun käyttäjä painaa Uusi peli -painiketta. Ajastimen toiminta on jätetty pois, koska se on LetterSceneControllerin sisäinen. Lopun `nextBit()`-kutsut tulevat vasta uuden pelin aloituksen (kutsu `newGameButtonAction()`) jälkeen ja ajastin jatkaa tätä kutsumista lopetuskäskyyn saakka.

![newGameButtonAction](https://github.com/Salm1ac/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/newGameButtonAction().png)
