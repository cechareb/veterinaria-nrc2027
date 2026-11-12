package edu.upc.sistemas.veterinarianrc2027.consola;

import edu.upc.sistemas.veterinarianrc2027.modelo.documental.Consulta;
import edu.upc.sistemas.veterinarianrc2027.modelo.documental.HistoriaClinica;
import edu.upc.sistemas.veterinarianrc2027.modelo.relacional.Dueno;
import edu.upc.sistemas.veterinarianrc2027.modelo.relacional.Mascota;
import edu.upc.sistemas.veterinarianrc2027.servicio.DuenoService;
import edu.upc.sistemas.veterinarianrc2027.servicio.HistoriaClinicaService;
import edu.upc.sistemas.veterinarianrc2027.servicio.MascotaService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

@Component
public class ConsolaVeterinaria implements CommandLineRunner {

    private static final DateTimeFormatter FORMATO_FECHA_HORA =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    private final Scanner scanner = new Scanner(System.in);
    private final DuenoService duenoService;
    private final MascotaService mascotaService;
    private final HistoriaClinicaService historiaService;

    public ConsolaVeterinaria(DuenoService duenoService,
                              MascotaService mascotaService,
                              HistoriaClinicaService historiaService) {
        this.duenoService = duenoService;
        this.mascotaService = mascotaService;
        this.historiaService = historiaService;
    }

    @Override
    public void run(String... args) {
        iniciar();
    }

    public void iniciar() {
        boolean salir = false;
        System.out.println("\n=== VETERINARIA ===");

        while (!salir) {
            System.out.println("\n1. Registrar dueño");
            System.out.println("2. Listar dueños");
            System.out.println("3. Registrar mascota");
            System.out.println("4. Listar mascotas");
            System.out.println("5. Registrar consulta médica");
            System.out.println("6. Ver historia clínica");
            System.out.println("0. Salir");

            try {
                int opcion = leerEntero("Seleccione una opción: ");
                switch (opcion) {
                    case 1 -> registrarDueno();
                    case 2 -> listarDuenos();
                    case 3 -> registrarMascota();
                    case 4 -> listarMascotas();
                    case 5 -> registrarConsulta();
                    case 6 -> verHistoriaClinica();
                    case 0 -> {
                        salir = true;
                        System.out.println("Gracias por utilizar el sistema.");
                    }
                    default -> System.out.println("Opción inválida. Intente nuevamente.");
                }
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Ocurrió un error inesperado: " + e.getMessage());
            }
        }
    }

    private void registrarDueno() {
        System.out.println("\nREGISTRAR DUEÑO");
        String nombre = leerTexto("Nombre completo: ");
        String telefono = leerTexto("Teléfono: ");
        String email = leerTexto("Correo electrónico: ");
        Dueno dueno = duenoService.registrar(nombre, telefono, email);
        System.out.println("Dueño registrado con ID " + dueno.getId() + ".");
    }

    private void listarDuenos() {
        List<Dueno> duenos = duenoService.listar();
        if (duenos.isEmpty()) {
            System.out.println("No hay dueños registrados.");
            return;
        }
        System.out.println("\nDUEÑOS");
        for (Dueno dueno : duenos) {
            System.out.println("ID: " + dueno.getId()
                    + " | Nombre: " + dueno.getNombre()
                    + " | Teléfono: " + dueno.getTelefono()
                    + " | Correo: " + dueno.getEmail());
        }
    }

    private void registrarMascota() {
        if (duenoService.listar().isEmpty()) {
            System.out.println("Primero debe registrar un dueño.");
            return;
        }
        System.out.println("\nREGISTRAR MASCOTA");
        String nombre = leerTexto("Nombre: ");
        String especie = leerTexto("Especie: ");
        String raza = leerTexto("Raza: ");
        LocalDate fechaNacimiento = leerFecha("Fecha de nacimiento (AAAA-MM-DD): ");
        listarDuenos();
        Long duenoId = leerLong("ID del dueño: ");
        Mascota mascota = mascotaService.registrar(
                nombre, especie, raza, fechaNacimiento, duenoId);
        System.out.println("Mascota registrada con ID " + mascota.getId() + ".");
    }

    private void listarMascotas() {
        List<Mascota> mascotas = mascotaService.listar();
        if (mascotas.isEmpty()) {
            System.out.println("No hay mascotas registradas.");
            return;
        }
        System.out.println("\nMASCOTAS");
        for (Mascota mascota : mascotas) {
            System.out.println("ID: " + mascota.getId()
                    + " | Nombre: " + mascota.getNombre()
                    + " | Especie: " + mascota.getEspecie()
                    + " | Raza: " + mascota.getRaza()
                    + " | Nacimiento: " + mascota.getFechaNacimiento()
                    + " | Dueño: " + mascota.getDueno().getNombre());
        }
    }

    private void registrarConsulta() {
        if (mascotaService.listar().isEmpty()) {
            System.out.println("Primero debe registrar una mascota.");
            return;
        }
        System.out.println("\nREGISTRAR CONSULTA MÉDICA");
        listarMascotas();
        Long mascotaId = leerLong("ID de la mascota: ");
        String motivo = leerTexto("Motivo: ");
        String diagnostico = leerTexto("Diagnóstico: ");
        String tratamiento = leerTexto("Tratamiento: ");
        historiaService.registrarConsulta(mascotaId, motivo, diagnostico, tratamiento);
        System.out.println("Consulta agregada a la historia clínica en MongoDB.");
    }

    private void verHistoriaClinica() {
        System.out.println("\nHISTORIA CLÍNICA");
        listarMascotas();
        Long mascotaId = leerLong("ID de la mascota: ");
        HistoriaClinica historia = historiaService.buscarPorMascotaId(mascotaId);
        System.out.println("ID del documento MongoDB: " + historia.getId());
        for (Consulta consulta : historia.getConsultas()) {
            System.out.println("\nFecha: " + consulta.getFecha().format(FORMATO_FECHA_HORA));
            System.out.println("Motivo: " + consulta.getMotivo());
            System.out.println("Diagnóstico: " + consulta.getDiagnostico());
            System.out.println("Tratamiento: " + consulta.getTratamiento());
        }
    }

    private String leerTexto(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String valor = scanner.nextLine().trim();
            if (!valor.isEmpty()) {
                return valor;
            }
            System.out.println("Este campo es obligatorio.");
        }
    }

    private int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un número entero.");
            }
        }
    }

    private Long leerLong(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Long.parseLong(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un ID numérico válido.");
            }
        }
    }

    private LocalDate leerFecha(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return LocalDate.parse(scanner.nextLine().trim());
            } catch (DateTimeParseException e) {
                System.out.println("Fecha inválida. Ejemplo: 2022-06-15.");
            }
        }
    }
}
