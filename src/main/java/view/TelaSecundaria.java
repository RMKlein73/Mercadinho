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

public class TelaSecundaria extends javax.swing.JFrame {

    private JButton jButton1, jButton3;
    private JLabel jLabel1;

    public TelaSecundaria() {
        initComponents();
    }

    private void initComponents() {

        setTitle("Área do Cliente");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(420, 260);
        setLocationRelativeTo(null);

        jLabel1 = new JLabel(
            "Login realizado com sucesso!",
            SwingConstants.CENTER
        );

        jButton1 = new JButton("Ver produtos");
        jButton3 = new JButton("Voltar à tela principal");

        jButton1.addActionListener(e -> {

            if (!Sessao.isClienteLogado()) {
                new TelaLogin().setVisible(true);
                dispose();
                return;
            }

            new TelaProdutos().setVisible(true);
            dispose();
        });

        jButton3.addActionListener(e -> voltar());

        JPanel p = new JPanel();

        p.setBorder(
            BorderFactory.createEmptyBorder(
                40, 60, 40, 60
            )
        );

        p.setLayout(
            new BoxLayout(
                p,
                BoxLayout.Y_AXIS
            )
        );

        p.add(jLabel1);
        p.add(Box.createVerticalStrut(30));

        p.add(jButton1);
        p.add(Box.createVerticalStrut(15));

        p.add(jButton3);

        setContentPane(p);
    }

    private void voltar() {
        Sessao.logout();
        new TelaPrincipal().setVisible(true);
        dispose();
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(
            () -> new TelaSecundaria().setVisible(true)
        );
    }
}