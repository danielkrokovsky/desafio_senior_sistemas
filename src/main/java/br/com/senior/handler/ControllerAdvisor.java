package br.com.senior.handler;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.senior.exception.PedidoFechadoException;
import br.com.senior.exception.ProdutoDesativadoException;
import br.com.senior.exception.ProdutoEmUsoException;
import br.com.senior.exception.ProdutoNaoIdentificadoException;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ControllerAdvisor extends ResponseEntityExceptionHandler {
	
	
	@ExceptionHandler(value = {PedidoFechadoException.class, ProdutoNaoIdentificadoException.class})
	protected ResponseEntity<Object> handlePedidoFechadoException(RuntimeException ex, WebRequest request) {
		
		Map<String, Object> body = criarMenssagen(HttpStatus.CONFLICT, ex.getMessage());
	
		return handleExceptionInternal(ex,body, new HttpHeaders(), HttpStatus.CONFLICT, request);
	}
	
	@ExceptionHandler(value = {ProdutoDesativadoException.class})
	protected ResponseEntity<Object> handleProdutoDesativadoException(RuntimeException ex, WebRequest request) {
		
		Map<String, Object> body = criarMenssagen(HttpStatus.CONFLICT, ex.getMessage());
	
		return handleExceptionInternal(ex, body, new HttpHeaders(), HttpStatus.CONFLICT, request);
	}
	
	@ExceptionHandler(value = {ProdutoEmUsoException.class})
	protected ResponseEntity<Object> handleProdutoEmUsoException(ProdutoEmUsoException ex, WebRequest request) {
		
		Map<String, Object> body = criarMenssagen(HttpStatus.CONFLICT, ex.getMessage());
	
		return handleExceptionInternal(ex, body, new HttpHeaders(), HttpStatus.CONFLICT, request);
	}	
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());
        
        
        String res = errors.stream()
				 .map(Object::toString)
				 .collect(Collectors.joining("\n"));
        
		Map<String, Object> body = criarMenssagen(HttpStatus.CONFLICT, res);

        return new ResponseEntity<>(body, headers, status);
	}
	
	private Map<String, Object> criarMenssagen(HttpStatus httpStatus, String msg){
		
		Map<String, Object> body = new LinkedHashMap<>();
		
		body.put("timestamp", new Date());
        body.put("status", httpStatus);

        body.put("errors", msg);
        
        
        return body;
	}
}
