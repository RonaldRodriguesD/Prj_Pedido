package Pck_Control;

import Pck_Model.ClienteModel;
import Pck_Persistencia.ClientePersistencia;
import java.util.List;

public class ClienteControl {
    ClienteModel obj_model = new ClienteModel();

    ClientePersistencia obj_persistencia = new ClientePersistencia();

    public void inserirCliente(String a01_nome, String a01_endereco, String a01_telefone, String a01_cpf, double a01_credito){
        obj_model.setA01_nome(a01_nome);
        obj_model.setA01_endereco(a01_endereco);
        obj_model.setA01_telefone(a01_telefone);
        obj_model.setA01_cpf(a01_cpf);
        obj_model.setA01_credito(a01_credito);

        obj_persistencia.inserirCliente(obj_model);
    }

    public List<ClienteModel> listarClientes(){
        return obj_persistencia.listarClientes();
    }

    public void alterarCliente(int a01_codigo, String a01_nome, String a01_endereco, String a01_telefone, String a01_cpf, double a01_credito) {
        obj_model.setA01_codigo(a01_codigo);
        obj_model.setA01_nome(a01_nome);
        obj_model.setA01_endereco(a01_endereco);
        obj_model.setA01_telefone(a01_telefone);
        obj_model.setA01_cpf(a01_cpf);
        obj_model.setA01_credito(a01_credito);
        obj_persistencia.alterarCliente(obj_model);
    }

    public void excluirCliente(int a01_codigo){
        obj_model.setA01_codigo(a01_codigo);
        obj_persistencia.excluirCliente(obj_model);
    }
}
