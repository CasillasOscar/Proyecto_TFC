-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 03-06-2025 a las 21:04:00
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
(30, 7, 22),
(31, 7, 20),
(32, 10, 23);

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
  `estado` enum('Nuevo','Seminuevo','Aceptable','Deteriorado') NOT NULL,
  `descripcion` text NOT NULL,
  `fecha_publicacion` date NOT NULL,
  `imagen_1` varchar(250) NOT NULL,
  `imagen_2` varchar(250) NOT NULL,
  `etapa` enum('vendido','activo') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`id`, `nombre`, `id_usuario`, `id_subcategoria`, `precio`, `estado`, `descripcion`, `fecha_publicacion`, `imagen_1`, `imagen_2`, `etapa`) VALUES
(20, 'Iphone 13', 10, 1, 700.00, 'Nuevo', 'Iphone 13 en perfecto estado', '2025-05-29', 'BlvckShvdow_productPhoto120250529174629.jpg', 'BlvckShvdow_productPhoto220250529174629.jpg', 'activo'),
(21, 'BMW E87', 10, 25, 6500.00, 'Aceptable', 'Bmw serie 1 en buen estado de 2006', '2025-05-29', 'BlvckShvdow_productPhoto120250529174819.jpg', 'BlvckShvdow_productPhoto220250529174819.jpg', 'activo'),
(22, 'Camiseta Leganes', 10, 11, 10.00, 'Seminuevo', 'Camiseta del leganes ', '2025-05-29', 'BlvckShvdow_productPhoto120250529174948.jpg', 'BlvckShvdow_productPhoto220250529174948.jpg', 'activo'),
(23, 'Air force one', 7, 14, 50.00, 'Nuevo', 'Air forcce one en muy buen estado', '2025-05-30', 'San_productPhoto120250530165118.jpg', 'San_productPhoto220250530165118.jpg', 'activo'),
(24, 'El monje que vendió su ferrari', 7, 21, 5.90, 'Aceptable', 'Libro buenísimo que me he leído muchas veces y es muy recomendable', '2025-05-30', 'San_productPhoto120250530201756.jpg', 'San_productPhoto220250530201756.jpg', 'activo');

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
(37, 'eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiI3Iiwibm9tYnJlIjoiU2FuZHJhIiwic3ViIjoiYWRtaW4zQGVtYWlsLmNvbSIsImlhdCI6MTc0Nzg1Nzk4OSwiZXhwIjoxNzQ4NDYyNzg5fQ.cW0ei_mRIuTjqavzxtUr2fsuctKa0lpTGpywzXHqWijXLtDJYKHsT9QAEJXQB7NZvBdTT6Iozpd9S5VR4kLTgA', 'BEARER', 1, 1, '2025-05-28 20:06:29', 7),
(38, 'eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiI3Iiwibm9tYnJlIjoiU2FuZHJhIiwic3ViIjoiYWRtaW4zQGVtYWlsLmNvbSIsImlhdCI6MTc0ODEwNjk1MSwiZXhwIjoxNzQ4NzExNzUxfQ.gZ-lQ5YIxZ_scn3653PW3T-ZguYJhxlt7H2wXritjAmK8g8KfJjQ9SPZOxa3SvOTrz1_bsN1ISpnmfozPH4aqQ', 'BEARER', 1, 1, '2025-05-31 17:15:51', 7),
(39, 'eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiI3Iiwibm9tYnJlIjoiU2FuZHJhIiwic3ViIjoiYWRtaW4zQGVtYWlsLmNvbSIsImlhdCI6MTc0ODEwNjk1NCwiZXhwIjoxNzQ4NzExNzU0fQ.7Tv1WgbzPdyGV7HDQYXtSInqAG19RV75F8OsRmH7AoG0h2FoQlxNC4nYH5WVvtDSiqjti9NeV7FLKVL8BC7RQg', 'BEARER', 1, 1, '2025-05-31 17:15:54', 7),
(40, 'eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiI3Iiwibm9tYnJlIjoiU2FuZHJhIiwic3ViIjoiYWRtaW4zQGVtYWlsLmNvbSIsImlhdCI6MTc0ODEwNjk3NSwiZXhwIjoxNzQ4NzExNzc1fQ.i4uXCdFCbPOOfKykrBqgSKwKnNCAhQWEBr8T9CkkS-mWQkVX4Zau--mwuIUOf0IcTBQF3Blcunm2nIYIdk1y4g', 'BEARER', 1, 1, '2025-05-31 17:16:15', 7),
(42, 'eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiI3Iiwibm9tYnJlIjoiU2FuZHJhIiwic3ViIjoiYWRtaW4zQGVtYWlsLmNvbSIsImlhdCI6MTc0ODEwNzA3NSwiZXhwIjoxNzQ4NzExODc1fQ.4mgBv9PTWBWb-ZkstjZFS-PDZNJI-IM1rR9Na-LoWwn5dKE418xzn6UoEc6hAFwXXRGEpLV7JukZnNfFow2Q4g', 'BEARER', 1, 1, '2025-05-31 17:17:55', 7),
(43, 'eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiI3Iiwibm9tYnJlIjoiU2FuZHJhIiwic3ViIjoiYWRtaW4zQGVtYWlsLmNvbSIsImlhdCI6MTc0ODEwNzI0NiwiZXhwIjoxNzQ4NzEyMDQ2fQ.Vd2LY8KZl1R4rbX9X97oAbmlfFsHxkrhd6Ykhff-WigUy60synCgo8ZbuS4o5Dv1hNmT5zD6hHXcVYLxeFuxAQ', 'BEARER', 1, 1, '2025-05-31 17:20:46', 7),
(44, 'eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiI3Iiwibm9tYnJlIjoiU2FuZHJhIiwic3ViIjoiYWRtaW4zQGVtYWlsLmNvbSIsImlhdCI6MTc0ODEwNzU1MiwiZXhwIjoxNzQ4NzEyMzUyfQ.zNQ0IkuiQsdgrWlbe0xZjOkFrmg8Tv6DUrH5kfdCTYdEF0VjO1uh1arNyObmYocZmNPYZ5j2uPhXc5ZpdUB82Q', 'BEARER', 1, 1, '2025-05-31 17:25:52', 7),
(45, 'eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiI3Iiwibm9tYnJlIjoiU2FuZHJhIiwic3ViIjoiYWRtaW4zQGVtYWlsLmNvbSIsImlhdCI6MTc0ODEwNzcxNywiZXhwIjoxNzQ4NzEyNTE3fQ.3BeSSioFuGUlLiDeKGA0azF2wxO4Xj0KtWP-8Mi7azkong0GOXXPzrFT0xc7NM6qCDMkjeYbmcHbQmz2AADvRg', 'BEARER', 1, 1, '2025-05-31 17:28:37', 7),
(46, 'eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiI3Iiwibm9tYnJlIjoiU2FuZHJhIiwic3ViIjoiYWRtaW4zQGVtYWlsLmNvbSIsImlhdCI6MTc0ODEwNzc0NCwiZXhwIjoxNzQ4NzEyNTQ0fQ.P5dyji6E6F7sYD6OveesQhJ7NNQS4Q76K8WLpfZVcM8bNPioXozPH0Fg3-JfsdgCtHrXpLeWIl6gsVzaOamrqA', 'BEARER', 1, 1, '2025-05-31 17:29:04', 7),
(47, 'eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiI3Iiwibm9tYnJlIjoiU2FuZHJhIiwic3ViIjoiYWRtaW4zQGVtYWlsLmNvbSIsImlhdCI6MTc0ODEwODEyMywiZXhwIjoxNzQ4NzEyOTIzfQ.riHii4xSE2nKkGUMLsc3xDaEAPeQkgohF8qJa7R7WSTRccY6qX5BmRhHNaS7Cl6-hpd3bejPeWEvIcxpdeEl9w', 'BEARER', 1, 1, '2025-05-31 17:35:23', 7),
(48, 'eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiI3Iiwibm9tYnJlIjoiU2FuZHJhIiwic3ViIjoiYWRtaW4zQGVtYWlsLmNvbSIsImlhdCI6MTc0ODEwODE5NCwiZXhwIjoxNzQ4NzEyOTk0fQ.BvMr_lrAwdDIrQ9DIEfpKgbX4Hro5jCVECqhIMsBe_6j1irsuHK-HZn5qWA6an4Rqq4acNvgSE90stP0XC1KTg', 'BEARER', 1, 1, '2025-05-31 17:36:34', 7),
(49, 'eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiI3Iiwibm9tYnJlIjoiU2FuZHJhIiwic3ViIjoiYWRtaW4zQGVtYWlsLmNvbSIsImlhdCI6MTc0ODEwODMyMiwiZXhwIjoxNzQ4NzEzMTIyfQ.AhK_p0YxD5yCeZAgZajiFzX7z0PiVkBPbr-ngC1BhP2LFElbR_yticZIMHOB_KwI_Qo8V7F0L-6FeFCHJ5TPNA', 'BEARER', 1, 1, '2025-05-31 17:38:42', 7),
(50, 'eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiI3Iiwibm9tYnJlIjoiU2FuZHJhIiwic3ViIjoiYWRtaW4zQGVtYWlsLmNvbSIsImlhdCI6MTc0ODEwODUyOSwiZXhwIjoxNzQ4NzEzMzI5fQ.4ZQ_qRDjAkCZ2uT0Dp3jDnQMXmf2EY7UPLG5EMJc7Ebsp_dCGhtoIPkS7FF3CTnQQ_9UP3qwEwEexLgGmhRtDA', 'BEARER', 1, 1, '2025-05-31 17:42:09', 7),
(51, 'eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiI3Iiwibm9tYnJlIjoiU2FuZHJhIiwic3ViIjoiYWRtaW4zQGVtYWlsLmNvbSIsImlhdCI6MTc0ODEwOTA2NywiZXhwIjoxNzQ4NzEzODY3fQ.bTx943HqTxiIBXyUSbWowSer2CJmJqq4nCimq0KyMvU3B4olYIpiVnQYrlSt2jlROUQH9b5pRL-wIn6NQ2y-TA', 'BEARER', 1, 1, '2025-05-31 17:51:07', 7),
(54, 'eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiIxMCIsIm5vbWJyZSI6Ik9zY2FyIiwic3ViIjoib3NjYXJAZ21haWwuY29tIiwiaWF0IjoxNzQ4MTEwNzE1LCJleHAiOjE3NDg3MTU1MTV9.QaRHZzjAhoArDGVXv5owBSvyYzK6w-0CVJMovyDQgIIX3KiuUzRrwgMHwMEWQyfL-LGy1_3qa77nfF4VPch7tA', 'BEARER', 1, 1, '2025-05-31 18:18:35', 10),
(55, 'eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiIxMCIsIm5vbWJyZSI6Ik9zY2FyIiwic3ViIjoib3NjYXJAZ21haWwuY29tIiwiaWF0IjoxNzQ4MTExMTYxLCJleHAiOjE3NDg3MTU5NjF9.BIQSezmdxB0UyU0M_lbYkZG536I_Od-kRCsIhkwq1bMgMNpsUWhrtSCQipvHgszhPcD0VY6_aIy73iXlLmA7PA', 'BEARER', 1, 1, '2025-05-31 18:26:01', 10),
(56, 'eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiIxMCIsIm5vbWJyZSI6Ik9zY2FyIiwic3ViIjoib3NjYXJAZ21haWwuY29tIiwiaWF0IjoxNzQ4MTE0OTE0LCJleHAiOjE3NDg3MTk3MTR9.p34zsQWKCt0T7YzCMTpzBqfcPAL7mX5zmBzNpbleDWSbhhKFekYhwDvS6eyqXM2jk4GP-iZeXnhAhXtRIH-dNg', 'BEARER', 1, 1, '2025-05-31 19:28:34', 10),
(57, 'eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiIxMCIsIm5vbWJyZSI6Ik9zY2FyIiwic3ViIjoib3NjYXJAZ21haWwuY29tIiwiaWF0IjoxNzQ4MTg2NjM5LCJleHAiOjE3NDg3OTE0Mzl9.yXUEjCW_glfuRbbA8dCwbDfAm8X2KiTx8jTpKFTw2qRdEO4dYOWN4zqMDcuQwx285BTd8NnZqnGYIdG1x9d_WQ', 'BEARER', 1, 1, '2025-06-01 15:23:59', 10),
(58, 'eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiIxMCIsIm5vbWJyZSI6Ik9zY2FyIiwic3ViIjoib3NjYXJAZ21haWwuY29tIiwiaWF0IjoxNzQ4MTkzMjg2LCJleHAiOjE3NDg3OTgwODZ9.o8vPbSdQDtBAlSVc08sfR7Rnmdxf5u-aYzkHqqIT4HWv8ymKXIQwO5i3JTgeX5j2wqb4peOtTvwJYvnIeoMekQ', 'BEARER', 1, 1, '2025-06-01 17:14:46', 10),
(59, 'eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiIxMCIsIm5vbWJyZSI6Ik9zY2FyIiwic3ViIjoib3NjYXJAZ21haWwuY29tIiwiaWF0IjoxNzQ4MTkzMzUwLCJleHAiOjE3NDg3OTgxNTB9.x42TTBpHHI2LSZQl_yh8wNWtWdd86x_OnfJ7KN_XrNX8dfjVVrwavhe-7iUq-B9F_JU__HR4_FlxlDK_lDbc6A', 'BEARER', 1, 1, '2025-06-01 17:15:50', 10),
(60, 'eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiIxMCIsIm5vbWJyZSI6Ik9zY2FyIiwic3ViIjoib3NjYXJAZ21haWwuY29tIiwiaWF0IjoxNzQ4MTkzNjQ0LCJleHAiOjE3NDg3OTg0NDR9.-blVuMRVG7a6t4rKknoMfSQtZCPHp5xBpxi8tOgQrq27qd2geKmOiqV2PcICgneFSpEZLQCk4OGJqnppc8eGEQ', 'BEARER', 1, 1, '2025-06-01 17:20:44', 10),
(61, 'eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiIxMCIsIm5vbWJyZSI6Ik9zY2FyIiwic3ViIjoib3NjYXJAZ21haWwuY29tIiwiaWF0IjoxNzQ4MTkzOTkzLCJleHAiOjE3NDg3OTg3OTN9.CJsTFr4oL5gB7CvoQq1_16SQa2ojCSZZinlD5Llgw1t9pVDWa5Qv2_r_oWYYOLFFC_hFKM5MMdqPE5150J97_w', 'BEARER', 1, 1, '2025-06-01 17:26:33', 10),
(62, 'eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiIxMCIsIm5vbWJyZSI6Ik9zY2FyIiwic3ViIjoib3NjYXJAZ21haWwuY29tIiwiaWF0IjoxNzQ4MTk0MTk3LCJleHAiOjE3NDg3OTg5OTd9.8eMxfJVN76uLXMhv2EqIU7r6ywcUyDAUkMbI1Qmk-_sOg6uGqOj_R7BCwZhzAgJTybTVoE517pMg9BsUWBsZBQ', 'BEARER', 1, 1, '2025-06-01 17:29:57', 10),
(63, 'eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiIxMCIsIm5vbWJyZSI6Ik9zY2FyIiwic3ViIjoib3NjYXJAZ21haWwuY29tIiwiaWF0IjoxNzQ4MTk0NTQ3LCJleHAiOjE3NDg3OTkzNDd9.lBFmuiB_hawZOOEfWUy3PkdpZJz0rNTgZWrA2NGDN8vl4xfEAz30AgJNf692VuFbM0xPUOtTu7CJRdBQ8ozvtw', 'BEARER', 1, 1, '2025-06-01 17:35:47', 10),
(64, 'eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiIxMCIsIm5vbWJyZSI6Ik9zY2FyIiwic3ViIjoib3NjYXJAZ21haWwuY29tIiwiaWF0IjoxNzQ4MTk0NjQ5LCJleHAiOjE3NDg3OTk0NDl9._6hKLMa8SwNPmBsHlRsqcr-jOybqlnQ7-efP6UFo1Kqp-wy3ieTWNfFtvcD3gUbgW-RAMBVRJN8Q4JKv0DjUFA', 'BEARER', 1, 1, '2025-06-01 17:37:29', 10),
(65, 'eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiIxMCIsIm5vbWJyZSI6Ik9zY2FyIiwic3ViIjoib3NjYXJAZ21haWwuY29tIiwiaWF0IjoxNzQ4MTk1OTQyLCJleHAiOjE3NDg4MDA3NDJ9.w6CsRSof3jEjwUVL6Jp9UyMr7LiDR3M674bVbb0zAjmARG6wj04NIDjairAqdyzqXoe_GjvXKXa5avrcAFNw6Q', 'BEARER', 1, 1, '2025-06-01 17:59:02', 10),
(66, 'eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiIxMCIsIm5vbWJyZSI6Ik9zY2FyIiwic3ViIjoib3NjYXJAZ21haWwuY29tIiwiaWF0IjoxNzQ4MTk2MDA5LCJleHAiOjE3NDg4MDA4MDl9.jVgWK5dIHwn1xIjCJfXNLQ0N-hMzCIIgoFGYDkOBuAwXwGRbjr8UNoOLJMF-GNwLmjHb9-Lgb7PWKx8R8eiWQg', 'BEARER', 1, 1, '2025-06-01 18:00:09', 10),
(67, 'eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiIxMCIsIm5vbWJyZSI6Ik9zY2FyIiwic3ViIjoib3NjYXJAZ21haWwuY29tIiwiaWF0IjoxNzQ4MTk4ODg5LCJleHAiOjE3NDg4MDM2ODl9.AJTNguNWnvI6Mrgs4GDqeH-bQV2rmMQuW57f21cq0OHuj1PzeRQkM71CIm5819f2oZKFzkgCpo8c-yrXbxfYwA', 'BEARER', 1, 1, '2025-06-01 18:48:09', 10),
(68, 'eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiIxMCIsIm5vbWJyZSI6Ik9zY2FyIiwic3ViIjoib3NjYXJAZ21haWwuY29tIiwiaWF0IjoxNzQ4MTk5MDMyLCJleHAiOjE3NDg4MDM4MzJ9.5d5C3xDKROPisrIgEL6WOpqYDi_f9rScY0eV2H-Eyj8HMTDd0j-hy2aaDLkpSZZwv59227DUlSr8XZlcgA0H8w', 'BEARER', 1, 1, '2025-06-01 18:50:32', 10),
(69, 'eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiIxMCIsIm5vbWJyZSI6Ik9zY2FyIiwic3ViIjoib3NjYXJAZ21haWwuY29tIiwiaWF0IjoxNzQ4Mjc5MDg3LCJleHAiOjE3NDg4ODM4ODd9.6ht3TTRKPsTnj4V4fZzWFOwC6MWM7DxvkaMx0JKYXsE54HWrIwRIZs3FO39b8cSUwrQJ2_V8-balQLvONlxQEg', 'BEARER', 1, 1, '2025-06-02 17:04:47', 10),
(70, 'eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiIxMCIsIm5vbWJyZSI6Ik9zY2FyIiwic3ViIjoib3NjYXJAZ21haWwuY29tIiwiaWF0IjoxNzQ4MjgxMjA3LCJleHAiOjE3NDg4ODYwMDd9.LQbQl95tgWkMjTn8ZCQYb7pBZVZLZ5NrqaQtYGIgsc-zShz09te8zUmkNdaLnHCN6H3Jc8dLhAV7swPWFUTGwQ', 'BEARER', 1, 1, '2025-06-02 17:40:07', 10),
(71, 'eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiIxMCIsIm5vbWJyZSI6Ik9zY2FyIiwic3ViIjoib3NjYXJAZ21haWwuY29tIiwiaWF0IjoxNzQ4MjgxNDUwLCJleHAiOjE3NDg4ODYyNTB9.z6Nyb7MT8siANffhVorJkvjTOGGtledYvyu6RKq9Y4vbyPn1RfOKhA696zQQIPUho9ESva185UjJTzpkC5efQg', 'BEARER', 1, 1, '2025-06-02 17:44:10', 10),
(72, 'eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiIxMCIsIm5vbWJyZSI6Ik9zY2FyIiwic3ViIjoib3NjYXJAZ21haWwuY29tIiwiaWF0IjoxNzQ4MjgxNTEzLCJleHAiOjE3NDg4ODYzMTN9.zHh1eX1_qjFWAxevnAsYfRranbFQ0NeGW1htLkdawo6FZXgYTF9xwO0tkBSzvdT9BaMuO6_EXCkgpyD8FO4jrQ', 'BEARER', 1, 1, '2025-06-02 17:45:13', 10),
(73, 'eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiIxMCIsIm5vbWJyZSI6Ik9zY2FyIiwic3ViIjoib3NjYXJAZ21haWwuY29tIiwiaWF0IjoxNzQ4Mjg2MDY1LCJleHAiOjE3NDg4OTA4NjV9.-0xf6ao4e051CfLP8DH51nTx0iU8MH3HBLmo_4gg6p07pvxvk042hznDiebJzITUvSLSGylH3dIfKOIbFcPt5Q', 'BEARER', 1, 1, '2025-06-02 19:01:05', 10),
(74, 'eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiIxMCIsIm5vbWJyZSI6Ik9zY2FyIiwic3ViIjoib3NjYXJAZ21haWwuY29tIiwiaWF0IjoxNzQ4Mjg2NDUzLCJleHAiOjE3NDg4OTEyNTN9.--c9YUCkKlQ1lDvuVlU3K6utYRcHqbTgXWU2X8t21sL2J2P-uIFwUvVGRmvwVtNOg4VXGM1lj_X5qZn72vFT3g', 'BEARER', 1, 1, '2025-06-02 19:07:33', 10),
(75, 'eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiIxMCIsIm5vbWJyZSI6Ik9zY2FyIiwic3ViIjoib3NjYXJAZ21haWwuY29tIiwiaWF0IjoxNzQ4Mjg2NTYwLCJleHAiOjE3NDg4OTEzNjB9.3fzQ9lGGSUnr2eC5LASvZzr3AJXdPVY5TijsmLxNYB1FWIxhk9t1BLDwpUJB9ILE7yEFYSFt9TWC1DUBUBHMsA', 'BEARER', 1, 1, '2025-06-02 19:09:20', 10),
(76, 'eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiIxMCIsIm5vbWJyZSI6Ik9zY2FyIiwic3ViIjoib3NjYXJAZ21haWwuY29tIiwiaWF0IjoxNzQ4Mjg2ODQ5LCJleHAiOjE3NDg4OTE2NDl9.7FJo4m-kCpcgT5x4eOiDbFRQah8ljFV9Qk0O3r_UYI8m8yfrP-w3zeV5ieGNTET0EKcMStF5Wvutpe179FsgSg', 'BEARER', 1, 1, '2025-06-02 19:14:09', 10),
(77, 'eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiIxMCIsIm5vbWJyZSI6Ik9zY2FyIiwic3ViIjoib3NjYXJAZ21haWwuY29tIiwiaWF0IjoxNzQ4Mjg2OTAyLCJleHAiOjE3NDg4OTE3MDJ9.xHhpHypiJv8f6Q7VZiCiZ2XBOO5xMveOKbJiC5QB4bRvnVRWlpx5J7DFv8d6no3XFeWG9gRrFgSaDx9Yvjxo2A', 'BEARER', 1, 1, '2025-06-02 19:15:02', 10),
(78, 'eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiI3Iiwibm9tYnJlIjoiU2FuZHJhIiwic3ViIjoiYWRtaW4zQGVtYWlsLmNvbSIsImlhdCI6MTc0ODI4Njk3OCwiZXhwIjoxNzQ4ODkxNzc4fQ.-86iU-Ea0--7DQCPp0MEkdqP9j8nzTZzxk-KJf6omQ3-jnO6ds76dAsnOuBg_qP1wyBHeY6wY0neWHXKY5WYXA', 'BEARER', 1, 1, '2025-06-02 19:16:18', 7),
(79, 'eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiIxMCIsIm5vbWJyZSI6Ik9zY2FyIiwic3ViIjoib3NjYXJAZ21haWwuY29tIiwiaWF0IjoxNzQ4NTEzODAwLCJleHAiOjE3NDkxMTg2MDB9.2a-zLtJkESGQ3khYSO2KASwNGKVwjKW3MoQDeUEr4d4GEnWX6-mbDuzX4vu0kFkxo5V3ZEy9lqF1pspHR3TKZA', 'BEARER', 1, 1, '2025-06-05 10:16:40', 10),
(80, 'eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiIxMCIsIm5vbWJyZSI6Ik9zY2FyIiwic3ViIjoib3NjYXJAZ21haWwuY29tIiwiaWF0IjoxNzQ4NTMzNDE3LCJleHAiOjE3NDkxMzgyMTd9.dEx53XhcN4fzAyPIodUJxIz3KVoz5BR6IQJ6-FK1Q-z9JZG9K135zl5O9-UGt7WxnNuvb7knTLOSbLa5xfPyLg', 'BEARER', 1, 1, '2025-06-05 15:43:37', 10),
(81, 'eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiIxMCIsIm5vbWJyZSI6Ik9zY2FyIiwic3ViIjoib3NjYXJAZ21haWwuY29tIiwiaWF0IjoxNzQ4NTk4NTAwLCJleHAiOjE3NDkyMDMzMDB9.yjtyI1PHmm4vgROvLtMZNjNlCHtULe2fJgOqlg7idO73NqoFvpSdKxKSqBFb1atcetwkAjQ3n9mOyn1ABi7e0A', 'BEARER', 1, 1, '2025-06-06 09:48:20', 10),
(82, 'eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiIxMCIsIm5vbWJyZSI6Ik9zY2FyIiwic3ViIjoib3NjYXJAZ21haWwuY29tIiwiaWF0IjoxNzQ4NjAyMzg5LCJleHAiOjE3NDkyMDcxODl9.uDDUaWEBwoq2XAdyEIT-0_POHNUqf46LKRjR1feEBeUtwYekCKaCNqILY_f5U-kyHsOo9WAPs1iVhkHmnCuHTg', 'BEARER', 1, 1, '2025-06-06 10:53:09', 10),
(83, 'eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiIxMCIsIm5vbWJyZSI6Ik9zY2FyIiwic3ViIjoib3NjYXJAZ21haWwuY29tIiwiaWF0IjoxNzQ4NjAyNjc0LCJleHAiOjE3NDkyMDc0NzR9.qYULNJdNrAwAEuGwIE_h3GUvtwV_FSIlcTdmGhoMHAIceuNBXxM4_WtDP3Ajpc_ZggSkb1cdACx-tEaNqQ5epw', 'BEARER', 1, 1, '2025-06-06 10:57:54', 10),
(84, 'eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiI3Iiwibm9tYnJlIjoiU2FuZHJhIiwic3ViIjoiYWRtaW4zQGVtYWlsLmNvbSIsImlhdCI6MTc0ODYxNjQ5OCwiZXhwIjoxNzQ5MjIxMjk4fQ.iJ7_XPxabfrdcKdiRY21g1imErtH7tOUeyrngFqSFJt-pgIiR3fBlz8llL7Zl931SJEC5DS8f9sx_WKfBBZOHw', 'BEARER', 1, 1, '2025-06-06 14:48:18', 7),
(85, 'eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiIxMCIsIm5vbWJyZSI6Ik9zY2FyIiwic3ViIjoib3NjYXJAZ21haWwuY29tIiwiaWF0IjoxNzQ4NjE2NzAzLCJleHAiOjE3NDkyMjE1MDN9.OoCfEcQFsf4fvH6fbhmn4o7E-KLuMxCnu5SzTvLsC3KCd2AP1JLTY1qv16UBsPJAt1IsKi04SVgSXJRABMhfVg', 'BEARER', 1, 1, '2025-06-06 14:51:43', 10),
(86, 'eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiIxMCIsIm5vbWJyZSI6Ik9zY2FyIiwic3ViIjoib3NjYXJAZ21haWwuY29tIiwiaWF0IjoxNzQ4NjE3ODgxLCJleHAiOjE3NDkyMjI2ODF9.6TV_zmHzvfGnM6xWrOP4PrKjfmKRuCgiT63LQagICJg1WsHg3Btl5B1YQGYaJAxxm2l_L9Uvr0MisN-CdjTp1A', 'BEARER', 1, 1, '2025-06-06 15:11:21', 10),
(87, 'eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiIxMCIsIm5vbWJyZSI6Ik9zY2FyIiwic3ViIjoib3NjYXJAZ21haWwuY29tIiwiaWF0IjoxNzQ4NjIwNzQ4LCJleHAiOjE3NDkyMjU1NDh9.UMJ18XihhaFYHF_YSerBpxgi4qXAwSpQUPSjmZ2X6HP7CeQzFBidrpGb183jojDBzYIK8JJ1_MSvVlr4WqbiAA', 'BEARER', 1, 1, '2025-06-06 15:59:08', 10),
(88, 'eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiIxMCIsIm5vbWJyZSI6Ik9zY2FyIiwic3ViIjoib3NjYXJAZ21haWwuY29tIiwiaWF0IjoxNzQ4NjIwODA1LCJleHAiOjE3NDkyMjU2MDV9.G3M2tHLVXHvGIGJczFOvyNn39FHhzhJSny7WyZIJYl8H5cC23If5phI4tqZKr_X71Ng-bJSuXCiLj97LuCLiNA', 'BEARER', 1, 1, '2025-06-06 16:00:05', 10),
(89, 'eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiIxMCIsIm5vbWJyZSI6Ik9zY2FyIiwic3ViIjoib3NjYXJAZ21haWwuY29tIiwiaWF0IjoxNzQ4NjIyNDA1LCJleHAiOjE3NDkyMjcyMDV9.Gd0gnP08WpWyoS5ElKv2Ob7E_7YpMjZhP7NB2NQ4UwVO7bU02Y4kEvpy6z6pyQ3Kqp4dJCma2PkOteNU45CB6w', 'BEARER', 1, 1, '2025-06-06 16:26:45', 10),
(90, 'eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiIxMCIsIm5vbWJyZSI6Ik9zY2FyIiwic3ViIjoib3NjYXJAZ21haWwuY29tIiwiaWF0IjoxNzQ4NjIyNTQ1LCJleHAiOjE3NDkyMjczNDV9.rk_dlTJD4p5E4qvtGQx4Iy8QVX2SYr4FZzk8z-cs0oBKXq0qIguzZsDFExWbzi11UP26hCkiGeLmt3-ZuuagHg', 'BEARER', 1, 1, '2025-06-06 16:29:05', 10),
(91, 'eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiIxMCIsIm5vbWJyZSI6Ik9zY2FyIiwic3ViIjoib3NjYXJAZ21haWwuY29tIiwiaWF0IjoxNzQ4NjI3MjExLCJleHAiOjE3NDkyMzIwMTF9.bp1IIfmJiVNlzrX3hg69lhpwP0WCQaEqnVKo2EQ9Lr7BrlrWRFyuzAajaL7V3zZ6DyoY627O4SbWISPHffK03Q', 'BEARER', 1, 1, '2025-06-06 17:46:51', 10),
(92, 'eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiIxMCIsIm5vbWJyZSI6Ik9zY2FyIiwic3ViIjoib3NjYXJAZ21haWwuY29tIiwiaWF0IjoxNzQ4NjI3ODAxLCJleHAiOjE3NDkyMzI2MDF9.mKQ0FQNyXXy3Y_HzKLOvHeN9vQqzqjLF0ZKt_RwLCYeXY_IQ9qE4ExUxT2Jgf39abudcqYrYM7BFAU8cIUD8XA', 'BEARER', 1, 1, '2025-06-06 17:56:41', 10),
(93, 'eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiI3Iiwibm9tYnJlIjoiU2FuZHJhIiwic3ViIjoiYWRtaW4zQGVtYWlsLmNvbSIsImlhdCI6MTc0ODYyODkxNiwiZXhwIjoxNzQ5MjMzNzE2fQ.Zats0fmJEf6HrLWdaDYVygtpEQ2uN8ABxUrVoRcZ4ZZ0pCTPEAlAK9g_2Vt3soCgkQfYJ4jY7U4jN0Oo-sm2MQ', 'BEARER', 1, 1, '2025-06-06 18:15:16', 7),
(94, 'eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiIxMCIsIm5vbWJyZSI6Ik9zY2FyIiwic3ViIjoib3NjYXJAZ21haWwuY29tIiwiaWF0IjoxNzQ4NjI5MTA4LCJleHAiOjE3NDkyMzM5MDh9.UR_sjapBV0kyLRecdgZIxfFrny4HVzq3XXo9UcOn-TNlLD0bwOaTqtB36cxvIE1q9RF9dHh-2rc9eG1LUirK7Q', 'BEARER', 1, 1, '2025-06-06 18:18:28', 10),
(95, 'eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiIxMCIsIm5vbWJyZSI6Ik9zY2FyIiwic3ViIjoib3NjYXJAZ21haWwuY29tIiwiaWF0IjoxNzQ4Nzg5NjkxLCJleHAiOjE3NDkzOTQ0OTF9.ICx_Ori5Btcz-PuZLyhBr9Hkk1_RCliqKZV_IRjvOEJFbapbvf6305Dld_WBLZttTbwfnNgjoRAPCQYKoIiP4g', 'BEARER', 1, 1, '2025-06-08 14:54:51', 10),
(96, 'eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiIxMCIsIm5vbWJyZSI6Ik9zY2FyIiwic3ViIjoib3NjYXJAZ21haWwuY29tIiwiaWF0IjoxNzQ4Nzk2Mjc3LCJleHAiOjE3NDk0MDEwNzd9.xSq3OL2kp-0lbCxuE2y-cPEBdocZbVfVERCfHheK_p7QKXjQY3iNR9mMxfdCm-KprxUPZ9VoXmGYJJAKTxhYpA', 'BEARER', 1, 1, '2025-06-08 16:44:37', 10),
(97, 'eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiIxMCIsIm5vbWJyZSI6Ik9zY2FyIiwic3ViIjoib3NjYXJAZ21haWwuY29tIiwiaWF0IjoxNzQ4OTc1NTQ3LCJleHAiOjE3NDk1ODAzNDd9.a81sfj3KRhXM4rz4HoBNwhxeW9G09H5_mFCuP3MqNLzJ94GpLDFDLmPvm9e8v_Qtn8rVqn2cOK-p9yud0oxyQg', 'BEARER', 1, 1, '2025-06-10 18:32:27', 10);

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
  `valoracion` float DEFAULT 5,
  `n_ventas` int(11) DEFAULT 0,
  `n_compras` int(11) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id`, `nickname`, `password`, `nombre`, `apellido`, `imagen_perfil`, `id_provincia`, `telefono`, `email`, `valoracion`, `n_ventas`, `n_compras`) VALUES
(7, 'San', '$2a$10$lI4SfzaNh6ufrKUnp6Bkw.8nt4jIXJo2Zb0RJEna7aXfc52LcOCE.', 'Sandra', 'Borja', 'San_profilePhoto.png', 42, 666666668, 'admin3@email.com', 5, 0, 0),
(10, 'BlvckShvdow', '$2a$10$uAeCcbCcm4snp6zjj53jqOdb9vU50PCbr84haEgRNglkACEBy/waa', 'Oscar', 'Casillas', 'BlvckShvdow_profilePhoto.png', 42, 689350653, 'oscar@gmail.com', 5, 0, 0);

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- AUTO_INCREMENT de la tabla `productos`
--
ALTER TABLE `productos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=98;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

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
