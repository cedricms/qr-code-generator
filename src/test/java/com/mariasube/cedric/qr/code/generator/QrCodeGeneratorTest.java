package com.mariasube.cedric.qr.code.generator;

import com.mariasube.cedric.qr.code.generator.exception.parameter.NoParameterException;
import com.mariasube.cedric.qr.code.generator.exception.parameter.ParameterException;
import com.mariasube.cedric.qr.code.generator.exception.parameter.TooManyParametersException;
import org.junit.Test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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

    @Test
    public void generateQrCodeImageGivenNullTextThenNullBufferedImage() throws IOException {
        // Given
        QrCodeGenerator qrCodeGenerator = new QrCodeGenerator();
        String text = null;

        // When
        BufferedImage bufferedImage = qrCodeGenerator.generateQrCodeImage(text);

        // Then
        assertTrue(bufferedImage == null);
    }

    @Test
    public void generateQrCodeImageGivenTextThenBufferedImage() throws IOException {
        // Given
        QrCodeGenerator qrCodeGenerator = new QrCodeGenerator();
        String text = "Awesome text to encode as a QR!!!";

        // When
        BufferedImage bufferedImage = qrCodeGenerator.generateQrCodeImage(text);

        // Then
        assertTrue(bufferedImage != null);
    }

    @Test
    public void createImageFileGivenNullImageAndNullFilePathThenNull() throws IOException {
        // Given
        QrCodeGenerator qrCodeGenerator = new QrCodeGenerator();
        BufferedImage bufferedImage = null;
        String filePath = null;

        // When
        File imageFile = qrCodeGenerator.createImageFile(bufferedImage, filePath);

        // Then
        assertTrue(imageFile == null);
    }

    @Test
    public void createImageFileGivenImageAndNullFilePathThenNull() throws IOException {
        // Given
        QrCodeGenerator qrCodeGenerator = new QrCodeGenerator();
        String text = "Some awewsome text.";
        BufferedImage bufferedImage = qrCodeGenerator.generateQrCodeImage(text);
        String filePath = null;

        // When
        File imageFile = qrCodeGenerator.createImageFile(bufferedImage, filePath);

        // Then
        assertTrue(imageFile == null);
    }

    @Test
    public void createImageFileGivenNullImageAndFilePathThenNull() throws IOException {
        // Given
        QrCodeGenerator qrCodeGenerator = new QrCodeGenerator();
        BufferedImage bufferedImage = null;
        String filePath = "./test/qrCode.png";

        // When
        File imageFile = qrCodeGenerator.createImageFile(bufferedImage, filePath);

        // Then
        assertTrue(imageFile == null);
    }

    @Test
    public void createImageFileGivenImageAndFilePathWithShortTextThenFile() throws IOException {
        // Given
        QrCodeGenerator qrCodeGenerator = new QrCodeGenerator();
        String text = "Some even more awesome text.";
        BufferedImage bufferedImage = qrCodeGenerator.generateQrCodeImage(text);
        String filePath = "./test/qrCode_short-text.png";

        // When
        File imageFile = qrCodeGenerator.createImageFile(bufferedImage, filePath);

        // Then
        assertTrue(imageFile != null);
    }

    @Test
    public void createImageFileGivenImageAndFilePathWithLongTextThenFile() throws IOException {
        // Given
        QrCodeGenerator qrCodeGenerator = new QrCodeGenerator();
        String text = "First line.\n"
                + "Second line.\n"
                + "And another line.\n"
                + "Let's wrap it up.";
        BufferedImage bufferedImage = qrCodeGenerator.generateQrCodeImage(text);
        String filePath = "./test/qrCode_long-text.png";

        // When
        File imageFile = qrCodeGenerator.createImageFile(bufferedImage, filePath);

        // Then
        assertTrue(imageFile != null);
    }

    @Test
    public void calculateImageSizeFromTextGivenNullTextThen100() {
        // Given
        QrCodeGenerator qrCodeGenerator = new QrCodeGenerator();
        String text = null;

        // When
        int imageSize = qrCodeGenerator.calculateImageSizeFromText(text);

        // Then
        assertTrue(imageSize == 100);
    }

    @Test
    public void calculateImageSizeFromTextGivenEmptyTextThen100() {
        // Given
        QrCodeGenerator qrCodeGenerator = new QrCodeGenerator();
        String text = "";

        // When
        int imageSize = qrCodeGenerator.calculateImageSizeFromText(text);

        // Then
        assertTrue(imageSize == 100);
    }

    @Test
    public void calculateImageSizeFromTextGivenShortTextThen101() {
        // Given
        QrCodeGenerator qrCodeGenerator = new QrCodeGenerator();
        String text = "Short text";

        // When
        int imageSize = qrCodeGenerator.calculateImageSizeFromText(text);

        // Then
        assertTrue(imageSize == 102);
    }

    @Test
    public void calculateImageSizeFromTextGivenLongTextThen100() {
        // Given
        QrCodeGenerator qrCodeGenerator = new QrCodeGenerator();
        String text = "First line.\n"
                + "Second line.\n"
                + "And another line.\n"
                + "Let's wrap it up.";

        // When
        int imageSize = qrCodeGenerator.calculateImageSizeFromText(text);

        // Then
        assertTrue(imageSize == 112);
    }
}
