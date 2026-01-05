# Hikvision Access Control Backend

## 📌 Descripción
Backend del prototipo de **Gestión, Control y Monitoreo de Terminales Faciales Hikvision**, desarrollado como parte del proyecto **PISIP**.

El sistema centraliza la captura, procesamiento y trazabilidad de eventos de acceso generados por terminales faciales **Hikvision DS-K1T343MX**, operando en infraestructura local segura.

## 🏗️ Arquitectura
El proyecto sigue los principios de:
- Clean Architecture
- Domain Driven Design (DDD)
- API REST con Spring Boot

Capas principales:
- Dominio
- Aplicación
- Infraestructura
- Presentación

## 🧰 Stack Tecnológico
- Java 17
- Spring Boot
- Spring Data JPA
- Spring Security
- PostgreSQL
- Flyway
- Lombok
- Resilience4j

## 🔐 Dispositivos Integrados
- Hikvision DS-K1T343MX (Outdoor)
- Integración mediante ISAPI / SDK Hikvision

## 📂 Estructura Base

```text
src/main/java
└── com.uisrael.hikvision.backend
    ├── Application.java
    │
    ├── aplicacion
    │   ├── casosuso
    │   │   ├── entradas
    │   │   └── impl
    │   ├── dto
    │   │   ├── request
    │   │   └── response
    │   ├── excepciones
    │   └── mapeadores
    │
    ├── dominio
    │   ├── entidades
    │   ├── enums
    │   ├── excepciones
    │   └── puertos
    │       ├── integraciones
    │       └── repositorio
    │
    ├── infraestructura
    │   ├── configuracion
    │   │   └── seguridad
    │   └── persistencia
    │       ├── adaptadores
    │       ├── integraciones
    │       │   └── hikvision
    │       │       ├── adaptador
    │       │       ├── cliente
    │       │       └── dto
    │       ├── jpa
    │       │   ├── entidades
    │       │   └── repositorios
    │       └── mapeadores
    │
    └── presentacion
        ├── controladores
        ├── filtros
        └── handlers
```

Estructura (Clean Architecture + DDD)

Esta organización aplica los principios de **Clean Architecture** y **Domain-Driven Design (DDD)** para separar el sistema por responsabilidades y reducir el acoplamiento entre capas, facilitando el mantenimiento, la escalabilidad y la comprensión del proyecto.

#### dominio/
Contiene el núcleo del negocio del sistema. Incluye las entidades del dominio, reglas de negocio, enumeraciones y contratos (puertos) que definen cómo el dominio se comunica con el exterior.  
Esta capa no depende de frameworks, librerías externas ni tecnologías específicas como Spring o JPA.  
Su objetivo es que la lógica de negocio sea estable, reutilizable y protegida frente a cambios tecnológicos.

#### aplicacion/
Define los casos de uso del sistema, es decir, las acciones que el negocio necesita ejecutar. Contiene los servicios de aplicación, DTOs de entrada y salida, mapeadores y excepciones propias de la lógica de aplicación.  
Esta capa orquesta el flujo de la aplicación sin conocer los detalles de infraestructura.  
Su objetivo es controlar la lógica de aplicación, como la creación de usuarios, el registro de eventos o la consulta de información.

#### infraestructura/
Implementa los detalles técnicos del sistema, como la persistencia con JPA, los adaptadores de repositorios, la configuración de seguridad y la integración con los dispositivos Hikvision mediante ISAPI/SDK.  
En esta capa residen los frameworks, librerías y dependencias externas.  
Su objetivo es conectar el dominio con el mundo real (base de datos, red, dispositivos externos) a través de adaptadores.

#### presentacion/
Expone el sistema hacia el exterior mediante una API REST. Incluye los controladores, filtros y manejadores globales de errores.  
Esta capa se encarga exclusivamente de la gestión HTTP (request/response) y la validación básica de las solicitudes.  
Su objetivo es mantener los endpoints limpios, sin lógica de negocio, delegando el procesamiento a la capa de aplicación.

## 🚀 Estado del Proyecto
Fase inicial:
- Estructura del proyecto
- Arquitectura definida
- Listo para desarrollo por historias de usuario

---

Proyecto académico – Universidad Israel