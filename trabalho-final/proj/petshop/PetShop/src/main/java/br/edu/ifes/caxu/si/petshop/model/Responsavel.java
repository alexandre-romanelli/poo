package br.edu.ifes.caxu.si.petshop.model;

/**
 *
 * @author alexandre
 */
public class Responsavel implements Entidade {

  private int id;
  private String nome;
  private String telefone;
  private String endereco;
  
  public Responsavel() {
    this(-1);
  }

  public Responsavel(int id) {
    this.id = id;
    this.nome = "";
    this.telefone = "";
    this.endereco = "";
  }

  public Responsavel(String nome, String telefone, String endereco) {
    this.id = -1;
    this.nome = nome;
    this.telefone = telefone;
    this.endereco = endereco;
  }

  public Responsavel(int id, String nome, String telefone, String endereco) {
    this.id = id;
    this.nome = nome;
    this.telefone = telefone;
    this.endereco = endereco;
  }

  @Override
  public int getId() {
    return id;
  }

  @Override
  public void setId(int id) {
    this.id = id;
  }

  public String getEndereco() {
    return endereco;
  }

  public void setEndereco(String endereco) {
    this.endereco = endereco;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getTelefone() {
    return telefone;
  }

  public void setTelefone(String telefone) {
    this.telefone = telefone;
  }

}
