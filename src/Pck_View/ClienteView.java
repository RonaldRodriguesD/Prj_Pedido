package Pck_View;

import Pck_Control.*;
import Pck_Model.ClienteModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ClienteView extends JFrame {
    private JPanel panel1;
    private JTextField txtNome;
    private JTextField txtEndereco;
    private JTextField txtTelefone;
    private JTextField txtCpf;
    private JTextField txtCredito;
    private JButton cadastrarButton;
    private JButton btnExitCadCli;
    private JTable table1;
    private JButton btnAltCli;
    private JButton btnDelCli;
    private DefaultTableModel tableModel;


    public ClienteView() {
        this.setContentPane(panel1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setTitle("Cadastro Cliente");
        this.setSize(700, 450);

        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Nome");
        tableModel.addColumn("Endereco");
        tableModel.addColumn("Telefone");
        tableModel.addColumn("CPF");
        tableModel.addColumn("Credito");

        table1 = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table1);
        scrollPane.setPreferredSize(new Dimension(450, 200));

        panel1.setLayout(new BorderLayout());
        panel1.add(scrollPane, BorderLayout.LINE_END);

        preencherTabela();

        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String nome = txtNome.getText();
                String endereco = txtEndereco.getText();
                String telefone = txtTelefone.getText();
                String cpf = txtCpf.getText();
                Double credito = Double.parseDouble(txtCredito.getText());
                JOptionPane.showMessageDialog(cadastrarButton, "Cliente " + txtNome.getText() + " cadastrado com sucesso!");
                ClienteControl clienteControl = new ClienteControl();
                clienteControl.inserirCliente(nome, endereco, telefone, cpf, credito);

                txtNome.setText("");
                txtEndereco.setText("");
                txtTelefone.setText("");
                txtCpf.setText("");
                txtCredito.setText("");

                atualizarTabela();
            }
        });

        btnExitCadCli.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuView menuView = new MenuView();
                menuView.setVisible(true);
                dispose();
            }
        });

        table1.getSelectionModel().addListSelectionListener(event -> {
            int selectedRow = table1.getSelectedRow();
            if (selectedRow >= 0) {
                txtNome.setText(tableModel.getValueAt(selectedRow, 1).toString());
                txtEndereco.setText(tableModel.getValueAt(selectedRow, 2).toString());
                txtTelefone.setText(tableModel.getValueAt(selectedRow, 3).toString());
                txtCpf.setText(tableModel.getValueAt(selectedRow, 4).toString());
                txtCredito.setText(tableModel.getValueAt(selectedRow, 5).toString());
            }
        });

        btnAltCli.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table1.getSelectedRow();
                if (selectedRow >= 0) {
                    int codigo = Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString());
                    String nome = txtNome.getText();
                    String endereco = txtEndereco.getText();
                    String telefone = txtTelefone.getText();
                    String cpf = txtCpf.getText();
                    Double credito = Double.parseDouble(txtCredito.getText());

                    ClienteControl clienteControl = new ClienteControl();
                    clienteControl.alterarCliente(codigo, nome, endereco, telefone, cpf, credito);

                    atualizarTabela();
                    txtNome.setText("");
                    txtEndereco.setText("");
                    txtTelefone.setText("");
                    txtCpf.setText("");
                    txtCredito.setText("");

                } else {
                    JOptionPane.showMessageDialog(btnAltCli, "Selecione um cliente na tabela!");
                }
            }
        });

        btnDelCli.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table1.getSelectedRow();
                if (selectedRow >= 0) {
                    int codigo = Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString());

                    ClienteControl clienteControl = new ClienteControl();
                    clienteControl.excluirCliente(codigo);

                    atualizarTabela();
                    txtNome.setText("");
                    txtEndereco.setText("");
                    txtTelefone.setText("");
                    txtCpf.setText("");
                    txtCredito.setText("");
                } else {
                    JOptionPane.showMessageDialog(btnDelCli, "Selecione um cliente na tabela!");
                }
            }
        });
    }

    public void preencherTabela(){
        ClienteControl clienteControl = new ClienteControl();
        List<ClienteModel> clientes = clienteControl.listarClientes();

        for (ClienteModel cliente : clientes){
            tableModel.addRow(new Object[]{
                    cliente.getA01_codigo(),
                    cliente.getA01_nome(),
                    cliente.getA01_endereco(),
                    cliente.getA01_telefone(),
                    cliente.getA01_cpf(),
                    cliente.getA01_credito()
            });
        }
    }

    private void atualizarTabela() {
        tableModel.setRowCount(0);
        preencherTabela();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ClienteView::new);
    }
}

