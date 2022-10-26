import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

public class Leitura {
	public ArvoreBuscaBinaria lerValores(String caminhoArquivo) throws IOException {
		String values = Files.readString(Paths.get(caminhoArquivo), StandardCharsets.UTF_8);

		ArvoreBuscaBinaria arvoreBuscaBinaria = new ArvoreBuscaBinaria(0);

		Stream<Integer> parsedValues = Arrays.stream(values.split("\\s+"))
				.map(Integer::parseInt);

		return arvoreBuscaBinaria;
	}
}
