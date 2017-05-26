<%-- 
    Document   : editaPedidos
    Created on : 15/05/2017, 21:30:24
    Author     : José Flávio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar Pedido</title>
    </head>
    <body>
        <center>
        <h1>Editar Pedidos</h1>
        <div style ="color: red;">${mensagem}</div>
        <form method="post">
            <input type="hidden" name="id" value="${pedido.id}"/>
            <div><label>Pedido: <input type="text" name="pedido" value="${pedido.numpedido}"/></label></div>
            <div><label>Dono: <input type="text" name="dono" value="${pedido.dono}"/></label></div>
            <div><label>Valor: <input type="text" name="valor"value="${pedido.valor}"/></label></div>
            <div><label>Nome: <input type="text" name="nome"value="${pedido.nome}"/></label></div>
            <div><input type="submit" value="Editar" /></div>
            <div><input type="reset" value="Cancelar" /></div>
            </center>
        </form>
    </body>
</html>
