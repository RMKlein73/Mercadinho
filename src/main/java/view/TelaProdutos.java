/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author Rafael
 */
import model.Produto;
import model.ProdutoDAO;
import model.Sessao;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class TelaProdutos extends javax.swing.JFrame {

    private JTable jTable2;
    private JTextField jTextField1, jTextField2;
    private JButton jButton1, jButton3;

    public TelaProdutos() {
        initComponents();
        carregarProdutos();
    }

    private void initComponents() {

        setTitle("Produtos");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(650, 480);
        setLocationRelativeTo(null);

        jTextField1 = new JTextField();
        jTextField2 = new JTextField();

        jButton1 = new JButton("Comprar");
        jButton3 = new JButton("Voltar à tela principal");

        jTable2 = new JTable(
            new DefaultTableModel(
                new Object[][] {},
                new String[] {
                    "ID",
                    "Nome",
                    "Categoria",
                    "Quantidade",
                    "Valor"
                }
            ) {
                @Override
                public boolean isCellEditable(int r, int c) {
                    return false;
                }
            }
        );

        jButton1.addActionListener(e -> comprar());
        jButton3.addActionListener(e -> voltar());

        JPanel topo = new JPanel(
            new java.awt.GridLayout(3, 2, 8, 8)
        );

        topo.setBorder(
            BorderFactory.createEmptyBorder(
                15, 15, 10, 15
            )
        );

        topo.add(new JLabel("Nome do produto:"));
        topo.add(jTextField1);

        topo.add(new JLabel("Quantidade:"));
        topo.add(jTextField2);

        topo.add(jButton1);
        topo.add(jButton3);

        JPanel p = new JPanel(
            new java.awt.BorderLayout()
        );

        p.add(
            new JLabel(
                "Produtos disponíveis",
                SwingConstants.CENTER
            ),
            java.awt.BorderLayout.NORTH
        );

        p.add(
            topo,
            java.awt.BorderLayout.PAGE_START
        );

        p.add(
            new JScrollPane(jTable2),
            java.awt.BorderLayout.CENTER
        );

        setContentPane(p);

        jTable2.setPreferredScrollableViewportSize(
            new java.awt.Dimension(600, 260)
        );
    }

    private void carregarProdutos() {

        try {

            DefaultTableModel m =
                    (DefaultTableModel) jTable2.getModel();

            m.setRowCount(0);

            for (Produto p : new ProdutoDAO().listar()) {

                m.addRow(
                    new Object[] {
                        p.getId(),
                        p.getNome(),
                        p.getCategoria(),
                        p.getQuantidade(),
                        String.format(
                            "R$ %.2f",
                            p.getValor()
                        )
                    }
                );
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                this,
                "Erro ao carregar produtos: "
                + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void comprar() {

    if (!Sessao.isClienteLogado() && !Sessao.isAdminLogado()) {

        JOptionPane.showMessageDialog(
            this,
            "Faça login antes de comprar."
        );

        return;
    }

    String nome = jTextField1.getText().trim();
    String textoQuantidade = jTextField2.getText().trim();

    if (nome.isEmpty()) {

        JOptionPane.showMessageDialog(
            this,
            "Informe o nome do produto."
        );

        return;
    }

    if (textoQuantidade.isEmpty()) {

        JOptionPane.showMessageDialog(
            this,
            "Informe a quantidade."
        );

        return;
    }

    int quantidade;

    try {

        quantidade = Integer.parseInt(textoQuantidade);

    } catch (NumberFormatException e) {

        JOptionPane.showMessageDialog(
            this,
            "A quantidade deve ser um número inteiro."
        );

        return;
    }

    if (quantidade <= 0) {

        JOptionPane.showMessageDialog(
            this,
            "A quantidade deve ser maior que zero."
        );

        return;
    }

    try {

        if (new ProdutoDAO().comprar(nome, quantidade)) {

            JOptionPane.showMessageDialog(
                this,
                "Compra realizada e estoque atualizado!"
            );

            carregarProdutos();

            jTextField1.setText("");
            jTextField2.setText("");

        } else {

            JOptionPane.showMessageDialog(
                this,
                "Produto inexistente ou estoque insuficiente."
            );
        }

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
            this,
            "Erro: " + e.getMessage(),
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
            () -> new TelaProdutos().setVisible(true)
        );
    }
}