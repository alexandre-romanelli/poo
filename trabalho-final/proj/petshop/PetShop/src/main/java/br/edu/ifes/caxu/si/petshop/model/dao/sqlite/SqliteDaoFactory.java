package br.edu.ifes.caxu.si.petshop.model.dao.sqlite;

import br.edu.ifes.caxu.si.petshop.model.dao.DaoFactory;
import br.edu.ifes.caxu.si.petshop.model.dao.EspecieDao;
import br.edu.ifes.caxu.si.petshop.model.dao.PetDao;
import br.edu.ifes.caxu.si.petshop.model.dao.RacaDao;
import br.edu.ifes.caxu.si.petshop.model.dao.ResponsavelDao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author alexandre
 */
public class SqliteDaoFactory extends DaoFactory {
    
    private static final String DB_URL = "jdbc:sqlite:petshop.db";
    private static Connection conn;
    
    private ResponsavelDao responsavelDao;
    
    public static Connection getConnection() {
        if (conn == null) {
            try {
                conn = DriverManager.getConnection(DB_URL);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Não foi possível conectar ao banco de dados.",
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        return conn;
    }

    @Override
    public ResponsavelDao getResponsavelDao() {
        if (responsavelDao == null) {
            responsavelDao = new SqliteResponsavelDao();
        }
        return responsavelDao;
    }

    @Override
    public EspecieDao getEspecieDao() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public RacaDao getRacaDao() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public PetDao getPetDao() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
