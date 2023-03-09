package br.com.projetofinal.dao;

import java.sql.SQLException;

import br.com.projetofinal.pojo.Curso;

public interface ICursoDAO  {
    //apenas definir os contratos
	//métodos abstratos (CRUD - cadastrar, pesquisar, alterar, excluir
	public abstract void cadastrar(Curso curso) throws SQLException ;
	public abstract void pesquisar();
	public abstract void alterar(Curso curso);
	public abstract void excluir(int id);
	
}
