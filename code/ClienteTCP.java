import java.io.*;
import java.net.*;

public class ClienteTCP {

    public static void main(String[] args) {
        System.out.println("Inicia el super programa:");
        try {
            // Entrada de usuario
            BufferedReader entradaDesdeUsuario = new BufferedReader(
                new InputStreamReader(System.in)
            );

            // Crear un socket
            Socket socketCliente = new Socket("localhost", 6789);
            // Flujo hacia el servidor
            DataOutputStream salidaAServidor = new DataOutputStream(
                socketCliente.getOutputStream()
            );
            // Flujo desde el servidor
            BufferedReader entradaDesdeServidor = new BufferedReader(
                new InputStreamReader(socketCliente.getInputStream())
            );

            // Lee la entrada del usuario
            String frase = entradaDesdeUsuario.readLine();
            // Escribe al fujo hacia el servidor
            salidaAServidor.writeBytes(frase + '\n');
            // Recibe desde el servidor
            String fraseDesdeElServidor = entradaDesdeServidor.readLine();

            System.out.println("DEL SERVIDOR: " + fraseDesdeElServidor);
            socketCliente.close();
        } catch (Exception ex) {
            System.err.println(ex);
        }        
    }

}