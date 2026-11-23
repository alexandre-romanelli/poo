package br.edu.ifes.caxu.si.petshop.model.dao.txtfile;

import br.edu.ifes.caxu.si.petshop.model.Entidade;
import br.edu.ifes.caxu.si.petshop.model.Especie;
import br.edu.ifes.caxu.si.petshop.model.Pet;
import br.edu.ifes.caxu.si.petshop.model.Raca;
import br.edu.ifes.caxu.si.petshop.model.Responsavel;
import br.edu.ifes.caxu.si.petshop.model.dao.DaoFactory;
import br.edu.ifes.caxu.si.petshop.model.dao.PetDao;
import br.edu.ifes.caxu.si.petshop.util.UtilConstantes;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author alexr
 */
public class TxtFilePetDao implements PetDao {

  private static int INDICE_INICIO_REGISTROS = 3;
  private int LINHAS_POR_REGISTRO = 7;

  private List<String> lerArquivo() {
    FileInputStream file;
    try {
      file = new FileInputStream(UtilConstantes.CAMINHO_ARQUIVO_PET);
      InputStreamReader inputStream = new InputStreamReader(file, StandardCharsets.UTF_8);
      List<String> listaLinhas = inputStream.readAllLines();
      inputStream.close();
      return listaLinhas;
    } catch (FileNotFoundException ex) {
      JOptionPane.showMessageDialog(null, "Arquivo de pets não encontrado.",
              "Erro", JOptionPane.ERROR_MESSAGE);
    } catch (IOException ex) {
      JOptionPane.showMessageDialog(null, "Falha ao ler arquivo de pets.",
              "Erro", JOptionPane.ERROR_MESSAGE);
    }
    return null;
  }

  private void obterRegistrosDeListaDeLinhas(List<String> linhas,
          ArrayList<Pet> listaPet,
          boolean pararEmId,
          Pet regProcurado) {
    int numRegistros = Integer.parseInt(linhas.get(1));
    for (int indRegistro = 0; indRegistro < numRegistros; ++indRegistro) {
      int posRegistro = INDICE_INICIO_REGISTROS + LINHAS_POR_REGISTRO * indRegistro;
      int id = Integer.parseInt(linhas.get(posRegistro));
      int idResponsavel = Integer.parseInt(linhas.get(posRegistro + 1));
      String nome = linhas.get(posRegistro + 2);
      char genero = linhas.get(posRegistro + 3).charAt(0);
      int idEspecie = Integer.parseInt(linhas.get(posRegistro + 4));
      int idRaca = Integer.parseInt(linhas.get(posRegistro + 5));
      SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
      String txtData = linhas.get(posRegistro + 6);
      Date dataNascimento;
      try {
        dataNascimento = df.parse(txtData);
      } catch (ParseException ex) {
        JOptionPane.showMessageDialog(null, "Data inválida no arquivo.", "Erro", JOptionPane.ERROR_MESSAGE);
        return;
      }
      Responsavel responsavel = DaoFactory.getDefaultDaoFactory().getResponsavelDao().buscar(new Responsavel(idResponsavel));
      Especie especie = DaoFactory.getDefaultDaoFactory().getEspecieDao().buscar(new Especie(idEspecie));
      Raca raca = DaoFactory.getDefaultDaoFactory().getRacaDao().buscar(new Raca(idRaca));
      if (!pararEmId) {
        Pet pet = new Pet(id, responsavel, nome, genero, especie, raca, dataNascimento);
        listaPet.add(pet);
      } else if (id == regProcurado.getId()) {
        regProcurado.setResponsavel(responsavel);
        regProcurado.setNome(nome);
        regProcurado.setGenero(genero);
        regProcurado.setEspecie(especie);
        regProcurado.setRaca(raca);
        regProcurado.setNascimento(dataNascimento);
        return;
      }
    }
  }

  private int obterMaiorId(ArrayList<Pet> lista) {
    int maiorId = -1;
    for (Pet p : lista) {
      if (p.getId() > maiorId) {
        maiorId = p.getId();
      }
    }
    return maiorId;
  }

  private boolean salvarListaEmArquivo(ArrayList<Pet> lista) {
    try {
      FileWriter f = new FileWriter(UtilConstantes.CAMINHO_ARQUIVO_PET);
      PrintWriter pw = new PrintWriter(f);
      pw.println("[Número de registros]");
      pw.println(lista.size());
      pw.println("[Registros]");
      for (Pet registro : lista) {
        pw.println(registro.getId());
        pw.println(registro.getResponsavel().getId());
        pw.println(registro.getNome());
        pw.println(registro.getGenero());
        pw.println(registro.getEspecie().getId());
        pw.println(registro.getRaca().getId());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        pw.println(df.format(registro.getNascimento()));
      }
      pw.close();
      return true;
    } catch (IOException ex) {
      JOptionPane.showMessageDialog(null, "Falha ao escrever em arquivo de pets.",
              "Erro", JOptionPane.ERROR_MESSAGE);
    }
    return false;
  }

  @Override
  public ArrayList<Pet> getLista() {
    List<String> listaLinhas = lerArquivo();
    ArrayList<Pet> lista = new ArrayList<>();
    if (listaLinhas != null) {
      obterRegistrosDeListaDeLinhas(listaLinhas, lista, false, null);
    }
    return lista;
  }

  @Override
  public Pet buscar(Entidade pet) {
    List<String> listaLinhas = lerArquivo();
    Pet registro = new Pet(pet.getId());
    obterRegistrosDeListaDeLinhas(listaLinhas, null, true, registro);
    return registro;
  }

  @Override
  public boolean inserir(Pet pet) {
    ArrayList<Pet> lista = getLista();
    int maiorId = obterMaiorId(lista);
    pet.setId(maiorId + 1);
    lista.add(pet);
    return salvarListaEmArquivo(lista);
  }

  @Override
  public boolean alterar(Pet regNovo) {
    ArrayList<Pet> lista = getLista();
    for (Pet regVelho : lista) {
      if (regVelho.getId() == regNovo.getId()) {
        regVelho.setResponsavel(regNovo.getResponsavel());
        regVelho.setNome(regNovo.getNome());
        regVelho.setGenero(regNovo.getGenero());
        regVelho.setEspecie(regNovo.getEspecie());
        regVelho.setRaca(regNovo.getRaca());
        regVelho.setNascimento(regNovo.getNascimento());
        return salvarListaEmArquivo(lista);
      }
    }
    return false;
  }

  @Override
  public boolean excluir(Entidade pet) {
    ArrayList<Pet> lista = getLista();
    for (int i = 0; i < lista.size(); ++i) {
      if (lista.get(i).getId() == pet.getId()) {
        lista.remove(i);
        return salvarListaEmArquivo(lista);
      }
    }
    return false;
  }

}
