package concessionario.db.tables;

public class Modello {
    private final String marca;
    private final String nome;
    private final int numero_di_posti;
    private final int cilindrata;
    private final int potenza;
    private final int prezzo_di_vendita;
    private final int prezzo_di_noleggio;
    
    public Modello(String marca, String nome, int numero_di_posti, int cilindrata, int potenza, int prezzo_di_vendita,
            int prezzo_di_noleggio) {
        this.marca = marca;
        this.nome = nome;
        this.numero_di_posti = numero_di_posti;
        this.cilindrata = cilindrata;
        this.potenza = potenza;
        this.prezzo_di_vendita = prezzo_di_vendita;
        this.prezzo_di_noleggio = prezzo_di_noleggio;
    }

    public String getMarca() {
        return marca;
    }

    public String getNome() {
        return nome;
    }

    public int getNumero_di_posti() {
        return numero_di_posti;
    }

    public int getCilindrata() {
        return cilindrata;
    }

    public int getPotenza() {
        return potenza;
    }

    public int getPrezzo_di_vendita() {
        return prezzo_di_vendita;
    }

    public int getPrezzo_di_noleggio() {
        return prezzo_di_noleggio;
    }
}
