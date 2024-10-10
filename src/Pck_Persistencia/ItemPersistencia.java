package Pck_Persistencia;

import Pck_Model.ItemModel;
import Pck_DAO.ConexaoDao;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class ItemPersistencia {
    CallableStatement obj_Call;
    ConexaoDao obj_Conectar = new ConexaoDao();

    public void inserirItem(ItemModel obj_model){
        try {
            Connection conn = obj_Conectar.getConnection();
            if (conn != null) {
                obj_Call = conn.prepareCall("CALL Proc_InsItem(?,?,?,?)");
                obj_Call.setInt(1, obj_model.getA03_codigo());
                obj_Call.setInt(2, obj_model.getA02_codigo());
                obj_Call.setInt(3, obj_model.getA04_quantidade());
                obj_Call.setDouble(4, obj_model.getA04_valor_item());

                obj_Call.execute();
            }
        } catch (SQLException erro) {
            System.out.println("Erro ao inserir Item" + erro);
        }
    }

    public List<ItemModel> listarItens(){
        List<ItemModel> itens = new ArrayList<>();
        try {
            Connection conn = obj_Conectar.getConnection();
            if (conn != null) {
                obj_Call = conn.prepareCall("CALL Proc_SelItens()");
                ResultSet rs = obj_Call.executeQuery();

                while (rs.next()) {
                    ItemModel item = new ItemModel();

                    item.setA04_codigo(rs.getInt("A04_codigo"));
                    item.setA03_codigo(rs.getInt("A03_codigo"));
                    item.setA02_codigo(rs.getInt("A02_codigo"));
                    item.setA04_quantidade(rs.getInt("A04_quantidade"));
                    item.setA04_valor_item(rs.getDouble("A04_valor_item"));

                    itens.add(item);
                }
            }
        } catch (SQLException erro) {
            System.out.println("Erro ao listar itens" + erro);
        }

        return itens;
    }

    public void alterarItem(ItemModel obj_model){
        try {
            Connection conn = obj_Conectar.getConnection();
            if (conn != null) {
                obj_Call = conn.prepareCall("CALL Proc_AltItem(?,?,?,?,?)");
                obj_Call.setInt(1, obj_model.getA04_codigo());
                obj_Call.setInt(2, obj_model.getA03_codigo());
                obj_Call.setInt(3, obj_model.getA02_codigo());
                obj_Call.setInt(4, obj_model.getA04_quantidade());
                obj_Call.setDouble(5, obj_model.getA04_valor_item());

                obj_Call.execute();
            }
        } catch (SQLException erro) {
            System.out.println("Erro ao alterar Item" + erro);
        }
    }

    public void excluirItem(ItemModel obj_model){
        try {
            Connection conn = obj_Conectar.getConnection();
            if (conn != null) {
                obj_Call = conn.prepareCall("CALL Proc_DelItem(?)");
                obj_Call.setInt(1, obj_model.getA04_codigo());

                obj_Call.execute();
            }
        } catch (SQLException erro) {
            System.out.println("Erro ao excluir Item" + erro);
        }
    }
}
