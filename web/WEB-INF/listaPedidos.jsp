<%-- 
    Document   : listaPedidos
    Created on : 15/05/2017, 21:43:05
    Author     : José Flávio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listagem de Pedidos</title>
    </head>
    <body>
    <center>
        <h1>Listagem de Pedidos</h1>
        <br>
        <table border="1" width="10" cellspacing="1" cellpadding="1">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Pedido</th>
                    <th>Dono</th>
                    <th>Valor</th>
                    <th>Nome</th>
                    <th>Atualização</th>
                </tr>
            </thead>
            <tbody>
                <tr>
            <c:forEach var="pedido" items="${pedidos}">
                <tr>
                    <td><a href="edita.html?id=${pedido.id}">${pedido.id} </a></td> 
                    <td>
                        <a href="listarPorPedido.html?pedido=${pedido.numpedido}">${pedido.numpedido}</a></td> 
                    <td>
                        <a href="listarPorDono.html?dono=${pedido.dono}">${pedido.dono}</a></td> 
                    <td>${pedido.valor}</td>
                    <td>${pedido.nome}</td>
                    <td>${pedido.atualizacao}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </center>
</body>
</html>
