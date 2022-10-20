public class Node {
	Double value;
	Node esquerda;
	Node direita;
	
	public Node(Double value) {
		this.value = value;
	}
	
	public Node(Double value, Node esquerda, Node direita) {
		this.value = value;
		this.esquerda = esquerda;
		this.direita = direita;
	}
}
