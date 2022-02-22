package controlador;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import toderos.Servicios;
import toderos.Solicitud;

@ManagedBean
@SessionScoped
public class ControladorListaSolicitudes {

	private static final String PERSISTENCE_UNIT_NAME = "toderos";
	private static EntityManagerFactory factory;
	
	private Solicitud solicitud;
	private List<Solicitud> result;
	
	
	public ControladorListaSolicitudes() {
		super();
		this.listadesolicitudes();
		// TODO Auto-generated constructor stub
	}
	
	public List listadesolicitudes() {
		result = new ArrayList<Solicitud>();
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery("Select lista FROM Solicitud lista");
		result = query.getResultList();
		System.out.println(result.toString());
		em.getTransaction().commit();
		em.close();
		
		return result;
		
	}
	
	

	public List<Solicitud> getResult() {
		return result;
	}

	public void setResult(List<Solicitud> result) {
		this.result = result;
	}

	public Solicitud getSolicitud() {
		return solicitud;
	}

	public void setSolicitud(Solicitud solicitud) {
		this.solicitud = solicitud;
	}
}

