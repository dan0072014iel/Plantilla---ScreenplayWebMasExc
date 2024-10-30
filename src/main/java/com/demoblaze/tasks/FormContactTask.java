package com.demoblaze.tasks;

import lombok.AllArgsConstructor;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.thucydides.core.annotations.Step;

import static com.demoblaze.userinterfaces.PaginaUsuarioUI.*;
import static net.serenitybdd.screenplay.Tasks.instrumented;

@AllArgsConstructor
public class FormContactTask implements Task {

    private String email;
    private String nombre;
    private String mensaje;

    @Override
    @Step("ingrese la informacion solicitada")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Enter.theValue(email).into(INPUT_EMAIL),
                Enter.theValue(nombre).into(INPUT_NAME),
                Enter.theValue(mensaje).into(TEXTAREA_MESSAGE),
                Click.on(BTN_ENVIAR)
        );

    }

    public static Performable formContactTask (String email, String nombre, String mensaje) {
        return instrumented(FormContactTask.class, email, nombre, mensaje);
    }
}
