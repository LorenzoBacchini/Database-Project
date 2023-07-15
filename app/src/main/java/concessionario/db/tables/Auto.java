package concessionario.db.tables;

import java.sql.Date;
import java.util.Optional;

public class Auto {
    private final String targa;
    private final int anno_di_immatricolazione;
    private final String numero_di_telaio;
    private final String marca;
    private final String modello;
    private final boolean venduta;
    private final Optional<Date> data_di_fine_noleggio;

    public Auto(String targa, int anno_di_immatricolazione, String numero_di_telaio, String marca, String modello,
            boolean venduta, Optional<Date> data_di_fine_noleggio) {
        this.targa = targa;
        this.anno_di_immatricolazione = anno_di_immatricolazione;
        this.numero_di_telaio = numero_di_telaio;
        this.marca = marca;
        this.modello = modello;
        this.venduta = venduta;
        this.data_di_fine_noleggio = data_di_fine_noleggio;
    }

    public String getTarga() {
        return targa;
    }

    public int getAnno_di_immatricolazione() {
        return anno_di_immatricolazione;
    }

    public String getNumero_di_telaio() {
        return numero_di_telaio;
    }

    public String getMarca() {
        return marca;
    }

    public String getModello() {
        return modello;
    }

    public boolean isVenduta() {
        return venduta;
    }

    public Optional<Date> getData_di_fine_noleggio() {
        return data_di_fine_noleggio;
    }

    
}
