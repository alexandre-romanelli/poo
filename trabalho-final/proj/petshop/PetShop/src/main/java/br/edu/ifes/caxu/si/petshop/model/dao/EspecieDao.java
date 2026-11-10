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
    
    public Especie buscar(Entidade responsavel);
    
    public boolean inserir(Especie responsavel);
    
    public boolean alterar(Especie responsavel);
    
    public boolean excluir(Especie responsavel);
}
