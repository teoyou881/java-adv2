package charset;

import static java.nio.charset.StandardCharsets.ISO_8859_1;
import static java.nio.charset.StandardCharsets.US_ASCII;
import static java.nio.charset.StandardCharsets.UTF_16BE;
import static java.nio.charset.StandardCharsets.UTF_8;

import java.nio.charset.Charset;
import java.util.Arrays;

public class EncodingMain2 {

  private static final Charset EUC_KR = Charset.forName("EUC-KR");
  private static final Charset MS_949 = Charset.forName("MS949");

  public static void main(String[] args) {
    System.out.println("== ASCII English Encoding ==");
    test("A", US_ASCII, US_ASCII);
    test("A", US_ASCII, ISO_8859_1); // ASCII extension (LATIN-1)
    test("A", US_ASCII, EUC_KR); // ASCII included
    test("A", US_ASCII, MS_949); // ASCII included
    test("A", US_ASCII, UTF_8); // ASCII included
    test("A", US_ASCII, UTF_16BE); // UTF-16 decoding fails

    System.out.println("== Korean Encoding - Basic ==");
    test("가", US_ASCII, US_ASCII); // X
    test("가", ISO_8859_1, ISO_8859_1); // X
    test("가", EUC_KR, EUC_KR);
    test("가", MS_949, MS_949);
    test("가", UTF_8, UTF_8);
    test("가", UTF_16BE, UTF_16BE);

    System.out.println("== Korean Encoding - Complex Characters ==");
    test("뷁", EUC_KR, EUC_KR); // X
    test("뷁", MS_949, MS_949);
    test("뷁", UTF_8, UTF_8);
    test("뷁", UTF_16BE, UTF_16BE);

    System.out.println("== Korean Encoding - Decoding Differences ==");
    test("가", EUC_KR, MS_949);
    test("뷁", MS_949, EUC_KR); // Encoding possible, decoding fails
    test("가", EUC_KR, UTF_8); // X
    test("가", MS_949, UTF_8); // X
    test("가", UTF_8, MS_949); // X

    System.out.println("== English Encoding - Decoding Differences ==");
    test("A", EUC_KR, UTF_8);
    test("A", MS_949, UTF_8);
    test("A", UTF_8, MS_949);
    test("A", UTF_8, UTF_16BE); // X
  }

  private static void test(String text, Charset encodingCharset, Charset decodingCharset) {
    byte[] encoded = text.getBytes(encodingCharset);
    String decoded = new String(encoded, decodingCharset);
    System.out.printf("%s -> [%s] Encoding -> %s %sbyte -> [%s] Decoding -> %s\n", text,
        encodingCharset, Arrays.toString(encoded), encoded.length, decodingCharset, decoded);
  }
}
