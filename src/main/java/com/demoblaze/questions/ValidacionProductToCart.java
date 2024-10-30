package com.demoblaze.questions;

import com.demoblaze.userinterfaces.PaginaCartUI;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.annotations.Subject;

public class ValidacionProductToCart implements Question <Boolean> {

    @Override
    @Subject("Se valida si se visualiza en la seccion del carrito el producto agregado")
    public Boolean answeredBy(Actor actor) {

        return PaginaCartUI.VALIDATE_PRODUCT.resolveFor(actor).waitUntilVisible().isVisible();
    }

    public static Question<Boolean> validacionProductToCart(){
        return new ValidacionProductToCart();
    }
}
