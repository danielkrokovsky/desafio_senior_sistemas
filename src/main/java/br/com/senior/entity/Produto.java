package br.com.senior.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
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
	@SequenceGenerator(name= "VOCABULARY_SEQUENCE", sequenceName = "VOCABULARY_SEQUENCE_ID", initialValue=1, allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.AUTO, generator="VOCABULARY_SEQUENCE")
    private Long id;
 
	@NotNull(message = "Nome não pode ser nulo")
    private String nome;
    
	@NotNull(message = "Valor não pode ser nulo")
    private Double valor;
    
	@Column(nullable = false,  columnDefinition = "boolean default false")  
    private boolean servico;
    
	@Column(nullable = false,  columnDefinition = "boolean default true")  
    private boolean ativo;

}
