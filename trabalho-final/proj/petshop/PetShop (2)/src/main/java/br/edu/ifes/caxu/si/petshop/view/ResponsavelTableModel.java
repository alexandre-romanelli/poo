package br.edu.ifes.caxu.si.petshop.view;

import br.edu.ifes.caxu.si.petshop.model.Responsavel;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author alexr
 */
public class ResponsavelTableModel extends AbstractTableModel {
    
    private final ArrayList<Responsavel> listaResponsavel;
    private final String[] colunas = {
        "Nome",
        "Telefone",
        "Endereço"
    };
    
    public ResponsavelTableModel(ArrayList<Responsavel> listaResponsavel) {
        super();
        this.listaResponsavel = listaResponsavel;
    }

    @Override
    public int getRowCount() {
        return listaResponsavel.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Responsavel linha = listaResponsavel.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> linha.getNome();
            case 1 -> linha.getTelefone();
            case 2 -> linha.getEndereco();
            default -> null;
        };
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }
    
}
