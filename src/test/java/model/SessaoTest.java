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
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class SessaoTest {

    @AfterEach
    void limparSessao() {
        Sessao.logout();
    }

    @Test
    void loginClienteDeveAtivarSomenteCliente() {
        Sessao.loginCliente();

        assertTrue(Sessao.isClienteLogado());
        assertFalse(Sessao.isAdminLogado());
    }

    @Test
    void loginAdminDeveAtivarSomenteAdmin() {
        Sessao.loginAdmin();

        assertTrue(Sessao.isAdminLogado());
        assertFalse(Sessao.isClienteLogado());
    }

    @Test
    void logoutDeveEncerrarTodasAsSessoes() {
        Sessao.loginCliente();
        Sessao.logout();

        assertFalse(Sessao.isClienteLogado());
        assertFalse(Sessao.isAdminLogado());
    }

    @Test
    void inicializacaoSemLoginNaoDeveTerSessaoAtiva() {
        assertFalse(Sessao.isClienteLogado());
        assertFalse(Sessao.isAdminLogado());
    }

    @Test
    void loginAdminDeveSobreescreverSessaoDeClienteSemAcumular() {
        Sessao.loginCliente();
        Sessao.loginAdmin();

        assertTrue(Sessao.isAdminLogado());
        assertFalse(Sessao.isClienteLogado());
    }

    @Test
    void loginClienteDeveSobreescreverSessaoDeAdminSemAcumular() {
        Sessao.loginAdmin();
        Sessao.loginCliente();

        assertTrue(Sessao.isClienteLogado());
        assertFalse(Sessao.isAdminLogado());
    }

    @Test
    void logoutRepetidoNaoDeveCausarExcecao() {
        assertDoesNotThrow(() -> {
            Sessao.logout();
            Sessao.logout();
        });

        assertFalse(Sessao.isClienteLogado());
        assertFalse(Sessao.isAdminLogado());
    }
}