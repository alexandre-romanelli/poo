package br.edu.ifes.caxu.si.petshop.model.dao.txtfile;

import br.edu.ifes.caxu.si.petshop.model.Entidade;
import br.edu.ifes.caxu.si.petshop.model.Responsavel;
import br.edu.ifes.caxu.si.petshop.model.dao.ResponsavelDao;
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
public class TxtFileResponsavelDao implements ResponsavelDao {

  private static int INDICE_INICIO_REGISTROS = 3;
  private int LINHAS_POR_REGISTRO = 4;

  private List<String> lerArquivo() {
    FileInputStream file;
    try {
      file = new FileInputStream(UtilConstantes.CAMINHO_ARQUIVO_RESPONSAVEL);
      InputStreamReader inputStream = new InputStreamReader(file, StandardCharsets.UTF_8);
      List<String> listaLinhas = inputStream.readAllLines();
      inputStream.close();
      return listaLinhas;
    } catch (FileNotFoundException ex) {
      JOptionPane.showMessageDialog(null, "Arquivo de responsáveis não encontrado.",
              "Erro", JOptionPane.ERROR_MESSAGE);
    } catch (IOException ex) {
      JOptionPane.showMessageDialog(null, "Falha ao ler arquivo de responsáveis.",
              "Erro", JOptionPane.ERROR_MESSAGE);
    }
    return null;
  }

  private void obterRegistrosDeListaDeLinhas(List<String> linhas,
          ArrayList<Responsavel> lista,
          boolean pararEmId,
          Responsavel regProcurado) {
    int numRegistros = Integer.parseInt(linhas.get(1));
    for (int indRegistro = 0; indRegistro < numRegistros; ++indRegistro) {
      int posRegistro = INDICE_INICIO_REGISTROS + LINHAS_POR_REGISTRO * indRegistro;
      int id = Integer.parseInt(linhas.get(posRegistro));
      String nome = linhas.get(posRegistro + 1);
      String telefone = linhas.get(posRegistro + 2);
      String endereco = linhas.get(posRegistro + 3);
      if (!pararEmId) {
        Responsavel responsavel = new Responsavel(id, nome, telefone, endereco);
        lista.add(responsavel);
      } else if (id == regProcurado.getId()) {
        regProcurado.setNome(nome);
        regProcurado.setTelefone(telefone);
        regProcurado.setEndereco(endereco);
        return;
      }
    }
  }

  private int obterMaiorId(ArrayList<Responsavel> lista) {
    int maiorId = -1;
    for (Responsavel r : lista) {
      if (r.getId() > maiorId) {
        maiorId = r.getId();
      }
    }
    return maiorId;
  }

  private boolean salvarListaEmArquivo(ArrayList<Responsavel> lista) {
    try {
      FileWriter f = new FileWriter(UtilConstantes.CAMINHO_ARQUIVO_RESPONSAVEL);
      PrintWriter pw = new PrintWriter(f);
      pw.println("[Número de registros]");
      pw.println(lista.size());
      pw.println("[Registros]");
      for (Responsavel registro : lista) {
        pw.println(registro.getId());
        pw.println(registro.getNome());
        pw.println(registro.getTelefone());
        pw.println(registro.getEndereco());
      }
      pw.close();
      return true;
    } catch (IOException ex) {
      JOptionPane.showMessageDialog(null, "Falha ao escrever em arquivo de responsáveis.",
              "Erro", JOptionPane.ERROR_MESSAGE);
    }
    return false;
  }

  @Override
  public ArrayList<Responsavel> getLista() {
    List<String> listaLinhas = lerArquivo();
    ArrayList<Responsavel> listaRegistros = new ArrayList<>();
    obterRegistrosDeListaDeLinhas(listaLinhas, listaRegistros, false, null);
    return listaRegistros;
  }

  @Override
  public Responsavel buscar(Entidade responsavel) {
    List<String> listaLinhas = lerArquivo();
    Responsavel regProcurado = new Responsavel(responsavel.getId());
    obterRegistrosDeListaDeLinhas(listaLinhas, null, true, regProcurado);
    return regProcurado;
  }

  @Override
  public boolean inserir(Responsavel responsavel) {
    ArrayList<Responsavel> lista = getLista();
    int maiorId = obterMaiorId(lista);
    responsavel.setId(maiorId + 1);
    lista.add(responsavel);
    return salvarListaEmArquivo(lista);
  }

  @Override
  public boolean alterar(Responsavel responsavel) {
    ArrayList<Responsavel> lista = getLista();
    for (Responsavel r : lista) {
      if (r.getId() == responsavel.getId()) {
        r.setNome(responsavel.getNome());
        r.setTelefone(responsavel.getTelefone());
        r.setEndereco(responsavel.getEndereco());
      }
    }
    return salvarListaEmArquivo(lista);
  }

  @Override
  public boolean excluir(Responsavel responsavel) {
    ArrayList<Responsavel> lista = getLista();
    for (int ind = 0; ind < lista.size(); ++ind) {
      if (lista.get(ind).getId() == responsavel.getId()) {
        lista.remove(ind);
        return salvarListaEmArquivo(lista);
      }
    }
    return false;
  }
}
