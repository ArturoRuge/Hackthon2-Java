import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean siguiente = true;
        int numeroMaximoContactos = 0;

        System.out.println("¡Bienvenido a tu Agenda de Contactos!");
        while (siguiente) {
            System.out.println("¿De cuántos contactos quieres crear tu agenda?\n" +
                    "Ingresa un número de contactos entre 1 y 1000: ");
            int cantidadContactos = scanner.nextInt();

            if (cantidadContactos < 1 || cantidadContactos > 1000) {
                System.out.println("Selecciona una cantidad válida");

            } else {
                numeroMaximoContactos = cantidadContactos;

                siguiente = false;
            }
        }
        //Instancia de la clase agenda
        Agenda agenda = new Agenda(numeroMaximoContactos);


        //Crear los contactos
        agenda.anadirContacto(new Contacto("Camila", "Jiménez", 1234567890));
        agenda.anadirContacto(new Contacto("Amer", "Abdal", 876543210));
        agenda.anadirContacto(new Contacto("Andrea", "Antolínez", 1237894560));
        agenda.anadirContacto(new Contacto("A", "A", 123));

        int opcionEscogida = 8;

        do {
            //Menú interactivo
            System.out.println("----------------------------------------------------------------\n" +
                    "Escoge una opción para comenzar: \n" +
                    "\n" +
                    "1: Agregar un nuevo contacto\n" +
                    "2: Verificar si el contacto existe en tu agenda\n" +
                    "3: Buscar un contacto de tu agenda\n" +
                    "4: Mostrar los contactos de tu agenda\n" +
                    "5: Eliminar un contacto de la agenda\n" +
                    "6: Modificar el teléfono de un contacto de tu agenda\n" +
                    "7: Mostrar espacios disponibles en la agenda\n" +
                    "0: Salir\n" +
                    "----------------------------------------------------------------\n" +
                    "Escribe tu opción: ");

            try {
                opcionEscogida = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Por favor, ingresa un número válido.");
                scanner.nextLine(); // Esto es para limpiar el buffer
                continue; // Esto es para volver a mostrar el menú
            }


            if (opcionEscogida > 7 || opcionEscogida < 0) {
                System.out.println("Selecciona una opción válida del menú");
            } else {
                scanner.nextLine();
                switch (opcionEscogida) {
                    case 1: //Para agregar un nuevo contacto
                        if (!agenda.agendaLlena(agenda, numeroMaximoContactos)) {

                            System.out.print("Digita el nombre del contacto a agregar: ");

                            String nombre = scanner.nextLine();
                            System.out.print("Digita el apellido del contacto a agregar: ");
                            String apellido = scanner.nextLine();

                            System.out.print("Digita el número de teléfono del contacto a agregar: ");
                            int numeroTelefono = scanner.nextInt();

                            if (agenda.existeContacto(nombre, apellido)) {
                                System.out.println("El contacto ya existe en la agenda.");
                            } else {
                                agenda.anadirContacto(new Contacto(nombre, apellido, numeroTelefono));
                            }

                        } else {
                            System.out.println("No es posible agregar el contacto, tu agenda está llena.");
                        }
                        break;
                    case 2: //Para verificar si el contacto existe
                        System.out.print("Digita el nombre del contacto que deseas verificar que existe: ");
                        String nombreVerificar = scanner.nextLine();

                        System.out.print("Digita el apellido del contacto que deseas verificar que existe: ");
                        String apellidoVerificar = scanner.nextLine();
                        Contacto contactoVerificado = agenda.buscarContacto(nombreVerificar, apellidoVerificar);
                        if (contactoVerificado != null) {
                            System.out.println("El contacto: " + contactoVerificado.getNombre() + " " + contactoVerificado.getApellido() + " si existe.");
                        } else {
                            System.out.println("El contacto: " + nombreVerificar + " " + apellidoVerificar + " no existe.");
                        }
                        break;

                    case 3: //Para buscar un contacto
                        System.out.print("Digita el nombre del contacto que deseas buscar: ");
                        String nombreBuscar = scanner.nextLine();

                        System.out.print("Digita el apellido del contacto que deseas buscar: ");
                        String apellidoBuscar = scanner.nextLine();
                        Contacto contactoEncontrado = agenda.buscarContacto(nombreBuscar, apellidoBuscar);
                        if (contactoEncontrado != null) {
                            System.out.println("El teléfono del contacto encontrado es: " + contactoEncontrado.getTelefono());
                        } else {
                            System.out.println("No se encontró el contacto: " + nombreBuscar + " " + apellidoBuscar);
                        }
                        break;
                    case 4: //Para mostrar los contactos de tu agenda
                        agenda.mostrarContactos();
                        break;
                    case 5://Para eliminar un contacto
                        System.out.print("Digita el nombre del contacto a eliminar: ");
                        String nombreAEliminar = scanner.nextLine();

                        System.out.print("Digita el apellido del contacto a eliminar: ");
                        String apellidoAEliminar = scanner.nextLine();

                        Contacto contactoAEliminar = agenda.eliminarContacto(nombreAEliminar, apellidoAEliminar);

                        if (contactoAEliminar != null) {
                            System.out.println("El contacto \n" +
                                    contactoAEliminar.getNombre() + "  \n " +
                                    contactoAEliminar.getApellido() + " \n " +
                                    contactoAEliminar.getTelefono() + "\n" +
                                    "ha sido eliminado.");
                        } else {
                            System.out.println("No se encontró el contacto: " + nombreAEliminar + " " + apellidoAEliminar);
                        }
                        break;
                    case 6: //Para modificar el teléfono de un contacto
                        System.out.print("Digita el nombre del contacto a modificar: ");
                        String nombreModificarTel = scanner.nextLine();

                        System.out.print("Digita el apellido del contacto a modificar: ");
                        String apellidoModificarTel = scanner.nextLine();

                        System.out.print("Digita el nuevo número de teléfono: ");
                        int TelefonoAModificar = scanner.nextInt();

                        Contacto contactoAModificar = agenda.modificarContacto(nombreModificarTel, apellidoModificarTel, TelefonoAModificar);

                        if (contactoAModificar != null) {
                            System.out.println("El contacto \n" +
                                    contactoAModificar.getNombre() + "  \n " +
                                    contactoAModificar.getApellido() + " \n " +
                                    contactoAModificar.getTelefono() + "\n" +
                                    "ha sido modificado.");
                        } else {
                            System.out.println("No se encontró el contacto: " + nombreModificarTel + " " + apellidoModificarTel);
                        }
                        break;
                    case 7: //Para mostrar espacios disponibles
                        System.out.println("La cantidad de espacios disponibles es: " + agenda.espaciosDisponibles(agenda, numeroMaximoContactos));
                        break;
                    case 0: //Para salir del menú
                        System.out.println("¡Finalizaste tu agenda!");
                        break;
                    default:
                        System.out.println("Por favor digita una opción válida según el menú.");
                        break;
                }
            }
        } while (opcionEscogida != 0);
        scanner.close();
    }
}

