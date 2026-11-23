package br.edu.ifes.caxu.si.petshop.model.dao.txtfile;

import br.edu.ifes.caxu.si.petshop.model.Entidade;
import br.edu.ifes.caxu.si.petshop.model.Especie;
import br.edu.ifes.caxu.si.petshop.model.dao.EspecieDao;
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
public class TxtFileEspecieDao implements EspecieDao {

  private static int INDICE_INICIO_REGISTROS = 3;
  private int LINHAS_POR_REGISTRO = 2;

  private List<String> lerArquivo() {
    FileInputStream file;
    try {
      file = new FileInputStream(UtilConstantes.CAMINHO_ARQUIVO_ESPECIES);
      InputStreamReader inputStream = new InputStreamReader(file, StandardCharsets.UTF_8);
      List<String> listaLinhas = inputStream.readAllLines();
      inputStream.close();
      return listaLinhas;
    } catch (FileNotFoundException ex) {
      JOptionPane.showMessageDialog(null, "Arquivo de espécies não encontrado.",
              "Erro", JOptionPane.ERROR_MESSAGE);
    } catch (IOException ex) {
      JOptionPane.showMessageDialog(null, "Falha ao ler arquivo de espécies.",
              "Erro", JOptionPane.ERROR_MESSAGE);
    }
    return null;
  }

  private void obterRegistrosDeListaDeLinhas(List<String> linhas,
          ArrayList<Especie> listaEspecie,
          boolean pararEmId,
          Especie regProcurado) {
    int numRegistros = Integer.parseInt(linhas.get(1));
    for (int indRegistro = 0; indRegistro < numRegistros; ++indRegistro) {
      int posRegistro = INDICE_INICIO_REGISTROS + LINHAS_POR_REGISTRO * indRegistro;
      int id = Integer.parseInt(linhas.get(posRegistro));
      String nome = linhas.get(posRegistro + 1);
      if (!pararEmId) {
        Especie especie = new Especie(id, nome);
        listaEspecie.add(especie);
      } else if (id == regProcurado.getId()) {
        regProcurado.setNome(nome);
        return;
      }
    }
  }

  private int obterMaiorId(ArrayList<Especie> lista) {
    int maiorId = -1;
    for (Especie e : lista) {
      if (e.getId() > maiorId) {
        maiorId = e.getId();
      }
    }
    return maiorId;
  }

  private boolean salvarListaEmArquivo(ArrayList<Especie> lista) {
    try {
      FileWriter f = new FileWriter(UtilConstantes.CAMINHO_ARQUIVO_ESPECIES);
      PrintWriter pw = new PrintWriter(f);
      pw.println("[Número de registros]");
      pw.println(lista.size());
      pw.println("[Registros]");
      for (Especie registro : lista) {
        pw.println(registro.getId());
        pw.println(registro.getNome());
      }
      pw.close();
      return true;
    } catch (IOException ex) {
      JOptionPane.showMessageDialog(null, "Falha ao escrever em arquivo de espécies.",
              "Erro", JOptionPane.ERROR_MESSAGE);
    }
    return false;
  }

  @Override
  public ArrayList<Especie> getLista() {
    List<String> listaLinhas = lerArquivo();
    ArrayList<Especie> listaRegistros = new ArrayList<>();
    obterRegistrosDeListaDeLinhas(listaLinhas, listaRegistros, false, null);
    return listaRegistros;
  }

  @Override
  public Especie buscar(Entidade especie) {
    List<String> listaLinhas = lerArquivo();
    Especie regProcurado = new Especie(especie.getId());
    obterRegistrosDeListaDeLinhas(listaLinhas, null, true, regProcurado);
    return regProcurado;
  }

  @Override
  public boolean inserir(Especie especie) {
    ArrayList<Especie> lista = getLista();
    int maiorId = obterMaiorId(lista);
    especie.setId(maiorId + 1);
    lista.add(especie);
    return salvarListaEmArquivo(lista);
  }

  @Override
  public boolean alterar(Especie especie) {
    ArrayList<Especie> listaEspecies = getLista();
    for (Especie e : listaEspecies) {
      if (e.getId() == especie.getId()) {
        e.setNome(especie.getNome());
      }
    }
    return salvarListaEmArquivo(listaEspecies);
  }

  @Override
  public boolean excluir(Especie especie) {
    ArrayList<Especie> listaEspecies = getLista();
    for (int ind = 0; ind < listaEspecies.size(); ++ind) {
      if (listaEspecies.get(ind).getId() == especie.getId()) {
        listaEspecies.remove(ind);
        return salvarListaEmArquivo(listaEspecies);
      }
    }
    return false;
  }
}
