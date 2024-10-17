package com.ucb.apimoodle.repositories;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.ucb.apimoodle.entities.Aluno;
import com.ucb.apimoodle.exceptions.DatabaseConnectionException;
import com.ucb.apimoodle.exceptions.InternalServerErrorExeception;
import com.ucb.apimoodle.exceptions.ResourceNotFoundException;

//REPOSITÓRIO PARA BUSCA DOS DADOS DO ALUNO
@Repository
public class AlunoRepository {
	
	@Autowired
	@Qualifier("firstDataSource")
	private DataSource firstDataSource;
	
	@Autowired
	@Qualifier("secondDataSource")
	private DataSource secondDataSource;
	
	private LocalDateTime agora;
	
	private static final Logger logger = LoggerFactory.getLogger(AlunoRepository.class);
	
	// OBTER CONEXAO COM O BANCO DE DADOS
	private Connection getConexao(DataSource dataSource) {
		agora = LocalDateTime.now();
		try {
			Connection conn = dataSource.getConnection();
			return conn;
		} catch (SQLException e) {
			logger.error("{} Erro de conexão com o banco de dados: {}", agora, e.getMessage());
			throw new DatabaseConnectionException("Falha ao conectar ao banco de dados", e);
		}
	}
	
	private void encerrarConexoes(Connection conn, ResultSet rs, CallableStatement cs) {
		agora = LocalDateTime.now();
		try {
			if (conn != null && !conn.isClosed()) {
				conn.clearWarnings();
				conn.close();
			}
			if (rs != null && !rs.isClosed()) {
				rs.clearWarnings();
				rs.close();
			}
			if (cs != null && !cs.isClosed()) {
				cs.clearWarnings();
				cs.close();
			}
		} catch (SQLException e) {
			System.out.println(agora + " Erro ao encerrar conexões / " + e.getMessage());
		}
	}
	
	private void encerrarConexoes(Connection conn, CallableStatement cs) {
		agora = LocalDateTime.now();
		try {
			if (conn != null && !conn.isClosed()) {
				conn.clearWarnings();
				conn.close();
			}
			if (cs != null && !cs.isClosed()) {
				cs.clearWarnings();
				cs.close();
			}
		} catch (SQLException e) {
			System.out.println(agora + " Erro ao encerrar conexões / " + e.getMessage());
		}
	}
	
	// BUSCA TODOS OS ALUNOS DE ACORDO COM O PERIODO LETIVO
	public List<Aluno> buscarAlunosPerLet(String perlet) {
		String sql = "{CALL buscarAlunosPerLet (?)}";
		Connection conn = getConexao(firstDataSource);
		ResultSet rs = null;
		CallableStatement cs = null;
		List<Aluno> alunos = new ArrayList<>();
		agora = LocalDateTime.now();
		try {
			cs = conn.prepareCall(sql);
			cs.setString(1, perlet);
			rs = cs.executeQuery();
			
			while (rs.next()) {
				String username = rs.getString("username");
				String firstname = rs.getString("firstname");
				String lastname = rs.getString("lastname");
				String password = rs.getString("password");
				String email = rs.getString("email");
				String telefone = rs.getString("telefone");
				String shortname = rs.getString("shortname");
				String datestart = rs.getString("datestart");
				String dateend = rs.getString("dateend");
				String group = rs.getString("group");
				Integer role = rs.getInt("role");
				
				Aluno aluno = new Aluno(username, firstname, lastname, password, email, telefone, shortname, datestart, dateend, group, role);
				alunos.add(aluno);
			}
			
			if (alunos.isEmpty()) {
				throw new ResourceNotFoundException("Nenhum aluno encontrado para o período letivo: " + perlet);
			} else {
				return alunos;
			}
		} catch (SQLException e) {
			logger.error("{} Erro ao buscar alunos (Filtro: período letivo): {}", agora, e.getMessage());
			throw new InternalServerErrorExeception("Erro ao buscar alunos no banco de dados");
		} finally {
			encerrarConexoes(conn, rs, cs);
		}
	}
}
