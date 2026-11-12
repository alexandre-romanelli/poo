package br.edu.ifes.caxu.si.petshop.view;

import br.edu.ifes.caxu.si.petshop.model.Raca;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author alexandre
 */
public class RacaTableModel extends AbstractTableModel {
    
    private final ArrayList<Raca> listaRaca;
    
    private final String[] colunas = {
        "Espécie",
        "Nome"
    };
    
    public RacaTableModel(ArrayList<Raca> listaRaca) {
        super();
        this.listaRaca = listaRaca;
    }

    @Override
    public int getRowCount() {
        return listaRaca.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Raca linha = listaRaca.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> linha.getEspecie().getNome();
            case 1 -> linha.getNome();
            default -> null;
        };
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }
    
    
    
}
