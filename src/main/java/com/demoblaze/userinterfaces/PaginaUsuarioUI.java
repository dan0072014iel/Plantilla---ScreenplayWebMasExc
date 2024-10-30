package com.demoblaze.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

public class PaginaUsuarioUI {

    private PaginaUsuarioUI() {

    }

    public static final Target MSJ_LOGIN = Target.the("mensaje de login exitoso").locatedBy("//a[text()='Welcome {0}']");

    public static final Target LINK_CONTACT = Target.the("menu contact").locatedBy("//a[text()='Contact']");
    public static final Target INPUT_EMAIL = Target.the("usuario").locatedBy("//input[@id='recipient-email']");
    public static final Target INPUT_NAME = Target.the("ingresar clave").locatedBy("//input[@id='recipient-name']");
    public static final Target TEXTAREA_MESSAGE = Target.the("ingresar clave").locatedBy("//textarea[@id='message-text']");
    public static final Target BTN_ENVIAR = Target.the("boton ingresar").locatedBy("//button[@onclick='send()']");

    public static final Target LINK_LAPTOP = Target.the("menu laptop").locatedBy("(//a[@id='itemc'])[2]");
    public static final Target LINK_NOTEBOOK = Target.the("producto notebook").locatedBy("//a[text()='MacBook Pro']");

}
