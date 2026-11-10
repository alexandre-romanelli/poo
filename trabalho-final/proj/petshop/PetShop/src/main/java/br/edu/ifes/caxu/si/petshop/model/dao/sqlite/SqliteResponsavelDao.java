package br.edu.ifes.caxu.si.petshop.model.dao.sqlite;

import br.edu.ifes.caxu.si.petshop.model.Entidade;
import br.edu.ifes.caxu.si.petshop.model.Responsavel;
import br.edu.ifes.caxu.si.petshop.model.dao.ResponsavelDao;
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
public class SqliteResponsavelDao implements ResponsavelDao {
    
    private static final String SCRIPT_LISTAR =
            "select responsavel_id, nome, telefone, endereco "
            + "from responsavel "
            + "order by nome";
    
    private static final String SCRIPT_BUSCAR =
            "select nome, telefone, endereco "
            + "from responsavel "
            + "where responsavel_id = ?";
    
    private static final String SCRIPT_INSERIR =
            "insert into responsavel (nome, telefone, endereco) "
            + "values (?, ?, ?)";
    
    private static final String SCRIPT_OBTER_ID_GERADO =
            "select last_insert_rowid()";
    
    private static final String SCRIPT_ALTERAR =
            "update responsavel "
            + "set nome = ?, "
            + "telefone = ?, "
            + "endereco = ? "
            + "where responsavel_id = ?";
    
    private static final String SCRIPT_EXCLUIR =
            "delete from responsavel where responsavel_id = ?";
    
    @Override
    public ArrayList<Responsavel> getLista() {
        ArrayList<Responsavel> lista = new ArrayList<>();
        try {
            Connection conn = SqliteDaoFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(SCRIPT_LISTAR);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String nome = rs.getString(2);
                String telefone = rs.getString(3);
                String endereco = rs.getString(4);
                lista.add(new Responsavel(id, nome, telefone, endereco));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, 
                    "Erro ao buscar registros de responsáveis.", 
                    "Erro", 
                    JOptionPane.ERROR_MESSAGE);
        }
        return lista;
    }

    @Override
    public Responsavel buscar(Entidade responsavel) {
        try {
            Connection conn = SqliteDaoFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(SCRIPT_BUSCAR);
            ps.setInt(1, responsavel.getId());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt(1);
                String nome = rs.getString(2);
                String telefone = rs.getString(3);
                String endereco = rs.getString(4);
                Responsavel responsavelBuscado = new Responsavel(id, nome, telefone, endereco);
                return responsavelBuscado;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, 
                    "Erro ao buscar registro de responsável.", 
                    "Erro", 
                    JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    @Override
    public boolean inserir(Responsavel responsavel) {
        try {
            Connection conn = SqliteDaoFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(SCRIPT_INSERIR);
            ps.setString(1, responsavel.getNome());
            ps.setString(2, responsavel.getTelefone());
            ps.setString(3, responsavel.getEndereco());
            ps.executeUpdate();
            PreparedStatement psObterId = conn.prepareStatement(SCRIPT_OBTER_ID_GERADO);
            ResultSet rs = psObterId.executeQuery();
            if (rs.next()) {
                int id = rs.getInt(1);
                responsavel.setIdResponsavel(id);
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao inserir registro de responsável.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    @Override
    public boolean alterar(Responsavel responsavel) {
        try {
            Connection conn = SqliteDaoFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(SCRIPT_ALTERAR);
            ps.setString(1, responsavel.getNome());
            ps.setString(2, responsavel.getTelefone());
            ps.setString(3, responsavel.getEndereco());
            ps.setInt(4, responsavel.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao alterar registro de responsável.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    @Override
    public boolean excluir(Responsavel responsavel) {
        try {
            Connection conn = SqliteDaoFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(SCRIPT_EXCLUIR);
            ps.setInt(1, responsavel.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao excluir registro de responsável.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}
