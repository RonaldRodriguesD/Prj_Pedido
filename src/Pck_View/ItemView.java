package Pck_View;
import Pck_Model.*;
import Pck_Control.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class ItemView extends JFrame {
    private JPanel panel1;
    private JComboBox<ProdutoModel> cmbProduto;
    private JComboBox<PedidoModel> cmbPedido;
    private JTextField txtQuantidade;
    private JTextField txtValorItem;
    private JButton btnCadItem;
    private JButton btnExitCadItem;
    private JTable tableItem;
    private JButton btnAltItem;
    private JButton btnDelItem;
    private DefaultTableModel tableModel;

    public ItemView() {
        this.setTitle("Cadastro Item");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(panel1);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setSize(700, 450);

        tableModel = new DefaultTableModel();
        tableModel.addColumn("N° Item");
        tableModel.addColumn("Produto");
        tableModel.addColumn("Pedido");
        tableModel.addColumn("Quantidade");
        tableModel.addColumn("Valor");

        tableItem = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tableItem);
        scrollPane.setPreferredSize(new Dimension(450, 200));

        panel1.setLayout(new BorderLayout());
        panel1.add(scrollPane, BorderLayout.LINE_END);

        carregarProdutos();
        carregarPedidos();
        preencherTabela();

        btnCadItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int quantidade = Integer.parseInt(txtQuantidade.getText());
                    double valorItem = Double.parseDouble(txtValorItem.getText());
                    ProdutoModel produtoSelecionado = (ProdutoModel) cmbProduto.getSelectedItem();
                    PedidoModel pedidoSelecionado = (PedidoModel) cmbPedido.getSelectedItem();
                    if (produtoSelecionado != null && pedidoSelecionado != null) {
                        int codProdSelecionado = produtoSelecionado.getA03_codigo();
                        int codPedSelecionado = pedidoSelecionado.getA02_codigo();

                        ItemControl itemControl = new ItemControl();
                        itemControl.inserirItem(codProdSelecionado, codPedSelecionado, quantidade, valorItem);

                        JOptionPane.showMessageDialog(btnCadItem, "Item cadastrado com sucesso!");
                        atualizarTabela();
                        cmbProduto.setSelectedItem(-1);
                        cmbPedido.setSelectedItem(-1);
                        txtQuantidade.setText("");
                        txtValorItem.setText("");
                    } else{
                        JOptionPane.showMessageDialog(btnCadItem, "Selecione um produto para o cadastro!");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(btnCadItem, "Valor item inválido!");
                }
            }
        });

        tableItem.getSelectionModel().addListSelectionListener(event -> {
            int selectedRow = tableItem.getSelectedRow();
            if (selectedRow >= 0) {
                cmbProduto.setSelectedItem(tableModel.getValueAt(selectedRow, 1));
                cmbPedido.setSelectedItem(tableModel.getValueAt(selectedRow, 2));
                txtQuantidade.setText(String.valueOf(tableModel.getValueAt(selectedRow, 3)));
                txtValorItem.setText(String.valueOf(tableModel.getValueAt(selectedRow, 4)));
            }
        });

        btnAltItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tableItem.getSelectedRow();
                if (selectedRow >= 0) {
                    int codigo = Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString());
                    int quantidade = Integer.parseInt(txtQuantidade.getText());
                    double valorItem = Double.parseDouble(txtValorItem.getText());
                    ProdutoModel produtoSelecionado = (ProdutoModel) cmbProduto.getSelectedItem();
                    PedidoModel pedidoSelecionado = (PedidoModel) cmbPedido.getSelectedItem();
                    if (produtoSelecionado != null && pedidoSelecionado != null) {
                        int codProdSelecionado = produtoSelecionado.getA03_codigo();
                        int codPedSelecionado = pedidoSelecionado.getA02_codigo();

                        ItemControl itemControl = new ItemControl();
                        itemControl.alterarItem(codigo, codProdSelecionado, codPedSelecionado, quantidade, valorItem);

                        atualizarTabela();
                        cmbProduto.setSelectedItem(-1);
                        cmbPedido.setSelectedItem(-1);
                        txtQuantidade.setText("");
                        txtValorItem.setText("");
                        JOptionPane.showMessageDialog(btnAltItem, "Item alterado com sucesso!");
                    }
                } else {
                    JOptionPane.showMessageDialog(btnAltItem, "Selecione um item!");
                }
            }
        });

        btnDelItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tableItem.getSelectedRow();
                if (selectedRow >= 0) {
                    int codigo = Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString());

                    ItemControl itemControl = new ItemControl();
                    itemControl.excluirItem(codigo);

                    atualizarTabela();
                    cmbProduto.setSelectedItem(-1);
                    cmbPedido.setSelectedItem(-1);
                    txtQuantidade.setText("");
                    txtValorItem.setText("");
                    JOptionPane.showMessageDialog(btnDelItem, "Item excluido com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(btnDelItem, "Selecione um item!");
                }
            }
        });

        btnExitCadItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuView menuView = new MenuView();
                menuView.setVisible(true);
                dispose();
            }
        });
    }

    public void carregarProdutos(){
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ProdutoControl produtoControl = new ProdutoControl();
                List<ProdutoModel> produtos = produtoControl.listarProdutos();

                if(produtos != null && !produtos.isEmpty()){
                    for(ProdutoModel produto : produtos){
                        cmbProduto.addItem(produto);
                    }
                } else {
                    System.out.println("Nenhum produto encontrado");
                }
            }
        });
    }

    public void carregarPedidos(){
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                PedidoControl pedidoControl = new PedidoControl();
                List<PedidoModel> pedidos = pedidoControl.listarPedidos();

                if(pedidos != null && !pedidos.isEmpty()){
                    for(PedidoModel pedido : pedidos){
                        cmbPedido.addItem(pedido);
                    }
                } else {
                    System.out.println("Nenhum pedido encontrado");
                }
            }
        });
    }

    public void preencherTabela(){
        ItemControl itemControl = new ItemControl();
        ProdutoControl produtoControl = new ProdutoControl();
        PedidoControl pedidoControl = new PedidoControl();
        List<ItemModel> itens = itemControl.listarItens();
        List<ProdutoModel> produtos = produtoControl.listarProdutos();
        List<PedidoModel> pedidos = pedidoControl.listarPedidos();

        for(ItemModel item : itens){
            ProdutoModel produtoCorrespondente = null;
            PedidoModel pedidoCorrespondente = null;

            for(ProdutoModel produto : produtos){
                if(produto.getA03_codigo() == item.getA03_codigo()){
                    produtoCorrespondente = produto;
                    break;
                }
            }

            for(PedidoModel pedido : pedidos){
                if(pedido.getA02_codigo() == item.getA02_codigo()){
                    pedidoCorrespondente = pedido;
                    break;
                }
            }

            tableModel.addRow(new Object[]{
                    item.getA04_codigo(),
                    produtoCorrespondente != null ? produtoCorrespondente.toString() : "-----------",
                    pedidoCorrespondente != null ? pedidoCorrespondente.toString() : "-----------",
                    item.getA04_quantidade(),
                    item.getA04_valor_item()
            });
        }
    }

    private void atualizarTabela() {
        tableModel.setRowCount(0);
        preencherTabela();
    }
}
