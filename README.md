# Introducción

**WeatherForecast** es un ejercicio similar a la [Weather kata](https://github.com/CodiumTeam/weather-kata) de [Codium Team](https://www.codium.team).

Se trata de una clase con un método público que devuelve la previsión del tiempo de una ciudad en una fecha concreta.

Para ello, esta clase utiliza una API externa (requiere conexión a internet): [www.metaweather.com](https://www.metaweather.com) 

Ejemplo:

```java
WeatherForecast weatherForecast = new WeatherForecast();
weatherForecast.getCityWeather("Madrid", new Date());
```


# Ejercicio

El ejercicio consiste en **refactorizar** el código para hacerlo más **mantenible**, ya que el código existente, aunque **funciona**, es muy difícil de entender. 
  
Para ello se pueden realizar múltiples modificaciones siempre que se mantenga el API público. Ejemplos de modificaciones: incluir tests automáticos, extraer métodos, renombrar variables, modificar tipos de datos, crear nuevas clases, añadir librerías, etc. 


# Documentación

La solución debería contener un fichero README donde se respondan estas preguntas:
- ¿Qué has empezado implementando y por qué?
  Primero de todo, he debugueado el código para entender lo que hacía, luego los tests, para poder realizar la TDD y saber cuando he cumplido los requisitos del código.
  y después he cambiado los Dates a new Locale Date para evitar usar código deprecated y luego he extraído la lógica que tenía que ver con fechas y la he implementado en una clase específica para eso.
- ¿Qué problemas te has encontrado al implementar los tests y cómo los has solventado?
  He tenido problemas para mockear la llamada http, en una he podido extraer el método y pasarle solo el response pero en la otra no he logrado mockear la llamada en si.
- ¿Qué componentes has creado y por qué?
  He creado la clase Time, y su clase Time tests para añadir allí todo el código que tenía relación con el tiempo para mantener una alta cohesíon, luego he creado una excepción personalizada para saber de dónde viene el error.
- Si has utilizado dependencias externas, ¿por qué as escogido esas dependencias?
  No he incluído ninguna dependencia más.
- ¿Has utilizado  streams, lambdas y optionals de Java 8? ¿Qué te parece la programación funcional?
  Si, me parece que es una programación mas limpia y fácil de entender ya que el código se encapsula en pequeñas funciones haciendo más fácil la compresión y el debugear
- ¿Qué piensas del rendimiento de la aplicación? 
  Viendo el test que he realizado dónde se envian 100 peticiones y estas han tardado menos en proporción que el test que el test individual, diría que es bueno ya que el test individual ha tardado casi 2s mientras que el de las 100 peticiones ha tardado 100s como se puede observar en la captura añadida.
- ¿Qué harías para mejorar el rendimiento si esta aplicación fuera a recibir al menos 100 peticiones por segundo?
  El uso de multihilos, ya que dos o más peticiones diferentes se ejecutarían a la vez, por lo tanto acabarían antes.Lo que esto llevaría un coste adicional ya que necesitamos mas recursos (memória y cpu) de la máquina que lo ejecuta.
- ¿Cuánto tiempo has invertido para implementar la solución? 
  Aproximadamente unas 8 horas, ya que llevaba un tiempo sin usar java y he tenido que buscar información de según que cosas.
- ¿Crees que en un escenario real valdría la pena dedicar tiempo a realizar esta refactorización?
  Depende, si es un código que funciona y no ha dado ningún error durante un tiempo y no requiere que se optimice yo no haría el refactor. En cambio si es una de las condiciones anteriormente citadas haría el refactor para evitar perder tiempo en futuras actualizaciones o aprovechar el cambio para hacer el refactor..


# A tener en cuenta

- Se valorará positivamente el uso de TDD, se revisarán los commits para comprobar su uso.
- Se valorará positivamente la aplicación de los principios SOLID y "código limpio".
- La aplicación ya tiene un API público: un método público que devuelve el tiempo en un String. No es necesario crear nuevas interfaces: REST, HTML, GraphQL, ni nada por el estilo.


# Entrega

La solución se puede subir a un repositorio de código público como [github](https://github.com/). 

Otra opción sería comprimir la solución (incluyendo el directorio .git) en un fichero .zip/.tar.gz y enviarlo por email.
