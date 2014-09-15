package controle.almoco.controller;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import controle.almoco.manager.FuncionarioManager;
import controle.almoco.model.Funcionario;

@Named
@RequestScoped
public class FuncionarioController {

	@Inject
    private FuncionarioManager funcionarioManager;
 
    private static final String CRIAR_FUNCIONARIO = "criarFuncionario";
 
    private Funcionario funcionario;
 
    public Funcionario getFuncionario() {
 
        if(funcionario == null){
            funcionario = new Funcionario();
        }
 
        return funcionario;
    }
 
    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
 
    public List<Funcionario> getAllFuncionarios() {
        return funcionarioManager.findAll();
    }
 
    public String salvarFuncionario(){
        try {
            funcionarioManager.save(funcionario);
        } catch (Exception e) {
            sendErrorMessageToUser("Error. Check if the weight is above 0 or call the adm");
            return null;
        }       
 
        return "sucesso";
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
}
