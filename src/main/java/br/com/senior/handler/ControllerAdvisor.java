package br.com.senior.handler;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.senior.exception.PedidoFechadoException;
import br.com.senior.exception.ProdutoDesativadoException;
import br.com.senior.exception.ProdutoEmUsoException;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ControllerAdvisor extends ResponseEntityExceptionHandler {
	
	
	@ExceptionHandler(value = {PedidoFechadoException.class})
	protected ResponseEntity<Object> handlePedidoFechadoException(RuntimeException ex, WebRequest request) {
	
		return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT, request);
	}
	
	@ExceptionHandler(value = {ProdutoDesativadoException.class})
	protected ResponseEntity<Object> handleProdutoDesativadoException(RuntimeException ex, WebRequest request) {
	
		return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT, request);
	}
	
	@ExceptionHandler(value = {ProdutoEmUsoException.class})
	protected ResponseEntity<Object> handleProdutoEmUsoException(RuntimeException ex, WebRequest request) {
	
		return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT, request);
	}
}
