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
    private final PreparedStatement opListarTotalPorDono;
    private final PreparedStatement opEditar;
    private final PreparedStatement opAtualiza;

    public PedidoDAO() throws Exception {
        Connection conexao = ConnectionFactory.createConnection();
        opNovo = conexao.prepareStatement("INSERT INTO pedido (numpedido, dono, valor, nome, atualizacao) VALUES(?,?,?,?,?)");
        opListar = conexao.prepareStatement("SELECT * FROM pedido");
        opListarPorPedido = conexao.prepareStatement("SELECT nome FROM pedido WHERE numpedido=?");
        opListarTotalPorPedido = conexao.prepareStatement("SELECT SUM(valor) FROM pedido WHERE numpedido=?");
        opListarTotalPorDono = conexao.prepareStatement("SELECT SUM(valor) FROM pedido WHERE dono=?");
        opEditar = conexao.prepareStatement("UPDATE pedido SET nome = ?");
        opAtualiza = conexao.prepareStatement("UPDATE pedido SET numpedido = ?, dono = ?, valor = ?, nome = ?, atualizacao = ? WHERE id = ?");
    }

    public void cria(Pedido novoPedido) throws Exception {
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
                novoPedido.setAtualizacao(resultado.getDate("atualizacao"));

                pedidos.add(novoPedido);
            }

            return pedidos;

        } catch (SQLException ex) {
            throw new Exception("Erro ao listar os pedidos.", ex);
        }
    }

    public Pedido getByPedido(int pedido) throws Exception {
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
                novoPedido.setAtualizacao(resultado.getDate("atualizacao"));
            }

            return p;
        } catch (SQLException ex) {
            throw new Exception("Erro ao buscar um pedido no listar!", ex);
        }
    }

    public Pedido getByDono(String dono) throws Exception {
        try {
            Pedido p = null;
            opListarPorPedido.clearParameters();
            opListarPorPedido.setString(2, dono);
            ResultSet resultado = opListarPorPedido.executeQuery();

            while (resultado.next()) {
                Pedido novoPedido = new Pedido();
                novoPedido.setId(resultado.getInt("id"));
                novoPedido.setPedido(resultado.getInt("numpedido"));
                novoPedido.setDono(resultado.getString("dono"));
                novoPedido.setValor(resultado.getDouble("valor"));
                novoPedido.setNome(resultado.getString("nome"));
                novoPedido.setAtualizacao(resultado.getDate("atualizacao"));
            }

            return p;
        } catch (SQLException ex) {
            throw new Exception("Erro ao buscar um pedido no listar!", ex);
        }
    }

    public void edita(String nome) throws Exception{
        try {
            
        } catch (SQLException ex) {
            throw new Exception("Erro ao editar o pedido!", ex);
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
            //opAtualiza.setDate(5, pedido.getAtualizacao());

            opAtualiza.executeUpdate();

        } catch (SQLException ex) {
            throw new Exception("Erro ao atualizar o pedido!", ex);
        }
    }

}
