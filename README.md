# MeliChallenge

**MeliChallenge** es una aplicación Android que permite buscar productos, listar resultados y ver detalles de productos utilizando la API de MercadoLibre.

## Funcionalidades

- **Conexión con Firebase**: La app está conectada a Firebase para la distribución de versiones a testers y la gestión de datos.
- **CI/CD con GitHub Actions**: Este proyecto utiliza GitHub Actions para la integración y entrega continua (CI/CD). Con cada push en la rama `develop`, la aplicación se construye y distribuye automáticamente a Firebase.

## Tecnologías utilizadas

Este proyecto hace uso de varias tecnologías y dependencias que incluyen:

- **Firebase**: Para la distribución de versiones a testers.
- **GitHub Actions**: Para el proceso automatizado de CI/CD.
- **Kotlin**: Lenguaje de programación principal.
- **Jetpack Compose**: Framework para construir interfaces de usuario modernas.
- **Hilt**: Dependencia para la inyección de dependencias.
- **Retrofit**: Librería para hacer solicitudes HTTP a la API de MercadoLibre.
- **Coil**: Librería para cargar imágenes en Compose.
- **JUnit y Mockito**: Para pruebas unitarias y mocks.
- **Timber**: Librería para logging.

Dependencias principales:
- `androidx.compose.ui`
- `androidx.lifecycle:lifecycle-runtime-ktx`
- `androidx.activity:activity-compose`
- `com.google.dagger:hilt-android`
- `com.squareup.retrofit2:retrofit`
- `io.coil-kt:coil-compose`
- `org.jetbrains.kotlinx:kotlinx-serialization-json`

## Requisitos previos

Para ejecutar este proyecto, asegúrate de tener instalada la última versión de **Android Studio**.

## Instalación

1. Clona el proyecto desde GitHub:

   ```bash
   git clone https://github.com/carlospolo09/MeliChallenge.git
2. Abre el proyecto en Android Studio.
3. En Android Studio, selecciona la variable de compilación adecuada (usualmente main).
4. Ejecuta la app directamente desde Android Studio, en un dispositivo o emulador Android.

## Uso

Inicia la aplicación.
Ingresa un texto en el campo de búsqueda para encontrar productos.
Selecciona un producto para ver los detalles.
