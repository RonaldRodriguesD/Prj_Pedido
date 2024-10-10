package Pck_View;
import Pck_Control.PedidoControl;
import Pck_Model.ClienteModel;
import Pck_Control.ClienteControl;
import Pck_Model.PedidoModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class PedidoView extends JFrame {
    private JPanel panel1;
    private JTextField txtData;
    private JTextField txtValorTotal;
    private JComboBox<ClienteModel> cmbCliente;
    private JButton bntCadastrar;
    private JButton btnExitCadPed;
    private JTable tablePedido;
    private JButton btnAltPed;
    private JButton btnDelPed;
    private DefaultTableModel tableModel;

    public PedidoView() {
        this.setTitle("Cadastro Pedido");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(panel1);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setSize(700, 450);

        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("DATA");
        tableModel.addColumn("TOTAL");
        tableModel.addColumn("Cliente");

        tablePedido = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tablePedido);
        scrollPane.setPreferredSize(new Dimension(450, 200));

        panel1.setLayout(new BorderLayout());
        panel1.add(scrollPane, BorderLayout.LINE_END);

        carregarClientes();
        preencherTabela();

        bntCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String dataEmTexto = txtData.getText();

                    SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
                    formatoData.setLenient(false); // Garante que a data seja rigorosamente validada

                    Date dataUtil = formatoData.parse(dataEmTexto);
                    java.sql.Date dataConv = new java.sql.Date(dataUtil.getTime());

                    Double valorTotal = Double.parseDouble(txtValorTotal.getText());

                    ClienteModel clienteSelecionado = (ClienteModel) cmbCliente.getSelectedItem();
                    if (clienteSelecionado != null) {
                        int codigoClienteSel = clienteSelecionado.getA01_codigo();

                        PedidoControl pedidoControl = new PedidoControl();
                        pedidoControl.inserirPedido(dataConv, valorTotal, codigoClienteSel);

                        txtData.setText("");
                        txtValorTotal.setText("");
                        cmbCliente.setSelectedIndex(-1);
                        atualizarTabela();

                    } else {
                        JOptionPane.showMessageDialog(null, "Selecione um cliente");
                    }

                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null, "Data inválida. Use o formato dd/MM/yyyy.");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Valor total inválido.");
                }
            }
        });

        tablePedido.getSelectionModel().addListSelectionListener(event -> {
            int selectedRow = tablePedido.getSelectedRow();
            if (selectedRow >= 0) {
                txtData.setText(tableModel.getValueAt(selectedRow, 1).toString());
                txtValorTotal.setText(tableModel.getValueAt(selectedRow, 2).toString());
                cmbCliente.setSelectedItem(tableModel.getValueAt(selectedRow, 3));
            }
        });

        btnExitCadPed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuView menuView = new MenuView();
                menuView.setVisible(true);
                dispose();
            }
        });

        btnAltPed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tablePedido.getSelectedRow();
                if (selectedRow >= 0) {
                    int codigo = Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString());
                    String dataEmTexto = txtData.getText();
                    Double valorTotal = Double.parseDouble(txtValorTotal.getText());
                    try{
                        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
                        formatoData.setLenient(false);

                        Date dataUtil = formatoData.parse(dataEmTexto);
                        java.sql.Date dataConv = new java.sql.Date(dataUtil.getTime());

                        ClienteModel clienteSelecionado = (ClienteModel) cmbCliente.getSelectedItem();
                        if (clienteSelecionado != null) {
                            int codigoClienteSel = clienteSelecionado.getA01_codigo();

                            PedidoControl pedidoControl = new PedidoControl();
                            pedidoControl.alterarPedido(codigo, dataConv, valorTotal, codigoClienteSel);

                            txtData.setText("");
                            txtValorTotal.setText("");
                            cmbCliente.setSelectedIndex(-1);
                            atualizarTabela();
                        }
                    } catch (ParseException erro) {
                        System.out.println("Erro na conversão de data" + erro);
                    } catch (NumberFormatException erro) {
                        System.out.println("Valor Total Inválido");
                    }
                } else{
                    JOptionPane.showMessageDialog(null, "Selecione um cliente");
                }
            }
        });

        btnDelPed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tablePedido.getSelectedRow();
                if (selectedRow >= 0) {
                    int codigo = Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString());

                    PedidoControl pedidoControl = new PedidoControl();
                    pedidoControl.excluirPedido(codigo);

                    txtData.setText("");
                    txtValorTotal.setText("");
                    cmbCliente.setSelectedIndex(-1);
                    atualizarTabela();
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione um cliente");
                }
            }
        });
    }

    public void carregarClientes() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ClienteControl clienteControl = new ClienteControl();
                List<ClienteModel> clientes = clienteControl.listarClientes();

                if (clientes != null && !clientes.isEmpty()) {
                    for (ClienteModel cliente : clientes) {
                        cmbCliente.addItem(cliente);
                    }
                } else {
                    System.out.println("nenhum cliente encontrado");
                }
            }
        });
    }

    public void preencherTabela(){
        PedidoControl pedidoControl = new PedidoControl();
        ClienteControl clienteControl = new ClienteControl();
        List<PedidoModel> pedidos = pedidoControl.listarPedidos();
        List<ClienteModel> clientes = clienteControl.listarClientes();

        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");

        for (PedidoModel pedido : pedidos) {
            ClienteModel clienteCorrespondente = null;
            for (ClienteModel cliente : clientes) {
                if (cliente.getA01_codigo() == pedido.getA01_codigo()) {
                    clienteCorrespondente = cliente;
                    break;
                }
            }

            String dataFormatada = "--/--/----";
            if(pedido.getA02_data() instanceof Date){
                dataFormatada = formatoData.format(pedido.getA02_data());
            }
            tableModel.addRow(new Object[]{
                    pedido.getA02_codigo(),
                    dataFormatada,
                    pedido.getA02_valor_total(),
                    clienteCorrespondente != null ? clienteCorrespondente.toString() : "----------"
            });
        }
    }

    private void atualizarTabela() {
        tableModel.setRowCount(0);
        preencherTabela();
    }

}
