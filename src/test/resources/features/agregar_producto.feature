#language: es

Característica: Agregar producto

  Antecedentes:
    Dado que el usuario se encuentre en la pagina web
    Cuando seleccione Log In e ingrese el user con la clave

  Escenario: Adicion del producto exitoso
    Dado que el usuario seleccione la categoria y producto de Laptops
    Cuando agregue al carrito el producto deseado
    Entonces el usuario visualizara un mensaje de adicion exitoso
