-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Mar 04, 2018 at 01:56 PM
-- Server version: 5.7.19
-- PHP Version: 5.6.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sp_friend`
--

-- --------------------------------------------------------

--
-- Table structure for table `sp_friend`
--

DROP TABLE IF EXISTS `sp_friend`;
CREATE TABLE IF NOT EXISTS `sp_friend` (
  `usera_id` int(11) NOT NULL,
  `userb_id` int(11) NOT NULL,
  PRIMARY KEY (`usera_id`,`userb_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sp_friend`
--

INSERT INTO `sp_friend` (`usera_id`, `userb_id`) VALUES
(1, 2),
(1, 3),
(2, 3);

-- --------------------------------------------------------

--
-- Table structure for table `sp_interaction`
--

DROP TABLE IF EXISTS `sp_interaction`;
CREATE TABLE IF NOT EXISTS `sp_interaction` (
  `usera_id` int(11) NOT NULL,
  `userb_id` int(11) NOT NULL,
  `relationship` int(11) NOT NULL COMMENT '0:non;1:only subscribing;2:only block;',
  PRIMARY KEY (`usera_id`,`userb_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sp_interaction`
--

INSERT INTO `sp_interaction` (`usera_id`, `userb_id`, `relationship`) VALUES
(1, 2, 2),
(2, 1, 2);

-- --------------------------------------------------------

--
-- Table structure for table `sp_user`
--

DROP TABLE IF EXISTS `sp_user`;
CREATE TABLE IF NOT EXISTS `sp_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sp_user`
--

INSERT INTO `sp_user` (`id`, `email`) VALUES
(1, 'a@gmail.com'),
(2, 'b@gmail.com'),
(3, 'c@gmail.com');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
