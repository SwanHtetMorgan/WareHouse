package dev.swanhtet.godaung.Authentication;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.crypto.NoSuchPaddingException;

public interface TokenServices {

  byte[] encryption(String userInfo) throws NoSuchAlgorithmException, NoSuchPaddingException;

  String decryption(byte[] cipherTex) throws NoSuchAlgorithmException;

  String byteToBase64(byte[] encryption);

  byte[] base64ToByte(String base64);

  String tokenPreparation(String email, String Role);

  String finalTokenGeneration(String preToken);

  List<String> tokenSegmentation(String DECRYPTED_TOKEN);
}
