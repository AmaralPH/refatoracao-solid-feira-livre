package feira.desconto;

public interface PoliticaDesconto {
    String codigo();

    double aplicar(double totalBruto);
}
