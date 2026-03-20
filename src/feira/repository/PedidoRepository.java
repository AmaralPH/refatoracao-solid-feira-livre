package feira.repository;

import feira.domain.Pedido;
import java.util.List;

public interface PedidoRepository {
    void salvar(Pedido pedido);

    List<Pedido> listarTodos();
}
