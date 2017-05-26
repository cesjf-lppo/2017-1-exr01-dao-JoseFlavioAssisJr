package br.cesjf.lppo.dao;

import br.cesjf.lppo.Pedido;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author José Flávio
 */
public class PedidoDAO {

    private final PreparedStatement opNovo;
    private final PreparedStatement opListar;
    private final PreparedStatement opListarPorPedido;
    private final PreparedStatement opListarTotalPorPedido;
    private final PreparedStatement opListarPorDono;
    private final PreparedStatement opListarTotalPorDono;
    private final PreparedStatement opBuscarPorId;
    private final PreparedStatement opAtualiza;

    public PedidoDAO() throws Exception {
        Connection conexao = ConnectionFactory.createConnection();
        opNovo = conexao.prepareStatement("INSERT INTO pedido (numpedido, dono, valor, nome, atualizacao) VALUES(?,?,?,?,?)");
        opListar = conexao.prepareStatement("SELECT * FROM pedido");
        opListarPorPedido = conexao.prepareStatement("SELECT * FROM pedido WHERE numpedido=?");
        opListarTotalPorPedido = conexao.prepareStatement("SELECT SUM(valor) as Total FROM pedido WHERE numpedido=?");
        opListarPorDono = conexao.prepareStatement("SELECT * FROM pedido WHERE dono=?");
        opListarTotalPorDono = conexao.prepareStatement("SELECT SUM(valor) as Total FROM pedido WHERE dono=?");
        opBuscarPorId = conexao.prepareStatement("SELECT * FROM pedido WHERE id=?");
        opAtualiza = conexao.prepareStatement("UPDATE Pedido SET pedido = ?, dono = ?, valor = ?, nome = ?, atualizacao = CURRENT_TIMESTAMP WHERE id = ?");
    }

    public void novoPedido(Pedido novoPedido) throws Exception {
        try {
            opNovo.setInt(1, novoPedido.getPedido());
            opNovo.setString(2, novoPedido.getDono());
            opNovo.setDouble(3, novoPedido.getValor());
            opNovo.setString(4, novoPedido.getNome());
            opNovo.executeUpdate();

        } catch (SQLException ex) {
            throw new Exception("Erro ao inserir o pedido!", ex);
        }
    }

    public List<Pedido> listAll() throws Exception {
        try {
            List<Pedido> pedidos = new ArrayList<>();
            ResultSet resultado = opListar.executeQuery();

            while (resultado.next()) {
                Pedido novoPedido = new Pedido();
                novoPedido.setId(resultado.getInt("id"));
                novoPedido.setPedido(resultado.getInt("numpedido"));
                novoPedido.setDono(resultado.getString("dono"));
                novoPedido.setValor(resultado.getDouble("valor"));
                novoPedido.setNome(resultado.getString("nome"));
                novoPedido.setAtualizacao(resultado.getTimestamp("atualizacao"));
                pedidos.add(novoPedido);
            }
            return pedidos;

        } catch (SQLException ex) {
            throw new Exception("Erro ao listar os pedidos.", ex);
        }
    }

    public Pedido ListarPorPedido(int pedido) throws Exception {
        try {
            Pedido p = null;
            opListarPorPedido.clearParameters();
            opListarPorPedido.setInt(1, pedido);
            ResultSet resultado = opListarPorPedido.executeQuery();

            while (resultado.next()) {
                Pedido novoPedido = new Pedido();
                novoPedido.setId(resultado.getInt("id"));
                novoPedido.setPedido(resultado.getInt("numpedido"));
                novoPedido.setDono(resultado.getString("dono"));
                novoPedido.setValor(resultado.getDouble("valor"));
                novoPedido.setNome(resultado.getString("nome"));
                novoPedido.setAtualizacao(resultado.getTimestamp("atualizacao"));
            }
            return p;

        } catch (SQLException ex) {
            throw new Exception("Erro ao buscar um pedido no listar!", ex);
        }
    }

    public float listarTotalPorPedido(int pedido) throws Exception {
        try {
            float valor = 0;
            opListarTotalPorPedido.setLong(1, pedido);
            ResultSet resultado = opListarTotalPorPedido.executeQuery();
            while (resultado.next()) {
                valor = resultado.getFloat(1);
            }
            return valor;
        } catch (SQLException ex) {
            throw new Exception("Erro ao buscar pedidos no banco", ex);
        }
    }

    public Pedido listarPorDono(String dono) throws Exception {
        try {
            Pedido p = null;
            opListarPorDono.clearParameters();
            opListarPorDono.setString(2, dono);
            ResultSet resultado = opListarPorDono.executeQuery();

            while (resultado.next()) {
                Pedido novoPedido = new Pedido();
                novoPedido.setId(resultado.getInt("id"));
                novoPedido.setPedido(resultado.getInt("numpedido"));
                novoPedido.setDono(resultado.getString("dono"));
                novoPedido.setValor(resultado.getDouble("valor"));
                novoPedido.setNome(resultado.getString("nome"));
                novoPedido.setAtualizacao(resultado.getTimestamp("atualizacao"));
            }
            return p;
        } catch (SQLException ex) {
            throw new Exception("Erro ao buscar um pedido no listar!", ex);
        }
    }

    public float listarTotalPorDono(String dono) throws Exception {
        try {
            float valor = 0;
            opListarTotalPorDono.setString(1, dono);
            ResultSet resultado = opListarTotalPorDono.executeQuery();
            while (resultado.next()) {
                valor = resultado.getFloat(1);
            }
            return valor;
        } catch (SQLException ex) {
            throw new Exception("Erro ao buscar pedidos no banco", ex);
        }
    }

    public Pedido listarPorId(int id) throws Exception {
        try {
            Pedido pedido = null;
            opBuscarPorId.clearParameters();
            opBuscarPorId.setLong(1, id);
            ResultSet resultado = opBuscarPorId.executeQuery();
            if (resultado.next()) {
                pedido = new Pedido();
                pedido.setId(resultado.getInt("id"));
                pedido.setPedido(resultado.getInt("pedido"));
                pedido.setDono(resultado.getString("dono"));
                pedido.setValor(resultado.getDouble("valor"));
                pedido.setNome(resultado.getString("nome"));
                pedido.setAtualizacao(resultado.getTimestamp("atualizacao"));
            }
            return pedido;
        } catch (SQLException ex) {
            throw new Exception("Erro ao buscar pedidos no banco", ex);
        }
    }

    public void atualiza(Pedido pedido) throws Exception {
        try {
            opAtualiza.clearParameters();
            opAtualiza.setInt(1, pedido.getPedido());
            opAtualiza.setString(2, pedido.getDono());
            opAtualiza.setDouble(3, pedido.getValor());
            opAtualiza.setString(4, pedido.getNome());
            opAtualiza.setInt(5, pedido.getId());
            opAtualiza.executeUpdate();

        } catch (SQLException ex) {
            throw new Exception("Erro ao atualizar o pedido!", ex);
        }
    }
}
