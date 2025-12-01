// MAPA

// crea mapa centrado en Madrid
const map = L.map('map').setView([40.4168, -3.7038], 13);

L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
    maxZoom: 19
}).addTo(map);

let marker;

// al hacer click en el mapa, mover/crear marcador y guardar coordenadas
map.on('click', function(e) {
    const { lat, lng } = e.latlng;

    if (marker) {
        marker.setLatLng(e.latlng);
    } else {
        marker = L.marker(e.latlng).addTo(map);
    }

    document.getElementById('lat').value = lat;
    document.getElementById('lng').value = lng;
});

// INPUT IMAGEN

const inputImagen = document.getElementById('imagenEvento');
const spanNombre = document.getElementById('nombre-imagen');

inputImagen.addEventListener('change', function () {
    if (this.files && this.files.length > 0) {
        spanNombre.textContent = this.files[0].name; // nombre del archivo
    } else {
        spanNombre.textContent = 'Adjuntar foto';    // texto por defecto
    }
});