package br.edu.ifes.caxu.si.petshop.view;

import br.edu.ifes.caxu.si.petshop.model.Especie;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author alexr
 */
public class EspecieTableModel extends AbstractTableModel {
    
    private final ArrayList<Especie> listaEspecie;
    
    private final String[] colunas = {
        "Nome"
    };
    
    public EspecieTableModel(ArrayList<Especie> listaEspecie) {
        super();
        this.listaEspecie = listaEspecie;
    }

    @Override
    public int getRowCount() {
        return listaEspecie.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Especie linha = listaEspecie.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> linha.getNome();
            default -> null;
        };
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }
    
    
}
