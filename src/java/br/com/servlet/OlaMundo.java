package br.com.servlet;


import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.*;

public class OlaMundo extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        
        // Escreve o texto
        out.println("<html>");
        out.println("<head>");
        out.println("<title>");
        out.println("Olá mundo");
        out.println("</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Primeira servlet</h1>");
        out.println("</body>");
        out.println("</html>");
        System.out.println("Fim da página");
    }
}
