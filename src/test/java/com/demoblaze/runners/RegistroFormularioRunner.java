package com.demoblaze.runners;


import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(CustomCucumberWithSerenity.class)
@CucumberOptions(features = "src/test/resources/features/registro_formulario_contacto.feature",
        glue = "com.demoblaze.stepdefinitions",
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        tags = "",
        plugin = {"summary", "pretty", "html:target/cucumber-reports"})

public class RegistroFormularioRunner {
}
