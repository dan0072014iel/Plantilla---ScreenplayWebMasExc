package com.demoblaze.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

public class PaginaProductoUI {

    private PaginaProductoUI() {

    }

    public static final Target BTN_ADDCART = Target.the("boton agregar al carrito").locatedBy("//a[@onclick='addToCart(15)']");
    public static final Target LINK_CART = Target.the("menu carrito").locatedBy("//a[@id='cartur']");




}
