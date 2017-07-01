-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: biblioteca
-- ------------------------------------------------------
-- Server version	5.7.18-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `autores`
--

DROP TABLE IF EXISTS `autores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `autores` (
  `id_autor` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `data_nascimento` date DEFAULT NULL,
  `pais_origem` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`id_autor`)
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `autores`
--

LOCK TABLES `autores` WRITE;
/*!40000 ALTER TABLE `autores` DISABLE KEYS */;
INSERT INTO `autores` VALUES (57,'teste2','1998-02-02','Argentina'),(59,'Fernando Pessoa','1987-05-05','Portugal'),(60,'José de Alencar','1998-01-01','Brasil'),(61,'Maurício de Souza','1998-01-01','Brasil'),(63,'Érico Veríssimo','1980-02-03','Brasil'),(64,'Machado de Assis','1839-05-05','Brasil'),(65,'Carlos Drummond de Andrade','1902-06-06','Brasil'),(66,'Clarice Lispector','1920-07-07','Brasil'),(67,'Mário de Andrade','1893-08-08','Brasil'),(68,'Luís de Camões','1580-09-09','Portugal'),(69,'Eça de Queiroz','1845-10-10','Portugal'),(70,'William Shakespeare','1564-01-01','Inglaterra'),(71,'George Orwell','1903-02-02','Inglaterra');
/*!40000 ALTER TABLE `autores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `livros`
--

DROP TABLE IF EXISTS `livros`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `livros` (
  `id_livro` int(11) NOT NULL AUTO_INCREMENT,
  `titulo` varchar(100) NOT NULL,
  `id_autor` int(11) NOT NULL,
  `isbn` varchar(13) DEFAULT NULL,
  `editora` varchar(50) DEFAULT NULL,
  `qtde_estoque` int(11) NOT NULL,
  PRIMARY KEY (`id_livro`),
  KEY `id_autor` (`id_autor`),
  CONSTRAINT `livros_ibfk_1` FOREIGN KEY (`id_autor`) REFERENCES `autores` (`id_autor`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `livros`
--

LOCK TABLES `livros` WRITE;
/*!40000 ALTER TABLE `livros` DISABLE KEYS */;
INSERT INTO `livros` VALUES (12,'Revolução dos Bichos',71,'2141424546878','Editora Teste 2',88),(14,'Iracema',60,'1232343464565','teste',456),(15,'A menina que roubava livros',66,'2736478293472','teste',78),(16,'Livro 1',60,'2123423423423','Editora teste 3',64),(17,'Romeu e Julieta',70,'7926348236412','Editora XYZ',47);
/*!40000 ALTER TABLE `livros` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-06-25 21:50:42
