package br.com.senior.exception;

public class ProdutoNaoEncontradoException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public ProdutoNaoEncontradoException(String msg) {
		super(msg);
	}

}