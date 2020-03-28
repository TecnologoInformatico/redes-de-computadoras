import java.io.*;
import java.net.*;

/**
 * ServidorTCP
 */
public class ServidorTCP {

    public static void main(String[] args) throws Exception {
        // Se crea un socket para recibir conexiones (de bienvenida)
        ServerSocket socketBienvenida = new ServerSocket(6789);
        boolean cerrarConexion = false;

        while (!cerrarConexion) {
            // Se acepta la conexión entrante
            Socket socketConexion = socketBienvenida.accept();
            // datos salientes del socket de conexión
            BufferedReader entradaDesdeCliente = new BufferedReader(
                new InputStreamReader(
                    socketConexion.getInputStream()
                )
            );
            // Salida al cliente
            DataOutputStream salidaAlCliente = new DataOutputStream(
                socketConexion.getOutputStream()
            );

            String fraseCliente = entradaDesdeCliente.readLine();
            String fraseEnMayusculas = fraseCliente.toUpperCase() + '\n';
            salidaAlCliente.writeBytes(fraseEnMayusculas);

            if (fraseCliente == "exit") {
                cerrarConexion = true;
            }
        }

        socketBienvenida.close();
    }
    
}
