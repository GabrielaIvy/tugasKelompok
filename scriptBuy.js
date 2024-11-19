// Fungsi untuk kembali ke halaman sebelumnya
function goBack() {
    window.history.back();
}

// Fungsi untuk menampilkan modal
function showModal(productType) {
    let size = document.getElementById('size').value;
    let frameMaterial, frameColor, fabricMaterial, fabricColor;

    // Menyesuaikan dengan elemen-elemen yang ada berdasarkan jenis produk
    if (productType === 'sofa') {
        frameMaterial = document.getElementById('frame-material').value;
        frameColor = document.getElementById('frame-color').value;
        fabricMaterial = document.getElementById('fabric-material').value;
        fabricColor = document.getElementById('fabric-color').value;
    } else if (productType === 'kursi') {
        frameMaterial = document.getElementById('leg-material').value;
        frameColor = document.getElementById('leg-color').value;
        fabricMaterial = document.getElementById('fabric-material').value;
        fabricColor = document.getElementById('fabric-color').value;
    } else if (productType === 'meja') {
        frameMaterial = document.getElementById('table-frame-material').value;
        frameColor = document.getElementById('table-frame-color').value;
        fabricMaterial = 'N/A'; // Meja tidak memiliki kain pelapis
        fabricColor = 'N/A';
    }

    const details = `
        <strong>Ukuran:</strong> ${size} <br>
        <strong>Bahan Kerangka:</strong> ${frameMaterial}, Warna: ${frameColor} <br>
        <strong>Bahan Kain Pelapis:</strong> ${fabricMaterial}, Warna: ${fabricColor} <br>
        <strong>Harga:</strong> Rp 5.000.000
    `;
    document.getElementById('order-details').innerHTML = details;

    document.getElementById('modal').style.display = 'flex';
}

// Fungsi untuk menutup modal
function closeModal() {
    document.getElementById('modal').style.display = 'none';
}
