package controle.almoco.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="LOTACAO")
public class Lotacao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LOTACAO_SEQ")
	@SequenceGenerator(name = "LOTACAO_SEQ", sequenceName = "LOTACAO_SEQ1", allocationSize = 1)
	@Column(name = "ID_LOTACAO")
	private Integer id;
	
	@Column(name = "DESCRICAO")
	private String descricao;
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
	@JoinColumn(name="ID_FUNCIONARIO", referencedColumnName="ID_FUNCIONARIO")
	private List<Funcionario> funcionarios;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
	@JoinColumn(name="ID_COZINHA", referencedColumnName="ID_COZINHA")
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
	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}
	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}
	public Cozinha getCozinha() {
		return cozinha;
	}
	public void setCozinha(Cozinha cozinha) {
		this.cozinha = cozinha;
	}

}
