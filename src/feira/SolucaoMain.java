package feira;

import feira.cupom.ImpressoraCupom;
import feira.cupom.ImpressoraTermica;
import feira.desconto.CalculadoraDesconto;
import feira.desconto.DescontoClienteFiel;
import feira.desconto.DescontoDomingo;
import feira.desconto.DescontoQueimaEstoque;
import feira.desconto.SemDesconto;
import feira.domain.Pedido;
import feira.domain.Produto;
import feira.entrega.CalculadoraPrazoEntrega;
import feira.entrega.EntregaExpressa;
import feira.entrega.EntregaNormal;
import feira.notificacao.NotificadorPedido;
import feira.notificacao.NotificadorWhatsApp;
import feira.pagamento.PagamentoBoleto;
import feira.pagamento.PagamentoCartao;
import feira.pagamento.PagamentoPix;
import feira.pagamento.ServicoPagamento;
import feira.relatorio.ExportadorCsvPedido;
import feira.relatorio.ExportadorRelatorioPedido;
import feira.repository.PedidoRepository;
import feira.repository.PedidoRepositoryMemoria;
import feira.service.FinalizadorPedidoService;
import feira.service.PedidoFinalizado;
import java.util.Arrays;

public class SolucaoMain {
    public static void main(String[] args) {
        PedidoRepository repository = new PedidoRepositoryMemoria();

        CalculadoraDesconto calculadoraDesconto = new CalculadoraDesconto(Arrays.asList(
                new SemDesconto(),
                new DescontoClienteFiel(),
                new DescontoQueimaEstoque(),
                new DescontoDomingo()));

        ServicoPagamento servicoPagamento = new ServicoPagamento(Arrays.asList(
                new PagamentoPix(),
                new PagamentoCartao(),
                new PagamentoBoleto()));

        ImpressoraCupom impressora = new ImpressoraTermica();
        NotificadorPedido notificador = new NotificadorWhatsApp();
        ExportadorRelatorioPedido exportador = new ExportadorCsvPedido();

        FinalizadorPedidoService finalizador = new FinalizadorPedidoService(
                repository,
                calculadoraDesconto,
                servicoPagamento,
                impressora,
                notificador,
                exportador);

        Pedido pedido = new Pedido("Maria da Feira");
        pedido.adicionarItem(new Produto("Tomate", 8.0), 2);
        pedido.adicionarItem(new Produto("Cenoura", 6.5), 3);

        PedidoFinalizado resultado = finalizador.finalizar(
                pedido,
                "CLIENTE_FIEL",
                "PIX",
                "85999990000");

        System.out.println("Total bruto: R$ " + resultado.getTotalBruto());
        System.out.println("Total líquido: R$ " + resultado.getTotalLiquido());
        System.out.println("CSV:\n" + resultado.getRelatorioCsv());

        CalculadoraPrazoEntrega entregaNormal = new EntregaNormal();
        CalculadoraPrazoEntrega entregaExpressa = new EntregaExpressa();
        System.out.println("Prazo normal (30km): " + entregaNormal.calcularPrazoDias(30) + " dia(s)");
        System.out.println("Prazo expresso (30km): " + entregaExpressa.calcularPrazoDias(30) + " dia(s)");
    }
}
