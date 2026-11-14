package br.edu.ifes.caxu.si.petshop.model.dao;

import br.edu.ifes.caxu.si.petshop.model.Entidade;
import br.edu.ifes.caxu.si.petshop.model.Especie;
import java.util.ArrayList;

/**
 *
 * @author alexandre
 */
public interface EspecieDao {
    public ArrayList<Especie> getLista();
    
    public Especie buscar(Entidade especie);
    
    public boolean inserir(Especie especie);
    
    public boolean alterar(Especie especie);
    
    public boolean excluir(Especie especie);
}
