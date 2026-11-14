package br.edu.ifes.caxu.si.petshop.model;

/**
 *
 * @author alexandre
 */
public class Responsavel implements Entidade {
    private int idResponsavel;
    private String nome;
    private String telefone;
    private String endereco;
    
    public Responsavel(int idResponsavel, String nome, 
            String telefone, String endereco) {
        this.idResponsavel = idResponsavel;
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    @Override
    public int getId() {
        return idResponsavel;
    }
    
    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getIdResponsavel() {
        return idResponsavel;
    }

    public void setIdResponsavel(int idResponsavel) {
        this.idResponsavel = idResponsavel;
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
