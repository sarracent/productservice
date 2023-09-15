# Microservicio REST

**Nombre del servicio**

`product-service`

**Puerto**

`8080`

**Objetivo**

Construir una aplicación/servicio que provea una end point rest de consulta tal que:

Acepte como parámetros de entrada: fecha de aplicación, identificador de producto, identificador de cadena.
Devuelva como datos de salida: identificador de producto, identificador de cadena, tarifa a aplicar, fechas de aplicación y precio final a aplicar.

**Execute Service**

`mvn clean install`

`mvn spring-boot:run`

**Ejemplo de peticones al servicio con datos del ejemplo**

* **Test 1: Request at 10:00 AM on Day 14 of Product 35455 for Brand 1 (ZARA)**

````
    curl --location 'http://localhost:8080/api/product?applicationDate=2020-06-14T10:00:00&productId=35455&brandId=1' \
        --header 'Service-Id: price-service' \
        --header 'Content-Type: application/json'
````

* **Test 2: Request at 4:00 PM on Day 14 of Product 35455 for Brand 1 (ZARA)**

````
    curl --location 'http://localhost:8080/api/product?applicationDate=2020-06-14T16:00:00&productId=35455&brandId=1' \
        --header 'Service-Id: price-service' \
        --header 'Content-Type: application/json'
````

* **Test 3: Request at 9:00 PM on Day 14 of Product 35455 for Brand 1 (ZARA)**

````
    curl --location 'http://localhost:8080/api/product?applicationDate=2020-06-14T21:00:00&productId=35455&brandId=1' \
        --header 'Service-Id: price-service' \
        --header 'Content-Type: application/json'
````

* **Test 4: Request at 10:00 AM on Day 15 of Product 35455 for Brand 1 (ZARA)**

````
    curl --location 'http://localhost:8080/api/product?applicationDate=2020-06-15T10:00:00&productId=35455&brandId=1' \
        -H 'service-id: prices-service' \
        -H 'Content-Type: application/json'
````

* **Test 5: Request at 9:00 PM on the 16th of Product 35455 for Brand 1 (ZARA)**

````
    curl --location 'http://localhost:8080/api/product?applicationDate=2020-06-16T21:00:00&productId=35455&brandId=1' \
        -H 'service-id: prices-service' \
        -H 'Content-Type: application/json'
````

**Estructura**

| Package     | Descripcion                                                  |
|-------------|--------------------------------------------------------------|
| commons/    | Clases comunes al proyecto                                   |
| config/     | Configuraciones                                              |
| constants/  | Clases con utilidades o auxiliares                           |
| controller/ | Controladores                                                |
| repository/ | Capa acceso a datos                                          |
| model/      | Capa de modelo de clases                                     |
| service/    | Capa de servicios                                            |


---

### Estructura

**config**

Contiene clase de configuración del servicio

**respository**

Componentes relacionados al acceso de base de datos.


**model**

Modelo de clases de cada objeto/tabla de la base.

**constants**

Contiene constantes, metodos estaticos que pueden ser reutilizados en el proyecto como asi tambien una clase de logueo
estandar pre-configurada.

### Informacion adicional

**Validaciones**

Las validaciones de datos que reciben los modelos se realizan a traves de anotaciones. Ej:

````
	@NotEmpty
	String unStringNoVacio;
	@Size(min = 3)
	String unStringDeAlMenos3Caracteres;
````

Otras validaciones de datos de entrada se realiza mediante las siguientes clases y metodos.

Ruta de la clase: `commons/resolver/CustomHeadersResolver`

Metodo: `public static void validateHeaders(Map<String, String> headersMap)`

Ruta de la clase: `commons/resolver/CustomInputResolver`

Metodo: `public static void validateInput(RechargeOfferRequest rechargeOfferRequest)`

**Resilience4j**

La clase donde se utiliza las configuraciones de la libreria resilience4j.

Ruta de la clase: `commons/resilience4j/Resilience4jService`

Ejemplo:

