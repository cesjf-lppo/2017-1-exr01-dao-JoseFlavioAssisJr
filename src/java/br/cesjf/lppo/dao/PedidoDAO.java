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
    
    private final PreparedStatement opListar;
    private final PreparedStatement opNovo;
    private final PreparedStatement opAtualiza;
    private final PreparedStatement opBuscaPorId;
    
    public PedidoDAO() throws Exception {
        Connection conexao = ConnectionFactory.createConnection();
        opListar = conexao.prepareStatement("SELECT * FROM pedido");
        opBuscaPorId = conexao.prepareStatement("SELECT * FROM pedido WHERE id=?");
        opNovo = conexao.prepareStatement("INSERT INTO pedido (pedido, dono, valor, nome, atualizacao) VALUES(?,?,?,?,?)");
        opAtualiza = conexao.prepareStatement("UPDATE pedido SET pedido = ?, dono = ?, valor = ?, nome = ?, atualizacao = ? WHERE id = ?");
    }
    
    public List<Pedido> listAll() throws Exception {
        try {
            List<Pedido> pedidos = new ArrayList<>();
            ResultSet resultado = opListar.executeQuery();

            while (resultado.next()) {
                Pedido novoPedido = new Pedido();
                novoPedido.setId(resultado.getInt("id"));
                novoPedido.setPedido(resultado.getInt("pedido"));
                novoPedido.setDono(resultado.getString("dono"));
                novoPedido.setValor(resultado.getDouble("valor"));
                novoPedido.setNome(resultado.getString("nome"));
                novoPedido.setAtualizacao(resultado.getDate("atualizacao"));
                
                pedidos.add(novoPedido);
            }

            return pedidos;
            
        } catch (SQLException ex) {
            throw new Exception("Erro ao listar os contatos.", ex);
        }
    }
    
    public Pedido getById(Long id) throws Exception {
        try {
            Pedido pedido = null;
            opBuscaPorId.clearParameters();
            opBuscaPorId.setLong(1, id);
            ResultSet resultado = opBuscaPorId.executeQuery();

            while (resultado.next()) {
                Pedido novoPedido = new Pedido();
                novoPedido.setId(resultado.getInt("id"));
                novoPedido.setPedido(resultado.getInt("pedido"));
                novoPedido.setDono(resultado.getString("dono"));
                novoPedido.setValor(resultado.getDouble("valor"));
                novoPedido.setNome(resultado.getString("nome"));
                novoPedido.setAtualizacao(resultado.getDate("atualizacao"));
            }
            
            return pedido;
        } catch (SQLException ex) {
            throw new Exception("Erro ao buscar um pedido no listar!", ex);
        }
    }
    
    public void cria(Pedido novoPedido) throws Exception {
        try {
            opNovo.setInt(1, novoPedido.getPedido());
            opNovo.setString(2, novoPedido.getDono());
            opNovo.setDouble(3, novoPedido.getValor());
            opNovo.setString(4, novoPedido.getNome());
           
            opNovo.executeUpdate();

        } catch (SQLException ex) {
            throw new Exception("Erro ao inserir o contato!", ex);
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
            throw new Exception("Erro ao atualizar o contato!", ex);
        }
}
    
}