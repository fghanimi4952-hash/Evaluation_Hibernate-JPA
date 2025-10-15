package ma.projet.util;

import ma.projet.classes.*;
import ma.projet.service.*;
import java.util.*;

public class App {
    public static void main(String[] args) {
        EmployeService employeService = new EmployeService();
        ProjetService projetService = new ProjetService();
        TacheService tacheService = new TacheService();
        EmployeTacheService etService = new EmployeTacheService();

        // 1⃣ Créer un chef
        Employe chef = new Employe();
        chef.setNom("El Amrani");
        chef.setPrenom("Khalid");
        employeService.create(chef);

        // 2️ Créer un projet
        Projet prj = new Projet();
        prj.setNom("Gestion de stock");
        prj.setDateDebut(new GregorianCalendar(2013, Calendar.JANUARY, 14).getTime());
        prj.setChef(chef);
        projetService.create(prj);

        // 3️ Créer des tâches
        Tache t1 = new Tache();
        t1.setNom("Analyse");
        t1.setDateDebut(new GregorianCalendar(2013, Calendar.FEBRUARY, 10).getTime());
        t1.setDateFin(new GregorianCalendar(2013, Calendar.FEBRUARY, 20).getTime());
        t1.setPrix(800);
        t1.setProjet(prj);
        tacheService.create(t1);

        Tache t2 = new Tache();
        t2.setNom("Conception");
        t2.setDateDebut(new GregorianCalendar(2013, Calendar.MARCH, 10).getTime());
        t2.setDateFin(new GregorianCalendar(2013, Calendar.MARCH, 15).getTime());
        t2.setPrix(900);
        t2.setProjet(prj);
        tacheService.create(t2);

        // 4️ Affichage des tâches du projet
        System.out.println("Projet : " + prj.getNom());
        System.out.println("Date début : " + prj.getDateDebut());
        System.out.println("Liste des tâches :");
        for (Tache t : tacheService.getAll()) {
            System.out.println(t.getNom() + " " + t.getDateDebut() + " " + t.getDateFin());
        }
    }
}
