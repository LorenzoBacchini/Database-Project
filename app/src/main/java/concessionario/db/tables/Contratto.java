package concessionario.db.tables;

import java.util.Optional;

public class Contratto {
    private final int numero_di_contratto;
    private final Optional<Integer> azienda;
    private final Optional<String> privatoString;
    private final String dipendente;
    private final Optional<Integer> sconto;
    private final String auto;

    public Contratto(int numero_di_contratto, Optional<Integer> azienda, Optional<String> privatoString,
            String dipendente, Optional<Integer> sconto, String auto) {
        this.numero_di_contratto = numero_di_contratto;
        this.azienda = azienda;
        this.privatoString = privatoString;
        this.dipendente = dipendente;
        this.sconto = sconto;
        this.auto = auto;
    }

    public int getNumero_di_contratto() {
        return numero_di_contratto;
    }

    public Optional<Integer> getAzienda() {
        return azienda;
    }

    public Optional<String> getPrivatoString() {
        return privatoString;
    }

    public String getDipendente() {
        return dipendente;
    }

    public Optional<Integer> getSconto() {
        return sconto;
    }

    public String getAuto() {
        return auto;
    }
}
