# modulo-service

**Problem**

You are given three integers **x**, **y** and **n**. Your task is to find the **maximum** integer **k** such that **0** ≤**k** ≤**n** 
that **k** **mod** **x**=**y**, where **mod** is modulo operation.

**Execute Service**

  `mvn clean install`

  `mvn spring-boot:run`

**Request Example**

* **GET**

````
    curl --location 'http://localhost:8080/modulo/calculate?integers=2,0,999999999' \
    --header 'Content-Type: application/json' \
    --header 'Cookie: JSESSIONID=3A9505624485E1FA96D5D04A13A4FCCC'
````

* **POST**

````
    curl --location 'http://localhost:8080/modulo/calculate' \
    --header 'Content-Type: application/json' \
    --header 'Cookie: JSESSIONID=3A9505624485E1FA96D5D04A13A4FCCC' \
    --data '{
        "x":2,
        "y":0,
        "n":999999999
    }'
````

**Structure**

| Package     | Description                           |
|-------------|---------------------------------------|
| constants/  | Classes with utilities or auxiliaries |
| controller/ | Controllers                           |
| model/      | Class model layer                     |
| service/    | services layer                        |

---

### Structure

**model**

Class model of each object/table in the database.

**constants**

It contains constants, static methods that can be reused in the project.

### Additional Information

**Validations**

The data validations that the models receive are done through annotations. Ex:

````
    @NotEmpty(message = "The list of integers must not be empty")
    @RequestParam List<Integer> integers
````

**Resilience4j**

The class where the configurations of the resilience4j library are used.

Ruta de la clase: `service/Resilience4jService`

Example:

```
    resilience4JService.executeCalculateModulo(() -> moduloService
    .calculateMaximumK(moduloRequest.getX(), moduloRequest.getY(), moduloRequest.getN()))
```

Where:
> * resilience4jService.executeCalculateModulo: Generated method to assign a custom configuration of the resilience4j library.
> * moduloService.calculateMaximumK: Method to encapsulate in order to execute the configurations made in the resilience4j library.
> * moduloRequest: Method input parameters.

* **Circuit Breaker**

To use this property you must assign the annotation `@CircuitBreaker` also indicating the name of the
configuration in the method that corresponds to encapsulate.

Example:

```
    @CircuitBreaker(name = MODULO_API)
    public <T> T executeCalculateModulo(Supplier<T> operation) {
        return operation.get();
    }
```

* **Rate Limiter**

To use this property you must assign the annotation `@RateLimiter` also indicating the name of the
configuration in the method that corresponds to encapsulate.

Example:

```
    @RateLimiter(name = MODULO_API)
    public <T> T executeCalculateModulo(Supplier<T> operation) {
        return operation.get();
    }
```

* **Bulkhead**

To use this property you must assign the annotation `@Bulkhead` also indicating the name of the
configuration in the method that corresponds to encapsulate.

Example:

``` 
    @Bulkhead(name = MODULO_API)
    public <T> T executeCalculateModulo(Supplier<T> operation) {
        return operation.get();
    }
```

* **Retry**

To use this property you must assign the annotation `@Retry` also indicating the name of the
configuration in the method that corresponds to encapsulate.

Example:

```
    @Retry(name = MODULO_API)
    public <T> T executeCalculateModulo(Supplier<T> operation) {
        return operation.get();
    }
```

**Excepciones**

In addition, the exceptions must be generated using the following classes`TechnicalException`,
a personalized one like `CustomException` generated for this service with an error code
and a message reporting the error. If necessary, you can also
Enter the caught exception.

### Versions

* **1.0.0 (Actual)**