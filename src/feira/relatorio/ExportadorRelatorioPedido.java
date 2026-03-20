package feira.relatorio;

import feira.domain.Pedido;

public interface ExportadorRelatorioPedido {
    String exportar(Pedido pedido, double totalLiquido);
}
