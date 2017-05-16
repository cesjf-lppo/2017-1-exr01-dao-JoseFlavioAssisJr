package br.cesjf.lppo;

import java.util.Date;

/**
 *
 * @author José Flávio
 */
public class Pedido {
    
    int id;
    int pedido;
    String dono;
    double valor;
    String nome;
    Date atualizacao;

    public Pedido() {
    }

    public Pedido(int id, int pedido, String dono, double valor, String nome, Date atualizacao) {
        this.id = id;
        this.pedido = pedido;
        this.dono = dono;
        this.valor = valor;
        this.nome = nome;
        this.atualizacao = atualizacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPedido() {
        return pedido;
    }

    public void setPedido(int pedido) {
        this.pedido = pedido;
    }

    public String getDono() {
        return dono;
    }

    public void setDono(String dono) {
        this.dono = dono;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getAtualizacao() {
        return atualizacao;
    }

    public void setAtualizacao(Date atualizacao) {
        this.atualizacao = atualizacao;
    }
    
    
}
