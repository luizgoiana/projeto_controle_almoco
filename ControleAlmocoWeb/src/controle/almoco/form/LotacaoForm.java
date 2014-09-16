package controle.almoco.form;

import controle.almoco.model.Cozinha;

public class LotacaoForm {
	private Integer id;
	private String descricao;
	private Cozinha cozinha;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Cozinha getCozinha() {
		return cozinha;
	}
	public void setCozinha(Cozinha cozinha) {
		this.cozinha = cozinha;
	}
	
	
	
}
