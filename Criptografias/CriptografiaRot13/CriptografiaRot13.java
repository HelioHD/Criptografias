import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CriptografiaRot13 {
	private static final int ESPACO_AMOSTRAL = 28;
	public static void main(String[] args) {
		// Declaração de variáveis
		char[] vetor = new char[ESPACO_AMOSTRAL];
		String texto = "";
		String criptograma = "";
		
		// Entrada de dados
		texto = lerTextoDoTeclado();
		
		// Processamento
		montarVetorDePermutacoes(vetor);
		criptograma = criptografar(texto, vetor);
		
		// Saída de dados
		System.out.println(criptograma);
	}
	
	private static String lerTextoDoTeclado() {
		String retorno = "";
		BufferedReader leitor = new BufferedReader(
								new InputStreamReader(
								System.in));
		
		try {
			System.out.print("Digite o texto: ");
			retorno = leitor.readLine();
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return retorno;
	}
	
	private static void montarVetorDePermutacoes(char[] vetor) {
		vetor[0] = 'a';
		vetor[1] = 'b';
		vetor[2] = 'c';
		vetor[3] = 'd';
		vetor[4] = 'e';
		vetor[5] = 'f';
		vetor[6] = 'g';
		vetor[7] = 'h';
		vetor[8] = 'i';
		vetor[9] = 'j';
		vetor[10] = 'k';
		vetor[11] = 'l';
		vetor[12] = 'm';
		vetor[13] = 'n';
		vetor[14] = 'o';
		vetor[15] = 'p';
		vetor[16] = 'q';
		vetor[17] = 'r';
		vetor[18] = 's';
		vetor[19] = 't';
		vetor[20] = 'u';
		vetor[21] = 'v';
		vetor[22] = 'w';
		vetor[23] = 'x';
		vetor[24] = 'y';
		vetor[25] = 'z';
		vetor[26] = 'A';
		vetor[27] = ' '; 		 	
	}
	
	private static String criptografar(String texto, char[] vetor) {
		String retorno = "";
		
		for (int i = 0 ; i < texto.length() ; i++) {
			retorno += encontrarSubstituto(texto.charAt(i), vetor);
		}
		
		return retorno;
	}
	
	private static char encontrarSubstituto(char letra, char[] vetor) {
		char retorno = letra;
		
		for (int i = 0 ; i < vetor.length ; i++) {
			if (letra == vetor[i]) {
				int indiceSubstituto = (i + (ESPACO_AMOSTRAL / 2));
				if (indiceSubstituto >= ESPACO_AMOSTRAL) {
					indiceSubstituto -= ESPACO_AMOSTRAL;
				}
				retorno = vetor[indiceSubstituto];
				break;
			}
		}
		
		return retorno;
	}
}
