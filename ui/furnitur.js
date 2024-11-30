const dataFurnitur = [
    {id: 1, nama: "Sofa", harga: 300000, stok: 12, image: "../assets/sofa.jpg"},
    {id: 2, nama: "Meja", harga: 200000, stok: 14, image: "../assets/meja makan.webp"},
    {id: 3, nama: "Kursi", harga: 150000, stok: 23, image: "../assets/kursi.jpg"},
    {id: 4, nama: "Furnitur", harga: 0, stok: 0, image: "../assets/"},
    {id: 5, nama: "Furnitur", harga: 0, stok: 0, image: "../assets/"},
    {id: 6, nama: "Furnitur", harga: 0, stok: 0, image: "../assets/"},
    {id: 7, nama: "Furnitur", harga: 0, stok: 0, image: "../assets/"},
    {id: 8, nama: "Furnitur", harga: 0, stok: 0, image: "../assets/"},
    {id: 9, nama: "Furnitur", harga: 0, stok: 0, image: "../assets/"},
    {id: 10, nama: "Furnitur", harga: 0, stok: 0, image: "../assets/"}
];

const listFurnitur = document.getElementById("item-list");
dataFurnitur.forEach(furnitur => {
    const item = document.createElement("div");
    item.classList.add("item");

    item.innerHTML = `
        <img src="${furnitur.image}" alt="${furnitur.nama}">
        <div class="info">
            <h3>${furnitur.nama}</h3>
            <p>Harga: Rp${furnitur.harga}</p>
            <p>Stok: ${furnitur.stok}</p>
        </div>
        <button class="btn-edit" onclick=editFurnitur(${furnitur.id})>Edit</button>
    `;
    listFurnitur.appendChild(item);
});

//fungsi edit furnitur
function editFurnitur(id){
    const furnitur = dataFurnitur.find(p => p.id === id); //cari produk
    if (furnitur) { //produk ditemukan
        //input stok, harga baru
        const newStok = prompt(`Tambah stok untuk ${furnitur.nama} (stok saat ini: ${furnitur.stok}):`, furnitur.stok);
        const newHarga = prompt(`Edit harga untuk ${furnitur.nama} (harga saat ini: Rp${furnitur.harga}):`, furnitur.harga);
        
        //ganti stok, harga dengan nilai yang diinput
        if (newStok !== null) furnitur.stok += parseInt(newStok);
        if (newHarga !== null) furnitur.harga = parseInt(newHarga);

        // Refresh tampilan
        listFurnitur.innerHTML = ""; // Hapus daftar sebelumnya
        dataFurnitur.forEach(produk => {
            const item = document.createElement("div");
            item.classList.add("item");

            item.innerHTML = `
                <img src="${produk.image}" alt="${produk.nama}">
                <div class="info">
                    <h3>${produk.nama}</h3>
                    <p>Harga: Rp${produk.harga}</p>
                    <p>Stok: ${produk.stok}</p>
                </div>
                <button class="btn-edit" onclick="editProduk(${produk.id})">Edit</button>
            `;

            listFurnitur.appendChild(item);
        });
    }
}