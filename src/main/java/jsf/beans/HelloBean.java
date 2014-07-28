package jsf.beans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Definição de um ManagedBean JSF (classe de controle que tem acesso direto ao
 * arquivo XHTML).
 * 
 * @author Michel Risucci
 */
@ManagedBean
@ViewScoped
public class HelloBean {

	private String message;

	/**
	 * Construtor
	 */
	public HelloBean() {
	}

	/**
	 * Método chamada após o construtor (respeite o padrão "Bean", deixe o
	 * construtor VAZIO)
	 */
	@PostConstruct
	public void init() {
		message = "\"message\" property visible via GET/SET";
	}

	/**
	 * Método público qualquer, a ser chamado sem parâmetros
	 * 
	 * @return
	 */
	public String sayHello() {
		return "Hello World from Managed Bean!";
	}

	/**
	 * Método público qualquer, a ser chamado com parâmetros
	 * 
	 * @return
	 */
	public String sayHelloPrimefaces(String custom) {
		return "Your custom message: " + custom;
	}

	/**
	 * @return message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 */
	public void setMessage(String message) {
		this.message = message;
	}
}