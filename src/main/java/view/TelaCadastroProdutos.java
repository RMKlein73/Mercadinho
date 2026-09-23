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

public class TelaCadastroProdutos extends javax.swing.JFrame {

    private JTextField jTextField1, jTextField2, jTextField3, jTextField4;
    private JButton jButton1, jButton2, jButton3, jButton4;
    private JTable jTable1;

    public TelaCadastroProdutos() {
        initComponents();
        carregar();
    }

    private void initComponents() {

        setTitle("Cadastro de Produtos - Admin");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(650, 560);
        setLocationRelativeTo(null);

        jTextField1 = new JTextField();
        jTextField2 = new JTextField();
        jTextField3 = new JTextField();
        jTextField4 = new JTextField();

        jButton1 = new JButton("Cadastrar");
        jButton2 = new JButton("Atualizar");
        jButton3 = new JButton("Excluir");
        jButton4 = new JButton("Voltar à tela principal");

        jTable1 = new JTable(
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

        JPanel form = new JPanel(
            new java.awt.GridLayout(5, 2, 8, 8)
        );

        form.setBorder(
            BorderFactory.createEmptyBorder(
                15, 25, 10, 25
            )
        );

        form.add(new JLabel("Nome:"));
        form.add(jTextField1);

        form.add(new JLabel("Categoria:"));
        form.add(jTextField2);

        form.add(new JLabel("Quantidade:"));
        form.add(jTextField3);

        form.add(new JLabel("Valor:"));
        form.add(jTextField4);

        JPanel b = new JPanel();

        b.add(jButton1);
        b.add(jButton2);
        b.add(jButton3);
        b.add(jButton4);

        JPanel p = new JPanel(
            new java.awt.BorderLayout()
        );

        p.add(
            new JLabel(
                "Cadastro de Produtos",
                SwingConstants.CENTER
            ),
            java.awt.BorderLayout.NORTH
        );

        p.add(
            form,
            java.awt.BorderLayout.PAGE_START
        );

        p.add(
            new JScrollPane(jTable1),
            java.awt.BorderLayout.CENTER
        );

        p.add(
            b,
            java.awt.BorderLayout.SOUTH
        );

        setContentPane(p);

        jTable1.setPreferredScrollableViewportSize(
            new java.awt.Dimension(600, 240)
        );

        jButton1.addActionListener(e -> cadastrar());
        jButton2.addActionListener(e -> atualizar());
        jButton3.addActionListener(e -> excluir());
        jButton4.addActionListener(e -> voltar());

        jTable1.getSelectionModel()
                .addListSelectionListener(e -> selecionar());
    }

    private boolean admin() {

        if (!Sessao.isAdminLogado()) {

            JOptionPane.showMessageDialog(
                this,
                "Acesso permitido somente ao administrador."
            );

            new TelaLoginAdmin().setVisible(true);
            dispose();

            return false;
        }

        return true;
    }

    private Produto dados(boolean id) {

        String nome = jTextField1.getText().trim();
        String cat = jTextField2.getText().trim();

        if (nome.isEmpty() || cat.isEmpty()) {
            throw new IllegalArgumentException(
                "Preencha nome e categoria."
            );
        }

        int q = Integer.parseInt(
            jTextField3.getText().trim()
        );

        double v = Double.parseDouble(
            jTextField4.getText()
                    .trim()
                    .replace(',', '.')
        );

        if (q < 0 || v < 0) {
            throw new IllegalArgumentException(
                "Quantidade e valor não podem ser negativos."
            );
        }

        Produto p = new Produto(
            nome,
            cat,
            q,
            v
        );

        if (id) {

            int r = jTable1.getSelectedRow();

            if (r < 0) {
                throw new IllegalArgumentException(
                    "Selecione um produto na tabela."
                );
            }

            p.setId(
                Integer.parseInt(
                    jTable1.getValueAt(r, 0).toString()
                )
            );
        }

        return p;
    }

    private void cadastrar() {

        if (!admin()) {
            return;
        }

        try {

            new ProdutoDAO().cadastrar(
                dados(false)
            );

            JOptionPane.showMessageDialog(
                this,
                "Produto cadastrado!"
            );

            limpar();
            carregar();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                this,
                "Não foi possível cadastrar: "
                + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void atualizar() {

        if (!admin()) {
            return;
        }

        try {

            new ProdutoDAO().atualizar(
                dados(true)
            );

            JOptionPane.showMessageDialog(
                this,
                "Produto atualizado!"
            );

            limpar();
            carregar();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                this,
                "Não foi possível atualizar: "
                + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void excluir() {

        if (!admin()) {
            return;
        }

        int r = jTable1.getSelectedRow();

        if (r < 0) {

            JOptionPane.showMessageDialog(
                this,
                "Selecione um produto."
            );

            return;
        }

        try {

            int id = Integer.parseInt(
                jTable1.getValueAt(r, 0).toString()
            );

            if (
                JOptionPane.showConfirmDialog(
                    this,
                    "Excluir produto selecionado?",
                    "Confirmar",
                    JOptionPane.YES_NO_OPTION
                ) == JOptionPane.YES_OPTION
            ) {

                new ProdutoDAO().excluir(id);

                limpar();
                carregar();
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                this,
                "Erro: " + e.getMessage()
            );
        }
    }

    private void selecionar() {

        int r = jTable1.getSelectedRow();

        if (r >= 0) {

            jTextField1.setText(
                jTable1.getValueAt(r, 1).toString()
            );

            jTextField2.setText(
                jTable1.getValueAt(r, 2).toString()
            );

            jTextField3.setText(
                jTable1.getValueAt(r, 3).toString()
            );

            jTextField4.setText(
                jTable1.getValueAt(r, 4)
                        .toString()
                        .replace("R$ ", "")
                        .replace(',', '.')
            );
        }
    }

    private void carregar() {

        try {

            DefaultTableModel m =
                    (DefaultTableModel) jTable1.getModel();

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
                "Erro ao carregar: " + e.getMessage()
            );
        }
    }

    private void limpar() {

        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");

        jTable1.clearSelection();
    }

    private void voltar() {
        new TelaPrincipal().setVisible(true);
        dispose();
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(
            () -> new TelaCadastroProdutos().setVisible(true)
        );
    }
}