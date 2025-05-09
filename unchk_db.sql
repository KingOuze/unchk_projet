-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost
-- Généré le : mar. 29 avr. 2025 à 15:26
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
  `status` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `formation`
--

INSERT INTO `formation` (`id`, `niveau`, `nom`, `type`, `date_debut`, `date_fin`, `user_id`, `archived`, `status`) VALUES
(1, 'licence', 'test123', 'EN_LIGNE', '2025-04-25 00:00:00.000000', '2025-04-25 00:00:00.000000', 35, 0, 'APPROVED'),
(2, 'licence', 'testFormation', 'PRESENTIEL', '2025-04-25 00:00:00.000000', '2025-04-29 00:00:00.000000', 35, 0, 'REJECTED'),
(3, 'licence', 'Dev Web', 'EN_LIGNE', '2025-04-29 00:00:00.000000', '2025-05-04 00:00:00.000000', 35, 0, 'PENDING'),
(4, 'master', 'Data analiste', 'PRESENTIEL', '2025-06-02 00:00:00.000000', '2025-10-28 00:00:00.000000', 35, 0, 'PENDING');

-- --------------------------------------------------------

--
-- Structure de la table `history`
--

CREATE TABLE `history` (
  `id` bigint(20) NOT NULL,
  `entitie` varchar(255) DEFAULT NULL,
  `login_time` datetime(6) DEFAULT NULL,
  `logout_time` datetime(6) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `history`
--

INSERT INTO `history` (`id`, `entitie`, `login_time`, `logout_time`, `user_id`) VALUES
(1, NULL, '2025-04-28 13:06:58.000000', '2025-04-28 13:17:47.000000', 35),
(2, NULL, '2025-04-28 13:18:01.000000', '2025-04-28 15:05:40.000000', 31),
(4, NULL, '2025-04-28 15:05:55.000000', '2025-04-28 15:52:59.000000', 35),
(5, NULL, '2025-04-28 15:54:02.000000', '2025-04-28 16:21:47.000000', 36),
(6, NULL, '2025-04-28 16:21:59.000000', '2025-04-29 11:27:09.000000', 31),
(8, NULL, '2025-04-29 11:28:56.000000', '2025-04-29 11:58:21.000000', 35);

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
(30, b'0', 'tes1.etud@example.com', 'test1Etu', '$2a$10$OkjQM/PqSLclFu62A1Bus.kq/Rodngm6e3yu2EsywjQmRfHAldvPS', 'test1Etu', 'ETUD', NULL, 1, 'N0245856XZ', NULL),
(31, b'0', 'tes.admin@example.com', 'admin', '$2a$10$vAZiReo5L3cKLLiIfh3Y4OukeEUn3pmj5GIt7QHLhmL13BQdhiDjy', 'admin', 'SUPERADMIN', NULL, NULL, NULL, NULL),
(32, b'0', 'ouzealdiey24@gmail.com', 'sow', '$2a$10$k/zD3lVjTVUp.j36wlDt4OcXIZ2NgVvNrAVkr71WG53yAXxiUgSkW', 'ouze', 'ADMIN', NULL, NULL, NULL, NULL),
(33, b'0', 'seck-niassy@gmail.com', 'seck', '$2a$10$RXapBnwfolXIQn5w4cZHButrOGnfYZ6TVRJeZxAzDaZqw4FG3j4bG', 'Moustapha', 'ETUD', '2025-03-13 00:00:00.000000', NULL, 'NAI58965DD', NULL),
(34, b'0', 'test_mail@gmail.com', 'seck', NULL, 'Niassy', 'FORMA', NULL, NULL, '', 'Informatique'),
(35, b'0', 'tyfa@exemple.com', 'Diallo', '$2a$10$tRJLNVYJwTGVo4mj/bTonuWzY87Jo6qtPhoXb0KyRsVlC1qeggyNC', 'FAty', 'FORMA', NULL, NULL, NULL, 'Informatique'),
(36, b'1', 'admin24@exemple.com', 'admin', '$2a$10$nOPF75SSQ6/1iQjqKQh41uUGkVtFXsDGzcZwMcj2ULw/5fDd0ReLW', 'admin', 'ADMIN', NULL, NULL, NULL, NULL),
(37, b'0', 'md-seck@gmail.com', 'seck', '$2a$10$ki4lLC.v.m4k1PhmabJXdudTNl9FYSvhnpCKbzIOtLM/JmOEmNgY2', 'mamadou', 'ETUD', '2003-06-28 00:00:00.000000', NULL, 'N01478ZEEZ', NULL),
(38, b'1', 'tes-mai@abc.com', 'test_nom', '$2a$10$gouPvKLTkJ5DnPk2xu3PFOo1LPlbO5uHRiJHVboGkXJUkfaxvZc2C', 'test_prenom', 'ETUD', '2015-03-29 00:00:00.000000', NULL, 'N0112596ZE', 'Informatique'),
(39, b'0', 'diop@exemple.com', 'diop', '$2a$10$LrIUuLkAAx/plVuGC6vy6uG7iz1y4TuUJMD1ksIL.90cT3uszwqt6', 'lamine', 'ETUD', '2001-05-24 00:00:00.000000', NULL, 'N0112596ZE', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `user_actions`
--

CREATE TABLE `user_actions` (
  `history_id` bigint(20) NOT NULL,
  `action` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `user_actions`
--

INSERT INTO `user_actions` (`history_id`, `action`) VALUES
(1, 'Archiver'),
(1, 'Restaurer'),
(1, 'Ajouter'),
(4, 'Modifier'),
(5, 'UPDATE'),
(5, 'UPDATE'),
(9, 'ARCHIVE'),
(9, 'UNARCHIVE'),
(9, 'CREATE'),
(9, 'UPDATE');

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
-- Index pour la table `history`
--
ALTER TABLE `history`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `user_actions`
--
ALTER TABLE `user_actions`
  ADD KEY `FKrv94fjya3cx3i8vbmb4iy7g9p` (`history_id`);

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
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `history`
--
ALTER TABLE `history`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;

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
