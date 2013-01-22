/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.codec.binary.Hex;

/**
 *
 * @author marti_000
 */
public class SecurityHacker {
	
	public static void main(String[] args) throws NoSuchAlgorithmException {
		String encoded = "68830b18c693ffb7cc6e04b5053aa293f2baccfc";
		String decoded = "9f1fb4c2-777d-4c44-99d3-97fceb1f1873";
		
		String[] encodings = new String[] {"MD2", "MD5", "SHA-1", "SHA-256", "SHA-384", "SHA-512"};
		for (String encoding : encodings) {
			MessageDigest messageDigest = MessageDigest.getInstance(encoding);
			messageDigest.update(decoded.getBytes());
			String myencoded = Hex.encodeHexString(messageDigest.digest());
			if (myencoded.equals(encoded)) {
				System.out.println("Algorithm: "+encoding);
				return;
			}else{
				System.out.println(myencoded+" != "+encoded);
			}
		}
		
		System.out.println("No algorithm found");
	}
	
}
