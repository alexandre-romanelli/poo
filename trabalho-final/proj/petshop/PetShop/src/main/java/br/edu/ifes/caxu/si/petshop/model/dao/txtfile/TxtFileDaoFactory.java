package br.edu.ifes.caxu.si.petshop.model.dao.txtfile;


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
public class TxtFileDaoFactory extends DaoFactory {

    private ResponsavelDao responsavelDao;
    private EspecieDao especieDao;
    private RacaDao racaDao;
    
    @Override
    public ResponsavelDao getResponsavelDao() {
        if (responsavelDao == null) {
            responsavelDao = new TxtFileResponsavelDao();
        }
        return responsavelDao;
    }

    @Override
    public EspecieDao getEspecieDao() {
        if (especieDao == null) {
            especieDao = new TxtFileEspecieDao();
        }
        return especieDao;
    }

    @Override
    public RacaDao getRacaDao() {
        if (racaDao == null) {
            racaDao = new TxtFileRacaDao();
        }
        return racaDao;
    }

    @Override
    public PetDao getPetDao() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
