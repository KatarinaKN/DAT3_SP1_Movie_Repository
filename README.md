# DAT3 - SP1

![Java Version](https://img.shields.io/badge/Java-25-blue.svg)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-blue?logo=postgresql&logoColor=white)
![Hibernate](https://img.shields.io/badge/Hibernate-JPA-59666C?logo=hibernate&logoColor=white)
![JUnit5](https://img.shields.io/badge/JUnit-5-25A162?logo=junit5&logoColor=white)
![Testcontainers](https://img.shields.io/badge/Testcontainers-1.20-2496ED?logo=docker&logoColor=white)
![License](https://img.shields.io/badge/License-GPL--3.0-green.svg)

En applikation til datafetching via TMBd-API

## Beskrivelse
Denne applikation har til formål at hente data via API og gemme det i en postgreSQL database, hvorefter vi kan manipulere med data via CRUD-metoder.

---

## 🛠️ Teknologier & Biblioteker
* **Sprog:** Java 25
* **Build Tool:** Apache Maven
* **Database:** PostgreSQL & JPA / Hibernate ORM
* **JSON Processing:** Jackson 
* **Boilerplate Reduction:** Lombok
* **Testing:** JUnit 5, Hamcrest & Testcontainers (Isoleret PostgreSQL-container til integrationstests)

---

### Datamodel & Entity Relationer
* `Movie` fungerer som **ejerside (Owning Side)** for relationerne.
* **Movie ↔ Director:** `@ManyToOne` (En film har én instruktør, en instruktør kan have flere film).
* **Movie ↔ Actor:** `@ManyToMany` via koblingstabellen `movie_actor`.
* **Movie ↔ Genre:** `@ManyToMany` via koblingstabellen `movie_genre`.

--- 
## 🚀 Funktioner & Kravopfyldelse
- [x] Hentning af alle danske film udgivet de seneste 5 år fra TMDb API.
- [ ] Udlæsning af tilhørende skuespillere (`cast`), instruktører (`crew`) og genrer.
- [ ] Gemme alle hentede entiteter i lokal PostgreSQL-database via JPA.
- [ ] Full CRUD-funktionalitet på film (opret, læs, opdater og slet).
- [ ] Case-insensitive søgning på filmtitler.
- [ ] Beregning af samlet gennemsnitlig rating.
- [ ] Top-10 over lavest/højest ratede og mest populære film.
- [x] Integrationstests skrevet med JUnit 5 og Testcontainers.

---
## Gruppemedlemmer
Vi er en projektgruppe på tre studerende:

* **Lucas:** [GladVillainy](https://github.com/GladVillainy)
* **Stine:** [stinetorndal](https://github.com/stinetorndal)
* **Katarina:** [KatarinaKN](https://github.com/KatarinaKN)
---
## Licens:
Dette projekt er licenseret under GPL-3.0 License - se den officielle side på https://choosealicense.com/licenses/gpl-3.0/
 for fuldstændige detaljer.

Dette betyder, at koden er open-source; andre må meget gerne inspicere, modificere og lære af koden i uddannelsesøjemed, men koden må ikke lukkes inde i et kommercielt, proprietært system.
---
