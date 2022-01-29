package com.mariasube.cedric.qr.code.generator.exception.parameter;

public class NoParameterException extends ParameterException {

    public NoParameterException() {
        super("You must state your text in quotes to generate the QR code.");
    }
}
