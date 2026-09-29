/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Rafael
 */
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ProdutoTest {

    @Test
    void deveCriarProdutoComDadosInformados() {
        Produto produto = new Produto("Arroz", "Alimentos", 20, 29.90);

        assertEquals("Arroz", produto.getNome());
        assertEquals("Alimentos", produto.getCategoria());
        assertEquals(20, produto.getQuantidade());
        assertEquals(29.90, produto.getValor(), 0.001);
    }

    @Test
    void deveAlterarQuantidadeEValor() {
        Produto produto = new Produto();

        produto.setQuantidade(50);
        produto.setValor(12.50);

        assertEquals(50, produto.getQuantidade());
        assertEquals(12.50, produto.getValor(), 0.001);
    }

    @Test
    void naoDevePermitirNomeVazioOuNulo() {
        Produto produto = new Produto();

        assertThrows(IllegalArgumentException.class, () -> {
            produto.setNome(null);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            produto.setNome("   ");
        });
    }

    @Test
    void naoDevePermitirCategoriaVaziaOuNula() {
        Produto produto = new Produto();

        assertThrows(IllegalArgumentException.class, () -> {
            produto.setCategoria(null);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            produto.setCategoria("");
        });
    }

    @Test
    void naoDevePermitirQuantidadeNegativa() {
        Produto produto = new Produto();

        assertThrows(IllegalArgumentException.class, () -> {
            produto.setQuantidade(-5);
        });
    }

    @Test
    void naoDevePermitirValorNegativo() {
        Produto produto = new Produto();

        assertThrows(IllegalArgumentException.class, () -> {
            produto.setValor(-10.0);
        });
    }

    @Test
    void naoDevePermitirIdNegativo() {
        Produto produto = new Produto();

        assertThrows(IllegalArgumentException.class, () -> {
            produto.setId(-1);
        });
    }
}