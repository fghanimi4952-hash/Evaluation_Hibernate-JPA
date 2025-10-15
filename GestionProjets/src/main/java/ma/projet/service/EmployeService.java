package ma.projet.service;

import ma.projet.classes.Employe;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import java.util.List;

public class EmployeService implements IDao<Employe> {
    @Override
    public void create(Employe o) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(o);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Employe> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Employe> list = session.createQuery("from Employe", Employe.class).list();
        session.close();
        return list;
    }

    // autres méthodes (update, delete, etc.)
}
