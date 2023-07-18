package concessionario.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import concessionario.db.tables.Auto;
import concessionario.db.tables.Azienda;
import concessionario.db.tables.Dipendente;
import concessionario.db.tables.Modello;
import concessionario.db.tables.Privato;
import concessionario.utils.Pair;

public class Logic {
    final private Connection connection;

    public Logic(final Connection connection) {
        this.connection = Objects.requireNonNull(connection);
    }

    public List<String> getAutoPiuRichiesta(){
        final String query = "SELECT a.Marca, a.Modello, COUNT(*) as n_contratti"+
                " FROM contratto c, auto a"+
                " WHERE c.Auto = a.Targa"+
                " GROUP BY a.Marca, a.Modello"+
                " HAVING COUNT(*) >= ALL (SELECT COUNT(*)"+
                " FROM contratto c1, auto a1"+
                " WHERE c1.Auto = a1.Targa"+
                " GROUP BY a1.Marca, a1.Modello);";

        try (final Statement statement = this.connection.createStatement()) {
            final ResultSet result = statement.executeQuery(query);
            final List<String> info = new ArrayList<>();
            while (result.next()) {
                info.add(result.getString("Marca"));
                info.add(result.getString("Modello"));
                info.add(result.getString("n_contratti"));
            }
            return info;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public List<Auto> getAutoDisponibili(){
        final String query = "SELECT Targa, Anno_di_immatricolazione, Numero_di_telaio, Marca, Modello"+
                " FROM auto"+
                " WHERE Venduta=0"+
                " AND (Data_di_fine_noleggio is null"+
                " OR Data_di_fine_noleggio<curdate());";

        try (final Statement statement = this.connection.createStatement()) {
            final ResultSet result = statement.executeQuery(query);
            final List<Auto> auto = new ArrayList<>();
            while (result.next()) {
                final String targa = result.getString("Targa");
                final int anno_di_immatricolazione = result.getInt("Anno_di_immatricolazione");
                final String numero_di_telaio = result.getString("Numero_di_telaio");
                final String marca = result.getString("Marca");
                final String modello = result.getString("Modello");
                
                final Auto a = new Auto(targa, anno_di_immatricolazione, numero_di_telaio, marca, modello, false, Optional.empty());
                auto.add(a);
            }
            return auto;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public String getStatoAuto(final String targa){
        final String query = "SELECT * FROM auto WHERE Targa = ?";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, targa);
            ResultSet result = statement.executeQuery();
            result.next();
            final Auto auto = new Auto(result.getString("Targa"), result.getInt("Anno_di_immatricolazione"), 
                result.getString("Numero_di_telaio"), result.getString("Marca"), result.getString("Modello"), 
                result.getBoolean("Venduta"), Optional.ofNullable(result.getDate("Data_di_fine_noleggio")));
            if(auto.isVenduta()){
                return "Venduta";
            } else if(auto.getData_di_fine_noleggio().isPresent()){
                Date data = auto.getData_di_fine_noleggio().get();
                LocalDate todayDate = LocalDate.now();
                if(todayDate.isBefore(data.toLocalDate()) || todayDate.isEqual(data.toLocalDate())){
                    return "Noleggiata";
                }
            }
            return "Disponibile";
        } catch (final SQLException e) {
            return "Errore";
        }
    }

    public List<String> getHistoryPrivati(final String targa){
        final String query = "SELECT c.Numero_di_contratto, a.Targa, a.Marca, a.Modello, p.Nome, p.Cognome, n.Data_di_inizio_noleggio, n.Data_di_fine_noleggio, null as Data_di_vendita"+
            " FROM auto a, contratto c, privato p, noleggio n"+
            " WHERE a.Targa = c.Auto"+
            " AND c.Privato = p.Codice_fiscale"+
            " AND c.Numero_di_contratto = n.Numero_di_contratto"+
            " AND a.Targa = ?"+
            " UNION ALL"+
            " SELECT c.Numero_di_contratto, a.Targa, a.Marca, a.Modello, p.Nome, p.Cognome, null, null, v.Data_di_vendita"+
            " FROM auto a, contratto c, privato p, vendita v"+
            " WHERE a.Targa = c.Auto"+
            " AND c.Privato = p.Codice_fiscale"+
            " AND c.Numero_di_contratto = v.Numero_di_contratto"+
            " AND a.Targa = ?;";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, targa);
            statement.setString(2, targa);
            final ResultSet result = statement.executeQuery();
            final List<String> info = new ArrayList<>();
            while (result.next()) {
                info.add(result.getString("Numero_di_contratto"));
                info.add(targa);
                info.add(result.getString("Marca"));
                info.add(result.getString("Modello"));
                info.add(result.getString("Nome"));
                info.add(result.getString("Cognome"));
                info.add(result.getString("Data_di_inizio_noleggio"));
                info.add(result.getString("Data_di_fine_noleggio"));
                info.add(result.getString("Data_di_vendita"));
            }
            return info;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public List<String> getHistoryAzienda(final String targa){
        final String query = "SELECT c.Numero_di_contratto, a.Targa, a.Marca, a.Modello, az.Nome, az.Sede, n.Data_di_inizio_noleggio, n.Data_di_fine_noleggio, null as Data_di_vendita"+
        " FROM auto a, contratto c, azienda az, noleggio n"+
        " WHERE a.Targa = c.Auto"+
        " AND c.Azienda = az.Partita_iva"+
        " AND c.Numero_di_contratto = n.Numero_di_contratto"+
        " AND a.Targa = ?"+
        " UNION ALL"+
        " SELECT c.Numero_di_contratto, a.Targa, a.Marca, a.Modello, az.Nome, az.Sede, null, null, v.Data_di_vendita"+
        " FROM auto a, contratto c, azienda az, vendita v"+
        " WHERE a.Targa = c.Auto"+
        " AND c.Azienda = az.Partita_iva"+
        " AND c.Numero_di_contratto = v.Numero_di_contratto"+
        " AND a.Targa = ?;";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, targa);
            statement.setString(2, targa);
            final ResultSet result = statement.executeQuery();
            final List<String> info = new ArrayList<>();
            while (result.next()) {
                info.add(result.getString("Numero_di_contratto"));
                info.add(targa);
                info.add(result.getString("Marca"));
                info.add(result.getString("Modello"));
                info.add(result.getString("Nome"));
                info.add(result.getString("Sede"));
                info.add(result.getString("Data_di_inizio_noleggio"));
                info.add(result.getString("Data_di_fine_noleggio"));
                info.add(result.getString("Data_di_vendita"));
            }
            return info;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public List<Modello> getModello(){
        final String query = "SELECT * FROM modello ORDER BY MARCA;";

        try (final Statement statement = this.connection.createStatement()) {
            final ResultSet result = statement.executeQuery(query);
            final List<Modello> modello = new ArrayList<>();
            while (result.next()) {
                final String marca = result.getString("Marca");
                final String nome = result.getString("Nome");
                final int numero_di_posti = result.getInt("Numero_di_posti");
                final int cilindrata = result.getInt("Cilindrata");
                final int potenza = result.getInt("Potenza");
                final int prezzo_di_vendita = result.getInt("Prezzo_di_vendita");
                final int prezzo_di_noleggio = result.getInt("Prezzo_di_noleggio");
                
                final Modello m = new Modello(marca, nome, numero_di_posti, cilindrata, potenza, prezzo_di_vendita, prezzo_di_noleggio);
                modello.add(m);
            }
            return modello;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }


    public boolean insertAuto(final String targa, final int anno_di_immatricolazione, final String numero_di_telaio, 
        final String marca, final String modello){
        final String query = "INSERT INTO auto (Targa, Anno_di_immatricolazione, Numero_di_telaio, Marca, Modello)" +
            "VALUES (?, ?, ?, ?, ?);";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, targa);
            statement.setInt(2, anno_di_immatricolazione);
            statement.setString(3, numero_di_telaio);
            statement.setString(4, marca);
            statement.setString(5, modello);
            statement.executeUpdate();
            return true;
        } catch (final SQLException e) {
            return false;
        }
    }

    public List<Auto> getAuto(){
        final String query = "SELECT * FROM auto;";

        try (final Statement statement = this.connection.createStatement()) {
            final ResultSet result = statement.executeQuery(query);
            final List<Auto> auto = new ArrayList<>();
            while (result.next()) {
                final String targa = result.getString("Targa");
                final int anno_di_immatricolazione = result.getInt("Anno_di_immatricolazione");
                final String numero_di_telaio = result.getString("Numero_di_telaio");
                final String marca = result.getString("Marca");
                final String modello = result.getString("Modello");
                final boolean venduta = result.getBoolean("Venduta");
                final Optional<Date> data_di_fine_noleggio = Optional.ofNullable(result.getDate("Data_di_fine_noleggio"));
                
                final Auto a = new Auto(targa, anno_di_immatricolazione, numero_di_telaio, marca, modello, venduta, data_di_fine_noleggio);
                auto.add(a);
            }
            return auto;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public List<String> getCostoContrattoPrivato(final int numero_di_contratto){
        final String query = "SELECT p.Nome, p.Cognome, p.Codice_fiscale, a.Targa, a.Marca, a.Modello, m.Prezzo_di_vendita, m.Prezzo_di_noleggio,"+
        "    ROUND(m.Prezzo_di_vendita) as costo_contratto"+
        " FROM vendita v, contratto c, auto a, modello m, privato p"+
        " WHERE v.Numero_di_contratto = c.Numero_di_contratto"+
        " AND c.Auto = a.Targa"+
        " AND c.Privato = p.Codice_fiscale"+
        " AND a.Modello = m.Nome"+
        " AND a.Marca = m.Marca"+
        " AND v.Numero_di_contratto = ?"+
        " UNION ALL"+
        " SELECT p.Nome, p.Cognome, p.Codice_fiscale, a.Targa, a.Marca, a.Modello, m.Prezzo_di_vendita, m.Prezzo_di_noleggio,"+
        "     ROUND(m.Prezzo_di_noleggio*(SELECT DATEDIFF(n.Data_di_fine_noleggio, n.Data_di_inizio_noleggio) +1)) as costo_contratto"+
        " FROM noleggio n, contratto c, auto a, modello m, privato p"+
        " WHERE n.Numero_di_contratto = c.Numero_di_contratto"+
        " AND c.Auto = a.Targa"+
        " AND c.Privato = p.Codice_fiscale"+
        " AND a.Modello = m.Nome"+
        " AND a.Marca = m.Marca"+
        " AND n.Numero_di_contratto = ?;";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setInt(1, numero_di_contratto);
            statement.setInt(2, numero_di_contratto);
            final ResultSet result = statement.executeQuery();
            final List<String> info = new ArrayList<>();
            while (result.next()) {
                info.add(result.getString("Nome"));
                info.add(result.getString("Cognome"));
                info.add(result.getString("Codice_fiscale"));
                info.add(result.getString("Targa"));
                info.add(result.getString("Marca"));
                info.add(result.getString("Modello"));
                info.add(result.getString("Prezzo_di_vendita"));
                info.add(result.getString("Prezzo_di_noleggio"));
                info.add(result.getString("costo_contratto"));
            }
            return info;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public List<String> getContrattiPrivato(final String codice_fiscale){
        final String query = "SELECT c.*, a.Marca, a.Modello"+
        " FROM contratto c, auto a"+
        " WHERE c.Auto = a.Targa"+
        " AND c.Privato = ?;";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, codice_fiscale);
            final ResultSet result = statement.executeQuery();
            final List<String> info = new ArrayList<>();
            while (result.next()) {
                info.add(result.getString("Numero_di_contratto"));
                info.add(result.getString("Privato"));
                info.add(result.getString("Dipendente"));
                info.add(result.getString("Auto"));
                info.add(result.getString("Marca"));
                info.add(result.getString("Modello"));
            }
            return info;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public boolean insertPrivati(final String nome, final String cognome, final String codice_fiscale, 
        final long numero_di_telefono, final String e_mail){
        final String query = "INSERT INTO privato (Nome, Cognome, Codice_fiscale, Numero_di_telefono, E_mail)"+
            "VALUES (?, ?, ?, ?, ?);";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, nome);
            statement.setString(2, cognome);
            statement.setString(3, codice_fiscale);
            statement.setLong(4, numero_di_telefono);
            statement.setString(5, e_mail);
            statement.executeUpdate();
            return true;
        } catch (final SQLException e) {
            return false;
        }  
    }

    public List<Privato> getPrivati(){
        final String query = "SELECT * FROM privato;";

        try (final Statement statement = this.connection.createStatement()) {
            final ResultSet result = statement.executeQuery(query);
            final List<Privato> privato = new ArrayList<>();
            while (result.next()) {
                final String nome = result.getString("Nome");
                final String cognome = result.getString("Cognome");
                final String codice_fiscale = result.getString("Codice_fiscale");
                final Long numero_di_telefono = result.getLong("Numero_di_telefono");
                final String e_mail = result.getString("E_mail");
                
                final Privato p = new Privato(nome, cognome, codice_fiscale, numero_di_telefono, e_mail);
                privato.add(p);
            }
            return privato;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public List<String> getCostoContrattoAzienda(final int numero_di_contratto){
        final String query = "SELECT az.Nome, az.Sede, az.Partita_iva, a.Targa, a.Marca, a.Modello, m.Prezzo_di_vendita, m.Prezzo_di_noleggio, "+
        " ROUND(m.Prezzo_di_vendita*(1-(s.Percentuale_di_sconto/100))) as costo_contratto"+
        " FROM vendita v, contratto c, auto a, modello m, sconto s, azienda az"+
        " WHERE v.Numero_di_contratto = c.Numero_di_contratto"+
        " AND c.Sconto = s.Fatturato"+
        " AND c.Azienda = az.Partita_iva"+
        " AND c.Auto = a.Targa"+
        " AND a.Modello = m.Nome"+
        " AND a.Marca = m.Marca"+
        " AND v.Numero_di_contratto = ?"+
        " UNION ALL"+
        " SELECT az.Nome, az.Sede, az.Partita_iva, a.Targa, a.Marca, a.Modello, m.Prezzo_di_vendita, m.Prezzo_di_noleggio,"+
        "     ROUND((m.Prezzo_di_noleggio*(SELECT DATEDIFF(n.Data_di_fine_noleggio, n.Data_di_inizio_noleggio)+1))*(1-(s.Percentuale_di_sconto/100)))"+
        "     as costo_contratto"+
        " FROM noleggio n, contratto c, auto a, modello m, sconto s, azienda az"+
        " WHERE n.Numero_di_contratto = c.Numero_di_contratto"+
        " AND c.Sconto = s.Fatturato"+
        " AND c.Azienda = az.Partita_iva"+
        " AND c.Auto = a.Targa"+
        " AND a.Modello = m.Nome"+
        " AND a.Marca = m.Marca"+
        " AND n.Numero_di_contratto = ?;";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setInt(1, numero_di_contratto);
            statement.setInt(2, numero_di_contratto);
            final ResultSet result = statement.executeQuery();
            final List<String> info = new ArrayList<>();
            while (result.next()) {
                info.add(result.getString("Nome"));
                info.add(result.getString("Sede"));
                info.add(result.getString("Partita_iva"));
                info.add(result.getString("Targa"));
                info.add(result.getString("Marca"));
                info.add(result.getString("Modello"));
                info.add(result.getString("Prezzo_di_vendita"));
                info.add(result.getString("Prezzo_di_noleggio"));
                info.add(result.getString("costo_contratto"));
            }
            return info;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public List<String> getContrattiAzienda(final String partita_iva){
        final String query = "SELECT c.*, a.Marca, a.Modello"+
        " FROM contratto c, auto a"+
        " WHERE c.Auto = a.Targa"+
        " AND c.Azienda = ?;";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, partita_iva);
            final ResultSet result = statement.executeQuery();
            final List<String> info = new ArrayList<>();
            while (result.next()) {
                info.add(result.getString("Numero_di_contratto"));
                info.add(result.getString("Azienda"));
                info.add(result.getString("Dipendente"));
                info.add(result.getString("Auto"));
                info.add(result.getString("Marca"));
                info.add(result.getString("Modello"));
            }
            return info;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public boolean insertAziende(final long partita_iva, final String nome, final String sede, final int fatturato){
        final String query = "INSERT INTO azienda (Partita_iva, Nome, Sede, Fatturato)"+
            "VALUES (?, ?, ?, ?);";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setLong(1, partita_iva);
            statement.setString(2, nome);
            statement.setString(3, sede);
            statement.setInt(4, fatturato);
            statement.executeUpdate();
            return true;
        } catch (final SQLException e) {
            return false;
        }
    }

    public List<Azienda> getAziende(){
        final String query = "SELECT * FROM azienda;";

        try (final Statement statement = this.connection.createStatement()) {
            final ResultSet result = statement.executeQuery(query);
            final List<Azienda> azienda = new ArrayList<>();
            while (result.next()) {
                final long partita_iva = result.getLong("Partita_iva");
                final String nome = result.getString("Nome");
                final String sede = result.getString("Sede");
                final int fatturato = result.getInt("Fatturato");
                
                final Azienda a = new Azienda(partita_iva, nome, sede, fatturato);
                azienda.add(a);
            }
            return azienda;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public Pair<Boolean, String> getNumeroContrattiDipendente(final String codice_fiscale){
        final String query = "SELECT d.Numero_di_contratti"+
            " FROM dipendente d"+
            " WHERE d.Codice_fiscale = ?;";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, codice_fiscale);
            ResultSet result = statement.executeQuery();
            result.next();
            return new Pair<Boolean,String>(true, result.getString("Numero_di_contratti"));
        } catch (final SQLException e) {
            return new Pair<Boolean,String>(false, null);
        }
    }

    public boolean insertDipendenti(final String nome, final String cognome, final String codice_fiscale,
        final long numero_di_telefono, final int stipendio, final Date data_di_assunzione){
        final String query = "INSERT INTO dipendente (Nome, Cognome, Codice_fiscale, Numero_di_telefono, Stipendio, Data_di_assunzione)"+
            "VALUES (?, ?, ?, ?, ?, ?);";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, nome);
            statement.setString(2, cognome);
            statement.setString(3, codice_fiscale);
            statement.setLong(4, numero_di_telefono);
            statement.setInt(5, stipendio);
            statement.setDate(6, data_di_assunzione);
            statement.executeUpdate();
            return true;
        } catch (final SQLException e) {
            return false;
        }
    }

    public List<Dipendente> getDipendenti(){
        final String query = "SELECT * FROM dipendente;";

        try (final Statement statement = this.connection.createStatement()) {
            final ResultSet result = statement.executeQuery(query);
            final List<Dipendente> dipendente = new ArrayList<>();
            while (result.next()) {
                final String nome = result.getString("Nome");
                final String cognome = result.getString("Cognome");
                final String codice_fiscale = result.getString("Codice_fiscale");
                final long numero_di_telefono = result.getLong("Numero_di_telefono");
                final int numero_di_contratti = result.getInt("Numero_di_contratti");
                final int stipendio = result.getInt("Stipendio");
                final Date data_di_assunzione = result.getDate("Data_di_assunzione");
                
                final Dipendente d = new Dipendente(nome, cognome, codice_fiscale, numero_di_telefono, numero_di_contratti,
                    stipendio, data_di_assunzione);
                dipendente.add(d);
            }
            return dipendente;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public List<String> getContratti(){
        final String query = "SELECT * FROM"+
            " (SELECT c.*, n.Data_di_inizio_noleggio, n.Data_di_fine_noleggio, null as Data_di_vendita"+
            " FROM contratto c, noleggio n"+
            " WHERE c.Numero_di_contratto = n.Numero_di_contratto"+
            " UNION ALL"+
            " SELECT c.*, null, null, v.Data_di_vendita"+
            " FROM contratto c, vendita v"+
            " WHERE c.Numero_di_contratto = v.Numero_di_contratto) as contratti"+
            " ORDER BY Numero_di_contratto;";

        try (final Statement statement = this.connection.createStatement()) {
            final ResultSet result = statement.executeQuery(query);
            final List<String> info = new ArrayList<>();
            while (result.next()) {
                info.add(result.getString("Numero_di_contratto"));
                info.add(result.getString("Azienda"));
                info.add(result.getString("Privato"));
                info.add(result.getString("Dipendente"));
                info.add(result.getString("Sconto"));
                info.add(result.getString("Auto"));
                info.add(result.getString("Data_di_vendita"));
                info.add(result.getString("Data_di_inizio_noleggio"));
                info.add(result.getString("Data_di_fine_noleggio"));
            }
            return info;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public boolean insertVenditaPrivato(final String codice_fiscalePrivato, final String codice_fiscaleDipendente,
        final String targa){
        ResultSet result;
        final int numero_di_contratto;
        final String query1 = "SELECT Targa, Anno_di_immatricolazione, Numero_di_telaio, Marca, Modello"+
        " FROM auto"+
        " WHERE Venduta=0"+
        " AND Targa = ?"+
        " AND (Data_di_fine_noleggio is null"+
        " OR Data_di_fine_noleggio<curdate());";
        try (final PreparedStatement statement = this.connection.prepareStatement(query1)) {
            statement.setString(1, targa);
            result = statement.executeQuery();
            if(!result.next()){
                return false;
            }
        } catch (final SQLException e) {
            System.out.println("auto non disponibile");
            return false;
        }

        final String query2 = "INSERT INTO contratto (Privato, Dipendente, Auto)"+
            " VALUES (?, ?, ?);";
        try (final PreparedStatement statement = this.connection.prepareStatement(query2)) {
            statement.setString(1, codice_fiscalePrivato);
            statement.setString(2, codice_fiscaleDipendente);
            statement.setString(3, targa);
            statement.executeUpdate();
        } catch (final SQLException e) {
            System.out.println("inserimento contratto non andato a buon fine");
            return false;
        }

        final String query3 = "SELECT Numero_di_contratto"+
                " FROM contratto"+
                " ORDER BY Numero_di_contratto DESC"+
                " LIMIT 1;";
        try (final Statement statement = this.connection.createStatement()) {
            result = statement.executeQuery(query3);
            result.next();
            numero_di_contratto = result.getInt("Numero_di_contratto");
        } catch (final SQLException e) {
            System.out.println("recupero numero_di_contratto fallito");
            return false;
        }

        final String query4 = "INSERT INTO vendita (Numero_di_contratto, Data_di_vendita)"+
                " VALUES (?, curdate());"; 
        try (final PreparedStatement statement = this.connection.prepareStatement(query4)) {
            statement.setInt(1, numero_di_contratto);
            statement.executeUpdate();
        } catch (final SQLException e) {
            System.out.println("inserimento vendita fallito");
            return false;
        }

        final String query5 = "UPDATE auto"+
            " SET Venduta = 1"+
            " WHERE Targa = ?;"; 
        try (final PreparedStatement statement = this.connection.prepareStatement(query5)) {
            statement.setString(1, targa);
            statement.executeUpdate();
        } catch (final SQLException e) {
            System.out.println("aggiornamento auto fallito");
            return false;
        }

        final String query6 = "UPDATE dipendente"+
            " SET Numero_di_contratti = Numero_di_contratti+1"+
            " WHERE Codice_fiscale = ?;"; 
        try (final PreparedStatement statement = this.connection.prepareStatement(query6)) {
            statement.setString(1, codice_fiscaleDipendente);
            statement.executeUpdate();
        } catch (final SQLException e) {
            System.out.println("aggiornamento dipendente fallito");
            return false;
        }
        return true;
    }

    public boolean insertNoleggioPrivato(final String codice_fiscalePrivato, final String codice_fiscaleDipendente,
        final String targa, final Date data_di_fine_noleggio){
        ResultSet result;
        final int numero_di_contratto;
        final String query1 = "SELECT Targa, Anno_di_immatricolazione, Numero_di_telaio, Marca, Modello"+
        " FROM auto"+
        " WHERE Venduta=0"+
        " AND Targa = ?"+
        " AND (Data_di_fine_noleggio is null"+
        " OR Data_di_fine_noleggio<curdate());";
        try (final PreparedStatement statement = this.connection.prepareStatement(query1)) {
            statement.setString(1, targa);
            result = statement.executeQuery();
            if(!result.next()){
                return false;
            }
        } catch (final SQLException e) {
            System.out.println("auto non disponibile");
            return false;
        }

        final String query2 = "INSERT INTO contratto (Privato, Dipendente, Auto)"+
            " VALUES (?, ?, ?);";
        try (final PreparedStatement statement = this.connection.prepareStatement(query2)) {
            statement.setString(1, codice_fiscalePrivato);
            statement.setString(2, codice_fiscaleDipendente);
            statement.setString(3, targa);
            statement.executeUpdate();
        } catch (final SQLException e) {
            System.out.println("inserimento contratto non andato a buon fine");
            return false;
        }

        final String query3 = "SELECT Numero_di_contratto"+
                " FROM contratto"+
                " ORDER BY Numero_di_contratto DESC"+
                " LIMIT 1;";
        try (final Statement statement = this.connection.createStatement()) {
            result = statement.executeQuery(query3);
            result.next();
            numero_di_contratto = result.getInt("Numero_di_contratto");
        } catch (final SQLException e) {
            System.out.println("recupero numero_di_contratto fallito");
            return false;
        }

        final String query4 = "INSERT INTO noleggio (Numero_di_contratto, Data_di_inizio_noleggio, Data_di_fine_noleggio)"+
            " VALUES (?, curdate(), ?);"; 
        try (final PreparedStatement statement = this.connection.prepareStatement(query4)) {
            statement.setInt(1, numero_di_contratto);
            statement.setDate(2, data_di_fine_noleggio);            
            statement.executeUpdate();
        } catch (final SQLException e) {
            System.out.println("inserimento noleggio fallito");
            return false;
        }

        final String query5 = "UPDATE auto"+
            " SET Data_di_fine_noleggio = ?"+
            " WHERE Targa = ?;"; 
        try (final PreparedStatement statement = this.connection.prepareStatement(query5)) {
            statement.setDate(1, data_di_fine_noleggio);
            statement.setString(2, targa);
            statement.executeUpdate();
        } catch (final SQLException e) {
            System.out.println("aggiornamento auto fallito");
            return false;
        }

        final String query6 = "UPDATE dipendente"+
            " SET Numero_di_contratti = Numero_di_contratti+1"+
            " WHERE Codice_fiscale = ?;"; 
        try (final PreparedStatement statement = this.connection.prepareStatement(query6)) {
            statement.setString(1, codice_fiscaleDipendente);
            statement.executeUpdate();
        } catch (final SQLException e) {
            System.out.println("aggiornamento dipendente fallito");
            return false;
        }
        return true;
    }

    public boolean insertVenditaAzienda(final long partita_iva, final String codice_fiscaleDipendente, final String targa){
        ResultSet result;
        final int sconto;
        final int numero_di_contratto;
        final String query1 = "SELECT Targa, Anno_di_immatricolazione, Numero_di_telaio, Marca, Modello"+
        " FROM auto"+
        " WHERE Venduta=0"+
        " AND Targa = ?"+
        " AND (Data_di_fine_noleggio is null"+
        " OR Data_di_fine_noleggio<curdate());";
        try (final PreparedStatement statement = this.connection.prepareStatement(query1)) {
            statement.setString(1, targa);
            result = statement.executeQuery();
            if(!result.next()){
                return false;
            }
        } catch (final SQLException e) {
            System.out.println("auto non disponibile");
            return false;
        }

        final String query2 = "SELECT max(Fatturato) as Fatturato"+
        " FROM sconto"+
        " WHERE Fatturato<=(SELECT a.Fatturato"+
        " FROM azienda a"+
        " WHERE Partita_iva=?);";
        try (final PreparedStatement statement = this.connection.prepareStatement(query2)) {
            statement.setLong(1, partita_iva);
            result = statement.executeQuery();
            result.next();
            sconto = result.getInt("Fatturato");
        } catch (final SQLException e) {
            System.out.println("recupero sconto fallito");
            return false;
        }

        final String query3 = "INSERT INTO contratto (Azienda, Dipendente, Sconto, Auto)"+
            " VALUES (?, ?, ?, ?);";
        try (final PreparedStatement statement = this.connection.prepareStatement(query3)) {
            statement.setLong(1, partita_iva);
            statement.setString(2, codice_fiscaleDipendente);
            statement.setInt(3, sconto);
            statement.setString(4, targa);
            statement.executeUpdate();
        } catch (final SQLException e) {
            System.out.println("inserimento contratto non andato a buon fine");
            return false;
        }

        final String query4 = "SELECT Numero_di_contratto"+
                " FROM contratto"+
                " ORDER BY Numero_di_contratto DESC"+
                " LIMIT 1;";
        try (final Statement statement = this.connection.createStatement()) {
            result = statement.executeQuery(query4);
            result.next();
            numero_di_contratto = result.getInt("Numero_di_contratto");
        } catch (final SQLException e) {
            System.out.println("recupero numero_di_contratto fallito");
            return false;
        }

        final String query5 = "INSERT INTO vendita (Numero_di_contratto, Data_di_vendita)"+
                " VALUES (?, curdate());"; 
        try (final PreparedStatement statement = this.connection.prepareStatement(query5)) {
            statement.setInt(1, numero_di_contratto);
            statement.executeUpdate();
        } catch (final SQLException e) {
            System.out.println("inserimento vendita fallito");
            return false;
        }

        final String query6 = "UPDATE auto"+
            " SET Venduta = 1"+
            " WHERE Targa = ?;"; 
        try (final PreparedStatement statement = this.connection.prepareStatement(query6)) {
            statement.setString(1, targa);
            statement.executeUpdate();
        } catch (final SQLException e) {
            System.out.println("aggiornamento auto fallito");
            return false;
        }

        final String query7 = "UPDATE dipendente"+
            " SET Numero_di_contratti = Numero_di_contratti+1"+
            " WHERE Codice_fiscale = ?;"; 
        try (final PreparedStatement statement = this.connection.prepareStatement(query7)) {
            statement.setString(1, codice_fiscaleDipendente);
            statement.executeUpdate();
        } catch (final SQLException e) {
            System.out.println("aggiornamento dipendente fallito");
            return false;
        }
        return true;
    }

    public boolean insertNoleggioAzienda(final long partita_iva, final String codice_fiscaleDipendente, final String targa,
        final Date data_di_fine_noleggio){
        ResultSet result;
        final int sconto;
        final int numero_di_contratto;
        final String query1 = "SELECT Targa, Anno_di_immatricolazione, Numero_di_telaio, Marca, Modello"+
        " FROM auto"+
        " WHERE Venduta=0"+
        " AND Targa = ?"+
        " AND (Data_di_fine_noleggio is null"+
        " OR Data_di_fine_noleggio<curdate());";
        try (final PreparedStatement statement = this.connection.prepareStatement(query1)) {
            statement.setString(1, targa);
            result = statement.executeQuery();
            if(!result.next()){
                return false;
            }
        } catch (final SQLException e) {
            System.out.println("auto non disponibile");
            return false;
        }

        final String query2 = "SELECT max(Fatturato) as Fatturato"+
        " FROM sconto"+
        " WHERE Fatturato<=(SELECT a.Fatturato"+
        " FROM azienda a"+
        " WHERE Partita_iva=?);";
        try (final PreparedStatement statement = this.connection.prepareStatement(query2)) {
            statement.setLong(1, partita_iva);
            result = statement.executeQuery();
            result.next();
            sconto = result.getInt("Fatturato");
        } catch (final SQLException e) {
            System.out.println("recupero sconto fallito");
            return false;
        }

        final String query3 = "INSERT INTO contratto (Azienda, Dipendente, Sconto, Auto)"+
            " VALUES (?, ?, ?, ?);";
        try (final PreparedStatement statement = this.connection.prepareStatement(query3)) {
            statement.setLong(1, partita_iva);
            statement.setString(2, codice_fiscaleDipendente);
            statement.setInt(3, sconto);
            statement.setString(4, targa);
            statement.executeUpdate();
        } catch (final SQLException e) {
            System.out.println("inserimento contratto non andato a buon fine");
            return false;
        }

        final String query4 = "SELECT Numero_di_contratto"+
                " FROM contratto"+
                " ORDER BY Numero_di_contratto DESC"+
                " LIMIT 1;";
        try (final Statement statement = this.connection.createStatement()) {
            result = statement.executeQuery(query4);
            result.next();
            numero_di_contratto = result.getInt("Numero_di_contratto");
        } catch (final SQLException e) {
            System.out.println("recupero numero_di_contratto fallito");
            return false;
        }

        final String query5 = "INSERT INTO noleggio (Numero_di_contratto, Data_di_inizio_noleggio, Data_di_fine_noleggio)"+
            " VALUES (?, curdate(), ?);"; 
        try (final PreparedStatement statement = this.connection.prepareStatement(query5)) {
            statement.setInt(1, numero_di_contratto);
            statement.setDate(2, data_di_fine_noleggio);
            statement.executeUpdate();
        } catch (final SQLException e) {
            System.out.println("inserimento noleggio fallito");
            return false;
        }

        final String query6 = "UPDATE auto"+
            " SET Data_di_fine_noleggio = ?"+
            " WHERE Targa = ?;"; 
        try (final PreparedStatement statement = this.connection.prepareStatement(query6)) {
            statement.setDate(1, data_di_fine_noleggio);
            statement.setString(2, targa);
            statement.executeUpdate();
        } catch (final SQLException e) {
            System.out.println("aggiornamento auto fallito");
            return false;
        }

        final String query7 = "UPDATE dipendente"+
            " SET Numero_di_contratti = Numero_di_contratti+1"+
            " WHERE Codice_fiscale = ?;"; 
        try (final PreparedStatement statement = this.connection.prepareStatement(query7)) {
            statement.setString(1, codice_fiscaleDipendente);
            statement.executeUpdate();
        } catch (final SQLException e) {
            System.out.println("aggiornamento dipendente fallito");
            return false;
        }
        return true;
    }
}
