package web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.internal.build.AllowSysOut;

import web.metier.ServiceImp;
import web.metier.ServiceInterface;
import web.model.Etudiant;

@WebServlet(name = "jar", urlPatterns = { "*.jee" })

public class WebServlet5 extends HttpServlet {

	private ServiceInterface serviceInterface;

	@Override
	public void init() throws ServletException {
		serviceInterface = new ServiceImp();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		System.out.println(path);

		if (path.equals("/index.jee")) {
			req.getRequestDispatcher("accueil.jsp").forward(req, resp);
		} else if (path.equals("/listEtudiant.jee")) {
			List<Etudiant> l = serviceInterface.listEtudiant();
			req.setAttribute("listEtudiant", l.toArray());
			req.getRequestDispatcher("etudiants.jsp").forward(req, resp);
		} else if (path.equals("/chercher.jee") && req.getMethod().equals("GET")) {
			List<Etudiant> list = serviceInterface.listEtudiant();
			List<Etudiant> listFinal = new ArrayList<Etudiant>();
			String rech = req.getParameter("search");
			System.out.println("test = " + rech);

			for (Etudiant e : list) {
				String chaine = e.getNom() + e.getPrenom() + e.getAge();
				if (chaine.contains(rech))
					listFinal.add(e);
			}

			req.setAttribute("listEtudiant", listFinal.toArray());
			req.getRequestDispatcher("etudiants.jsp").forward(req, resp);
		} else if (path.equals("/supprime.jee")) {
			Long id = Long.parseLong(req.getParameter("codeE"));
			serviceInterface.removeEtudiant(id);
			resp.sendRedirect("/exerciceHibernateFinal/listEtudiant.jee");
		} else if (path.equals("/modifie.jee")) {
			Long id = Long.parseLong(req.getParameter("codeE"));
			Etudiant e = serviceInterface.getEtudiantById(id);
			req.setAttribute("etudiant", e);
			req.getRequestDispatcher("verifierFormulaire.jsp").forward(req, resp);
		} else if (path.equals("/ajouteEtudiant.jee")) {
			System.out.println("Bonjour");
			HashMap<String, String> m = new HashMap<String, String>();
			m.put("nom", "");
			m.put("prenom", "");
			m.put("age", "");

			req.setAttribute("erreur", m);
			req.getRequestDispatcher("formulaire.jsp").forward(req, resp);
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String path = req.getServletPath();

		HashMap<String, String> m = serviceInterface.verifierFormulaire(req, resp);

		req.setAttribute("erreur", m);
		System.out.println("\n\n size : " + m.size());
		System.out.println("\n\n");
		if (m.size() != 0)
			req.getRequestDispatcher("formulaire.jsp").forward(req, resp);
		else if (path.equals("/modifie.jee")) {

			Long id = Long.parseLong(req.getParameter("codeE"));
			System.out.println("test : " + id);
			Etudiant e = serviceInterface.getEtudiantById(id);
			e.setNom(req.getParameter("nom"));
			e.setPrenom(req.getParameter("prenom"));
			e.setAge(Integer.parseInt(req.getParameter("age")));

			serviceInterface.updateEtudiantById(e);

			/*
			 * List<Etudiant> l = serviceInterface.listEtudiant();
			 * req.setAttribute("listEtudiant", l.toArray());
			 * req.getRequestDispatcher("etudiants.jsp").forward(req, resp);
			 */

			resp.sendRedirect("/exerciceHibernateFinal/listEtudiant.jee");

		} else if (m.size() == 0) {
			Etudiant e = new Etudiant(req.getParameter("nom"), req.getParameter("prenom"),
					Integer.parseInt(req.getParameter("age")));
			System.out.println("\n\n test " + e.getNom());
			System.out.println("\n\n");
			serviceInterface.addEtudiant(e);

			/*
			 * List<Etudiant> l = serviceInterface.listEtudiant();
			 * req.setAttribute("listEtudiant", l.toArray());
			 * 
			 * req.getRequestDispatcher("etudiants.jsp").forward(req, resp);
			 */

			resp.sendRedirect("/exerciceHibernateFinal/listEtudiant.jee");
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}

}
