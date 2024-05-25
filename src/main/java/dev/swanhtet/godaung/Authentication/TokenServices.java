package dev.swanhtet.godaung.Authentication;

import javax.crypto.NoSuchPaddingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface TokenServices {

	byte[] encryption(String userInfo) throws NoSuchAlgorithmException, NoSuchPaddingException;

	String decryption(byte[] cipherTex) throws  NoSuchAlgorithmException;

	String byteToBase64(byte[] encryption);

	byte[] base64ToByte(String base64);

	String tokenPreparation(String email , String Role);
	String finalTokenGeneration(String preToken);

	List<String> tokenSegmentation(String DECRYPTED_TOKEN);
}
