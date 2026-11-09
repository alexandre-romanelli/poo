package br.edu.ifes.caxu.si.petshop.model.dao;

import br.edu.ifes.caxu.si.petshop.model.Entidade;
import br.edu.ifes.caxu.si.petshop.model.Raca;
import java.util.ArrayList;

/**
 *
 * @author alexandre
 */
public interface RacaDao {
    public ArrayList<Raca> getLista();
    
    public Raca buscar(Entidade raca);
    
    public boolean inserir(Raca raca);
    
    public boolean alterar(Raca raca);
    
    public boolean excluir(Raca raca);
}
