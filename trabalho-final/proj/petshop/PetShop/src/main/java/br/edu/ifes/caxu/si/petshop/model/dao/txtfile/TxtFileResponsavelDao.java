package br.edu.ifes.caxu.si.petshop.model.dao.txtfile;

import br.edu.ifes.caxu.si.petshop.model.Entidade;
import br.edu.ifes.caxu.si.petshop.model.Especie;
import br.edu.ifes.caxu.si.petshop.model.Responsavel;
import br.edu.ifes.caxu.si.petshop.model.dao.ResponsavelDao;
import br.edu.ifes.caxu.si.petshop.util.UtilConstantes;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author alexandre
 */
public class TxtFileResponsavelDao implements ResponsavelDao {

    private ArrayList<String> lerArquivo() {
        ArrayList<String> listaResponsavel = new ArrayList<>();
        FileInputStream fileInputStreamResponsavel;
        try {
            fileInputStreamResponsavel = new FileInputStream(UtilConstantes.CAMINHO_ARQUIVO_RESPONSAVEL);
            InputStreamReader inputStreamResponsavel = new InputStreamReader(fileInputStreamResponsavel, StandardCharsets.UTF_8);
            BufferedReader bufferedReaderResponsavel = new BufferedReader(inputStreamResponsavel);
            List<String> listaLinhas = bufferedReaderResponsavel.readAllLines();
            listaResponsavel.addAll(listaLinhas);
            
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Arquivo de responsáveis não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao ler arquivo de responsáveis.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return listaResponsavel;
    }

    @Override
    public ArrayList<Responsavel> getLista() {
        ArrayList<Responsavel> lista = new ArrayList<>();
        ArrayList<String> listaLinhas = lerArquivo();
        int numRegistros = Integer.parseInt(listaLinhas.get(1));
        int linhaInicioRegistro = 3;
        int linhasPorRegistro = 4;
        for (int indiceRegistro = 0; indiceRegistro < numRegistros; ++indiceRegistro) {
            // identificador
            int id = Integer.parseInt(listaLinhas.get(indiceRegistro * linhasPorRegistro + linhaInicioRegistro + 0));
            // nome
            String nome = listaLinhas.get(indiceRegistro * linhasPorRegistro + linhaInicioRegistro + 1);
            // telefone
            String telefone = listaLinhas.get(indiceRegistro * linhasPorRegistro + linhaInicioRegistro + 2);
            // endereço
            String endereco = listaLinhas.get(indiceRegistro * linhasPorRegistro + linhaInicioRegistro + 3);
            // responsável
            Responsavel responsavel = new Responsavel(id, nome, telefone, endereco);
            lista.add(responsavel);
        }
        return lista;
    }

    @Override
    public Responsavel buscar(Entidade responsavel) {
        Responsavel registroBuscado = new Responsavel(-1, "", "", "");
        ArrayList<String> listaLinhas = lerArquivo();
        int numRegistros = Integer.parseInt(listaLinhas.get(1));
        int linhaInicioRegistro = 3;
        int linhasPorRegistro = 4;
        for (int indiceRegistro = 0; indiceRegistro < numRegistros; ++indiceRegistro) {
            // identificador
            int id = Integer.parseInt(listaLinhas.get(indiceRegistro * linhasPorRegistro + linhaInicioRegistro + 0));
            if (id == responsavel.getId()) {
                // nome
                String nome = listaLinhas.get(indiceRegistro * linhasPorRegistro + linhaInicioRegistro + 1);
                // telefone
                String telefone = listaLinhas.get(indiceRegistro * linhasPorRegistro + linhaInicioRegistro + 2);
                // nome
                String endereco = listaLinhas.get(indiceRegistro * linhasPorRegistro + linhaInicioRegistro + 3);
                // espécie
                registroBuscado = new Responsavel(id, nome, telefone, endereco);
                break;
            }
        }
        return registroBuscado;
    }
    
    private int obtemMaiorId() {
        int maiorId = -1;
        ArrayList<Responsavel> listaResponsaveis = getLista();
        for (Responsavel registro : listaResponsaveis) {
            if (registro.getId() > maiorId) {
                maiorId = registro.getId();
            }
        }
        return maiorId;
    }
    
    private boolean salvarListaEmArquivo(ArrayList<Responsavel> lista) {
        boolean resultado = true;
        FileOutputStream fileOutputStreamResponsavel;
        try {
            fileOutputStreamResponsavel = new FileOutputStream(UtilConstantes.CAMINHO_ARQUIVO_RESPONSAVEL);
            OutputStreamWriter outputStreamResponsavel = new OutputStreamWriter(fileOutputStreamResponsavel, StandardCharsets.UTF_8);
            outputStreamResponsavel.write("[Número de registros]\n");
            outputStreamResponsavel.write(Integer.toString(lista.size()) + "\n");
            outputStreamResponsavel.write("[Registros]\n");
            for (Responsavel responsavel : lista) {
                outputStreamResponsavel.write(Integer.toString(responsavel.getId()) + "\n");
                outputStreamResponsavel.write(responsavel.getNome() + "\n");
                outputStreamResponsavel.write(responsavel.getTelefone()+ "\n");
                outputStreamResponsavel.write(responsavel.getEndereco()+ "\n");
            }
            outputStreamResponsavel.close();
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Arquivo de dados não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
            resultado = false;
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao escrever em arquivo.", "Erro", JOptionPane.ERROR_MESSAGE);
            resultado = false;
        }
        return resultado;
    }

    @Override
    public boolean inserir(Responsavel responsavel) {
        int maiorId = obtemMaiorId();
        responsavel.setIdResponsavel(maiorId + 1);
        ArrayList<Responsavel> listaResponsavel = getLista();
        listaResponsavel.add(responsavel);
        return salvarListaEmArquivo(listaResponsavel);
    }

    @Override
    public boolean alterar(Responsavel responsavel) {
        ArrayList<Responsavel> listaResponsaveis = getLista();
        for (Responsavel r : listaResponsaveis) {
            if (r.getId() == responsavel.getId()) {
                r.setNome(responsavel.getNome());
                r.setTelefone(responsavel.getTelefone());
                r.setEndereco(responsavel.getEndereco());
            }
        }
        return salvarListaEmArquivo(listaResponsaveis);
    }

    @Override
    public boolean excluir(Responsavel responsavel) {
        ArrayList<Responsavel> listaResponsaveis = getLista();
        for (int ind = 0; ind < listaResponsaveis.size(); ++ind) {
            if (listaResponsaveis.get(ind).getId() == responsavel.getId()) {
                listaResponsaveis.remove(ind);
                return salvarListaEmArquivo(listaResponsaveis);
            }
        }
        return false;
    }
    
}
