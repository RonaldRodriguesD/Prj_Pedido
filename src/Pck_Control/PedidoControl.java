package Pck_Control;

import Pck_Model.PedidoModel;
import Pck_Persistencia.PedidoPersistencia;
import java.sql.Date;
import java.util.List;

public class PedidoControl {
    PedidoModel obj_model = new PedidoModel();
    PedidoPersistencia obj_persistencia = new PedidoPersistencia();

    public void inserirPedido(Date a02_data, double a02_valor_total, int a01_codigo){

        obj_model.setA02_data(a02_data);
        obj_model.setA02_valor_total(a02_valor_total);
        obj_model.setA01_codigo(a01_codigo);

        obj_persistencia.inserirPedido(obj_model);
    }

    public List<PedidoModel> listarPedidos(){
        return obj_persistencia.listarPedidos();
    }

    public void alterarPedido(int a02_codigo, Date a02_data, double a02_valor_total, int a01_codigo){
        obj_model.setA02_codigo(a02_codigo);
        obj_model.setA02_data(a02_data);
        obj_model.setA02_valor_total(a02_valor_total);
        obj_model.setA01_codigo(a01_codigo);
        obj_persistencia.alterarPedido(obj_model);
    }

    public void excluirPedido(int a02_codigo){
        obj_model.setA02_codigo(a02_codigo);
        obj_persistencia.excluirPedido(obj_model);
    }
}
