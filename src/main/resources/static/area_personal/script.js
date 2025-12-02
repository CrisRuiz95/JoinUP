// Script para manejar la navegación de pestañas en el área personal******************************
    const tabs = document.querySelectorAll(".menu-item");
    const pantallas = document.querySelectorAll(".pantalla");

    tabs.forEach((tab, index) => {
        tab.addEventListener("click", () => {

            tabs.forEach(btn => btn.classList.remove("active"));
            tab.classList.add("active");
   
            pantallas.forEach(p => p.classList.remove("visible"));

            switch(index) {
                case 0:
                    document.getElementById("pantalla-info").classList.add("visible");
                    break;
                case 1:
                    document.getElementById("pantalla-deseados").classList.add("visible");
                    break;                      
                case 2:
                    document.getElementById("pantalla-ajustes").classList.add("visible");
                    break;
            }
        });
    });

//************** Script para cambiar la foto de perfil ***************************************
const avatarImg = document.querySelector(".avatar");
const avatarInput = document.getElementById("avatar-input");
const editarIcono = document.querySelector(".editar-icono");
const borrarIcono = document.querySelector(".borrar-foto");

// Guardar la imagen original
const originalAvatar = avatarImg.src;

editarIcono.addEventListener("click", () => {
    avatarInput.click();
});


avatarInput.addEventListener("change", function () {
    const file = this.files[0];

    if (file) {
        const reader = new FileReader();
        reader.onload = function (e) {
            avatarImg.src = e.target.result; // Cambia la foto
        };
        reader.readAsDataURL(file);
    }
});

// ekiminar la foto de perfil y restaurar la original
borrarIcono.addEventListener("click", () => {
    avatarImg.src = originalAvatar;  
    avatarInput.value = "";         
});


//JS para los intereses
const intereses = document.querySelectorAll('label.interes');
let seleccionados = []; // Guardará las etiquetas seleccionadas

intereses.forEach(interes => {
    interes.addEventListener('click', () => {

        // Si ya está seleccionado lo podemos quitar
        if (interes.classList.contains('selected')) {
            interes.classList.remove('selected');
            seleccionados = seleccionados.filter(i => i !== interes);
            return;
        }

        // Si ya hay 3 seleccionados, eliminamos el primero
        if (seleccionados.length >= 3) {
            const primero = seleccionados.shift(); 
            primero.classList.remove('selected');
        }

        // Agregamos el nuevo interés
        interes.classList.add('selected');
        seleccionados.push(interes);
    });
});
