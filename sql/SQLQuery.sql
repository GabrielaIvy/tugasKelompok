DROP VIEW IF EXISTS TotalPesanan;
DROP VIEW IF EXISTS KomponenTerlaris;
DROP VIEW IF EXISTS FurniturTerlaris;
DROP VIEW IF EXISTS LaporanPenjualanFinal;
DROP VIEW IF EXISTS TotalPenjualan;
DROP VIEW IF EXISTS HitungTotalHarga;
DROP VIEW IF EXISTS LaporanPenjualan;
DROP VIEW IF EXISTS KecamatanKelurahan;
DROP TABLE IF EXISTS KomponenMaterialWarna;
DROP TABLE IF EXISTS KomponenFurnitur;
DROP TABLE IF EXISTS PesanFurnitur;
DROP TABLE IF EXISTS PesanKomponen;
DROP TABLE IF EXISTS Pesanan;
DROP TABLE IF EXISTS Furnitur;
DROP TABLE IF EXISTS Komponen;
DROP TABLE IF EXISTS Material;
DROP TABLE IF EXISTS Warna;
DROP TABLE IF EXISTS Pengguna;
DROP TABLE IF EXISTS Kelurahan;
DROP TABLE IF EXISTS Kecamatan;


CREATE TABLE Kecamatan (
    id SERIAL PRIMARY KEY,
    nama VARCHAR(50)
);

INSERT INTO Kecamatan (nama) VALUES
('Andir'), ('Buah Batu'), ('Cibiru');

CREATE TABLE Kelurahan (
    id SERIAL PRIMARY KEY,
    nama VARCHAR(50),
    idKecamatan INT REFERENCES Kecamatan (id)
);

INSERT INTO Kelurahan (nama, idKecamatan) VALUES
	('Campaka', 1), ('Ciroyom', 1), ('Dunguscariang', 1), ('Garuda', 1), ('Kebonjeruk', 1), ('Maleber', 1),
	('Cijaura', 2), ('Jatisari', 2), ('Margasari', 2), ('Sekejati', 2),
	('Cipadung', 3), ('Cisurupan', 3), ('Palasari', 3), ('Pasirbiru', 3);

CREATE TABLE Pengguna (
    id SERIAL PRIMARY KEY,
    nama VARCHAR(100),
    username VARCHAR(20),
    passwords VARCHAR(15),
    roles VARCHAR(15),
    alamat VARCHAR(100),
    noHP VARCHAR(15),
    email VARCHAR(30),
    idKelurahan INT REFERENCES Kelurahan (id)
);

INSERT INTO Pengguna (nama,username,passwords,roles,alamat,noHP,email,idKelurahan)VALUES
	('Kapi', 'kapikapi', '98765', 'PemilikToko', 'jl suka no 8', '087654451234', 'kapi@gmail.com', 1),
	('Dodo', 'do123', '12345', 'Pelanggan', 'jl damai no 9', '087755221010', 'dodo@gmail.com', 2),
	('Wombat', 'wom123', '56789', 'Pelanggan', 'jl bahagia no 119', '081122334451', 'wombat@gmail.com', 5),
	('Kapi', 'kapi123', '23456', 'Pelanggan', 'jl suka no 8', '087654451234', 'kapi@gmail.com', 1),
	('Dudu', 'du123', '67890', 'Pelanggan', 'jl duka no 1', '087766557788', 'dudu@gmail.com',13),
	('Wombi', 'bi123', '45678', 'Pelanggan', 'jl singa no 90', '08811223344', 'wombi@gmail.com', 10),
	('Didi', 'di987', '99880', 'Pelanggan', 'jl pribadi no 7', '088777788881', 'didi@gmailo.com', 8);
	
CREATE TABLE Warna (
    id SERIAL PRIMARY KEY,
    nama VARCHAR(20)
);

INSERT INTO Warna (nama) VALUES
	('Hitam'), ('Putih'), ('Cokelat Tua'), ('Cokelat Muda'), ('Silver'), ('Hijau');

CREATE TABLE Material (
    id SERIAL PRIMARY KEY,
    nama VARCHAR(30)
);

INSERT INTO Material (nama) VALUES
	('Kayu Solid'), ('Rotan'), ('Plastik'),
	('Kulit'), ('Kayu Olahan'), ('Stainless Steel');

CREATE TABLE Komponen (
    id SERIAL PRIMARY KEY,
    nama VARCHAR(50),
    ukuran VARCHAR(20),
    stok INT,
    harga DOUBLE PRECISION,
	gambar VARCHAR(100)
);

