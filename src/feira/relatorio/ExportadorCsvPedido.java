package feira.relatorio;

import feira.domain.Pedido;
import feira.domain.PedidoItem;

public class ExportadorCsvPedido implements ExportadorRelatorioPedido {
    public String exportar(Pedido pedido, double totalLiquido) {
        StringBuilder csv = new StringBuilder();
        csv.append("cliente,produto,quantidade,subtotal\n");
        for (PedidoItem item : pedido.getItens()) {
            csv.append(pedido.getCliente()).append(",")
                    .append(item.getProduto().getNome()).append(",")
                    .append(item.getQuantidade()).append(",")
                    .append(item.subtotal()).append("\n");
        }
        csv.append("TOTAL,,,").append(totalLiquido);
        return csv.toString();
    }
}
