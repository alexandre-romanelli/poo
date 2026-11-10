package br.edu.ifes.caxu.si.petshop.model.dao;

import br.edu.ifes.caxu.si.petshop.model.Entidade;
import br.edu.ifes.caxu.si.petshop.model.Pet;
import java.util.ArrayList;

/**
 *
 * @author alexandre
 */
public interface PetDao {
    public ArrayList<Pet> getLista();
    
    public Pet buscar(Entidade pet);
    
    public boolean inserir(Pet pet);
    
    public boolean alterar(Pet pet);
    
    public boolean excluir(Entidade pet);
}
