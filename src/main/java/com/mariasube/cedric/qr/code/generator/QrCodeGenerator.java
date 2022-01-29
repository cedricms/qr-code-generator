package com.mariasube.cedric.qr.code.generator;

import com.mariasube.cedric.qr.code.generator.exception.parameter.NoParameterException;

import java.util.Arrays;

import com.mariasube.cedric.qr.code.generator.exception.parameter.ParameterException;
import com.mariasube.cedric.qr.code.generator.exception.parameter.TooManyParametersException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class QrCodeGenerator {

    private static final Logger logger = LogManager.getLogger("QrCodeGenerator");

    public static void main(String[] args) {
        QrCodeGenerator qrCodeGenerator = new QrCodeGenerator();

        try {
            qrCodeGenerator.validateParameters(args);
        } catch (NoParameterException npe) {
            logger.error("You must state your text in quotes to generate the QR code.", npe);
        } catch (TooManyParametersException tmpe) {
            logger.error("You must only state your text in quotes to generate the QR code. For now, there are too many parameters.", tmpe);
        } catch (ParameterException pe) {
            logger.error("Ouch, something bad happened, but we have no idea what it might be ... sorry ...", pe);
        }
    }

    public void validateParameters(String[] parameters) throws ParameterException {
        if (parameters == null) {
            throw new NoParameterException();
        } else if (parameters.length >= 2) {
            throw new TooManyParametersException();
        }
    }
}
