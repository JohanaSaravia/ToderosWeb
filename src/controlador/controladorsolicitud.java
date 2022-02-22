package controlador;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import toderos.Administrador;
import toderos.Cliente;
import toderos.Login;
import toderos.Servicios;
import toderos.Solicitud;
import toderos.Trabajador;

@ManagedBean
@SessionScoped
public class ControladorSolicitud {

	private static final String PERSISTENCE_UNIT_NAME = "toderos";
	private static EntityManagerFactory factory;
	private List<Servicios> listaservicios;
	ArrayList<String> listastring = new ArrayList<String>();
	private List<Cliente> result;

	private Cliente cliente;
	private Login login;
	private Solicitud solicitud;
	private Administrador administrador;
	private Trabajador trabajador;
	private Servicios servicio;

	public List<String> listadeservicios() {

		listaservicios = new ArrayList<Servicios>();
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		String jpql = "SELECT pro FROM Servicios pro";
		Query query = em.createQuery(jpql);
		listaservicios = query.getResultList();
		System.out.println("AQUI...: " + listaservicios.size());
		for (int i = 0; i < listaservicios.size(); i++) {
			listastring.add(listaservicios.get(i).getNombre());
			System.out.println(listastring.get(i));
		}
		listaservicios.get(0).getNombre();
		System.out.println(listaservicios);
		em.close();
		return listastring;
	}

	public void crearSolicitud(Cliente cliente) {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		System.out.println(cliente.getCelular());
		this.solicitud.setCliente(cliente);
		this.solicitud.setAdministrador(null);
		this.solicitud.setTrabajador(null);
		this.solicitud.setFecha("");
		em.persist(this.solicitud);
		em.getTransaction().commit();
		em.close();
	}

	public ControladorSolicitud() {
		this.cliente = new Cliente();
		this.login = new Login();
		this.administrador = new Administrador();
		this.trabajador = new Trabajador();
		this.setSolicitud(new Solicitud());
		this.listadeservicios();
	}

	public datosCliente(Login login) {
		result = new ArrayList<Cliente>();
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createQuery("Select cliente FROM Cliente cliente WHERE cliente.codigoCliente = :codigo");
		query.setParameter("codigo", login.getUsuario());
		result = query.getResultList();
		em.close();		
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

	public Solicitud getSolicitud() {
		return solicitud;
	}

	public void setSolicitud(Solicitud solicitud) {
		this.solicitud = solicitud;
	}

	public Administrador getAdministrador() {
		return administrador;
	}

	public void setAdministrador(Administrador administrador) {
		this.administrador = administrador;
	}

	public Trabajador getTrabajador() {
		return trabajador;
	}

	public void setTrabajador(Trabajador trabajador) {
		this.trabajador = trabajador;
	}

	public ArrayList<String> getListastring() {
		return listastring;
	}

	public void setListastring(ArrayList<String> listastring) {
		this.listastring = listastring;
	}

	public List<Servicios> getListaservicios() {
		return listaservicios;
	}

	public void setListaservicios(List<Servicios> listaservicios) {
		this.listaservicios = listaservicios;
	}

	public Servicios getServicio() {
		return servicio;
	}

	public void setServicio(Servicios servicio) {
		this.servicio = servicio;
	}

	public List<Cliente> getResult() {
		return result;
	}

	public void setResult(List<Cliente> result) {
		this.result = result;
	}
	
}
