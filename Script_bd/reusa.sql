-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 12-04-2025 a las 18:04:56
-- Versión del servidor: 10.6.17-MariaDB
-- Versión de PHP: 8.0.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `reusa`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categorias`
--

CREATE TABLE `categorias` (
  `id` int(11) NOT NULL,
  `nombre` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Volcado de datos para la tabla `categorias`
--

INSERT INTO `categorias` (`id`, `nombre`) VALUES
(1, 'Electrónica'),
(2, 'Hogar y Jardín'),
(3, 'Moda y Accesorios'),
(4, 'Deportes y Ocio'),
(5, 'Vehículos y Accesorios'),
(6, 'Inmobiliaria');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `comunidad_autonoma`
--

CREATE TABLE `comunidad_autonoma` (
  `id` int(11) NOT NULL,
  `nombre` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Volcado de datos para la tabla `comunidad_autonoma`
--

INSERT INTO `comunidad_autonoma` (`id`, `nombre`) VALUES
(1, 'Comunidad de Madrid'),
(2, 'Galicia'),
(3, 'La Rioja'),
(4, 'Andalucía'),
(5, 'Aragón'),
(6, 'Asturias'),
(7, 'Islas Baleares'),
(8, 'Islas Canarias'),
(9, 'Cantabria'),
(10, 'Castilla y León'),
(11, 'Castilla La-Mancha'),
(12, 'Cataluña'),
(13, 'Comunidad Valenciana'),
(14, 'Extremadura'),
(15, 'Murcia'),
(16, 'Navarra'),
(17, 'País Vasco');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `contraoferta`
--

CREATE TABLE `contraoferta` (
  `id` int(11) NOT NULL,
  `id_usuario_comprador` int(11) NOT NULL,
  `id_producto` int(11) NOT NULL,
  `precio` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `favoritos`
--

CREATE TABLE `favoritos` (
  `id` int(11) NOT NULL,
  `id_usuario_comprador` int(11) NOT NULL,
  `id_producto` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE `productos` (
  `id` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `id_subcategoria` int(11) NOT NULL,
  `precio` decimal(10,2) NOT NULL,
  `estado` enum('nuevo','seminuevo','aceptable','deteriorado') NOT NULL,
  `descripcion` text NOT NULL,
  `fecha_publicacion` date NOT NULL,
  `fecha_inactivo` date NOT NULL,
  `imagen_1` varchar(250) NOT NULL,
  `imagen_2` varchar(250) NOT NULL,
  `imagen_3` varchar(250) NOT NULL,
  `imagen_4` varchar(250) NOT NULL,
  `vendido` enum('vendido','activo') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `provincia`
--

CREATE TABLE `provincia` (
  `id` int(11) NOT NULL,
  `id_comunidad_autonoma` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Volcado de datos para la tabla `provincia`
--

INSERT INTO `provincia` (`id`, `id_comunidad_autonoma`, `nombre`) VALUES
(1, 1, 'Madrid'),
(2, 2, 'La Coruña'),
(3, 2, 'Lugo'),
(4, 2, 'Orense'),
(5, 2, 'Pontevedra'),
(6, 3, 'Logroño'),
(7, 4, 'Almería'),
(8, 4, 'Cádiz'),
(9, 4, 'Córdoba'),
(10, 4, 'Granada'),
(11, 4, 'Huelva'),
(12, 4, 'Jaén'),
(13, 4, 'Málaga'),
(14, 4, 'Sevilla'),
(15, 5, 'Huesca'),
(16, 5, 'Teruel'),
(17, 5, 'Zaragoza'),
(18, 6, 'Oviedo'),
(19, 7, 'Mallorca'),
(20, 7, 'Menorca'),
(21, 7, 'Formentera'),
(22, 8, 'Tenerife'),
(23, 8, 'Gran Canaria'),
(24, 8, 'Lanzarote'),
(25, 8, 'Fuerteventura'),
(26, 8, 'La Palma'),
(27, 8, 'La Gomera'),
(28, 8, 'El Hierro'),
(29, 9, 'Santander'),
(30, 10, 'Ávila'),
(31, 10, 'Burgos'),
(32, 10, 'León'),
(33, 10, 'Salamanca'),
(34, 10, 'Segovia'),
(35, 10, 'Soria'),
(36, 10, 'Valladolid'),
(37, 10, 'Zamora'),
(38, 11, 'Albacete'),
(39, 11, 'Ciudad Real'),
(40, 11, 'Cuenca'),
(41, 11, 'Guadalajara'),
(42, 11, 'Toledo'),
(43, 12, 'Barcelona'),
(44, 12, 'Gerona'),
(45, 12, 'Lérida'),
(46, 12, 'Tarragona'),
(47, 13, 'Alicante'),
(48, 13, 'Valencia'),
(49, 13, 'Castellón de la Plana'),
(50, 14, 'Cáceres'),
(51, 14, 'Badajoz'),
(52, 15, 'Murcia'),
(53, 16, 'Pamplona'),
(54, 17, 'Bilbao'),
(55, 17, 'San Sebastián'),
(56, 17, 'Vitoria');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `subcategorias`
--

CREATE TABLE `subcategorias` (
  `id` int(11) NOT NULL,
  `id_categoria` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Volcado de datos para la tabla `subcategorias`
--

INSERT INTO `subcategorias` (`id`, `id_categoria`, `nombre`) VALUES
(1, 1, 'Teléfonos móviles y accesorios'),
(2, 1, 'Ordenadores y portátiles'),
(3, 1, 'Televisores y equipos de sonido'),
(4, 1, 'Cámaras y fotografía'),
(5, 1, 'Videojuegos y consolas'),
(6, 2, 'Muebles'),
(7, 2, 'Iluminación'),
(8, 2, 'Jardinería y exteriores'),
(9, 2, 'Herramientas y bricolaje'),
(10, 2, 'Menaje de cocina'),
(11, 3, 'Ropa Hombre'),
(12, 3, 'Ropa Mujer'),
(13, 3, 'Ropa Niño'),
(14, 3, 'Calzado'),
(15, 3, 'Bolsos y Complementos'),
(16, 3, 'Joyas y Relojes'),
(17, 3, 'Cosmética y Belleza'),
(18, 4, 'Equipamiento Deportivo'),
(19, 4, 'Bicicletas y Patinetes'),
(20, 4, 'Gimnasio y Fitness'),
(21, 4, 'Libros'),
(22, 4, 'Juguetes y juegos'),
(23, 4, 'Películas'),
(24, 4, 'Música'),
(25, 5, 'Coches'),
(26, 5, 'Motos'),
(27, 5, 'Piezas y accesorios'),
(28, 5, 'Neumáticos y llantas'),
(29, 5, 'Audio y electrónica'),
(30, 6, 'Pisos'),
(31, 6, 'Chalets'),
(32, 6, ' Áticos'),
(33, 6, 'Garajes');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL,
  `nickname` varchar(25) NOT NULL,
  `nombre` varchar(25) NOT NULL,
  `apellido` varchar(25) NOT NULL,
  `imagen_perfil` varchar(255) NOT NULL,
  `id_provincia` int(11) NOT NULL,
  `telefono` int(9) NOT NULL,
  `email` varchar(50) NOT NULL,
  `valoracion` float NOT NULL,
  `n_ventas` int(11) NOT NULL,
  `n_compras` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `venta`
--

CREATE TABLE `venta` (
  `id` int(11) NOT NULL,
  `id_usuario_comprador` int(11) NOT NULL,
  `id_usuario_vendedor` int(11) NOT NULL,
  `id_producto` int(11) NOT NULL,
  `fecha_venta` date NOT NULL,
  `precio` int(11) NOT NULL,
  `valoracion_comprador` int(11) NOT NULL,
  `valoracion_vendedor` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `categorias`
--
ALTER TABLE `categorias`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `comunidad_autonoma`
--
ALTER TABLE `comunidad_autonoma`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `contraoferta`
--
ALTER TABLE `contraoferta`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_usuario_comprador` (`id_usuario_comprador`),
  ADD KEY `id_producto` (`id_producto`);

