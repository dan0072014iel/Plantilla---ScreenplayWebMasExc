package com.demoblaze.stepdefinitions;


import com.demoblaze.questions.ValidacionTextoAlerta;
import com.demoblaze.tasks.FormContactTask;
import com.demoblaze.tasks.SelectContactTask;
import io.cucumber.java.en.*;
import io.cucumber.java.es.Cuando;

import static com.demoblaze.utils.Constants.VALIDACION_FORMULARIO;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.*;
import static org.hamcrest.Matchers.containsString;

public class RegistroFormularioStepDefinition {

    @Given("que el usuario seleccione el menu Contacts")
    public void queElUsuarioSeleccioneElMenuContacts() {
        theActorInTheSpotlight().attemptsTo(SelectContactTask.selectContactTask());
    }
    @Cuando("{string}: {string}, {string} y {string}.")
    public void y(String string, String email, String nombre, String mensaje) {
        theActorInTheSpotlight().attemptsTo(FormContactTask.formContactTask(email, nombre, mensaje));
    }
    @Then("{string}")
    public void string(String string) {
        theActorInTheSpotlight().should(
                seeThat(ValidacionTextoAlerta.validacionAlerta(), containsString(VALIDACION_FORMULARIO))
        );
    }

}
