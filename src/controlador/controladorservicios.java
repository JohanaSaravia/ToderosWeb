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

@ManagedBean
@SessionScoped
public class ControladorServicios {

	private static final String PERSISTENCE_UNIT_NAME = "toderos";
	private static EntityManagerFactory factory;

	private Servicios servicios;
	private List<Servicios> result;

	public List<Servicios> getResult() {
		return result;
	}

	public void setResult(List<Servicios> result) {
		this.result = result;
	}

	public ControladorServicios() {
		this.servicios = new Servicios();
		mostrar();
	}

	
	public void mostrar() {

		result = new ArrayList<Servicios>();
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();

		Query query = em.createQuery("Select servicios FROM Servicios servicios");
		result = query.getResultList();
		System.out.println(result);
		em.close();
	}

	public Servicios getServicios() {
		return servicios;
	}

	public void setServicios(Servicios servicios) {
		this.servicios = servicios;
	}

}
