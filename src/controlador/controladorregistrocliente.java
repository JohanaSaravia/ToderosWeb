package controlador;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import toderos.Cliente;
import toderos.Login;

@ManagedBean
@SessionScoped
public class ControladorRegistroCliente {

	private static final String PERSISTENCE_UNIT_NAME = "toderos";
	private static EntityManagerFactory factory;

	private Cliente cliente;
	private Login login;

	public ControladorRegistroCliente() {
		this.cliente = new Cliente();
		this.login = new Login();
	}

	public String registroCliente() {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();

		login.setUsuario(cliente.getCodigoCliente());
		login.setTipoUsuario("Cliente");

		em.persist(this.cliente);
		em.persist(this.login);
		em.getTransaction().commit();
		em.close();
		return "PerfilCliente.xhtml";
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

}