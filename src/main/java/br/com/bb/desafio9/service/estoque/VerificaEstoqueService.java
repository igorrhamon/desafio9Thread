package br.com.bb.desafio9.service.estoque;

import java.util.Map;

import br.com.bb.desafio9.model.Compra;
import br.com.bb.desafio9.model.Estoque;
import br.com.bb.desafio9.model.Produto;

public class VerificaEstoqueService implements Runnable {
    private Compra compra;
    private Estoque estoque;
    public VerificaEstoqueService(Compra compra, Estoque estoque) {
        if(compra == null || estoque == null) {
            throw new IllegalArgumentException("Compra e Estoque não podem ser nulos");
        }
        this.compra = compra;
        this.estoque = estoque;
    }

    private void executa() { 
        if(this.isEstoqueDisponivel(compra.getProdutos())) {
            System.out.println("Estoque disponível");
        } else {
            System.out.println("Estoque não disponível");
        }
        
    }

    private boolean isEstoqueDisponivel(Map<Produto, Integer> produtos) {
        for (Map.Entry<Produto, Integer> produto : produtos.entrySet()) {
            if (estoque.getItens().get(produto.getKey()) < produto.getValue()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void run() {

        synchronized(this) {
            this.executa();
            notify();
        }
    }
    
}
