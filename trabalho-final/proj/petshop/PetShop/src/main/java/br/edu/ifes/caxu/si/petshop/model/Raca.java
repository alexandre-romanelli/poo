package br.edu.ifes.caxu.si.petshop.model;

/**
 *
 * @author alexandre
 */
public class Raca implements Entidade {
    private int idRaca;
    private Especie especie;
    private String nome;
    
    public Raca(int idRaca, Especie especie, String nome) {
        this.idRaca = idRaca;
        this.especie = especie;
        this.nome = nome;
    }

    @Override
    public int getId() {
        return idRaca;
    }

    public void setIdRaca(int idRaca) {
        this.idRaca = idRaca;
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
