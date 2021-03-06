package com.jp.hibernate_project;

import com.jp.hibernate_project.connection.EMUtil;
import com.jp.hibernate_project.connection.exceptions.PreexistingEntityException;
import com.jp.hibernate_project.controllers.UserJpaController;
import com.jp.hibernate_project.models.User;
import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author User
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        new NewMain();
    }
    
    private EntityManagerFactory em;
    private Scanner console;

    public NewMain() {
        while (true) {
            console = new Scanner(System.in);
            String[] operaciones = {"Crear", "Mostrar", "Borrar", "Actualizar"};
            String prompt = "";
            for (int i = 0; i < operaciones.length; i++) {
                if (i != 0) {
                    prompt += "\n";
                }
                prompt += (i + 1) + " " + operaciones[i];
            }
            System.out.println(prompt + "\n0 salir");
            System.out.flush();
            int commandNumber = console.nextInt();

            if (commandNumber == 0) {
            }
            switch (commandNumber) {
                case 0:
                    if (em != null) {
                        em.close();
                    }
                    System.out.println("Bye!");
                    System.exit(0);
                    break;
                case 1:
                    crear();
                    break;
                case 2:
                    mostrar();
                    break;
                case (3):
                    borrar();
                    break;
                case (4):
                    actualizar();
                    break;
                default:
                    System.out.println("Error, opcion no valida");
            }
        }

    }

    private void crear() {
        System.out.println("Escriba el nick del nuevo usuario:");
        String userNick = console.next();

        System.out.println("Escriba el nombre del nuevo usuario:");
        String userName = console.next();

        String userPass = "";
        while (true) {

            System.out.println("Escriba la contrase??a del nuevo usuario:");
            userPass = console.next();

            System.out.println("Repita la contrase??a del nuevo usuario:");
            String userPass2 = console.next();

            if (userPass.equals(userPass2)) {
                break;
            }

            // otras validaciones
            System.out.println("Las contrase??as no coinciden");
        }

        //creates a new user
        User newUser = new User(userNick, userName, User.encodePass(userPass));
        newUser.setDocument("sdfghdjfk");

        // Retrieve an application managed entity manager
        em = EMUtil.getFactory();
        UserJpaController controller = new UserJpaController(em);

        try {
            //try {
            controller.create(newUser);
            System.out.println("Usuario Creado Correctamente");
            System.out.println(newUser);
        } catch (PreexistingEntityException ex) {
            System.out.printf("El usuario con Username %s ya existe\n", userNick);
        }

        System.out.println();

    }

    private void mostrar() {
        int opcion;
        do {
            System.out.println("Que desa ver:\n1 Un usario por nombre\n2 Todos los usuarios\n0 volver al menu anterior");
            opcion = console.nextInt();

            if (opcion == 1) {
                System.out.println("Escriba el nombre del usuario a mostrar:");
                String name = console.next();

                break;
            } else if (opcion == 2) {
                em = EMUtil.getFactory();
                UserJpaController controller = new UserJpaController(em);
                List<User> users = controller.findUserEntities();
                users.forEach(user -> {
                    System.out.println(user);
                });
                break;
            } else if (opcion != 0){
                System.out.println("Error, opcion no valida");
            }
        } while (opcion != 1 && opcion != 2 && opcion != 0);

        System.out.println();
        
    }
    

    

    private void borrar() {

    }

    private void actualizar() {

    }

}
