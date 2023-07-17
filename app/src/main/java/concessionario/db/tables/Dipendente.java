package concessionario.db.tables;

import java.sql.Date;

public class Dipendente {
    private final String nome;
    private final String cognome;
    private final String codice_fiscale;
    private final long numero_di_telefono;
    private final int numero_di_contratti;
    private final int stipendio;
    private final Date data_di_assunzione;
    
    public Dipendente(String nome, String cognome, String codice_fiscale, long numero_di_telefono,
            int numero_di_contratti, int stipendio, Date data_di_assunzione) {
        this.nome = nome;
        this.cognome = cognome;
        this.codice_fiscale = codice_fiscale;
        this.numero_di_telefono = numero_di_telefono;
        this.numero_di_contratti = numero_di_contratti;
        this.stipendio = stipendio;
        this.data_di_assunzione = data_di_assunzione;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getCodice_fiscale() {
        return codice_fiscale;
    }

    public long getNumero_di_telefono() {
        return numero_di_telefono;
    }

    public int getNumero_di_contratti() {
        return numero_di_contratti;
    }

    public int getStipendio() {
        return stipendio;
    }

    public Date getData_di_assunzione() {
        return data_di_assunzione;
    }
}
