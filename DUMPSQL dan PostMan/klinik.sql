-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 22 Feb 2021 pada 15.25
-- Versi server: 10.4.17-MariaDB
-- Versi PHP: 8.0.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `klinik`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `detailobat`
--

CREATE TABLE `detailobat` (
  `idDetailObat` varchar(300) NOT NULL,
  `idTransaction` varchar(300) NOT NULL,
  `idObat` varchar(300) NOT NULL,
  `qty` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `detailobat`
--

INSERT INTO `detailobat` (`idDetailObat`, `idTransaction`, `idObat`, `qty`) VALUES
('ab595457-00ce-46aa-b8a8-6a0957e20ea2', 'f4cf87b2-35ff-4bca-b7ec-f707a4c926c4', '12613659-5d47-467b-95b3-ccd9a95c6812', 30);

--
-- Trigger `detailobat`
--
DELIMITER $$
CREATE TRIGGER `delete-stock-obat` AFTER DELETE ON `detailobat` FOR EACH ROW UPDATE obat set obat.qty = obat.qty + old.qty
WHERE obat.idObat = old.idObat
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `update-stock-obat` AFTER INSERT ON `detailobat` FOR EACH ROW BEGIN
UPDATE obat SET obat.qty = obat.qty - NEW.qty
WHERE idObat = NEW.idObat;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Struktur dari tabel `detailtindakan`
--

CREATE TABLE `detailtindakan` (
  `idDetailTindakan` varchar(300) NOT NULL,
  `idTransaction` varchar(300) NOT NULL,
  `idTindakan` varchar(300) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `detailtindakan`
--

INSERT INTO `detailtindakan` (`idDetailTindakan`, `idTransaction`, `idTindakan`) VALUES
('d4eac0bc-a18d-4248-967d-258ec4d83aa3', 'f4cf87b2-35ff-4bca-b7ec-f707a4c926c4', 'a904e87b-9f69-4876-95d4-aad13709cd5b');

-- --------------------------------------------------------

--
-- Struktur dari tabel `dokter`
--

CREATE TABLE `dokter` (
  `idDokter` varchar(300) NOT NULL,
  `namaDokter` varchar(300) NOT NULL,
  `tglPraktek` date NOT NULL,
  `gender` varchar(30) NOT NULL,
  `noTelp` varchar(100) NOT NULL,
  `alamat` varchar(300) NOT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `dokter`
--

INSERT INTO `dokter` (`idDokter`, `namaDokter`, `tglPraktek`, `gender`, `noTelp`, `alamat`, `status`) VALUES
('0074adf2-a638-4e08-8e8e-ed8751bd6404', 'Budi', '2021-05-25', 'laki-laki', '084736453728', 'Malang', 1),
('6c372131-f8d8-4a8b-97a9-758977dcd865', 'elo', '2021-02-22', 'laki-laki', '081278829165', 'serpong', 1);

-- --------------------------------------------------------

--
-- Struktur dari tabel `obat`
--

CREATE TABLE `obat` (
  `idObat` varchar(300) NOT NULL,
  `namaObat` varchar(300) NOT NULL,
  `qty` int(11) NOT NULL,
  `harga` int(11) NOT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `obat`
--

INSERT INTO `obat` (`idObat`, `namaObat`, `qty`, `harga`, `status`) VALUES
('12613659-5d47-467b-95b3-ccd9a95c6812', 'Paracetamol', 100, 5000, 1),
('f2dfd451-97c6-434c-8590-5ad9ada82943', 'Decradyl', 100, 7000, 1),
('f38a564e-0022-4ea4-ab74-c90e4fe556c0', 'Sanmol', 109, 4000, 1);

-- --------------------------------------------------------

--
-- Struktur dari tabel `pasien`
--

CREATE TABLE `pasien` (
  `idPasien` varchar(300) NOT NULL,
  `namaPasien` varchar(50) NOT NULL,
  `tempat` varchar(300) NOT NULL,
  `tglLahir` date NOT NULL,
  `gender` varchar(20) NOT NULL,
  `noTelp` varchar(300) NOT NULL,
  `alamat` varchar(100) NOT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `pasien`
--

INSERT INTO `pasien` (`idPasien`, `namaPasien`, `tempat`, `tglLahir`, `gender`, `noTelp`, `alamat`, `status`) VALUES
('391c421e-c47f-4981-8335-361c9f455c3a', 'Leo', 'Serpong', '2022-02-02', 'Laki-Laki', '083728172635', 'Unknown', 1),
('3d7f1edf-0da4-40b1-8d92-1117e52e671d', 'King', 'BSD', '1984-04-22', 'Laki-Laki', '08321234232', 'Tangerang', 1),
('454e1867-2345-44ec-9de7-962268105fc1', 'Bro', 'BSD', '1984-04-22', 'Laki-Laki', '08321234232', 'Tangerang', 1),
('87e183cc-aedd-42c6-8a36-0e0ac47ee46c', 'Leo', 'Gading', '1984-03-22', 'Laki-Laki', '0847283874721', 'Unknown', 1);

-- --------------------------------------------------------

--
-- Struktur dari tabel `report`
--

CREATE TABLE `report` (
  `idTransaction` varchar(100) NOT NULL,
  `idPasien` varchar(100) NOT NULL,
  `idDokter` varchar(100) NOT NULL,
  `tglTransaction` date NOT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `report`
--

INSERT INTO `report` (`idTransaction`, `idPasien`, `idDokter`, `tglTransaction`, `status`) VALUES
('f4cf87b2-35ff-4bca-b7ec-f707a4c926c4', '3d7f1edf-0da4-40b1-8d92-1117e52e671d', '6c372131-f8d8-4a8b-97a9-758977dcd865', '2021-02-22', 1);

-- --------------------------------------------------------

--
-- Struktur dari tabel `tindakan`
--

CREATE TABLE `tindakan` (
  `idTindakan` varchar(300) NOT NULL,
  `namaTindakan` varchar(300) NOT NULL,
  `biayaTindakan` int(11) NOT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tindakan`
--

INSERT INTO `tindakan` (`idTindakan`, `namaTindakan`, `biayaTindakan`, `status`) VALUES
('a904e87b-9f69-4876-95d4-aad13709cd5b', 'pasang behel', 455000, 1),
('cb1741f7-52a5-4c26-b1ef-88eb3118bff0', 'Cabut Gigi', 300000, 1);

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `detailobat`
--
ALTER TABLE `detailobat`
  ADD PRIMARY KEY (`idDetailObat`),
  ADD KEY `idObat` (`idObat`),
  ADD KEY `idTransaction` (`idTransaction`);

--
-- Indeks untuk tabel `detailtindakan`
--
ALTER TABLE `detailtindakan`
  ADD PRIMARY KEY (`idDetailTindakan`),
  ADD KEY `idTransaction` (`idTransaction`),
  ADD KEY `idTindakan` (`idTindakan`);

--
-- Indeks untuk tabel `dokter`
--
ALTER TABLE `dokter`
  ADD PRIMARY KEY (`idDokter`);

--
-- Indeks untuk tabel `obat`
--
ALTER TABLE `obat`
  ADD PRIMARY KEY (`idObat`);

--
-- Indeks untuk tabel `pasien`
--
ALTER TABLE `pasien`
  ADD PRIMARY KEY (`idPasien`);

--
-- Indeks untuk tabel `report`
--
ALTER TABLE `report`
  ADD PRIMARY KEY (`idTransaction`),
  ADD KEY `idDokter` (`idDokter`),
  ADD KEY `idPasien` (`idPasien`);

--
-- Indeks untuk tabel `tindakan`
--
ALTER TABLE `tindakan`
  ADD PRIMARY KEY (`idTindakan`);

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `detailobat`
--
ALTER TABLE `detailobat`
  ADD CONSTRAINT `detailobat_ibfk_1` FOREIGN KEY (`idObat`) REFERENCES `obat` (`idObat`),
  ADD CONSTRAINT `detailobat_ibfk_2` FOREIGN KEY (`idTransaction`) REFERENCES `report` (`idTransaction`);

--
-- Ketidakleluasaan untuk tabel `detailtindakan`
--
ALTER TABLE `detailtindakan`
  ADD CONSTRAINT `detailtindakan_ibfk_1` FOREIGN KEY (`idTransaction`) REFERENCES `report` (`idTransaction`),
  ADD CONSTRAINT `detailtindakan_ibfk_2` FOREIGN KEY (`idTindakan`) REFERENCES `tindakan` (`idTindakan`);

--
-- Ketidakleluasaan untuk tabel `report`
--
ALTER TABLE `report`
  ADD CONSTRAINT `report_ibfk_1` FOREIGN KEY (`idDokter`) REFERENCES `dokter` (`idDokter`),
  ADD CONSTRAINT `report_ibfk_2` FOREIGN KEY (`idPasien`) REFERENCES `pasien` (`idPasien`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
