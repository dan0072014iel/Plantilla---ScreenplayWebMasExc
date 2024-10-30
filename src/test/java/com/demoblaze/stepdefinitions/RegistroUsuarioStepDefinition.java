package com.demoblaze.stepdefinitions;

import com.demoblaze.questions.ValidacionTextoAlerta;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.containsString;
import com.demoblaze.tasks.SignUpTask;
import io.cucumber.java.en.*;
import static net.serenitybdd.screenplay.actors.OnStage.*;

public class RegistroUsuarioStepDefinition {


    @When("seleccione Sign Up e ingrese el usuario con la clave")
    public void seleccioneSignUpEIngreseElUsuarioConLaClave() {
        theActorInTheSpotlight().attemptsTo(SignUpTask.signUpTask());
    }

    @Then("el usuario visualizara un mensaje de registro exitoso")
    public void elUsuarioVisualizaraUnMensajeDeRegistroExitoso() {
        theActorInTheSpotlight().should(
                seeThat(ValidacionTextoAlerta.validacionAlerta(), containsString("Sign up successful."))
        );
    }
}
