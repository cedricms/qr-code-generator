package com.mariasube.cedric.qr.code.generator.exception.parameter;

public class TooManyParametersException extends ParameterException {

    public TooManyParametersException() {
        super("There are too many parameters, you must only state your text in quotes in order to generate the QR code.");
    }
}
