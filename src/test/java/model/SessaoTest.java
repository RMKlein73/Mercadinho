package model;

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
}
