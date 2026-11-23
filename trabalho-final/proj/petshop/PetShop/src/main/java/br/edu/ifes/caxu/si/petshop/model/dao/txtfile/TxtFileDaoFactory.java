package br.edu.ifes.caxu.si.petshop.model.dao.txtfile;

import br.edu.ifes.caxu.si.petshop.model.dao.DaoFactory;
import br.edu.ifes.caxu.si.petshop.model.dao.EspecieDao;
import br.edu.ifes.caxu.si.petshop.model.dao.PetDao;
import br.edu.ifes.caxu.si.petshop.model.dao.RacaDao;
import br.edu.ifes.caxu.si.petshop.model.dao.ResponsavelDao;

/**
 *
 * @author alexandre
 */
public class TxtFileDaoFactory extends DaoFactory {

  private ResponsavelDao responsavelDao;
  private EspecieDao especieDao;
  private RacaDao racaDao;
  private PetDao petDao;

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
    if (petDao == null) {
      petDao = new TxtFilePetDao();
    }
    return petDao;
  }

}
