package Pck_View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuView extends JFrame {
    private JPanel panelMenu;
    private JButton btnCadCli;
    private JButton btnCadProd;
    private JButton btnCadPed;
    private JButton btnCadIten;

    public MenuView() {
        this.setTitle("Menu");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panelMenu);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setSize(400, 400);

        btnCadCli.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClienteView clienteView = new ClienteView();
                clienteView.setVisible(true);
                dispose();
            }
        });

        btnCadPed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PedidoView pedidoView = new PedidoView();
                pedidoView.setVisible(true);
                dispose();
            }
        });

        btnCadProd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProdutoView produtoView = new ProdutoView();
                produtoView.setVisible(true);
                dispose();
            }
        });

        btnCadIten.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ItemView itemView = new ItemView();
                itemView.setVisible(true);
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MenuView::new);
    }
}
