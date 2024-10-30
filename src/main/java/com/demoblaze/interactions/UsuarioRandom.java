package com.demoblaze.interactions;

import lombok.AllArgsConstructor;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import static com.demoblaze.utils.Constants.*;
import static net.serenitybdd.screenplay.Tasks.instrumented;

@AllArgsConstructor
public class UsuarioRandom implements Interaction {

    private final int longitud;
    private final int cantidad;

    private String randomAlphaNumeric(int length) {
        StringBuilder builder = new StringBuilder();
        int charactersLength = ALPHA_NUMERIC_STRING.length();
        SecureRandom secureRandom = new SecureRandom();
        for (int i = 0; i < length; i++) {
            int index = secureRandom.nextInt(charactersLength);
            builder.append(ALPHA_NUMERIC_STRING.charAt(index));
        }
        return builder.toString();
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        List<String> cadenasGeneradas = new ArrayList<>();
        for (int i = 0; i < cantidad; i++) {
            cadenasGeneradas.add(randomAlphaNumeric(longitud));
        }
        actor.remember("cadenasAlfanumericas", cadenasGeneradas);
    }

    public static UsuarioRandom usuarioRandom(int longitud, int cantidad) {
        return instrumented(UsuarioRandom.class, longitud, cantidad);
    }

}
