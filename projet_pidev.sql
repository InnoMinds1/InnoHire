-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : mer. 21 fév. 2024 à 13:58
-- Version du serveur : 10.4.24-MariaDB
-- Version de PHP : 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `projet_pidev`
--

-- --------------------------------------------------------

--
-- Structure de la table `admin`
--

CREATE TABLE `admin` (
  `id_admin` int(11) NOT NULL,
  `cin` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `mdp` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `admin`
--

INSERT INTO `admin` (`id_admin`, `cin`, `nom`, `prenom`, `email`, `mdp`) VALUES
(13, 12, 'Alen', 'sqdj', 'qsd', 'ef90d9c1ec76b1edc9edfaf2c0c05359c10ccc49ae8ecf7b7fd25ce9c02e86a4'),
(14, 122, 'amenallah', 'kthiri', '@', '9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08');

-- --------------------------------------------------------

--
-- Structure de la table `commentaire`
--

CREATE TABLE `commentaire` (
  `id_commentaire` int(11) NOT NULL,
  `id_publication` int(11) NOT NULL,
  `id_utilisateur` int(11) NOT NULL,
  `description_co` varchar(255) NOT NULL,
  `date_co` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `etablissement`
--

CREATE TABLE `etablissement` (
  `id_etablissement` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `lieu` varchar(255) NOT NULL,
  `code_etablissement` int(11) NOT NULL,
  `type_etablissement` varchar(255) NOT NULL,
  `id_utilisateur` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `logs`
--

CREATE TABLE `logs` (
  `id_log` int(11) NOT NULL,
  `id_utilisateur` int(11) NOT NULL,
  `id_etablissement` int(11) NOT NULL,
  `id_wallet` int(11) NOT NULL,
  `id_publication` int(11) NOT NULL,
  `id_commentaire` int(11) NOT NULL,
  `id_quiz` int(11) NOT NULL,
  `id_reclamation` int(11) NOT NULL,
  `id_message` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `messagerie`
--

CREATE TABLE `messagerie` (
  `id_message` int(11) NOT NULL,
  `date` datetime NOT NULL,
  `type` varchar(255) NOT NULL,
  `contenu` varchar(255) NOT NULL,
  `sender_id` int(11) NOT NULL,
  `reciver_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `publication`
--

CREATE TABLE `publication` (
  `id_publication` int(11) NOT NULL,
  `code_pub` varchar(255) NOT NULL,
  `id_utilisateur` int(11) NOT NULL,
  `description` varchar(255) NOT NULL,
  `hashtag` varchar(255) NOT NULL,
  `visibilite` varchar(255) NOT NULL,
  `image` varchar(255) NOT NULL,
  `date` date NOT NULL,
  `nb_report` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `publication`
--

INSERT INTO `publication` (`id_publication`, `code_pub`, `id_utilisateur`, `description`, `hashtag`, `visibilite`, `image`, `date`, `nb_report`) VALUES
(1, '', 22, 'H', 'L', 'H', 'G', '2024-02-18', 22);

-- --------------------------------------------------------

--
-- Structure de la table `question`
--

CREATE TABLE `question` (
  `id_question` int(11) NOT NULL,
  `question` varchar(255) NOT NULL,
  `choix` varchar(255) NOT NULL,
  `id_quiz` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `quiz`
--

CREATE TABLE `quiz` (
  `id_quiz` int(11) NOT NULL,
  `code_quiz` int(11) NOT NULL,
  `nom_quiz` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `prix_quiz` int(11) NOT NULL,
  `id_etablissement` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `reclamation`
--

CREATE TABLE `reclamation` (
  `id_reclamation` int(11) NOT NULL,
  `type` varchar(255) NOT NULL,
  `titre` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `date` datetime NOT NULL,
  `status` int(11) NOT NULL,
  `id_publication` int(11) NOT NULL,
  `id_utilisateur` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `reponse`
--

CREATE TABLE `reponse` (
  `id_reponse` int(11) NOT NULL,
  `choix_correcte` int(11) NOT NULL,
  `id_question` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `id_utilisateur` int(11) NOT NULL,
  `cin` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `adresse` varchar(255) NOT NULL,
  `mdp` varchar(255) NOT NULL,
  `role` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`id_utilisateur`, `cin`, `nom`, `prenom`, `adresse`, `mdp`, `role`) VALUES
(17, 233, 'AMENN', 'IKHDIM', 'YARABY@iii', '9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08', 2),
(19, 66, 'SD', 'AZ', '@', '24a04859593fb924a99c9de70afb8a09c46f5fe29ddd43afa3724e0dcc412106', 1),
(20, 88, 'tz', 'AZ', 'AZ', '495496f0156b1a6cf5cbebd2d575c02cc9c68884300c14a6a759bc4129d41057', 1),
(22, 333, 'AZ', 'AZEE', 'ze', '72b289ec78e0a928c565480a435453e30acb92eddb3b78ff168b28737cf6a849', 2),
(24, 4, 'AZE', 'aa', '@', '6b51d431df5d7f141cbececcf79edf3dd861c3b4069f0b11661a3eefacbba918', 2),
(26, 12, 'AZERTY', 'YARABY', '@@', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 0),
(27, 13, 'AZERTY', 'YARABY', '@@', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 0);

-- --------------------------------------------------------

--
-- Structure de la table `wallet`
--

CREATE TABLE `wallet` (
  `id_wallet` int(11) NOT NULL,
  `balance` int(11) NOT NULL,
  `id_etablissement` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id_admin`);

--
-- Index pour la table `commentaire`
--
ALTER TABLE `commentaire`
  ADD PRIMARY KEY (`id_commentaire`),
  ADD KEY `id_publication` (`id_publication`),
  ADD KEY `id_utilisateur` (`id_utilisateur`),
  ADD KEY `id_publication_2` (`id_publication`);

--
-- Index pour la table `etablissement`
--
ALTER TABLE `etablissement`
  ADD PRIMARY KEY (`id_etablissement`) USING BTREE,
  ADD UNIQUE KEY `code_etablissement` (`code_etablissement`),
  ADD KEY `id_utilisateur` (`id_utilisateur`);

--
-- Index pour la table `logs`
--
ALTER TABLE `logs`
  ADD PRIMARY KEY (`id_log`);

--
-- Index pour la table `messagerie`
--
ALTER TABLE `messagerie`
  ADD PRIMARY KEY (`id_message`),
  ADD KEY `sender_id` (`sender_id`),
  ADD KEY `reciver_id` (`reciver_id`);

--
-- Index pour la table `publication`
--
ALTER TABLE `publication`
  ADD PRIMARY KEY (`id_publication`),
  ADD UNIQUE KEY `code_pub` (`code_pub`),
  ADD KEY `id_utilisateur` (`id_utilisateur`);

--
-- Index pour la table `question`
--
ALTER TABLE `question`
  ADD PRIMARY KEY (`id_question`),
  ADD KEY `id_quiz` (`id_quiz`);

--
-- Index pour la table `quiz`
--
ALTER TABLE `quiz`
  ADD PRIMARY KEY (`id_quiz`),
  ADD UNIQUE KEY `code_quiz` (`code_quiz`),
  ADD KEY `id_etablissement` (`id_etablissement`);

--
-- Index pour la table `reclamation`
--
ALTER TABLE `reclamation`
  ADD PRIMARY KEY (`id_reclamation`),
  ADD KEY `id_publication` (`id_publication`),
  ADD KEY `id_utilisateur` (`id_utilisateur`);

--
-- Index pour la table `reponse`
--
ALTER TABLE `reponse`
  ADD PRIMARY KEY (`id_reponse`),
  ADD KEY `id_question` (`id_question`);

--
-- Index pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`id_utilisateur`) USING BTREE;

--
-- Index pour la table `wallet`
--
ALTER TABLE `wallet`
  ADD PRIMARY KEY (`id_wallet`),
  ADD KEY `id_etablissement` (`id_etablissement`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `admin`
--
ALTER TABLE `admin`
  MODIFY `id_admin` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT pour la table `commentaire`
--
ALTER TABLE `commentaire`
  MODIFY `id_commentaire` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `etablissement`
--
ALTER TABLE `etablissement`
  MODIFY `id_etablissement` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `logs`
--
ALTER TABLE `logs`
  MODIFY `id_log` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `messagerie`
--
ALTER TABLE `messagerie`
  MODIFY `id_message` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `publication`
--
ALTER TABLE `publication`
  MODIFY `id_publication` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `question`
--
ALTER TABLE `question`
  MODIFY `id_question` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `quiz`
--
ALTER TABLE `quiz`
  MODIFY `id_quiz` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `reclamation`
--
ALTER TABLE `reclamation`
  MODIFY `id_reclamation` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `reponse`
--
ALTER TABLE `reponse`
  MODIFY `id_reponse` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  MODIFY `id_utilisateur` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT pour la table `wallet`
--
ALTER TABLE `wallet`
  MODIFY `id_wallet` int(11) NOT NULL AUTO_INCREMENT;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `commentaire`
--
ALTER TABLE `commentaire`
  ADD CONSTRAINT `commentaire_ibfk_1` FOREIGN KEY (`id_publication`) REFERENCES `publication` (`id_publication`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `commentaire_ibfk_2` FOREIGN KEY (`id_utilisateur`) REFERENCES `utilisateur` (`id_utilisateur`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `etablissement`
--
ALTER TABLE `etablissement`
  ADD CONSTRAINT `etablissement_ibfk_1` FOREIGN KEY (`id_utilisateur`) REFERENCES `utilisateur` (`id_utilisateur`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `messagerie`
--
ALTER TABLE `messagerie`
  ADD CONSTRAINT `messagerie_ibfk_1` FOREIGN KEY (`sender_id`) REFERENCES `utilisateur` (`id_utilisateur`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `messagerie_ibfk_2` FOREIGN KEY (`reciver_id`) REFERENCES `utilisateur` (`id_utilisateur`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `publication`
--
ALTER TABLE `publication`
  ADD CONSTRAINT `publication_ibfk_1` FOREIGN KEY (`id_utilisateur`) REFERENCES `utilisateur` (`id_utilisateur`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `question`
--
ALTER TABLE `question`
  ADD CONSTRAINT `question_ibfk_1` FOREIGN KEY (`id_quiz`) REFERENCES `quiz` (`id_quiz`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `quiz`
--
ALTER TABLE `quiz`
  ADD CONSTRAINT `quiz_ibfk_1` FOREIGN KEY (`id_etablissement`) REFERENCES `etablissement` (`id_etablissement`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `reclamation`
--
ALTER TABLE `reclamation`
  ADD CONSTRAINT `reclamation_ibfk_2` FOREIGN KEY (`id_publication`) REFERENCES `publication` (`id_publication`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `reclamation_ibfk_3` FOREIGN KEY (`id_utilisateur`) REFERENCES `utilisateur` (`id_utilisateur`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `reponse`
--
ALTER TABLE `reponse`
  ADD CONSTRAINT `reponse_ibfk_1` FOREIGN KEY (`id_question`) REFERENCES `question` (`id_question`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `wallet`
--
ALTER TABLE `wallet`
  ADD CONSTRAINT `wallet_ibfk_1` FOREIGN KEY (`id_etablissement`) REFERENCES `etablissement` (`id_etablissement`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
