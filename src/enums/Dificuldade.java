package enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Dificuldade {
    FACIL("FACIL", "F"),
    MEDIO("MEDIO", "M"),
    DIFICIL("DIFICIL", "D");

    private String labelDificuldade;
    private String siglaDificuldade;

     private Dificuldade(String labelDificuldade, String siglaDificuldade) {
        this.labelDificuldade = labelDificuldade;
        this.siglaDificuldade = siglaDificuldade;
    }

    public static Dificuldade getDificuldadeBySigla(final String sigla) {
        return Arrays.stream(values()).filter(dificuldade -> dificuldade.getSiglaDificuldade().equals(sigla))
                .findFirst().get();
    }

    public String getLabelDificuldade() {
        return labelDificuldade;
    }



    public String getSiglaDificuldade() {
        return siglaDificuldade;
    }

    public static List<String> getAllSiglas() {
         return Arrays.stream(values()).map(Dificuldade::getSiglaDificuldade).collect(Collectors.toList());
    }

    public static String showDificuldades() {
        return Arrays.stream(values()).map(Dificuldade::formatDificuldade).collect(Collectors.joining());
    }

    private static String formatDificuldade(Dificuldade dificuldade) {
         return String.format("%s - (%s)\n", dificuldade.labelDificuldade, dificuldade.siglaDificuldade);
    }
}
