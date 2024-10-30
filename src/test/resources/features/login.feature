#language: es

Característica: Login

  Antecedentes:
    Dado que el usuario se encuentre en la pagina web

  Escenario: Login exitoso
    Cuando seleccione Log In e ingrese el user con la clave


  @ScenarioLogin
  Esquema del escenario: Inicio de sesion: <Caso de prueba>
    Cuando "<Cuando>": "<Usuario>" y "<Clave>".
    Entonces "<Entonces>""<Status>"

    Ejemplos:
      | Caso de prueba | Usuario | Clave | Cuando | Entonces | Status |
##@externaldata@./src/main/resources/data/DatosExcel.xlsx@DatosLogin
|CP1 - Login exitoso|daniel58|daniel58|ingrese usuario y clave correctas|el usuario visualizara un mensaje de login exitoso|true|
|CP2 - Login fallido por contraseña incorrecta|daniel58|abc|ingrese usuario correcto y clave incorrecta|el usuario visualizara un mensaje de excepcion de contraseña incorrecta|false|
|CP3 - Login fallido por usuario incorrecto|daniel10|daniel58|ingrese usuario incorrecto y clave correcta|el usuario visualizara un mensaje de excepcion de usuario incorrecto|false|
|CP4 - Login fallido por contraseña vacia|daniel57||ingrese usuario correcto y clave vacia|el usuario visualizara un mensaje de excepcion del campo contraseña vacio|false|
|CP5 - Login fallido por usuario vacio||daniel58|ingrese usuario vacio y clave correcta|el usuario visualizara un mensaje de excepcion del campo usuario vacio|false|
|CP5 - Login fallido por usuario y contraseña vacias|||ingrese usuario y clave vacias|el usuario visualizara un mensaje de excepcion del campo usuario o contraseña vacia|false|
|CP7 - Login fallido por usuario y contraseña incorrecta|ghfg|fggh|ingrese usuario y clave incorrectas|el usuario visualizara un mensaje de excepcion de usuario o contraseña incorrecta|false|
