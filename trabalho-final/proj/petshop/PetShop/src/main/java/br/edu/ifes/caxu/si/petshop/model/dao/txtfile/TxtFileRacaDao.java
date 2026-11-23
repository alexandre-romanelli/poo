package br.edu.ifes.caxu.si.petshop.model.dao.txtfile;

import br.edu.ifes.caxu.si.petshop.model.Entidade;
import br.edu.ifes.caxu.si.petshop.model.Especie;
import br.edu.ifes.caxu.si.petshop.model.Raca;
import br.edu.ifes.caxu.si.petshop.model.dao.DaoFactory;
import br.edu.ifes.caxu.si.petshop.model.dao.RacaDao;
import br.edu.ifes.caxu.si.petshop.util.UtilConstantes;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author alexandre
 */
public class TxtFileRacaDao implements RacaDao {

  private static int INDICE_INICIO_REGISTROS = 3;
  private int LINHAS_POR_REGISTRO = 3;

  private List<String> lerArquivo() {
    FileInputStream file;
    try {
      file = new FileInputStream(UtilConstantes.CAMINHO_ARQUIVO_RACA);
      InputStreamReader inputStream = new InputStreamReader(file, StandardCharsets.UTF_8);
      List<String> listaLinhas = inputStream.readAllLines();
      inputStream.close();
      return listaLinhas;
    } catch (FileNotFoundException ex) {
      JOptionPane.showMessageDialog(null, "Arquivo de raças não encontrado.",
              "Erro", JOptionPane.ERROR_MESSAGE);
    } catch (IOException ex) {
      JOptionPane.showMessageDialog(null, "Falha ao ler arquivo de raças.",
              "Erro", JOptionPane.ERROR_MESSAGE);
    }
    return null;
  }

  private void obterRegistrosDeListaDeLinhas(List<String> linhas,
          ArrayList<Raca> listaEspecie,
          boolean pararEmId,
          Raca regProcurado) {
    int numRegistros = Integer.parseInt(linhas.get(1));
    for (int indRegistro = 0; indRegistro < numRegistros; ++indRegistro) {
      int posRegistro = INDICE_INICIO_REGISTROS + LINHAS_POR_REGISTRO * indRegistro;
      int id = Integer.parseInt(linhas.get(posRegistro));
      int idEspecie = Integer.parseInt(linhas.get(posRegistro + 1));
      Especie especie = DaoFactory.getDefaultDaoFactory().getEspecieDao().buscar(new Especie(idEspecie));
      String nome = linhas.get(posRegistro + 2);
      if (!pararEmId) {
        Raca pet = new Raca(id, especie, nome);
        listaEspecie.add(pet);
      } else if (id == regProcurado.getId()) {
        regProcurado.setEspecie(especie);
        regProcurado.setNome(nome);
        return;
      }
    }
  }

  private int obterMaiorId(ArrayList<Raca> lista) {
    int maiorId = -1;
    for (Raca e : lista) {
      if (e.getId() > maiorId) {
        maiorId = e.getId();
      }
    }
    return maiorId;
  }

  private boolean salvarListaEmArquivo(ArrayList<Raca> lista) {
    try {
      FileWriter f = new FileWriter(UtilConstantes.CAMINHO_ARQUIVO_RACA);
      PrintWriter pw = new PrintWriter(f);
      pw.println("[Número de registros]");
      pw.println(lista.size());
      pw.println("[Registros]");
      for (Raca registro : lista) {
        pw.println(registro.getId());
        pw.println(registro.getEspecie().getId());
        pw.println(registro.getNome());
      }
      pw.close();
      return true;
    } catch (FileNotFoundException ex) {
      JOptionPane.showMessageDialog(null, "Arquivo de raças não encontrado.",
              "Erro", JOptionPane.ERROR_MESSAGE);
    } catch (IOException ex) {
      JOptionPane.showMessageDialog(null, "Falha ao escrever em arquivo de raças.",
              "Erro", JOptionPane.ERROR_MESSAGE);
    }
    return false;
  }

  @Override
  public ArrayList<Raca> getLista() {
    List<String> listaLinhas = lerArquivo();
    ArrayList<Raca> listaRegistros = new ArrayList<>();
    obterRegistrosDeListaDeLinhas(listaLinhas, listaRegistros, false, null);
    return listaRegistros;
  }

  @Override
  public Raca buscar(Entidade raca) {
    List<String> listaLinhas = lerArquivo();
    Raca regProcurado = new Raca(raca.getId());
    obterRegistrosDeListaDeLinhas(listaLinhas, null, true, regProcurado);
    return regProcurado;
  }

  @Override
  public boolean inserir(Raca raca) {
    ArrayList<Raca> lista = getLista();
    int maiorId = obterMaiorId(lista);
    raca.setId(maiorId + 1);
    lista.add(raca);
    return salvarListaEmArquivo(lista);
  }

  @Override
  public boolean alterar(Raca raca) {
    ArrayList<Raca> lista = getLista();
    for (Raca r : lista) {
      if (r.getId() == raca.getId()) {
        r.setEspecie(raca.getEspecie());
        r.setNome(raca.getNome());
      }
    }
    return salvarListaEmArquivo(lista);
  }

  @Override
  public boolean excluir(Raca raca) {
    ArrayList<Raca> lista = getLista();
    for (int ind = 0; ind < lista.size(); ++ind) {
      if (lista.get(ind).getId() == raca.getId()) {
        lista.remove(ind);
        return salvarListaEmArquivo(lista);
      }
    }
    return false;
  }
}
