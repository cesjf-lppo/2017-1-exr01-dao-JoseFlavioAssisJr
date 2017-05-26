package br.cesjf.lppo.servlets;

import br.cesjf.lppo.Pedido;
import br.cesjf.lppo.dao.PedidoDAO;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author José Flávio
 */
@WebServlet(name = "EditaServlet", urlPatterns = {"/edita.html"})
public class EditaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
        int id = Integer.parseInt(request.getParameter("id")); 
        
        PedidoDAO dao = new PedidoDAO();
        Pedido pedido = dao.listarPorId(id);
        request.setAttribute("pedido", pedido);
        request.getRequestDispatcher("WEB-INF/editaPedido.jsp").forward(request, response);
        
        }catch (NumberFormatException e){
        response.sendRedirect("pedidos.html");
        }catch (Exception ex){
        Logger.getLogger(EditaServlet.class.getName()).log(Level.SEVERE, null, ex);
        response.sendRedirect("pedidos.html");
        }
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         try{
        int id = Integer.parseInt(request.getParameter("id"));
        
        PedidoDAO dao = new PedidoDAO();
        Pedido pedido = dao.listarPorId(id);
        
        pedido.setPedido(Integer.parseInt(request.getParameter("pedido")));
        pedido.setDono(request.getParameter("dono"));
        pedido.setValor(Double.parseDouble(request.getParameter("valor")));
        pedido.setNome(request.getParameter("nome"));
        dao.atualiza(pedido);
        response.sendRedirect("pedidos.html");
        
        }catch (NumberFormatException e){
        response.sendRedirect("pedidos.html");
        }catch (Exception ex){
        Logger.getLogger(EditaServlet.class.getName()).log(Level.SEVERE, null, ex);
        response.sendRedirect("pedidos.html");
        }
       
    }

    
}
