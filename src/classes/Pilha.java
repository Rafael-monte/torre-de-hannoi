package classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Pilha {
    private List<Integer> elementos = new ArrayList<>();
    private Integer tamMax;
    public final Integer PILHA_VAZIA = null;


    public Pilha(Integer tamanhoPilha, boolean pilhaVazia) {
        this.tamMax = tamanhoPilha;
        if (!pilhaVazia) {
            this.popularPilha();
        }
    }

    public void empilha(Integer element) {
        if (!isPilhaCheia()) {
            this.elementos.add(element);
        }
    }

    public Integer desempilha() {
        if (!this.isPilhaVazia()) {
            Integer el = this.elementos.get(this.elementos.size() - 1);
            this.elementos.remove(this.elementos.size() - 1);
            return el;
        }
        return null;
    }

    public boolean isPilhaCheia() {
        return this.elementos.size() == this.tamMax;
    }

    private void popularPilha() {
        Random geradorNumeroAleatorio = new Random();
        Integer valorGerado;
        for (int i = 0; i < tamMax; i++) {
            do {
                valorGerado = this.gerarNumeroParaPilha(geradorNumeroAleatorio);
            } while (this.elementoPresenteNaPilha(valorGerado));
            this.elementos.add(valorGerado);
        }
    }

    public void printarPilha() {
        System.out.print("[");
        for(int i = 0; i < this.elementos.size(); i++) {
            if (i != (this.tamMax - 1)) {
                System.out.print(this.elementos.get(i) + ", ");
            } else {
                System.out.print(this.elementos.get(i));
            }
        }
        System.out.print("]"+(this.elementos.size() == 0? "": "<-Topo da pilha")+"\n");
    }

    public Integer gerarNumeroParaPilha(Random geradorNumeroAleatorio) {
        return geradorNumeroAleatorio.nextInt(this.tamMax);
    }

    public boolean elementoPresenteNaPilha(Integer valorGerado) {
        return this.elementos.stream().anyMatch(el -> el.equals(valorGerado));
    }

    public boolean isPilhaVazia() {
        return this.elementos.isEmpty();
    }

    public Integer elementoDoTopo() {
        if (!this.isPilhaVazia()) {
            return this.elementos.get(this.elementos.size() - 1);
        }
        return this.PILHA_VAZIA;
    }

    public boolean isPilhaFinalizada(int numeroElementosIguais) {
       if (this.elementos.size() == numeroElementosIguais) {
           Integer elementoAmostra = this.elementos.get(0);
           return this.elementos.stream().allMatch(el -> el.equals(elementoAmostra));
       }
       return this.isPilhaVazia();
    }
}
