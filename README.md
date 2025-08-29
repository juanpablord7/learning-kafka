# Learning Kafka

Learning-Kafka es un proyecto de ejemplo que demuestra la integración de **Spring Boot microservicios** con **Apache Kafka**, usando un sistema de **órdenes y productos**, un **API Gateway** y un **Eureka Server** para la gestión de microservicios.

---

## Arquitectura

El proyecto consta de cuatro componentes principales:

1. **Service Order**: Microservicio encargado de crear órdenes.
2. **Service Product**: Microservicio encargado de gestionar productos y actualizar el stock.
3. **API Gateway**: Exposición de endpoints unificados para los microservicios.
4. **Eureka Server**: Registro y descubrimiento de microservicios.

Además, utiliza **Kafka** para la comunicación asíncrona entre los microservicios:

- Topic: `order`
- El microservicio de órdenes envía mensajes a Kafka cuando se crea una orden.
- El microservicio de productos escucha el topic y actualiza el stock correspondiente.

---

## Modelo de datos

### Product
| Campo    | Tipo    | Descripción                  |
|----------|--------|------------------------------|
| id       | Long   | Identificador del producto   |
| name     | String | Nombre del producto          |
| category | String | Categoría del producto       |
| price    | Long   | Precio del producto          |
| image    | String | URL de la imagen del producto |
| stock    | Long   | Cantidad disponible          |

### Order
| Campo     | Tipo | Descripción                           |
|-----------|------|---------------------------------------|
| id        | Long | Identificador de la orden             |
| productId | Long | Id del producto asociado              |
| price     | Long | Precio del producto en la orden       |
| quantity  | Long | Cantidad de producto en la orden      |

---

## Flujo de la aplicación

1. Se crea un **producto** con stock disponible.
2. Se crea una **orden** asociada a un producto.
3. La orden se envía al **topic Kafka `order`**.
4. El microservicio de productos recibe el mensaje y **reduce el stock** del producto correspondiente según la cantidad de la orden.

---

## Docker

En la raíz del proyecto se encuentra el archivo `docker-compose.yml` que permite levantar:

- **Zookeeper**
- **Kafka Broker**

Solo ejecutando:

```bash
docker-compose up -d
