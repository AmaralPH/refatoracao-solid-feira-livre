package feira.notificacao;

public interface NotificadorPedido {
    void notificarFinalizacao(String contato, double total);
}
