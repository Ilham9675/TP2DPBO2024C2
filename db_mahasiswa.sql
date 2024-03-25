-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 25, 2024 at 03:09 PM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_mahasiswa`
--

-- --------------------------------------------------------

--
-- Table structure for table `mahasiswa`
--

CREATE TABLE `mahasiswa` (
  `id` int(11) NOT NULL,
  `nim` varchar(255) NOT NULL,
  `nama` varchar(255) NOT NULL,
  `jenis_kelamin` varchar(255) NOT NULL,
  `kelas` varchar(255) DEFAULT NULL,
  `nilai` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `mahasiswa`
--

INSERT INTO `mahasiswa` (`id`, `nim`, `nama`, `jenis_kelamin`, `kelas`, `nilai`) VALUES
(1, '2203999', 'Amelia Zalfa Julianti', 'Perempuan', 'C1', 'A'),
(2, '2202292', 'Muhammad Iqbal Fadhilah', 'Laki-laki', 'C2', 'A\r\n'),
(3, '2202346', 'Muhammad Rifky Afandi', 'Laki-laki', 'C2', 'A'),
(4, '2210239', 'Muhammad Hanif Abdillah', 'Laki-laki', 'C1', 'A'),
(5, '2202046', 'Nurainun', 'Perempuan', 'C1', 'A'),
(6, '2205101', 'Kelvin Julian Putra', 'Laki-laki', 'C2', 'A'),
(7, '2200163', 'Rifanny Lysara Annastasya', 'Perempuan', 'C2', 'A'),
(8, '2202869', 'Revana Faliha Salma', 'Perempuan', 'C2', 'A'),
(9, '2209489', 'Rakha Dhifiargo Hariadi', 'Laki-laki', 'C1', 'A'),
(10, '2203142', 'Roshan Syalwan Nurilham', 'Laki-laki', 'C2', 'A'),
(11, '2200311', 'Raden Rahman Ismail', 'Laki-laki', 'C2', 'A'),
(12, '2200978', 'Ratu Syahirah Khairunnisa', 'Perempuan', 'C2', 'A'),
(13, '2204509', 'Muhammad Fahreza Fauzan', 'Laki-laki', 'C1', 'A'),
(14, '2205027', 'Muhammad Rizki Revandi', 'Laki-laki', 'C1', 'A'),
(15, '2203484', 'Arya Aydin Margono', 'Laki-laki', 'C1', 'A'),
(16, '2200481', 'Marvel Ravindra Dioputra', 'Laki-laki', 'C2', 'A'),
(17, '2209889', 'Muhammad Fadlul Hafiizh', 'Laki-laki', 'C2', 'A'),
(18, '2206697', 'Rifa Sania', 'Perempuan', 'C1', 'A'),
(19, '2207260', 'Imam Chalish Rafidhul Haque', 'Laki-laki', 'C1', 'A'),
(29, '2201017', 'ilham akbar', 'Laki-laki', 'C2', 'A'),
(30, '2201018', 'akbar', 'Laki-laki', 'C2', 'B');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `mahasiswa`
--
ALTER TABLE `mahasiswa`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `mahasiswa`
--
ALTER TABLE `mahasiswa`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
