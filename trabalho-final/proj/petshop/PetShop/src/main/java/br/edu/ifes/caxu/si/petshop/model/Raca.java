package br.edu.ifes.caxu.si.petshop.model;

/**
 *
 * @author alexandre
 */
public class Raca implements Entidade {

  private int id;
  private Especie especie;
  private String nome;
  
  public Raca() {
    this(-1);
  }

  public Raca(int id) {
    this.id = id;
    this.especie = null;
    this.nome = "";
  }

  public Raca(Especie especie, String nome) {
    this.id = -1;
    this.especie = especie;
    this.nome = nome;
  }

  public Raca(int id, Especie especie, String nome) {
    this.id = id;
    this.especie = especie;
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

  public Especie getEspecie() {
    return especie;
  }

  public void setEspecie(Especie especie) {
    this.especie = especie;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

}
