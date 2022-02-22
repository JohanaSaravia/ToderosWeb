package controlador;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import toderos.Login;

@ManagedBean
@SessionScoped
public class LoginControlador {

	private static final String PERSISTENCE_UNIT_NAME = "toderos";
	private static EntityManagerFactory factory;

	private Login login;

	public LoginControlador() {
		this.login = new Login();
	}

	public String ingresar() {

		String paginaRetorno = "inicio.xhtml";

		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();

		Login loginDB = new Login();

		loginDB = em.find(Login.class, this.login.getUsuario());
		
		if(loginDB.getTipoUsuario().equals(this.login.getTipoUsuario())) {
			
		if (loginDB.getContrasenia().equals(this.login.getContrasenia())
				&& loginDB.getTipoUsuario().equals("Trabajador")) {

			paginaRetorno = "CrearSolicitud.xhtml";

		} else if (loginDB.getContrasenia().equals(this.login.getContrasenia())
				&& loginDB.getTipoUsuario().equals("Administrador")) {
			
			paginaRetorno = "CrearSolicitud.xhtml";
			
		}else if (loginDB.getContrasenia().equals(this.login.getContrasenia())
				&& loginDB.getTipoUsuario().equals("Cliente")) {

			paginaRetorno = "CrearSolicitud.xhtml";
		}
		
		}
		em.close();

		return paginaRetorno;

	}

	public String registro() {
		String tipo = "inicio.xhtml";

		if (login.getTipoUsuario().equals("")) {

		} else if (login.getTipoUsuario().equals("Trabajador")) {

			tipo = "RegistroTrabajador.xhtml";

		} else if (login.getTipoUsuario().equals("Administrador")) {

			tipo = "RegistroAdministrador.xhtml";

		} else if (login.getTipoUsuario().equals("Cliente")) {

			tipo = "RegistroCliente.xhtml";
		}

		return tipo;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

}
