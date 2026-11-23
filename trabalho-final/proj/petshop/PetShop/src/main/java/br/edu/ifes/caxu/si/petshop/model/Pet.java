package br.edu.ifes.caxu.si.petshop.model;

import java.util.Calendar;
import static java.util.Calendar.*;
import java.util.Date;

/**
 *
 * @author alexandre
 */
public class Pet implements Entidade {
  private int id;
  private Responsavel responsavel;
  private String nome;
  private char genero;
  private Especie especie;
  private Raca raca;
  private Date nascimento;
  // (...)
  
  public Pet() {
    this(-1);
  }
  
  public Pet(int id) {
    this.id = id;
  }

  public Pet(Responsavel responsavel, String nome, char genero, Especie especie, 
             Raca raca, Date nascimento) {
    this.responsavel = responsavel;
    this.nome = nome;
    this.genero = genero;
    this.especie = especie;
    this.raca = raca;
    this.nascimento = nascimento;
  }

  public Pet(int id, Responsavel responsavel, String nome, char genero, 
             Especie especie, Raca raca, Date nascimento) {
    this.id = id;
    this.responsavel = responsavel;
    this.nome = nome;
    this.genero = genero;
    this.especie = especie;
    this.raca = raca;
    this.nascimento = nascimento;
  }

  @Override
  public int getId() {
    return id;
  }

  @Override
  public void setId(int id) {
    this.id = id;
  }

  public Responsavel getResponsavel() {
    return responsavel;
  }

  public void setResponsavel(Responsavel responsavel) {
    this.responsavel = responsavel;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public char getGenero() {
    return genero;
  }

  public void setGenero(char genero) {
    this.genero = genero;
  }

  public Raca getRaca() {
    return raca;
  }

  public void setRaca(Raca raca) {
    this.raca = raca;
  }

  public Especie getEspecie() {
    return especie;
  }

  public void setEspecie(Especie especie) {
    this.especie = especie;
  }

  public Date getNascimento() {
    return nascimento;
  }

  public void setNascimento(Date nascimento) {
    this.nascimento = nascimento;
  }

  public int getIdade() {
    Calendar calNascimento = Calendar.getInstance();
    calNascimento.setTime(this.nascimento);
    Calendar hoje = Calendar.getInstance();
    int difAnos = hoje.get(YEAR) - calNascimento.get(YEAR);
    int diaDoAnoNascimento = calNascimento.get(Calendar.DAY_OF_YEAR);
    int diaDoAnoHoje = hoje.get(Calendar.DAY_OF_YEAR);
    if (diaDoAnoNascimento > diaDoAnoHoje) {
      difAnos--;
    }
    return difAnos;
  }

}
