package br.com.bb.desafio9.model;

import java.util.HashMap;
import java.util.Map;

public class Transportadora {

    private Map<String, Integer> produtos = new HashMap<>();

    public Map<String, Integer> getProdutos() {
        return produtos;
    }

    public Transportadora(Map<String, Integer> produtos) {
        this.produtos = produtos;
    }

    
}
