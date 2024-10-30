package com.demoblaze.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.thucydides.core.annotations.Step;

import static com.demoblaze.userinterfaces.PaginaUsuarioUI.*;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class SelectContactTask implements Task {

    @Override
    @Step("que el usuario seleccione el menu Contacts")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(LINK_CONTACT)
        );

    }
    public static Performable selectContactTask () {
        return instrumented(SelectContactTask.class);
    }
}
