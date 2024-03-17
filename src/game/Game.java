package game;

import classes.Pilha;
import classes.ScoreSetter;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Game {

    private final Scanner teclado = new Scanner(System.in);

    private final List<Pilha> pilhasJogo = new ArrayList<>();

    private final Integer GAP_PILHAS = 2;

    private Integer numeroJogadas = 0;

    private final ScoreSetter scoreSetter = new ScoreSetter();

    private Game() {}

    public static void start() {
        new Game().perguntarQtdeEPopularPilhas();
    }

    private void perguntarQtdeEPopularPilhas() {
        System.out.println("Bem vindo ao torre de Hanoi!");
        System.out.print("Por favor insira a quantidade de pilhas que deseja no jogo: ");
        int numeroDePilhas = this.teclado.nextInt();
        if (numeroDePilhas <= 1) {
            System.out.println("Não é possível criar menos de 1 pilha. Tente novamente");
            this.perguntarQtdeEPopularPilhas();
            return;
        }
        System.out.print("Agora insira o tamanho das pilhas: ");
        int tamanhoPilhas = this.teclado.nextInt();
        if (tamanhoPilhas <= 0) {
            System.out.println("Não é possível colocar pilhas menores que zero. Tente novamente.");
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
        System.out.printf("Parabéns, você venceu! Foram necessarias apenas %d jogadas!%n", this.numeroJogadas);
        String resposta="";
        resposta = this.teclado.nextLine();
        while(!resposta.equalsIgnoreCase("S") && !resposta.equalsIgnoreCase("N")) {
            System.out.print("\nDeseja salvar um recorde? (S/N): ");
            resposta = this.teclado.nextLine();
        }
        if (resposta.equalsIgnoreCase("S")) {
            System.out.print("\nPor favor, escreva seu nome: ");
            String nomeJogador = this.teclado.nextLine();
            this.scoreSetter.registrarNovoJogador(nomeJogador, this.numeroJogadas);
        }
    }

    private void realizarJogada() {
        this.numeroJogadas++;
        System.out.print("Escolha a pilha que deseja remover um elemento: ");
        int pilhaOrigem = this.teclado.nextInt();
        System.out.print("\nEscolha a pilha que deseja inserir: ");
        int pilhaDestino = this.teclado.nextInt();
        boolean existePilhaInvalida = Stream.of(pilhaOrigem, pilhaDestino)
                .anyMatch(pilha->pilha < 1 || pilha > this.pilhasJogo.size());
        if (existePilhaInvalida) {
            System.out.printf("Indice inválido! Insira um valor entre 1 e %d%n", pilhaDestino);
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
        return true;
    }

    private boolean jogoFinalizado() {
        return this.pilhasJogo.stream().allMatch(pilha -> pilha.isPilhaFinalizada(this.pilhasJogo.size() - this.GAP_PILHAS));
    }

    private void printarJogo() {
        int counter = 1;
        for (Pilha pilha : this.pilhasJogo) {
            System.out.printf("PILHA: %d: ", counter);
            pilha.printarPilha();
            counter++;
        }
    }


}
