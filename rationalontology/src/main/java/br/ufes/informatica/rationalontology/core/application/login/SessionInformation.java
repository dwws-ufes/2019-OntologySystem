package br.ufes.informatica.rationalontology.core.application.login;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import br.ufes.informatica.rationalontology.core.domain.Ontology;
import br.ufes.informatica.rationalontology.core.domain.SubOntology;
import br.ufes.informatica.rationalontology.core.domain.User;
  
  
public class SessionInformation {
     
    private static SessionInformation instance;
    private static User usuarioLogado;
     
    public static SessionInformation getInstance(){
         if (instance == null){
             instance = new SessionInformation();
         }
         return instance;
    }
     
    private SessionInformation(){
          
    }
     
    private ExternalContext currentExternalContext(){
         if (FacesContext.getCurrentInstance() == null){
             throw new RuntimeException("O FacesContext não pode ser chamado fora de uma requisição HTTP");
         }else{
             return FacesContext.getCurrentInstance().getExternalContext();
         }
    }
     
    public User getUsuarioLogado(){
         return (User) getAttribute("usuarioLogado");
    }
    
    public User getUsuarioLogado2() {
    	return usuarioLogado;
    }
     
    public void setUsuarioLogado(User usuario){
        setAttribute("usuarioLogado", usuario);
        usuarioLogado = usuario;
    }
    
    public Ontology getOntology(){
        return (Ontology) getAttribute("ontology");
    }
    
    public void setOntology(Ontology ontology){
       setAttribute("ontology", ontology);
    }
    
    public SubOntology getSubOntology(){
        return (SubOntology) getAttribute("subOntology");
    }
    
    public void setSubOntology(SubOntology subOntology){
       setAttribute("subOntology",subOntology);
    }
     
    public void encerrarSessao(){
    	instance = null;
        currentExternalContext().invalidateSession();
    }
     
    public Object getAttribute(String nome){
         return currentExternalContext().getSessionMap().get(nome);
    }
     
    public void setAttribute(String nome, Object valor){
         currentExternalContext().getSessionMap().put(nome, valor);
    }
     
}