// Gestionar las Sesiones de los usuarios

/**
 * Verifica si hay un usuario logueado
 * @returns {boolean} true si está logueado, false si no
 */
function isUserLoggedIn() {
    return localStorage.getItem("isLoggedIn") === "true";
}

/**
 * Obtiene los datos del usuario logueado
 * @returns {Object|null} Datos del usuario o null si no está logueado
 */
function getLoggedUser() {
    if (isUserLoggedIn()) {
        const userData = localStorage.getItem("usuario");
        return userData ? JSON.parse(userData) : null;
    }
    return null;
}

/**
 * Cierra la sesión del usuario
 */
function logout() {
    localStorage.removeItem("usuario");
    localStorage.removeItem("isLoggedIn");
    localStorage.removeItem("userId");
    window.location.href = "/login/login.html";
}

/**
 * Redirige a login si no hay usuario logueado
 */
function requireLogin() {
    if (!isUserLoggedIn()) {
        window.location.href = "/login/login.html";
    }
}

/**
 * Actualiza el menú según si el usuario está logueado o no
 */
function updateNavbar() {
    const usuario = getLoggedUser();
    
    // Buscar el enlace de cuenta
    const cuentaLink = document.getElementById("cuentaLink");
    
    if (usuario && cuentaLink) {
        // Si está logueado, mostrar nombre del usuario y redirigir a área personal
        cuentaLink.textContent = `Hola, ${usuario.nombre}`;
        cuentaLink.href = "/area_personal/usuario.html";
    } else if (cuentaLink) {
        // Si no está logueado, mostrar "Mi cuenta" y redirigir a login
        cuentaLink.textContent = "Mi cuenta";
        cuentaLink.href = "/login/login.html";
    }
}

// Ejecutar al cargar la página
document.addEventListener("DOMContentLoaded", () => {
    updateNavbar();
});
