import java.util.Optional;

public class ArvoreBuscaBinaria {
	Node raiz;

	public boolean isValido(Node node) {

		return node.esquerda == null
				|| (node.value > node.esquerda.value && isValido(node.esquerda)) && node.direita == null
				|| (node.value < node.direita.value && isValido(node.direita));
	}

	public boolean isValido() {
		return isValido(raiz);
	}

	private Node buscar(Node node, Double valor) {
		if (node == null)
			return null;
		else if (valor < node.value)
			return buscar(node.esquerda, valor);
		else if (valor > node.value)
			return buscar(node.direita, valor);
		else
			return node;
	}

	public Node buscar(Double valor) {
		return buscar(raiz, valor);
	}

	private void inserir(Node node, Double valor) throws Exception {
		throw new Exception("undefined");
	}

	public void inserir(Double valor) throws Exception {
		inserir(raiz, valor);
	}
	
	private void remover(Node node, Double valor) throws Exception {
		throw new Exception("valor não existe");
	}

	public void remover(Double valor) throws Exception {
		remover(raiz, valor);
	}
}
