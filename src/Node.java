public class Node {
	int valor;
	Node esquerda;
	Node direita;

	public Node(int valor) {
		this.valor = valor;
	}

	public Node(int valor, Node esquerda, Node direita) {
		this.valor = valor;
		this.esquerda = esquerda;
		this.direita = direita;
	}
}
