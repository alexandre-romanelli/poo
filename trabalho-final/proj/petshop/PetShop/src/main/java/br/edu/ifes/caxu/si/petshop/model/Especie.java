package br.edu.ifes.caxu.si.petshop.model;

/**
 *
 * @author alexandre
 */
public class Especie implements Entidade {
    private int idEspecie;
    private String nome;
    
    public Especie(int idEspecie, String nome) {
        this.idEspecie = idEspecie;
        this.nome = nome;
    }

    @Override
    public int getId() {
        return idEspecie;
    }

    public void setIdEspecie(int idEspecie) {
        this.idEspecie = idEspecie;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
}
