package classes;

import java.time.LocalDate;

public class PersonDetails {

    private Integer numeroJogadas = 0;
    private String nomeJogador = "";
    private final LocalDate dataFinalJogo = LocalDate.now();

    public Integer getNumeroJogadas() {
        return numeroJogadas;
    }

    public void setNumeroJogadas(Integer numeroJogadas) {
        this.numeroJogadas = numeroJogadas;
    }

    public String getNomeJogador() {
        return nomeJogador;
    }

    public void setNomeJogador(String nomeJogador) {
        this.nomeJogador = nomeJogador;
    }


    public String getPlayerDetailsAsLine() {
        return String.format("\n- %s: %d jogadas *(Jogo finalizado em %s)*\n", this.getNomeJogador(), this.getNumeroJogadas(), toDDMMYYYY(this.dataFinalJogo));
    }

    private String toDDMMYYYY(LocalDate dataFinalJogo) {
        return String.format("%d/%d/%d", dataFinalJogo.getDayOfMonth(), dataFinalJogo.getMonthValue(), dataFinalJogo.getYear());
    }
}
