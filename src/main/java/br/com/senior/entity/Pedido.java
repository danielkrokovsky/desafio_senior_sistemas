package br.com.senior.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

import lombok.Data;

@Entity
@Data
public class Pedido implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name= "PEDIDO_SEQUENCE", sequenceName = "PEDIDO_SEQUENCE_ID", initialValue=1, allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PEDIDO_SEQUENCE")
    private Long id;
	
	@Column(nullable = false,  columnDefinition = "boolean default true")  
    private Boolean ativo = true;
    
    @ManyToMany
    private List<Produto> produtos;
    
    private Double valorFinal;
    
}
