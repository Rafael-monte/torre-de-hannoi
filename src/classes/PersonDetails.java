package classes;

import java.time.LocalDate;

public class PersonDetails {

    private Integer numeroJogadas = 0;
    private String nomeJogador = "";
    private LocalDate dataFinalJogo = LocalDate.now();

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
        return String.format("\n- %s: %d jogadas *(Jogo finalizado em %s)*\n", this.nomeJogador, this.numeroJogadas, toDDMMYYYY(this.dataFinalJogo));
    }

    private String toDDMMYYYY(LocalDate dataFinalJogo) {
        return String.format("%s/%s/%s", String.valueOf(dataFinalJogo.getDayOfMonth()), String.valueOf(dataFinalJogo.getMonthValue()), String.valueOf(dataFinalJogo.getYear()));
    }
}
