/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author Rafael
 */
import model.AdminDAO;
import model.Sessao;
import javax.swing.*;

public class TelaLoginAdmin extends javax.swing.JFrame {

    private JTextField jTextField1;
    private JPasswordField jPasswordField1;
    private JButton jButton1, jButton2;

    public TelaLoginAdmin() {
        initComponents();
    }

    private void initComponents() {

        setTitle("Login Admin");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 260);
        setLocationRelativeTo(null);

        jTextField1 = new JTextField();
        jPasswordField1 = new JPasswordField();

        jButton1 = new JButton("Entrar");
        jButton2 = new JButton("Voltar à tela principal");

        JPanel p = new JPanel(
            new java.awt.GridLayout(3, 2, 10, 10)
        );

        p.setBorder(
            BorderFactory.createEmptyBorder(
                35, 35, 35, 35
            )
        );

        p.add(new JLabel("Usuário:"));
        p.add(jTextField1);

        p.add(new JLabel("Senha:"));
        p.add(jPasswordField1);

        p.add(jButton1);
        p.add(jButton2);

        setContentPane(p);

        jButton1.addActionListener(e -> login());
        jButton2.addActionListener(e -> voltar());
    }

    private void login() {

        try {

            if (
                new AdminDAO().autenticar(
                    jTextField1.getText().trim(),
                    new String(jPasswordField1.getPassword())
                )
            ) {

                Sessao.loginAdmin();

                new TelaSecundariaAdmin().setVisible(true);
                dispose();

            } else {

                JOptionPane.showMessageDialog(
                    this,
                    "Usuário ou senha de administrador incorretos."
                );
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                this,
                "Erro no banco: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void voltar() {
        new TelaPrincipal().setVisible(true);
        dispose();
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(
            () -> new TelaLoginAdmin().setVisible(true)
        );
    }
}