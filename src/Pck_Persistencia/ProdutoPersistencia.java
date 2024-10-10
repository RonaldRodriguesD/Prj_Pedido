package Pck_Persistencia;

import Pck_DAO.ConexaoDao;
import Pck_Model.ProdutoModel;

import javax.swing.*;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class ProdutoPersistencia {
    CallableStatement obj_Call;
    ConexaoDao obj_Conexao = new ConexaoDao();

    public void inserirProdutos(ProdutoModel obj_model){
        try{
            Connection conn = obj_Conexao.getConnection();
            if(conn != null){
                obj_Call = conn.prepareCall("CALL Proc_InsProduto(?,?,?)");
                obj_Call.setString(1, obj_model.getA03_descricao());
                obj_Call.setDouble(2, obj_model.getA03_valor_unitario());
                obj_Call.setInt(3, obj_model.getA03_estoque());

                obj_Call.execute();
                JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
            }
        } catch(SQLException erro){
            System.out.println("Erro ao inserir produto" + erro);
        }
    }

    public List<ProdutoModel> listarProdutos(){
        List<ProdutoModel> produtos = new ArrayList<>();

        try{
            Connection conn = obj_Conexao.getConnection();
            if(conn != null){
                obj_Call = conn.prepareCall("CALL Proc_SelProdutos()");
                ResultSet rs = obj_Call.executeQuery();

                while(rs.next()){

                    ProdutoModel produto = new ProdutoModel();
                    produto.setA03_codigo(rs.getInt("a03_codigo"));
                    produto.setA03_descricao(rs.getString("a03_descricao"));
                    produto.setA03_valor_unitario(rs.getDouble("A03_valor_unitario"));
                    produto.setA03_estoque(rs.getInt("A03_estoque"));

                    produtos.add(produto);
                }
            }
        } catch(SQLException erro){
            System.out.println("Erro ao listar produtos" + erro);
        }
        return produtos;
    }

    public void alterarProdutos(ProdutoModel obj_model){
        try {
            Connection conn = obj_Conexao.getConnection();
            if(conn != null){
                obj_Call = conn.prepareCall("CALL Proc_AltProduto(?,?,?,?)");
                obj_Call.setInt(1, obj_model.getA03_codigo());
                obj_Call.setString(2, obj_model.getA03_descricao());
                obj_Call.setDouble(3, obj_model.getA03_valor_unitario());
                obj_Call.setInt(4, obj_model.getA03_estoque());

                obj_Call.execute();
                JOptionPane.showMessageDialog(null, "Produto alterado com sucesso!");
            }
        } catch(SQLException erro){
            System.out.println("Erro ao alterar produto" + erro);
        }
    }

    public void excluirProdutos(ProdutoModel obj_model){
        try {
            Connection conn = obj_Conexao.getConnection();
            if(conn != null){
                obj_Call = conn.prepareCall("CALL Proc_DelProduto(?)");
                obj_Call.setInt(1, obj_model.getA03_codigo());

                obj_Call.execute();
                JOptionPane.showMessageDialog(null, "Produto excluido com sucesso!");
            }
        } catch (SQLException erro) {
            System.out.println("Erro ao excluir produto" + erro);
        }
    }
}
