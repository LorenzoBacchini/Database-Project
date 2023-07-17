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
import concessionario.db.tables.Contratto;
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
}
