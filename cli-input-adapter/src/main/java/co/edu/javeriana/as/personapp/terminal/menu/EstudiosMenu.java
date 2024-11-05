package co.edu.javeriana.as.personapp.terminal.menu;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

import co.edu.javeriana.as.personapp.common.exceptions.InvalidOptionException;
import co.edu.javeriana.as.personapp.terminal.adapter.EstudiosInputAdapterCli;
import co.edu.javeriana.as.personapp.terminal.model.StudyModelCli;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EstudiosMenu {

    private static String DATABASE = "MARIA";
    private static final int OPCION_REGRESAR_MODULOS = 0;
    private static final int PERSISTENCIA_MARIADB = 1;
	private static final int PERSISTENCIA_MONGODB = 2;

    private static final int OPCION_REGRESAR_MOTOR_PERSISTENCIA = 0;
    private static final int OPCION_VER_TODO = 1;
    private static final int OPCION_CREAR = 2;
    private static final int OPCION_ACTUALIZAR = 3;
    private static final int OPCION_BUSCAR = 4;
    private static final int OPCION_ELIMINAR = 5;

    public void iniciarMenu(EstudiosInputAdapterCli estudiosInputAdapterCli, Scanner keyboard) {
        boolean isValid = false;
        do {
            try {
                mostrarMenuMotorPersistencia();
                int opcion = leerOpcion(keyboard);
                switch (opcion) {
                    case OPCION_REGRESAR_MODULOS:
                        isValid = true;
                        break;
                    case PERSISTENCIA_MARIADB:
                        DATABASE = "MARIA";
                        estudiosInputAdapterCli.setStudyOutputPortInjection("MARIA");
                        menuOpciones(estudiosInputAdapterCli,keyboard);
                        break;
                    case PERSISTENCIA_MONGODB:
                        DATABASE = "MONGO";
                        estudiosInputAdapterCli.setStudyOutputPortInjection("MONGO");
                        menuOpciones(estudiosInputAdapterCli,keyboard);
                        break;
                    default:
                        log.warn("La opción elegida no es válida.");
                }
            }  catch (InvalidOptionException e) {
                log.warn(e.getMessage());
            }
        } while (!isValid);
    }

    //TODO: FALTA ESTO
    private void menuOpciones(EstudiosInputAdapterCli estudiosInputAdapterCli, Scanner keyboard) {
        boolean isValid = false;
        String estudio = "";
        do {
            try {
                mostrarMenuOpciones();
                int opcion = leerOpcion(keyboard);
                switch (opcion) {
                    case OPCION_REGRESAR_MOTOR_PERSISTENCIA:
                        isValid = true;
                        break;
                    case OPCION_VER_TODO:
                        // estudiosInputAdapterCli.findAll();
                        break;
                    case OPCION_CREAR:
                        estudio = leerEntidad(keyboard).toString();
                        // estudiosInputAdapterCli.create(leerEntidad(keyboard));
                        break;
                    case OPCION_ACTUALIZAR:
                        estudio = leerEntidad(keyboard).toString();
                        // estudiosInputAdapterCli.update(leerEntidad(keyboard));
                        break;
                    case OPCION_BUSCAR:
                        System.out.println("Ingrese el id del estudio a buscar:");
                        estudio = keyboard.nextLine();
                        // estudiosInputAdapterCli.findById(estudio);
                        break;
                    case OPCION_ELIMINAR:
                        System.out.println("Ingrese el id del estudio a eliminar:");
                        estudio = keyboard.nextLine();
                        // estudiosInputAdapterCli.delete(estudio);
                        break;
                    default:
                        log.warn("La opción elegida no es válida.");
                }
            } catch (InputMismatchException e) {
                log.warn("Solo se permiten números.");
            }
        } while (!isValid);
    }

    private void mostrarMenuOpciones() {
        System.out.println("----------------------");
        System.out.println(OPCION_VER_TODO + " para ver todos los Estudios");
        System.out.println(OPCION_CREAR + " para crear un Estudios");
        System.out.println(OPCION_ACTUALIZAR + " para actualizar un Estudios");
        System.out.println(OPCION_BUSCAR + " para buscar un Estudios");
        System.out.println(OPCION_ELIMINAR + " para eliminar un Estudios");
        System.out.println(OPCION_REGRESAR_MOTOR_PERSISTENCIA + " para regresar");
    }

    private void mostrarMenuMotorPersistencia() {
        System.out.println("----------------------");
        System.out.println(PERSISTENCIA_MARIADB + " para MariaDB");
        System.out.println(PERSISTENCIA_MONGODB + " para MongoDB");
        System.out.println(OPCION_REGRESAR_MODULOS + " para regresar");
    }

    private int leerOpcion(Scanner keyboard) {
        try {
            System.out.print("Ingrese una opción: ");
            return keyboard.nextInt();
        } catch (InputMismatchException e) {
            log.warn("Solo se permiten números.");
            return leerOpcion(keyboard);
        }
    }

    public StudyModelCli leerEntidad(Scanner keyboard) {
        try {
            StudyModelCli StudyModelCli = new StudyModelCli();
            keyboard.nextLine();
            System.out.println("Ingrese la identificación de la persona:");
            StudyModelCli.setIdPerson(keyboard.nextLine());
            System.out.println("Ingrese la identificación de la profesión:");
            StudyModelCli.setIdProfession(keyboard.nextLine());
            System.out.println("Ingrese el nombre de la universidad:");
            StudyModelCli.setUniversityName(keyboard.nextLine());
            StudyModelCli.setGraduationDate(leerFecha(keyboard));
            return StudyModelCli;
        } catch (Exception e) {
            System.out.println("Datos incorrectos, ingrese los datos nuevamente.");
            return leerEntidad(keyboard);
        }
    }

    private LocalDate leerFecha(Scanner keyboard) {
        try {
            System.out.println("Ingrese la fecha de graduación (yyyy-mm-dd):");
            return LocalDate.parse(keyboard.nextLine());
        } catch (Exception e) {
            System.out.println("Fecha incorrecta, ingrese la fecha nuevamente.");
            return leerFecha(keyboard);
        }
    }
    
}
