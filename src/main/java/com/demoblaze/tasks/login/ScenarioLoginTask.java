package com.demoblaze.tasks.login;

import lombok.AllArgsConstructor;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.thucydides.core.annotations.Step;

import static com.demoblaze.userinterfaces.PaginaInicioUI.*;
import static net.serenitybdd.screenplay.Tasks.instrumented;

@AllArgsConstructor
public class ScenarioLoginTask implements Task {


    private final String usuario;
    private final String clave;

    @Override
    @Step("seleccione Log In e ingrese el user con la clave")
    public <T extends Actor> void performAs(T actor) {
        actor.remember("usuario", usuario);
        actor.attemptsTo(
                Click.on(BTN_LOGIN),
                Enter.theValue(usuario).into(TXT_USUARIO),
                Enter.theValue(clave).into(TXT_CLAVE),
                Click.on(BTN_INGRESAR)
        );
    }

    public static Performable login2Task(String usuario, String clave) {
        return instrumented(ScenarioLoginTask.class, usuario, clave);
    }
}