INSERT INTO Komponen (nama, ukuran, stok, harga, gambar) VALUES
	('Handle Pintu', 'Small (10 cm)', 5, 20000, 'handle pintu.webp'),
	('Handle Pintu', 'Medium (15 cm)', 7, 25000, 'handle pintu.webp'),
	('Handle Pintu', 'Large (20 cm)', 3, 30000, 'handle pintu.webp'),
	('Engsel', 'Small (10 cm)', 8, 15000, 'engsel.jpg'),
	('Engsel', 'Medium (15 cm)', 8, 25000, 'engsel.jpg'),
	('Engsel', 'Large (20 cm)', 8, 35000, 'engsel.jpg'),
	('Kaki Kursi', 'Small (60 cm)', 2, 40000, 'kaki kursi.jpg'),
	('Kaki Kursi', 'Medium (70 cm)', 3, 60000, 'kaki kursi.jpg'),
	('Kaki Kursi', 'Larga (80 cm)', 2, 80000, 'kaki kursi.jpg'),
	('Sandaran Kursi', 'Small (50 cm)', 2, 30000, 'sandaran kursi.avif'),
	('Sandaran Kursi', 'Medium (60 cm)', 2, 40000, 'sandaran kursi.avif'),
	('Sandaran Kursi', 'Large (70 cm)', 2, 50000, 'sandaran kursi.avif'),
	('Kain Pelapis', 'Small (50 cm)', 2, 20000, 'kain pelapis.webp'),
	('Kain Pelapis', 'Medium (60 cm)', 2, 30000, 'kain pelapis.webp'),
	('Kain Pelapis', 'Large (70 cm)', 2, 40000, 'kain pelapis.webp');
	
CREATE TABLE Furnitur (
    id SERIAL PRIMARY KEY,
    nama VARCHAR(30),
    ukuran VARCHAR(50),
	stok INT,
    harga DOUBLE PRECISION,
	gambar VARCHAR(100)
);

INSERT INTO Furnitur (nama, ukuran, stok, harga, gambar) VALUES
	('Kursi', 'Small (50cm x 60cm)', 4, 100000, 'kursi.jpg'),
	('Kursi', 'Medium (60cm x 70cm)', 2, 150000, 'kursi.jpg'),
	('Kursi', 'Large (70cm x 80cm)', 3, 180000, 'kursi.jpg'),
	('Meja Makan', 'Small (120cm x 80cm)', 6, 700000, 'meja makan.webp'),
	('Meja Makan', 'Medium (150cm x 90cm)', 2, 90000, 'meja makan.webp'),
	('Meja Makan', 'Large (180cm x 100cm)', 9, 120000, 'meja makan.webp');

CREATE TABLE Pesanan (
    idPesanan SERIAL PRIMARY KEY,
    tglPesanan DATE,
    idPelanggan INT REFERENCES Pengguna (id)
);

INSERT INTO Pesanan (tglPesanan, idPelanggan) VALUES
	('2024-09-08', 2),
	('2024-09-08', 4),
	('2024-09-08', 5),
	('2024-09-09', 3),
	('2024-09-09', 6),
	('2024-09-10', 2);

CREATE TABLE PesanKomponen (
    idPesanan INT NOT NULL,
    idKomponen INT NOT NULL,
    jumlah INT,
    PRIMARY KEY (idPesanan, idKomponen)
);

INSERT INTO PesanKomponen (idPesanan, idKomponen, jumlah) VALUES
	(1, 1, 2), (3, 3, 1), (4, 6, 2), (2, 2, 2);

CREATE TABLE PesanFurnitur (
    idPesanan INT NOT NULL,
    idFurnitur INT NOT NULL,
    jumlah INT,
    PRIMARY KEY (idPesanan, idFurnitur)
);

INSERT INTO PesanFurnitur (idPesanan, idFurnitur, jumlah) VALUES
	(2, 1, 2), (5, 3, 1), (3, 3, 2);

CREATE TABLE KomponenFurnitur (
    idFurnitur INT NOT NULL,
    idKomponen INT NOT NULL,
    PRIMARY KEY (idFurnitur, idKomponen)
);

INSERT INTO KomponenFurnitur VALUES
	(1, 7), (1, 10), (1, 13),
	(2, 8), (2, 11), (2, 14),
	(3, 9), (3, 12), (3, 15);

CREATE TABLE KomponenMaterialWarna (
    idKomponen INT NOT NULL,
    idMaterial INT NOT NULL,
    idWarna INT NOT NULL,
    PRIMARY KEY (idKomponen, idMaterial, idWarna)
);

INSERT INTO KomponenMaterialWarna VALUES
	(1, 1, 3), (2, 6, 4), (3, 7, 5), (7, 2, 2), (10, 5, 5);

CREATE TABLE Transaksi(
	id SERIAL PRIMARY KEY,
	idFurnitur int,
	idKomponen int,
	stok int,
	tanggal date
);

