package com.demoblaze.questions;

import com.demoblaze.userinterfaces.PaginaUsuarioUI;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.actions.AlertText;
import net.serenitybdd.screenplay.actions.Switch;
import net.serenitybdd.screenplay.annotations.Subject;
import org.openqa.selenium.NoAlertPresentException;

public class ValidacionLogin implements Question<Boolean> {

    Boolean status;

    @Override
    @Subject("Se valida si se visualiza el nombre del usuario en la seccion de cuenta")
    public Boolean answeredBy(Actor actor) {
        try {
            if (AlertText.currentlyVisible().answeredBy(actor) != null) {
                Switch.toAlert().andAccept();
                status = false;
            }
        } catch (NoAlertPresentException e) {
            if (PaginaUsuarioUI.MSJ_LOGIN.of(actor.recall("usuario").toString()).resolveFor(actor).waitUntilVisible().isVisible()) {
                status = true;
            }
        }
        return status;
    }

    public static Question<Boolean> validarLogin() {
        return new ValidacionLogin();
    }
}
