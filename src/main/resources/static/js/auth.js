// Función para mostrar alertas personalizadas
function showAlert(message, type = 'success') {
    // Obtener o crear el contenedor de alertas
    let alertContainer = document.getElementById('alert-container');
    
    // Si no existe el contenedor, crearlo
    if (!alertContainer) {
        alertContainer = document.createElement('div');
        alertContainer.id = 'alert-container';
        alertContainer.className = 'alert-container';
        document.body.appendChild(alertContainer);
    }
    
    const alert = document.createElement('div');
    
    // Configurar clases según el tipo
    const typeClasses = {
        success: 'alert-success',
        error: 'alert-error',
        warning: 'alert-warning'
    };
    
    // Configurar iconos según el tipo
    const icons = {
        success: `<svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7"></path>
                  </svg>`,
        error: `<svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12"></path>
                  </svg>`,
        warning: `<svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 9v2m0 4h.01m-6.938 4h13.856c1.54 0 2.502-1.667 1.732-3L13.732 4c-.77-1.333-2.694-1.333-3.464 0L3.34 16c-.77 1.333.192 3 1.732 3z"></path>
                  </svg>`
    };
    
    // Configurar títulos según el tipo
    const titles = {
        success: '¡Éxito!',
        error: 'Error',
        warning: 'Advertencia'
    };
    
    alert.className = `alert ${typeClasses[type]}`;
    alert.innerHTML = `
        ${icons[type]}
        <div>
            <p class="font-semibold">${titles[type]}</p>
            <p class="text-sm">${message}</p>
        </div>
    `;
    
    alertContainer.appendChild(alert);
    
    // Eliminar la alerta después de un tiempo
    setTimeout(() => {
        alert.style.animation = 'fadeOut 0.3s ease-out forwards';
        setTimeout(() => {
            if (alertContainer.contains(alert)) {
                alertContainer.removeChild(alert);
            }
        }, 300);
    }, type === 'success' ? 2000 : 3000);
}

// Función para mostrar mensajes de éxito
function showSuccess(message) {
    showAlert(message, 'success');
}

// Función para mostrar errores
function showError(message) {
    showAlert(message, 'error');
}

// Función para mostrar advertencias
function showWarning(message) {
    showAlert(message, 'warning');
}

// Función para validar email
function isValidEmail(email) {
    const re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return re.test(email);
}

// Función para validar contraseña
function isValidPassword(password) {
    return password.length >= 6;
}

// Función para manejar el inicio de sesión
async function handleLogin(event) {
    event.preventDefault();
    
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;

    // Validaciones
    if (!email || !password) {
        showWarning('Por favor complete todos los campos');
        return;
    }

    if (!isValidEmail(email)) {
        showWarning('Por favor ingrese un email válido');
        return;
    }

    try {
        const response = await fetch('/api/auth/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            },
            body: JSON.stringify({ 
                email: email, 
                contrasena: password 
            })
        });

        let data;
        try {
            const contentType = response.headers.get('content-type');
            if (contentType && contentType.includes('application/json')) {
                data = await response.json();
            } else {
                throw new Error('Respuesta no válida del servidor');
            }
        } catch (e) {
            console.error('Error al parsear respuesta:', e);
            showError('Error en la respuesta del servidor');
            return;
        }

        if (response.ok && data) {
            // Guardar datos del usuario en localStorage
            localStorage.setItem('isLoggedIn', 'true');
            localStorage.setItem('user', JSON.stringify({
                nombre: data.nombre,
                email: data.email,
                role: data.rol
            }));
            localStorage.setItem('userRole', data.rol);
            
            showSuccess('¡Bienvenido ' + data.nombre + '!');
            
            // Redirigir según el rol del usuario
            setTimeout(() => {
                let redirectUrl = '/dashboard';
                switch(data.rol) {
                    case 'ADMIN':
                        redirectUrl = '/admin/dashboard';
                        break;
                    case 'INSTITUTO':
                        redirectUrl = '/instituto/dashboard';
                        break;
                    case 'ASPIRANTE':
                        redirectUrl = '/aspirante/dashboard';
                        break;
                }
                window.location.replace(redirectUrl);
            }, 2000);
        } else {
            if (response.status === 400) {
                showError('Credenciales inválidas. Por favor, verifique su email y contraseña.');
            } else if (response.status === 401) {
                showError('No autorizado. Por favor, inicie sesión nuevamente.');
            } else {
                showError(data?.message || 'Error al iniciar sesión. Por favor, intente nuevamente.');
            }
        }
    } catch (error) {
        console.error('Error:', error);
        showError('Error al conectar con el servidor. Por favor, intente nuevamente más tarde.');
    }
}

