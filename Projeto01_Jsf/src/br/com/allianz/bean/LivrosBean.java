package br.com.allianz.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import br.com.allianz.dao.LivrosDao;
import br.com.allianz.models.*;

@ManagedBean (name = "beanLivro")
@RequestScoped
public class LivrosBean {

	private Livro livro;
		
	public LivrosBean() {
		if (livro == null) {
			livro = new Livro();
		}
	}
	
	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}
	
	
	//metodo de ação (action) que, quando executado, retorna o redirecionamento conforme o resultado da execução
	public String incluirLivro() {
		try {
			new LivrosDao().incluirLivro(livro);
			return "sucesso";
		} catch (Exception e) {
			e.printStackTrace();
			
			FacesContext ctxt = FacesContext.getCurrentInstance();
			FacesMessage msg = new FacesMessage();
			msg.setSummary("Erro durante o cadastro:" + e.getMessage());
			msg.setDetail(e.getMessage());
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			ctxt.addMessage(null, msg);
			
			return "erro";
		}
	}
	
	
	//propriedade usad para apresentar a lista dos livros cadastrados
	public List<Livro> getListaLivros() throws Exception {
		
		return new LivrosDao().listarLivros();
		
	}
	
	
}
