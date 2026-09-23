/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author Rafael
 */
import model.BancoDados;
import javax.swing.*;

public class TelaPrincipal extends javax.swing.JFrame {

    private JButton jButton1, jButton2, jButton3, jButton4;
    private JLabel jLabel1;

    public TelaPrincipal() {
        initComponents();

        try {
            BancoDados.inicializar();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                this,
                "Erro ao preparar o banco: " + e.getMessage(),
                "Banco de dados",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void initComponents() {

        setTitle("Mercadinho - Tela Principal");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(420, 300);
        setLocationRelativeTo(null);

        jLabel1 = new JLabel(
            "Bem Vindo Ao Mercadinho!",
            SwingConstants.CENTER
        );

        jButton1 = new JButton("Login");
        jButton2 = new JButton("Login Admin");
        jButton3 = new JButton("Cadastro");
        jButton4 = new JButton("Sair do sistema");

        jButton1.addActionListener(
            e -> abrir(new TelaLogin())
        );

        jButton2.addActionListener(
            e -> abrir(new TelaLoginAdmin())
        );

        jButton3.addActionListener(
            e -> abrir(new TelaCadastro())
        );

        jButton4.addActionListener(
            e -> System.exit(0)
        );

        JPanel p = new JPanel();

        p.setBorder(
            BorderFactory.createEmptyBorder(
                30, 60, 30, 60
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

        p.add(jButton2);
        p.add(Box.createVerticalStrut(10));

        p.add(jButton3);
        p.add(Box.createVerticalStrut(10));

        p.add(jButton4);

        setContentPane(p);
    }

    private void abrir(JFrame tela) {
        tela.setVisible(true);
        dispose();
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(
            () -> new TelaPrincipal().setVisible(true)
        );
    }
}