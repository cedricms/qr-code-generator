package com.mariasube.cedric.qr.code.generator;

import com.mariasube.cedric.qr.code.generator.exception.parameter.NoParameterException;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import com.mariasube.cedric.qr.code.generator.exception.parameter.ParameterException;
import com.mariasube.cedric.qr.code.generator.exception.parameter.TooManyParametersException;
import net.glxn.qrgen.javase.QRCode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.imageio.ImageIO;

public class QrCodeGenerator {

    private static final Logger logger = LogManager.getLogger("QrCodeGenerator");

    public static void main(String[] args) {
        QrCodeGenerator qrCodeGenerator = new QrCodeGenerator();

        qrCodeGenerator.generateQrCode(args);
    }

    private void generateQrCode(String[] args) {
        try {
            this.validateParameters(args);

            String text = args[0];

            BufferedImage bufferedImage = generateQrCodeImage(text);

            File imageFile = createImageFile(bufferedImage, "./test/qrCode.png");
        } catch (NoParameterException npe) {
            logger.error("You must state your text in quotes to generate the QR code.", npe);
        } catch (TooManyParametersException tmpe) {
            logger.error("You must only state your text in quotes to generate the QR code. For now, there are too many parameters.", tmpe);
        } catch (ParameterException pe) {
            logger.error("Ouch, something bad happened, but we have no idea what it might be ... sorry ... really ... good luck.", pe);
        } catch (IOException ioe) {
            logger.error("Unable to render the QR code or the file.", ioe);
        }
    }

    public File createImageFile(BufferedImage bufferedImage, String filePath) throws IOException {
        if ((bufferedImage == null) || (filePath == null)) {
            return null;
        } else {
            File file = new File(filePath);
            checkFileValidity(file);

            ImageIO.write(bufferedImage, "PNG", file);
            return file;
        }
    }

    private boolean checkFileValidity(File file) throws IOException {
        if (file != null) {
            if (!file.exists()) {
                if (file.isDirectory()) {
                    return file.mkdirs();
                } else {
                    File directory = file.getParentFile();
                    if (directory != null) {
                        if (!directory.exists()) {
                            directory.mkdirs();
                        }

                        return file.createNewFile();
                    }
                }
            } else {
                return true;
            }
        } else {
            return false;
        }

        return false;
    }

    public BufferedImage generateQrCodeImage(String text) throws IOException {
        if (text == null) {
            return null;
        } else {
            int imageSize = calculateImageSizeFromText(text);
            ByteArrayOutputStream stream = QRCode
                    .from(text)
                    .withSize(imageSize, imageSize)
                    .stream();
            ByteArrayInputStream bis = new ByteArrayInputStream(stream.toByteArray());

            return ImageIO.read(bis);
        }
    }

    public int calculateImageSizeFromText(String text) {
        if (text == null) {
            return 100;
        } else {
            return (((int) (text.length() / 5)) + 100);
        }
    }

    public void validateParameters(String[] parameters) throws ParameterException {
        if ((parameters == null) || (parameters.length == 0)) {
            throw new NoParameterException();
        } else if (parameters.length >= 2) {
            throw new TooManyParametersException();
        }
    }
}
