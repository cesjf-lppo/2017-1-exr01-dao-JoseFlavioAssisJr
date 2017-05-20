package br.cesjf.lppo.servlets;

import br.cesjf.lppo.Pedido;
import br.cesjf.lppo.dao.PedidoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author alunoces
 */
@WebServlet(name = "DetalhesServlet", urlPatterns = {"/detalhes.html/exclui.html"})
public class DetalhesServlet extends HttpServlet {

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            //Long int = Long.parseInt(request.getParameter("id"));
            
            PedidoDAO dao = new PedidoDAO();
            Pedido pedido = dao.getById(id);
            // achou o objeto, agor desenha ele
            request.setAttribute("pedido", pedido);
            request.getRequestDispatcher("WEB-INF/listaPedidos.jsp").forward(request, response);
            
        } catch (NumberFormatException e){
            response.sendRedirect("pedidos.html");
        } catch (Exception ex) {
            Logger.getLogger(DetalhesServlet.class.getName()).log(Level.SEVERE, null, ex);
            response.sendRedirect("pedidos.html");
        }
    }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            Long id = Long.parseLong(request.getParameter("id"));
            
            PedidoDAO dao = new PedidoDAO();
            Pedido contato = dao.getById(id);
            
//            contato.setNome(request.getParameter("nome"));
//            contato.setSobrenome(request.getParameter("sobrenome"));
//            contato.setTelefone(request.getParameter("telefone"));
            
            dao.atualiza(contato);
            response.sendRedirect("pedidos.html");
            
        } catch (NumberFormatException e){
            response.sendRedirect("pedidos.html");
        } catch (Exception ex) {
            Logger.getLogger(DetalhesServlet.class.getName()).log(Level.SEVERE, null, ex);
            response.sendRedirect("pedidos.html");
        }
        
    }

    

}
