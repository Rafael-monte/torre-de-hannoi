package game;

import classes.Pilha;
import classes.ScoreSetter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {

    private Scanner teclado = new Scanner(System.in);

    private List<Pilha> pilhasJogo = new ArrayList<>();

    private static Game game = null;

    private final Integer GAP_PILHAS = 2;

    private Integer numeroJogadas = 0;

    private String nomeJogador = "";

    private ScoreSetter scoreSetter = new ScoreSetter();

    private Game() {
        this.perguntarQtdeEPopularPilhas();
    }


    public static Game start() {
        if (game == null) {
            return new Game();
        }
        return null;
    }

    private void perguntarQtdeEPopularPilhas() {
        System.out.println("Bem vindo ao torre de Hanoi!");
        System.out.print("Por favor insira a quantidade de pilhas que deseja no jogo: ");
        int numeroDePilhas = this.teclado.nextInt();
        if (numeroDePilhas <= 0) {
            System.out.println("Larga a mão de ser safado, tente novamente!");
            this.perguntarQtdeEPopularPilhas();
            return;
        } else {
            numeroDePilhas+=(GAP_PILHAS);
        }
        System.out.print("Agora insira o tamanho das pilhas: ");
        int tamanhoPilhas = this.teclado.nextInt();

        if (tamanhoPilhas <= 0) {
            System.out.println("Para de gracinha mano, tenta dnv pqp");
            this.perguntarQtdeEPopularPilhas();
            return;
        }

        System.out.println("Gerando pilhas");
        for (int i = 0; i < numeroDePilhas; i++) {
            this.pilhasJogo.add(new Pilha(tamanhoPilhas, (i > (numeroDePilhas - (this.GAP_PILHAS + 1)))));
        }
        System.out.println("Pronto!");
        this.printarJogo();
        this.iniciarMovimentos();
    }

    private void iniciarMovimentos() {
        while (!this.jogoFinalizado()) {
            this.realizarJogada();
        }
        this.encerrarJogo();
    }

    private void encerrarJogo() {
        System.out.println(String.format("Parabéns, você venceu! Foram necessarias apenas %d jogadas!", this.numeroJogadas));
        String resposta;
        do {
            System.out.print("\nDeseja salvar um recorde? (S/N): ");
            resposta = this.teclado.nextLine();
        }
        while(!resposta.equalsIgnoreCase("S") && !resposta.equalsIgnoreCase("N"));
        if (resposta.equalsIgnoreCase("S")) {
            System.out.print("\nPor favor, escreva seu nome: ");
            this.nomeJogador = this.teclado.nextLine();
            this.scoreSetter.registrarNovoJogador(this.nomeJogador, this.numeroJogadas);
        }
    }

    private void realizarJogada() {
        this.numeroJogadas++;
        System.out.print("Escolha a pilha que deseja remover um elemento: ");
        int pilhaOrigem = this.teclado.nextInt();
        System.out.print("\nEscolha a pilha que deseja inserir: ");
        int pilhaDestino = this.teclado.nextInt();
        if (!this.isIndicesValidos(pilhaOrigem, pilhaDestino)) {
            System.out.println(String.format("Indice inválido! Insira um valor entre 1 e %d", pilhaDestino));
            this.realizarJogada();
            return;
        }
        if (this.isJogadaValida(this.pilhasJogo.get(pilhaOrigem - 1), this.pilhasJogo.get(pilhaDestino - 1))) {
            this.swap(this.pilhasJogo.get(pilhaOrigem - 1), this.pilhasJogo.get(pilhaDestino - 1));
            this.printarJogo();
        } else {
            this.realizarJogada();
            return;
        }
    }

    private void swap(Pilha pilhaOrigem, Pilha pilhaDestino) {
        pilhaDestino.empilha(pilhaOrigem.desempilha());
    }

    private boolean isIndicesValidos(int pilhaOrigem, int pilhaDestino) {
        return indiceValido(pilhaOrigem) && indiceValido(pilhaDestino);
    }

    private boolean indiceValido(int indicePilha) {
       return indicePilha >= 1 && indicePilha <= this.pilhasJogo.size();
    }

    private boolean isJogadaValida(Pilha pilhaOrigem, Pilha pilhaDestino) {
        if (pilhaOrigem.isPilhaVazia()) {
            System.out.println("A pilha de origem está vazia, escolha outra!");
            return false;
        }

        if (pilhaDestino.isPilhaCheia()) {
            System.out.println("A pilha de destino está cheia, escolha outra!");
            return false;
        }

        //TODO: futuro modo "hard", não podendo ter o numero do topo diferente entre as duas pilhas
//        if (!pilhaOrigem.elementoDoTopo().equals(pilhaDestino.elementoDoTopo()) && !pilhaDestino.isPilhaVazia()) {
//            System.out.println("Os elementos das pilhas de origem e destino são diferentes! Jogue novamente!");
//            return false;
//        }

        return true;
    }

    private boolean jogoFinalizado() {
        return this.pilhasJogo.stream().allMatch(pilha -> pilha.isPilhaFinalizada(this.pilhasJogo.size() - this.GAP_PILHAS));
    }

    private void printarJogo() {
        for (int i=0; i<this.pilhasJogo.size(); i++) {
            System.out.print("PILHA "+(i+1)+": ");
            this.pilhasJogo.get(i).printarPilha();
        }
    }


}
