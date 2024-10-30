package com.demoblaze.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

public class PaginaInicioUI {

    private PaginaInicioUI() {

    }

    public static final Target BTN_LOGIN = Target.the("Log In").locatedBy("//a[@id='login2']");
    public static final Target TXT_USUARIO = Target.the("usuario").locatedBy("//input[@id='loginusername']");
    public static final Target TXT_CLAVE = Target.the("ingresar clave").locatedBy("//input[@id='loginpassword']");
    public static final Target BTN_INGRESAR = Target.the("boton ingresar").locatedBy("//button[@onclick='logIn()']");

    public static final Target BTN_SIGNUP = Target.the("Sign Up").locatedBy("//a[@id='signin2']");
    public static final Target TXT_NEWUSUARIO = Target.the("nuevo usuario").locatedBy("//input[@id='sign-username']");
    public static final Target TXT_NEWCLAVE = Target.the("nueva clave").locatedBy("//input[@id='sign-password']");
    public static final Target BTN_NEWINGRESAR = Target.the("ingresar").locatedBy("//button[@onclick='register()']");
}