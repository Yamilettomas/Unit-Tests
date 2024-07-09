import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import Exceptions.InvalidAddressException;
import Exceptions.InvalidMessageException;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
public class NotificadorEmailTest {

    @Mock
    private EmailCliente emailClienteMock;

    @Test
    public void testNotificar() throws InvalidMessageException, InvalidAddressException {
        NotificadorEmail notificador = new NotificadorEmail(emailClienteMock);
        notificador.notificar("ejemplo@test.com", "Hola Mundo");

        // Verificar que emailClienteMock.enviarCorreo se llamó con los argumentos correctos
        verify(emailClienteMock).enviarCorreo("ejemplo@test.com", "Hola Mundo");
    }

    // Test para verificar que no se envía correo con dirección vacía
    @Test
    public void testNotificarConDireccionVacia() {
        NotificadorEmail notificador = new NotificadorEmail(emailClienteMock);
        assertThrows(InvalidAddressException.class, () ->
            notificador.notificar("", "Mensaje")
        );

        // Verificar que no se realiza el envío si la dirección es vacía
        verify(emailClienteMock, times(0)).enviarCorreo(anyString(), anyString());
    }

    // Test para verificar el comportamiento con mensaje nulo
    @Test
    public void testNotificarConMensajeNulo() {
        NotificadorEmail notificador = new NotificadorEmail(emailClienteMock);
        assertThrows(InvalidMessageException.class, () ->
            notificador.notificar("ejemplo@test.com", null)
        );

        // Verificar que se maneja adecuadamente un mensaje nulo
        verify(emailClienteMock, times(0)).enviarCorreo(anyString(), anyString());
    }

    @Test
    public void testNotificacionConDireccionYMensajeNulo() {
        NotificadorEmail notificador = new NotificadorEmail(emailClienteMock);
        assertThrows(InvalidAddressException.class, () ->
            notificador.notificar(null, null)
        );

        verify(emailClienteMock, times(0)).enviarCorreo(anyString(), anyString());
    }

    @Test
    public void textNotificacionConDireccionYMensajeVacios(){
        NotificadorEmail notificador = new NotificadorEmail(emailClienteMock);
        assertThrows(InvalidAddressException.class, () ->
                notificador.notificar("", "")
        );

        verify(emailClienteMock, times(0)).enviarCorreo(anyString(), anyString());
    }
}
