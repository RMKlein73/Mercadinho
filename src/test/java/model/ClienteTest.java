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

    @Test
    void naoDevePermitirUsuarioVazioOuNulo() {
        Cliente cliente = new Cliente();

        assertThrows(IllegalArgumentException.class, () -> {
            cliente.setUsuario(null);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            cliente.setUsuario("   ");
        });
    }

    @Test
    void naoDevePermitirSenhaVaziaOuNula() {
        Cliente cliente = new Cliente();

        assertThrows(IllegalArgumentException.class, () -> {
            cliente.setSenha(null);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            cliente.setSenha("");
        });
    }

    @Test
    void naoDeveAceitarEmailSemFormatoValido() {
        Cliente cliente = new Cliente();

        assertThrows(IllegalArgumentException.class, () -> {
            cliente.setEmail("emailinvalido.com");
        });
    }

    @Test
    void naoDeveAceitarIdNegativo() {
        Cliente cliente = new Cliente();

        assertThrows(IllegalArgumentException.class, () -> {
            cliente.setId(-1);
        });
    }
}