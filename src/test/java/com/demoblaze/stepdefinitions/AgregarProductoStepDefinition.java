package com.demoblaze.stepdefinitions;

import com.demoblaze.exceptions.ResultadoNoEsperado;
import com.demoblaze.questions.ValidacionProductToCart;
import com.demoblaze.tasks.AddtoCartTask;
import com.demoblaze.tasks.SelectCatLaptopTask;
import io.cucumber.java.en.*;
import org.hamcrest.Matchers;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class AgregarProductoStepDefinition {

    @Given("que el usuario seleccione la categoria y producto de Laptops")
    public void queElUsuarioSeleccioneLaCategoriaYProductoDeLaptops() {
        theActorInTheSpotlight().attemptsTo(SelectCatLaptopTask.selectCatLaptopTask());
    }
    @When("agregue al carrito el producto deseado")
    public void AgregueAlCarritoElProductoDeseado() {
        theActorInTheSpotlight().attemptsTo(AddtoCartTask.addtoCartTask());
    }
    @Then("el usuario visualizara un mensaje de adicion exitoso")
    public void elUsuarioVisualizaraUnMensajeDeAdicionExitoso() {
        theActorInTheSpotlight().should(seeThat(ValidacionProductToCart.validacionProductToCart(),
                Matchers.equalTo(true)).orComplainWith(ResultadoNoEsperado.class,ResultadoNoEsperado.ADICION_PRODUCTO_FALLO));
    }
}