// Función para manejar el registro
async function handleRegister(event) {
    event.preventDefault();
    
    const nombre = document.getElementById('nombre').value;
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;
    const confirmPassword = document.getElementById('confirmPassword').value;

    // Validaciones
    if (!nombre || !email || !password || !confirmPassword) {
        showWarning('Por favor complete todos los campos');
        return;
    }

    if (!isValidEmail(email)) {
        showWarning('Por favor ingrese un email válido');
        return;
    }

    if (!isValidPassword(password)) {
        showWarning('La contraseña debe tener al menos 6 caracteres');
        return;
    }

    if (password !== confirmPassword) {
        showWarning('Las contraseñas no coinciden');
        return;
    }

    try {
        const response = await fetch('/api/auth/register', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            },
            body: JSON.stringify({
                nombre: nombre,
                email: email,
                contrasena: password,
                rol: 'ASPIRANTE'
            })
        });

        let data;
        try {
            data = await response.json();
        } catch (e) {
            console.error('Error al parsear respuesta:', e);
            showError('Error en la respuesta del servidor');
            return;
        }

        if (response.ok && data) {
            // Guardar datos del usuario en localStorage
            localStorage.setItem('isLoggedIn', 'true');
            localStorage.setItem('user', JSON.stringify({
                nombre: data.nombre,
                email: data.email,
                role: data.rol
            }));
            localStorage.setItem('userRole', data.rol);
            
            showSuccess('¡Registro exitoso! Bienvenido ' + data.nombre);
            
            // Redirigir al dashboard de aspirante
            setTimeout(() => {
                window.location.replace('/aspirante/dashboard');
            }, 2000);
        } else {
            if (response.status === 400) {
                if (data?.message?.includes('email')) {
                    showError('Este email ya está registrado. Por favor, use otro email o inicie sesión.');
                } else {
                    showError('Datos inválidos. Por favor, verifique la información ingresada.');
                }
            } else {
                showError(data?.message || 'Error al registrar usuario. Por favor, intente nuevamente.');
            }
        }
    } catch (error) {
        console.error('Error:', error);
        showError('Error al conectar con el servidor. Por favor, intente nuevamente más tarde.');
    }
}

// Función para verificar si el usuario está logueado
function checkAuth() {
    const isLoggedIn = localStorage.getItem('isLoggedIn');
    const userRole = localStorage.getItem('userRole');
    
    if (isLoggedIn === 'true' && userRole) {
        // Redirigir según el rol si está en páginas de auth
        if (window.location.pathname.includes('/auth/')) {
            switch(userRole) {
                case 'ADMIN':
                    window.location.href = '/admin/dashboard';
                    break;
                case 'INSTITUTO':
                    window.location.href = '/instituto/dashboard';
                    break;
                case 'ASPIRANTE':
                    window.location.href = '/aspirante/dashboard';
                    break;
                default:
                    window.location.href = '/dashboard';
            }
        }
    }
}

// Función para cerrar sesión
function logout() {
    localStorage.removeItem('isLoggedIn');
    localStorage.removeItem('user');
    localStorage.removeItem('userRole');
    window.location.href = '/auth/login';
}

// Agregar event listeners cuando el DOM esté cargado
document.addEventListener('DOMContentLoaded', () => {
    const loginForm = document.getElementById('login-form');
    const registerForm = document.getElementById('register-form');

    if (loginForm) {
        loginForm.addEventListener('submit', handleLogin);
    }

    if (registerForm) {
        registerForm.addEventListener('submit', handleRegister);
    }

    // Verificar autenticación
    checkAuth();
}); 