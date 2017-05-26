<%-- 
    Document   : listarPorPedido
    Created on : 08/05/2017, 22:03:35
    Author     : José Flávio
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listar Por Número de Pedido</title>
    </head>
    <body>
    <center>
        <h1>Listar Por Número de Pedido</h1>
        <table border="1">
            <tr>
                <th>ID</th>
                <th>Pedido</th>
                <th>Dono</th>
                <th>Valor</th>
                <th>Nome</th>
                <th>Atualizacao</th>

            </tr>
            <c:forEach var="pedido" items="${pedidos}">
                <tr>
                    <td>${pedido.id} </a></td> 
                    <td>${pedido.numpedido}</td> 
                    <td>${pedido.dono}</td> 
                    <td>${pedido.valor}</td>
                    <td>${pedido.nome}</td>
                    <td>${pedido.atualizacao}</td>
                </tr>
            </c:forEach>
            <tr>
                <td colspan="5"> Total</td>
                <td>${total}</td>
            </tr>
        </table>
    </center>
</body>
</html>

