public class Node {
	Double valor;
	Node esquerda;
	Node direita;

	public Node(Double valor) {
		this.valor = valor;
	}

	public Node(Double valor, Node esquerda, Node direita) {
		this.valor = valor;
		this.esquerda = esquerda;
		this.direita = direita;
	}
}
