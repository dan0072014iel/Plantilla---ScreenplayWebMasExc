package com.demoblaze.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Switch;
import net.thucydides.core.annotations.Step;

import static com.demoblaze.userinterfaces.PaginaProductoUI.*;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class AddtoCartTask implements Task {

    @Override
    @Step("agregue al carrito el producto deseado")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(BTN_ADDCART),
                Switch.toAlert().andAccept(),
                Click.on(LINK_CART)
        );

    }


    public static Performable addtoCartTask() {
        return instrumented(AddtoCartTask.class);
    }
}
