package web.metier;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import generique.HibernateUtil;
import web.model.Etudiant;

public class ServiceImp implements ServiceInterface {

	public ServiceImp() {

	}

	@Override
	public HashMap<String, String> verifierFormulaire(HttpServletRequest req, HttpServletResponse resp) {

		HashMap<String, String> m = new HashMap<String, String>();
		
	
		if (!req.getParameter("nom").matches("[a-zA-Z]+\s?-?[a-zA-Z]+"))
			m.put("nom", "saisir les caractères");

		if (!req.getParameter("prenom").matches("[a-zA-Z]+"))
			m.put("prenom", "saisir les caractères");

		if (!req.getParameter("age").matches("[0-9]{1,3}"))
			m.put("age", "saisir un age correct");

		return m;

	}

	@Override
	public void addEtudiant(Etudiant e) {
		System.out.println("bonjour");
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		try {
			session.save(e);
			session.getTransaction().commit();
		} catch (Exception ex) {
			session.getTransaction().rollback();
		}
		session.close();
	}

	@Override
	public List<Etudiant> listEtudiant() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		List<Etudiant> l = session.createQuery("from Etudiant").list();

		session.getTransaction().commit();
		session.close();
		return l;
	}

	@Override
	public Etudiant getEtudiantById(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		Etudiant e = session.load(Etudiant.class, id);
		session.getTransaction().commit();
		
		session.close();
		return e;
	}

	@Override
	public void removeEtudiant(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		try {
			Etudiant e = getEtudiantById(id);
			session.remove(e);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
		}
		session.close();

	}

	@Override
	public void updateEtudiantById(Etudiant e) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		System.out.println("bonjour update");
		
		session.update(e);
		session.getTransaction().commit();
		
		session.close();
	}

}
