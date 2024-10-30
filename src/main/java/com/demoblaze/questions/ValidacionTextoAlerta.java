package com.demoblaze.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.actions.AlertText;
import net.serenitybdd.screenplay.annotations.Subject;

public class ValidacionTextoAlerta implements Question<String> {

    @Override
    @Subject("Se valida si se visualiza una alerta de operacion exitosa")
    public String answeredBy(Actor actor) {
        return actor.asksFor(AlertText.currentlyVisible());
    }

    public static Question<String> validacionAlerta() {
        return new ValidacionTextoAlerta();
    }
}
