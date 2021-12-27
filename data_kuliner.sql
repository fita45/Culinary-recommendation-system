-- phpMyAdmin SQL Dump
-- version 4.0.4.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 15, 2015 at 05:25 AM
-- Server version: 5.5.32
-- PHP Version: 5.4.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `data_kuliner`
--
CREATE DATABASE IF NOT EXISTS `data_kuliner` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `data_kuliner`;

-- --------------------------------------------------------

--
-- Table structure for table `bobot_alternatif`
--

CREATE TABLE IF NOT EXISTS `bobot_alternatif` (
  `indeks` int(11) NOT NULL AUTO_INCREMENT,
  `nama` text NOT NULL,
  `harga` int(11) NOT NULL,
  `fasilitas` int(11) NOT NULL,
  `jarak` int(11) NOT NULL,
  `suasana` int(11) NOT NULL,
  PRIMARY KEY (`indeks`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=51 ;

--
-- Dumping data for table `bobot_alternatif`
--

INSERT INTO `bobot_alternatif` (`indeks`, `nama`, `harga`, `fasilitas`, `jarak`, `suasana`) VALUES
(1, 'AYAM BAKAR WONG SOLO', 4, 4, 4, 7),
(2, 'AYAM GORENG ABC', 2, 2, 2, 3),
(3, 'AYAM GORENG GAMA', 2, 2, 2, 3),
(4, 'AYAM GORENG PEMUDA', 6, 6, 4, 10),
(5, 'AYAM GORENG REMAJA', 2, 2, 6, 7),
(6, 'AYAM LODO', 6, 4, 2, 7),
(7, 'BAKSO BAKAR TROWULAN', 4, 2, 8, 3),
(8, 'BAKSO KIKIL', 4, 2, 2, 3),
(9, 'BAKSO KOTA', 4, 2, 4, 7),
(10, 'CAFE 18', 4, 4, 2, 7),
(11, 'CAFE KOPMA UNIBRAW', 2, 2, 2, 3),
(12, 'CFC KOMP. MATOS', 2, 2, 2, 3),
(13, 'DEPOT LISA', 4, 2, 6, 3),
(14, 'DEPOT MAHARANI', 4, 2, 6, 3),
(15, 'DEPOT MANINJAU', 4, 2, 6, 7),
(16, 'DEPOT MIE 63', 6, 4, 8, 3),
(17, 'DEPOT MINIE', 4, 4, 2, 7),
(18, 'DEPOT POJOK/SURADI', 4, 2, 2, 3),
(19, 'DEPOT RAWON PINCUK', 2, 2, 4, 3),
(20, 'DEPOT SEDAP RASA', 2, 4, 2, 3),
(21, 'DEPOT SELERA', 4, 4, 8, 3),
(22, 'DEPOT SOTO KAMBING NGELO', 6, 2, 10, 7),
(23, 'DEPOT SOTO LAMONGAN', 6, 2, 10, 7),
(24, 'DIVA RESTO LIVE', 8, 6, 2, 10),
(25, 'DUNDEE FRED CHECKEN', 6, 4, 4, 7),
(26, 'DUNKIN DONUTS', 8, 6, 8, 10),
(27, 'KAFE DELTA 5', 6, 6, 10, 7),
(28, 'KDS CANTONESE RESTAURANT', 6, 4, 10, 7),
(29, 'KFC ', 8, 6, 6, 10),
(30, 'LESUNG CAFE SHOP', 6, 4, 6, 7),
(31, 'MC. DONALD', 8, 6, 6, 10),
(32, 'MINANG AGUNG', 6, 4, 6, 3),
(33, 'MR. BASO MATOS', 6, 4, 2, 3),
(34, 'PECEL LELE Gg. MOJO', 2, 2, 4, 3),
(35, 'PECEL MADIUN', 4, 2, 4, 3),
(36, 'PIZZA BAKSO', 8, 4, 4, 7),
(37, 'PIZZA HUT', 10, 6, 6, 10),
(38, 'RESTORAN AZARIA', 8, 6, 10, 7),
(39, 'RESTORAN LITTLE TASTE CHATINGAND ENJOYING', 6, 4, 6, 7),
(40, 'RESTORAN PIZZA HUT MATOS', 10, 6, 2, 10),
(41, 'SOTO AYAM SURABAYA', 4, 4, 10, 7),
(42, 'SOTO BANJAR', 4, 4, 6, 7),
(43, 'SUMBER GIZI', 4, 2, 8, 3),
(44, 'TENDA BAKSO D500', 4, 2, 6, 3),
(45, 'TONG SENG ASLI SOLO', 4, 4, 10, 7),
(46, 'WARUNG BU GITO', 2, 2, 6, 3),
(47, 'WARUNG NASI SUDIMORO BAROKAH', 2, 2, 8, 3),
(48, 'WARUNG NYATA RASA', 2, 2, 2, 3),
(49, 'WARUNG SANTAI', 2, 2, 10, 3),
(50, 'WENDY''S', 10, 4, 6, 10);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
