function konfirmasiPesanan(){
    const konfirmasi = window.confirm("Apakah Anda yakin ingin membeli produk ini?");
    if(konfirmasi){
        document.querySelector('form').submit();
    }
}

function setAction(actionUrl) {
    const form = document.getElementById('formPesanan');
    form.action = actionUrl;
}