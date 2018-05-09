package br.com.database;
import br.com.bean.ContatoBean;
import br.com.bean.Data;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ContatoDAO {
    
    private Connection conexao;
    
    public ContatoDAO(String usuario, String senha) {
        try {
            // Comando para o servlet web reconhecer o driver
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            conexao = DriverManager.getConnection
                      ("jdbc:mysql://localhost/agenda", usuario, senha);
            System.out.println("Conexão efetuada com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao se conectar ao banco de dados!");
            System.err.println(e.getMessage());
        }
    }
    
    public void cadastrarContato(ContatoBean contato) {
        String sql = "INSERT INTO contato(nome, email, endereco, data_nasc) "
                + "VALUES(?, ?, ?, ?)";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, contato.getNome());
            stmt.setString(2, contato.getEmail());
            stmt.setString(3, contato.getEndereco());
            stmt.setString(4, contato.getData_nascimento().convertToSQLFormat());
            stmt.execute();
            System.out.println("Executando a SQL:\n" + stmt);
            System.out.println("Contato adicionado com sucesso!");
        } catch (SQLException ex) {
            System.err.println("Erro ao inserir dado no banco de dados");
            System.out.println("Comando executado:\n" + sql);
            Logger.getLogger(ContatoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public ArrayList<ContatoBean> consultarTodos() {
        String sql = "SELECT * FROM contato";
        ArrayList<ContatoBean> lista = new ArrayList<>();
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet r = stmt.executeQuery();
            
            while (r.next()) {
                ContatoBean bean = new ContatoBean();
                bean.setNome(r.getString("nome"));
                bean.setEndereco(r.getString("endereco"));
                bean.setEmail(r.getString("email"));
                String aux[] = r.getString("data_nasc").split("-");
                System.out.println(aux[0] + " " + aux[1] + " " + aux[2]);
                bean.setData_nascimento(new Data(Integer.parseInt(aux[2]),
                                                 Integer.parseInt(aux[1]),
                                                 Integer.parseInt(aux[0])));
                lista.add(bean);
            }
                        
        } catch (SQLException ex) {
            Logger.getLogger(ContatoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return lista;
    }
    
    
    public void fecharConexao() {
        try {
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(ContatoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
