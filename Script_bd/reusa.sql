-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 22-05-2025 a las 19:58:54
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
  `id_usuario_vendedor` int(11) NOT NULL,
  `id_producto` int(11) NOT NULL,
  `precio` decimal(10,2) NOT NULL,
  `expired` tinyint(1) NOT NULL,
  `tipo` enum('vendedor','comprador','','') NOT NULL,
  `estado` enum('rechazada','aceptada','en curso','') NOT NULL
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

--
-- Volcado de datos para la tabla `favoritos`
--

INSERT INTO `favoritos` (`id`, `id_usuario_comprador`, `id_producto`) VALUES
(2, 1, 7),
(3, 2, 8),
(8, 2, 5),
(9, 1, 5),
(10, 1, 12);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE `productos` (
  `id` int(11) NOT NULL,
  `nombre` varchar(200) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `id_subcategoria` int(11) NOT NULL,
  `precio` decimal(10,2) NOT NULL,
  `estado` enum('nuevo','seminuevo','aceptable','deteriorado') NOT NULL,
  `descripcion` text NOT NULL,
  `fecha_publicacion` date NOT NULL,
  `imagen_1` varchar(250) NOT NULL,
  `imagen_2` varchar(250) NOT NULL,
  `imagen_3` varchar(250) NOT NULL,
  `imagen_4` varchar(250) NOT NULL,
  `etapa` enum('vendido','activo') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`id`, `nombre`, `id_usuario`, `id_subcategoria`, `precio`, `estado`, `descripcion`, `fecha_publicacion`, `imagen_1`, `imagen_2`, `imagen_3`, `imagen_4`, `etapa`) VALUES
(1, 'Piso', 1, 30, 95000.00, 'nuevo', 'Casa espaciosa con jardín en zona tranquila.', '2024-07-15', '', '', '', '', 'activo'),
(2, 'Telefono', 2, 1, 499.99, 'seminuevo', 'Smartphone de última generación, poco uso.', '2024-08-01', '', '', '', '', 'activo'),
(3, 'Camiseta Hombre', 1, 11, 29.95, 'nuevo', 'Camiseta de algodón orgánico, varios colores.', '2024-08-10', '', '', '', '', 'activo'),
(4, 'Mesa', 2, 6, 75.50, 'aceptable', 'Mesa de centro de madera con signos de uso.', '2024-07-28', '', '', '', '', 'activo'),
(5, 'Balón fútbol', 1, 18, 10.00, 'nuevo', 'balon', '2024-08-05', '', '', '', '', 'activo'),
(6, 'Martillo', 2, 9, 15.75, 'seminuevo', 'Martillo precioso', '2024-07-20', '', '', '', '', 'activo'),
(7, 'Iphone', 1, 1, 340.00, 'nuevo', 'Iphone 13', '2024-08-12', '', '', '', '', 'activo'),
(8, 'BMW E87', 2, 25, 6689.90, 'aceptable', 'Coche negro', '2024-07-01', '', '', '', '', 'activo'),
(9, 'Bolso Gucci', 1, 15, 45.00, 'seminuevo', 'Bolso muy bonito', '2024-08-18', '', '', '', '', 'activo'),
(10, 'Lámpara vintage', 2, 7, 35.20, 'deteriorado', 'Lámpara de pie con la pantalla rota.', '2024-07-05', '', '', '', '', 'activo'),
(11, 'Mancuernas', 1, 20, 55.99, 'nuevo', '20kg', '2024-08-22', '', '', '', '', 'activo'),
(12, 'Calzonzillos', 2, 11, 22.50, 'seminuevo', 'talla M', '2024-07-10', '', '', '', '', 'vendido'),
(13, 'Portatil hp', 1, 2, 220.00, 'nuevo', 'Portatil', '2024-08-25', '', '', '', '', 'activo'),
(14, 'Bicicleta azul niño', 2, 19, 199.00, 'seminuevo', 'Bici niño', '2024-07-18', '', '', '', '', 'activo'),
(15, 'Conjunto jardín', 1, 8, 19.99, 'nuevo', 'Para 5 personas', '2024-08-30', '', '', '', '', 'activo');

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
-- Estructura de tabla para la tabla `token`
--

