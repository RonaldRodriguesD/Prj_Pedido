package Pck_Persistencia;
import Pck_Model.ClienteModel;
import Pck_DAO.ConexaoDao;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientePersistencia {

    CallableStatement obj_Call;
    ConexaoDao obj_Conectar = new ConexaoDao();

    public void inserirCliente(ClienteModel obj_model) {
        try{
            Connection conn = obj_Conectar.getConnection();
            if(conn != null){
                obj_Call = conn.prepareCall("CALL Proc_InsCliente(?,?,?,?,?)");
                obj_Call.setString(1, obj_model.getA01_nome());
                obj_Call.setString(2, obj_model.getA01_endereco());
                obj_Call.setString(3, obj_model.getA01_telefone());
                obj_Call.setString(4, obj_model.getA01_cpf());
                obj_Call.setDouble(5, obj_model.getA01_credito());

                obj_Call.execute();
            }
        } catch (SQLException erro){
            System.out.println("Erro ao inserir cliente" + erro);
        }
    }

    public List<ClienteModel> listarClientes() {
        List<ClienteModel> clientes = new ArrayList<>();
        try {
            Connection conn = obj_Conectar.getConnection();
            if (conn != null) {
                obj_Call = conn.prepareCall("CALL Proc_SelClientes()");
                ResultSet rs = obj_Call.executeQuery();

                while (rs.next()) {

                    ClienteModel cliente = new ClienteModel();
                    cliente.setA01_codigo(rs.getInt("A01_codigo"));
                    cliente.setA01_nome(rs.getString("A01_nome"));
                    cliente.setA01_endereco(rs.getString("A01_endereco"));
                    cliente.setA01_telefone(rs.getString("A01_telefone"));
                    cliente.setA01_cpf(rs.getString("A01_cpf"));
                    cliente.setA01_credito(rs.getDouble("A01_credito"));

                    clientes.add(cliente);
                }
            }
        } catch (SQLException erro) {
            System.out.println("Erro ao listar cliente" + erro);
        }
        return clientes;
    }

    public void alterarCliente(ClienteModel obj_model) {
        try {
            Connection conn = obj_Conectar.getConnection();
            if(conn != null){
                obj_Call = conn.prepareCall("CALL Proc_AltCliente(?,?,?,?,?,?)");
                obj_Call.setInt(1, obj_model.getA01_codigo());
                obj_Call.setString(2, obj_model.getA01_nome());
                obj_Call.setString(3, obj_model.getA01_endereco());
                obj_Call.setString(4, obj_model.getA01_telefone());
                obj_Call.setString(5, obj_model.getA01_cpf());
                obj_Call.setDouble(6, obj_model.getA01_credito());

                obj_Call.execute();
                JOptionPane.showMessageDialog(null, "Cliente alterado com sucesso!");
            }
        } catch (SQLException erro) {
            System.out.println("Erro ao alterar cliente" + erro);
        }
    }

    public void excluirCliente(ClienteModel obj_model) {
        try {
            Connection conn = obj_Conectar.getConnection();
            if (conn != null) {
                obj_Call = conn.prepareCall("CALL Proc_DelCliente(?)");
                obj_Call.setInt(1, obj_model.getA01_codigo());

                obj_Call.execute();
                JOptionPane.showMessageDialog(null, "Cliente excluido com sucesso!");
            }
        } catch (SQLIntegrityConstraintViolationException erro){
            JOptionPane.showMessageDialog(null,"Não é possível excluir um cliente com pedido ativo!");
        }
        catch (SQLException erro) {
            System.out.println("Erro ao excluir cliente" + erro);
        }
    }
}