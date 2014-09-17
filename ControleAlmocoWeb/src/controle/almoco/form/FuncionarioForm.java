package controle.almoco.form;

import java.util.ArrayList;
import java.util.List;

import controle.almoco.model.Lotacao;

public class FuncionarioForm {
	private Integer id;
	private String nome;
	private String funcao;
	private String email;
	private List<Lotacao> listaLotacoes = new ArrayList<Lotacao>();
	private Integer idLotacaoSelecionada;
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getFuncao() {
		return funcao;
	}
	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public List<Lotacao> getListaLotacoes() {
		return listaLotacoes;
	}
	public void setListaLotacoes(List<Lotacao> listaLotacoes) {
		this.listaLotacoes = listaLotacoes;
	}
	public Integer getIdLotacaoSelecionada() {
		return idLotacaoSelecionada;
	}
	public void setIdLotacaoSelecionada(Integer idLotacaoSelecionada) {
		this.idLotacaoSelecionada = idLotacaoSelecionada;
	}
}
