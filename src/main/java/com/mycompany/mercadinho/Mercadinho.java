package com.mycompany.mercadinho;

import model.BancoDados;
import javax.swing.SwingUtilities;
import javax.swing.JOptionPane;
import view.TelaPrincipal;

public class Mercadinho {

    public static void main(String[] args) {

        try {
            BancoDados.inicializar();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                null,
                "Erro ao abrir o banco:\n" + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        SwingUtilities.invokeLater(() ->
            new TelaPrincipal().setVisible(true)
        );
    }
}