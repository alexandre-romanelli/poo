package br.edu.ifes.caxu.si.petshop.model.dao.sqlite;

import br.edu.ifes.caxu.si.petshop.model.Entidade;
import br.edu.ifes.caxu.si.petshop.model.Especie;
import br.edu.ifes.caxu.si.petshop.model.Raca;
import br.edu.ifes.caxu.si.petshop.model.dao.DaoFactory;
import br.edu.ifes.caxu.si.petshop.model.dao.RacaDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

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
        ArrayList<Raca> lista = new ArrayList<>();
        try {
            Connection conn = SqliteDaoFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(SCRIPT_LISTAR);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idRaca = rs.getInt(1);
                String nomeRaca = rs.getString(2);
                int idEspecie = rs.getInt(3);
                String nomeEspecie = rs.getString(4);
                Especie especie = DaoFactory.getDefaultDaoFactory().getEspecieDao().buscar(new Especie(idEspecie, nomeEspecie));
                lista.add(new Raca(idRaca, especie, nomeRaca));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, 
                    "Erro ao buscar registros de espécies.", 
                    "Erro", 
                    JOptionPane.ERROR_MESSAGE);
        }
        return lista;
    }

    @Override
    public Raca buscar(Entidade raca) {
        try {
            Connection conn = SqliteDaoFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(SCRIPT_BUSCAR);
            ps.setInt(1, raca.getId());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String nomeRaca = rs.getString(1);
                int idEspecie = rs.getInt(2);
                String nomeEspecie = rs.getString(3);
                Especie especie = new Especie(idEspecie, nomeEspecie);
                Raca racaBuscada = new Raca(raca.getId(), especie, nomeRaca);
                return racaBuscada;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, 
                    "Erro ao buscar registro de raça.", 
                    "Erro",         
                    JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    @Override
    public boolean inserir(Raca raca) {
        try {
            Connection conn = SqliteDaoFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(SCRIPT_INSERIR);
            ps.setString(1, raca.getNome());
            ps.setInt(2, raca.getEspecie().getId());
            int resultado = ps.executeUpdate();
            return resultado == 1;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao inserir registro de raça.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    @Override
    public boolean alterar(Raca raca) {
        try {
            Connection conn = SqliteDaoFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(SCRIPT_ALTERAR);
            ps.setString(1, raca.getNome());
            ps.setInt(2, raca.getEspecie().getId());
            ps.setInt(3, raca.getId());
            int resultado = ps.executeUpdate();
            return resultado == 1;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao alterar registro de raça.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    @Override
    public boolean excluir(Raca raca) {
        try {
            Connection conn = SqliteDaoFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(SCRIPT_EXCLUIR);
            ps.setInt(1, raca.getId());
            int resultado = ps.executeUpdate();
            return resultado == 1;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao excluir registro de raça.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
    
}
