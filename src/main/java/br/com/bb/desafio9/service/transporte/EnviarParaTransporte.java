package br.com.bb.desafio9.service.transporte;

import br.com.bb.desafio9.model.Compra;

public class EnviarParaTransporte  implements Runnable {
    private Compra compra;

    
    public EnviarParaTransporte(Compra compra) {
        this.compra = compra;
    }
    public Compra getCompra() {
        return compra;
    }

    @Override
    public void run() {
        synchronized(this) {
            System.out.println("Compra enviada para o transporte");
            notify();
        }
                
    }


}
