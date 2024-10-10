package Pck_Model;

public class ProdutoModel {
    private int a03_codigo;
    private String a03_descricao;
    private double a03_valor_unitario;
    private int a03_estoque;

    public int getA03_codigo() {
        return a03_codigo;
    }

    public void setA03_codigo(int a03_codigo) {
        this.a03_codigo = a03_codigo;
    }

    public String getA03_descricao() {
        return a03_descricao;
    }

    public void setA03_descricao(String a03_descricao) {
        this.a03_descricao = a03_descricao;
    }

    public double getA03_valor_unitario() {
        return a03_valor_unitario;
    }

    public void setA03_valor_unitario(double a03_valor_unitario) {
        this.a03_valor_unitario = a03_valor_unitario;
    }

    public int getA03_estoque() {
        return a03_estoque;
    }

    public void setA03_estoque(int a03_estoque) {
        this.a03_estoque = a03_estoque;
    }

    @Override
    public String toString() {
        return a03_descricao;
    }
}
