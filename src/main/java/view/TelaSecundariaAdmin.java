/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author Rafael
 */
import model.Sessao;
import javax.swing.*;

public class TelaSecundariaAdmin extends javax.swing.JFrame {

    private JButton jButton1, jButton3, jButton4;
    private JLabel jLabel1;

    public TelaSecundariaAdmin() {
        initComponents();
    }

    private void initComponents() {

        setTitle("Área do Administrador");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(440, 300);
        setLocationRelativeTo(null);

        jLabel1 = new JLabel(
            "Login de administrador realizado!",
            SwingConstants.CENTER
        );

        jButton1 = new JButton("Ver produtos");
        jButton3 = new JButton("Cadastrar produtos");
        jButton4 = new JButton("Voltar à tela principal");

        jButton1.addActionListener(
            e -> abrir(new TelaProdutos())
        );

        jButton3.addActionListener(
            e -> abrir(new TelaCadastroProdutos())
        );

        jButton4.addActionListener(
            e -> voltar()
        );

        JPanel p = new JPanel();

        p.setBorder(
            BorderFactory.createEmptyBorder(
                35, 55, 35, 55
            )
        );

        p.setLayout(
            new BoxLayout(
                p,
                BoxLayout.Y_AXIS
            )
        );

        p.add(jLabel1);
        p.add(Box.createVerticalStrut(25));

        p.add(jButton1);
        p.add(Box.createVerticalStrut(10));

        p.add(jButton3);
        p.add(Box.createVerticalStrut(10));

        p.add(jButton4);

        setContentPane(p);
    }

    private void abrir(JFrame f) {

        if (!Sessao.isAdminLogado()) {
            new TelaLoginAdmin().setVisible(true);
        } else {
            f.setVisible(true);
        }

        dispose();
    }

    private void voltar() {
        Sessao.logout();
        new TelaPrincipal().setVisible(true);
        dispose();
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(
            () -> new TelaSecundariaAdmin().setVisible(true)
        );
    }
}