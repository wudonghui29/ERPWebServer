package com.modules.Util;

import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.crypto.Cipher;
 
/**
 * ÷§ È◊Èº˛
 * @author tony
 *
 */
public abstract class CertificateCoder2 {
 
	/**
	 *  ˝◊÷÷§ Èµƒ∏Ò Ω
	 */
	public static final String CERT_TYPE = "X.509";
 
	/**
	 * ∏˘æ›√‹‘øø‚µƒ¬∑æ∂°¢±√˚∫Õ√‹¬ÎªÒ»° ˝◊÷÷§ ÈµƒÀΩ‘ø
	 * @param keyStorePath √‹‘øø‚µƒ¬∑æ∂
	 * @param alias ÷§ È±√˚
	 * @param password √‹¬Î
	 * @return ∑µªÿªÒ»°µΩµƒÀΩ‘ø∂‘œÛ
	 * @throws Exception 
	 */
	public static PrivateKey getPrivateKeyByKeyStore(String keyStorePath, String alias, String password) throws Exception {
		KeyStore keyStore = getKeyStore(keyStorePath, password);
		return (PrivateKey) keyStore.getKey(alias, password.toCharArray());
	}
 
	/**
	 * ∏˘æ›√‹‘øø‚¬∑æ∂ªÒ»°π´‘ø
	 * @param keyStorePath
	 * @param alias
	 * @param password
	 * @return
	 * @throws Exception 
	 */
	public static PublicKey getPublicKeyByKeyStore(String certificatePath) throws Exception {
		Certificate certificate = getCertificate(certificatePath);
		return certificate.getPublicKey();
	}
 
	/**
	 * ∏˘æ› ˝◊÷÷§ Èµƒ¬∑æ∂ªÒ»° ˝◊÷÷§ È∂‘œÛ
	 * @param certificatePath  ˝◊÷÷§ È¬∑æ∂
	 * @return º”‘ÿ∫Ûµƒ ˝◊÷÷§ È∂‘œÛ
	 * @throws Exception 
	 */
	public static Certificate getCertificate(String certificatePath) throws Exception {
		CertificateFactory certificateFactory = CertificateFactory.getInstance(CERT_TYPE);
		FileInputStream fileInputStream = new FileInputStream(new File(certificatePath));
		Certificate certificate =  certificateFactory.generateCertificate(fileInputStream);
		fileInputStream.close();
		return certificate;
	}
 
	/**
	 * ∏˘æ›√‹‘øø‚¬∑æ∂ªÒ»° ˝◊÷÷§ È
	 * @param keyStorePath √‹‘øø‚¬∑æ∂
	 * @param alias ±√˚
	 * @param password √‹¬Î
	 * @return ∑µªÿ ˝◊÷÷§ È∂‘œÛ
	 * @throws Exception
	 */
	public static Certificate getCertificate(String keyStorePath, String alias, String password) throws Exception {
		// ªÒ»°√‹‘øø‚
		KeyStore keyStore = getKeyStore(keyStorePath, password);
		// ∏˘æ›√‹‘øø‚ªÒ»° ˝◊÷÷§ È
		return keyStore.getCertificate(alias);
	}
 
	/**
	 * ∏˘æ›÷∏∂®µƒ√‹‘øø‚¬∑æ∂ªÒ»°√‹‘øø‚∂‘œÛ
	 * @param keyStorePath  ˝◊÷÷§ È¬∑æ∂
	 * @param password √‹¬Î
	 * @return ∑µªÿªÒ»°µΩµƒ ˝◊÷÷§ È∂‘œÛ
	 * @throws Exception 
	 */
	public static KeyStore getKeyStore(String keyStorePath, String password) throws Exception {
		//  µ¿˝ªØ√‹‘øø‚∂‘œÛ
		KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
		// ªÒµ√√‹‘øø‚µƒŒƒº˛¡˜∂‘œÛ
		FileInputStream fileInputStream = new FileInputStream(new File(keyStorePath));
		// º”‘ÿ√‹‘ø–≈œ¢
		keyStore.load(fileInputStream, password.toCharArray());
		// πÿ±’¡˜∂‘œÛ
		fileInputStream.close();
 
		// ∑µªÿº”‘ÿ∫Ûµƒ√‹‘øø‚∂‘œÛ
		return keyStore;
	}
 
