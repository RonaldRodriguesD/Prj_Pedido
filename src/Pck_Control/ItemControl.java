package Pck_Control;

import Pck_Model.ItemModel;
import Pck_Persistencia.ItemPersistencia;
import java.util.List;


public class ItemControl {
    ItemModel obj_model = new ItemModel();
    ItemPersistencia obj_persistencia = new ItemPersistencia();

    public void inserirItem(int a03_codigo, int a02_codigo, int a04_quantidade, Double a04_valor_item){
        obj_model.setA03_codigo(a03_codigo);
        obj_model.setA02_codigo(a02_codigo);
        obj_model.setA04_quantidade(a04_quantidade);
        obj_model.setA04_valor_item(a04_valor_item);

        obj_persistencia.inserirItem(obj_model);
    }

    public List<ItemModel> listarItens(){
        return obj_persistencia.listarItens();
    }

    public void alterarItem(int a04_codigo, int a03_codigo, int a02_codigo, int a04_quantidade, Double a04_valor_item){
        obj_model.setA04_codigo(a04_codigo);
        obj_model.setA03_codigo(a03_codigo);
        obj_model.setA02_codigo(a02_codigo);
        obj_model.setA04_quantidade(a04_quantidade);
        obj_model.setA04_valor_item(a04_valor_item);
        obj_persistencia.alterarItem(obj_model);
    }

    public void excluirItem(int a04_codigo){
        obj_model.setA04_codigo(a04_codigo);
        obj_persistencia.excluirItem(obj_model);
    }
}
