# Preguntas — Arquitectura en Capas

## 1. ¿Qué es un controlador y de qué se encarga?
Un controlador es la capa que recibe las peticiones HTTP del cliente (como un GET o POST), extrae los datos de la solicitud y delega el trabajo pesado a la capa de servicio, para finalmente devolver una respuesta HTTP al cliente.

## 2. ¿Qué responsabilidad tiene la capa de servicio?
Es el cerebro de la aplicación. Contiene toda la lógica de negocio, realiza cálculos, aplica reglas (como normalizar textos o calcular impuestos) y orquesta la comunicación entre el controlador y el repositorio.

## 3. ¿Qué hace el repositorio y de qué se encarga?
Es la capa dedicada exclusivamente a la persistencia de datos. Se encarga de interactuar con la base de datos (guardar, actualizar, buscar o eliminar registros) sin aplicar ninguna regla de negocio.

## 4. ¿Qué es una entidad y a qué se mapea en la base de datos?
Una entidad es una clase de nuestro código que representa directamente una tabla en la base de datos relacional. Cada instancia de la entidad equivale a una fila, y sus atributos equivalen a las columnas de esa tabla.

## 5. ¿Para qué sirve un DTO y por qué no devolvemos la entidad directamente?
Un DTO (Data Transfer Object) sirve para transportar solo la información necesaria entre el cliente y el servidor. Evitamos devolver la entidad para no exponer información sensible de la base de datos y para mantener separada la estructura interna de lo que consume el cliente.

## 6. ¿Cuál es la diferencia entre un Request y un Response?
El Request es el DTO que modela los datos que el servidor recibe o "acepta" desde el cliente (lo que entra). El Response es el DTO que modela los datos que el servidor "devuelve" al cliente una vez procesada la petición (lo que sale).

## 7. ¿Por qué separamos la aplicación en capas? Menciona una ventaja.
Las separamos para cumplir el Principio de Responsabilidad Única. Una ventaja clave es el mantenimiento: si cambiamos la base de datos, solo modificamos la capa de repositorio sin afectar la lógica de negocio ni los controladores.

## 8. ¿Qué anotación se usa para marcar un controlador REST? ¿Y un servicio?
Para marcar un controlador usamos `@RestController` (que combina `@Controller` y `@ResponseBody`). Para marcar un servicio usamos `@Service`.

## 9. ¿Qué hace @RequestBody en un endpoint?
Toma el cuerpo (body) de la petición HTTP entrante, que usualmente está en formato JSON, y lo deserializa automáticamente transformándolo en un objeto de Kotlin (como nuestro DTO de Request).

## 10. ¿Cuál es el flujo que sigue un request desde que llega hasta que se guarda en la base de datos?
El cliente envía la petición HTTP; el `Controller` la recibe y la pasa al `Service`. El `Service` aplica la lógica de negocio y transforma el DTO en una `Entity`. Finalmente, el `Service` envía la `Entity` al `Repository`, el cual ejecuta la consulta SQL para guardarla en la base de datos.