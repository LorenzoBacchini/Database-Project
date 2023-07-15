package concessionario.db.tables;

public class Marca {
    private final String nome;
    private final String nazione;
    
    public Marca(String nome, String nazione) {
        this.nome = nome;
        this.nazione = nazione;
    }

    public String getNome() {
        return nome;
    }

    public String getNazione() {
        return nazione;
    }
}
