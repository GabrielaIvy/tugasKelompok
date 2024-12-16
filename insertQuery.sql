--furnitur
INSERT INTO Furnitur (nama, ukuran, stok, harga, gambar) VALUES
('Sofa', 'Small (120cm x 80cm)', 7, 300000, 'sofa.jpg'), --bahan kayu logam
('Sofa', 'Medium (150cm x 90cm)', 2, 450000, 'sofa.jpg'), -- coklat hitam 13 kain pelapis
('Sofa', 'Large (180cm x 100cm)', 5, 600000, 'sofa.jpg'); --kapas kulit, abu-abu biru

--komponen
UPDATE Komponen
SET gambar = 'kaki kursi.jpg'
WHERE nama = 'kaki kursi';

UPDATE Komponen
SET gambar = 'sandaran kursi.avif'
WHERE nama = 'sandaran kursi';

UPDATE Komponen
SET gambar = 'kain pelapis.webp'
WHERE nama = 'kain pelapis';

INSERT INTO Komponen (nama, ukuran, stok, harga, gambar) VALUES 
('Kaki Meja', 'Pendek (40cm)', 8, 45000, 'kaki meja.jpg'), --kayu logam
('Kaki Meja', 'Sedang (60cm)', 4, 50000, 'kaki meja.jpg'), --coklat hitam
('Kaki Meja', 'Tinggi (80cm)', 6, 65000, 'kaki meja.jpg');

--komponen furnitur
INSERT INTO KomponenFurnitur VALUES
(4, 16), (5, 17), (6, 18),
(7, 13), (8, 14), (9, 15);
--4 5 6 meja makan(16 17 18), 7 8 9 sofa (13 14 15)

--material
INSERT INTO Material (nama) VALUES ('logam');

INSERT INTO Warna (nama) VALUES ('emas');

--komponenmaterialwarna (ini random)
INSERT INTO KomponenMaterialWarna VALUES 
(4, 7, 5), (5, 7, 7), (6, 6, 5),
(8, 7, 5), (9, 1, 4),
(11,4, 2), (12, 4, 6),
(13, 4, 4), (14, 4, 1), (15, 4, 1),
(16, 7, 5), (17, 5, 4), (18, 7, 3);
--4 5 6(matz:5 6 7/col: 7 5)/ 8 9 (1 7, 3 4 5)/ 11 12 (4, 1 2 4)/ 13 14 15 (4, 1 4)/ 16 17 18(1 5 7, 3 4 5)

--keranjangfurnitur


--kerangjangkomponen


--transaksi
INSERT INTO Transaksi (idFurnitur, idKomponen, stok, tanggal)
SELECT
    f.id AS idFurnitur,
    c.id AS idKomponen,
    c.stok AS stok,
    ps.tglPesanan AS tanggal
FROM
    Pesanan ps
LEFT JOIN
    PesanFurnitur pf ON ps.idPesanan = pf.idPesanan
LEFT JOIN
    Furnitur f ON pf.idFurnitur = f.id
LEFT JOIN
    KomponenFurnitur cf ON f.id = cf.idFurnitur
LEFT JOIN
    Komponen c ON cf.idKomponen = c.id;