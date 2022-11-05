import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Leitura {
	public static ArvoreBuscaBinaria lerValores(String caminhoArquivo) throws Exception {
		String values = null;
		try {
			values = Files.readString(Paths.get(caminhoArquivo), StandardCharsets.UTF_8);
		} catch (IOException e) {
			throw new Exception("Erro ao ler arquivo de valores");
		}

		String[] parsedValues = values.split(" ", 0);

		if (parsedValues.length == 0)
			throw new Exception("Arquivo sem números");

		ArvoreBuscaBinaria arvoreBuscaBinaria = new ArvoreBuscaBinaria(Integer.parseInt(parsedValues[0]));

		for (int i = 1; i < parsedValues.length; i++)
			arvoreBuscaBinaria.inserir(Integer.parseInt(parsedValues[i]));

		return arvoreBuscaBinaria;
	}

	private static Integer obterSegundoValorStringSeparadaEspaco(String string) {
		return Integer.parseInt(string.split(" ", 0)[1]);
	}

	private static String booleanToString(boolean b) {
		return b ? "SIM" : "NAO";
	}

	public static void interpretarComandos(ArvoreBuscaBinaria arvoreBuscaBinaria, String caminhoArquivo)
			throws Exception {
		List<String> linhas = null;

		try {
			linhas = Files.readAllLines(Paths.get(caminhoArquivo), StandardCharsets.UTF_8);
		} catch (IOException e) {
			throw new Exception("Erro ao ler arquivo de instruções");
		}

		for (String linha : linhas) {
			if (linha.startsWith("ENESIMO")) {
				System.out.println(arvoreBuscaBinaria.enesimoElemento(
						obterSegundoValorStringSeparadaEspaco(linha)));
			} else if (linha.startsWith("POSICAO")) {
				System.out.println(arvoreBuscaBinaria.posicao(
						obterSegundoValorStringSeparadaEspaco(linha)));
			} else if (linha.startsWith("IMPRIMA")) {
				arvoreBuscaBinaria.imprimeArvore(
						obterSegundoValorStringSeparadaEspaco(linha));
			} else if (linha.startsWith("REMOVA")) {
				Integer n = obterSegundoValorStringSeparadaEspaco(linha);
				if (arvoreBuscaBinaria.remover(n)) {
					System.out.println(n + " removido");
				} else {
					System.out.println(n + " não está na árvore, não pode ser removido");
				}
			} else if (linha.startsWith("MEDIANA")) {
				System.out.println(arvoreBuscaBinaria.mediana());
			} else if (linha.startsWith("MEDIA")) {
				System.out.println(arvoreBuscaBinaria.media(
						obterSegundoValorStringSeparadaEspaco(linha)));
			} else if (linha.startsWith("CHEIA")) {
				System.out.println(booleanToString(arvoreBuscaBinaria.ehCheia()));
			} else if (linha.startsWith("COMPLETA")) {
				System.out.println(booleanToString(arvoreBuscaBinaria.ehCompleta()));
			} else if (linha.startsWith("PREORDEM")) {
				System.out.println(arvoreBuscaBinaria.pre_ordem());
			} else if (linha.startsWith("INSIRA")) {
				Integer n = obterSegundoValorStringSeparadaEspaco(linha);
				if (arvoreBuscaBinaria.inserir(n)) {
					System.out.println(n + " adicionado");
				} else {
					System.out.println(n + " já está na árvore, não pode ser inserido");
				}
			} else if (linha.startsWith("BUSCAR")) {
				Integer n = obterSegundoValorStringSeparadaEspaco(linha);
				if (arvoreBuscaBinaria.buscar(n) == null) {
					System.out.println("Chave não encontrada");
				} else {
					System.out.println("Chave encontrada");
				}
			} else {
				System.out.println("Comando desconhecido: " + linha.split(" ", 0)[0]);
			}
		}
	}
}
