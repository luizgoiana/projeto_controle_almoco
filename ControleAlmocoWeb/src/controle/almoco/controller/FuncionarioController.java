package controle.almoco.controller;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;


import javax.inject.Named;

import controle.almoco.form.FuncionarioForm;
import controle.almoco.manager.FuncionarioManager;
import controle.almoco.model.Funcionario;

@Named
@RequestScoped
public class FuncionarioController {

	@Inject
    private FuncionarioManager funcionarioManager;
	
	@Inject
	private FuncionarioForm funcionarioForm;
 
    private static final String CRIAR_FUNCIONARIO = "criarFuncionario";
    private static final String LISTAR_FUNCIONARIOS = "listAllFuncionarios";
     
 
 
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
            sendErrorMessageToUser("Ocorreu um erro em nosso sistema, favor verificar com o administrador");
            return null;
        }       
 
        return LISTAR_FUNCIONARIOS;
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
