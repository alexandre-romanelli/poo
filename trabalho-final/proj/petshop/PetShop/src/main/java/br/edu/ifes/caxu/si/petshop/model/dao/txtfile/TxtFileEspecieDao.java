package br.edu.ifes.caxu.si.petshop.model.dao.txtfile;

import br.edu.ifes.caxu.si.petshop.model.Entidade;
import br.edu.ifes.caxu.si.petshop.model.Especie;
import br.edu.ifes.caxu.si.petshop.model.dao.EspecieDao;
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
public class TxtFileEspecieDao implements EspecieDao {
    
    private ArrayList<String> lerArquivo() {
        ArrayList<String> listaEspecies = new ArrayList<>();
        FileInputStream fileInputStreamEspecie;
        try {
            fileInputStreamEspecie = new FileInputStream(UtilConstantes.CAMINHO_ARQUIVO_ESPECIES);
            InputStreamReader inputStreamEspecie = new InputStreamReader(fileInputStreamEspecie, StandardCharsets.UTF_8);
            BufferedReader bufferedReaderEspecie = new BufferedReader(inputStreamEspecie);
            List<String> listaLinhas = bufferedReaderEspecie.readAllLines();
            listaEspecies.addAll(listaLinhas);
            
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Arquivo de espécies não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao ler arquivo de espécies.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return listaEspecies;
    }

    @Override
    public ArrayList<Especie> getLista() {
        ArrayList<Especie> lista = new ArrayList<>();
        ArrayList<String> listaLinhas = lerArquivo();
        int numRegistros = Integer.parseInt(listaLinhas.get(1));
        int linhaInicioRegistro = 3;
        for (int indiceRegistro = 0; indiceRegistro < numRegistros; ++indiceRegistro) {
            // identificador
            int id = Integer.parseInt(listaLinhas.get(indiceRegistro * 2 + linhaInicioRegistro + 0));
            // nome
            String nome = listaLinhas.get(indiceRegistro * 2 + linhaInicioRegistro + 1);
            // espécie
            Especie especie = new Especie(id, nome);
            lista.add(especie);
        }
        return lista;
    }

    @Override
    public Especie buscar(Entidade especie) {
        Especie registroBuscado = new Especie(-1, "");
        ArrayList<String> listaLinhas = lerArquivo();
        int numRegistros = Integer.parseInt(listaLinhas.get(1));
        int linhaInicioRegistro = 3;
        for (int indiceRegistro = 0; indiceRegistro < numRegistros; ++indiceRegistro) {
            // identificador
            int id = Integer.parseInt(listaLinhas.get(indiceRegistro * 2 + linhaInicioRegistro + 0));
            if (id == especie.getId()) {
                // nome
                String nome = listaLinhas.get(indiceRegistro * 2 + linhaInicioRegistro + 1);
                // espécie
                registroBuscado = new Especie(id, nome);
                break;
            }
        }
        return registroBuscado;
    }
    
    private int obtemMaiorId() {
        int maiorId = -1;
        ArrayList<Especie> listaEspecies = getLista();
        for (Especie registro : listaEspecies) {
            if (registro.getId() > maiorId) {
                maiorId = registro.getId();
            }
        }
        return maiorId;
    }
    
    private boolean salvarListaEmArquivo(ArrayList<Especie> lista) {
        boolean resultado = true;
        FileOutputStream fileOutputStreamEspecie;
        try {
            fileOutputStreamEspecie = new FileOutputStream(UtilConstantes.CAMINHO_ARQUIVO_ESPECIES);
            OutputStreamWriter outputStreamEspecie = new OutputStreamWriter(fileOutputStreamEspecie, StandardCharsets.UTF_8);
            outputStreamEspecie.write("[Número de registros]\n");
            outputStreamEspecie.write(Integer.toString(lista.size()) + "\n");
            outputStreamEspecie.write("[Registros]\n");
            for (Especie especie : lista) {
                outputStreamEspecie.write(Integer.toString(especie.getId()) + "\n");
                outputStreamEspecie.write(especie.getNome() + "\n");
            }
            outputStreamEspecie.close();
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
    public boolean inserir(Especie especie) {
        int maiorId = obtemMaiorId();
        especie.setId(maiorId + 1);
        ArrayList<Especie> listaEspecies = getLista();
        listaEspecies.add(especie);
        return salvarListaEmArquivo(listaEspecies);
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