CREATE TABLE `token` (
  `id` int(11) NOT NULL,
  `token` varchar(400) NOT NULL,
  `token_type` enum('BEARER') NOT NULL,
  `revoked` tinyint(1) NOT NULL,
  `expired` tinyint(1) NOT NULL,
  `date_expired` datetime NOT NULL,
  `id_usuario` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Volcado de datos para la tabla `token`
--

INSERT INTO `token` (`id`, `token`, `token_type`, `revoked`, `expired`, `date_expired`, `id_usuario`) VALUES
(24, 'eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiI3Iiwibm9tYnJlIjoiU2FuZHJhIiwic3ViIjoiYWRtaW4zQGVtYWlsLmNvbSIsImlhdCI6MTc0NjM4MzQwNCwiZXhwIjoxNzQ2OTg4MjA0fQ.neRKhHJBQUz9EnC_PWvRTSMHZotPIQNXEQcdWVtKzgecDrYASEUxV01NmBKUXJkjkO7g8le-OskdV03biYdPow', 'BEARER', 1, 1, '2025-05-11 18:30:04', 7),
(25, 'eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiI3Iiwibm9tYnJlIjoiU2FuZHJhIiwic3ViIjoiYWRtaW4zQGVtYWlsLmNvbSIsImlhdCI6MTc0NjM4Mzk2NywiZXhwIjoxNzQ2OTg4NzY3fQ.ust2LPbRax4g_ahcMKKeoXkRBGMam0W3HnpC7YgUdJ_6Dnm7jE3SBoW34wVNUVx6NEqMgZNQ7wINB1xAa5cPbA', 'BEARER', 1, 1, '2025-05-11 18:39:27', 7),
(26, 'eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiI3Iiwibm9tYnJlIjoiU2FuZHJhIiwic3ViIjoiYWRtaW4zQGVtYWlsLmNvbSIsImlhdCI6MTc0NzMzMDgyNywiZXhwIjoxNzQ3OTM1NjI3fQ.g59Eh6qr0JPpzcXnwpzei7RlHUa7CvMqMDQVpKS3M1rCyYdzuOoCJi7YU8CsL-wLmdbOp-b4RkJdxOVGX5l8ig', 'BEARER', 1, 1, '2025-05-22 17:40:27', 7),
(27, 'eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiI3Iiwibm9tYnJlIjoiU2FuZHJhIiwic3ViIjoiYWRtaW4zQGVtYWlsLmNvbSIsImlhdCI6MTc0Nzg1MjEwMiwiZXhwIjoxNzQ4NDU2OTAyfQ.AyHk5fxPS_tHMt_6eXzbDiCig-ZYbD2JD3AHqLqyaKVV8XF3E8PykQ6Mk9xEUZacnmvQdc7b8lFG2qhT6kS_MQ', 'BEARER', 1, 1, '2025-05-28 18:28:22', 7),
(28, 'eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiI3Iiwibm9tYnJlIjoiU2FuZHJhIiwic3ViIjoiYWRtaW4zQGVtYWlsLmNvbSIsImlhdCI6MTc0Nzg1MjIxNSwiZXhwIjoxNzQ4NDU3MDE1fQ.S_pShMGMJpptX2XKEFWw-xc_i7DU2lZ6RViJGV5_NafMWuDx4XQRthf-jY26JxHq829cNSruCrUzr_D3lUo8hQ', 'BEARER', 1, 1, '2025-05-28 18:30:15', 7),
(29, 'eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiI3Iiwibm9tYnJlIjoiU2FuZHJhIiwic3ViIjoiYWRtaW4zQGVtYWlsLmNvbSIsImlhdCI6MTc0Nzg1MjI4NywiZXhwIjoxNzQ4NDU3MDg3fQ.Nhm4PKF5YwT1ejSdVwkELi2ZfqlAzc33D2uqaZHMRq5bh_USnX0fgucMiLEzmCkUP92E77dBOq1X-tebHPL0tQ', 'BEARER', 1, 1, '2025-05-28 18:31:27', 7),
(32, 'eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiI3Iiwibm9tYnJlIjoiU2FuZHJhIiwic3ViIjoiYWRtaW4zQGVtYWlsLmNvbSIsImlhdCI6MTc0Nzg1NDEzNSwiZXhwIjoxNzQ4NDU4OTM1fQ.vbBLnYJE1ag9hP6DI4e15XIqEjyVam5-dY0pJcvdr7iKU9rtQwkwZl3nJXkVoYEX1iFQN8ZJOcoZCdEninprng', 'BEARER', 1, 1, '2025-05-28 19:02:15', 7),
(36, 'eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiI3Iiwibm9tYnJlIjoiU2FuZHJhIiwic3ViIjoiYWRtaW4zQGVtYWlsLmNvbSIsImlhdCI6MTc0Nzg1Nzc5MCwiZXhwIjoxNzQ4NDYyNTkwfQ.wtSONvvlpTTyYCZL-oOuZsIaaI9NdvSrA1VJdUrbgyVdWO5uJtPoxMMlSW4jRnYwryBvQjM9drd8msx7XlGYWA', 'BEARER', 1, 1, '2025-05-28 20:03:10', 7),
(37, 'eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiI3Iiwibm9tYnJlIjoiU2FuZHJhIiwic3ViIjoiYWRtaW4zQGVtYWlsLmNvbSIsImlhdCI6MTc0Nzg1Nzk4OSwiZXhwIjoxNzQ4NDYyNzg5fQ.cW0ei_mRIuTjqavzxtUr2fsuctKa0lpTGpywzXHqWijXLtDJYKHsT9QAEJXQB7NZvBdTT6Iozpd9S5VR4kLTgA', 'BEARER', 1, 1, '2025-05-28 20:06:29', 7);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL,
  `nickname` varchar(25) NOT NULL,
  `password` varchar(200) NOT NULL,
  `nombre` varchar(25) NOT NULL,
  `apellido` varchar(25) NOT NULL,
  `imagen_perfil` varchar(255) DEFAULT NULL,
  `id_provincia` int(11) DEFAULT NULL,
  `telefono` int(9) NOT NULL,
  `email` varchar(50) NOT NULL,
  `valoracion` float DEFAULT NULL,
  `n_ventas` int(11) DEFAULT NULL,
  `n_compras` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id`, `nickname`, `password`, `nombre`, `apellido`, `imagen_perfil`, `id_provincia`, `telefono`, `email`, `valoracion`, `n_ventas`, `n_compras`) VALUES
(1, 'Admin', '$2a$10$tOLRgcG8zDPtkzRxol8lkeG/EnaK9uAlgvhsnP3B6FFnEz9yV.TxC', 'Oscar', 'Casillas', NULL, 42, 611111111, 'admin@email.com', 5, 1, 3),
(2, 'Blvck', '$2a$10$e/e0E00tJEeKR0Yjn68vm.H31wsmUI4Berw5GWVoJGmLmDD1BMTOS', 'Oscar', 'Rodriguez', NULL, NULL, 666666667, 'admin2@email.com', NULL, NULL, NULL),
(7, 'San', '$2a$10$lI4SfzaNh6ufrKUnp6Bkw.8nt4jIXJo2Zb0RJEna7aXfc52LcOCE.', 'Sandra', 'Rodriguez', 'San_profilePhoto.png', NULL, 666666668, 'admin3@email.com', NULL, NULL, NULL);

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
  `precio` decimal(10,2) NOT NULL
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
  ADD KEY `id_producto` (`id_producto`),
  ADD KEY `id_usuario_vendedor` (`id_usuario_vendedor`);

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
-- Indices de la tabla `token`
--
ALTER TABLE `token`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_usuario` (`id_usuario`);

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `productos`
--
ALTER TABLE `productos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT de la tabla `provincia`
--
ALTER TABLE `provincia`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=57;

--
-- AUTO_INCREMENT de la tabla `subcategorias`
--
ALTER TABLE `subcategorias`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;

--
-- AUTO_INCREMENT de la tabla `token`
--
ALTER TABLE `token`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

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
  ADD CONSTRAINT `contraoferta_ibfk_2` FOREIGN KEY (`id_producto`) REFERENCES `productos` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `contraoferta_ibfk_3` FOREIGN KEY (`id_usuario_vendedor`) REFERENCES `usuarios` (`id`);

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
-- Filtros para la tabla `token`
--
ALTER TABLE `token`
  ADD CONSTRAINT `token_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id`);

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
