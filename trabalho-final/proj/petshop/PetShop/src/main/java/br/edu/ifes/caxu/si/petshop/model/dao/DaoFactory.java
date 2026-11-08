package br.edu.ifes.caxu.si.petshop.model.dao;

import br.edu.ifes.caxu.si.petshop.model.dao.sqlite.SqliteDaoFactory;

/**
 *
 * @author alexandre
 */
public abstract class DaoFactory {
    public static final int TIPO_SQLITE = 1;
    public static final int TIPO_POSTGRE = 2;
    
    public static DaoFactory getDaoFactory(int tipo) {
        switch (tipo) {
            case TIPO_SQLITE: return new SqliteDaoFactory();
            case TIPO_POSTGRE:
            default: return null;
        }
    }
    
    public static DaoFactory getDefaultDaoFactory() {
        return getDaoFactory(TIPO_SQLITE);
    }
    
    public abstract ResponsavelDao getResponsavelDao();
    
    public abstract EspecieDao getEspecieDao();
    
    public abstract RacaDao getRacaDao();
    
    public abstract PetDao getPetDao();
}