CREATE VIEW LaporanPenjualan AS
SELECT 
	p.idPesanan,
    p.tglPesanan,
	f.id AS idFurnitur,
    f.nama AS namaFurnitur,
    c.nama AS namaKomponen,
    w.nama AS warna,
    m.nama AS material,
    f.ukuran AS ukuran,
	pf.jumlah AS jumlah,
    f.harga AS harga
FROM 
    Pesanan p
JOIN 
    PesanFurnitur pf ON p.idPesanan = pf.idPesanan
JOIN 
    Furnitur f ON pf.idFurnitur = f.id
JOIN 
    KomponenFurnitur cf ON f.id = cf.idFurnitur
JOIN 
    Komponen c ON cf.idKomponen = c.id
LEFT JOIN 
    KomponenMaterialWarna cmw ON c.id = cmw.idKomponen
LEFT JOIN 
    Warna w ON cmw.idWarna = w.id
LEFT JOIN 
    Material m ON cmw.idMaterial = m.id

UNION ALL

SELECT 
    p.idPesanan,
    p.tglPesanan,
	-1 AS idFurnitur,
	'-' AS namaFurnitur,
	k.nama AS namaKomponen,
    w.nama AS warna,
    m.nama AS material,
    k.ukuran AS ukuran,
    pk.jumlah AS jumlah,
    k.harga AS harga
FROM 
    Pesanan p
JOIN 
    PesanKomponen pk ON p.idPesanan = pk.idPesanan
JOIN 
    Komponen k ON pk.idKomponen = k.id
LEFT JOIN 
    KomponenMaterialWarna cmw ON k.id = cmw.idKomponen
LEFT JOIN 
    Warna w ON cmw.idWarna = w.id
LEFT JOIN 
    Material m ON cmw.idMaterial = m.id;

CREATE VIEW KecamatanKelurahan AS
SELECT 
    l.id,
    l.nama AS nama,
	l.idKecamatan AS idKecamatan
	
FROM 
    Kecamatan k
JOIN 
    Kelurahan l ON k.id = l.idKecamatan;

CREATE VIEW HitungTotalHarga AS
SELECT 
	idPesanan,
	SUM(jumlah*harga) AS totalHarga
FROM (
	SELECT 
		p.idPesanan,
		pf.jumlah AS jumlah,
	    f.harga AS harga
	FROM 
	    Pesanan p
	JOIN 
	    PesanFurnitur pf ON p.idPesanan = pf.idPesanan
	JOIN 
	    Furnitur f ON pf.idFurnitur = f.id
		
	UNION ALL
	
	SELECT
		p.idPesanan,
	    pk.jumlah AS jumlah,
	    k.harga AS harga
	FROM 
	    Pesanan p
	JOIN 
	    PesanKomponen pk ON p.idPesanan = pk.idPesanan
	JOIN 
	    Komponen k ON pk.idKomponen = k.id
) AS Pesanan
GROUP BY idPesanan;

CREATE VIEW LaporanPenjualanFinal AS
SELECT
	lp.idPesanan,
    lp.tglPesanan,
	lp.idFurnitur,
	lp.namaFurnitur,
	lp.namaKomponen,
    lp.warna,
    lp.material,
    lp.ukuran,
    lp.jumlah,
    lp.harga,
	ht.totalHarga
FROM
	LaporanPenjualan lp
LEFT JOIN
	HitungTotalHarga ht ON lp.idPesanan = ht.idPesanan;

CREATE VIEW TotalPenjualan AS
SELECT
	SUM(totalHarga)
FROM HitungTotalHarga;

CREATE VIEW FurniturTerlaris AS
SELECT 
	f.nama, 
	furniturTerlaris.totalPesanan
FROM
	Furnitur f
JOIN(
	SELECT 
		idFurnitur, 
		SUM(jumlah) as totalPesanan
	FROM 
		pesanFurnitur
	GROUP BY idFurnitur
	ORDER BY totalPesanan DESC
	LIMIT 1
) AS furniturTerlaris
ON f.id = furniturTerlaris.idFurnitur;

CREATE VIEW KomponenTerlaris AS
SELECT 
	k.nama, 
	komponenTerlaris.totalPesanan
FROM
	Komponen k
JOIN(
	SELECT 
		idKomponen, 
		SUM(jumlah) as totalPesanan
	FROM 
		pesanKomponen
	GROUP BY idKomponen
	ORDER BY totalPesanan DESC
	LIMIT 1
) AS komponenTerlaris
ON k.id = komponenTerlaris.idKomponen;

CREATE VIEW TotalPesanan AS
SELECT 
	COUNT(idPesanan) AS totalPesanan
FROM 
	pesanan;
