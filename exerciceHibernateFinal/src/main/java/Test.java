import java.util.List;

import web.dao.Service;
import web.metier.ServiceImp;
import web.model.Etudiant;

public class Test {
	public static void main(String[] args) {
        ServiceImp service = new ServiceImp();
        //service.addEtudiant(new Etudiant("Debaghi","Hamza",22));
		
		  List<Etudiant> l = service.listEtudiant(); for(Etudiant e : l)
		  System.out.println(e.getNom());
		 
        
        System.out.println("id = 1 de "+service.getEtudiantById(1L).getNom());
	}
}
