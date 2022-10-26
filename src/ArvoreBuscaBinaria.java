import java.util.*;

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
	 *
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

	/**
	 * Função privada utilizada para remover o valor passado do nó
	 * 
	 * @param no    nó da arvore onde o valor deverá ser removido
	 * @param valor valor a ser removido
	 * @return retorna true quando a remoção ocorre com sucesso; e
	 *         retorna false quando o nó é vazio, ou seja, null.
	 *
	 */
	private boolean remover(No no, int valor) {
		if (no == null)
			return false;

		if (valor > no.valor)
			return remover(no.direita, valor);

		if (valor < no.valor)
			return remover(no.esquerda, valor);

		if (no.esquerda == null && no.direita == null) {
			no = null;
			return true;
		}

		if (no.esquerda == null) {
			no = no.direita;
			return true;
		}

		if (no.direita == null) {
			no = no.esquerda;
			return true;
		}

		no.valor = popFolhaMaisDireita(no);
		return true;
	}

	public boolean remover(int valor) {
		return remover(raiz, valor);
	}

	private int popFolhaMaisDireita(No no) {
		if (no.direita != null) {
			return popFolhaMaisDireita(no.direita);
		}

		int result = no.valor;
		no = no.esquerda;

		return result;
	}

	/*
	 * log(n)
	 */
	private int enesimoElemento(No no, int n) {
		if (n == no.tamanhoArvoreEsquerda + 1)
			return no.valor;

		if (n < no.tamanhoArvoreEsquerda + 1)
			return enesimoElemento(no.esquerda, n);

		return enesimoElemento(no.direita, n - no.tamanhoArvoreEsquerda - 1);
	}

	public int enesimoElemento(int n) {
		return enesimoElemento(raiz, n);
	}

	private int posicao(No no, int n, int x) {
		if (no != null && x == no.valor)
			return no.tamanhoArvoreEsquerda + 1;

		if (no.esquerda != null && x < no.valor)
			return posicao(no.esquerda, n + 1, x);

		if (no.direita != null && x > no.valor)
			return posicao(no.direita, n + 1, x);

		return -1;
	}

	private int posicao(int x) {
		return posicao(raiz, 1, x);
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

	public boolean ehCompleta(No no) {
		int max = maxNivel(raiz);
		
		if(no.esquerda == null || no.direita == null) {
			int nivel = getNivel(no, raiz);
			if(max == nivel || max - 1 == nivel) {
				return true;
			}else{
				return false;
			}
		}
		
		if (no.esquerda != null) {
			return ehCompleta(no.esquerda);
		}

		if (no.direita != null) {
			return ehCompleta(no.direita);
		}
		
		return true;
	}
	
	public boolean ehCompleta() {
		return ehCompleta(raiz);
	}

	private int getNivel(No no, No raiz) {
		if (no.valor > raiz.valor) {
			return 1 + getNivel(no, raiz.direita);
		}
		if (no.valor < raiz.valor) {
			return 1 + getNivel(no, raiz.esquerda);
		}
		return 1;
	}

	public int maxNivel(No no) {
		int max = 1;
		if (no.esquerda != null) {
			int maxAux = maxNivel(no.esquerda);
			if(maxAux > max)
				max = maxAux;
		}
		if (no.direita != null) {
			int maxAux = maxNivel(no.direita);
			if(maxAux > max)
				max = maxAux;
		}
		if(no.esquerda == null && no.direita == null)
			max = getNivel(no, raiz);
		return max;
	}

	private String pre_ordem(No no) {
		String retorno = "" + no.valor;
		if (no.esquerda != null) {
			retorno += " " + pre_ordem(no.esquerda);
		}
		if (no.direita != null) {
			retorno += " " + pre_ordem(no.direita);
		}
		return retorno;
	}

	public String pre_ordem() {
		return pre_ordem(raiz);
	}

	public void imprimeArvore(int s) {
		if (s == 1 || s == 2) {
			if (s == 1) {
				System.out.println(formato1(raiz));
			} else if (s == 2) {
				System.out.println(formato2(raiz));
			}
		}
	}

	public String formato1(No no) {
		String retorno = "(" + no.valor;
		if (no.esquerda != null) {
			retorno += " " + formato1(no.esquerda);
		}
		if (no.direita != null) {
			retorno += " " + formato1(no.direita);
		}
		return retorno + ")";
	}

	public String formato2(No no) {
		String retorno = "(" + no.valor;
		if (no.esquerda != null) {
			retorno += " " + formato2(no.esquerda);
		}
		if (no.direita != null) {
			retorno += " " + formato2(no.direita);
		}
		return retorno + ")";
	}

	public static void main(String[] args) {
		ArvoreBuscaBinaria arvore = new ArvoreBuscaBinaria(5);
		arvore.inserir(3);
		arvore.inserir(8);
		arvore.inserir(6);
		arvore.inserir(9);
		
		arvore.imprimeArvore(1);
		
		System.out.println(arvore.maxNivel(arvore.raiz));

		System.out.println(arvore.ehCompleta());
	}
}
