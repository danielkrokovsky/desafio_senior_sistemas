package br.com.senior.entity;

import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Pedido {
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
	
    private Boolean situacao;
    
    @OneToMany
    private List<Produto> produtos;
    
    
    private Pedido(){
    	this.id = UUID.randomUUID().toString();    	
    }

}
