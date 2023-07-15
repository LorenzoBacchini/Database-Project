package concessionario.db.tables;

public class Privato {
    private final String nome;
    private final String cognome;
    private final String codice_fiscale;
    private final int numero_di_telefono;
    private final String e_mail;
    
    public Privato(String nome, String cognome, String codice_fiscale, int numero_di_telefono, String e_mail) {
        this.nome = nome;
        this.cognome = cognome;
        this.codice_fiscale = codice_fiscale;
        this.numero_di_telefono = numero_di_telefono;
        this.e_mail = e_mail;
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

    public int getNumero_di_telefono() {
        return numero_di_telefono;
    }

    public String getE_mail() {
        return e_mail;
    }
}
