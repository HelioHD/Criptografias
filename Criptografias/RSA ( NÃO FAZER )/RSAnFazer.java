import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.spec.EncodedKeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.Cipher;

public class RSAnFazer {
public static void main(String[] args) {
	BufferedReader leitor = new BufferedReader(new InputStreamReader(System.in));
	String chavePublica ="";
	String chaveSecreta = "";
	String texto = "";
	String criptograma = "";
	
	try {
		KeyPairGenerator geradorDeChaves = KeyPairGenerator.getInstance("RSA");
		geradorDeChaves.initialize(2048);
		KeyPair parDeChaves = geradorDeChaves.generateKeyPair();
		System.out.println("Chave pública: " + Base64.getEncoder().encodeToString(parDeChaves.getPublic().getEncoded()));
		System.out.println("Chave secreta: " + Base64.getEncoder().encodeToString(parDeChaves.getPrivate().getEncoded()));
		
		System.out.println("Digite o texto: ");
		texto = leitor.readLine();
		System.out.println("Digite a chave publica: ");
		chavePublica = leitor.readLine();
		
		criptograma = encriptar(texto, chavePublica);
		System.out.println("Criptograma: " + criptograma);
		
		System.out.println("Digite o criptograma: ");
		criptograma = leitor.readLine();
		
		System.out.println("Digite a chave Secreta: ");
		chaveSecreta = leitor.readLine();
		
		texto = decriptar(criptograma, chaveSecreta);
		System.out.println("Texto Original:" + texto);
		
		
	}catch(Exception erro) {
		System.out.println(erro);
	}
	
}

private static String encriptar(String texto, String chavePublica) throws Exception{
	Cipher cifra = Cipher.getInstance("RSA");
	EncodedKeySpec specDaChavePublica = new X509EncodedKeySpec(Base64.getDecoder().decode(chavePublica));
	cifra.init(Cipher.ENCRYPT_MODE, KeyFactory.getInstance("RSA").generatePublic(specDaChavePublica));
	return Base64.getEncoder().encodeToString(cifra.doFinal(texto.getBytes("utf-8")));
	}

private static String decriptar(String criptograma, String chaveSecreta) throws Exception{
	Cipher cifra = Cipher.getInstance("RSA");
	EncodedKeySpec specDaChaveSecreta = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(chaveSecreta));
	cifra.init(Cipher.DECRYPT_MODE, KeyFactory.getInstance("RSA").generatePrivate(specDaChaveSecreta));
	return new String(cifra.doFinal(Base64.getDecoder().decode(criptograma)),"utf-8");
	}
}
