package br.com.senior.entity;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Pedido {
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
	
    private Boolean situacao;
    
    
    private Pedido(){
    	this.id = UUID.randomUUID().toString();    	
    }

}
