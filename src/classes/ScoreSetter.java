package classes;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ScoreSetter {

    private final PersonDetails playerDetails = new PersonDetails();

    public void registrarNovoJogador(String nomePessoa, Integer numeroJogadas) {
        this.playerDetails.setNomeJogador(nomePessoa);
        this.playerDetails.setNumeroJogadas(numeroJogadas);
        try {
            this.adicionarLinhaAoScore();
        } catch(IOException ioe) {
            System.out.println("Ocorreu um erro ao salvar score! "+ioe.getMessage());
        }
    }

    private void adicionarLinhaAoScore() throws IOException {
        File metadataFile = new File(Path.of("src/scores/scores.md").toUri());
        FileWriter fw = new FileWriter(metadataFile, true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(this.playerDetails.getPlayerDetailsAsLine());
        bw.close();
        fw.close();
    }


}
