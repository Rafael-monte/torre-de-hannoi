package classes;

import enums.Dificuldade;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class DificultyController {

    private String dificuldadeSelecionada;


    public void setDificuldade(Scanner teclado) {
        System.out.println("Escolha a dificuldade do jogo");
        System.out.println(Dificuldade.showDificuldades());
        System.out.print("Escolha a sua dificuldade: ");
        this.dificuldadeSelecionada = teclado.nextLine();
        while (!this.isDificuladadeValida()) {
            System.out.print("\nNão entendi a sua escolha, tente novamente: ");
            this.dificuldadeSelecionada = teclado.nextLine();
        }
    }

    public Dificuldade getDificuldadeSelecionada() {
        return Dificuldade.getDificuldadeBySigla(this.dificuldadeSelecionada);
    }

    private boolean isDificuladadeValida() {
        this.dificuldadeSelecionada = this.dificuldadeSelecionada.toUpperCase();
        return Dificuldade.getAllSiglas().contains(this.dificuldadeSelecionada);
    }

}
