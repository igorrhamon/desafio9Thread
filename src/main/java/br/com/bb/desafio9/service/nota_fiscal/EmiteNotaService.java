package br.com.bb.desafio9.service.nota_fiscal;

import br.com.bb.desafio9.model.Compra;
import br.com.bb.desafio9.model.NotaFiscal;

public class EmiteNotaService implements Runnable {

    
    private Compra compra;

    public EmiteNotaService(  Compra compra) {
        
        if(compra == null) {
            throw new IllegalArgumentException("NotaFiscal e Compra não podem ser nulos");
        }
        this.compra = compra;
    }

    @Override
    public void run() {
        synchronized(this){
            NotaFiscal notaFiscal = new NotaFiscal(compra);
            System.out.println("Nota Fiscal emitida com sucesso. Total: " + notaFiscal.getTotal());
            
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                
                e.printStackTrace();
            }
            notify();
        }
        
    }


    
}
