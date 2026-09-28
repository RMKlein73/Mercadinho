package model;

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
}
