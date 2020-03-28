
public class HelloWorld {
    public static void main(String[] args) {
        String nombre = "World";
        if (args.length > 0) {
            nombre = args[0];
        }

        System.out.println("Hello " + nombre + "!");
    }
}