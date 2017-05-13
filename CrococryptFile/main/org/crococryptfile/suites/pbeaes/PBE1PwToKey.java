package org.crococryptfile.suites.pbeaes;

import javax.crypto.Cipher;
import javax.crypto.Mac;

import org.fhissen.crypto.CryptoCodes;
import org.fhissen.crypto.CryptoCodes.BASECIPHER;
import org.fhissen.crypto.CryptoUtils;
import org.fhissen.crypto.FSecretKeySpec;
import org.fhissen.crypto.KeygenUtils;
import org.fhissen.crypto.PBKDF2;
import org.fhissen.utils.ByteUtils;
import org.fhissen.utils.ui.StatusUpdate;


public class PBE1PwToKey {
	public static final PBE1AESKeySet createPBE(char[] ret){
		return createPBE(ret, 0, null);
	}

	public static final PBE1AESKeySet createPBE(char[] ret, int ext_its, StatusUpdate status){
		try {
			int its = 0;
			
			if(ext_its <= 0)
				its = CryptoCodes.STANDARD_PBKDF2_ITERATIONS + ((int)(System.currentTimeMillis() % 10000));
			else
				its = ext_its;
			
			byte[] s = CryptoUtils.randIv(CryptoCodes.STANDARD_SALTSIZE);
			byte[] k = new KeygenUtils().raw(CryptoCodes.AES_KEYSIZE_BITS);
			
			byte[] bbuf = ByteUtils.charsToBytes(ret);
			byte[] pwkey = new PBKDF2(Mac.getInstance(CryptoCodes.HMAC_SHA512), status).generateDerivedParameters(CryptoCodes.AES_KEYSIZE_BITS, bbuf, s, its);
			
			CryptoUtils.kill(bbuf);
			CryptoUtils.kill(ret);

			byte[] k_enc = ecbRaw(pwkey, BASECIPHER.AES.getECB(), true, k);
			CryptoUtils.kill(pwkey);

			PBE1AESKeySet key = new PBE1AESKeySet();
			key.enckey = k_enc;
			key.plainkey = k;
			key.its = its;
			key.salt = s;

			return key;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static final void loadPBE(char[] pw, PBE1AESKeySet key, StatusUpdate status){
		if(pw == null || pw.length == 0) return;
		try {
			byte[] bbuf = ByteUtils.charsToBytes(pw);
			byte[] pwkey = new PBKDF2(Mac.getInstance(CryptoCodes.HMAC_SHA512), status).generateDerivedParameters(CryptoCodes.AES_KEYSIZE_BITS, bbuf, key.salt, key.its);

			byte[] k = ecbRaw(pwkey, BASECIPHER.AES.getECB(), false, key.enckey);

			CryptoUtils.kill(bbuf, pwkey);
			CryptoUtils.kill(pw);

			key.plainkey = k;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static byte[] ecbRaw(byte[] rawkey, String ciphercode, boolean encrypt, byte[] in){
		try {
			FSecretKeySpec fkey = new FSecretKeySpec(rawkey);
			Cipher ciph = Cipher.getInstance(ciphercode);
			if(encrypt)
				ciph.init(Cipher.ENCRYPT_MODE, fkey);
			else
				ciph.init(Cipher.DECRYPT_MODE, fkey);
			
			byte[] ret = ciph.doFinal(in);

			fkey.wipe(false);
			
			ciph.init(Cipher.ENCRYPT_MODE, new FSecretKeySpec(CryptoUtils.randIv16()));
			
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
