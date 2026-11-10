package br.edu.ifes.caxu.si.petshop.model.dao.sqlite;

import br.edu.ifes.caxu.si.petshop.model.Entidade;
import br.edu.ifes.caxu.si.petshop.model.Especie;
import br.edu.ifes.caxu.si.petshop.model.dao.EspecieDao;
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
public class SqliteEspecieDao implements EspecieDao {
    
    private static final String SCRIPT_LISTAR =
            "select especie_id, nome "
            + "from especie "
            + "order by nome";
    
    private static final String SCRIPT_BUSCAR =
            "select nome "
            + "from especie "
            + "where especie_id = ?";
    
    private static final String SCRIPT_INSERIR =
            "insert into especie (nome) "
            + "values (?)";
    
    private static final String SCRIPT_ALTERAR =
            "update especie "
            + "set nome = ? "
            + "where especie_id = ?";
    
    private static final String SCRIPT_EXCLUIR =
            "delete from especie where especie_id = ?";

    @Override
    public ArrayList<Especie> getLista() {
        ArrayList<Especie> lista = new ArrayList<>();
        try {
            Connection conn = SqliteDaoFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(SCRIPT_LISTAR);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String nome = rs.getString(2);
                lista.add(new Especie(id, nome));
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
    public Especie buscar(Entidade especie) {
        try {
            Connection conn = SqliteDaoFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(SCRIPT_BUSCAR);
            ps.setInt(1, especie.getId());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt(1);
                String nome = rs.getString(2);
                Especie especieBuscada = new Especie(id, nome);
                return especieBuscada;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, 
                    "Erro ao buscar registro de espécie.", 
                    "Erro", 
                    JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    @Override
    public boolean inserir(Especie especie) {
        try {
            Connection conn = SqliteDaoFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(SCRIPT_INSERIR);
            ps.setString(1, especie.getNome());
            int resultado = ps.executeUpdate();
            return resultado == 1;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao inserir registro de espécie.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    @Override
    public boolean alterar(Especie especie) {
        try {
            Connection conn = SqliteDaoFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(SCRIPT_ALTERAR);
            ps.setString(1, especie.getNome());
            ps.setInt(2, especie.getId());
            int resultado = ps.executeUpdate();
            return resultado == 1;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao alterar registro de espécie.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    @Override
    public boolean excluir(Especie especie) {
        try {
            Connection conn = SqliteDaoFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(SCRIPT_EXCLUIR);
            ps.setInt(1, especie.getId());
            int resultado = ps.executeUpdate();
            return resultado == 1;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao excluir registro de espécie.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
    
}
