class AVL {
    No raiz;
    int altura(No no) {
        if (no == null)
            return 0;

        return no.altura;
    }
    int maior(int a, int b) {
        return Math.max(a, b);
    }
    int fatorBalanceamento(No no) {

        if (no == null)
            return 0;

        return altura(no.esquerda) - altura(no.direita);
    }

    No rotacaoDireita(No y) {
        No x = y.esquerda;
        No t2 = x.direita;
        // rotação
        x.direita = y;
        y.esquerda = t2;
        y.altura = maior(altura(y.esquerda), altura(y.direita)) + 1;
        x.altura = maior(altura(x.esquerda), altura(x.direita)) + 1;

        return x;
    }
    No rotacaoEsquerda(No x) {
        No y = x.direita;
        No t2 = y.esquerda;
        // rotação
        y.esquerda = x;
        x.direita = t2;
        x.altura = maior(altura(x.esquerda), altura(x.direita)) + 1;
        y.altura = maior(altura(y.esquerda), altura(y.direita)) + 1;
        return y;
    }
    No inserir(No no, int valor) {
        if (no == null)
            return new No(valor);
        if (valor < no.valor)
            no.esquerda = inserir(no.esquerda, valor);
        else if (valor > no.valor)
            no.direita = inserir(no.direita, valor);
        else
            return no;
        no.altura = 1 + maior(altura(no.esquerda), altura(no.direita));
        int fb = fatorBalanceamento(no);
        // ROTAÇÃO
        // ESQUERDA ESQUERDA
        if (fb > 1 && valor < no.esquerda.valor)
            return rotacaoDireita(no);

        // DIREITA DIREITA
        if (fb < -1 && valor > no.direita.valor)
            return rotacaoEsquerda(no);

        // ESQUERDA DIREITA
        if (fb > 1 && valor > no.esquerda.valor) {

            no.esquerda = rotacaoEsquerda(no.esquerda);

            return rotacaoDireita(no);
        }

        // DIREITA ESQUERDA
        if (fb < -1 && valor < no.direita.valor) {

            no.direita = rotacaoDireita(no.direita);

            return rotacaoEsquerda(no);
        }

        return no;
    }

    // =========================
    // MENOR VALOR
    // =========================
    No menorValor(No no) {

        No atual = no;

        while (atual.esquerda != null)
            atual = atual.esquerda;

        return atual;
    }

    // =========================
    // REMOVER
    // =========================
    No remover(No raiz, int valor) {

        // remoção BST
        if (raiz == null)
            return raiz;

        if (valor < raiz.valor)
            raiz.esquerda = remover(raiz.esquerda, valor);

        else if (valor > raiz.valor)
            raiz.direita = remover(raiz.direita, valor);

        else {

            // um filho ou nenhum
            if ((raiz.esquerda == null) || (raiz.direita == null)) {

                No temp;

                if (raiz.esquerda != null)
                    temp = raiz.esquerda;
                else
                    temp = raiz.direita;

                // sem filhos
                if (temp == null) {
                    temp = raiz;
                    raiz = null;
                }

                // um filho
                else
                    raiz = temp;
            }

            // dois filhos
            else {

                No temp = menorValor(raiz.direita);

                raiz.valor = temp.valor;

                raiz.direita = remover(raiz.direita, temp.valor);
            }
        }

        // árvore tinha apenas um nó
        if (raiz == null)
            return raiz;

        // atualiza altura
        raiz.altura = maior(altura(raiz.esquerda), altura(raiz.direita)) + 1;

        // fator balanceamento
        int fb = fatorBalanceamento(raiz);

        // CASOS DE ROTAÇÃO

        // esquerda esquerda
        if (fb > 1 && fatorBalanceamento(raiz.esquerda) >= 0)
            return rotacaoDireita(raiz);

        // direita direita
        if (fb < -1 && fatorBalanceamento(raiz.direita) <= 0)
            return rotacaoEsquerda(raiz);

        // direita esquerda
        if (fb < -1 && fatorBalanceamento(raiz.direita) > 0) {

            raiz.direita = rotacaoDireita(raiz.direita);

            return rotacaoEsquerda(raiz);
        }

        // esquerda direita
        if (fb > 1 && fatorBalanceamento(raiz.esquerda) < 0) {

            raiz.esquerda = rotacaoEsquerda(raiz.esquerda);

            return rotacaoDireita(raiz);
        }

        

        return raiz;
    }

    void imprimir(No no) {

        if (no != null) {

            imprimir(no.esquerda);

            System.out.println(
                    "Valor: " + no.valor +
                    " | FB: " + fatorBalanceamento(no));

            imprimir(no.direita);
        }
    }
    boolean verificarAVL(No no) {

        if (no == null)
            return true;
        int fb = fatorBalanceamento(no);

        if (fb < -1 || fb > 1)
            return false;

        return verificarAVL(no.esquerda)
                && verificarAVL(no.direita);
    }
}