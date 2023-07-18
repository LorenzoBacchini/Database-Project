/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package concessionario.graphics;

import concessionario.model.Logic;
import concessionario.utils.Pair;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import concessionario.db.tables.Auto;
import concessionario.db.tables.Azienda;
import concessionario.db.tables.Dipendente;
import concessionario.db.tables.Modello;
import concessionario.db.tables.Privato;

/**
 *
 * @author bacco
 */
public class View extends javax.swing.JFrame {
    private final Logic logic;
    private Map<String, JTextField> insertAutoFields = new HashMap<>();
    private Map<String, JTextField> insertPrivatiFields = new HashMap<>();
    private Map<String, JTextField> insertAziendeFields = new HashMap<>();
    private Map<String, JTextField> insertDipendentiFields = new HashMap<>();
    private Map<String, JTextField> insertVenditaPrivato = new HashMap<>();
    private Map<String, JTextField> insertNoleggioPrivato = new HashMap<>();
    private Map<String, JTextField> insertVenditaAzienda = new HashMap<>();
    private Map<String, JTextField> insertNoleggioAzienda= new HashMap<>();
    /**
     * Creates new form View
     */
    public View(final Logic logic) {
        super("Concessionario");
        initComponents();
        initLook();
        initGroups();
        this.logic = logic;
        this.setVisible(true);
    }

    private void loadAutoPiuRichiesta(){
        String[] colName = new String[3];
        colName[0] = "Marca";
        colName[1] = "Modello";
        colName[2] = "Numero di contratti";
        ((javax.swing.table.DefaultTableModel) this.TabellaAuto1.getModel()).setColumnIdentifiers(colName);
        List<String> info = this.logic.getAutoPiuRichiesta();
        Iterator<String> infoIterator = info.iterator();
        while(infoIterator.hasNext()){
            final String[] row = {infoIterator.next(), infoIterator.next(), infoIterator.next()};
            ((javax.swing.table.DefaultTableModel) this.TabellaAuto1.getModel()).addRow(row);
        }
    }

    private void loadAutoDisponibili(){
        String[] colName = new String[5];
        colName[0] = "Targa";
        colName[1] = "Anno di immatricolazione";
        colName[2] = "Numero di telaio";
        colName[3] = "Marca";
        colName[4] = "Modello";
        ((javax.swing.table.DefaultTableModel) this.TabellaAuto1.getModel()).setColumnIdentifiers(colName);
        for (Auto a : this.logic.getAutoDisponibili()) {
            final String[] row = {a.getTarga(), String.valueOf(a.getAnno_di_immatricolazione()), a.getNumero_di_telaio(), a.getMarca(),
                    a.getModello()};
            ((javax.swing.table.DefaultTableModel) this.TabellaAuto1.getModel()).addRow(row);
        }
    }

    private void loadHistoryPrivati(final List<String> info){
        String[] colName = new String[9];
        colName[0] = "Numero di contratto";
        colName[1] = "Targa";
        colName[2] = "Marca";
        colName[3] = "Modello";
        colName[4] = "Nome Privato";
        colName[5] = "Cognome Privato";
        colName[6] = "Data di inizio noleggio";
        colName[7] = "Data di fine noleggio";
        colName[8] = "Data di vendita";
        ((javax.swing.table.DefaultTableModel) this.TabellaAuto1.getModel()).setColumnIdentifiers(colName);
        Iterator<String> infoIterator = info.iterator();
        while(infoIterator.hasNext()){
            final String[] row = {infoIterator.next(), infoIterator.next(), infoIterator.next(), infoIterator.next(), infoIterator.next(),
                    infoIterator.next(), infoIterator.next(), infoIterator.next(), infoIterator.next()};
            ((javax.swing.table.DefaultTableModel) this.TabellaAuto1.getModel()).addRow(row);
        }
    }

    private void loadHistoryAzienda(final List<String> info){
        String[] colName = new String[9];
        colName[0] = "Numero di contratto";
        colName[1] = "Targa";
        colName[2] = "Marca";
        colName[3] = "Modello";
        colName[4] = "Nome Azienda";
        colName[5] = "Sede Azienda";
        colName[6] = "Data di inizio noleggio";
        colName[7] = "Data di fine noleggio";
        colName[8] = "Data di vendita";
        ((javax.swing.table.DefaultTableModel) this.TabellaAuto1.getModel()).setColumnIdentifiers(colName);
        Iterator<String> infoIterator = info.iterator();
        while(infoIterator.hasNext()){
            final String[] row = {infoIterator.next(), infoIterator.next(), infoIterator.next(), infoIterator.next(), infoIterator.next(),
                    infoIterator.next(), infoIterator.next(), infoIterator.next(), infoIterator.next()};
            ((javax.swing.table.DefaultTableModel) this.TabellaAuto1.getModel()).addRow(row);
        }
    }

    private void loadModello(){
        String[] colName = new String[7];
        colName[0] = "Marca";
        colName[1] = "Nome";
        colName[2] = "Numero di posti";
        colName[3] = "Cilindrata";
        colName[4] = "Potenza";
        colName[5] = "Prezzo di vendita";
        colName[6] = "Prezzo di noleggio(al giorno)";
        ((javax.swing.table.DefaultTableModel) this.TabellaAuto1.getModel()).setColumnIdentifiers(colName);
        for (Modello m : this.logic.getModello()) {
            final String[] row = {m.getMarca(), m.getNome(), String.valueOf(m.getNumero_di_posti()), String.valueOf(m.getCilindrata()),
                    String.valueOf(m.getPotenza()), String.valueOf(m.getPrezzo_di_vendita()), String.valueOf(m.getPrezzo_di_noleggio())};
            ((javax.swing.table.DefaultTableModel) this.TabellaAuto1.getModel()).addRow(row);
        }
    }

    private void loadAuto(){
        String[] colName = new String[7];
        colName[0] = "Targa";
        colName[1] = "Anno di immatricolazione";
        colName[2] = "Numero di telaio";
        colName[3] = "Marca";
        colName[4] = "Modello";
        colName[5] = "Venduta";
        colName[6] = "Data di fine noleggio";
        ((javax.swing.table.DefaultTableModel) this.TabellaAuto1.getModel()).setColumnIdentifiers(colName);
        for (Auto a : this.logic.getAuto()) {
            final String[] row = {a.getTarga(), String.valueOf(a.getAnno_di_immatricolazione()), a.getNumero_di_telaio(), a.getMarca(),
                    a.getModello(), String.valueOf(a.isVenduta()), a.getData_di_fine_noleggio().isPresent() ? String.valueOf(a.getData_di_fine_noleggio().get()) : null};
            ((javax.swing.table.DefaultTableModel) this.TabellaAuto1.getModel()).addRow(row);
        }
    }

    private void loadPrivati(){
        String[] colName = new String[5];
        colName[0] = "Nome";
        colName[1] = "Cognome";
        colName[2] = "Codice fiscale";
        colName[3] = "Numero di telefono";
        colName[4] = "E-Mail";
        ((javax.swing.table.DefaultTableModel) this.TabellaPrivati1.getModel()).setColumnIdentifiers(colName);
        for (Privato p : this.logic.getPrivati()) {
            final String[] row = {p.getNome(), p.getCognome(), p.getCodice_fiscale(), String.valueOf(p.getNumero_di_telefono()), p.getE_mail()};
            ((javax.swing.table.DefaultTableModel) this.TabellaPrivati1.getModel()).addRow(row);
        }
    }

    private void loadContrattiPrivato(final List<String> info){
        String[] colName = new String[6];
        colName[0] = "Numero di contratto";
        colName[1] = "Codice fiscale privato";
        colName[2] = "Codice fiscale dipendente";
        colName[3] = "Targa";
        colName[4] = "Marca";
        colName[5] = "Modello";
        ((javax.swing.table.DefaultTableModel) this.TabellaPrivati1.getModel()).setColumnIdentifiers(colName);
        Iterator<String> infoIterator = info.iterator();
        while(infoIterator.hasNext()){
            final String[] row = {infoIterator.next(), infoIterator.next(), infoIterator.next(), infoIterator.next(), infoIterator.next(),
                    infoIterator.next()};
            ((javax.swing.table.DefaultTableModel) this.TabellaPrivati1.getModel()).addRow(row);
        }
    }

    private void loadCostoContrattoPrivato(final List<String> info){
        String[] colName = new String[9];
        colName[0] = "Nome privato";
        colName[1] = "Cognome privato";
        colName[2] = "Codice fiscale privato";
        colName[3] = "Targa";
        colName[4] = "Marca";
        colName[5] = "Modello";
        colName[6] = "Prezzo vendita";
        colName[7] = "Prezzo noleggio";
        colName[8] = "Costo contratto";
        ((javax.swing.table.DefaultTableModel) this.TabellaPrivati1.getModel()).setColumnIdentifiers(colName);
        Iterator<String> infoIterator = info.iterator();
        while(infoIterator.hasNext()){
            final String[] row = {infoIterator.next(), infoIterator.next(), infoIterator.next(), infoIterator.next(), infoIterator.next(),
                    infoIterator.next(), infoIterator.next(), infoIterator.next(), infoIterator.next()};
            ((javax.swing.table.DefaultTableModel) this.TabellaPrivati1.getModel()).addRow(row);
        }
    }

    private void loadAziende(){
        String[] colName = new String[4];
        colName[0] = "Partita_iva";
        colName[1] = "Nome";
        colName[2] = "Sede";
        colName[3] = "Fatturato";
        ((javax.swing.table.DefaultTableModel) this.TabellaAziende1.getModel()).setColumnIdentifiers(colName);
        for (Azienda a : this.logic.getAziende()) {
            final String[] row = {String.valueOf(a.getPartita_iva()), a.getNome(), a.getSede(), String.valueOf(a.getFatturato())};
            ((javax.swing.table.DefaultTableModel) this.TabellaAziende1.getModel()).addRow(row);
        }
    }

    private void loadContrattiAzienda(final List<String> info){
        String[] colName = new String[6];
        colName[0] = "Numero di contratto";
        colName[1] = "Partita iva azienda";
        colName[2] = "Codice fiscale dipendente";
        colName[3] = "Targa";
        colName[4] = "Marca";
        colName[5] = "Modello";
        ((javax.swing.table.DefaultTableModel) this.TabellaAziende1.getModel()).setColumnIdentifiers(colName);
        Iterator<String> infoIterator = info.iterator();
        while(infoIterator.hasNext()){
            final String[] row = {infoIterator.next(), infoIterator.next(), infoIterator.next(), infoIterator.next(), infoIterator.next(),
                    infoIterator.next()};
            ((javax.swing.table.DefaultTableModel) this.TabellaAziende1.getModel()).addRow(row);
        }
    }

