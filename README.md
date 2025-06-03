
Ez a projekt egy egyszerű könyvtárkezelő rendszer Spring Boot alapon, amely könyveket, tagokat és kölcsönzéseket kezel. 

## Technológiák

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- H2 In-Memory adatbázis
- Gradle
- JUnit + Jacoco
- Checkstyle
- GitHub Actions (CI pipeline)
- Artifactory (artifact deploy)


- Könyvek hozzáadása, listázása, törlése
- Tagok (felhasználók) kezelése
- Kölcsönzési lehetőségek
- REST API végpontok
- Alap szintű tesztelés és coverage mérés


- JUnit tesztek a service osztályokra
- Jacoco line & branch coverage
- Checkstyle konfiguráció, sikeres build feltétele


- GitHub Actions segítségével automatikus build
- Artifact deploy Artifactory-ba minden új commit után


Verziókövetés GitHub-on
CI pipeline
H2 adatbázis használata
In-memory mockDB (Java Collection alapú)
3 entitás (pl. Book, Member, Loan)
tesztlefedettség
service metódus tesztlefedettség
Checkstyle ellenőrzés


