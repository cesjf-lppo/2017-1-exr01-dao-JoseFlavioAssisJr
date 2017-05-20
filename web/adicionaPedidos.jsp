<%-- 
    Document   : adicionaPedidos
    Created on : 15/05/2017, 21:15:54
    Author     : José Flávio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Adicionar Pedidos</title>
    </head>
    <body>
    <center>
        <form action="processaPedido" method="get">
        <h1>Adicionar Pedidos</h1>
        <br>
        <b>Pedido:<input type="text" name="pedido" value="" /><br>
        Dono:<input type="text" name="dono" value="" /><br>
        Valor: </b><input type="text" name="valor" value="" /><br>
        <br>
        <input type="submit" value="Cadastrar" name="cadastrar" />
        <input type="reset" value="Cancelar" name="cancelar" />
    </center>
        </form>
    </body>
</html>
