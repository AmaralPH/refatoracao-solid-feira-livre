package feira.cupom;

import feira.domain.Pedido;

public interface ImpressoraCupom {
    void imprimir(Pedido pedido, double totalLiquido);
}
