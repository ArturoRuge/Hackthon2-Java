import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Agenda {
    private ArrayList<Contacto> listaContactos;
    private Boolean agendaLlena = false;

    //Constructores
    public Agenda() {

    }

    public Agenda(int numeroMaximoContactos) {
        this.listaContactos = new ArrayList<>(numeroMaximoContactos);
    }

    //Añadir contactos
    public void anadirContacto(Contacto nuevoContacto) {

        if (listaContactos.contains(nuevoContacto)) {
            System.out.println("El contacto " + nuevoContacto.getNombre() + " ya existe.");
        } else {
            listaContactos.add(nuevoContacto);
            System.out.println("El contacto " + nuevoContacto.getNombre() + " ha sido agregado");
        }

    }

    //Buscar contactos y verificar que el contacto existe
    public Contacto buscarContacto(String nombre, String apellido) {

        for (Contacto contacto : listaContactos) {
            if (contacto.getNombre().equalsIgnoreCase(nombre) && contacto.getApellido().equalsIgnoreCase(apellido)) {
                return contacto;
            }
        }
        return null;
    }

    //Mostrar contactos
    public void mostrarContactos() {
        Collections.sort(listaContactos, new Comparator<Contacto>(){
            @Override
            public int compare(Contacto contacto1,Contacto contacto2){
                return contacto1.getNombre().compareToIgnoreCase(contacto2.getNombre());
            }

        });
        for (Contacto contacto : listaContactos) {
            System.out.println("Nombre: " + contacto.getNombre() + "\n" +
                    "Apellido: " + contacto.getApellido() + "\n" +
                    "Número de teléfono: " + contacto.getTelefono());
        }
    }

    //Eliminar contactos
    public Contacto eliminarContacto(String nombre, String apellido) {
        Contacto contacto = buscarContacto(nombre, apellido);
        if (contacto != null) {
            listaContactos.remove(contacto);
            return contacto;
        }
        return null;
    }

    //Modificar el teléfono
    public Contacto modificarContacto(String nombre, String apellido, int nuevoTelefono) {
        Contacto contacto = buscarContacto(nombre, apellido);
        if (contacto != null) {
            contacto.setTelefono(nuevoTelefono);
            return contacto;
        }
        return null;
    }

    //Mostrar espacios disponibles
    public int espaciosDisponibles(Agenda agenda, int numeroMaximoContactos) {
        int cantidadEspacios = agenda.listaContactos.size();
        int numMaximoContactos = numeroMaximoContactos;
        return numMaximoContactos - cantidadEspacios;
    }

    //Agenda llena
    public boolean agendaLlena(Agenda agenda, int numeroMaximoContactos) {
        int cantidadEspacios = agenda.listaContactos.size();
        int numMaximoContactos = numeroMaximoContactos;
        int espaciosDisponibles = numMaximoContactos - cantidadEspacios;

        if (espaciosDisponibles == 0) {
            agendaLlena = true;
            return agendaLlena;
        }
        return agendaLlena;
    }

    //Verificación de contacto
    public boolean existeContacto(String nombre, String apellido) {

        for (Contacto contacto : listaContactos) {
            if (contacto.getNombre().equalsIgnoreCase(nombre) && contacto.getApellido().equalsIgnoreCase(apellido)) {
                return true;
            }
        }
        return false;
    }
}






