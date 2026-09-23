/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author Rafael
 */
import model.Cliente;
import model.ClienteDAO;
import javax.swing.*;

public class TelaCadastro extends javax.swing.JFrame {

    private JTextField jTextField1, jTextField3, jTextField4, jTextField5;
    private JPasswordField jPasswordField1;
    private JButton jButton1, jButton2;

    public TelaCadastro() {
        initComponents();
    }

    private void initComponents() {

        setTitle("Cadastro de Cliente");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(430, 390);
        setLocationRelativeTo(null);

        jTextField1 = new JTextField();
        jPasswordField1 = new JPasswordField();
        jTextField3 = new JTextField();
        jTextField4 = new JTextField();
        jTextField5 = new JTextField();

        jButton1 = new JButton("Cadastrar");
        jButton2 = new JButton("Voltar à tela principal");

        JPanel p = new JPanel(
                new java.awt.GridLayout(6, 2, 8, 8)
        );

        p.setBorder(
                BorderFactory.createEmptyBorder(
                        25, 35, 25, 35
                )
        );

        p.add(new JLabel("Usuário:"));
        p.add(jTextField1);

        p.add(new JLabel("Senha:"));
        p.add(jPasswordField1);

        p.add(new JLabel("CPF:"));
        p.add(jTextField3);

        p.add(new JLabel("Telefone:"));
        p.add(jTextField4);

        p.add(new JLabel("E-mail:"));
        p.add(jTextField5);

        p.add(jButton1);
        p.add(jButton2);

        setContentPane(p);

        jButton1.addActionListener(e -> cadastrar());
        jButton2.addActionListener(e -> voltar());
    }

    private void cadastrar() {

        String usuario = jTextField1.getText().trim();
        String senha = new String(jPasswordField1.getPassword());
        String cpf = jTextField3.getText().trim();
        String tel = jTextField4.getText().trim();
        String email = jTextField5.getText().trim();

        if (
            usuario.isEmpty()
            || senha.isEmpty()
            || cpf.isEmpty()
            || tel.isEmpty()
            || email.isEmpty()
        ) {
            msg(
                "Preencha todos os campos.",
                JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        try {

            new ClienteDAO().cadastrar(
                new Cliente(
                    usuario,
                    senha,
                    cpf,
                    tel,
                    email
                )
            );

            msg(
                "Cliente cadastrado com sucesso!",
                JOptionPane.INFORMATION_MESSAGE
            );

            voltar();

        } catch (Exception ex) {

            msg(
                "Não foi possível cadastrar. Usuário ou CPF podem já existir.\n"
                + ex.getMessage(),
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void voltar() {
        new TelaPrincipal().setVisible(true);
        dispose();
    }

    private void msg(String s, int t) {
        JOptionPane.showMessageDialog(
            this,
            s,
            "Mercadinho",
            t
        );
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(
            () -> new TelaCadastro().setVisible(true)
        );
    }
}