package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ClienteTest {

    @Test
    void deveCriarClienteComDadosInformados() {
        Cliente cliente = new Cliente(
                "rafael",
                "1234",
                "111.111.111-11",
                "(51) 99999-9999",
                "rafael@email.com"
        );

        assertEquals("rafael", cliente.getUsuario());
        assertEquals("1234", cliente.getSenha());
        assertEquals("111.111.111-11", cliente.getCpf());
        assertEquals("(51) 99999-9999", cliente.getTelefone());
        assertEquals("rafael@email.com", cliente.getEmail());
    }

    @Test
    void deveAlterarDadosDoCliente() {
        Cliente cliente = new Cliente();

        cliente.setId(10);
        cliente.setUsuario("novoUsuario");
        cliente.setSenha("novaSenha");

        assertEquals(10, cliente.getId());
        assertEquals("novoUsuario", cliente.getUsuario());
        assertEquals("novaSenha", cliente.getSenha());
    }
}
