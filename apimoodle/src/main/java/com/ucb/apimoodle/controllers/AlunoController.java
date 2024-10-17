package com.ucb.apimoodle.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ucb.apimoodle.entities.Aluno;
import com.ucb.apimoodle.services.AlunoService;

@RestController
@RequestMapping(value = "/aluno")
public class AlunoController {
	
	@Autowired
	private AlunoService service;
	
	@GetMapping(value = "/buscar/perlet")
	public ResponseEntity<List<Aluno>> buscarAlunosPerLet(@RequestParam String perlet) {
		List<Aluno> result = service.buscarAlunosPerLet(perlet);
		return ResponseEntity.ok(result);
	}
}
