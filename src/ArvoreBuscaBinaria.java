public class ArvoreBuscaBinaria {
	public No raiz;

	public ArvoreBuscaBinaria(int raiz) {
		this.raiz = new No(raiz);
	}

	/**
	 * Função privada utilizada para fazer a busca do valor passado
	 *
	 * @param no    nó da arvore onde o valor deverá ser buscado
	 * @param valor valor a ser consultado
	 * @return retorna null quando o nó é vazio; e
	 *         retorna o nó quando encontra o valor procurado.
	 */
	private No buscar(No no, int valor) {
		if (no == null)
			return null;

		if (valor < no.valor)
			return buscar(no.esquerda, valor);

		if (valor > no.valor)
			return buscar(no.direita, valor);

		return no;
	}

	public No buscar(int valor) {
		return buscar(raiz, valor);
	}

	/**
	 * Função privada utilizada para inserir o valor passado no nó
	 *
	 * @param no    nó da arvore onde o valor deverá ser inserido
	 * @param valor valor a ser inserido
	 * @return retorna true quando o valor é inserido; e
	 *         retorna false quando nenhuma das condições é atendida.
	 */
	private boolean inserir(No no, int valor) {
		if (valor < no.valor) {
			no.tamanhoArvoreEsquerda += 1;
			if (no.esquerda == null) {
				no.esquerda = new No(valor);
				return true;
			}

			return inserir(no.esquerda, valor);
		}

		if (valor > no.valor) {
			no.tamanhoArvoreDireita += 1;
			if (no.direita == null) {
				no.direita = new No(valor);
				return true;
			}

			return inserir(no.direita, valor);
		}

		return false;
	}

	public boolean inserir(int valor) {
		return inserir(raiz, valor);
	}

	private No buscarNoPai(No no, int valor) throws Exception {
		if (no == null)
			return null;

		if (valor < no.valor) {
			if (no.esquerda == null)
				return null;

			if (no.esquerda.valor == valor)
				return no;

			return buscar(no.esquerda, valor);
		}

		if (valor > no.valor) {
			if (no.direita == null)
				return null;

			if (no.direita.valor == valor)
				return no;

			return buscar(no.direita, valor);
		}

		throw new Exception("O nó passado já possui o valor");
	}

	/**
	 * Função privada utilizada para remover o valor passado do nó
	 *
	 * @param no    nó da arvore onde o valor deverá ser removido
	 * @param valor valor a ser removido
	 * @return retorna true quando a remoção ocorre com sucesso; e
	 *         retorna false quando o nó é vazio, ou seja, null.
	 */
	private boolean remover(No no, int valor) {
		if (no == null)
			return false;

		try {
			No noEncontrado = buscarNoPai(raiz, valor);

			if (noEncontrado.esquerda.valor == valor) {
				if (noEncontrado.esquerda.esquerda == null && noEncontrado.esquerda.direita == null) {
					noEncontrado.esquerda = null;
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	public boolean remover(int valor) {
		return remover(raiz, valor);
	}

	private int popFolhaMaisDireita(No no) {
		No noAtual = no;
		while (noAtual.direita != null && noAtual.direita.direita != null) {
			noAtual = noAtual.direita;
		}

		int valor = noAtual.direita.valor;
		noAtual.direita = null;

		return valor;
	}

	/*
	 * log(n)
	 */
	private Integer enesimoElemento(No no, int n) {
		if (no == null) {
			return null;
		}

		if (n == no.tamanhoArvoreEsquerda + 1)
			return no.valor;

		if (n < no.tamanhoArvoreEsquerda + 1)
			return enesimoElemento(no.esquerda, n);

		return enesimoElemento(no.direita, n - no.tamanhoArvoreEsquerda - 1);
	}

	public Integer enesimoElemento(int n) {
		return enesimoElemento(raiz, n);
	}

	private int posicao(No no, int x) throws Exception {
		if (no == null)
			throw new Exception("Valor não encontrado");

		if (x < no.valor)
			return posicao(no.esquerda, x);

		if (x > no.valor)
			return posicao(no.direita, x) + no.tamanhoArvoreEsquerda + 1;

		return no.tamanhoArvoreEsquerda + 1;
	}

	public int posicao(int x) throws Exception {
		return posicao(raiz, x);
	}

	/*
	 * log(n)
	 */
	public int mediana() {
		int tamanhoArvore = raiz.tamanhoArvoreEsquerda + raiz.tamanhoArvoreDireita + 1;

		if (tamanhoArvore % 2 == 1)
			return enesimoElemento((tamanhoArvore + 1) / 2);

		return enesimoElemento(tamanhoArvore / 2);
	}

	private double soma(No no) {
		if (no == null)
			return 0;
		return no.valor + soma(no.esquerda) + soma(no.direita);
	}

	private double media(No no, int x) {
		if (no == null)
			return 0;

		if (x < no.valor)
			return media(no.esquerda, x);

		if (x > no.valor)
			return media(no.direita, x);

		return soma(no) / (no.tamanhoArvoreDireita + no.tamanhoArvoreEsquerda + 1);
	}

	public double media(int x) {
		return media(raiz, x);
	}

	public boolean ehCheia(No no) {
		if ((no.esquerda == null && no.direita == null) && maxNivel(raiz) != getNivel(no, raiz))
			return false;

		if ((no.esquerda == null && no.direita != null) || (no.esquerda != null && no.direita == null))
			return false;

		if (no.esquerda != null && !ehCheia(no.esquerda))
			return false;

		if (no.direita != null && !ehCheia(no.direita))
			return false;

		return true;
	}

	// eh cheia quando os seus nós possuem 0 ou 2 filhos
	public boolean ehCheia() {
		return ehCheia(raiz);
	}

	public boolean ehCompleta(No no) {
		if (no.esquerda == null || no.direita == null) {
			int max = maxNivel(raiz);
			int nivel = getNivel(no, raiz);
			if (!(max == nivel || max - 1 == nivel))
				return false;
		}

		if (no.esquerda != null)
			return ehCompleta(no.esquerda);

		if (no.direita != null)
			return ehCompleta(no.direita);

		return true;
	}

	public boolean ehCompleta() {
		return ehCompleta(raiz);
	}

	private int getNivel(No no, No raiz) {
		if (no.valor > raiz.valor)
			return 1 + getNivel(no, raiz.direita);

		if (no.valor < raiz.valor)
			return 1 + getNivel(no, raiz.esquerda);

		return 1;
	}

	public int maxNivel(No no) {
		int max = 1;
		if (no.esquerda != null) {
			int maxAux = maxNivel(no.esquerda);
			if (maxAux > max)
				max = maxAux;
		}

		if (no.direita != null) {
			int maxAux = maxNivel(no.direita);
			if (maxAux > max)
				max = maxAux;
		}

		if (no.esquerda == null && no.direita == null)
			max = getNivel(no, raiz);

		return max;
	}

	private String pre_ordem(No no) {
		String retorno = "" + no.valor;

		if (no.esquerda != null)
			retorno += " " + pre_ordem(no.esquerda);

		if (no.direita != null)
			retorno += " " + pre_ordem(no.direita);

		return retorno;
	}

	public String pre_ordem() {
		return pre_ordem(raiz);
	}

	public void imprimeArvore(int s) {
		if (s == 1 || s == 2) {
			if (s == 1)
				formato1(raiz);
			else if (s == 2)
				System.out.println(formato2(raiz));
		}
	}

	public void formato1(No no) {
		int nivel = getNivel(no, raiz);
		for (int i = 0; i < nivel - 1; i++)
			System.out.print("\t");

		System.out.print(no.valor);
		for (int i = 0; i < maxNivel(raiz) - nivel + 1; i++)
			System.out.print("--------");

		System.out.println();
		if (no.esquerda != null)
			formato1(no.esquerda);

		if (no.direita != null)
			formato1(no.direita);
	}

	public String formato2(No no) {
		String retorno = "(" + no.valor;
		if (no.esquerda != null)
			retorno += " " + formato2(no.esquerda);

		if (no.direita != null)
			retorno += " " + formato2(no.direita);

		return retorno + ")";
	}

	public static void main(String[] args) {
		ArvoreBuscaBinaria arvore = null;
		try {
			arvore = Leitura.lerValores(args[0]);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return;
		}

		try {
			Leitura.interpretarComandos(arvore, args[1]);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
