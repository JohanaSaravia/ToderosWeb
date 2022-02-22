package controlador;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import toderos.Administrador;
import toderos.Login;

@ManagedBean
@SessionScoped
public class ControladorRegistroAdministrador {

	private static final String PERSISTENCE_UNIT_NAME = "toderos";
	private static EntityManagerFactory factory;

	private Login login;
	private Administrador administrador;

	public ControladorRegistroAdministrador() {
		this.administrador = new Administrador();
		this.login = new Login();
	}
	
	
	public void registroAdministrador() {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		login.setUsuario(administrador.getCodigoAdministrador());
		login.setTipoUsuario("Trabajador");
		
		em.persist(this.administrador);
		em.persist(this.login);
		em.getTransaction().commit();
		em.close();
			
	}
	

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public Administrador getAdministrador() {
		return administrador;
	}

	public void setAdministrador(Administrador administrador) {
		this.administrador = administrador;
	}

}