	/**
	 *  π”√√‹‘øø‚∂‘ ˝æ›Ω¯––ÀΩ‘øº”√‹
	 * @param data “™º”√‹µƒ ˝æ›
	 * @param keyStorePath √‹‘øø‚¬∑æ∂
	 * @param alias √‹‘øø‚±√˚
	 * @param password √‹¬Î
	 * @return 
	 * @throws Exception 
	 */
	public static byte[] encryptByPrivateKey(byte[] data, String keyStorePath, String alias, String password) throws Exception {
		PrivateKey privateKey = getPrivateKeyByKeyStore(keyStorePath, alias, password);
		Cipher cipher = Cipher.getInstance(privateKey.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, privateKey);
		return cipher.doFinal(data);
	}
 
	/**
	 *  π”√√‹‘øø‚ÀΩ‘ø∂‘ ˝æ›Ω¯––Ω‚√‹
	 * @param data “™Ω‚√‹µƒ ˝æ›
	 * @param keyStorePath √‹‘øø‚¬∑æ∂
	 * @param alias ±√˚
	 * @param password √‹¬Î
	 * @return ∑µªÿΩ‚√‹∫Ûµƒ ˝æ›
	 * @throws Exception 
	 */
	public static byte[] decryptByPrivateKey(byte[] data, String keyStorePath, String alias, String password) throws Exception {
		PrivateKey privateKey = getPrivateKeyByKeyStore(keyStorePath, alias, password);
		Cipher cipher = Cipher.getInstance(privateKey.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		return cipher.doFinal(data);
	}
 
	/**
	 *  π”√ ˝◊÷÷§ È∂‘ ˝æ›Ω¯––π´‘øº”√‹
	 * @param data “™º”√‹µƒ ˝æ›
	 * @param certificatePath  ˝◊÷÷§ È
	 * @return
	 * @throws Exception 
	 */
	public static byte[] encryptByPublicKey(byte[] data, String certificatePath) throws Exception {
		PublicKey publicKey = getPublicKeyByKeyStore(certificatePath);
		Cipher cipher = Cipher.getInstance(publicKey.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		return cipher.doFinal(data);
	}
 
	/**
	 *  π”√ ˝◊÷÷§ È∂‘ ˝æ›Ω¯––π´‘øΩ‚√‹
	 * @param data “™Ω‚√‹µƒ ˝æ›
	 * @param certificatePath  ˝◊÷÷§ Èµƒ¬∑æ∂
	 * @return
	 * @throws Exception 
	 */
	public static byte[] decryptByPublicKey(byte[] data, String certificatePath) throws Exception {
		PublicKey publicKey = getPublicKeyByKeyStore(certificatePath);
		Cipher cipher = Cipher.getInstance(publicKey.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, publicKey);
		return cipher.doFinal(data);
	}
 
	/**
	 *  π”√√‹‘øø‚∂‘ ˝æ›Ω¯––«©√˚£¨∑µªÿ ˝æ›µƒ ˝◊÷«©√˚
	 * @param data “™«©√˚µƒ ˝æ›
	 * @param keyStorePath √‹‘øø‚¬∑æ∂
	 * @param alias ±√˚
	 * @param password √‹¬Î
	 * @return
	 * @throws Exception 
	 */
	public static byte[] sign(byte[] data, String keyStorePath, String alias, String password) throws Exception {
		Signature signature = Signature.getInstance("SHA1withRSA");
		signature.initSign(getPrivateKeyByKeyStore(keyStorePath, alias, password));
 
		signature.update(data);
		return signature.sign();
	}
 
	/**
	 *  π”√ ˝◊÷÷§ È∂‘ ˝æ›Ω¯––—È÷§
	 * @param data “™—È÷§µƒ ˝æ›
	 * @param sign  ˝æ›µƒ ˝◊÷«©√˚
	 * @param certificatePath  ˝◊÷÷§ È¬∑æ∂
	 * @return ∑µªÿ «∑Ò—È÷§≥…π¶
	 * @throws Exception 
	 */
	public static boolean verify(byte[] data, byte[] sign, String certificatePath) throws Exception {
		// ªÒ»°X509∏Ò Ωµƒ ˝◊÷÷§ È∂‘œÛ
		X509Certificate x509Certificate = (X509Certificate) getCertificate(certificatePath);
 
		//  µ¿˝ªØ“ª∏ˆ«©√˚∂‘œÛ
		Signature signature = Signature.getInstance("SHA1withRSA");
		signature.initVerify(x509Certificate);
		signature.update(data);
		return signature.verify(sign);
	}
}
