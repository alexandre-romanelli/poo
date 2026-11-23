package br.edu.ifes.caxu.si.petshop.model.dao.sqlite;

import br.edu.ifes.caxu.si.petshop.model.Entidade;
import br.edu.ifes.caxu.si.petshop.model.Especie;
import br.edu.ifes.caxu.si.petshop.model.Pet;
import br.edu.ifes.caxu.si.petshop.model.Raca;
import br.edu.ifes.caxu.si.petshop.model.Responsavel;
import br.edu.ifes.caxu.si.petshop.model.dao.DaoFactory;
import br.edu.ifes.caxu.si.petshop.model.dao.PetDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author alexr
 */
public class SqlitePetDao implements PetDao {
  
  private static final String SCRIPT_LISTAR
          = "select pet_id, responsavel, nome, genero, especie, raca, nascimento "
          + "from pet "
          + "order by nome";
  
  private static final String SCRIPT_BUSCAR
          = "select responsavel, nome, genero, especie, raca, nascimento "
          + "from pet "
          + "where pet_id = ?";
  
  private static final String SCRIPT_INSERIR
          = "insert into pet (responsavel, nome, genero, especie, raca, nascimento) "
          + "values (?, ?, ?, ?, ?, ?)";
  
  private static final String SCRIPT_ALTERAR
          = "update pet "
          + "set responsavel = ?,"
          + "    nome = ?,"
          + "    genero = ?"
          + "    especie = ?"
          + "    raca = ?"
          + "    nascimento = ? "
          + "where pet_id = ?";
  
  private static final String SCRIPT_EXCLUIR
          = "delete from pet where pet_id = ?";

  @Override
  public ArrayList<Pet> getLista() {
    ArrayList<Pet> lista = new ArrayList<>();
    int id = -1;
    try {
      Connection conn = SqliteDaoFactory.getConnection();
      PreparedStatement ps = conn.prepareStatement(SCRIPT_LISTAR);
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        id = rs.getInt(1);
        int idResponsavel = rs.getInt(2);
        Responsavel resp = DaoFactory.getDefaultDaoFactory()
                .getResponsavelDao().buscar(new Responsavel(idResponsavel));
        String nome = rs.getString(3);
        char genero = rs.getString(4).charAt(0);
        int idEspecie = rs.getInt(5);
        Especie esp = DaoFactory.getDefaultDaoFactory().getEspecieDao().buscar(new Especie(idEspecie));
        int idRaca = rs.getInt(6);
        Raca raca = DaoFactory.getDefaultDaoFactory().getRacaDao().buscar(new Raca(idRaca));
        String strNascimento = rs.getString(7);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date nascimento = df.parse(strNascimento);
        
        Pet pet = new Pet(id, resp, nome, genero, esp, raca, nascimento);
        lista.add(pet);
      }
    } catch (SQLException ex) {
      JOptionPane.showMessageDialog(null,
              "Erro ao listar registros de pets.",
              "Erro",
              JOptionPane.ERROR_MESSAGE);
    } catch (ParseException ex) {
      JOptionPane.showMessageDialog(null,
              "Data inválida no registro de pet com id = " + Integer.toString(id) + ".",
              "Erro",
              JOptionPane.ERROR_MESSAGE);
    }
    return lista;
  }

  @Override
  public Pet buscar(Entidade pet) {
    try {
      Connection conn = SqliteDaoFactory.getConnection();
      PreparedStatement ps = conn.prepareStatement(SCRIPT_BUSCAR);
      ps.setInt(1, pet.getId());
      ResultSet rs = ps.executeQuery();
      if (rs.next()) {
        int idResponsavel = rs.getInt(1);
        Responsavel resp = DaoFactory.getDefaultDaoFactory()
                .getResponsavelDao().buscar(new Responsavel(idResponsavel));
        String nome = rs.getString(2);
        char genero = rs.getString(3).charAt(0);
        int idEspecie = rs.getInt(4);
        Especie esp = DaoFactory.getDefaultDaoFactory().getEspecieDao().buscar(new Especie(idEspecie));
        int idRaca = rs.getInt(5);
        Raca raca = DaoFactory.getDefaultDaoFactory().getRacaDao().buscar(new Raca(idRaca));
        String strNascimento = rs.getString(6);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date nascimento = df.parse(strNascimento);
        
        Pet petBuscado = new Pet(pet.getId(), resp, nome, genero, esp, raca, nascimento);
        return petBuscado;
      }
    } catch (SQLException ex) {
      JOptionPane.showMessageDialog(null,
              "Erro ao buscar registro de pet.",
              "Erro",
              JOptionPane.ERROR_MESSAGE);
    } catch (ParseException ex) {
      JOptionPane.showMessageDialog(null,
              "Data inválida no registro de pet com id = " + Integer.toString(pet.getId()) + ".",
              "Erro",
              JOptionPane.ERROR_MESSAGE);
    }
    return null;
  }

  @Override
  public boolean inserir(Pet pet) {
    try {
      Connection conn = SqliteDaoFactory.getConnection();
      PreparedStatement ps = conn.prepareStatement(SCRIPT_INSERIR);
      ps.setInt(1, pet.getResponsavel().getId());
      ps.setString(2, pet.getNome());
      ps.setString(3, String.valueOf(pet.getGenero()));
      ps.setInt(4, pet.getEspecie().getId());
      ps.setInt(5, pet.getRaca().getId());
      SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
      ps.setString(6, df.format(pet.getNascimento()));
      int resultado = ps.executeUpdate();
      return resultado == 1;
    } catch (SQLException ex) {
      JOptionPane.showMessageDialog(null,
              "Erro ao inserir registro de pet.",
              "Erro",
              JOptionPane.ERROR_MESSAGE);
    }
    return false;
  }

  @Override
  public boolean alterar(Pet pet) {
    try {
      Connection conn = SqliteDaoFactory.getConnection();
      PreparedStatement ps = conn.prepareStatement(SCRIPT_ALTERAR);
      ps.setString(1, pet.getNome());
      ps.setString(2, String.valueOf(pet.getGenero()));
      ps.setInt(3, pet.getEspecie().getId());
      ps.setInt(4, pet.getRaca().getId());
      SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
      ps.setString(5, df.format(pet.getNascimento()));
      ps.setInt(6, pet.getResponsavel().getId());
      int resultado = ps.executeUpdate();
      return resultado == 1;
    } catch (SQLException ex) {
      JOptionPane.showMessageDialog(null,
              "Erro ao alterar registro de pet.",
              "Erro",
              JOptionPane.ERROR_MESSAGE);
    }
    return false;
  }

  @Override
  public boolean excluir(Entidade pet) {
    try {
      Connection conn = SqliteDaoFactory.getConnection();
      PreparedStatement ps = conn.prepareStatement(SCRIPT_EXCLUIR);
      ps.setInt(1, pet.getId());
      int resultado = ps.executeUpdate();
      return resultado == 1;
    } catch (SQLException e) {
      JOptionPane.showMessageDialog(null,
              "Erro ao excluir registro de pet.",
              "Erro",
              JOptionPane.ERROR_MESSAGE);
    }
    return false;
  }
  
}
