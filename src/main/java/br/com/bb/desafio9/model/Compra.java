package br.com.bb.desafio9.model;

import java.util.HashMap;
import java.util.Map;

public class Compra {
    private Map<Produto, Integer> itens;
    private double valorTotal;

    public Compra(Map<Produto, Integer> itens) {
        this.itens = itens;
        
        double valorTotal = 0;
        for (Produto produto : itens.keySet()) {
            valorTotal += produto.getPreco() * itens.get(produto);
        }
        this.valorTotal = valorTotal;
    }

    public Map<Produto, Integer> getItens() {
        return itens;
    }

    public void setItens(Map<Produto, Integer> itens) {
        this.itens = itens;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Map<Produto, Integer> getProdutos() {
        
        Map<Produto, Integer> produtos = new HashMap<>();
        for (Produto produto : itens.keySet()) {
            produtos.put(produto, itens.get(produto));
        }
        return produtos;
    }

    @Override
    public String toString() {
        return "Compra [itens=" + itens + ", valorTotal=" + valorTotal + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((itens == null) ? 0 : itens.hashCode());
        long temp;
        temp = Double.doubleToLongBits(valorTotal);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Compra other = (Compra) obj;
        if (itens == null) {
            if (other.itens != null)
                return false;
        } else if (!itens.equals(other.itens))
            return false;
        if (Double.doubleToLongBits(valorTotal) != Double.doubleToLongBits(other.valorTotal))
            return false;
        return true;
    }


    
}
