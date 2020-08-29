package br.com.senior.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Data
public class Pedido implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@Column(nullable = false,  columnDefinition = "boolean default true")  
    private Boolean ativo;
    
    @ManyToMany
    private List<Produto> produtos;
    
    @NotNull(message = "Valor do pedido não pode ser nulo")
    private Double valorFinal;
    
}
