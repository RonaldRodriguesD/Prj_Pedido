package Pck_View;

import Pck_Control.ProdutoControl;
import Pck_Model.ProdutoModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ProdutoView extends JFrame {
    private JPanel panel1;
    private JTextField txtDesc;
    private JTextField txtValorUnitario;
    private JTextField txtEstoque;
    private JButton btnCadProd;
    private JButton btnExitCadProd;
    private JTable tableProduto;
    private JButton btnAltProd;
    private JButton btnDelProd;
    private DefaultTableModel tableModel;

    public ProdutoView() {
        this.setTitle("Cadastro Produto");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panel1);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setSize(700, 450);

        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Descição");
        tableModel.addColumn("Valor");
        tableModel.addColumn("Estoque");

        tableProduto = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tableProduto);
        scrollPane.setPreferredSize(new Dimension(450, 200));

        panel1.setLayout(new BorderLayout());
        panel1.add(scrollPane, BorderLayout.LINE_END);

        preencherTabela();

        btnCadProd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String desc = txtDesc.getText();
                double valorUnitario = Double.parseDouble(txtValorUnitario.getText());
                int estoque = Integer.parseInt(txtEstoque.getText());

                ProdutoControl produtoControl = new ProdutoControl();
                produtoControl.inserirProduto(desc, valorUnitario, estoque);

                txtDesc.setText("");
                txtValorUnitario.setText("");
                txtEstoque.setText("");
                atualizarTabela();
            }
        });

        tableProduto.getSelectionModel().addListSelectionListener(event -> {
            int selectedRow = tableProduto.getSelectedRow();
            if (selectedRow >= 0) {
                txtDesc.setText(tableModel.getValueAt(selectedRow, 1).toString());
                txtValorUnitario.setText(tableModel.getValueAt(selectedRow, 2).toString());
                txtEstoque.setText(tableModel.getValueAt(selectedRow, 3).toString());
            }
        });

        btnExitCadProd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuView menuView = new MenuView();
                menuView.setVisible(true);
                dispose();
            }
        });

        btnAltProd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tableProduto.getSelectedRow();
                if (selectedRow >= 0) {
                    int codigo = Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString());
                    String desc = txtDesc.getText();
                    double valorUnitario = Double.parseDouble(txtValorUnitario.getText());
                    int estoque = Integer.parseInt(txtEstoque.getText());

                    ProdutoControl produtoControl = new ProdutoControl();
                    produtoControl.alterarProduto(codigo, desc, valorUnitario, estoque);
                    atualizarTabela();
                    txtDesc.setText("");
                    txtValorUnitario.setText("");
                    txtEstoque.setText("");
                } else {
                    JOptionPane.showMessageDialog(btnAltProd, "Selecione um produto!");
                }
            }
        });

        btnDelProd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tableProduto.getSelectedRow();
                if (selectedRow >= 0) {
                    int codigo = Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString());

                    ProdutoControl produtoControl = new ProdutoControl();
                    produtoControl.excluirProduto(codigo);

                    atualizarTabela();
                    txtDesc.setText("");
                    txtValorUnitario.setText("");
                    txtEstoque.setText("");
                } else {
                    JOptionPane.showMessageDialog(btnDelProd, "Selecione um produto!");
                }
            }
        });
    }

    public void preencherTabela(){
        ProdutoControl produtoControl = new ProdutoControl();
        List<ProdutoModel> produtos = produtoControl.listarProdutos();

        for (ProdutoModel produto : produtos){
            tableModel.addRow(new Object[]{
                    produto.getA03_codigo(),
                    produto.getA03_descricao(),
                    produto.getA03_valor_unitario(),
                    produto.getA03_estoque()
            });
        }
    }

    private void atualizarTabela() {
        tableModel.setRowCount(0);
        preencherTabela();
    }

}
