package dev.swanhtet.godaung.Authentication;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class TokenSerivceImpl implements TokenServices, AuthorizationServices {

	private static final String SECRET_KEY = "ThisIsASecretKey1234567890123456"; // 32 byte
	private static final String ALGORITHM = "AES/CBC/PKCS5Padding";
	private static final int IV_SIZE = 16;

	@SneakyThrows
	@Override
	public byte[] encryption(String userInfo) throws NoSuchAlgorithmException, NoSuchPaddingException {
		SecretKey secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
		Cipher cipher = Cipher.getInstance(ALGORITHM);

		byte[] iv = new byte[IV_SIZE];
		SecureRandom secureRandom = new SecureRandom();
		secureRandom.nextBytes(iv);
		IvParameterSpec ivParams = new IvParameterSpec(iv);

		cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParams);
		byte[] cipherText = cipher.doFinal(userInfo.getBytes());

		byte[] cipherTextWithIv = new byte[IV_SIZE + cipherText.length];
		System.arraycopy(iv, 0, cipherTextWithIv, 0, IV_SIZE);
		System.arraycopy(cipherText, 0, cipherTextWithIv, IV_SIZE, cipherText.length);

		return cipherTextWithIv;
	}

	@Override
	@SneakyThrows
	public String decryption(byte[] cipherTextWithIv) {
		SecretKey secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
		Cipher cipher = Cipher.getInstance(ALGORITHM);

		byte[] iv = new byte[IV_SIZE];
		System.arraycopy(cipherTextWithIv, 0, iv, 0, IV_SIZE);
		IvParameterSpec ivParams = new IvParameterSpec(iv);

		byte[] cipherText = new byte[cipherTextWithIv.length - IV_SIZE];
		System.arraycopy(cipherTextWithIv, IV_SIZE, cipherText, 0, cipherText.length);

		cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParams);
		byte[] decryptedByte = cipher.doFinal(cipherText);

		return new String(decryptedByte);
	}

	@Override
	public String byteToBase64(byte[] encryption) {
		return Base64.getEncoder().encodeToString(encryption);
	}

	@Override
	public byte[] base64ToByte(String base64) {
		return Base64.getDecoder().decode(base64);
	}

	@Override
	public String tokenPreparation(String email, String role) {
		return email + "|" + role + "|" + LocalDateTime.now() + "|" + LocalDateTime.now().plusHours(24);
	}

	@Override
	@SneakyThrows
	public String finalTokenGeneration(String preToken) {
		byte[] preByteToken = encryption(preToken);
		return byteToBase64(preByteToken);
	}

	@Override
	public List<String> tokenSegmentation(String decryptedToken) {
		String[] segmentations = decryptedToken.split("\\|");
		return new ArrayList<>(Arrays.asList(segmentations));
	}

	/**
	 * @Author : Swan Htet Aung Phyo
	 * @Usage : For the authorization Checking
	 */
	@Override
	public boolean dbInfoMatcher(List<String> segmentation) {
		String email = segmentation.get(0);
		String role = segmentation.get(1);
		LocalDateTime createdAt = LocalDateTime.parse(segmentation.get(2));
		LocalDateTime expiredAt = LocalDateTime.parse(segmentation.get(3));
		return expiredAt.isAfter(LocalDateTime.now());
	}
}




/*	public static void main(String[] args) {
		// Create an instance of TokenServiceImpl
		TokenSerivceImpl tokenService = new TokenSerivceImpl();

		// Example data
		String email = "example@example.com";
		String role = "ADMIN";

		// Prepare the token
		String preToken = tokenService.tokenPreparation(email, role);

		// Generate the final token
		String finalToken = tokenService.finalTokenGeneration(preToken);

		// Output the final token
		System.out.println("Final Token: " + finalToken);

		// Decode the token
		byte[] decodedToken = tokenService.base64ToByte(finalToken);
		String decryptedToken = tokenService.decryption(decodedToken);

		// Output the decrypted token
		System.out.println("Decrypted Token: " + decryptedToken);
		System.out.println(decryptedToken.length());
		String token = "example@example.com|ADMIN|2024-05-25T17:30:06.137182|2024-05-26T17:30:06.138082";
		String[] segments = decryptedToken.split("\\|");
		for(String segment : segments){
			System.out.println(segment);
		}
	}*/

