import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;



public class DistribuicaoChave {
	private static BigInteger  p = new BigInteger("102031405123416071809152453627382938465749676859789");
	private static BigInteger g = new BigInteger("1234567890123456789012345");
	
	public static void main(String[] args) {
		
		BufferedReader leitor = new BufferedReader(
				new InputStreamReader(
				System.in));
		
		BigInteger minhaChaveSecreta = null;
		BigInteger minhaChavePublica = null;
		BigInteger chavePublicaDoOutro = null;
		BigInteger nossaChaveCompartilhada = null;
		
		// Entrada de dados
		try {
		System.out.println("Digite sua chave secreta: ");
		minhaChaveSecreta = new BigInteger(leitor.readLine());
		
		minhaChavePublica = g.modPow(minhaChaveSecreta, p);
		System.out.println("Chave publica: " + minhaChavePublica);
		
		System.out.println("Digite a chave publica do outro: ");
		chavePublicaDoOutro = new BigInteger(leitor.readLine());
		
		
		// Processamento 
		nossaChaveCompartilhada = chavePublicaDoOutro.modPow(minhaChaveSecreta, p);
		System.out.println("Nossa Chave compartilhada: " + nossaChaveCompartilhada);
		
		
		
		
		
		} catch (Exception erro) {
			System.out.println(erro);
		}
	}
}
