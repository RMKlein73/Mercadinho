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
        cliente.setCpf("222.222.222-22");
        cliente.setTelefone("(51) 98888-8888");
        cliente.setEmail("novo@email.com");

        assertEquals(10, cliente.getId());
        assertEquals("novoUsuario", cliente.getUsuario());
        assertEquals("novaSenha", cliente.getSenha());
        assertEquals("222.222.222-22", cliente.getCpf());
        assertEquals("(51) 98888-8888", cliente.getTelefone());
        assertEquals("novo@email.com", cliente.getEmail());
    }

    @Test
    void naoDevePermitirUsuarioVazioOuNulo() {
        Cliente cliente = new Cliente();

        assertThrows(
                IllegalArgumentException.class,
                () -> cliente.setUsuario(null)
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> cliente.setUsuario("")
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> cliente.setUsuario("   ")
        );
    }

    @Test
    void naoDevePermitirSenhaVaziaOuNula() {
        Cliente cliente = new Cliente();

        assertThrows(
                IllegalArgumentException.class,
                () -> cliente.setSenha(null)
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> cliente.setSenha("")
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> cliente.setSenha("   ")
        );
    }

    @Test
    void naoDeveAceitarEmailSemFormatoValido() {
        Cliente cliente = new Cliente();

        assertThrows(
                IllegalArgumentException.class,
                () -> cliente.setEmail("emailinvalido.com")
        );
    }

    @Test
    void deveAceitarEmailNulo() {
        Cliente cliente = new Cliente();

        assertDoesNotThrow(
                () -> cliente.setEmail(null)
        );

        assertNull(cliente.getEmail());
    }

    @Test
    void naoDeveAceitarIdNegativo() {
        Cliente cliente = new Cliente();

        assertThrows(
                IllegalArgumentException.class,
                () -> cliente.setId(-1)
        );
    }

    @Test
    void deveAceitarIdZero() {
        Cliente cliente = new Cliente();

        assertDoesNotThrow(
                () -> cliente.setId(0)
        );

        assertEquals(0, cliente.getId());
    }
}