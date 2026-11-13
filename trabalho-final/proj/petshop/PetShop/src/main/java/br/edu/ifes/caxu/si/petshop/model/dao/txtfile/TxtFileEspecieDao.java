package br.edu.ifes.caxu.si.petshop.model.dao.txtfile;

import br.edu.ifes.caxu.si.petshop.model.Entidade;
import br.edu.ifes.caxu.si.petshop.model.Especie;
import br.edu.ifes.caxu.si.petshop.model.dao.EspecieDao;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author alexandre
 */
public class TxtFileEspecieDao implements EspecieDao {
    
    private String caminhoArquivo = "txt_data/especie.txt";
    
    private ArrayList<String> lerArquivo() {
        ArrayList<String> listaEspecies = new ArrayList<>();
        FileInputStream fileInputStreamEspecie;
        try {
            fileInputStreamEspecie = new FileInputStream(caminhoArquivo);
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
            
            Especie especie = new Especie(id, nome);
            lista.add(especie);
        }
        return lista;
    }

    @Override
    public Especie buscar(Entidade responsavel) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean inserir(Especie responsavel) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean alterar(Especie responsavel) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean excluir(Especie responsavel) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
