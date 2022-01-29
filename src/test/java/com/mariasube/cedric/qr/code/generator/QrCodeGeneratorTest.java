package com.mariasube.cedric.qr.code.generator;

import com.mariasube.cedric.qr.code.generator.exception.parameter.NoParameterException;
import com.mariasube.cedric.qr.code.generator.exception.parameter.ParameterException;
import com.mariasube.cedric.qr.code.generator.exception.parameter.TooManyParametersException;
import org.junit.Test;
import static org.junit.Assert.*;

public class QrCodeGeneratorTest {

    @Test(expected = NoParameterException.class)
    public void validateParametersGivenNoParameterThenNoParameterException() throws ParameterException {
        // Given
        QrCodeGenerator qrCodeGenerator = new QrCodeGenerator();

        // When
        String[] parameters = null;

        // Then
        qrCodeGenerator.validateParameters(parameters);
    }

    @Test
    public void validateParametersGivenOneParameterThenNoException() throws ParameterException {
        // Given
        QrCodeGenerator qrCodeGenerator = new QrCodeGenerator();

        // When
        String[] parameters = {"Super text to transform into QR code."};

        // Then
        qrCodeGenerator.validateParameters(parameters);
    }

    @Test(expected = TooManyParametersException.class)
    public void validateParametersGivenTwoParametersThenTooManyParametersException() throws ParameterException {
        // Given
        QrCodeGenerator qrCodeGenerator = new QrCodeGenerator();

        // When
        String[] parameters = {"Super text to transform into QR code.", "Oops, too much text!"};

        // Then
        qrCodeGenerator.validateParameters(parameters);
    }
}
