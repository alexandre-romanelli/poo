package br.edu.ifes.caxu.si.petshop.view;

import br.edu.ifes.caxu.si.petshop.model.Pet;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author alexandre
 */
public class PetTableModel extends AbstractTableModel {
    
    private final ArrayList<Pet> listaPet;
    private final String[] colunas = {
        "Responsável", 
        "Pet", 
        "Gênero", 
        "Espécie", 
        "Raça", 
        "Idade"
    }; 
    
    public PetTableModel(ArrayList<Pet> listaPet) {
        this.listaPet = listaPet;
    }

    @Override
    public int getRowCount() {
        return listaPet.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (rowIndex >= 0 && rowIndex < listaPet.size() && 
                columnIndex >= 0 && columnIndex < colunas.length) {
            Pet p = listaPet.get(rowIndex);
            switch (columnIndex) {
                case 0: return p.getResponsavel();
                case 1: return p.getNome();
                case 2: return p.getGenero();
                case 3: return p.getEspecie();
                case 4: return p.getRaca();
                case 5: return p.getIdade();
            }
        }
        return null;
    }
    
}
