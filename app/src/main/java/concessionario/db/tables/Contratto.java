package concessionario.db.tables;

import java.util.Optional;

public class Contratto {
    private final int numero_di_contratto;
    private final Optional<Long> azienda;
    private final Optional<String> privato;
    private final String dipendente;
    private final Optional<Integer> sconto;
    private final String auto;

    public Contratto(int numero_di_contratto, Optional<Long> azienda, Optional<String> privato,
            String dipendente, Optional<Integer> sconto, String auto) {
        this.numero_di_contratto = numero_di_contratto;
        this.azienda = azienda;
        this.privato = privato;
        this.dipendente = dipendente;
        this.sconto = sconto;
        this.auto = auto;
    }

    public int getNumero_di_contratto() {
        return numero_di_contratto;
    }

    public Optional<Long> getAzienda() {
        return azienda;
    }

    public Optional<String> getPrivato() {
        return privato;
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