--
-- Indices de la tabla `favoritos`
--
ALTER TABLE `favoritos`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_producto` (`id_producto`),
  ADD KEY `id_usuario` (`id_usuario_comprador`);

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_usuario` (`id_usuario`),
  ADD KEY `id_subcategoria` (`id_subcategoria`);

--
-- Indices de la tabla `provincia`
--
ALTER TABLE `provincia`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_comunidad_autonoma` (`id_comunidad_autonoma`);

--
-- Indices de la tabla `subcategorias`
--
ALTER TABLE `subcategorias`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_categoria` (`id_categoria`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nickname` (`nickname`),
  ADD UNIQUE KEY `telefono` (`telefono`),
  ADD UNIQUE KEY `email` (`email`),
  ADD KEY `id_provincia` (`id_provincia`);

--
-- Indices de la tabla `venta`
--
ALTER TABLE `venta`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_usuario_comprador` (`id_usuario_comprador`),
  ADD KEY `id_usuario_vendedor` (`id_usuario_vendedor`),
  ADD KEY `id_producto` (`id_producto`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `categorias`
--
ALTER TABLE `categorias`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `comunidad_autonoma`
--
ALTER TABLE `comunidad_autonoma`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT de la tabla `contraoferta`
--
ALTER TABLE `contraoferta`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `favoritos`
--
ALTER TABLE `favoritos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `productos`
--
ALTER TABLE `productos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `provincia`
--
ALTER TABLE `provincia`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=57;

--
-- AUTO_INCREMENT de la tabla `subcategorias`
--
ALTER TABLE `subcategorias`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `venta`
--
ALTER TABLE `venta`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `contraoferta`
--
ALTER TABLE `contraoferta`
  ADD CONSTRAINT `contraoferta_ibfk_1` FOREIGN KEY (`id_usuario_comprador`) REFERENCES `usuarios` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `contraoferta_ibfk_2` FOREIGN KEY (`id_producto`) REFERENCES `productos` (`id`) ON DELETE CASCADE;

--
-- Filtros para la tabla `favoritos`
--
ALTER TABLE `favoritos`
  ADD CONSTRAINT `favoritos_ibfk_1` FOREIGN KEY (`id_usuario_comprador`) REFERENCES `usuarios` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `favoritos_ibfk_2` FOREIGN KEY (`id_producto`) REFERENCES `productos` (`id`) ON DELETE CASCADE;

--
-- Filtros para la tabla `productos`
--
ALTER TABLE `productos`
  ADD CONSTRAINT `producto_de` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `productos_ibfk_1` FOREIGN KEY (`id_subcategoria`) REFERENCES `subcategorias` (`id`) ON DELETE CASCADE;

--
-- Filtros para la tabla `provincia`
--
ALTER TABLE `provincia`
  ADD CONSTRAINT `provincia_ibfk_1` FOREIGN KEY (`id_comunidad_autonoma`) REFERENCES `comunidad_autonoma` (`id`) ON DELETE CASCADE;

--
-- Filtros para la tabla `subcategorias`
--
ALTER TABLE `subcategorias`
  ADD CONSTRAINT `subcategorias_ibfk_2` FOREIGN KEY (`id_categoria`) REFERENCES `categorias` (`id`) ON DELETE CASCADE;

--
-- Filtros para la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD CONSTRAINT `usuarios_ibfk_1` FOREIGN KEY (`id_provincia`) REFERENCES `provincia` (`id`) ON DELETE CASCADE;

--
-- Filtros para la tabla `venta`
--
ALTER TABLE `venta`
  ADD CONSTRAINT `venta_ibfk_1` FOREIGN KEY (`id_usuario_comprador`) REFERENCES `usuarios` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `venta_ibfk_2` FOREIGN KEY (`id_usuario_vendedor`) REFERENCES `usuarios` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `venta_ibfk_3` FOREIGN KEY (`id_producto`) REFERENCES `productos` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
