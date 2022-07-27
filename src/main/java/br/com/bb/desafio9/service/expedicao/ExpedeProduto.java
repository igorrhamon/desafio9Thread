package br.com.bb.desafio9.service.expedicao;

import br.com.bb.desafio9.model.Compra;

public class ExpedeProduto implements Runnable {
    private Compra compra;
    
    public ExpedeProduto(Compra compra) {
        this.compra = compra;
    }
    
    @Override
    public void run() {
        synchronized(this){
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                
                e.printStackTrace();
            }
            System.out.println("Produto expedido com sucesso");
            notify();
        }
        
    }

    public Compra getCompra() {
        return compra;
    }
}
