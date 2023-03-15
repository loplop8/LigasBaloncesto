-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 01-03-2023 a las 01:37:18
-- Versión del servidor: 10.4.27-MariaDB
-- Versión de PHP: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `ligabaloncesto`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `equipo`
--

CREATE TABLE `equipo` (
  `id` bigint(11) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `id_liga` bigint(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `equipo`
--

INSERT INTO `equipo` (`id`, `nombre`, `id_liga`) VALUES
(27, 'Betis', 17),
(29, 'Sevilla', 17),
(30, 'Madrid', 17),
(31, 'Barcelona', 17);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `jornada`
--

CREATE TABLE `jornada` (
  `id` bigint(11) NOT NULL,
  `numero` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `id_liga` bigint(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `jornada`
--

INSERT INTO `jornada` (`id`, `numero`, `fecha`, `id_liga`) VALUES
(639, 1, '2023-03-01', 17),
(640, 2, '2023-03-08', 17),
(641, 3, '2023-03-15', 17),
(642, 4, '2023-03-22', 17),
(643, 5, '2023-03-29', 17),
(644, 6, '2023-04-05', 17);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `liga`
--

CREATE TABLE `liga` (
  `id` bigint(11) NOT NULL,
  `nombre` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `liga`
--

INSERT INTO `liga` (`id`, `nombre`) VALUES
(17, 'Prueba');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `partido`
--

CREATE TABLE `partido` (
  `id` bigint(11) NOT NULL,
  `id_equipoLocal` bigint(11) NOT NULL,
  `id_equipoVisitante` bigint(11) NOT NULL,
  `puntos_equipoLocal` int(11) NOT NULL,
  `puntos_equipoVisitante` int(11) NOT NULL,
  `id_jornada` bigint(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `partido`
--

INSERT INTO `partido` (`id`, `id_equipoLocal`, `id_equipoVisitante`, `puntos_equipoLocal`, `puntos_equipoVisitante`, `id_jornada`) VALUES
(1471, 27, 31, 83, 104, 639),
(1472, 31, 27, 0, 0, 644),
(1473, 29, 30, 0, 102, 639),
(1474, 30, 29, 0, 0, 644),
(1475, 27, 30, 12, 33, 640),
(1476, 30, 27, 0, 0, 643),
(1477, 31, 29, 31, 48, 640),
(1478, 29, 31, 0, 0, 643),
(1479, 27, 29, 112, 20, 641),
(1480, 29, 27, 0, 0, 642),
(1481, 30, 31, 105, 104, 641),
(1482, 31, 30, 0, 0, 642);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id` bigint(11) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `rol` varchar(255) NOT NULL,
  `nickname` varchar(255) NOT NULL,
  `pass` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id`, `nombre`, `rol`, `nickname`, `pass`) VALUES
(5, 'Arbitro 1', 'arbitro', 'arb1', 'arb1'),
(8, 'admin', 'admin', 'admin', 'admin');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `equipo`
--
ALTER TABLE `equipo`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nombre` (`nombre`),
  ADD KEY `id_liga` (`id_liga`);

--
-- Indices de la tabla `jornada`
--
ALTER TABLE `jornada`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_liga` (`id_liga`);

--
-- Indices de la tabla `liga`
--
ALTER TABLE `liga`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `partido`
--
ALTER TABLE `partido`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_equipoLocal` (`id_equipoLocal`),
  ADD KEY `id_equipoVisitante` (`id_equipoVisitante`),
  ADD KEY `id_jornada` (`id_jornada`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nickname` (`nickname`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `equipo`
--
ALTER TABLE `equipo`
  MODIFY `id` bigint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- AUTO_INCREMENT de la tabla `jornada`
--
ALTER TABLE `jornada`
  MODIFY `id` bigint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=645;

--
-- AUTO_INCREMENT de la tabla `liga`
--
ALTER TABLE `liga`
  MODIFY `id` bigint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT de la tabla `partido`
--
ALTER TABLE `partido`
  MODIFY `id` bigint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1483;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` bigint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `equipo`
--
ALTER TABLE `equipo`
  ADD CONSTRAINT `equipo_ibfk_1` FOREIGN KEY (`id_liga`) REFERENCES `liga` (`id`);

--
-- Filtros para la tabla `jornada`
--
ALTER TABLE `jornada`
  ADD CONSTRAINT `jornada_ibfk_1` FOREIGN KEY (`id_liga`) REFERENCES `liga` (`id`);

--
-- Filtros para la tabla `partido`
--
ALTER TABLE `partido`
  ADD CONSTRAINT `partido_ibfk_1` FOREIGN KEY (`id_equipoLocal`) REFERENCES `equipo` (`id`),
  ADD CONSTRAINT `partido_ibfk_2` FOREIGN KEY (`id_equipoVisitante`) REFERENCES `equipo` (`id`),
  ADD CONSTRAINT `partido_ibfk_3` FOREIGN KEY (`id_jornada`) REFERENCES `jornada` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
