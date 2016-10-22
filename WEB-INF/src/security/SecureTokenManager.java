package security;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
public class SecureTokenManager {
	private static int TOKEN_LENGTH = 16;//16*2=32バイト

	//32バイトのCSRFトークンを作成
	public static String getCsrfToken() throws NoSuchAlgorithmException {
		byte[] token = new byte[TOKEN_LENGTH];
		StringBuffer buf = new StringBuffer();
		SecureRandom random = null;
		random = SecureRandom.getInstance("SHA1PRNG");
		random.nextBytes(token);

		for (int i = 0; i < token.length; i++) {
			buf.append(String.format("%02x", token[i]));
		}
		System.out.println("Secure Token:"+buf.toString());
		return buf.toString();
	}
}
