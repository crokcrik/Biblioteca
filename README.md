# 📚 Sistema di Gestione Biblioteca API

Questo progetto è un'applicazione Backend sviluppata con **Spring Boot** per la gestione di una biblioteca digitale. Permette la gestione di libri, autori e prestiti, integrando un sistema di sicurezza basato su token JWT.

L'applicazione è containerizzata con **Docker** e distribuita in cloud su **Render**.

## 🚀 Live Demo
Puoi testare le API in tempo reale tramite l'interfaccia Swagger qui:
👉 [**Biblioteca API - Swagger UI**](https://biblioteca-i8l0.onrender.com/swagger-ui/index.html)

---

## 🛠️ Tecnologie Utilizzate

* **Java 21**
* **Spring Boot 3.x**
* **Spring Security & JWT** (JSON Web Token) per l'autenticazione.
* **Spring Data JPA**: Per la gestione dello strato di persistenza.
* **PostgreSQL**: Database relazionale utilizzato in ambiente di produzione.
* **Docker**: Per la containerizzazione e la portabilità dell'applicazione.
* **Swagger/OpenAPI**: Per la documentazione interattiva delle API.

---

## 🏗️ Architettura e Deployment

Il progetto segue una pipeline di **Continuous Deployment**:
1.  Il codice viene pushato su **GitHub**.
2.  **Render** rileva le modifiche e avvia la build tramite il **Dockerfile**.
3.  L'app viene compilata con Maven ed eseguita in un container Docker isolato.
4.  La connessione al database avviene tramite variabili d'ambiente criptate per garantire la sicurezza.

---

## 🔑 Funzionalità Principali

* **Autenticazione**: Registrazione e Login utenti con generazione di Token JWT.
* **Gestione Libri**: Operazioni CRUD complete per il catalogo libri.
* **Sistema Prestiti**: Logica di business per gestire la disponibilità dei volumi.
* **Sicurezza**: Protezione delle rotte sensibili tramite filtri di sicurezza personalizzati.

---

## 👤 Autore
* **GitHub**: [@crokcrik](https://github.com/crokcrik)
