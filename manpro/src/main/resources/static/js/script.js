//filter kelurahan berdasarkan kecamatan
const kecamatan = document.getElementById('kecamatan');

kecamatan.addEventListener('change', function(){
    const idKecamatan = this.value;
    const kelurahanDropDown = document.getElementById('kelurahan');

    kelurahanDropDown.innerHTML = '<option value="" disabled selected>Pilih kelurahan</option>';

    //ambil kelurahan berdasarkan idKecamatan
    fetch('/furniturKustom/getKelurahanByKecamatan?idKecamatan=' + idKecamatan)
        .then(response => response.json())
        .then(data =>{
            data.forEach(function(kelurahan){ //buat option untuk seluruh data
                const option = document.createElement('option');
                option.value = kelurahan.id;
                option.text = kelurahan.nama;
                kelurahanDropDown.appendChild(option);
            });
        });
});