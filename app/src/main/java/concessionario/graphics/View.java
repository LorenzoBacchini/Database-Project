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
import concessionario.db.tables.Modello;

/**
 *
 * @author bacco
 */
public class View extends javax.swing.JFrame {
    private final Logic logic;
    private Map<String, JTextField> insertAutoFields = new HashMap<>(); 
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
        Aziende = new javax.swing.JPanel();
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
            .addGap(0, 1209, Short.MAX_VALUE)
        );
        ContrattiLayout.setVerticalGroup(
            ContrattiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 554, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Contratti", Contratti);

        javax.swing.GroupLayout PrivatiLayout = new javax.swing.GroupLayout(Privati);
        Privati.setLayout(PrivatiLayout);
        PrivatiLayout.setHorizontalGroup(
            PrivatiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1209, Short.MAX_VALUE)
        );
        PrivatiLayout.setVerticalGroup(
            PrivatiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 554, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Privati", Privati);

        javax.swing.GroupLayout AziendeLayout = new javax.swing.GroupLayout(Aziende);
        Aziende.setLayout(AziendeLayout);
        AziendeLayout.setHorizontalGroup(
            AziendeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1209, Short.MAX_VALUE)
        );
        AziendeLayout.setVerticalGroup(
            AziendeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 554, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Aziende", Aziende);

        javax.swing.GroupLayout DipendentiLayout = new javax.swing.GroupLayout(Dipendenti);
        Dipendenti.setLayout(DipendentiLayout);
        DipendentiLayout.setHorizontalGroup(
            DipendentiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1209, Short.MAX_VALUE)
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        TabellaAuto1.setAutoCreateRowSorter(true);
        TabellaAuto1.setEnabled(false);
        TabellaAuto.setViewportView(TabellaAuto1);

        javax.swing.GroupLayout TabellaAutoPanelLayout = new javax.swing.GroupLayout(TabellaAutoPanel);
        TabellaAutoPanel.setLayout(TabellaAutoPanelLayout);
        TabellaAutoPanelLayout.setHorizontalGroup(
            TabellaAutoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TabellaAutoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TabellaAuto, javax.swing.GroupLayout.DEFAULT_SIZE, 905, Short.MAX_VALUE)
                .addContainerGap())
        );
        TabellaAutoPanelLayout.setVerticalGroup(
            TabellaAutoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TabellaAuto, javax.swing.GroupLayout.DEFAULT_SIZE, 542, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout AutoLayout = new javax.swing.GroupLayout(Auto);
        Auto.setLayout(AutoLayout);
        AutoLayout.setHorizontalGroup(
            AutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AutoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(OperazioniAuto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TabellaAutoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void VisualizzaAutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VisualizzaAutoActionPerformed
        ((javax.swing.table.DefaultTableModel) this.TabellaAuto1.getModel()).setRowCount(0);
        this.loadAuto();
        this.repaint();
    }//GEN-LAST:event_VisualizzaAutoActionPerformed

    private void InserisciAnno_di_immatricolazioeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InserisciAnno_di_immatricolazioeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InserisciAnno_di_immatricolazioeActionPerformed

    private void InserisciAutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InserisciAutoActionPerformed
        for (JTextField field : this.insertAutoFields.values()) {
            if(field.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Inserire tutti i campi", "Errore", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        try {
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
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Inserire un numero valido", "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_InserisciAutoActionPerformed

    private void VisualizzaModelliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VisualizzaModelliActionPerformed
        ((javax.swing.table.DefaultTableModel) this.TabellaAuto1.getModel()).setRowCount(0);
        this.loadModello();
        this.repaint();
    }//GEN-LAST:event_VisualizzaModelliActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void InserisciModelloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InserisciModelloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InserisciModelloActionPerformed

    private void VisualizzaStoricoPrivatiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VisualizzaStoricoPrivatiActionPerformed
        if(this.StoricoTarga.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Inserire tutti i campi", "Errore", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
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
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Inserire un numero valido", "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_VisualizzaStoricoPrivatiActionPerformed

    private void VisualizzaStoricoAziendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VisualizzaStoricoAziendaActionPerformed
        if(this.StoricoTarga.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Inserire tutti i campi", "Errore", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
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
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Inserire un numero valido", "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_VisualizzaStoricoAziendaActionPerformed

    private void VisualizzaStatoAutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VisualizzaStatoAutoActionPerformed
        if(this.StatoTarga.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Inserire tutti i campi", "Errore", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
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
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Inserire un numero valido", "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_VisualizzaStatoAutoActionPerformed

    private void VisualizzaAutoDisponibiliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VisualizzaAutoDisponibiliActionPerformed
        ((javax.swing.table.DefaultTableModel) this.TabellaAuto1.getModel()).setRowCount(0);
        this.loadAutoDisponibili();
        this.repaint();
    }//GEN-LAST:event_VisualizzaAutoDisponibiliActionPerformed

    private void AutoPiuRichiestaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AutoPiuRichiestaActionPerformed
        ((javax.swing.table.DefaultTableModel) this.TabellaAuto1.getModel()).setRowCount(0);
        this.loadAutoPiuRichiesta();
        this.repaint();
    }//GEN-LAST:event_AutoPiuRichiestaActionPerformed

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
    private javax.swing.JTextField InserisciMarca;
    private javax.swing.JTextField InserisciModello;
    private javax.swing.JTextField InserisciNumero_di_telaio;
    private javax.swing.JTextField InserisciTarga;
    private javax.swing.JPanel OperazioniAuto;
    private javax.swing.JPanel Privati;
    private javax.swing.JTextField StatoAuto;
    private javax.swing.JTextField StatoTarga;
    private javax.swing.JTextField StoricoTarga;
    private javax.swing.JScrollPane TabellaAuto;
    private javax.swing.JTable TabellaAuto1;
    private javax.swing.JPanel TabellaAutoPanel;
    private javax.swing.JButton VisualizzaAuto;
    private javax.swing.JButton VisualizzaAutoDisponibili;
    private javax.swing.JButton VisualizzaModelli;
    private javax.swing.JButton VisualizzaStatoAuto;
    private javax.swing.JButton VisualizzaStoricoAzienda;
    private javax.swing.JButton VisualizzaStoricoPrivati;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    // End of variables declaration//GEN-END:variables
}