    private void loadCostoContrattoAzienda(List<String> info){
        String[] colName = new String[9];
        colName[0] = "Nome azienda";
        colName[1] = "Sede privato";
        colName[2] = "Partita iva azienda";
        colName[3] = "Targa";
        colName[4] = "Marca";
        colName[5] = "Modello";
        colName[6] = "Prezzo vendita";
        colName[7] = "Prezzo noleggio";
        colName[8] = "Costo contratto";
        ((javax.swing.table.DefaultTableModel) this.TabellaAziende1.getModel()).setColumnIdentifiers(colName);
        Iterator<String> infoIterator = info.iterator();
        while(infoIterator.hasNext()){
            final String[] row = {infoIterator.next(), infoIterator.next(), infoIterator.next(), infoIterator.next(), infoIterator.next(),
                    infoIterator.next(), infoIterator.next(), infoIterator.next(), infoIterator.next()};
            ((javax.swing.table.DefaultTableModel) this.TabellaAziende1.getModel()).addRow(row);
        }
    }

    private void loadDipendenti(){
        String[] colName = new String[7];
        colName[0] = "Nome";
        colName[1] = "Cognome";
        colName[2] = "Codice fiscale";
        colName[3] = "Numero di telefono";
        colName[4] = "Numero di contratti";
        colName[5] = "Stipendio";
        colName[6] = "Data di assunzione";
        ((javax.swing.table.DefaultTableModel) this.TabellaDipendenti1.getModel()).setColumnIdentifiers(colName);
        for (Dipendente d : this.logic.getDipendenti()) {
            final String[] row = {d.getNome(), d.getCognome(), d.getCodice_fiscale(), String.valueOf(d.getNumero_di_telefono()),
                    String.valueOf(d.getNumero_di_contratti()), String.valueOf(d.getStipendio()), String.valueOf(d.getData_di_assunzione())};
            ((javax.swing.table.DefaultTableModel) this.TabellaDipendenti1.getModel()).addRow(row);
        }
    }

    private void loadContratti(){
        String[] colName = new String[10];
        colName[0] = "Numero di contratto";
        colName[1] = "Partita iva azienda";
        colName[2] = "Codice fiscale privato";
        colName[3] = "Codice fiscale dipendente";
        colName[4] = "Sconto";
        colName[5] = "Percentuale di sconto";
        colName[6] = "Targa auto";
        colName[7] = "Data di vendita";
        colName[8] = "Data di inizio noleggio";
        colName[9] = "Data di fine noleggio";
        ((javax.swing.table.DefaultTableModel) this.TabellaContratti1.getModel()).setColumnIdentifiers(colName);
        List<String> info = this.logic.getContratti();
        Iterator<String> infoIterator = info.iterator();
        while(infoIterator.hasNext()){
            final String[] row = {infoIterator.next(), infoIterator.next(), infoIterator.next(), infoIterator.next(), infoIterator.next(),
                    infoIterator.next(), infoIterator.next(), infoIterator.next(), infoIterator.next(), infoIterator.next()};
            ((javax.swing.table.DefaultTableModel) this.TabellaContratti1.getModel()).addRow(row);
        }
    }

