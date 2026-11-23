package br.edu.ifes.caxu.si.petshop.model;

/**
 *
 * @author alexandre
 */
public class Especie implements Entidade {

  private int id;
  private String nome;
  
  public Especie() {
    this(-1);
  }

  public Especie(int id) {
    this.id = id;
    nome = "";
  }

  public Especie(String nome) {
    this.id = -1;
    this.nome = nome;
  }

  public Especie(int id, String nome) {
    this.id = id;
    this.nome = nome;
  }

  @Override
  public int getId() {
    return id;
  }

  @Override
  public void setId(int id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

}
