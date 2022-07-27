package br.com.bb.desafio9;



import java.util.HashMap;
import java.util.Map;

import br.com.bb.desafio9.model.Compra;
import br.com.bb.desafio9.model.Email;
import br.com.bb.desafio9.model.Estoque;
import br.com.bb.desafio9.model.Pagamento;
import br.com.bb.desafio9.model.Produto;
import br.com.bb.desafio9.service.envia_email.EnviaEmail;
import br.com.bb.desafio9.service.estoque.BloqueiaEstoqueService;
import br.com.bb.desafio9.service.estoque.VerificaEstoqueService;
import br.com.bb.desafio9.service.expedicao.ExpedeProduto;
import br.com.bb.desafio9.service.nota_fiscal.EmiteNotaService;
import br.com.bb.desafio9.service.pagamento.EfetuaPagamentoService;
import br.com.bb.desafio9.service.transporte.EnviarParaTransporte;

public class App {
    public static void main(String[] args) throws Exception {
        Produto produto1 = new Produto("Produto 1", 10.0);
        Produto produto2 = new Produto("Produto 2", 20.0);
        Estoque estoque = new Estoque();
        Map<Produto, Integer> produtos = new HashMap<>();
        produtos.put(produto1, 1);
        produtos.put(produto2, 2);
        Compra compra = new Compra(produtos);
        Pagamento pagamento = new Pagamento(compra);

        final Thread verificaEstoqueServiceThread = new Thread( new VerificaEstoqueService(compra, estoque));
        final Thread pagamentoServiceThread = new Thread( new EfetuaPagamentoService(pagamento));
        final Thread emiteNotaServiceThread = new Thread( new EmiteNotaService(compra));
        final Thread bloqueiaEstoqueServiceThread = new Thread( new BloqueiaEstoqueService(compra, estoque));
        final Thread enviaEmailServiceThread = new Thread( new EnviaEmail(new Email("Corpo do E-mail"), compra));
        final Thread expedeProdutoServiceThread = new Thread( new ExpedeProduto(compra));
        final Thread enviaParaTransportadoraServiceThread = new Thread( new EnviarParaTransporte(compra));

        pagamentoServiceThread.start();
        synchronized(pagamentoServiceThread) {
            pagamentoServiceThread.wait();
            verificaEstoqueServiceThread.start();
            synchronized(verificaEstoqueServiceThread) {
                verificaEstoqueServiceThread.wait();
                bloqueiaEstoqueServiceThread.start();
                synchronized(bloqueiaEstoqueServiceThread){
                    bloqueiaEstoqueServiceThread.wait();
                    expedeProdutoServiceThread.start();
                    synchronized(expedeProdutoServiceThread){
                        expedeProdutoServiceThread.wait();
                        enviaParaTransportadoraServiceThread.start();
                    }
                }
                emiteNotaServiceThread.start();
                synchronized(emiteNotaServiceThread){
                    emiteNotaServiceThread.wait();
                    enviaEmailServiceThread.start();
                }
                
            }
        }
        
        
    }
}
