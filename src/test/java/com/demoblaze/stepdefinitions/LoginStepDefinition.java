package com.demoblaze.stepdefinitions;


import com.demoblaze.exceptions.ResultadoNoEsperado;
import com.demoblaze.questions.ValidacionLogin;
import com.demoblaze.tasks.login.ScenarioLoginTask;
import io.cucumber.java.en.*;
import org.hamcrest.Matchers;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;


public class LoginStepDefinition {

    @When("{string}: {string} y {string}.")
    public void y(String string, String userName, String password) {
        theActorInTheSpotlight().attemptsTo(ScenarioLoginTask.login2Task(userName, password));
    }

    @Then("{string}{string}")
    public void string(String string, String status) {
        theActorInTheSpotlight().should(seeThat("Verificación del resultado", ValidacionLogin.validarLogin(),
                Matchers.equalTo(Boolean.parseBoolean(status))).orComplainWith(ResultadoNoEsperado.class,ResultadoNoEsperado.MENSAJE_FALLO));

    }

}
