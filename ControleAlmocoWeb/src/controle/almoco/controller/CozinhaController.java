package controle.almoco.controller;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import controle.almoco.form.CozinhaForm;
import controle.almoco.manager.CozinhaManager;
import controle.almoco.model.Cozinha;

@Named
@RequestScoped
public class CozinhaController {

	@Inject
    private CozinhaManager cozinhaManager;
	
	@Inject
	private CozinhaForm cozinhaForm;
 
    private static final String CRIAR_COZINHA = "criarCozinha";
    private static final String LISTAR_COZINHAS = "listAllCozinhas";
    private static final String ALTERAR_COZINHA = "alterarCozinha";
     
 
 
    public List<Cozinha> getAllCozinhas() {
        return cozinhaManager.findAll();
    }
 
    public String salvarCozinha(){
        Cozinha cozinha = new Cozinha();
        cozinha.setNome(cozinhaForm.getNome());
    	try {
            cozinhaManager.save(cozinha);
        } catch (Exception e) {
        	sendErrorMessageToUser("Ocorreu um erro em nossos servidores. Por favor, contate o administrador do sistema.");   
        }        
    	sendInfoMessageToUser("Cozinha Cadastrado com Sucesso.");  
        return LISTAR_COZINHAS;
    }
    
    public String preparaEditarUsuario(Integer idCozinha){
    	Cozinha cozinha = cozinhaManager.findCozinhaById(idCozinha);
    	HttpSession session = (HttpSession) getContext().getExternalContext().getSession(false);
    	session.setAttribute("idCozinha", idCozinha);
    	cozinhaForm.setNome(cozinha.getNome());
    	
    	return ALTERAR_COZINHA;
    }
    
    public String alterarCozinha(){
    	Cozinha cozinha = populaCozinha();
    	try {
            cozinhaManager.update(cozinha);
        } catch (Exception e) {
        	sendErrorMessageToUser("Ocorreu um erro em nossos servidores. Por favor, contate o administrador do sistema.");            	
        }        
    	sendInfoMessageToUser("Cozinha Alterada com Sucesso.");  
    	
    	return LISTAR_COZINHAS;
    }
    
    public String excluirCozinha(Integer idCozinha) throws Exception{
    	
    	cozinhaManager.delete(idCozinha, Cozinha.class);
    	
    	sendInfoMessageToUser("Cozinha Excluído com Sucesso.");
    	return LISTAR_COZINHAS;
    }
    
    public Cozinha populaCozinha(){
    	Cozinha cozinha = new Cozinha();
    	HttpSession session = (HttpSession) getContext().getExternalContext().getSession(false);
    	cozinha.setId(Integer.parseInt(session.getAttribute("idCozinha").toString()));
        cozinha.setNome(cozinhaForm.getNome());
        
        return cozinha;
    }
 
    private void sendInfoMessageToUser(String message){
        FacesContext context = getContext();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, message));
    }
 
    private void sendErrorMessageToUser(String message){
        FacesContext context = getContext();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message));
    }
 
    private FacesContext getContext() {
        FacesContext context = FacesContext.getCurrentInstance();
        return context;
    }

	public CozinhaForm getCozinhaForm() {
		return cozinhaForm;
	}

	public void setCozinhaForm(CozinhaForm cozinhaForm) {
		this.cozinhaForm = cozinhaForm;
	}
}
