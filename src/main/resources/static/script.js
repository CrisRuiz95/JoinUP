const imagenes = [
   "imagenes/la-gente-surfeando-en-brasil.jpg",
   "imagenes/grupo-de-amigos-comiendo-en-restaurante.jpg",
   "imagenes/Equipo-futbol.jpg",
   "imagenes/Group-of-Friends-having-Fun.jpg"
]
 
const imgEvento = document.getElementById("imgEvento");
const botonAnterior = document.getElementById("anterior");
const botonSiguiente = document.getElementById("siguiente");

let indice=0; 

function mostrarDestino(){
imgEvento.src = imagenes[indice];
}
botonAnterior.addEventListener ("click", function() {
    indice = (indice - 1 + imagenes.length) % imagenes.length;
   mostrarDestino();
});
botonSiguiente.addEventListener ("click",  function() {
    indice = (indice + 1) % imagenes.length;
   mostrarDestino();
});
//Poner el incio
mostrarDestino();

/* Código para el menú */

var posPreviaScroll = document.documentElement.scrollTop 

window.onscroll = function() {EsconderMostrarMenu()};

function EsconderMostrarMenu(){

    var posActualScroll = document.documentElement.scrollTop;

    if (posPreviaScroll>posActualScroll){
        //Cuando estamos subiendo mostramos el menú
        document.getElementById("navbar").style.top = "0";
    }
    else {
        //Cuando estamos bajando escondemos el menú (120 es la altura del menú)
        document.getElementById("navbar").style.top = "-120px";
    }

    posPreviaScroll= posActualScroll;
}

/* BOTON DE ELIMINAR ARCHIVO */ 
const inputArchivo = document.getElementById("archivo");
  const botonBorrar = document.getElementById("borrar-archivo");

  botonBorrar.addEventListener("click", () => {
    inputArchivo.value = ""; // Limpia el archivo seleccionado
  });