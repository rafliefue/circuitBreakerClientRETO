package com.bootcamp.cb.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.everis.bootcamp.CircuitBreaker;

@RestController
public class IndexController {

	@Autowired
	private CircuitBreaker cb;
	
	@Autowired
	private EndpointEstados actuator;
	
	Integer contador = 0;
	
	public IndexController() {
		
	}
	
	@GetMapping("/")
	public ResponseEntity<String> index(){
		return new ResponseEntity<String>(HttpStatus.OK).ok("");
	}
	
	@GetMapping("/open")
	public ResponseEntity<String> open(){
		this.actuator.writeOperation("open");
		contador = contador +1;
		
		if(contador == 10) {
			return new ResponseEntity<String>(HttpStatus.OK).ok("YA HA LLEGADO AL NÚMERO MÁXIMO DE ITERACIONES");
		} else {
			return new ResponseEntity<String>(HttpStatus.OK).ok(cb.proximoEstado(contador));
		}
	}
	
	@GetMapping("/half-open")
	public ResponseEntity<String> halfOpen(){
		this.actuator.writeOperation("half-open");
		contador = contador +1;
		
		if(contador == 10) {
			return new ResponseEntity<String>(HttpStatus.OK).ok("YA HA LLEGADO AL NÚMERO MÁXIMO DE ITERACIONES");
		} else {
			return new ResponseEntity<String>(HttpStatus.OK).ok(cb.proximoEstado(contador));
		}
	}
	
	@GetMapping("/closed")
	public ResponseEntity<String> closed(){
		this.actuator.writeOperation("closed");
		contador = contador +1;
		
		if(contador == 10) {
			return new ResponseEntity<String>(HttpStatus.OK).ok("YA HA LLEGADO AL NÚMERO MÁXIMO DE ITERACIONES");
		} else {
			return new ResponseEntity<String>(HttpStatus.OK).ok(cb.proximoEstado(contador));
		}
		
	}
	
	
	
}
