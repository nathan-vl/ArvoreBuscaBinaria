import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class Leitura {
	public ArvoreBuscaBinaria lerValores(String caminhoArquivo) throws Exception {
		String values = null;
		try {
			values = Files.readString(Paths.get(caminhoArquivo), StandardCharsets.UTF_8);
		} catch (IOException e) {
			throw new Exception("Erro ao ler arquivo");
		}

		Stream<Integer> parsedValues = Arrays.stream(values.split("\\s+")).map(Integer::parseInt);

		if (parsedValues.count() == 0) {
			throw new Exception("Arquivo sem números");
		}

		List<Integer> valores = parsedValues.toList();

		ArvoreBuscaBinaria arvoreBuscaBinaria = new ArvoreBuscaBinaria(valores.get(0));

		for (int i = 1; i < valores.size(); i++) {
			arvoreBuscaBinaria.inserir(valores.get(i));
		}

		return arvoreBuscaBinaria;
	}
}
