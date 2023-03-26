-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost:8889
-- Généré le : dim. 26 mars 2023 à 22:07
-- Version du serveur :  5.7.34
-- Version de PHP : 7.4.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `FinalJavaBDD`
--

-- --------------------------------------------------------

--
-- Structure de la table `Admin`
--

CREATE TABLE `Admin` (
  `Id_admin` int(11) NOT NULL,
  `Prenom_Admin` varchar(100) NOT NULL,
  `Nom_Admin` varchar(100) NOT NULL,
  `mail_admin` varchar(100) NOT NULL,
  `phone_number_admin` varchar(100) NOT NULL,
  `admin_username` varchar(100) NOT NULL,
  `admin_password` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `Admin`
--

INSERT INTO `Admin` (`Id_admin`, `Prenom_Admin`, `Nom_Admin`, `mail_admin`, `phone_number_admin`, `admin_username`, `admin_password`) VALUES
(1, 'marwane', 'boubekeur', 'marwane.boubekeur@edu.ece.fr', '019283729', 'warmane95', '9575'),
(2, 'ralphou', 'BJ', 'aiuaz', '1234', 'rabj', '123');

-- --------------------------------------------------------

--
-- Structure de la table `Car`
--

CREATE TABLE `Car` (
  `registration` varchar(100) NOT NULL,
  `model` varchar(100) NOT NULL,
  `make` varchar(100) NOT NULL,
  `garageadress` varchar(500) NOT NULL,
  `priceperday` float NOT NULL,
  `date` date NOT NULL,
  `due` date NOT NULL,
  `availability` tinyint(1) NOT NULL,
  `picture` varchar(500) DEFAULT NULL,
  `ID_client` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `Car`
--

INSERT INTO `Car` (`registration`, `model`, `make`, `garageadress`, `priceperday`, `date`, `due`, `availability`, `picture`, `ID_client`) VALUES
('AVZ17BZP', 'X5', 'BMW', '34 Abbey Road', 200, '2023-01-01', '2023-01-07', 1, NULL, 1),
('BAI92JJA', 'GLA', 'Mercedes', '80 Edgware Road', 200, '2023-01-01', '2023-01-05', 1, NULL, NULL),
('IGA91JHVA', 'M2', 'BMW', '18 Acol Road', 300, '2023-03-16', '2023-04-01', 0, NULL, 3),
('JBS99BZB', 'Golf', 'Volkswagen', '57 Cambridge Street', 70, '2023-01-01', '2023-01-04', 0, NULL, 1),
('JNBA92NZ', 'Urus', 'Lamborghini', '80 rue de la paix', 500, '2023-01-01', '2023-01-13', 0, NULL, 2),
('PAN81JHA', '308', 'Peugeot', '5 Avenue des Champs Elysées', 70, '2023-01-01', '2023-01-04', 0, NULL, 1),
('PAN91NAA', '911', 'Porsche', '9 avenue de Versailles', 500, '2023-01-01', '2023-01-06', 1, NULL, NULL),
('XARY00HBU', 'Auris', 'Toyota', '80 Liverpool Street London', 75, '2023-01-01', '2023-01-06', 1, NULL, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `Client`
--

CREATE TABLE `Client` (
  `ID_client` int(11) NOT NULL,
  `Prenom_client` varchar(100) NOT NULL,
  `Nom_client` varchar(100) NOT NULL,
  `phone_number_client` varchar(100) NOT NULL,
  `mail_client` varchar(100) NOT NULL,
  `statut_client` varchar(100) NOT NULL,
  `client_username` varchar(100) NOT NULL,
  `client_password` varchar(100) NOT NULL,
  `Id_admin` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `Client`
--

INSERT INTO `Client` (`ID_client`, `Prenom_client`, `Nom_client`, `phone_number_client`, `mail_client`, `statut_client`, `client_username`, `client_password`, `Id_admin`) VALUES
(1, 'valerian', 'darmente', '13425', 'uidgeiz', 'Individual', 'valou', '1234', NULL),
(2, 'Kelian', 'Arnoux', '182929', 'kelianoziz', 'Business', 'katrium', 'katrium', NULL),
(3, 'Brad', 'Pitt', '14567809', 'bradpitt@gmail.com', 'Business', 'bradpitt', 'iambradpitt', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `contract`
--

CREATE TABLE `contract` (
  `ID_contract` int(11) NOT NULL,
  `total_due` float DEFAULT NULL,
  `date` date DEFAULT NULL,
  `due` date DEFAULT NULL,
  `registration` varchar(100) DEFAULT NULL,
  `Id_admin` int(11) DEFAULT NULL,
  `ID_client` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `contract`
--

INSERT INTO `contract` (`ID_contract`, `total_due`, `date`, `due`, `registration`, `Id_admin`, `ID_client`) VALUES
(3, 2000, '2023-01-05', '2023-01-15', 'AVZ17BZP', 1, 1),
(5, 3840, '2023-03-16', '2023-04-01', 'IGA91JHVA', 1, 3),
(6, 210, '2023-01-01', '2023-01-04', 'PAN81JHA', 1, 1),
(7, 4800, '2023-01-01', '2023-01-13', 'JNBA92NZ', 1, 2),
(8, NULL, NULL, NULL, 'PAN91NAA', 1, NULL),
(9, NULL, NULL, NULL, 'BAI92JJA', 1, NULL),
(10, 210, '2023-01-01', '2023-01-04', 'JBS99BZB', 1, 1),
(11, NULL, NULL, NULL, 'XARY00HBU', 1, NULL);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `Admin`
--
ALTER TABLE `Admin`
  ADD PRIMARY KEY (`Id_admin`);

--
-- Index pour la table `Car`
--
ALTER TABLE `Car`
  ADD PRIMARY KEY (`registration`),
  ADD KEY `Car_Client_FK` (`ID_client`);

--
-- Index pour la table `Client`
--
ALTER TABLE `Client`
  ADD PRIMARY KEY (`ID_client`),
  ADD KEY `Client_Admin_FK` (`Id_admin`);

--
-- Index pour la table `contract`
--
ALTER TABLE `contract`
  ADD PRIMARY KEY (`ID_contract`),
  ADD UNIQUE KEY `contract_Car_AK` (`registration`),
  ADD KEY `contract_Admin0_FK` (`Id_admin`),
  ADD KEY `contract_Client1_FK` (`ID_client`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `Admin`
--
ALTER TABLE `Admin`
  MODIFY `Id_admin` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `Client`
--
ALTER TABLE `Client`
  MODIFY `ID_client` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `contract`
--
ALTER TABLE `contract`
  MODIFY `ID_contract` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `Car`
--
ALTER TABLE `Car`
  ADD CONSTRAINT `Car_Client_FK` FOREIGN KEY (`ID_client`) REFERENCES `Client` (`ID_client`);

--
-- Contraintes pour la table `Client`
--
ALTER TABLE `Client`
  ADD CONSTRAINT `Client_Admin_FK` FOREIGN KEY (`Id_admin`) REFERENCES `Admin` (`Id_admin`);

--
-- Contraintes pour la table `contract`
--
ALTER TABLE `contract`
  ADD CONSTRAINT `contract_Admin0_FK` FOREIGN KEY (`Id_admin`) REFERENCES `Admin` (`Id_admin`),
  ADD CONSTRAINT `contract_Car_FK` FOREIGN KEY (`registration`) REFERENCES `Car` (`registration`),
  ADD CONSTRAINT `contract_Client1_FK` FOREIGN KEY (`ID_client`) REFERENCES `Client` (`ID_client`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
