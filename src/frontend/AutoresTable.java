package frontend;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

import backend.Autores;

@SuppressWarnings("serial")
public class AutoresTable extends AbstractTableModel{
	private List<Autores> autores_lista;
	
    public AutoresTable(List<Autores> autores) {
        this.autores_lista = new ArrayList<>(autores);
    }

    private String[] columnNames = {"ID", "Nome do Autor", "Data de Nascimento", "País de Origem"};

    @Override
    public String getColumnName(int columnIndex){
         return columnNames[columnIndex];
    }

    @Override     
    public int getRowCount() {
        return autores_lista.size();
    }

    @Override        
    public int getColumnCount() {
        return 4; 
    }
    
    public void removeRow(int row) {
    	autores_lista.remove(row);
        fireTableRowsDeleted(row, row);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Autores aut = autores_lista.get(rowIndex);
        switch (columnIndex) {
            case 0: 
                return aut.getId();
            case 1:
                return aut.getNome();
            case 2:
                return aut.getDataNascimento();
            case 3:
                return aut.getPais();
        }
        return null;
   }

   @Override
   public Class<?> getColumnClass(int columnIndex){
          switch (columnIndex){
             case 0:
               return Integer.class;
             case 1:
               return String.class;
             case 2:
               return Date.class;
             case 3:
               return String.class;
            }
          return null;
     }        
}        