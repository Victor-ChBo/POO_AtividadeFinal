import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PessoaDAO {
    private static final String URL = "jdbc:sqlite:clube.db";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public void criarTabelas() {
        String sqlPessoa = "CREATE TABLE IF NOT EXISTS Pessoa (cpf VARCHAR(14) PRIMARY KEY, nome VARCHAR(255), email VARCHAR(255));";
        String sqlSocio = "CREATE TABLE IF NOT EXISTS Socio (cpf_pessoa VARCHAR(14) PRIMARY KEY, numeroSocio INT, plano VARCHAR(50), FOREIGN KEY (cpf_pessoa) REFERENCES Pessoa(cpf) ON DELETE CASCADE);";
        String sqlConvidado = "CREATE TABLE IF NOT EXISTS Convidado (cpf_pessoa VARCHAR(14) PRIMARY KEY, dataVisita VARCHAR(20), FOREIGN KEY (cpf_pessoa) REFERENCES Pessoa(cpf) ON DELETE CASCADE);";
        String sqlFuncionario = "CREATE TABLE IF NOT EXISTS Funcionario (cpf_pessoa VARCHAR(14) PRIMARY KEY, cargo VARCHAR(100), salario REAL, FOREIGN KEY (cpf_pessoa) REFERENCES Pessoa(cpf) ON DELETE CASCADE);";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sqlPessoa);
            stmt.execute(sqlSocio);
            stmt.execute(sqlConvidado);
            stmt.execute(sqlFuncionario);
        } catch (SQLException e) {
            System.out.println("Erro ao criar tabelas: " + e.getMessage());
        }
    }

    public void salvarSocio(Socio socio) throws SQLException {
        String sqlPessoa = "INSERT INTO Pessoa (cpf, nome, email) VALUES (?, ?, ?)";
        String sqlSocio = "INSERT INTO Socio (cpf_pessoa, numeroSocio, plano) VALUES (?, ?, ?)";

        try (Connection conn = getConnection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement psP = conn.prepareStatement(sqlPessoa);
                 PreparedStatement psS = conn.prepareStatement(sqlSocio)) {
                
                psP.setString(1, socio.getCpf());
                psP.setString(2, socio.getNome());
                psP.setString(3, socio.getEmail());
                psP.executeUpdate();

                psS.setString(1, socio.getCpf());
                psS.setInt(2, socio.getNumeroSocio());
                psS.setString(3, socio.getPlano());
                psS.executeUpdate();
                
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        }
    }

    public List<Socio> listarSocios() {
        List<Socio> lista = new ArrayList<>();
        String sql = "SELECT p.nome, p.cpf, p.email, s.numeroSocio, s.plano FROM Pessoa p JOIN Socio s ON p.cpf = s.cpf_pessoa";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(new Socio(
                    rs.getString("nome"),
                    rs.getString("cpf"),
                    rs.getString("email"),
                    rs.getInt("numeroSocio"),
                    rs.getString("plano")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar sócios: " + e.getMessage());
        }
        return lista;
    }

    public boolean removerSocio(String cpf) {
        return removerPessoaGenerico(cpf, "Socio");
    }

    public void salvarConvidado(Convidado convidado) throws SQLException {
        String sqlPessoa = "INSERT INTO Pessoa (cpf, nome, email) VALUES (?, ?, ?)";
        String sqlConvidado = "INSERT INTO Convidado (cpf_pessoa, dataVisita) VALUES (?, ?)";

        try (Connection conn = getConnection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement psP = conn.prepareStatement(sqlPessoa);
                 PreparedStatement psC = conn.prepareStatement(sqlConvidado)) {
                psP.setString(1, convidado.getCpf());
                psP.setString(2, convidado.getNome());
                psP.setString(3, convidado.getEmail());
                psP.executeUpdate();
                psC.setString(1, convidado.getCpf());
                psC.setString(2, convidado.getDataVisita());
                psC.executeUpdate();
                conn.commit();
            } catch (SQLException e) { conn.rollback(); throw e; }
        }
    }

    public List<Convidado> listarConvidados() {
        List<Convidado> lista = new ArrayList<>();
        String sql = "SELECT p.nome, p.cpf, p.email, c.dataVisita FROM Pessoa p JOIN Convidado c ON p.cpf = c.cpf_pessoa";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Convidado(rs.getString("nome"), rs.getString("cpf"), rs.getString("email"), rs.getString("dataVisita")));
            }
        } catch (SQLException e) { System.out.println("Erro listar convidados: " + e.getMessage()); }
        return lista;
    }


    public void salvarFuncionario(Funcionario func) throws SQLException {
        String sqlPessoa = "INSERT INTO Pessoa (cpf, nome, email) VALUES (?, ?, ?)";
        String sqlFunc = "INSERT INTO Funcionario (cpf_pessoa, cargo, salario) VALUES (?, ?, ?)";
        try (Connection conn = getConnection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement psP = conn.prepareStatement(sqlPessoa); PreparedStatement psF = conn.prepareStatement(sqlFunc)) {
                psP.setString(1, func.getCpf()); psP.setString(2, func.getNome()); psP.setString(3, func.getEmail()); psP.executeUpdate();
                psF.setString(1, func.getCpf()); psF.setString(2, func.getCargo()); psF.setDouble(3, func.getSalario()); psF.executeUpdate();
                conn.commit();
            } catch (SQLException e) { conn.rollback(); throw e; }
        }
    }

    public List<Funcionario> listarFuncionarios() {
        List<Funcionario> lista = new ArrayList<>();
        String sql = "SELECT p.nome, p.cpf, p.email, f.cargo, f.salario FROM Pessoa p JOIN Funcionario f ON p.cpf = f.cpf_pessoa";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Funcionario(rs.getString("nome"), rs.getString("cpf"), rs.getString("email"), rs.getString("cargo"), rs.getDouble("salario")));
            }
        } catch (SQLException e) { System.out.println("Erro listar funcionários: " + e.getMessage()); }
        return lista;
    }

    public boolean removerFuncionario(String cpf) {
        return removerPessoaGenerico(cpf, "Funcionario");
    }

    private boolean removerPessoaGenerico(String cpf, String tabelaFilha) {
        String sqlFilha = "DELETE FROM " + tabelaFilha + " WHERE cpf_pessoa = ?";
        String sqlPai = "DELETE FROM Pessoa WHERE cpf = ?";
        try (Connection conn = getConnection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement psF = conn.prepareStatement(sqlFilha); PreparedStatement psP = conn.prepareStatement(sqlPai)) {
                psF.setString(1, cpf); psF.executeUpdate();
                psP.setString(1, cpf);
                if (psP.executeUpdate() > 0) { conn.commit(); return true; } 
                else { conn.rollback(); return false; }
            } catch (SQLException e) { conn.rollback(); throw e; }
        } catch (SQLException e) { return false; }
    }
}