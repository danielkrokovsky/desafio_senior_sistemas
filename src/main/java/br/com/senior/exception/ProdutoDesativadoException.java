package br.com.senior.exception;

public class ProdutoDesativadoException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public ProdutoDesativadoException(String msg) {
		super(msg);
	}

}