package Pck_Control;

import Pck_Model.ProdutoModel;
import Pck_Persistencia.ProdutoPersistencia;
import java.util.List;


public class ProdutoControl {
    ProdutoModel obj_Model = new ProdutoModel();
    ProdutoPersistencia obj_Persistencia = new ProdutoPersistencia();

    public void inserirProduto(String a03_descricao, Double a03_valor_unitario, int a03_estoque){
        obj_Model.setA03_descricao(a03_descricao);
        obj_Model.setA03_valor_unitario(a03_valor_unitario);
        obj_Model.setA03_estoque(a03_estoque);

        obj_Persistencia.inserirProdutos(obj_Model);
    }

    public List<ProdutoModel> listarProdutos(){
        return obj_Persistencia.listarProdutos();
    }

    public void alterarProduto(int a03_codigo,String a03_descricao, Double a03_valor_unitario, int a03_estoque){
        obj_Model.setA03_codigo(a03_codigo);
        obj_Model.setA03_descricao(a03_descricao);
        obj_Model.setA03_valor_unitario(a03_valor_unitario);
        obj_Model.setA03_estoque(a03_estoque);
        obj_Persistencia.alterarProdutos(obj_Model);
    }

    public void excluirProduto(int a03_codigo){
        obj_Model.setA03_codigo(a03_codigo);
        obj_Persistencia.excluirProdutos(obj_Model);
    }
}
