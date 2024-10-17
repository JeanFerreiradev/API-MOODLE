package com.ucb.apimoodle.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ucb.apimoodle.entities.Aluno;
import com.ucb.apimoodle.repositories.AlunoRepository;

//SERVIÇO PARA SEPARAR REGRAS DE NEGÓCIO DO CONTROLADOR
@Service
public class AlunoService {
	
	@Autowired
	private AlunoRepository repository;
	
	public List<Aluno> buscarAlunosPerLet(String perlet) {
		return repository.buscarAlunosPerLet(perlet);
	}
}
