-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost
-- Généré le : sam. 26 avr. 2025 à 13:38
-- Version du serveur : 10.4.32-MariaDB
-- Version de PHP : 8.1.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `unchk_db`
--

-- --------------------------------------------------------

--
-- Structure de la table `compt_rendu`
--

CREATE TABLE `compt_rendu` (
  `id` bigint(20) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `etudiant`
--

CREATE TABLE `etudiant` (
  `id` bigint(20) NOT NULL,
  `dateNaiss` datetime(6) DEFAULT NULL,
  `ine` varchar(255) DEFAULT NULL,
  `formation_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `date_naiss` datetime(6) DEFAULT NULL,
  `formation_id_primaire` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `formateur`
--

CREATE TABLE `formateur` (
  `id` bigint(20) NOT NULL,
  `specialite` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `formation`
--

CREATE TABLE `formation` (
  `id` bigint(20) NOT NULL,
  `niveau` varchar(20) NOT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `type` varchar(20) NOT NULL,
  `date_debut` datetime(6) DEFAULT NULL,
  `date_fin` datetime(6) DEFAULT NULL,
  `user_id` bigint(11) NOT NULL,
  `archived` tinyint(1) NOT NULL DEFAULT 0,
  `status` enum('APPROVED','PENDING','REJECTED') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `formation`
--

INSERT INTO `formation` (`id`, `niveau`, `nom`, `type`, `date_debut`, `date_fin`, `user_id`, `archived`, `status`) VALUES
(1, 'licence', 'test123', 'EN_LIGNE', '2025-04-25 00:00:00.000000', '2025-04-25 00:00:00.000000', 35, 0, 'PENDING'),
(2, 'licence', 'testFormation', 'PRESENTIEL', '2025-04-25 00:00:00.000000', '2025-04-29 00:00:00.000000', 35, 0, 'PENDING'),
(3, 'licence', 'Dev Web', 'EN_LIGNE', '2025-04-29 00:00:00.000000', '2025-05-04 00:00:00.000000', 35, 0, 'PENDING');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `archived` bit(1) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `date_naiss` datetime(6) DEFAULT NULL,
  `formation_id` bigint(20) DEFAULT NULL,
  `ine` varchar(255) DEFAULT NULL,
  `specialite` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `archived`, `email`, `nom`, `password`, `prenom`, `role`, `date_naiss`, `formation_id`, `ine`, `specialite`) VALUES
(6, b'0', 'test13@example.com', 'testNom', 'password123', 'testPrenom', 'ADMIN', '2025-04-25 16:10:41.000000', NULL, NULL, NULL),
(30, b'1', 'tes1.etud@example.com', 'test1Etu', '$2a$10$OkjQM/PqSLclFu62A1Bus.kq/Rodngm6e3yu2EsywjQmRfHAldvPS', 'test1Etu', 'ETUD', NULL, 1, 'N0245856XZ', NULL),
(31, b'0', 'tes.admin@example.com', 'admin', '$2a$10$vAZiReo5L3cKLLiIfh3Y4OukeEUn3pmj5GIt7QHLhmL13BQdhiDjy', 'admin', 'SUPERADMIN', NULL, NULL, NULL, NULL),
(32, b'0', 'ouzealdiey24@gmail.com', 'sow', '$2a$10$k/zD3lVjTVUp.j36wlDt4OcXIZ2NgVvNrAVkr71WG53yAXxiUgSkW', 'ouze', 'ADMIN', NULL, NULL, NULL, NULL),
(33, b'0', 'seck-niassy@gmail.com', 'seck', '$2a$10$RXapBnwfolXIQn5w4cZHButrOGnfYZ6TVRJeZxAzDaZqw4FG3j4bG', 'Moustapha', 'ETUD', '2025-03-13 00:00:00.000000', NULL, 'NAI58965DD', NULL),
(34, b'0', 'test_mail@gmail.com', 'seck', NULL, 'Niassy', 'FORMA', NULL, NULL, '', 'Informatique'),
(35, b'0', 'tyfa@exemple.com', 'Diallo', '$2a$10$tRJLNVYJwTGVo4mj/bTonuWzY87Jo6qtPhoXb0KyRsVlC1qeggyNC', 'FAty', 'FORMA', NULL, NULL, NULL, 'Informatique');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `compt_rendu`
--
ALTER TABLE `compt_rendu`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_80jg7grv231mu41q7lxq2s92y` (`user_id`);

--
-- Index pour la table `etudiant`
--
ALTER TABLE `etudiant`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_90okvy99sug5si0nugi2fntl8` (`user_id`),
  ADD KEY `FKfh8vhmqptpo8v43c4ige5grea` (`formation_id`);

--
-- Index pour la table `formateur`
--
ALTER TABLE `formateur`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_nfhhwwxiwn75gwd4vkt1fsow7` (`user_id`);

--
-- Index pour la table `formation`
--
ALTER TABLE `formation`
  ADD PRIMARY KEY (`id`),
  ADD KEY `formation_user_id` (`user_id`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `compt_rendu`
--
ALTER TABLE `compt_rendu`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `etudiant`
--
ALTER TABLE `etudiant`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT pour la table `formateur`
--
ALTER TABLE `formateur`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT pour la table `formation`
--
ALTER TABLE `formation`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `compt_rendu`
--
ALTER TABLE `compt_rendu`
  ADD CONSTRAINT `FK7imqamh4nywwr9kn0bshgd6t6` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `etudiant`
--
ALTER TABLE `etudiant`
  ADD CONSTRAINT `FK95uv9fy15rj1o5f5mae0eoy8f` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKfh8vhmqptpo8v43c4ige5grea` FOREIGN KEY (`formation_id`) REFERENCES `formation` (`id`);

--
-- Contraintes pour la table `formateur`
--
ALTER TABLE `formateur`
  ADD CONSTRAINT `FKa4ygy4opu1epsoatpm968d2lu` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `formation`
--
ALTER TABLE `formation`
  ADD CONSTRAINT `formation_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
