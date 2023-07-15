package concessionario.db.tables;

import java.sql.Date;

public class Noleggio {
    private final int numero_di_contratto;
    private final Date data_di_inizio_noleggio;
    private final Date data_di_fine_noleggio;
    
    public Noleggio(int numero_di_contratto, Date data_di_inizio_noleggio, Date data_di_fine_noleggio) {
        this.numero_di_contratto = numero_di_contratto;
        this.data_di_inizio_noleggio = data_di_inizio_noleggio;
        this.data_di_fine_noleggio = data_di_fine_noleggio;
    }

    public int getNumero_di_contratto() {
        return numero_di_contratto;
    }

    public Date getData_di_inizio_noleggio() {
        return data_di_inizio_noleggio;
    }

    public Date getData_di_fine_noleggio() {
        return data_di_fine_noleggio;
    }
}
