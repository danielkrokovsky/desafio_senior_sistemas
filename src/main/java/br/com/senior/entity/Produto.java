package br.com.senior.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.Data;


@Entity
@Data
public class Produto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
 
	@NotNull(message = "Nome não pode ser nulo")
    private String nome;
    
	@NotNull(message = "Valor não pode ser nulo")
    private Double valor;
    
    private Boolean isServico = false;
    
    private boolean ativo = true;

}
