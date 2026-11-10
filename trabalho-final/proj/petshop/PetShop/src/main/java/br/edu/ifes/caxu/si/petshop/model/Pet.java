package br.edu.ifes.caxu.si.petshop.model;

import java.util.Calendar;
import static java.util.Calendar.*;
import java.util.Date;

/**
 *
 * @author alexandre
 */
public class Pet implements Entidade {
    private int pet_id;
    private Responsavel responsavel;
    private String nome;
    private char genero;
    private Especie especie;
    private Especie raca;
    private Date nascimento;

    @Override
    public int getId() {
        return pet_id;
    }

    public void setPet_id(int pet_id) {
        this.pet_id = pet_id;
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

    public Especie getEspecie() {
        return especie;
    }

    public void setEspecie(Especie especie) {
        this.especie = especie;
    }

    public Especie getRaca() {
        return raca;
    }

    public void setRaca(Especie raca) {
        this.raca = raca;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }
    
    public int getIdade() {
        Calendar nasc = Calendar.getInstance();
        nasc.setTime(nascimento);
        Calendar hoje = Calendar.getInstance();
        
        int difAnos = hoje.get(YEAR) - nasc.get(YEAR);
        if (nasc.get(MONTH) > hoje.get(MONTH) || nasc.get(DATE) > hoje.get(DATE)) {
            difAnos--;
        }
        return difAnos;
    }

}
