package web.metier;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.model.Etudiant;

public interface ServiceInterface {

	 public HashMap<String,String> verifierFormulaire(HttpServletRequest req,HttpServletResponse resp);
	 public void addEtudiant(Etudiant e);
	 public List<Etudiant> listEtudiant();
	 public Etudiant getEtudiantById(Long id);
	 public void removeEtudiant(Long id);
	 public void updateEtudiantById(Etudiant e);
}
