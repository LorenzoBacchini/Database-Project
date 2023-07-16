/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package concessionario.graphics;

import concessionario.model.Logic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import concessionario.db.tables.Auto;
import concessionario.db.tables.Azienda;
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
                    a.getModello(), String.valueOf(a.isVenduta()), a.getData_di_fine_noleggio().isPresent() ? String.valueOf(a.getData_di_fine_noleggio()) : null};
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
        TabellaAziendePanel = new javax.swing.JPanel();
        TabellaAziende = new javax.swing.JScrollPane();
        TabellaAziende1 = new javax.swing.JTable();
        Dipendenti = new javax.swing.JPanel();
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

        javax.swing.GroupLayout ContrattiLayout = new javax.swing.GroupLayout(Contratti);
        Contratti.setLayout(ContrattiLayout);
        ContrattiLayout.setHorizontalGroup(
            ContrattiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1300, Short.MAX_VALUE)
        );
        ContrattiLayout.setVerticalGroup(
            ContrattiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 554, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Contratti", Contratti);

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
        );

        OperazioniPrivatiLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jTextField10, jTextField11, jTextField12, jTextField8, jTextField9});

        OperazioniPrivatiLayout.setVerticalGroup(
            OperazioniPrivatiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OperazioniPrivatiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(VisualizzaPrivati)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        TabellaPrivati1.setAutoCreateRowSorter(true);
        TabellaPrivati1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        TabellaPrivati1.setEnabled(false);
        TabellaPrivati.setViewportView(TabellaPrivati1);

        javax.swing.GroupLayout TabellaPrivatiPanelLayout = new javax.swing.GroupLayout(TabellaPrivatiPanel);
        TabellaPrivatiPanel.setLayout(TabellaPrivatiPanelLayout);
        TabellaPrivatiPanelLayout.setHorizontalGroup(
            TabellaPrivatiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TabellaPrivatiPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TabellaPrivati, javax.swing.GroupLayout.DEFAULT_SIZE, 1054, Short.MAX_VALUE)
                .addContainerGap())
        );
        TabellaPrivatiPanelLayout.setVerticalGroup(
            TabellaPrivatiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TabellaPrivati, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 542, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout PrivatiLayout = new javax.swing.GroupLayout(Privati);
        Privati.setLayout(PrivatiLayout);
        PrivatiLayout.setHorizontalGroup(
            PrivatiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PrivatiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(OperazioniPrivati, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TabellaPrivatiPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        PrivatiLayout.setVerticalGroup(
            PrivatiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PrivatiLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PrivatiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(TabellaPrivatiPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(OperazioniPrivati, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Privati", Privati);

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

        javax.swing.GroupLayout OperazioniAziendeLayout = new javax.swing.GroupLayout(OperazioniAziende);
        OperazioniAziende.setLayout(OperazioniAziendeLayout);
        OperazioniAziendeLayout.setHorizontalGroup(
            OperazioniAziendeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OperazioniAziendeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(OperazioniAziendeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(OperazioniAziendeLayout.createSequentialGroup()
                        .addGroup(OperazioniAziendeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                        .addGroup(OperazioniAziendeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(InserisciFatturato, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(InserisciSede, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(InserisciNomeAzienda, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(InserisciPartita_Iva, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(OperazioniAziendeLayout.createSequentialGroup()
                        .addGroup(OperazioniAziendeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(VisualizzaAziende)
                            .addComponent(InserisciAzienda))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        OperazioniAziendeLayout.setVerticalGroup(
            OperazioniAziendeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OperazioniAziendeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(VisualizzaAziende)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
                .addContainerGap(366, Short.MAX_VALUE))
        );

        TabellaAziende1.setAutoCreateRowSorter(true);
        TabellaAziende1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        TabellaAziende1.setEnabled(false);
        TabellaAziende.setViewportView(TabellaAziende1);

        javax.swing.GroupLayout TabellaAziendePanelLayout = new javax.swing.GroupLayout(TabellaAziendePanel);
        TabellaAziendePanel.setLayout(TabellaAziendePanelLayout);
        TabellaAziendePanelLayout.setHorizontalGroup(
            TabellaAziendePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TabellaAziendePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TabellaAziende, javax.swing.GroupLayout.DEFAULT_SIZE, 1078, Short.MAX_VALUE)
                .addContainerGap())
        );
        TabellaAziendePanelLayout.setVerticalGroup(
            TabellaAziendePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TabellaAziende, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 542, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout AziendeLayout = new javax.swing.GroupLayout(Aziende);
        Aziende.setLayout(AziendeLayout);
        AziendeLayout.setHorizontalGroup(
            AziendeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AziendeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(OperazioniAziende, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TabellaAziendePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        AziendeLayout.setVerticalGroup(
            AziendeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AziendeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(AziendeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(TabellaAziendePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(OperazioniAziende, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Aziende", Aziende);

        javax.swing.GroupLayout DipendentiLayout = new javax.swing.GroupLayout(Dipendenti);
        Dipendenti.setLayout(DipendentiLayout);
        DipendentiLayout.setHorizontalGroup(
            DipendentiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1300, Short.MAX_VALUE)
        );
        DipendentiLayout.setVerticalGroup(
            DipendentiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 554, Short.MAX_VALUE)
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

        VisualizzaModelli.setText("Visualizza modelli disponibili");
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
                            .addComponent(VisualizzaModelli)
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
                            .addComponent(VisualizzaAutoDisponibili))
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
                .addComponent(VisualizzaModelli)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(OperazioniAutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(StoricoTarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(VisualizzaStoricoPrivati)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(VisualizzaStoricoAzienda)
                .addGap(18, 18, 18)
                .addGroup(OperazioniAutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(StatoTarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(OperazioniAutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(VisualizzaStatoAuto)
                    .addComponent(StatoAuto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(VisualizzaAutoDisponibili)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(AutoPiuRichiesta)
                .addContainerGap(72, Short.MAX_VALUE))
        );

        TabellaAuto1.setAutoCreateRowSorter(true);
        TabellaAuto1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        TabellaAuto1.setEnabled(false);
        TabellaAuto.setViewportView(TabellaAuto1);

        javax.swing.GroupLayout TabellaAutoPanelLayout = new javax.swing.GroupLayout(TabellaAutoPanel);
        TabellaAutoPanel.setLayout(TabellaAutoPanelLayout);
        TabellaAutoPanelLayout.setHorizontalGroup(
            TabellaAutoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TabellaAutoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TabellaAuto, javax.swing.GroupLayout.DEFAULT_SIZE, 996, Short.MAX_VALUE)
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
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
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
    private javax.swing.JPanel Contratti;
    private javax.swing.JPanel Dipendenti;
    private javax.swing.JTextField InserisciAnno_di_immatricolazioe;
    private javax.swing.JButton InserisciAuto;
    private javax.swing.JButton InserisciAzienda;
    private javax.swing.JTextField InserisciCodice_fiscale;
    private javax.swing.JTextField InserisciCognome;
    private javax.swing.JTextField InserisciE_mail;
    private javax.swing.JTextField InserisciFatturato;
    private javax.swing.JTextField InserisciMarca;
    private javax.swing.JTextField InserisciModello;
    private javax.swing.JTextField InserisciNome;
    private javax.swing.JTextField InserisciNomeAzienda;
    private javax.swing.JTextField InserisciNumero_di_telaio;
    private javax.swing.JTextField InserisciNumero_di_telefono;
    private javax.swing.JTextField InserisciPartita_Iva;
    private javax.swing.JButton InserisciPrivato;
    private javax.swing.JTextField InserisciSede;
    private javax.swing.JTextField InserisciTarga;
    private javax.swing.JPanel OperazioniAuto;
    private javax.swing.JPanel OperazioniAziende;
    private javax.swing.JPanel OperazioniPrivati;
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
    private javax.swing.JScrollPane TabellaPrivati;
    private javax.swing.JTable TabellaPrivati1;
    private javax.swing.JPanel TabellaPrivatiPanel;
    private javax.swing.JButton VisualizzaAuto;
    private javax.swing.JButton VisualizzaAutoDisponibili;
    private javax.swing.JButton VisualizzaAziende;
    private javax.swing.JButton VisualizzaModelli;
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
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables
}
