package concessionario.db.tables;

import java.sql.Date;

public class Vendita {
    private final int numero_di_contratto;
    private final Date data_di_vendita;
    
    public Vendita(int numero_di_contratto, Date data_di_vendita) {
        this.numero_di_contratto = numero_di_contratto;
        this.data_di_vendita = data_di_vendita;
    }

    public int getNumero_di_contratto() {
        return numero_di_contratto;
    }

    public Date getData_di_vendita() {
        return data_di_vendita;
    }

    
}
