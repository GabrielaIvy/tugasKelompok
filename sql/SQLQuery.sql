DROP VIEW IF EXISTS LaporanPenjualanFinal CASCADE;
DROP VIEW IF EXISTS TotalPendapatan CASCADE;
DROP VIEW IF EXISTS HitungTotalHarga CASCADE;
DROP VIEW IF EXISTS LaporanPenjualan CASCADE;
DROP VIEW IF EXISTS KecamatanKelurahan CASCADE;

DROP TABLE IF EXISTS KeranjangKomponen CASCADE;
DROP TABLE IF EXISTS KeranjangFurnitur CASCADE;
DROP TABLE IF EXISTS Transaksi CASCADE;
DROP TABLE IF EXISTS KomponenMaterialWarna CASCADE;
DROP TABLE IF EXISTS KomponenFurnitur CASCADE;
DROP TABLE IF EXISTS PesanFurnitur CASCADE;
DROP TABLE IF EXISTS PesanKomponen CASCADE;
DROP TABLE IF EXISTS Pesanan CASCADE;
DROP TABLE IF EXISTS Furnitur CASCADE;
DROP TABLE IF EXISTS Komponen CASCADE;
DROP TABLE IF EXISTS Material CASCADE;
DROP TABLE IF EXISTS Warna CASCADE;
DROP TABLE IF EXISTS Pengguna CASCADE;
DROP TABLE IF EXISTS Kelurahan CASCADE;
DROP TABLE IF EXISTS Kecamatan CASCADE;


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
	('kapi', 'kapikapi', '98765', 'PemilikToko', 'jl suka no 8', '087654451234', 'kapi@gmail.com', 1),
	('dodo', 'do123', '12345', 'Pelanggan', 'jl damai no 9', '087755221010', 'dodo@gmail.com', 2),
	('wombat', 'wom123', '56789', 'Pelanggan', 'jl bahagia no 119', '081122334451', 'wombat@gmail.com', 5),
	('kapi', 'kapi123', '23456', 'Pelanggan', 'jl suka no 8', '087654451234', 'kapi@gmail.com', 1),
	('dudu', 'du123', '67890', 'Pelanggan', 'jl duka no 1', '087766557788', 'dudu@gmail.com',13),
	('wombi', 'bi123', '45678', 'Pelanggan', 'jl singa no 90', '08811223344', 'wombi@gmail.com', 10),
	('didi', 'di987', '99880', 'Pelanggan', 'jl pribadi no 7', '088777788881', 'didi@gmailo.com', 8);
	
CREATE TABLE Warna (
    id SERIAL PRIMARY KEY,
    nama VARCHAR(20)
);

INSERT INTO Warna (nama) VALUES
	('hitam'), ('putih'), ('cokelat tua'), ('cokelat muda'), ('silver'), ('hijau');

CREATE TABLE Material (
    id SERIAL PRIMARY KEY,
    nama VARCHAR(30)
);

INSERT INTO Material (nama) VALUES
	('kayu solid'), ('rotan'), ('plastik'),
	('kulit'), ('kayu olahan'), ('stainless steel');

CREATE TABLE Komponen (
    id SERIAL PRIMARY KEY,
    nama VARCHAR(50),
    ukuran VARCHAR(20),
    stok INT,
    harga DOUBLE PRECISION,
	gambar VARCHAR(100)
);

INSERT INTO Komponen (nama, ukuran, stok, harga, gambar) VALUES
	('handle pintu', 'Small (10 cm)', 5, 20000, 'handle pintu.webp'),
	('handle pintu', 'Medium (15 cm)', 7, 25000, 'handle pintu.webp'),
	('handle pintu', 'Large (20 cm)', 3, 30000, 'handle pintu.webp'),
	('engsel', 'Small (10 cm)', 8, 15000, 'engsel.jpg'),
	('engsel', 'Medium (15 cm)', 8, 25000, 'engsel.jpg'),
	('engsel', 'Large (20 cm)', 8, 35000, 'engsel.jpg'),
	('kaki kursi', 'Small (60 cm)', 2, 40000, ''),
	('kaki kursi', 'Medium (70 cm)', 3, 60000, ''),
	('kaki kursi', 'Larga (80 cm)', 2, 80000, ''),
	('sandaran kursi', 'Small (50 cm)', 2, 30000, ''),
	('sandaran kursi', 'Medium (60 cm)', 2, 40000, ''),
	('sandaran kursi', 'Large (70 cm)', 2, 50000, ''),
	('kain pelapis', 'Small (50 cm)', 2, 20000, ''),
	('kain pelapis', 'Medium (60 cm)', 2, 30000, ''),
	('kain pelapis', 'Large (70 cm)', 2, 40000, '');
	
CREATE TABLE Furnitur (
    id SERIAL PRIMARY KEY,
    nama VARCHAR(30),
    ukuran VARCHAR(50),
	stok INT,
    harga DOUBLE PRECISION,
	gambar VARCHAR(100)
);

INSERT INTO Furnitur (nama, ukuran, stok, harga, gambar) VALUES
	('kursi', 'Small (50cm x 60cm)', 4, 100000, 'kursi.jpg'),
	('kursi', 'Medium (60cm x 70cm)', 2, 150000, 'kursi.jpg'),
	('kursi', 'Large (70cm x 80cm)', 3, 180000, 'kursi.jpg'),
	('meja makan', 'Small (120cm x 80cm)', 6, 700000, 'meja makan.webp'),
	('meja makan', 'Medium (150cm x 90cm)', 2, 90000, 'meja makan.webp'),
	('meja makan', 'Large (180cm x 100cm)', 9, 120000, 'meja makan.webp');

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
	idFurnitur int REFERENCES Furnitur (id),
	idKomponen int REFERENCES Komponen (id),
	stok int,
	tanggal date
);

CREATE TABLE KeranjangKomponen (
    idU int REFERENCES pengguna (id) PRIMARY KEY,
	idKomponen int REFERENCES Komponen (id),
	jumlah int
);

select * from keranjangkomponen

CREATE TABLE KeranjangFurnitur (
    idU int REFERENCES pengguna (id) PRIMARY KEY,
	idFurnitur int REFERENCES Furnitur (id),
	jumlah int
);

select * from keranjangfurnitur

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

CREATE VIEW TotalPendapatan AS
SELECT
	SUM(totalHarga)
FROM HitungTotalHarga;
