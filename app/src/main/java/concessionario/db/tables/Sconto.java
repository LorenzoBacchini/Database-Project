package concessionario.db.tables;

public class Sconto {
    private final int fatturato;
    private final int percentuale_di_sconto;
    
    public Sconto(int fatturato, int percentuale_di_sconto) {
        this.fatturato = fatturato;
        this.percentuale_di_sconto = percentuale_di_sconto;
    }

    public int getFatturato() {
        return fatturato;
    }

    public int getPercentuale_di_sconto() {
        return percentuale_di_sconto;
    }
}
