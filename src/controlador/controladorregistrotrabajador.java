package controlador;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import toderos.HojaDeVida;
import toderos.Login;
import toderos.Trabajador;

@ManagedBean
@SessionScoped
public class ControladorRegistroTrabajador {

	private static final String PERSISTENCE_UNIT_NAME = "toderos";
	private static EntityManagerFactory factory;
	
	private Trabajador trabajador;
	private Login login;
	private HojaDeVida hojadevida;
	

	
	public ControladorRegistroTrabajador() {
		this.trabajador = new Trabajador();
		this.login = new Login();
		this.hojadevida = new HojaDeVida();
	}
	
	public void registroTrabajador() {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String dateString = format.format( new Date()   );
		
		System.out.println(dateString);
		
		login.setUsuario(trabajador.getUsuarioTrabajador());
		login.setTipoUsuario("Trabajador");
		hojadevida.setTrabajador(trabajador);
		hojadevida.setFecha(dateString);
		System.out.println();
		
		em.persist(this.trabajador);
		em.persist(this.login);
		em.persist(this.hojadevida);
		em.getTransaction().commit();
		em.close();
			
	}

	public Trabajador getTrabajador() {
		return trabajador;
	}

	public void setTrabajador(Trabajador trabajador) {
		this.trabajador = trabajador;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public HojaDeVida getHojadevida() {
		return hojadevida;
	}

	public void setHojadevida(HojaDeVida hojadevida) {
		this.hojadevida = hojadevida;
	}
	
	
	
	
}
