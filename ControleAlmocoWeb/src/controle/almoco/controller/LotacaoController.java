package controle.almoco.controller;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import controle.almoco.form.LotacaoForm;
import controle.almoco.manager.LotacaoManager;
import controle.almoco.model.Lotacao;

@Named
@RequestScoped
public class LotacaoController {

	@Inject
    private LotacaoManager lotacaoManager;
	
	@Inject
	private LotacaoForm lotacaoForm;
 
    private static final String CRIAR_LOTACAO = "criarLotacao"; //foward's
    private static final String LISTAR_LOTACOES = "listAllLotacoes"; //foward's
     
 
 
    public List<Lotacao> findAllLotacoes() {
        return lotacaoManager.findAll();
    }
 
    public String salvarLotacao(){
        Lotacao lotacao = new Lotacao();
        lotacao.setDescricao(lotacaoForm.getDescricao());
        lotacao.setCozinha(lotacaoForm.getCozinha());
       	try {
       		lotacaoManager.save(lotacao);
        } catch (Exception e) {
            //sendErrorMessageToUser("Ocorreu um erro em nosso sistema, favor verificar com o administrador");
            return null;
        }       
 
        return LISTAR_LOTACOES;
    }
 
   
    private FacesContext getContext() {
        FacesContext context = FacesContext.getCurrentInstance();
        return context;
    }

	public LotacaoForm getLotacaoForm() {
		return lotacaoForm;
	}

	public void setLotacaoForm(LotacaoForm lotacaoForm) {
		this.lotacaoForm = lotacaoForm;
	}
}
