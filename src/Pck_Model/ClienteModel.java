package Pck_Model;

public class ClienteModel {
    private int a01_codigo;
    private String a01_nome;
    private String a01_endereco;
    private String a01_telefone;
    private String a01_cpf;
    private double a01_credito;

    public int getA01_codigo() {
        return a01_codigo;
    }

    public void setA01_codigo(int a01_codigo) {
        this.a01_codigo = a01_codigo;
    }

    public String getA01_nome() {
        return a01_nome;
    }

    public void setA01_nome(String a01_nome) {
        this.a01_nome = a01_nome;
    }

    public String getA01_endereco() {
        return a01_endereco;
    }

    public void setA01_endereco(String a01_endereco) {
        this.a01_endereco = a01_endereco;
    }

    public String getA01_telefone() {
        return a01_telefone;
    }

    public void setA01_telefone(String a01_telefone) {
        this.a01_telefone = a01_telefone;
    }

    public String getA01_cpf() {
        return a01_cpf;
    }

    public void setA01_cpf(String a01_cpf) {
        this.a01_cpf = a01_cpf;
    }

    public double getA01_credito() {
        return a01_credito;
    }

    public void setA01_credito(double a01_credito) {
        this.a01_credito = a01_credito;
    }

    @Override
    public String toString() {
        return a01_nome;
    }
}
