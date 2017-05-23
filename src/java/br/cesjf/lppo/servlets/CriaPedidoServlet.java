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
 * @author aluno
 */
@WebServlet(name = "CriaPedidoServlet", urlPatterns = {"/novo.html"})
public class CriaPedidoServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	request.getRequestDispatcher("WEB-INF/adicionaPedidos.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	Pedido novoPedido = new Pedido();
	novoPedido.setPedido(Integer.parseInt(request.getParameter("pedido").toString()));
	novoPedido.setDono(request.getParameter("dono"));
	novoPedido.setValor(Double.parseDouble(request.getParameter("valor").toString()));
        novoPedido.setNome(request.getParameter("nome"));
	
	try {
	PedidoDAO dao = new PedidoDAO();
	dao.cria(novoPedido);
	} catch (Exception ex) {
	 request.setAttribute("mensagem", ex);
	 request.getRequestDispatcher("WEB-INF/adicionaPedidos.jsp").forward(request, response);
	    return;
	}
		
	response.sendRedirect("pedidos.html");
	
    }

}
