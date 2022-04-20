
package crptAES;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class CriptografiaAES {
	public static void main(String[] args) {
		// Declaração de variáveis
		BufferedReader leitor = new BufferedReader(
								new InputStreamReader(
								System.in));
		
		String texto = "";
		String IV = "";
		String senha = "";
		String criptograma = "";
		
		try {
			// Entrada de dados
			System.out.print("Digite o texto: ");
			texto = leitor.readLine();
			
			System.out.print("Digite o IV: ");
			IV = leitor.readLine();
			
			System.out.print("Digite a senha: ");
			senha = leitor.readLine();
			
			// Processamento inicialização
			SecretKey chave = new SecretKeySpec(senha.getBytes("utf-8"), "AES");
			IvParameterSpec objIV = new IvParameterSpec(IV.getBytes("utf-8"));
			Cipher cifra = Cipher.getInstance("AES/CBC/PKCS5Padding");
			
			// Processamento encriptração
			cifra.init(Cipher.ENCRYPT_MODE, chave, objIV);
			criptograma = Base64.getEncoder().encodeToString(
						  cifra.doFinal(texto.getBytes("utf-8")));
			System.out.println(criptograma);
			
			// Processamento decriptração
			cifra.init(Cipher.DECRYPT_MODE, chave, objIV);
			texto = new String(cifra.doFinal(Base64.getDecoder().decode(criptograma)), "utf-8");
			System.out.println(texto);
		} catch (Exception erro) {
			System.out.println(erro);
		}
	}
}
