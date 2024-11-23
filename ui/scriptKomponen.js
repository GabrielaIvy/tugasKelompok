// Fungsi untuk kembali ke halaman sebelumnya
function goBack() {
    window.history.back();
}

// Fungsi untuk menampilkan modal
function showModal(componentType) {
    const size = document.getElementById('size').value;

    let material, color;
    let price;

    if (componentType === 'handle') {
        material = document.getElementById('handle-material').value;
        color = document.getElementById('handle-color').value;
        price = 100000; // Harga untuk Handle Pintu
    } else if (componentType === 'leg') {
        material = document.getElementById('leg-material').value;
        color = document.getElementById('leg-color').value;
        price = 200000; // Harga untuk Kaki Meja
    } else if (componentType === 'hinge') {
        material = document.getElementById('hinge-material').value;
        color = document.getElementById('hinge-color').value;
        price = 150000; // Harga untuk Engsel
    }

    const details = `
        <strong>Ukuran:</strong> ${size} <br>
        <strong>Bahan:</strong> ${material}, Warna: ${color} <br>
        <strong>Harga:</strong> Rp ${price.toLocaleString()}
    `;
    document.getElementById('order-details').innerHTML = details;

    document.getElementById('modal').style.display = 'flex';
}

// Fungsi untuk menutup modal
function closeModal() {
    document.getElementById('modal').style.display = 'none';
}
