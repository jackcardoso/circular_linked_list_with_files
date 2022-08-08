/* ******************************************** */
// Curso de Tecnologia em Sistemas de Computação
// Disciplina: Programação Orientada a Objetos
// AD2 1° semestre de 2022.

// Aluno: Jackson Cardoso de Oliveira
// Matrícula: 20213050225
// Polo: Nova Iguaçu
/* ********************************************/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


/* Estrutura do No */
class No {
    String info;
    int pos;
    No prox;

    No(String s, int p) {
        info = s;
        pos = p;
        prox = null;
    }
};


class ListaCircular {

    /* Função para excluir em cada passo o No */
    static No remover(No cabeca_ref, int passo) {
        No cabeca = cabeca_ref;
        // If list is empty, simply return.
        if (cabeca == null) {
            return null;
        }

        // Pega dois ponteiros, atual e anterior
        No atual = cabeca, anterior = null;
        while (true) {

            for (int i = 0; i < passo; i++) {
                anterior = atual;
                atual = atual.prox;
            }

            if (atual.prox == cabeca && atual == cabeca) {
                cabeca = cabeca.prox;
                break;
            }
            else if (atual == cabeca) {
                anterior = cabeca;
                while (anterior.prox != cabeca)
                    anterior = anterior.prox;
                    
                cabeca = atual.prox;
                anterior.prox = cabeca;
                cabeca_ref = cabeca;
            }
            // Se o No a ser delatado é o ultimo No
            else if (atual.prox == cabeca) {
                anterior.prox = cabeca;
            } else {
                anterior.prox = atual.prox;
            }
           atual = atual.prox;
        }
        return cabeca;
    }

    /*
     * Função para inserir um No 
     * no fim da Lista Circular
     */
    static No inserir(No cabeca_ref, String s, int pos) {
        // Cria um novo No
        No cabeca = cabeca_ref;
        No temp = new No(s, pos);
        /*
         * Se a Lista está vazia, define um novo No cabeca
         * que aponta para si mesmo.
         */
        if (cabeca == null) {
            temp.prox = temp;
            cabeca_ref = temp;
            return cabeca_ref;
        }
        /*
         * Percorre a lista para chegar ao último No
         * e insire o No
         */ 
        else {
            No temp1 = cabeca;
            while (temp1.prox != cabeca) {
                temp1 = temp1.prox;
            }
            temp1.prox = temp;
            temp.prox = cabeca;
        }
        return cabeca;
    }

    public static void main(String args[]) {

        No cabeca = null;

        String file = args[0];
        String fim = "FIM";
        int passo = 0;
        int cont = 1;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (fim.equals(line)) {
                    passo = Integer.parseInt(br.readLine());
                } else {
                    cabeca = inserir(cabeca, line, cont);
                    cont++;
                }

            }
        } catch (IOException e) {
            System.out.println("An error ocatualed.");
            e.printStackTrace();
        }

        // Remove o No a cada intervalo de passo
        // até sobrar somente um Nó 
        cabeca = remover(cabeca, passo);

        // Imprime no Terminal a posição do No restante
        System.out.println(cabeca.pos);
    }
}