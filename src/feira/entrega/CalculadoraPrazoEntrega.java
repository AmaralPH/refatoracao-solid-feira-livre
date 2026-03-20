package feira.entrega;

public interface CalculadoraPrazoEntrega {
    String tipo();

    int calcularPrazoDias(double distanciaKm);
}
