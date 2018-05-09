package br.com.servlet;

import br.com.bean.ContatoBean;
import br.com.bean.Data;
import br.com.database.ContatoDAO;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;


@WebServlet(name = "adicionaContato", urlPatterns = {"/adicionacontato"})
public class AdicionaContatoServlet extends HttpServlet {
    private int contador;
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        contador = 0;
        log("Iniciando o servlet");
    }
    
    /**
     * Método principal para processar as requisições e respostas do usuário
     * @param request objeto responsável por processar as entradas do usuário
     * @param response objeto responsável por gerar o conteúdo da página
     */
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) {
        ContatoDAO dao = new ContatoDAO("aluno", "1234");
        ContatoBean bean = new ContatoBean();        
        contador++;
        try {
            /* Obtém as entradas (requisições) fornecidas pelo usuário
            no formulário web */
            String nome = request.getParameter("nome");
            String endereco = request.getParameter("endereco");
            String email = request.getParameter("email");
            String data_nasc = request.getParameter("dataNascimento");
                        
            /* Criar um objeto bean para representar um contato da agenda */            
            bean.setNome(nome);
            bean.setEndereco(endereco);
            bean.setEmail(email);
            String aux[] = data_nasc.split("-");
            System.out.println(aux[0] + " " + aux[1] + " " + aux[2]);
            bean.setData_nascimento(new Data(Integer.parseInt(aux[2]),
                                             Integer.parseInt(aux[1]),
                                             Integer.parseInt(aux[0])));
            
            dao.cadastrarContato(bean);
            
            ArrayList<ContatoBean> lista = dao.consultarTodos();
            
            /* Cria a página HTML como resposta ao usuário */
            PrintWriter out = response.getWriter();
            out.println("<html>");
            out.println("<head>");
            out.println("<title>");
            out.println("Olá mundo");
            out.println("</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("Contato " + nome + " adicionado com sucesso!");
            
            out.println("<hr>");
            
            out.println("<h2>Lista de contatos cadastrados</h2>");
            for (ContatoBean c : lista) {
                out.println(c.getNome() + "\t" 
                        + c.getEndereco() + "\t"
                        + c.getEmail() + "\t"
                        + c.getData_nascimento() + "<br>");
            }
            
            out.println("</body>");
            out.println("</html>");
            dao.fecharConexao();
        } catch (IOException ex) {
            Logger.getLogger(AdicionaContatoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
    @Override
    public void destroy() {
        super.destroy();
        log("Destruindo a servlet");
    }
    
}
