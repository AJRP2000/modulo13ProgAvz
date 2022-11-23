package com.ServicioBackEnd;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DemoController {
	
	@RequestMapping("/demo")
	public ResponseEntity<String> demo() {
		return ResponseEntity.ok().body("Mensaje Prueba");
	}
	
	@SuppressWarnings("rawtypes")
	@GetMapping("/demoGet")
	public ResponseEntity demoGet(@RequestParam(name="variable", required=false, defaultValue="default") String variable) {
		return ResponseEntity.ok().body("Mensaje Prueba. Variable recibida: " + variable);
	}

}
