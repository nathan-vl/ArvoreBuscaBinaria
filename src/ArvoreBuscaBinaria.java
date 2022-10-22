public class ArvoreBuscaBinaria {
	Node raiz;

	public boolean isValido(Node node) {
		return (node.esquerda == null || node.valor > node.esquerda.valor && isValido(node.esquerda))
				&& (node.direita == null || node.valor < node.direita.valor && isValido(node.direita));
	}

	public boolean isValido() {
		return isValido(raiz);
	}

	private Node buscar(Node node, Double valor) {
		if (node == null)
			return null;

		if (valor < node.valor)
			return buscar(node.esquerda, valor);

		if (valor > node.valor)
			return buscar(node.direita, valor);

		return node;
	}

	public Node buscar(Double valor) {
		return buscar(raiz, valor);
	}

	private void inserir(Node node, Double valor) throws Exception {
		if (node == null)
			node = new Node(valor);
		else if (valor < node.valor)
			inserir(node.esquerda, valor);
		else if (valor > node.valor)
			inserir(node.direita, valor);
		else
			throw new Exception("Valor já existe");
	}

	public void inserir(Double valor) throws Exception {
		inserir(raiz, valor);
	}

	private void remover(Node node, Double valor) throws Exception {
		if (node == null)
			throw new Exception("valor não existe");

		if (valor > node.valor) {
			remover(node.direita, valor);
			return;
		}
		if (valor < node.valor) {
			remover(node.esquerda, valor);
			return;
		}

		boolean isFolha = node.esquerda == null && node.direita == null;
		if (isFolha) {
			node = null;
			return;
		}

		if (node.esquerda == null) {
			node = node.direita;
			return;
		} else if (node.direita == null) {
			node = node.esquerda;
			return;
		}

		node.valor = popFolhaMaisDireita(node);
	}

	public void remover(Double valor) throws Exception {
		remover(raiz, valor);
	}

	private Double popFolhaMaisDireita(Node node) {
		if (node.direita != null) {
			return popFolhaMaisDireita(node.direita);
		}

		Double result = node.valor;
		node = node.esquerda;

		return result;
	}
}
