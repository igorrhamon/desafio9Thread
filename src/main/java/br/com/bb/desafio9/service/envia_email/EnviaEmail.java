package br.com.bb.desafio9.service.envia_email;

import br.com.bb.desafio9.model.Compra;
import br.com.bb.desafio9.model.Email;

public class EnviaEmail implements Runnable{
    private Email email;
    private Compra compra;

    public EnviaEmail(Email email, Compra compra) {
        this.email = email;
        this.compra = compra;
    }

    @Override
    public void run() {
        synchronized(this) {
            try {
                System.out.println("Enviando email...");
                Thread.sleep(2000);
                System.out.println("Email enviado com sucesso");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            notify();
        }
        
    }

    public Email getEmail() {
        return email;
    }

    public Compra getCompra() {
        return compra;
    }

    
}
