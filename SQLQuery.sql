DROP TABLE IF EXISTS KomponenFurnitur
DROP TABLE IF EXISTS KomponenMaterialWarna
DROP TABLE IF EXISTS PesanFurnitur
DROP TABLE IF EXISTS PesanKomponen
DROP TABLE IF EXISTS Pesanan
DROP TABLE IF EXISTS Furnitur
DROP TABLE IF EXISTS Komponen
DROP TABLE IF EXISTS Material
DROP TABLE IF EXISTS Warna
DROP TABLE IF EXISTS Pengguna
DROP TABLE IF EXISTS Kelurahan
DROP TABLE IF EXISTS Kecamatan

CREATE TABLE Kecamatan(
	id int NOT NULL IDENTITY(1,1) PRIMARY KEY,
	nama varchar(50)
)

CREATE TABLE Kelurahan(
	id int NOT NULL IDENTITY(1,1) PRIMARY KEY,
	nama varchar(50),
	idKecamatan int FOREIGN KEY REFERENCES Kecamatan (id)
)

CREATE TABLE Pengguna(
	id int NOT NULL IDENTITY(1,1) PRIMARY KEY,
	nama varchar(100),
	username varchar(20),
	passwords varchar(15),
	roles varchar(15),
	alamat varchar(100),
	noHP varchar(15),
	email varchar(30),
	idKelurahan int FOREIGN KEY REFERENCES Kelurahan (id)
)

CREATE TABLE Warna(
	id int NOT NULL IDENTITY(1,1) PRIMARY KEY,
	nama varchar(20)
)

CREATE TABLE Material(
	id int NOT NULL IDENTITY(1,1) PRIMARY KEY,
	nama varchar(30),
	harga money
)

CREATE TABLE Komponen(
	id int NOT NULL IDENTITY(1,1) PRIMARY KEY,
	nama varchar(50),
	ukuran varchar(3),
	stok int,
	harga money
)

CREATE TABLE Furnitur(
	id int NOT NULL IDENTITY(1,1) PRIMARY KEY,
	nama varchar(30),
	ukuran varchar(3),
	--stok int,
	harga money
)

CREATE TABLE Pesanan(
	idPesanan int NOT NULL IDENTITY(1,1) PRIMARY KEY,
	tglPesanan Date,
	idPelanggan int FOREIGN KEY REFERENCES Pengguna (id)
)

CREATE TABLE PesanKomponen(
	idPesanan int NOT NULL,
	idKomponen int NOT NULL,
	jumlah int
)

ALTER TABLE PesanKomponen
	ADD PRIMARY KEY (idPesanan, idKomponen)

CREATE TABLE PesanFurnitur(
	idPesanan int NOT NULL,
	idFurnitur int NOT NULL,
	jumlah int
)

ALTER TABLE PesanFurnitur
	ADD PRIMARY KEY (idPesanan, idFurnitur)

CREATE TABLE KomponenFurnitur(
	idFurnitur int NOT NULL,
	idKomponen int NOT NULL,
	jumlah int
)

ALTER TABLE KomponenFurnitur
	ADD PRIMARY KEY (idFurnitur, idKomponen)

CREATE TABLE KomponenMaterialWarna(
	idKomponen int NOT NULL,
	idMaterial int NOT NULL,
	idWarna int NOT NULL
)

ALTER TABLE KomponenMaterialWarna
	ADD PRIMARY KEY (idKomponen, idMaterial, idWarna)