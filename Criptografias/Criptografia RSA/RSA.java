import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.security.spec.EncodedKeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class RSA {
	private static final String IV = "JuniorEraBonitao";
	private static final int TAMANHO_CHAVE_RSA = 2048;
	
	public static void main(String[] args) {
		// Declaração de variáveis
		BufferedReader leitor = new BufferedReader(new InputStreamReader(System.in));
		String texto = "";
		String criptograma = "";
		String chavePublica = "";
		String chaveSecreta = "";
		String chaveCompartilhada = "";

		try {
			// Geração do par de chave do RSA
			gerarParDeChavesRSA();
			
			// Encriptação
			System.out.print("Digite o texto: ");
			texto = leitor.readLine();
			
			System.out.print("Digite a chave pública: ");
			chavePublica = leitor.readLine();
			
			criptograma = encriptar(texto, chavePublica);
			System.out.println("Criptograma: " + criptograma);
			
			// Decriptação
			System.out.print("Digite o criptograma: ");
			criptograma = leitor.readLine();
			
			System.out.print("Digite a chave secreta: ");
			chaveSecreta = leitor.readLine();
			
			System.out.print("Digite a chave compartilhada: ");
			chaveCompartilhada = leitor.readLine();
			
			texto = decriptar(criptograma, chaveSecreta, chaveCompartilhada);
			System.out.println("Texto original: " + texto);
		} catch (Exception erro) {
			System.out.println(erro);
		}
	}
	
	private static void gerarParDeChavesRSA() throws Exception {
		KeyPairGenerator geradorDeChaves = KeyPairGenerator.getInstance("RSA");
		geradorDeChaves.initialize(TAMANHO_CHAVE_RSA);
		KeyPair parDeChaves = geradorDeChaves.generateKeyPair();
		
		System.out.println("Chave pública: " + Base64.getEncoder().encodeToString(
											   parDeChaves.getPublic().getEncoded()));
		System.out.println("Chave secreta: " + Base64.getEncoder().encodeToString(
											   parDeChaves.getPrivate().getEncoded()));
	}
	
	private static String encriptar(String texto, String chavePublica) throws Exception {
		byte[] chaveCompartilhada = new byte[100];
		new SecureRandom().nextBytes(chaveCompartilhada);
		
		System.out.println("Chave compartilhada: " + encriptarRSA(chaveCompartilhada, chavePublica));
		
		return encriptarAES(texto, calcularHash(chaveCompartilhada));
	}
	
	private static String decriptar(String criptograma, String chaveSecreta, String chaveCompartilhada) throws Exception {
		return decriptarAES(criptograma, calcularHash(decriptarRSA(chaveCompartilhada, chaveSecreta)));
	}
	
	private static String encriptarRSA(byte[] texto, String chavePublica) throws Exception {
		Cipher cifra = Cipher.getInstance("RSA");
		EncodedKeySpec specDaChavePublica = new X509EncodedKeySpec(Base64.getDecoder().decode(chavePublica));
		cifra.init(Cipher.ENCRYPT_MODE, KeyFactory.getInstance("RSA").generatePublic(specDaChavePublica));
		return Base64.getEncoder().encodeToString(cifra.doFinal(texto));
	}
	
	private static byte[] decriptarRSA(String criptograma, String chaveSecreta) throws Exception {
		Cipher cifra = Cipher.getInstance("RSA");
		EncodedKeySpec specDaChaveSecreta = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(chaveSecreta));
		cifra.init(Cipher.DECRYPT_MODE, KeyFactory.getInstance("RSA").generatePrivate(specDaChaveSecreta));
		return cifra.doFinal(Base64.getDecoder().decode(criptograma));
	}
	
	private static String encriptarAES(String texto, byte[] senha) throws Exception {
		SecretKey chave = new SecretKeySpec(senha, "AES");
		IvParameterSpec objIV = new IvParameterSpec(IV.getBytes("utf-8"));
		Cipher cifra = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cifra.init(Cipher.ENCRYPT_MODE, chave, objIV);
		return Base64.getEncoder().encodeToString(cifra.doFinal(texto.getBytes("utf-8")));
	}

	private static String decriptarAES(String criptograma, byte[] senha) throws Exception {
		SecretKey chave = new SecretKeySpec(senha, "AES");
		IvParameterSpec objIV = new IvParameterSpec(IV.getBytes("utf-8"));
		Cipher cifra = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cifra.init(Cipher.DECRYPT_MODE, chave, objIV);
		return new String(cifra.doFinal(Base64.getDecoder().decode(criptograma)), "utf-8");
	}
	
	private static byte[] calcularHash(byte[] texto) throws Exception {
		MessageDigest objHash = MessageDigest.getInstance("SHA-256");
		objHash.reset();
		objHash.update(texto);
		return objHash.digest();
	}
}
