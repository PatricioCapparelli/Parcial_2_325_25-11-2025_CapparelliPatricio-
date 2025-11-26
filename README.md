# 🎬 Sistema de Gestión de Cine (Persistencia Nativa)

Aplicación de escritorio desarrollada en **Java** para la gestión y venta de entradas de cine.
Este proyecto implementa un sistema de **persistencia de datos mediante Serialización Binaria**, permitiendo el almacenamiento local del estado de la aplicación en archivos (`.ser`) sin necesidad de bases de datos externas.

## 🚀 Características Principales

### 🎨 Interfaz de Usuario (JavaFX)
* **Cartelera Visual Interactiva:** Selección de películas mediante pósters gráficos cargados dinámicamente desde la carpeta de recursos.
* **Mapa de Sala en Tiempo Real:** Visualización gráfica de butacas con estados (Libres 🟩 / Ocupadas 🟥).
* **Diseño Moderno:** Interfaz estilizada con CSS y layouts fluidos (`FlowPane`).

### 🔐 Seguridad & Gestión
* **Encriptación Robusta:** Contraseñas de usuarios protegidas con **BCrypt** (Hasheo + Salting) para evitar almacenamiento de texto plano.
* **Autenticación:** Sistema completo de Login y Registro con validación de credenciales.
* **Panel Administrativo:** Funcionalidad exclusiva para el usuario administrador que permite reiniciar el sistema y liberar todas las salas.

### 💾 Persistencia (Serialización)
* Almacenamiento de objetos complejos (`Cine`, `Sala`, `Cliente`, `Entrada`) en archivos binarios.
* Recuperación automática del estado completo de la aplicación al iniciar.

## 🛠️ Tecnologías Utilizadas
* **Lenguaje:** Java (JDK 21+)
* **GUI:** JavaFX
* **Persistencia:** Java Serialization API (`java.io.Serializable`)
* **Seguridad:** Librería jBCrypt
* **Patrón de Arquitectura:** MVC (Modelo-Vista-Controlador)

## ⚙️ Instalación y Ejecución

Para correr este proyecto en tu máquina local, sigue estos pasos:

1.  **Clonar el repositorio:**
    ```bash
    git clone https://github.com/PatricioCapparelli/Parcial_2_325_25-11-2025_CapparelliPatricio-
    ```

2.  **Verificar Recursos (Assets):**
    ⚠️ **Importante:** Asegúrate de que la carpeta `assets` (que contiene las imágenes `.jpg` o `.png` de las películas) se encuentre en la **raíz del proyecto** (al mismo nivel que la carpeta `src`). Sin esta carpeta, la cartelera no cargará las imágenes.

3.  **Configurar Librerías:**
    Para que el proyecto compile, debes agregar las siguientes librerías al *Classpath* o *Library Path* de tu IDE (NetBeans/IntelliJ):
    * **jBCrypt:** Agregar el archivo `jbcrypt-0.4.jar`.
    * **JavaFX SDK:** Si utilizas JDK 11 o superior, debes agregar las librerías de JavaFX (mínimamente `javafx.controls` y `javafx.fxml`).

4.  **Ejecutar:**
    Corre la clase `Main.java`.
    * *Nota:* Si es la primera vez que lo ejecutas, el sistema detectará la ausencia de datos y creará automáticamente el archivo `cine.ser` con datos de prueba iniciales.

## 👤 Credenciales de Prueba

El sistema genera automáticamente un usuario administrador para pruebas rápidas:

* **Email:** `admin@cine.com`
* **Contraseña:** `1234`

## 📂 Estructura del Proyecto

El código sigue una arquitectura MVC estricta para mantener el orden:

* `src/cine/modelo`: Clases de dominio (`Sala`, `Butaca`, `Cliente`) que implementan la interfaz `Serializable`.
* `src/cine/vista`: Clases de la interfaz gráfica construidas con JavaFX.
* `src/cine/controlador`: Lógica de negocio que conecta la vista con el modelo y gestiona el flujo de datos.
* `src/cine/persistencia`: Clase utilitaria encargada de la lectura y escritura del archivo binario.
* `src/cine/util`: Utilidades de seguridad (PasswordUtil).

---
*Proyecto Académico - UTN*
