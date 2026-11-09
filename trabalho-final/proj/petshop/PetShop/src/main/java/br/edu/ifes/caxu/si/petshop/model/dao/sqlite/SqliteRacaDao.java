package br.edu.ifes.caxu.si.petshop.model.dao.sqlite;

import br.edu.ifes.caxu.si.petshop.model.Entidade;
import br.edu.ifes.caxu.si.petshop.model.Raca;
import br.edu.ifes.caxu.si.petshop.model.dao.RacaDao;
import java.util.ArrayList;

/**
 *
 * @author alexandre
 */
public class SqliteRacaDao implements RacaDao {

    private static final String SCRIPT_LISTAR =
            "select r.raca_id, r.nome, r.especie, e.especie_id, e.nome "
            + "from raca r"
            + "     join especie e on (r.especie = e.especie_id) "
            + "order by e.nome, r.nome";
    
    private static final String SCRIPT_BUSCAR =
            "select r.nome, e.especie_id, e.nome "
            + "from raca r"
            + "     join especie e on (r.especie = e.especie_id) "
            + "where raca_id = ?";
    
    private static final String SCRIPT_INSERIR =
            "insert into raca (nome, especie) "
            + "values (?, ?)";
    
    private static final String SCRIPT_ALTERAR =
            "update raca "
            + "set nome = ?,"
            + "    especie = ? "
            + "where raca_id = ?";
    
    private static final String SCRIPT_EXCLUIR =
            "delete from raca where raca_id = ?";

    @Override
    public ArrayList<Raca> getLista() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Raca buscar(Entidade raca) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean inserir(Raca raca) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean alterar(Raca raca) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean excluir(Raca raca) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
