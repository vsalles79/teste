package br.com.database;

import br.com.bean.ContatoBean;
import br.com.bean.Data;
import org.junit.Test;
import static org.junit.Assert.*;

public class ContatoDAOTest {
    
    public ContatoDAOTest() {
    }

    /**
     * Test of fecharConexao method, of class ContatoDAO.
     */
    @Test
    public void testarAbrirConexao() {
        System.out.println("\n\n --------------- TESTE 1 ---------------");
        ContatoDAO db = new ContatoDAO("root", "1234");
    }
    
    @Test
    public void testarAdicionarContato() {
        System.out.println("\n\n --------------- TESTE 2 ---------------");
        ContatoDAO dao = new ContatoDAO("aluno", "1234");
        ContatoBean c1 = new ContatoBean();
        c1.setNome("Fulano de Tal");
        c1.setEmail("fulano@gmail.com");
        c1.setEndereco("Rua XPTO");
        c1.setData_nascimento(new Data(8,5,2000));
        dao.cadastrarContato(c1);
    }
    
    
}
