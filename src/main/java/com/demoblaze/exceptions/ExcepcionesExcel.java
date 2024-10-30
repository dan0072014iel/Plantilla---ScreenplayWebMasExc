package com.demoblaze.exceptions;

public class ExcepcionesExcel extends RuntimeException {

    public static final String MENSAJE_EXCEL_VARIABLES = "Error de lectura de la hoja Variables: ";
    public static final String MENSAJE_EXCEL_LOGIN = "Error de lectura de la hoja DatosLogin: ";
    public static final String MENSAJE_EXCEL_FORM = "Error de lectura de la hoja DatosFormularioContacto: ";
    
    public ExcepcionesExcel(String message) {
        super(message);
    }

    public ExcepcionesExcel(String message, Throwable cause) {
        super(message, cause);
    }
}