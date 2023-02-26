#------------------------------------------------------------
#        Script MySQL.
#------------------------------------------------------------


#------------------------------------------------------------
# Table: Admin
#------------------------------------------------------------

CREATE TABLE Admin(
        NumAdmin Int  Auto_increment  NOT NULL ,
        Prenom   Varchar (50) NOT NULL ,
        Nom      Varchar (50) NOT NULL ,
        Mail     Varchar (50) NOT NULL ,
        Tel      Int NOT NULL
	,CONSTRAINT Admin_PK PRIMARY KEY (NumAdmin)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Client
#------------------------------------------------------------

CREATE TABLE Client(
        Numeros  Int  Auto_increment  NOT NULL ,
        Prenom   Varchar (50) NOT NULL ,
        Nom      Varchar (50) NOT NULL ,
        TEL      Int NOT NULL ,
        MAIL     Varchar (50) NOT NULL ,
        NumAdmin Int
	,CONSTRAINT Client_PK PRIMARY KEY (Numeros)

	,CONSTRAINT Client_Admin_FK FOREIGN KEY (NumAdmin) REFERENCES Admin(NumAdmin)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Car
#------------------------------------------------------------

CREATE TABLE Car(
        NumImmatriculation Int NOT NULL ,
        Modele             Varchar (50) NOT NULL ,
        Marque             Varchar (50) NOT NULL ,
        Annee              Int NOT NULL ,
        Dispo_ou_pas       Bool NOT NULL ,
        Image              Varchar (200) NOT NULL ,
        GarageAdress       Varchar (200) NOT NULL ,
        Date               Date NOT NULL ,
        Price              Float NOT NULL ,
        Due                Date NOT NULL ,
        Caution            Float NOT NULL ,
        Numeros            Int NOT NULL ,
        NumAdmin           Int NOT NULL
	,CONSTRAINT Car_PK PRIMARY KEY (NumImmatriculation)

	,CONSTRAINT Car_Client_FK FOREIGN KEY (Numeros) REFERENCES Client(Numeros)
	,CONSTRAINT Car_Admin0_FK FOREIGN KEY (NumAdmin) REFERENCES Admin(NumAdmin)
)ENGINE=InnoDB;

