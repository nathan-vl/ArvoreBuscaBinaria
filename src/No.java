public class No {
	int valor;

	int altura;

	int tamanhoArvoreEsquerda = 0;
	int tamanhoArvoreDireita = 0;

	No esquerda;
	No direita;

	public No(int valor) {
		this.valor = valor;

		altura = 1;
		tamanhoArvoreEsquerda = 0;
		tamanhoArvoreDireita = 0;
	}
}
