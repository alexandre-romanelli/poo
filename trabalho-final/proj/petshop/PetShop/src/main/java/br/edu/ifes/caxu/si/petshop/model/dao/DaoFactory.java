package br.edu.ifes.caxu.si.petshop.model.dao;

import br.edu.ifes.caxu.si.petshop.model.dao.sqlite.SqliteDaoFactory;
import br.edu.ifes.caxu.si.petshop.model.dao.txtfile.TxtFileDaoFactory;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
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
        switch (tipo) {
            case CODIGO_SQLITE: return new SqliteDaoFactory();
            case CODIGO_ARQUIVO_TEXTO: return new TxtFileDaoFactory();
            default: return null;
        }
    }
    
    public static DaoFactory getDefaultDaoFactory() {
        FileInputStream fileInputStreamConfiguracao;
        try {
            fileInputStreamConfiguracao = new FileInputStream("conf/configuracao.txt");
            InputStreamReader inputStreamConfiguracao = new InputStreamReader(fileInputStreamConfiguracao, StandardCharsets.UTF_8);
            BufferedReader bufferedReaderConfiguracao = new BufferedReader(inputStreamConfiguracao);
            bufferedReaderConfiguracao.readLine();
            int opcao = Integer.parseInt(bufferedReaderConfiguracao.readLine().trim());
            return getDaoFactory(opcao);
            
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Arquivo de configuração não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao ler arquivo de configuração.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
    
    public abstract ResponsavelDao getResponsavelDao();
    
    public abstract EspecieDao getEspecieDao();
    
    public abstract RacaDao getRacaDao();
    
    public abstract PetDao getPetDao();
}
