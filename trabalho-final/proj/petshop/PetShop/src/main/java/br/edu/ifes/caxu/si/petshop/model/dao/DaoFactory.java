package br.edu.ifes.caxu.si.petshop.model.dao;

import br.edu.ifes.caxu.si.petshop.model.dao.sqlite.SqliteDaoFactory;
import br.edu.ifes.caxu.si.petshop.model.dao.txtfile.TxtFileDaoFactory;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author alexandre
 */
public abstract class DaoFactory {

  public static final int CODIGO_SQLITE = 1;
  public static final int CODIGO_ARQUIVO_TEXTO = 2;
  public static final int CODIGO_POSTGRESQL = 3;

  public static DaoFactory getDaoFactory(int tipo) {
    return switch (tipo) {
      case CODIGO_SQLITE ->
        new SqliteDaoFactory();
      case CODIGO_ARQUIVO_TEXTO ->
        new TxtFileDaoFactory();
      default ->
        null;
    };
  }

  public static DaoFactory getDefaultDaoFactory() {
    try {
      FileInputStream file = new FileInputStream("conf/configuracao.txt");
      Scanner scanner = new Scanner(file);
      scanner.nextLine(); // [Default DAO Factory]
      int opcao = scanner.nextInt(); // o número que indica a opção de DAO Factory
      return getDaoFactory(opcao);

    } catch (FileNotFoundException ex) {
      JOptionPane.showMessageDialog(null, "Arquivo de configuração não encontrado.",
              "Erro", JOptionPane.ERROR_MESSAGE);
    }
    return null;
  }

  public abstract ResponsavelDao getResponsavelDao();

  public abstract EspecieDao getEspecieDao();

  public abstract RacaDao getRacaDao();

  public abstract PetDao getPetDao();
}
