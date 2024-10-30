#language: es

Característica: Registro formulario de contacto

  Antecedentes:
    Dado que el usuario se encuentre en la pagina web
    Cuando seleccione Log In e ingrese el user con la clave

  Esquema del escenario: Registrar formulario: <Caso de prueba>
    Dado que el usuario seleccione el menu Contacts
    Cuando "<Cuando>": "<Email>", "<Nombre>" y "<Mensaje>".
    Entonces "<Entonces>"

    Ejemplos:
      | Caso de prueba | Email | Nombre | Mensaje | Cuando | Entonces |
##@externaldata@./src/main/resources/data/DatosExcel.xlsx@DatosFormularioContacto
|CP1 - Registro de formulario exitoso|daniel@gmail.com|daniel|Esta es una prueba|ingrese email, nombre y mensaje correctos|el usuario visualizara un mensaje de envio exitoso|
|CP2 - Registro de formulario exitoso|danilefes|daniel|Esta es una prueba|ingrese email incorrecto, nombre y mensaje correctos|el usuario visualizara un mensaje de envio exitoso|
|CP3 - Registro de formulario exitoso|daniel@gmail.com|887783|Esta es una prueba|ingrese email, nombre incorrecto y mensaje correctos|el usuario visualizara un mensaje de envio exitoso|
|CP4 - Registro de formulario exitoso|daniel@gmail.com|daniel|534345354|ingrese email, nombre y mensaje incorrecto|el usuario visualizara un mensaje de envio exitoso|
|CP5 - Registro de formulario exitoso|daniel@gmail.com|daniel||ingrese email, nombre y mensaje vacio|el usuario visualizara un mensaje de envio exitoso|
|CP6 - Registro de formulario exitoso|daniel@gmail.com|||ingrese email, nombre vacio y mensaje vacio|el usuario visualizara un mensaje de envio exitoso|
|CP7 - Registro de formulario exitoso||daniel|Esta es una prueba|ingrese email vacio , nombre y mensaje correctos|el usuario visualizara un mensaje de envio exitoso|
|CP8 - Registro de formulario exitoso||||ingrese email, nombre y mensaje vacios|el usuario visualizara un mensaje de envio exitoso|
