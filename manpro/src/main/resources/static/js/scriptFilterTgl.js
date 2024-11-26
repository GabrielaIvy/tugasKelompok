//filter tanggal di laporan penjualan
const filterBtn = document.getElementById('filterTgl');

filterBtn.addEventListener('click', function() {
    const fromDate = new Date(document.getElementById('fromDate').value);
    const toDate = new Date(document.getElementById('toDate').value);
    let totalPenjualan = 0;

    const rows = document.querySelectorAll('table tr:not(#tableHeader)');

    rows.forEach(r => {
        const tglPesanan = new Date(r.querySelector("td:nth-child(2)").textContent.trim());
        const curHarga = parseFloat(r.querySelector("td:nth-child(9)").textContent.trim().replace("Rp", ""));
        //cek tanggal
        if(fromDate && toDate){ //ada input filter
            if(tglPesanan >= fromDate && tglPesanan <= toDate){ //ditampilkan
                r.style.display = ""; 
                totalPenjualan += curHarga;
            }else{ //disembunyikan
                r.style.display = "none";
            }
        }else{ //tidak ada input filter, tampilkan semua
            r.style.display = "";
        }
    });

    document.getElementById('totalPenjualan').textContent = `Rp ${totalPenjualan}`;
});