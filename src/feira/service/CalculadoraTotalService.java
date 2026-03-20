package feira.service;

import feira.domain.Pedido;

public class CalculadoraTotalService {
    public double calcularTotal(Pedido pedido) {
        return pedido.totalBruto();
    }
}
