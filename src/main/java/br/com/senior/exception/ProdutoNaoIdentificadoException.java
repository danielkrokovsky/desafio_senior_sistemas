package br.com.senior.exception;

public class ProdutoNaoIdentificadoException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public ProdutoNaoIdentificadoException(String msg) {
		super(msg);
	}

}