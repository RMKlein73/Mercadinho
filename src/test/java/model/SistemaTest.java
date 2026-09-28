package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class SistemaTest {

    @Test
    void deveAutenticarClienteExistente() {
        Cliente cliente = Sistema.autenticarCliente("cliente", "1234");

        assertNotNull(cliente);
        assertEquals("cliente", cliente.getUsuario());
    }

    @Test
    void naoDeveAutenticarClienteComSenhaErrada() {
        assertNull(Sistema.autenticarCliente("cliente", "senhaErrada"));
    }

    @Test
    void deveAutenticarAdministradorComCredenciaisCorretas() {
        assertTrue(Sistema.autenticarAdmin("admin", "admin123"));
    }

    @Test
    void naoDeveAutenticarAdministradorComCredenciaisErradas() {
        assertFalse(Sistema.autenticarAdmin("admin", "errada"));
    }

    @Test
    void naoDeveCadastrarClienteComUsuarioVazio() {
        Cliente cliente = new Cliente("", "1234", "cpf", "telefone", "email");

        assertFalse(Sistema.cadastrarCliente(cliente));
    }

    @Test
    void naoDeveCadastrarUsuarioAdminComoCliente() {
        Cliente cliente = new Cliente("admin", "1234", "cpf2", "telefone", "email");

        assertFalse(Sistema.cadastrarCliente(cliente));
    }

    @Test
    void deveComprarProdutoQuandoQuantidadeForSuficiente() {
        Produto produto = new Produto("ProdutoTesteCompra", "Teste", 10, 5.0);
        Sistema.adicionarProduto(produto);

        int antes = produto.getQuantidade();

        assertTrue(Sistema.comprarProduto(Sistema.getProdutos().size() - 1, 3));
        assertEquals(antes - 3, produto.getQuantidade());

        Sistema.excluirProduto(Sistema.getProdutos().size() - 1);
    }

    @Test
    void naoDeveComprarQuantidadeMaiorQueEstoque() {
        Produto produto = new Produto("ProdutoTesteEstoque", "Teste", 2, 5.0);
        Sistema.adicionarProduto(produto);

        int indice = Sistema.getProdutos().size() - 1;

        assertFalse(Sistema.comprarProduto(indice, 3));
        assertEquals(2, produto.getQuantidade());

        Sistema.excluirProduto(indice);
    }

    @Test
    void naoDeveComprarQuantidadeZeroOuNegativa() {
        Produto produto = new Produto("ProdutoTesteQuantidade", "Teste", 10, 5.0);
        Sistema.adicionarProduto(produto);

        int indice = Sistema.getProdutos().size() - 1;

        assertFalse(Sistema.comprarProduto(indice, 0));
        assertFalse(Sistema.comprarProduto(indice, -1));

        Sistema.excluirProduto(indice);
    }
}