```
        (resilience4JService.executePrices(() -> priceService.findPricesByParameters(request));
```

Donde:
> * resilience4jService.executePrices: Metodo generado para asignar una configuración personalizada de la libreria resilience4j.
> * priceService.findPricesByParameters: Metodo a encapsular con el fin de ejecutar las configuraciones realizadas en la libreria resilience4j.
> * request: Parámetros de entrada del metodo.

* **Circuit Breaker**

Para utilizar esta propiedad se debe asignar la anotacion `@CircuitBreaker` indicando tambien el nombre de la
configuracion en el metodo que corresponda encapsular.

Ejemplo:

```
    @CircuitBreaker(name = PRODUCT_API)
    public <T> T executeProduct(Supplier<T> operation) {
        return operation.get();
    }
```

* **Rate Limiter**

Para utilizar esta propiedad se debe asignar la anotacion `@RateLimiter` indicando tambien el nombre de la configuracion
en el metodo que corresponda encapsular.

Ejemplo:

```
    @RateLimiter(name = PRODUCT_API)
    public <T> T executeProduct(Supplier<T> operation) {
        return operation.get();
    }
```

* **Bulkhead**

Para utilizar esta propiedad se debe asignar la anotacion `@Bulkhead` indicando tambien el nombre de la configuracion en
el metodo que corresponda encapsular.

Ejemplo:

```
    @Bulkhead(name = PRODUCT_API)
    public <T> T executeProduct(Supplier<T> operation) {
        return operation.get();
    }
```

* **Retry**

Para utilizar esta propiedad se debe asignar la anotacion `@Retry` indicando tambien el nombre de la configuracion en el
metodo que corresponda encapsular.

Ejemplo:

```
    @Retry(name = PRODUCT_API)
    public <T> T executeProduct(Supplier<T> operation) {
        return operation.get();
    }
```

**Excepciones y Log**

Todas las excepciones y los tiempos de ejecución son capturados a traves del logueo por
aspecto.

Ruta de la clase: `commons/aop/LogAspect`

Ademas las excepciones deben generarse utilizando las siguientes clases `BusinessException`, `TechnicalException`
, `InternalException`, `ExternalException`, una personalizada como `CustomException` o `ValidateException` generada para
este servicio con un código de error y un mensaje que informe el error. En el caso que sea necesario tambien se puede
ingresar la excepcion capturada. De esta forma la clase LogAspect podra capturar el error y que el mismo se registre en
el servicio para que luego se envie a ElasticSearch de manera transparente.

* **Error**

El servicio tiene una estructura generica de respuesta para los casos de error, la misma esta compuesta de la siguiente
informacion:

* `resultCode`: Registra el codigo de error/ejecucion.
* `resultMessage`: Registra el mensaje de error/ejecucion.

Ejemplo:

````
{
    "resultCode": "100003",
    "resultMessage": "Error el campo Service-Id es nulo o vacio"
}
````

* **Estructura de Logueo**

La estructura de logueo se define en la clase `LogAspect` en el constructor de la clase.

`LogUtil.initializeStructure(EnumSet.allOf(Logs.Header.class), EnumSet.allOf(Logs.Basic.class));`

Donde los Enums Logs.Header y Logs.Basic, poseen los siguientes valores:

````
    Logs.Header:
        SERVICE_ID;
    Logs.Basic
        OPERATION,
        CODE,
        DESCRIPTION,
        ELAPSED,
        REQUEST,
        RESPONSE;
````

Dando asi por ejemplo esta estructura final de logueo:

`[TRANSACTION_ID=XX | SESSION_ID=XX | SERVICE_ID=XX | CHANNEL_ID=XX | OPERATION=XX | CODE=XX | DESCRIPTION=XX | ELAPSED=XX | REQUEST=XX | RESPONSE=XX]`

* **ElasticSearch**

El envio de los log a ElasticSearch se realiza a traves de la libreria `log4elk-spring-boot`, version `2.1.0`.

### Versiones

* **1.4.0 (Actual)**