    /**
     * Method to inizialize the group of button and textField with the same
     * or correlated function
     */
    private void initGroups(){
        this.insertAutoFields.put("targa", this.InserisciTarga);
        this.insertAutoFields.put("anno_di_immatricolazione", this.InserisciAnno_di_immatricolazioe);
        this.insertAutoFields.put("numero_di_telaio", this.InserisciNumero_di_telaio);
        this.insertAutoFields.put("marca", this.InserisciMarca);
        this.insertAutoFields.put("modello", this.InserisciModello);

        this.insertPrivatiFields.put("nome", this.InserisciNome);
        this.insertPrivatiFields.put("cognome", this.InserisciCognome);
        this.insertPrivatiFields.put("codice_fiscale", this.InserisciCodice_fiscale);
        this.insertPrivatiFields.put("numero_di_telefono", this.InserisciNumero_di_telefono);
        this.insertPrivatiFields.put("e_mail", this.InserisciE_mail);

        this.insertAziendeFields.put("partita_iva", this.InserisciPartita_Iva);
        this.insertAziendeFields.put("nome", this.InserisciNomeAzienda);
        this.insertAziendeFields.put("sede", this.InserisciSede);
        this.insertAziendeFields.put("fatturato", this.InserisciFatturato);

        this.insertDipendentiFields.put("nome", this.InserisciNomeDipendente);
        this.insertDipendentiFields.put("cognome", this.InserisciCognomeDipendente);
        this.insertDipendentiFields.put("codice_fiscale", this.InserisciCodice_fiscaleDipendente);
        this.insertDipendentiFields.put("numero_di_telefono", this.InserisciNumero_di_telefonoDipendente);
        this.insertDipendentiFields.put("stipendio", this.InserisciStipendio);
        this.insertDipendentiFields.put("data_di_assunzione", this.InserisciData_di_assunzione);

        this.insertVenditaPrivato.put("codice_fiscalePrivato", this.InserisciCodice_fiscaleContrattoPrivato);
        this.insertVenditaPrivato.put("codice_fiscaleDipendente", this.InserisciCodice_fiscaleContrattoDipendente);
        this.insertVenditaPrivato.put("targa", this.InserisciTargaContrattoPrivato);
        
        this.insertNoleggioPrivato.put("codice_fiscalePrivato", this.InserisciCodice_fiscaleContrattoPrivato);
        this.insertNoleggioPrivato.put("codice_fiscaleDipendente", this.InserisciCodice_fiscaleContrattoDipendente);
        this.insertNoleggioPrivato.put("targa", this.InserisciTargaContrattoPrivato);
        this.insertNoleggioPrivato.put("data_di_fine_noleggio", this.InserisciData_di_fine_noleggioPrivato);

        this.insertVenditaAzienda.put("partita_iva", this.InserisciPartita_ivaContratto);
        this.insertVenditaAzienda.put("codice_fiscaleDipendente", this.InserisciCodice_fiscaleContrattoDipendenteAzienda);
        this.insertVenditaAzienda.put("targa", this.InserisciTargaContrattoAzienda);
        
        this.insertNoleggioAzienda.put("partita_iva", this.InserisciPartita_ivaContratto);
        this.insertNoleggioAzienda.put("codice_fiscaleDipendente", this.InserisciCodice_fiscaleContrattoDipendenteAzienda);
        this.insertNoleggioAzienda.put("targa", this.InserisciTargaContrattoAzienda);
        this.insertNoleggioAzienda.put("data_di_fine_noleggio", this.InserisciData_di_fine_noleggioAzienda);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        Contratti = new javax.swing.JPanel();
        OperazioniContratti = new javax.swing.JPanel();
        VisualizzaContratti = new javax.swing.JButton();
        jTextField26 = new javax.swing.JTextField();
        InserisciCodice_fiscaleContrattoPrivato = new javax.swing.JTextField();
        jTextField27 = new javax.swing.JTextField();
        InserisciCodice_fiscaleContrattoDipendente = new javax.swing.JTextField();
        InserisciTargaContrattoPrivato = new javax.swing.JTextField();
        jTextField28 = new javax.swing.JTextField();
        InserisciVenditaPrivati = new javax.swing.JButton();
        jTextField30 = new javax.swing.JTextField();
        InserisciData_di_fine_noleggioPrivato = new javax.swing.JTextField();
        InserisciNoleggioPrivato = new javax.swing.JButton();
        jTextField31 = new javax.swing.JTextField();
        InserisciPartita_ivaContratto = new javax.swing.JTextField();
        InserisciCodice_fiscaleContrattoDipendenteAzienda = new javax.swing.JTextField();
        jTextField32 = new javax.swing.JTextField();
        jTextField33 = new javax.swing.JTextField();
        InserisciTargaContrattoAzienda = new javax.swing.JTextField();
        jTextField35 = new javax.swing.JTextField();
        InserisciData_di_fine_noleggioAzienda = new javax.swing.JTextField();
        InserisciVenditaAzienda = new javax.swing.JButton();
        InserisciNoleggioAzienda = new javax.swing.JButton();
        TabellaContrattiPanel = new javax.swing.JPanel();
        TabellaContratti = new javax.swing.JScrollPane();
        TabellaContratti1 = new javax.swing.JTable();
        Privati = new javax.swing.JPanel();
        OperazioniPrivati = new javax.swing.JPanel();
        VisualizzaPrivati = new javax.swing.JButton();
        jTextField8 = new javax.swing.JTextField();
        InserisciNome = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        InserisciCognome = new javax.swing.JTextField();
        InserisciCodice_fiscale = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        InserisciNumero_di_telefono = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jTextField12 = new javax.swing.JTextField();
        InserisciE_mail = new javax.swing.JTextField();
        InserisciPrivato = new javax.swing.JButton();
        jTextField24 = new javax.swing.JTextField();
        Codice_fiscaleStoricoContratti = new javax.swing.JTextField();
        VisualizzaContrattiPrivato = new javax.swing.JButton();
        jTextField29 = new javax.swing.JTextField();
        Numero_di_contrattoPrivati = new javax.swing.JTextField();
        VisualizzaCostoContrattoPrivato = new javax.swing.JButton();
        TabellaPrivatiPanel = new javax.swing.JPanel();
        TabellaPrivati = new javax.swing.JScrollPane();
        TabellaPrivati1 = new javax.swing.JTable();
        Aziende = new javax.swing.JPanel();
        OperazioniAziende = new javax.swing.JPanel();
        VisualizzaAziende = new javax.swing.JButton();
        jTextField13 = new javax.swing.JTextField();
        InserisciPartita_Iva = new javax.swing.JTextField();
        jTextField14 = new javax.swing.JTextField();
        InserisciNomeAzienda = new javax.swing.JTextField();
        InserisciSede = new javax.swing.JTextField();
        jTextField15 = new javax.swing.JTextField();
        InserisciFatturato = new javax.swing.JTextField();
        jTextField16 = new javax.swing.JTextField();
        InserisciAzienda = new javax.swing.JButton();
        jTextField25 = new javax.swing.JTextField();
        Partita_ivaStoricoContratti = new javax.swing.JTextField();
        VisualizzaContrattiAzienda = new javax.swing.JButton();
        VisualizzaCostoContrattoAzienda = new javax.swing.JButton();
        Numero_di_contrattoAziende = new javax.swing.JTextField();
        jTextField34 = new javax.swing.JTextField();
        TabellaAziendePanel = new javax.swing.JPanel();
        TabellaAziende = new javax.swing.JScrollPane();
        TabellaAziende1 = new javax.swing.JTable();
        Dipendenti = new javax.swing.JPanel();
        OperazioniDIpendenti = new javax.swing.JPanel();
        VisualizzaDipendenti = new javax.swing.JButton();
        jTextField17 = new javax.swing.JTextField();
        InserisciNomeDipendente = new javax.swing.JTextField();
        jTextField18 = new javax.swing.JTextField();
        InserisciCognomeDipendente = new javax.swing.JTextField();
        InserisciCodice_fiscaleDipendente = new javax.swing.JTextField();
        jTextField19 = new javax.swing.JTextField();
        InserisciNumero_di_telefonoDipendente = new javax.swing.JTextField();
        jTextField20 = new javax.swing.JTextField();
        InserisciDipendente = new javax.swing.JButton();
        jTextField22 = new javax.swing.JTextField();
        jTextField23 = new javax.swing.JTextField();
        InserisciData_di_assunzione = new javax.swing.JTextField();
        InserisciStipendio = new javax.swing.JTextField();
        jTextField21 = new javax.swing.JTextField();
        Codice_fiscaleNumeroDiContratti = new javax.swing.JTextField();
        VisualizzaNumeroContratti = new javax.swing.JButton();
        TabellaDipendentiPanel = new javax.swing.JPanel();
        TabellaDipendenti = new javax.swing.JScrollPane();
        TabellaDipendenti1 = new javax.swing.JTable();
        Auto = new javax.swing.JPanel();
        OperazioniAuto = new javax.swing.JPanel();
        VisualizzaAuto = new javax.swing.JButton();
        InserisciTarga = new javax.swing.JTextField();
        InserisciAnno_di_immatricolazioe = new javax.swing.JTextField();
        InserisciNumero_di_telaio = new javax.swing.JTextField();
        InserisciMarca = new javax.swing.JTextField();
        InserisciModello = new javax.swing.JTextField();
        InserisciAuto = new javax.swing.JButton();
        VisualizzaModelli = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        StoricoTarga = new javax.swing.JTextField();
        VisualizzaStoricoPrivati = new javax.swing.JButton();
        VisualizzaStoricoAzienda = new javax.swing.JButton();
        jTextField7 = new javax.swing.JTextField();
        StatoTarga = new javax.swing.JTextField();
        VisualizzaStatoAuto = new javax.swing.JButton();
        StatoAuto = new javax.swing.JTextField();
        VisualizzaAutoDisponibili = new javax.swing.JButton();
        AutoPiuRichiesta = new javax.swing.JButton();
        TabellaAutoPanel = new javax.swing.JPanel();
        TabellaAuto = new javax.swing.JScrollPane();
        TabellaAuto1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Concessionario");
        setPreferredSize(new java.awt.Dimension(1500, 785));

        jTabbedPane1.setPreferredSize(new java.awt.Dimension(1584, 864));

        OperazioniContratti.setPreferredSize(new java.awt.Dimension(210, 210));

        VisualizzaContratti.setText("Visualizza contratti");
        VisualizzaContratti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VisualizzaContrattiActionPerformed(evt);
            }
        });

        jTextField26.setEditable(false);
        jTextField26.setText("Codice fiscale privato");
        jTextField26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField26ActionPerformed(evt);
            }
        });

        InserisciCodice_fiscaleContrattoPrivato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InserisciCodice_fiscaleContrattoPrivatoActionPerformed(evt);
            }
        });

        jTextField27.setEditable(false);
        jTextField27.setText("Codice fiscale dipendente");
        jTextField27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField27ActionPerformed(evt);
            }
        });

        InserisciCodice_fiscaleContrattoDipendente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InserisciCodice_fiscaleContrattoDipendenteActionPerformed(evt);
            }
        });

        jTextField28.setEditable(false);
        jTextField28.setText("Targa auto");
        jTextField28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField28ActionPerformed(evt);
            }
        });

        InserisciVenditaPrivati.setText("Inserisci vendita privato");
        InserisciVenditaPrivati.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InserisciVenditaPrivatiActionPerformed(evt);
            }
        });

        jTextField30.setEditable(false);
        jTextField30.setText("Data di fine noleggio");

        InserisciData_di_fine_noleggioPrivato.setText("YYYY-MM-DD");
        InserisciData_di_fine_noleggioPrivato.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                InserisciData_di_fine_noleggioPrivatoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                InserisciData_di_fine_noleggioPrivatoFocusLost(evt);
            }
        });

        InserisciNoleggioPrivato.setText("Inserisci noleggio privato");
        InserisciNoleggioPrivato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InserisciNoleggioPrivatoActionPerformed(evt);
            }
        });

        jTextField31.setEditable(false);
        jTextField31.setText("Partita iva azienda");
        jTextField31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField31ActionPerformed(evt);
            }
        });

        InserisciPartita_ivaContratto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InserisciPartita_ivaContrattoActionPerformed(evt);
            }
        });

        InserisciCodice_fiscaleContrattoDipendenteAzienda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InserisciCodice_fiscaleContrattoDipendenteAziendaActionPerformed(evt);
            }
        });

        jTextField32.setEditable(false);
        jTextField32.setText("Codice fiscale dipendente");
        jTextField32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField32ActionPerformed(evt);
            }
        });

        jTextField33.setEditable(false);
        jTextField33.setText("Targa auto");
        jTextField33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField33ActionPerformed(evt);
            }
        });

        jTextField35.setEditable(false);
        jTextField35.setText("Data di fine noleggio");

        InserisciData_di_fine_noleggioAzienda.setText("YYYY-MM-DD");
        InserisciData_di_fine_noleggioAzienda.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                InserisciData_di_fine_noleggioAziendaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                InserisciData_di_fine_noleggioAziendaFocusLost(evt);
            }
        });

        InserisciVenditaAzienda.setText("Inserisci vendita azienda");
        InserisciVenditaAzienda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InserisciVenditaAziendaActionPerformed(evt);
            }
        });

        InserisciNoleggioAzienda.setText("Inserisci noleggio azienda");
        InserisciNoleggioAzienda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InserisciNoleggioAziendaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout OperazioniContrattiLayout = new javax.swing.GroupLayout(OperazioniContratti);
        OperazioniContratti.setLayout(OperazioniContrattiLayout);
        OperazioniContrattiLayout.setHorizontalGroup(
            OperazioniContrattiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OperazioniContrattiLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(OperazioniContrattiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(OperazioniContrattiLayout.createSequentialGroup()
                        .addGroup(OperazioniContrattiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTextField30)
                            .addComponent(jTextField28, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField27, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField26, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(OperazioniContrattiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(InserisciTargaContrattoPrivato)
                            .addComponent(InserisciCodice_fiscaleContrattoDipendente)
                            .addComponent(InserisciCodice_fiscaleContrattoPrivato, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(InserisciData_di_fine_noleggioPrivato)))
                    .addGroup(OperazioniContrattiLayout.createSequentialGroup()
                        .addGroup(OperazioniContrattiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTextField35)
                            .addComponent(jTextField33, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField32, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField31, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(OperazioniContrattiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(InserisciTargaContrattoAzienda, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                            .addComponent(InserisciCodice_fiscaleContrattoDipendenteAzienda)
                            .addComponent(InserisciPartita_ivaContratto, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(InserisciData_di_fine_noleggioAzienda)))
                    .addGroup(OperazioniContrattiLayout.createSequentialGroup()
                        .addGroup(OperazioniContrattiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(VisualizzaContratti)
                            .addComponent(InserisciVenditaPrivati)
                            .addComponent(InserisciNoleggioPrivato)
                            .addComponent(InserisciVenditaAzienda)
                            .addComponent(InserisciNoleggioAzienda))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        OperazioniContrattiLayout.setVerticalGroup(
            OperazioniContrattiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OperazioniContrattiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(VisualizzaContratti)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(OperazioniContrattiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(OperazioniContrattiLayout.createSequentialGroup()
                        .addComponent(InserisciCodice_fiscaleContrattoPrivato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(InserisciCodice_fiscaleContrattoDipendente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(InserisciTargaContrattoPrivato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(OperazioniContrattiLayout.createSequentialGroup()
                        .addComponent(jTextField26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(OperazioniContrattiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(InserisciData_di_fine_noleggioPrivato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(InserisciVenditaPrivati)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(InserisciNoleggioPrivato)
                .addGap(57, 57, 57)
                .addGroup(OperazioniContrattiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(OperazioniContrattiLayout.createSequentialGroup()
                        .addComponent(InserisciPartita_ivaContratto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(InserisciCodice_fiscaleContrattoDipendenteAzienda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(InserisciTargaContrattoAzienda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(OperazioniContrattiLayout.createSequentialGroup()
                        .addComponent(jTextField31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(OperazioniContrattiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(InserisciData_di_fine_noleggioAzienda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(InserisciVenditaAzienda)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(InserisciNoleggioAzienda)
                .addContainerGap(316, Short.MAX_VALUE))
        );

        TabellaContrattiPanel.setPreferredSize(new java.awt.Dimension(1066, 542));

        TabellaContratti1.setAutoCreateRowSorter(true);
        TabellaContratti1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        TabellaContratti.setViewportView(TabellaContratti1);

        javax.swing.GroupLayout TabellaContrattiPanelLayout = new javax.swing.GroupLayout(TabellaContrattiPanel);
        TabellaContrattiPanel.setLayout(TabellaContrattiPanelLayout);
        TabellaContrattiPanelLayout.setHorizontalGroup(
            TabellaContrattiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TabellaContrattiPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TabellaContratti, javax.swing.GroupLayout.DEFAULT_SIZE, 988, Short.MAX_VALUE)
                .addContainerGap())
        );
        TabellaContrattiPanelLayout.setVerticalGroup(
            TabellaContrattiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TabellaContrattiPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TabellaContratti)
                .addContainerGap())
        );

        javax.swing.GroupLayout ContrattiLayout = new javax.swing.GroupLayout(Contratti);
        Contratti.setLayout(ContrattiLayout);
        ContrattiLayout.setHorizontalGroup(
            ContrattiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ContrattiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(OperazioniContratti, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TabellaContrattiPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE)
                .addContainerGap())
        );
        ContrattiLayout.setVerticalGroup(
            ContrattiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ContrattiLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ContrattiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(OperazioniContratti, javax.swing.GroupLayout.DEFAULT_SIZE, 742, Short.MAX_VALUE)
                    .addComponent(TabellaContrattiPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 742, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Contratti", Contratti);

        OperazioniPrivati.setPreferredSize(new java.awt.Dimension(210, 210));

        VisualizzaPrivati.setText("VIsualizza Privati");
        VisualizzaPrivati.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VisualizzaPrivatiActionPerformed(evt);
            }
        });

        jTextField8.setEditable(false);
        jTextField8.setText("Nome");
        jTextField8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField8ActionPerformed(evt);
            }
        });

        jTextField9.setEditable(false);
        jTextField9.setText("Cognome");

        InserisciCognome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InserisciCognomeActionPerformed(evt);
            }
        });

        jTextField10.setEditable(false);
        jTextField10.setText("Codice fiscale");

        jTextField11.setEditable(false);
        jTextField11.setText("Numero di telefono");

        jTextField12.setEditable(false);
        jTextField12.setText("E-mail");

        InserisciE_mail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InserisciE_mailActionPerformed(evt);
            }
        });

        InserisciPrivato.setText("Inserisci Privato");
        InserisciPrivato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InserisciPrivatoActionPerformed(evt);
            }
        });

        jTextField24.setEditable(false);
        jTextField24.setText("Codice fiscale");
        jTextField24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField24ActionPerformed(evt);
            }
        });

        Codice_fiscaleStoricoContratti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Codice_fiscaleStoricoContrattiActionPerformed(evt);
            }
        });

        VisualizzaContrattiPrivato.setText("Visualizza contratti");
        VisualizzaContrattiPrivato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VisualizzaContrattiPrivatoActionPerformed(evt);
            }
        });

        jTextField29.setEditable(false);
        jTextField29.setText("Numero di contratto");
        jTextField29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField29ActionPerformed(evt);
            }
        });

        Numero_di_contrattoPrivati.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Numero_di_contrattoPrivatiActionPerformed(evt);
            }
        });

        VisualizzaCostoContrattoPrivato.setText("Visualizza costo contratto");
        VisualizzaCostoContrattoPrivato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VisualizzaCostoContrattoPrivatoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout OperazioniPrivatiLayout = new javax.swing.GroupLayout(OperazioniPrivati);
        OperazioniPrivati.setLayout(OperazioniPrivatiLayout);
        OperazioniPrivatiLayout.setHorizontalGroup(
            OperazioniPrivatiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OperazioniPrivatiLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(OperazioniPrivatiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(VisualizzaPrivati)
                    .addComponent(InserisciPrivato)
                    .addGroup(OperazioniPrivatiLayout.createSequentialGroup()
                        .addGroup(OperazioniPrivatiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(OperazioniPrivatiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(InserisciNumero_di_telefono, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(InserisciCodice_fiscale, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(InserisciCognome, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(InserisciE_mail, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(InserisciNome, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, OperazioniPrivatiLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(OperazioniPrivatiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(VisualizzaCostoContrattoPrivato)
                    .addGroup(OperazioniPrivatiLayout.createSequentialGroup()
                        .addComponent(jTextField29, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Numero_di_contrattoPrivati, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(VisualizzaContrattiPrivato)
                    .addGroup(OperazioniPrivatiLayout.createSequentialGroup()
                        .addComponent(jTextField24, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Codice_fiscaleStoricoContratti, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        OperazioniPrivatiLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jTextField10, jTextField11, jTextField12, jTextField24, jTextField29, jTextField8, jTextField9});

        OperazioniPrivatiLayout.setVerticalGroup(
            OperazioniPrivatiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OperazioniPrivatiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(VisualizzaPrivati)
                .addGap(47, 47, 47)
                .addGroup(OperazioniPrivatiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(InserisciNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(OperazioniPrivatiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(InserisciCognome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(OperazioniPrivatiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(InserisciCodice_fiscale, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(OperazioniPrivatiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(InserisciNumero_di_telefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(OperazioniPrivatiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(InserisciE_mail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(InserisciPrivato)
                .addGap(44, 44, 44)
                .addGroup(OperazioniPrivatiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Codice_fiscaleStoricoContratti, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(VisualizzaContrattiPrivato)
                .addGap(46, 46, 46)
                .addGroup(OperazioniPrivatiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Numero_di_contrattoPrivati, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(VisualizzaCostoContrattoPrivato)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        TabellaPrivati1.setAutoCreateRowSorter(true);
        TabellaPrivati1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        TabellaPrivati.setViewportView(TabellaPrivati1);

        javax.swing.GroupLayout TabellaPrivatiPanelLayout = new javax.swing.GroupLayout(TabellaPrivatiPanel);
        TabellaPrivatiPanel.setLayout(TabellaPrivatiPanelLayout);
        TabellaPrivatiPanelLayout.setHorizontalGroup(
            TabellaPrivatiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TabellaPrivatiPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TabellaPrivati, javax.swing.GroupLayout.DEFAULT_SIZE, 1050, Short.MAX_VALUE)
                .addContainerGap())
        );
        TabellaPrivatiPanelLayout.setVerticalGroup(
            TabellaPrivatiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TabellaPrivati, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 742, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout PrivatiLayout = new javax.swing.GroupLayout(Privati);
        Privati.setLayout(PrivatiLayout);
        PrivatiLayout.setHorizontalGroup(
            PrivatiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PrivatiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(OperazioniPrivati, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TabellaPrivatiPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        PrivatiLayout.setVerticalGroup(
            PrivatiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PrivatiLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PrivatiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(TabellaPrivatiPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(OperazioniPrivati, javax.swing.GroupLayout.DEFAULT_SIZE, 742, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Privati", Privati);

        OperazioniAziende.setPreferredSize(new java.awt.Dimension(210, 210));

        VisualizzaAziende.setText("Visualizza aziende");
        VisualizzaAziende.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VisualizzaAziendeActionPerformed(evt);
            }
        });

        jTextField13.setEditable(false);
        jTextField13.setText("Partita iva");
        jTextField13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField13ActionPerformed(evt);
            }
        });

        jTextField14.setEditable(false);
        jTextField14.setText("Nome");

        InserisciNomeAzienda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InserisciNomeAziendaActionPerformed(evt);
            }
        });

        jTextField15.setEditable(false);
        jTextField15.setText("Sede");

        jTextField16.setEditable(false);
        jTextField16.setText("Fatturato");

        InserisciAzienda.setText("Inserisci azienda");
        InserisciAzienda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InserisciAziendaActionPerformed(evt);
            }
        });

        jTextField25.setEditable(false);
        jTextField25.setText("Partita iva");
        jTextField25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField25ActionPerformed(evt);
            }
        });

        Partita_ivaStoricoContratti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Partita_ivaStoricoContrattiActionPerformed(evt);
            }
        });

        VisualizzaContrattiAzienda.setText("Visualizza contratti");
        VisualizzaContrattiAzienda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VisualizzaContrattiAziendaActionPerformed(evt);
            }
        });

        VisualizzaCostoContrattoAzienda.setText("Visualizza costo contratto");
        VisualizzaCostoContrattoAzienda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VisualizzaCostoContrattoAziendaActionPerformed(evt);
            }
        });

        Numero_di_contrattoAziende.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Numero_di_contrattoAziendeActionPerformed(evt);
            }
        });

        jTextField34.setEditable(false);
        jTextField34.setText("Numero di contratto");
        jTextField34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField34ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout OperazioniAziendeLayout = new javax.swing.GroupLayout(OperazioniAziende);
        OperazioniAziende.setLayout(OperazioniAziendeLayout);
        OperazioniAziendeLayout.setHorizontalGroup(
            OperazioniAziendeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OperazioniAziendeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(OperazioniAziendeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(OperazioniAziendeLayout.createSequentialGroup()
                        .addComponent(jTextField34, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Numero_di_contrattoAziende, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(OperazioniAziendeLayout.createSequentialGroup()
                        .addComponent(jTextField25, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Partita_ivaStoricoContratti, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(OperazioniAziendeLayout.createSequentialGroup()
                        .addGroup(OperazioniAziendeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(OperazioniAziendeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(InserisciFatturato, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(InserisciSede, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(InserisciNomeAzienda, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(InserisciPartita_Iva, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(VisualizzaAziende)
                    .addComponent(InserisciAzienda)
                    .addComponent(VisualizzaContrattiAzienda)
                    .addComponent(VisualizzaCostoContrattoAzienda))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        OperazioniAziendeLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jTextField13, jTextField14, jTextField15, jTextField16});

        OperazioniAziendeLayout.setVerticalGroup(
            OperazioniAziendeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OperazioniAziendeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(VisualizzaAziende)
                .addGap(40, 40, 40)
                .addGroup(OperazioniAziendeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(InserisciPartita_Iva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(OperazioniAziendeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(InserisciNomeAzienda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(OperazioniAziendeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(InserisciSede, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(OperazioniAziendeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(InserisciFatturato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(InserisciAzienda)
                .addGap(45, 45, 45)
                .addGroup(OperazioniAziendeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Partita_ivaStoricoContratti, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(VisualizzaContrattiAzienda)
                .addGap(45, 45, 45)
                .addGroup(OperazioniAziendeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Numero_di_contrattoAziende, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(VisualizzaCostoContrattoAzienda)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        TabellaAziendePanel.setPreferredSize(new java.awt.Dimension(1062, 542));

        TabellaAziende1.setAutoCreateRowSorter(true);
        TabellaAziende1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        TabellaAziende.setViewportView(TabellaAziende1);

        javax.swing.GroupLayout TabellaAziendePanelLayout = new javax.swing.GroupLayout(TabellaAziendePanel);
        TabellaAziendePanel.setLayout(TabellaAziendePanelLayout);
        TabellaAziendePanelLayout.setHorizontalGroup(
            TabellaAziendePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TabellaAziendePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TabellaAziende, javax.swing.GroupLayout.DEFAULT_SIZE, 1048, Short.MAX_VALUE)
                .addContainerGap())
        );
        TabellaAziendePanelLayout.setVerticalGroup(
            TabellaAziendePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TabellaAziende, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 742, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout AziendeLayout = new javax.swing.GroupLayout(Aziende);
        Aziende.setLayout(AziendeLayout);
        AziendeLayout.setHorizontalGroup(
            AziendeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AziendeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(OperazioniAziende, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TabellaAziendePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 1060, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );
        AziendeLayout.setVerticalGroup(
            AziendeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AziendeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(AziendeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(TabellaAziendePanel, javax.swing.GroupLayout.DEFAULT_SIZE, 742, Short.MAX_VALUE)
                    .addComponent(OperazioniAziende, javax.swing.GroupLayout.DEFAULT_SIZE, 742, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Aziende", Aziende);

        OperazioniDIpendenti.setPreferredSize(new java.awt.Dimension(210, 210));

        VisualizzaDipendenti.setText("Visualizza dipendenti");
        VisualizzaDipendenti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VisualizzaDipendentiActionPerformed(evt);
            }
        });

        jTextField17.setEditable(false);
        jTextField17.setText("Nome");
        jTextField17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField17ActionPerformed(evt);
            }
        });

        InserisciNomeDipendente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InserisciNomeDipendenteActionPerformed(evt);
            }
        });

        jTextField18.setEditable(false);
        jTextField18.setText("Cognome");
        jTextField18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField18ActionPerformed(evt);
            }
        });

        InserisciCognomeDipendente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InserisciCognomeDipendenteActionPerformed(evt);
            }
        });

        jTextField19.setEditable(false);
        jTextField19.setText("Codice fiscale");

        jTextField20.setEditable(false);
        jTextField20.setText("Numero di telefono");
        jTextField20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField20ActionPerformed(evt);
            }
        });

        InserisciDipendente.setText("Inserisci dipendente");
        InserisciDipendente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InserisciDipendenteActionPerformed(evt);
            }
        });

        jTextField22.setEditable(false);
        jTextField22.setText("Stipendio");

        jTextField23.setEditable(false);
        jTextField23.setText("Data di assunzione");

        InserisciData_di_assunzione.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        InserisciData_di_assunzione.setText("YYYY-MM-DD");
        InserisciData_di_assunzione.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                InserisciData_di_assunzioneFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                InserisciData_di_assunzioneFocusLost(evt);
            }
        });
        InserisciData_di_assunzione.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InserisciData_di_assunzioneActionPerformed(evt);
            }
        });

        jTextField21.setEditable(false);
        jTextField21.setText("Codice fiscale");
        jTextField21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField21ActionPerformed(evt);
            }
        });

        Codice_fiscaleNumeroDiContratti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Codice_fiscaleNumeroDiContrattiActionPerformed(evt);
            }
        });

        VisualizzaNumeroContratti.setText("Visualizza numero contratti");
        VisualizzaNumeroContratti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VisualizzaNumeroContrattiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout OperazioniDIpendentiLayout = new javax.swing.GroupLayout(OperazioniDIpendenti);
        OperazioniDIpendenti.setLayout(OperazioniDIpendentiLayout);
        OperazioniDIpendentiLayout.setHorizontalGroup(
            OperazioniDIpendentiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OperazioniDIpendentiLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(OperazioniDIpendentiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(OperazioniDIpendentiLayout.createSequentialGroup()
                        .addGroup(OperazioniDIpendentiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTextField22)
                            .addComponent(jTextField23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(OperazioniDIpendentiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(InserisciStipendio, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                            .addComponent(InserisciData_di_assunzione, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, OperazioniDIpendentiLayout.createSequentialGroup()
                        .addGroup(OperazioniDIpendentiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTextField19, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField18, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField17, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField20))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(OperazioniDIpendentiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(InserisciCodice_fiscaleDipendente, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(InserisciCognomeDipendente, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(InserisciNumero_di_telefonoDipendente)
                            .addComponent(InserisciNomeDipendente)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, OperazioniDIpendentiLayout.createSequentialGroup()
                        .addComponent(jTextField21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Codice_fiscaleNumeroDiContratti, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(OperazioniDIpendentiLayout.createSequentialGroup()
                        .addGroup(OperazioniDIpendentiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(VisualizzaDipendenti)
                            .addComponent(InserisciDipendente)
                            .addComponent(VisualizzaNumeroContratti))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        OperazioniDIpendentiLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jTextField17, jTextField18, jTextField19, jTextField20, jTextField22, jTextField23});

        OperazioniDIpendentiLayout.setVerticalGroup(
            OperazioniDIpendentiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OperazioniDIpendentiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(VisualizzaDipendenti)
                .addGap(48, 48, 48)
                .addGroup(OperazioniDIpendentiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(OperazioniDIpendentiLayout.createSequentialGroup()
                        .addComponent(InserisciNomeDipendente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(InserisciCognomeDipendente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(InserisciCodice_fiscaleDipendente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(InserisciNumero_di_telefonoDipendente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(OperazioniDIpendentiLayout.createSequentialGroup()
                        .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(OperazioniDIpendentiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(OperazioniDIpendentiLayout.createSequentialGroup()
                        .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(OperazioniDIpendentiLayout.createSequentialGroup()
                        .addComponent(InserisciStipendio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(InserisciData_di_assunzione, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(InserisciDipendente)
                .addGap(48, 48, 48)
                .addGroup(OperazioniDIpendentiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Codice_fiscaleNumeroDiContratti, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(VisualizzaNumeroContratti)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        OperazioniDIpendentiLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jTextField17, jTextField18, jTextField19, jTextField20, jTextField22, jTextField23});

        TabellaDipendentiPanel.setPreferredSize(new java.awt.Dimension(1066, 542));

        TabellaDipendenti1.setAutoCreateRowSorter(true);
        TabellaDipendenti1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        TabellaDipendenti.setViewportView(TabellaDipendenti1);

        javax.swing.GroupLayout TabellaDipendentiPanelLayout = new javax.swing.GroupLayout(TabellaDipendentiPanel);
        TabellaDipendentiPanel.setLayout(TabellaDipendentiPanelLayout);
        TabellaDipendentiPanelLayout.setHorizontalGroup(
            TabellaDipendentiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TabellaDipendentiPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TabellaDipendenti, javax.swing.GroupLayout.DEFAULT_SIZE, 1048, Short.MAX_VALUE)
                .addContainerGap())
        );
        TabellaDipendentiPanelLayout.setVerticalGroup(
            TabellaDipendentiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TabellaDipendenti, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 742, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout DipendentiLayout = new javax.swing.GroupLayout(Dipendenti);
        Dipendenti.setLayout(DipendentiLayout);
        DipendentiLayout.setHorizontalGroup(
            DipendentiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DipendentiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(OperazioniDIpendenti, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TabellaDipendentiPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 1060, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );
        DipendentiLayout.setVerticalGroup(
            DipendentiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DipendentiLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(DipendentiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(TabellaDipendentiPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 742, Short.MAX_VALUE)
                    .addComponent(OperazioniDIpendenti, javax.swing.GroupLayout.DEFAULT_SIZE, 742, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Dipendenti", Dipendenti);

        VisualizzaAuto.setText("Visualizza tutte le auto");
        VisualizzaAuto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VisualizzaAutoActionPerformed(evt);
            }
        });

        InserisciAnno_di_immatricolazioe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InserisciAnno_di_immatricolazioeActionPerformed(evt);
            }
        });

        InserisciModello.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InserisciModelloActionPerformed(evt);
            }
        });

        InserisciAuto.setText("Inserisci Auto");
        InserisciAuto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InserisciAutoActionPerformed(evt);
            }
        });

        VisualizzaModelli.setText("Visualizza modelli");
        VisualizzaModelli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VisualizzaModelliActionPerformed(evt);
            }
        });

        jTextField1.setEditable(false);
        jTextField1.setText("Targa");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jTextField2.setEditable(false);
        jTextField2.setText("Anno di Immatricolazione");

        jTextField3.setEditable(false);
        jTextField3.setText("Numero di telaio");

        jTextField4.setEditable(false);
        jTextField4.setText("Marca");

        jTextField5.setEditable(false);
        jTextField5.setText("Modello");

        jTextField6.setEditable(false);
        jTextField6.setText("Targa");

        VisualizzaStoricoPrivati.setText("Visualizza storico privati");
        VisualizzaStoricoPrivati.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VisualizzaStoricoPrivatiActionPerformed(evt);
            }
        });

        VisualizzaStoricoAzienda.setText("Visualizza storico azienda");
        VisualizzaStoricoAzienda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VisualizzaStoricoAziendaActionPerformed(evt);
            }
        });

        jTextField7.setEditable(false);
        jTextField7.setText("Targa");

        VisualizzaStatoAuto.setText("Visualizza stato auto");
        VisualizzaStatoAuto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VisualizzaStatoAutoActionPerformed(evt);
            }
        });

        StatoAuto.setEditable(false);

        VisualizzaAutoDisponibili.setText("Visualizza auto disponibili");
        VisualizzaAutoDisponibili.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VisualizzaAutoDisponibiliActionPerformed(evt);
            }
        });

        AutoPiuRichiesta.setText("Visualizza il modello di auto piu' richiesta");
        AutoPiuRichiesta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AutoPiuRichiestaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout OperazioniAutoLayout = new javax.swing.GroupLayout(OperazioniAuto);
        OperazioniAuto.setLayout(OperazioniAutoLayout);
        OperazioniAutoLayout.setHorizontalGroup(
            OperazioniAutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OperazioniAutoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(OperazioniAutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(AutoPiuRichiesta, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
                    .addGroup(OperazioniAutoLayout.createSequentialGroup()
                        .addGroup(OperazioniAutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(OperazioniAutoLayout.createSequentialGroup()
                                .addGroup(OperazioniAutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(OperazioniAutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(InserisciMarca, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(InserisciNumero_di_telaio, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(InserisciAnno_di_immatricolazioe, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(InserisciModello, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(InserisciTarga, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(VisualizzaAuto)
                            .addComponent(InserisciAuto)
                            .addGroup(OperazioniAutoLayout.createSequentialGroup()
                                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(StoricoTarga, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(VisualizzaStoricoPrivati)
                            .addComponent(VisualizzaStoricoAzienda)
                            .addGroup(OperazioniAutoLayout.createSequentialGroup()
                                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(StatoTarga, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(OperazioniAutoLayout.createSequentialGroup()
                                .addComponent(VisualizzaStatoAuto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(StatoAuto, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(VisualizzaAutoDisponibili)
                            .addComponent(VisualizzaModelli))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        OperazioniAutoLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jTextField1, jTextField2, jTextField3, jTextField4, jTextField5});

        OperazioniAutoLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {InserisciAnno_di_immatricolazioe, InserisciMarca, InserisciModello, InserisciNumero_di_telaio, InserisciTarga});

        OperazioniAutoLayout.setVerticalGroup(
            OperazioniAutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OperazioniAutoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(VisualizzaAuto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(VisualizzaAutoDisponibili)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(VisualizzaModelli)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(AutoPiuRichiesta)
                .addGap(56, 56, 56)
                .addGroup(OperazioniAutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(InserisciTarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(OperazioniAutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(InserisciAnno_di_immatricolazioe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(OperazioniAutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(InserisciNumero_di_telaio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(OperazioniAutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(InserisciMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(OperazioniAutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(InserisciModello, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(InserisciAuto)
                .addGap(56, 56, 56)
                .addGroup(OperazioniAutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(StoricoTarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(VisualizzaStoricoPrivati)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(VisualizzaStoricoAzienda)
                .addGap(57, 57, 57)
                .addGroup(OperazioniAutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(StatoTarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(OperazioniAutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(VisualizzaStatoAuto)
                    .addComponent(StatoAuto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(145, Short.MAX_VALUE))
        );

        TabellaAuto1.setAutoCreateRowSorter(true);
        TabellaAuto1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        TabellaAuto.setViewportView(TabellaAuto1);

        javax.swing.GroupLayout TabellaAutoPanelLayout = new javax.swing.GroupLayout(TabellaAutoPanel);
        TabellaAutoPanel.setLayout(TabellaAutoPanelLayout);
        TabellaAutoPanelLayout.setHorizontalGroup(
            TabellaAutoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TabellaAutoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TabellaAuto, javax.swing.GroupLayout.DEFAULT_SIZE, 998, Short.MAX_VALUE)
                .addContainerGap())
        );
        TabellaAutoPanelLayout.setVerticalGroup(
            TabellaAutoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TabellaAuto, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        javax.swing.GroupLayout AutoLayout = new javax.swing.GroupLayout(Auto);
        Auto.setLayout(AutoLayout);
        AutoLayout.setHorizontalGroup(
            AutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AutoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(OperazioniAuto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TabellaAutoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        AutoLayout.setVerticalGroup(
            AutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AutoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(AutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(TabellaAutoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(OperazioniAuto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Auto", Auto);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1302, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 785, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void AutoPiuRichiestaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AutoPiuRichiestaActionPerformed
        ((javax.swing.table.DefaultTableModel) this.TabellaAuto1.getModel()).setRowCount(0);
        this.loadAutoPiuRichiesta();
        this.repaint();
    }//GEN-LAST:event_AutoPiuRichiestaActionPerformed

    private void VisualizzaAutoDisponibiliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VisualizzaAutoDisponibiliActionPerformed
        ((javax.swing.table.DefaultTableModel) this.TabellaAuto1.getModel()).setRowCount(0);
        this.loadAutoDisponibili();
        this.repaint();
    }//GEN-LAST:event_VisualizzaAutoDisponibiliActionPerformed

    private void VisualizzaStatoAutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VisualizzaStatoAutoActionPerformed
        if(this.StatoTarga.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Inserire tutti i campi", "Errore", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String targa = this.StatoTarga.getText();
        String stato = this.logic.getStatoAuto(targa);
        if(stato=="Errore"){
            JOptionPane.showMessageDialog(this, "Auto non trovata", "lettura", JOptionPane.ERROR_MESSAGE);
        } else {
            this.StatoAuto.setText(stato);
            this.repaint();
        }
        for (JTextField field : this.insertAutoFields.values()) {
            field.setText("");
        }
    }//GEN-LAST:event_VisualizzaStatoAutoActionPerformed

    private void VisualizzaStoricoAziendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VisualizzaStoricoAziendaActionPerformed
        if(this.StoricoTarga.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Inserire tutti i campi", "Errore", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String targa = this.StoricoTarga.getText();
        List<String> info = this.logic.getHistoryAzienda(targa);
        if(info.size()>0){
            ((javax.swing.table.DefaultTableModel) this.TabellaAuto1.getModel()).setRowCount(0);
            this.loadHistoryAzienda(info);
            this.repaint();
        } else {
            JOptionPane.showMessageDialog(this, "Nessuno Storico per la targa selezionata", "lettura", JOptionPane.ERROR_MESSAGE);
        }
        for (JTextField field : this.insertAutoFields.values()) {
            field.setText("");
        }
    }//GEN-LAST:event_VisualizzaStoricoAziendaActionPerformed

    private void VisualizzaStoricoPrivatiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VisualizzaStoricoPrivatiActionPerformed
        if(this.StoricoTarga.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Inserire tutti i campi", "Errore", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String targa = this.StoricoTarga.getText();
        List<String> info = this.logic.getHistoryPrivati(targa);
        if(info.size()>0){
            ((javax.swing.table.DefaultTableModel) this.TabellaAuto1.getModel()).setRowCount(0);
            this.loadHistoryPrivati(info);
            this.repaint();
        } else {
            JOptionPane.showMessageDialog(this, "Nessuno Storico per la targa selezionata", "lettura", JOptionPane.ERROR_MESSAGE);
        }
        for (JTextField field : this.insertAutoFields.values()) {
            field.setText("");
        }
    }//GEN-LAST:event_VisualizzaStoricoPrivatiActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void VisualizzaModelliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VisualizzaModelliActionPerformed
        ((javax.swing.table.DefaultTableModel) this.TabellaAuto1.getModel()).setRowCount(0);
        this.loadModello();
        this.repaint();
    }//GEN-LAST:event_VisualizzaModelliActionPerformed

    private void InserisciAutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InserisciAutoActionPerformed
        for (JTextField field : this.insertAutoFields.values()) {
            if(field.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Inserire tutti i campi", "Errore", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        String targa = this.insertAutoFields.get("targa").getText();
        int anno_di_immatricolazione = Integer.parseInt(this.insertAutoFields.get("anno_di_immatricolazione").getText());
        String numero_di_telaio = this.insertAutoFields.get("numero_di_telaio").getText();
        String marca = this.insertAutoFields.get("marca").getText();
        String modello = this.insertAutoFields.get("modello").getText();
        if(this.logic.insertAuto(targa, anno_di_immatricolazione, numero_di_telaio, marca, modello)){
            JOptionPane.showMessageDialog(this, "Auto inserita correttamente", "Inserimento", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Auto non inserita", "Inserimento", JOptionPane.ERROR_MESSAGE);
        }
        for (JTextField field : this.insertAutoFields.values()) {
            field.setText("");
        }
    }//GEN-LAST:event_InserisciAutoActionPerformed

    private void InserisciModelloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InserisciModelloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InserisciModelloActionPerformed

    private void InserisciAnno_di_immatricolazioeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InserisciAnno_di_immatricolazioeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InserisciAnno_di_immatricolazioeActionPerformed

    private void VisualizzaAutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VisualizzaAutoActionPerformed
        ((javax.swing.table.DefaultTableModel) this.TabellaAuto1.getModel()).setRowCount(0);
        this.loadAuto();
        this.repaint();
    }//GEN-LAST:event_VisualizzaAutoActionPerformed

    private void VisualizzaPrivatiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VisualizzaPrivatiActionPerformed
        ((javax.swing.table.DefaultTableModel) this.TabellaPrivati1.getModel()).setRowCount(0);
        this.loadPrivati();
        this.repaint();
    }//GEN-LAST:event_VisualizzaPrivatiActionPerformed

    private void jTextField8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField8ActionPerformed

    private void InserisciCognomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InserisciCognomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InserisciCognomeActionPerformed

    private void InserisciE_mailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InserisciE_mailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InserisciE_mailActionPerformed

    private void InserisciPrivatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InserisciPrivatoActionPerformed
        for (JTextField field : this.insertPrivatiFields.values()) {
            if(field.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Inserire tutti i campi", "Errore", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        String nome = this.insertPrivatiFields.get("nome").getText();
        String cognome = this.insertPrivatiFields.get("cognome").getText();
        String codice_fiscale = this.insertPrivatiFields.get("codice_fiscale").getText();
        long numero_di_telefono = Long.parseLong(this.insertPrivatiFields.get("numero_di_telefono").getText());
        String e_mail = this.insertPrivatiFields.get("e_mail").getText();
        if(this.logic.insertPrivati(nome, cognome, codice_fiscale, numero_di_telefono, e_mail)){
            JOptionPane.showMessageDialog(this, "Privato inserito correttamente", "Inserimento", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Privato non inserito", "Inserimento", JOptionPane.ERROR_MESSAGE);
        }
        for (JTextField field : this.insertPrivatiFields.values()) {
            field.setText("");
        }
    }//GEN-LAST:event_InserisciPrivatoActionPerformed

    private void VisualizzaAziendeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VisualizzaAziendeActionPerformed
        ((javax.swing.table.DefaultTableModel) this.TabellaAziende1.getModel()).setRowCount(0);
        this.loadAziende();
        this.repaint();
    }//GEN-LAST:event_VisualizzaAziendeActionPerformed

    private void jTextField13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField13ActionPerformed

    private void InserisciNomeAziendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InserisciNomeAziendaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InserisciNomeAziendaActionPerformed

    private void InserisciAziendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InserisciAziendaActionPerformed
        for (JTextField field : this.insertAziendeFields.values()) {
            if(field.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Inserire tutti i campi", "Errore", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        long partita_iva = Long.parseLong(this.insertAziendeFields.get("partita_iva").getText());
        String nome = this.insertAziendeFields.get("nome").getText();
        String sede = this.insertAziendeFields.get("sede").getText();
        int fatturato = Integer.parseInt((this.insertAziendeFields.get("fatturato").getText()));
        if(this.logic.insertAziende(partita_iva, nome, sede, fatturato)){
            JOptionPane.showMessageDialog(this, "Azienda inserita correttamente", "Inserimento", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Azienda non inserita", "Inserimento", JOptionPane.ERROR_MESSAGE);
        }
        for (JTextField field : this.insertAziendeFields.values()) {
            field.setText("");
        }
    }//GEN-LAST:event_InserisciAziendaActionPerformed

    private void VisualizzaDipendentiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VisualizzaDipendentiActionPerformed
        ((javax.swing.table.DefaultTableModel) this.TabellaDipendenti1.getModel()).setRowCount(0);
        this.loadDipendenti();
        this.repaint();
    }//GEN-LAST:event_VisualizzaDipendentiActionPerformed

    private void jTextField17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField17ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField17ActionPerformed

    private void InserisciCognomeDipendenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InserisciCognomeDipendenteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InserisciCognomeDipendenteActionPerformed

    private void InserisciDipendenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InserisciDipendenteActionPerformed
        for (JTextField field : this.insertDipendentiFields.values()) {
            if(field.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Inserire tutti i campi", "Errore", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        String nome = this.insertDipendentiFields.get("nome").getText();
        String cognome = this.insertDipendentiFields.get("cognome").getText();
        String codice_fiscale = this.insertDipendentiFields.get("codice_fiscale").getText();
        long numero_di_telefono = Long.parseLong(this.insertDipendentiFields.get("numero_di_telefono").getText());
        int stipendio = Integer.parseInt((this.insertDipendentiFields.get("stipendio").getText()));
        Date data_di_assunzione = Date.valueOf(this.insertDipendentiFields.get("data_di_assunzione").getText());
        if(this.logic.insertDipendenti(nome, cognome, codice_fiscale, numero_di_telefono, stipendio, data_di_assunzione)){
            JOptionPane.showMessageDialog(this, "Dipendente inserito correttamente", "Inserimento", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Dipendente non inserito", "Inserimento", JOptionPane.ERROR_MESSAGE);
        }
        for (JTextField field : this.insertDipendentiFields.values()) {
            field.setText("");
        }
    }//GEN-LAST:event_InserisciDipendenteActionPerformed

    private void jTextField20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField20ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField20ActionPerformed

    private void jTextField18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField18ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField18ActionPerformed

    private void InserisciNomeDipendenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InserisciNomeDipendenteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InserisciNomeDipendenteActionPerformed

    private void InserisciData_di_assunzioneFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_InserisciData_di_assunzioneFocusGained
        if(InserisciData_di_assunzione.getText().equals("YYYY-MM-DD")){
            InserisciData_di_assunzione.setText("");
        }
    }//GEN-LAST:event_InserisciData_di_assunzioneFocusGained

    private void InserisciData_di_assunzioneFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_InserisciData_di_assunzioneFocusLost
        if(InserisciData_di_assunzione.getText().equals("")){
            InserisciData_di_assunzione.setText("YYYY-MM-DD");
        }
    }//GEN-LAST:event_InserisciData_di_assunzioneFocusLost

    private void jTextField21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField21ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField21ActionPerformed

    private void Codice_fiscaleNumeroDiContrattiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Codice_fiscaleNumeroDiContrattiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Codice_fiscaleNumeroDiContrattiActionPerformed

    private void VisualizzaNumeroContrattiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VisualizzaNumeroContrattiActionPerformed
        if(this.Codice_fiscaleNumeroDiContratti.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Inserire tutti i campi", "Errore", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String codice_fiscale = this.Codice_fiscaleNumeroDiContratti.getText();
        Pair<Boolean, String> info = this.logic.getNumeroContrattiDipendente(codice_fiscale);
        if(info.getX()){
            this.Codice_fiscaleNumeroDiContratti.setText(info.getY());
        } else {
            JOptionPane.showMessageDialog(this, "Dipendente non trovato", "lettura", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_VisualizzaNumeroContrattiActionPerformed

    private void jTextField24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField24ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField24ActionPerformed

    private void Codice_fiscaleStoricoContrattiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Codice_fiscaleStoricoContrattiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Codice_fiscaleStoricoContrattiActionPerformed

    private void VisualizzaContrattiPrivatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VisualizzaContrattiPrivatoActionPerformed
        if(this.Codice_fiscaleStoricoContratti.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Inserire tutti i campi", "Errore", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String codice_fiscale = this.Codice_fiscaleStoricoContratti.getText();
        List<String> info = this.logic.getContrattiPrivato(codice_fiscale);
        if(info.size()>0){
            ((javax.swing.table.DefaultTableModel) this.TabellaPrivati1.getModel()).setRowCount(0);
            this.loadContrattiPrivato(info);
            this.repaint();
        } else {
            JOptionPane.showMessageDialog(this, "Nessun contratto per il privato selezionato", "lettura", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_VisualizzaContrattiPrivatoActionPerformed

    private void jTextField25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField25ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField25ActionPerformed

    private void Partita_ivaStoricoContrattiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Partita_ivaStoricoContrattiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Partita_ivaStoricoContrattiActionPerformed

    private void VisualizzaContrattiAziendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VisualizzaContrattiAziendaActionPerformed
        if(this.Partita_ivaStoricoContratti.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Inserire tutti i campi", "Errore", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String partita_iva = this.Partita_ivaStoricoContratti.getText();
        List<String> info = this.logic.getContrattiAzienda(partita_iva);
        if(info.size()>0){
            ((javax.swing.table.DefaultTableModel) this.TabellaAziende1.getModel()).setRowCount(0);
            this.loadContrattiAzienda(info);
            this.repaint();
        } else {
            JOptionPane.showMessageDialog(this, "Nessun contratto per l'azienda selezionata", "lettura", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_VisualizzaContrattiAziendaActionPerformed

    private void VisualizzaContrattiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VisualizzaContrattiActionPerformed
        ((javax.swing.table.DefaultTableModel) this.TabellaContratti1.getModel()).setRowCount(0);
            this.loadContratti();
            this.repaint();
    }//GEN-LAST:event_VisualizzaContrattiActionPerformed

    private void jTextField26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField26ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField26ActionPerformed

    private void InserisciCodice_fiscaleContrattoPrivatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InserisciCodice_fiscaleContrattoPrivatoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InserisciCodice_fiscaleContrattoPrivatoActionPerformed

    private void jTextField27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField27ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField27ActionPerformed

    private void InserisciCodice_fiscaleContrattoDipendenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InserisciCodice_fiscaleContrattoDipendenteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InserisciCodice_fiscaleContrattoDipendenteActionPerformed

    private void InserisciVenditaPrivatiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InserisciVenditaPrivatiActionPerformed
        for (JTextField field : this.insertVenditaPrivato.values()) {
            if(field.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Inserire tutti i campi", "Errore", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        String codice_fiscalePrivato = this.insertVenditaPrivato.get("codice_fiscalePrivato").getText();
        String codice_fiscaleDipendente = this.insertVenditaPrivato.get("codice_fiscaleDipendente").getText();
        String targa = this.insertVenditaPrivato.get("targa").getText();
        if(this.logic.insertVenditaPrivato(codice_fiscalePrivato, codice_fiscaleDipendente, targa)){
            JOptionPane.showMessageDialog(this, "Vendita privato inserita correttamente", "Inserimento", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "vendita privato non inserita", "Inserimento", JOptionPane.ERROR_MESSAGE);
        }
        for (JTextField field : this.insertVenditaPrivato.values()) {
            field.setText("");
        }
    }//GEN-LAST:event_InserisciVenditaPrivatiActionPerformed

    private void jTextField28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField28ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField28ActionPerformed

    private void jTextField31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField31ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField31ActionPerformed

    private void InserisciPartita_ivaContrattoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InserisciPartita_ivaContrattoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InserisciPartita_ivaContrattoActionPerformed

    private void InserisciCodice_fiscaleContrattoDipendenteAziendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InserisciCodice_fiscaleContrattoDipendenteAziendaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InserisciCodice_fiscaleContrattoDipendenteAziendaActionPerformed

    private void jTextField32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField32ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField32ActionPerformed

    private void jTextField33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField33ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField33ActionPerformed

    private void InserisciVenditaAziendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InserisciVenditaAziendaActionPerformed
        for (JTextField field : this.insertVenditaAzienda.values()) {
            if(field.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Inserire tutti i campi", "Errore", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        long partita_iva = Long.valueOf(this.insertVenditaAzienda.get("partita_iva").getText());
        String codice_fiscaleDipendente = this.insertVenditaAzienda.get("codice_fiscaleDipendente").getText();
        String targa = this.insertVenditaAzienda.get("targa").getText();
        if(this.logic.insertVenditaAzienda(partita_iva, codice_fiscaleDipendente, targa)){
            JOptionPane.showMessageDialog(this, "Vendita azienda inserita correttamente", "Inserimento", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "vendita azienda non inserita", "Inserimento", JOptionPane.ERROR_MESSAGE);
        }
        for (JTextField field : this.insertVenditaAzienda.values()) {
            field.setText("");
        }
    }//GEN-LAST:event_InserisciVenditaAziendaActionPerformed

    private void InserisciNoleggioPrivatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InserisciNoleggioPrivatoActionPerformed
        for (JTextField field : this.insertNoleggioPrivato.values()) {
            if(field.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Inserire tutti i campi", "Errore", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        if (LocalDate.now().isAfter(Date.valueOf(this.insertNoleggioPrivato.get("data_di_fine_noleggio").getText()).toLocalDate())) {
            JOptionPane.showMessageDialog(this, "La data di fine noleggio non può essere antecedente alla data odierna", "Errore", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String codice_fiscalePrivato = this.insertNoleggioPrivato.get("codice_fiscalePrivato").getText();
        String codice_fiscaleDipendente = this.insertNoleggioPrivato.get("codice_fiscaleDipendente").getText();
        String targa = this.insertNoleggioPrivato.get("targa").getText();
        Date data_di_fine_noleggio = Date.valueOf(this.insertNoleggioPrivato.get("data_di_fine_noleggio").getText());
        if(this.logic.insertNoleggioPrivato(codice_fiscalePrivato, codice_fiscaleDipendente, targa, data_di_fine_noleggio)){
            JOptionPane.showMessageDialog(this, "Noleggio privato inserito correttamente", "Inserimento", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Noleggio privato non inserito", "Inserimento", JOptionPane.ERROR_MESSAGE);
        }
        for (JTextField field : this.insertNoleggioPrivato.values()) {
            field.setText("");
        }
    }//GEN-LAST:event_InserisciNoleggioPrivatoActionPerformed

    private void InserisciData_di_fine_noleggioPrivatoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_InserisciData_di_fine_noleggioPrivatoFocusGained
        if(InserisciData_di_fine_noleggioPrivato.getText().equals("YYYY-MM-DD")){
            InserisciData_di_fine_noleggioPrivato.setText("");
        }
    }//GEN-LAST:event_InserisciData_di_fine_noleggioPrivatoFocusGained

    private void InserisciData_di_fine_noleggioPrivatoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_InserisciData_di_fine_noleggioPrivatoFocusLost
        if(InserisciData_di_fine_noleggioPrivato.getText().equals("")){
            InserisciData_di_fine_noleggioPrivato.setText("YYYY-MM-DD");
        }
    }//GEN-LAST:event_InserisciData_di_fine_noleggioPrivatoFocusLost

    private void InserisciData_di_fine_noleggioAziendaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_InserisciData_di_fine_noleggioAziendaFocusGained
        if(InserisciData_di_fine_noleggioAzienda.getText().equals("YYYY-MM-DD")){
            InserisciData_di_fine_noleggioAzienda.setText("");
        }
    }//GEN-LAST:event_InserisciData_di_fine_noleggioAziendaFocusGained

    private void InserisciData_di_fine_noleggioAziendaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_InserisciData_di_fine_noleggioAziendaFocusLost
        if(InserisciData_di_fine_noleggioAzienda.getText().equals("")){
            InserisciData_di_fine_noleggioAzienda.setText("YYYY-MM-DD");
        }
    }//GEN-LAST:event_InserisciData_di_fine_noleggioAziendaFocusLost

    private void InserisciData_di_assunzioneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InserisciData_di_assunzioneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InserisciData_di_assunzioneActionPerformed

    private void InserisciNoleggioAziendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InserisciNoleggioAziendaActionPerformed
        for (JTextField field : this.insertNoleggioAzienda.values()) {
            if(field.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Inserire tutti i campi", "Errore", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        if (LocalDate.now().isAfter(Date.valueOf(this.insertNoleggioAzienda.get("data_di_fine_noleggio").getText()).toLocalDate())) {
            JOptionPane.showMessageDialog(this, "La data di fine noleggio non può essere antecedente alla data odierna", "Errore", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Long partita_iva = Long.valueOf(this.insertNoleggioAzienda.get("partita_iva").getText());
        String codice_fiscaleDipendente = this.insertNoleggioAzienda.get("codice_fiscaleDipendente").getText();
        String targa = this.insertNoleggioAzienda.get("targa").getText();
        Date data_di_fine_noleggio = Date.valueOf(this.insertNoleggioAzienda.get("data_di_fine_noleggio").getText());
        if(this.logic.insertNoleggioAzienda(partita_iva, codice_fiscaleDipendente, targa, data_di_fine_noleggio)){
            JOptionPane.showMessageDialog(this, "Noleggio privato inserito correttamente", "Inserimento", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Noleggio privato non inserito", "Inserimento", JOptionPane.ERROR_MESSAGE);
        }
        for (JTextField field : this.insertNoleggioAzienda.values()) {
            field.setText("");
        }
    }//GEN-LAST:event_InserisciNoleggioAziendaActionPerformed

    private void jTextField29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField29ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField29ActionPerformed

    private void Numero_di_contrattoPrivatiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Numero_di_contrattoPrivatiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Numero_di_contrattoPrivatiActionPerformed

    private void VisualizzaCostoContrattoPrivatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VisualizzaCostoContrattoPrivatoActionPerformed
        if (this.Numero_di_contrattoPrivati.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Inserire tutti i campi", "Errore", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int numero_di_contratto = Integer.valueOf(this.Numero_di_contrattoPrivati.getText());
        List<String> info = this.logic.getCostoContrattoPrivato(numero_di_contratto);
        
        if(info.size()>0){
            ((javax.swing.table.DefaultTableModel) this.TabellaPrivati1.getModel()).setRowCount(0);
            this.loadCostoContrattoPrivato(info);
            this.repaint();
        } else {
            JOptionPane.showMessageDialog(this, "Contratto n." + numero_di_contratto + " non trovato per i privati", "lettura", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_VisualizzaCostoContrattoPrivatoActionPerformed

    private void VisualizzaCostoContrattoAziendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VisualizzaCostoContrattoAziendaActionPerformed
        if (this.Numero_di_contrattoAziende.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Inserire tutti i campi", "Errore", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int numero_di_contratto = Integer.valueOf(this.Numero_di_contrattoAziende.getText());
        List<String> info = this.logic.getCostoContrattoAzienda(numero_di_contratto);
        
        if(info.size()>0){
            ((javax.swing.table.DefaultTableModel) this.TabellaAziende1.getModel()).setRowCount(0);
            this.loadCostoContrattoAzienda(info);
            this.repaint();
        } else {
            JOptionPane.showMessageDialog(this, "Contratto n." + numero_di_contratto + " non trovato per le aziende", "lettura", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_VisualizzaCostoContrattoAziendaActionPerformed

    private void Numero_di_contrattoAziendeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Numero_di_contrattoAziendeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Numero_di_contrattoAziendeActionPerformed

    private void jTextField34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField34ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField34ActionPerformed

    public void initLook() {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Auto;
    private javax.swing.JButton AutoPiuRichiesta;
    private javax.swing.JPanel Aziende;
    private javax.swing.JTextField Codice_fiscaleNumeroDiContratti;
    private javax.swing.JTextField Codice_fiscaleStoricoContratti;
    private javax.swing.JPanel Contratti;
    private javax.swing.JPanel Dipendenti;
    private javax.swing.JTextField InserisciAnno_di_immatricolazioe;
    private javax.swing.JButton InserisciAuto;
    private javax.swing.JButton InserisciAzienda;
    private javax.swing.JTextField InserisciCodice_fiscale;
    private javax.swing.JTextField InserisciCodice_fiscaleContrattoDipendente;
    private javax.swing.JTextField InserisciCodice_fiscaleContrattoDipendenteAzienda;
    private javax.swing.JTextField InserisciCodice_fiscaleContrattoPrivato;
    private javax.swing.JTextField InserisciCodice_fiscaleDipendente;
    private javax.swing.JTextField InserisciCognome;
    private javax.swing.JTextField InserisciCognomeDipendente;
    private javax.swing.JTextField InserisciData_di_assunzione;
    private javax.swing.JTextField InserisciData_di_fine_noleggioAzienda;
    private javax.swing.JTextField InserisciData_di_fine_noleggioPrivato;
    private javax.swing.JButton InserisciDipendente;
    private javax.swing.JTextField InserisciE_mail;
    private javax.swing.JTextField InserisciFatturato;
    private javax.swing.JTextField InserisciMarca;
    private javax.swing.JTextField InserisciModello;
    private javax.swing.JButton InserisciNoleggioAzienda;
    private javax.swing.JButton InserisciNoleggioPrivato;
    private javax.swing.JTextField InserisciNome;
    private javax.swing.JTextField InserisciNomeAzienda;
    private javax.swing.JTextField InserisciNomeDipendente;
    private javax.swing.JTextField InserisciNumero_di_telaio;
    private javax.swing.JTextField InserisciNumero_di_telefono;
    private javax.swing.JTextField InserisciNumero_di_telefonoDipendente;
    private javax.swing.JTextField InserisciPartita_Iva;
    private javax.swing.JTextField InserisciPartita_ivaContratto;
    private javax.swing.JButton InserisciPrivato;
    private javax.swing.JTextField InserisciSede;
    private javax.swing.JTextField InserisciStipendio;
    private javax.swing.JTextField InserisciTarga;
    private javax.swing.JTextField InserisciTargaContrattoAzienda;
    private javax.swing.JTextField InserisciTargaContrattoPrivato;
    private javax.swing.JButton InserisciVenditaAzienda;
    private javax.swing.JButton InserisciVenditaPrivati;
    private javax.swing.JTextField Numero_di_contrattoAziende;
    private javax.swing.JTextField Numero_di_contrattoPrivati;
    private javax.swing.JPanel OperazioniAuto;
    private javax.swing.JPanel OperazioniAziende;
    private javax.swing.JPanel OperazioniContratti;
    private javax.swing.JPanel OperazioniDIpendenti;
    private javax.swing.JPanel OperazioniPrivati;
    private javax.swing.JTextField Partita_ivaStoricoContratti;
    private javax.swing.JPanel Privati;
    private javax.swing.JTextField StatoAuto;
    private javax.swing.JTextField StatoTarga;
    private javax.swing.JTextField StoricoTarga;
    private javax.swing.JScrollPane TabellaAuto;
    private javax.swing.JTable TabellaAuto1;
    private javax.swing.JPanel TabellaAutoPanel;
    private javax.swing.JScrollPane TabellaAziende;
    private javax.swing.JTable TabellaAziende1;
    private javax.swing.JPanel TabellaAziendePanel;
    private javax.swing.JScrollPane TabellaContratti;
    private javax.swing.JTable TabellaContratti1;
    private javax.swing.JPanel TabellaContrattiPanel;
    private javax.swing.JScrollPane TabellaDipendenti;
    private javax.swing.JTable TabellaDipendenti1;
    private javax.swing.JPanel TabellaDipendentiPanel;
    private javax.swing.JScrollPane TabellaPrivati;
    private javax.swing.JTable TabellaPrivati1;
    private javax.swing.JPanel TabellaPrivatiPanel;
    private javax.swing.JButton VisualizzaAuto;
    private javax.swing.JButton VisualizzaAutoDisponibili;
    private javax.swing.JButton VisualizzaAziende;
    private javax.swing.JButton VisualizzaContratti;
    private javax.swing.JButton VisualizzaContrattiAzienda;
    private javax.swing.JButton VisualizzaContrattiPrivato;
    private javax.swing.JButton VisualizzaCostoContrattoAzienda;
    private javax.swing.JButton VisualizzaCostoContrattoPrivato;
    private javax.swing.JButton VisualizzaDipendenti;
    private javax.swing.JButton VisualizzaModelli;
    private javax.swing.JButton VisualizzaNumeroContratti;
    private javax.swing.JButton VisualizzaPrivati;
    private javax.swing.JButton VisualizzaStatoAuto;
    private javax.swing.JButton VisualizzaStoricoAzienda;
    private javax.swing.JButton VisualizzaStoricoPrivati;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField21;
    private javax.swing.JTextField jTextField22;
    private javax.swing.JTextField jTextField23;
    private javax.swing.JTextField jTextField24;
    private javax.swing.JTextField jTextField25;
    private javax.swing.JTextField jTextField26;
    private javax.swing.JTextField jTextField27;
    private javax.swing.JTextField jTextField28;
    private javax.swing.JTextField jTextField29;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField30;
    private javax.swing.JTextField jTextField31;
    private javax.swing.JTextField jTextField32;
    private javax.swing.JTextField jTextField33;
    private javax.swing.JTextField jTextField34;
    private javax.swing.JTextField jTextField35;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables
}
