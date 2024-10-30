package com.demoblaze.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

public class PaginaCartUI {

    private PaginaCartUI() {

    }

    public static final Target VALIDATE_PRODUCT = Target.the("producto en el carrito").locatedBy("//td[text()='MacBook Pro']");
}
