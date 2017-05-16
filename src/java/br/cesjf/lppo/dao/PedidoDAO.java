package br.cesjf.lppo.dao;

import br.cesjf.lppo.Pedido;
import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author José Flávio
 */
public class PedidoDAO {
    public List<Pedido> listAll() throws Exception{
	try {
	    List<Pedido> pedidos = new ArrayList<>();
	    Class.forName("org.apache.derby.jdbc.ClientDriver");
	    Connection conexao = DriverManager.getConnection("jdbc:derby://localhost:1527/lppo-2017-1", "usuario", "senha");
	    Statement operacao = conexao.createStatement();
	    ResultSet resultado = operacao.executeQuery("SELECT * FROM pedido");
	    while(resultado.next()){
		Pedido novoPedido = new Pedido();
		novoPedido.setId((int) resultado.getLong("id"));
                novoPedido.setPedido((int) resultado.getLong("pedido"));
                novoPedido.setDono(resultado.getString("dono"));
                novoPedido.setValor(resultado.getDouble("valor"));
		novoPedido.setNome(resultado.getString("nome"));
                novoPedido.setAtualizacao(resultado.getDate("atualizacao"));
		
		PedidoDAO dao = new PedidoDAO();
                dao.cria(novoPedido);
                }
	    return pedidos;
	    
	} catch (ClassNotFoundException ex) {
	    throw new Exception("Driver não encontrado", ex);
	}
	catch(SQLException ex){
	    throw new Exception("Erro ao listar pedidos no banco", ex);
	    }
    }

    public void cria(Pedido novoPedido) throws Exception {
        try {
            Class.forName("org.apache.derby.jdbc.ClienteDriver");
            Connection conexao = DriverManager.getConnection("jdbc:derby://localhost:1527/lppo-2017-1","usuario","senha");
            Statement operacao = conexao.createStatement();
            operacao.executeUpdate("INSERT INTO pedido (pedido,dono,valor, nome) VALUES ('"
                    +novoPedido.getPedido()+ "','"
                    +novoPedido.getDono() + "','"
                    +novoPedido.getValor()+ "','"
                    +novoPedido.getNome()+ "')");
        } catch (ClassNotFoundException ex) {
            throw new Exception("Erro ao carregar o driver!",ex);
        }
        catch (SQLException ex){
        throw new Exception("Erro ao inserir o pedido",ex);
        }
        
        public int getById(int id)throws Exception {
            try {
                Pedido pedido = null;
                /*opBuscaPorId.clearParameter();
                opBuscaPorId.setLong(i,id);
                opBuscaPorId.setLong(1,id);*/
                ResultSet resultado = opBuscarPorId.executeQuery();
                
                if(resultado.next()){
                pedido = new Pedido();
                pedido.setId(resultado.getInt("id"));
                pedido.setPedido(resultado.getInt("pedido"));
                pedido.setDono(resultado.getString("dono"));
                pedido.setValor(resultado.getDouble("valor"));
                pedido.setNome(resultado.getString("nome"));
                
                }
                
            } catch (Exception e) {
            }
        
        return id;}

    private static class opBuscarPorId {

        public opBuscarPorId() {
        }
    }
        
    }
    
}
