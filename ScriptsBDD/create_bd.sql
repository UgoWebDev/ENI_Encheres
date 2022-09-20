-- Script de création de la base de données ENCHERES
--   type :      SQL Server 2012
--

CREATE DATABASE ENCHERE
GO
USE ENCHERE
GO

----------------------------------------------------------------------------------------------------------------

CREATE TABLE CATEGORIES (
    no_categorie   INTEGER IDENTITY(1,1) NOT NULL,
    libelle        VARCHAR(30) NOT NULL
)

ALTER TABLE CATEGORIES ADD constraint categorie_pk PRIMARY KEY (no_categorie)

----------------------------------------------------------------------------------------------------------------

CREATE TABLE ADRESSES (
	no_adresse       INTEGER IDENTITY(1,1) NOT NULL,
    rue              VARCHAR(30) NOT NULL,
    code_postal      CHAR(5) NOT NULL,
    ville            VARCHAR(30) NOT NULL
)

ALTER TABLE ADRESSES ADD constraint adresse_pk PRIMARY KEY  (no_adresse)

----------------------------------------------------------------------------------------------------------------

CREATE TABLE UTILISATEURS (
    no_utilisateur   INTEGER IDENTITY(1,1) NOT NULL,
    pseudo           VARCHAR(30) NOT NULL,
    nom              VARCHAR(30) NOT NULL,
    prenom           VARCHAR(30) NOT NULL,
    email            VARCHAR(50) NOT NULL,
    telephone        VARCHAR(15),
    no_adresse		 INTEGER NOT NULL,
    mot_de_passe     VARCHAR(30) NOT NULL,
    credit           INTEGER NOT NULL,
    administrateur   bit NOT NULL
)

ALTER TABLE UTILISATEURS ADD constraint utilisateur_pk PRIMARY KEY (no_utilisateur)

ALTER TABLE UTILISATEURS
    ADD CONSTRAINT utilisateur_adresse_fk FOREIGN KEY ( no_adresse ) REFERENCES  ADRESSES (no_adresse)
	ON DELETE NO ACTION 
    ON UPDATE NO action 

ALTER TABLE UTILISATEURS
    ADD CONSTRAINT pseudo_uc UNIQUE ( pseudo )

ALTER TABLE UTILISATEURS
    ADD CONSTRAINT email_uc UNIQUE ( email )

----------------------------------------------------------------------------------------------------------------

CREATE TABLE ARTICLES (
    no_article                    INTEGER IDENTITY(1,1) NOT NULL,
    nom_article                   VARCHAR(30) NOT NULL,
    description                   VARCHAR(300) NOT NULL,
	date_debut_encheres           DATE NOT NULL,
    date_fin_encheres             DATE NOT NULL,
    prix_initial                  INTEGER,
    prix_vente                    INTEGER,
    no_utilisateur                INTEGER NOT NULL,
    no_categorie                  INTEGER NOT NULL,
	no_adresse					  INTEGER NOT NULL
)

ALTER TABLE ARTICLES ADD constraint article_pk PRIMARY KEY (no_article)

ALTER TABLE ARTICLES
    ADD CONSTRAINT article_adresse_fk FOREIGN KEY ( no_adresse ) REFERENCES  ADRESSES (no_adresse)
	ON DELETE NO ACTION 
    ON UPDATE NO action 

ALTER TABLE ARTICLES
    ADD CONSTRAINT article_utilisateur_fk FOREIGN KEY ( no_utilisateur )
        REFERENCES utilisateurs ( no_utilisateur )
	ON DELETE NO ACTION 
    ON UPDATE NO action 

ALTER TABLE ARTICLES
    ADD CONSTRAINT article_categorie_fk FOREIGN KEY ( no_categorie )
        REFERENCES categories ( no_categorie )
	ON DELETE NO ACTION 
    ON UPDATE NO action 

----------------------------------------------------------------------------------------------------------------

CREATE TABLE ENCHERES(	
	no_enchere		INTEGER IDENTITY(1,1) NOT NULL,
	date_enchere	DATETIME NOT NULL,
	montant_enchere INTEGER NOT NULL,
	no_article		INTEGER NOT NULL,
	no_utilisateur	INTEGER NOT NULL
 )

ALTER TABLE ENCHERES ADD constraint enchere_pk PRIMARY KEY ( no_enchere)
 
ALTER TABLE ENCHERES
    ADD CONSTRAINT enchere_utilisateur_fk FOREIGN KEY ( no_utilisateur ) REFERENCES UTILISATEURS ( no_utilisateur )
	ON DELETE NO ACTION 
    ON UPDATE no action 

ALTER TABLE ENCHERES
    ADD CONSTRAINT enchere_article_fk FOREIGN KEY ( no_article ) REFERENCES ARTICLES ( no_article )
	ON DELETE NO ACTION 
    ON UPDATE NO action 
	
----------------------------------------------------------------------------------------------------------------







