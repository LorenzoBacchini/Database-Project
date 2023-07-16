package concessionario.db.tables;

public class Azienda {
    private final long partita_iva;
    private final String nome;
    private final String sede;
    private final int fatturato;
    
    public Azienda(long partita_iva, String nome, String sede, int fatturato) {
        this.partita_iva = partita_iva;
        this.nome = nome;
        this.sede = sede;
        this.fatturato = fatturato;
    }

    public long getPartita_iva() {
        return partita_iva;
    }

    public String getNome() {
        return nome;
    }

    public String getSede() {
        return sede;
    }

    public int getFatturato() {
        return fatturato;
    }    
}
