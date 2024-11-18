const dataKomponen = [
    {id: 1, nama: "Handle Pintu", harga: "xxx", stok: "x", image: "assets/handle pintu.webp"},
    {id: 2, nama: "Kaki Meja", harga: "xxx", stok: "x", image: "assets/kaki meja.jpg"},
    {id: 3, nama: "Engsel", harga: "xxx", stok: "x", image: "assets/engsel.jpg"},
    {id: 4, nama: "Komponen", harga: "xxx", stok: "x", image: "assets/"},
    {id: 5, nama: "Komponen", harga: "xxx", stok: "x", image: "assets/"},
    {id: 6, nama: "Komponen", harga: "xxx", stok: "x", image: "assets/"},
    {id: 7, nama: "Komponen", harga: "xxx", stok: "x", image: "assets/"},
    {id: 8, nama: "Komponen", harga: "xxx", stok: "x", image: "assets/"},
    {id: 9, nama: "Komponen", harga: "xxx", stok: "x", image: "assets/"},
    {id: 10, nama: "Komponen", harga: "xxx", stok: "x", image: "assets/"}
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
        const newStok = prompt(`Edit stok untuk ${komponen.nama} (stok saat ini: ${komponen.stok}):`, komponen.stok);
        const newHarga = prompt(`Edit harga untuk ${komponen.nama} (harga saat ini: Rp${komponen.harga}):`, komponen.harga);
        
        //ganti stok, harga dengan nilai yang diinput
        if (newStok !== null) komponen.stok = parseInt(newStok);
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