import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
public class Main {
    public static void main(String[] args) {
        AVL arvore = new AVL();
        Random random = new Random();
        ArrayList<Integer> numeros = new ArrayList<>();
        System.out.println("Inserindo números...\n");
        for (int i = 0; i < 100; i++) {
            int numero = random.nextInt(1001) - 500;
            numeros.add(numero);
            arvore.raiz = arvore.inserir(arvore.raiz, numero);
        }
        System.out.println("Árvore após inserções:\n");

        arvore.imprimir(arvore.raiz);
        System.out.println("\nA árvore é AVL? " + arvore.verificarAVL(arvore.raiz));
        // REMOVER 20 NÚMEROS
        Collections.shuffle(numeros);
        System.out.println("\nRemovendo 20 números:\n");
        for (int i = 0; i < 20; i++) {
            int valor = numeros.get(i);
            System.out.println("Removido: " + valor);
            arvore.raiz = arvore.remover(arvore.raiz, valor);
        }
        System.out.println("\nÁrvore após remoções:\n");
        arvore.imprimir(arvore.raiz);
        System.out.println("\nA árvore continua AVL? " + arvore.verificarAVL(arvore.raiz));
    }
}