package br.edu.ifes.caxu.si.petshop.model.dao;

import br.edu.ifes.caxu.si.petshop.model.Entidade;
import br.edu.ifes.caxu.si.petshop.model.Responsavel;
import java.util.ArrayList;

/**
 *
 * @author alexandre
 */
public interface ResponsavelDao {
    public ArrayList<Responsavel> getLista();
    
    public Responsavel buscar(Entidade responsavel);
    
    public boolean inserir(Responsavel responsavel);
    
    public boolean alterar(Responsavel responsavel);
    
    public boolean excluir(Responsavel responsavel);
}
