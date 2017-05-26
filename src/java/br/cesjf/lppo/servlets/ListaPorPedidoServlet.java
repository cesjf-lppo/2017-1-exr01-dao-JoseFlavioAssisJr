package br.cesjf.lppo.servlets;

import br.cesjf.lppo.Pedido;
import br.cesjf.lppo.dao.PedidoDAO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
@WebServlet(name = "ListarPorPedidoServlet", urlPatterns = {"/listarPorPedido.html"})
public class ListaPorPedidoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Pedido> pedidos;
        int filtro = Integer.parseInt(request.getParameter("pedido"));
        float total = 0;
        try {
            PedidoDAO dao = new PedidoDAO();
            pedidos = (List<Pedido>) dao.ListarPorPedido(filtro);
            total = dao.listarTotalPorPedido(filtro);
        } catch (Exception ex) {
            Logger.getLogger(ListaPedidosServlet.class.getName()).log(Level.SEVERE, null, ex);
            pedidos = new ArrayList<>();
            request.setAttribute("mensagem", ex.getLocalizedMessage());
        }

        request.setAttribute("pedidos", pedidos);
        request.setAttribute("total", total);
        request.getRequestDispatcher("WEB-INF/listarPorPedido.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
