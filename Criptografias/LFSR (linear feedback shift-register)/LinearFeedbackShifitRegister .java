import java.io.File;
import java.io.FileWriter;
import java.nio.charset.Charset;

public class LinearFeedbackShifitRegister {
	public static int deslocar(int[] registrador, boolean shiftar, boolean tipo) {
	int retorno = 0;
	int xor = 0;
	
	
	retorno = registrador[0];
	if(shiftar) {
		if(! tipo ) {
			xor = registrador[31] ^ 
				  registrador[6] ^
				  registrador[4] ^
				  registrador[2] ^
				  registrador[1] ^
				  registrador[0];
		} else {
			xor = registrador[31] ^
				  registrador[6] ^
				  registrador[5] ^
				  registrador[1]; 
		}
		for (int i = 0 ; i < (registrador.length - 1); i++) {
			registrador[i] = registrador[i + 1];
		}
		registrador[31] = xor;
	}
			return  retorno;
}
 public static void main(String[] args) throws Exception{
	 // Declaração de variaveis
	int[] cabeca = new int[32];
	int[] gerador1 = new int[32];
	int[] gerador2 = new int[32];
	int operando1 = 0;
	int operando2 = 0;
	String caractere = "";
	FileWriter arquivo = new FileWriter(new File("C:\\Users\\helio.delfino\\Downloads\\lfsr.txt"));
	
	// Inicialização dos registradores
	for ( int i = 0; i < cabeca.length; i++) {
		cabeca[i] = ((int)(2 * Math.random()));
		gerador1[i] = ((int)(2 * Math.random()));
		gerador2[i] = ((int)(2 * Math.random()));

	}
	// Geração de numeros
for(int i = 0; i < 8000000 ; i++ ) {
	if (deslocar(cabeca, true, false)== 0) {
		operando1 = deslocar(gerador1, true, false);
		operando2 = deslocar(gerador2, false, true);
	} else {
		operando1 = deslocar(gerador1, false, false );
		operando2 = deslocar(gerador2, true, true);
	}
	caractere += (operando1 ^ operando2);
	
	if((i % 8 ) == 7) {
		System.out.println(caractere + " " + Integer.parseInt(caractere, 2)+ " " + ((char) Integer.parseInt(caractere ,2)));
		arquivo.write((char) Integer.parseInt(caractere ,2));
		caractere = "";
	}
}
arquivo.close();
}
}
