import Exceptions.InvalidAddressException;
import Exceptions.InvalidMessageException;

public class NotificadorEmail {
    private EmailCliente emailCliente;

    public NotificadorEmail(EmailCliente emailCliente) {
        this.emailCliente = emailCliente;
    }

    public void notificar(String direccion, String mensaje) throws InvalidAddressException, InvalidMessageException {
        if (direccion == null || direccion.isEmpty()) {
            throw new InvalidAddressException("La dirección no puede estar vacía");
        }
        if (mensaje == null || mensaje.isEmpty()) {
            throw new InvalidMessageException("El mensaje no puede estar vacío");
        }
        emailCliente.enviarCorreo(direccion, mensaje);
    }
}
