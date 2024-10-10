package Pck_Persistencia;
import Pck_Model.PedidoModel;
import Pck_DAO.ConexaoDao;

import javax.swing.*;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class PedidoPersistencia {
    CallableStatement obj_Call;
    ConexaoDao obj_Conectar = new ConexaoDao();

    public void inserirPedido(PedidoModel obj_model) {
        try {
            Connection conn = obj_Conectar.getConnection();
            if (conn != null) {
                obj_Call = conn.prepareCall("CALL Proc_InsPedido(?,?,?)");
                obj_Call.setDate(1, obj_model.getA02_data());
                obj_Call.setDouble(2, obj_model.getA02_valor_total());
                obj_Call.setInt(3, obj_model.getA01_codigo());

                obj_Call.execute();
                JOptionPane.showMessageDialog(null, "Pedido cadastrado com sucesso!");
            }
        } catch (SQLException erro){
            System.out.println("Erro ao inserir Pedido" + erro);
        }
    }

    public List<PedidoModel> listarPedidos() {
        List<PedidoModel> pedidos = new ArrayList<>();
        try {
            Connection conn = obj_Conectar.getConnection();
            if (conn != null) {
                obj_Call = conn.prepareCall("CALL Proc_SelPedidos()");
                ResultSet rs = obj_Call.executeQuery();

                while (rs.next()) {
                    PedidoModel pedido = new PedidoModel();

                    pedido.setA02_codigo(rs.getInt("A02_codigo"));
                    pedido.setA02_data(rs.getDate("A02_data"));
                    pedido.setA02_valor_total(rs.getDouble("A02_valor_total"));
                    pedido.setA01_codigo(rs.getInt("A01_codigo"));

                    pedidos.add(pedido);
                }
            }
        } catch (SQLException erro){
            System.out.println("Erro ao listar pedidos" + erro);
        }

        return pedidos;
    }

    public  void alterarPedido(PedidoModel obj_model) {
        try {
            Connection conn = obj_Conectar.getConnection();
            if (conn != null) {
                obj_Call = conn.prepareCall("CALL Proc_AltPedido(?,?,?,?)");
                obj_Call.setInt(1, obj_model.getA02_codigo());
                obj_Call.setDate(2, obj_model.getA02_data());
                obj_Call.setDouble(3, obj_model.getA02_valor_total());
                obj_Call.setInt(4, obj_model.getA01_codigo());

                obj_Call.execute();
                JOptionPane.showMessageDialog(null, "Pedido alterado com sucesso!");
            }
        } catch (SQLException erro){
            System.out.println("Erro ao alterar pedido" + erro);
        }
    }

    public void excluirPedido(PedidoModel obj_model) {
        try {
            Connection conn = obj_Conectar.getConnection();
            if (conn != null) {
                obj_Call = conn.prepareCall("CALL Proc_DelPedido(?)");
                obj_Call.setInt(1, obj_model.getA02_codigo());

                obj_Call.execute();
                JOptionPane.showMessageDialog(null, "Pedido excluido com sucesso!");
            }
        } catch (SQLException erro){
            System.out.println("Erro ao excluir pedido" + erro);
        }
    }
}
