package br.com.senior.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import lombok.Data;

@Data
@Entity
public class ItensPedido implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name= "ITENS_PEDIDO_SEQUENCE", sequenceName = "ITENS_PEDIDO_SEQUENCE_ID", initialValue=1, allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ITENS_PEDIDO_SEQUENCE")
    private Long id;
	
	@Column(nullable = false,  columnDefinition = "boolean default true")  
	private boolean status;
	
	@OneToMany(fetch = FetchType.EAGER)
	private List<Produto> produtos;

}
