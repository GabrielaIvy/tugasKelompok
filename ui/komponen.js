const dataKomponen = [
    {id: 1, nama: "Handle Pintu", harga: 50000, stok: 13, image: "../assets/handle pintu.webp"},
    {id: 2, nama: "Kaki Meja", harga: 65000, stok: 24, image: "../assets/kaki meja.jpg"},
    {id: 3, nama: "Engsel", harga: 15000, stok: 12, image: "../assets/engsel.jpg"},
    {id: 4, nama: "Komponen", harga: "xxx", stok: "x", image: "../assets/"},
    {id: 5, nama: "Komponen", harga: "xxx", stok: "x", image: "../assets/"},
    {id: 6, nama: "Komponen", harga: "xxx", stok: "x", image: "../assets/"},
    {id: 7, nama: "Komponen", harga: "xxx", stok: "x", image: "../assets/"},
    {id: 8, nama: "Komponen", harga: "xxx", stok: "x", image: "../assets/"},
    {id: 9, nama: "Komponen", harga: "xxx", stok: "x", image: "../assets/"},
    {id: 10, nama: "Komponen", harga: "xxx", stok: "x", image: "../assets/"}
];

const listKomponen = document.getElementById("item-list");
dataKomponen.forEach(komponen => {
    const item = document.createElement("div");
    item.classList.add("item");

    item.innerHTML = `
        <img src="${komponen.image}" alt="${komponen.nama}">
        <div class="info">
            <h3>${komponen.nama}</h3>
            <p>Harga: Rp${komponen.harga}</p>
            <p>Stok: ${komponen.stok}</p>
        </div>
        <button class="btn-edit" onclick="editKomponen(${komponen.id})">Edit</button>
    `;

    listKomponen.appendChild(item);
});

//fungsi edit komponen
function editKomponen(id){
    const komponen = dataKomponen.find(p => p.id === id);
    if (komponen){ //komponen ditemukan
        //input stok, harga baru
        const newStok = prompt(`Tambah stok untuk ${komponen.nama} (stok saat ini: ${komponen.stok}):`, komponen.stok);
        const newHarga = prompt(`Edit harga untuk ${komponen.nama} (harga saat ini: Rp${komponen.harga}):`, komponen.harga);
        
        //ganti stok, harga dengan nilai yang diinput
        if (newStok !== null) komponen.stok += parseInt(newStok);
        if (newHarga !== null) komponen.harga = parseInt(newHarga);

        // Refresh tampilan
        listKomponen.innerHTML = ""; // Hapus daftar sebelumnya
        dataKomponen.forEach(komponen => {
            const item = document.createElement("div");
            item.classList.add("item");

            item.innerHTML = `
                <img src="${komponen.image}" alt="${komponen.nama}">
                <div class="info">
                    <h3>${komponen.nama}</h3>
                    <p>Harga: Rp${komponen.harga}</p>
                    <p>Stok: ${komponen.stok}</p>
                </div>
                <button class="btn-edit" onclick="editKomponen(${komponen.id})">Edit</button>
            `;

            listKomponen.appendChild(item);
        });
    }
}