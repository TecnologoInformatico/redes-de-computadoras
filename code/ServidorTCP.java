import java.io.*;
import java.net.*;

/**
 * ServidorTCP
 */
public class ServidorTCP {

    public static void main(String[] args) throws Exception {
        System.out.println("INICIANDO SERVIDOR EN PUERTO 6789");
        System.out.println("---------------------------------");
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
            System.out.println("SE HA RECIBIDO UNA CONEXION");
            String fraseEnMayusculas = fraseCliente.toUpperCase() + '\n';
            System.out.println("Frase del cliente -> " + fraseCliente);
            salidaAlCliente.writeBytes(fraseEnMayusculas);

            if (fraseCliente == "exit") {
                cerrarConexion = true;
            }
        }

        socketBienvenida.close();
    }
    
}
