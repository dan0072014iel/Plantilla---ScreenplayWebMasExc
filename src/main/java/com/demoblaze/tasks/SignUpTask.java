package com.demoblaze.tasks;

import com.demoblaze.interactions.UsuarioRandom;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.thucydides.core.annotations.Step;

import java.util.List;

import static com.demoblaze.userinterfaces.PaginaInicioUI.*;
import static com.demoblaze.utils.Constants.*;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class SignUpTask implements Task {

    @Override
    @Step("seleccione Sign Up e ingrese el usuario con la clave")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                UsuarioRandom.usuarioRandom(LONGITUD_CHAR, CANTIDAD_STRING)
        );
        List<String> cadenasGeneradas = actor.recall("cadenasAlfanumericas");
        actor.attemptsTo(
                Click.on(BTN_SIGNUP),
                Enter.theValue(cadenasGeneradas.get(0)).into(TXT_NEWUSUARIO),
                Enter.theValue(cadenasGeneradas.get(1)).into(TXT_NEWCLAVE),
                Click.on(BTN_NEWINGRESAR)
        );
    }

    public static Performable signUpTask () {
        return instrumented(SignUpTask.class);
    }

}
