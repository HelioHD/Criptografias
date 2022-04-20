 package cifraDeVigenere;

import java.io.BufferedReader;
import java.io.InputStreamReader;



public class criptografiaDeVigenere  {
 public static void main(String[] args) throws Exception {
	// declaração de variaveis 
	 BufferedReader leitor = new BufferedReader(new InputStreamReader(System.in));
	 String entrada = "";
	 String chave = "";
	 String opcao = "";
	 String saida = "";
	 
	 // Entrada de dadps
	 System.out.print("Digite o texto: ");
	 entrada = leitor.readLine();
	 System.out.print("Digite a chave: ");
	 chave = leitor.readLine();
	 System.out.print("Digite E para encriptografar ou D para decriptografar: ");
	 opcao = leitor.readLine();
	 
	 // Processamento 
	 
	 if (opcao.equalsIgnoreCase("E")) {
		 saida = encriptar(entrada, chave);
	 } else {
		 saida = decriptar(entrada, chave);
	 }
	 
	System.out.println(saida);
}

private static String encriptar(String entrada, String chave) {
	String retorno = "";
	
	for (int i = 0; i < entrada.length(); i++) {
		int letraDoTexto = entrada.charAt(i);
		int letraDaChave = chave.charAt(i % chave.length());
		int letraDoXOR = (letraDoTexto ^ letraDaChave);
		String caractere = Integer.toHexString(letraDoXOR);
		
		retorno += ((caractere.length() == 2 ? "" : "0") + caractere);
	}
	
	return retorno;
}
 

private static String decriptar(String entrada, String chave) {
	String retorno = "";
	
	for (int i = 0; i < entrada.length(); i+=2) {
		String letraDoTexto = entrada.substring(i, i + 2);
		int letraDaChave = chave.charAt(i/2 % chave.length());
		char letraDoXOR = (char) (Integer.parseInt(letraDoTexto, 16) ^ letraDaChave);
		
		
		retorno += letraDoXOR;
	}
	
	return retorno;
}

}
