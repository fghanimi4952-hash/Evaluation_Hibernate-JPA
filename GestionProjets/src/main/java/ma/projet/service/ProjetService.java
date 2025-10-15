package ma.projet.service;

import ma.projet.classes.Projet;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.util.List;

public class ProjetService implements IDao<Projet> {

    @Override
    public void create(Projet o) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(o);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(Projet o) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(o);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(Projet o) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(o);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Projet findById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Projet p = session.get(Projet.class, id);
        session.close();
        return p;
    }

    @Override
    public List<Projet> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Projet> list = session.createQuery("from Projet", Projet.class).list();
        session.close();
        return list;
    }

    //  1⃣ Afficher la liste des tâches planifiées pour un projet
    public List<Object[]> getTachesPlanifiees(int projetId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Object[]> query = session.createQuery(
                "select t.nom, t.dateDebut, t.dateFin " +
                        "from Tache t where t.projet.id = :id", Object[].class);
        query.setParameter("id", projetId);
        List<Object[]> results = query.list();
        session.close();
        return results;
    }

    //  2️ Afficher la liste des tâches réalisées avec leurs dates réelles
    public List<Object[]> getTachesRealisees(int projetId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Object[]> query = session.createQuery(
                "select t.nom, et.dateDebutReelle, et.dateFinReelle " +
                        "from EmployeTache et join et.tache t " +
                        "where t.projet.id = :id", Object[].class);
        query.setParameter("id", projetId);
        List<Object[]> results = query.list();
        session.close();
        return results;
    }
}
