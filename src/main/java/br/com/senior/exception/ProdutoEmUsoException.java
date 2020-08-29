package br.com.senior.exception;

public class ProdutoEmUsoException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public ProdutoEmUsoException(String msg) {
		super(msg);
	}

}