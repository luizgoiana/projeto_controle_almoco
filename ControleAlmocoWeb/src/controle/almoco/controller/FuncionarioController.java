package controle.almoco.controller;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import controle.almoco.form.FuncionarioForm;
import controle.almoco.manager.FuncionarioManager;
import controle.almoco.model.Funcionario;
import controle.almoco.util.Constantes;

@Named
@RequestScoped
public class FuncionarioController {

	@Inject
    private FuncionarioManager funcionarioManager;
	
	@Inject
	private FuncionarioForm funcionarioForm;
 
    private static final String CRIAR_FUNCIONARIO = "criarFuncionario";
    private static final String LISTAR_FUNCIONARIOS = "listAllFuncionarios";
    private static final String ALTERAR_FUNCIONARIO = "alterarFuncionario";
     
 
 
    public List<Funcionario> getAllFuncionarios() {
        return funcionarioManager.findAll();
    }
 
    public String salvarFuncionario(){
        Funcionario funcionario = new Funcionario();
        funcionario.setNome(funcionarioForm.getNome());
        funcionario.setFuncao(funcionarioForm.getFuncao());
        funcionario.setEmail(funcionarioForm.getEmail());
    	try {
            funcionarioManager.save(funcionario);
        } catch (Exception e) {
            if(Constantes.EXCEPTION.USUARIO_EXISTENTE.equals(e.getMessage())){
            	sendErrorMessageToUser("Usuário já registrado");            	
            }else{
            	sendErrorMessageToUser("Ocorreu um erro em nossos servidores. Por favor, contate o administrador do sistema.");            	
            }
        }        
    	sendInfoMessageToUser("Funcionário Cadastrado com Sucesso.");  
        return LISTAR_FUNCIONARIOS;
    }
    
    public String preparaEditarUsuario(Integer idFuncionario){
    	Funcionario funcionario = funcionarioManager.findFuncionarioById(idFuncionario);
    	HttpSession session = (HttpSession) getContext().getExternalContext().getSession(false);
    	session.setAttribute("idFuncionario", idFuncionario);
    	session.setAttribute("emailAnterior",funcionario.getEmail());
    	funcionarioForm.setNome(funcionario.getNome());
    	funcionarioForm.setFuncao(funcionario.getFuncao());
    	funcionarioForm.setEmail(funcionario.getEmail());

    	
    	return ALTERAR_FUNCIONARIO;
    }
    
    public String alterarFuncionario(){
    	Funcionario funcionario = populaFuncionario();
    	HttpSession session = (HttpSession) getContext().getExternalContext().getSession(false);
    	try {
    		
            funcionarioManager.update(funcionario, session.getAttribute("emailAnterior").toString());
        } catch (Exception e) {
            if(Constantes.EXCEPTION.USUARIO_EXISTENTE.equals(e.getMessage())){
            	sendErrorMessageToUser("Usuário já registrado");            	
            }else{
            	sendErrorMessageToUser("Ocorreu um erro em nossos servidores. Por favor, contate o administrador do sistema.");            	
            }
        }        
    	sendInfoMessageToUser("Funcionário Alterado com Sucesso.");  
    	
    	return LISTAR_FUNCIONARIOS;
    }
    
    public String excluirFuncionario(Integer idFuncionario) throws Exception{
    	
    	funcionarioManager.delete(idFuncionario, Funcionario.class);
    	
    	sendInfoMessageToUser("Funcionário Excluído com Sucesso.");
    	return LISTAR_FUNCIONARIOS;
    }
    
    public Funcionario populaFuncionario(){
    	Funcionario funcionario = new Funcionario();
    	HttpSession session = (HttpSession) getContext().getExternalContext().getSession(false);
    	funcionario.setId(Integer.parseInt(session.getAttribute("idFuncionario").toString()));
        funcionario.setNome(funcionarioForm.getNome());
        funcionario.setFuncao(funcionarioForm.getFuncao());
        funcionario.setEmail(funcionarioForm.getEmail());
        
        return funcionario;
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

	public FuncionarioForm getFuncionarioForm() {
		return funcionarioForm;
	}

	public void setFuncionarioForm(FuncionarioForm funcionarioForm) {
		this.funcionarioForm = funcionarioForm;
	}
}
