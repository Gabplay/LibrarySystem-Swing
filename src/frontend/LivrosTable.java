package frontend;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

import backend.Livros;

@SuppressWarnings("serial")
public class LivrosTable extends AbstractTableModel{
	private List<Livros> livros_lista;
	
    public LivrosTable(List<Livros> livros) {
        this.livros_lista = new ArrayList<>(livros);
    }

    private String[] columnNames = {"ISBN", "Título", "Autor", "Editora", "Estoque"};

    @Override
    public String getColumnName(int columnIndex){
         return columnNames[columnIndex];
    }

    @Override     
    public int getRowCount() {
        return livros_lista.size();
    }

    @Override        
    public int getColumnCount() {
        return 5; 
    }
    
    public void removeRow(int row) {
    	livros_lista.remove(row);
        fireTableRowsDeleted(row, row);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Livros l = livros_lista.get(rowIndex);
        switch (columnIndex) {
            case 0: 
                return l.getIsbn();
            case 1:
                return l.getTitulo();
            case 2:
                return l.getAutor();
            case 3:
                return l.getEditora();
            case 4:
            	return l.getQtdeEstoque();
        }
        return null;
   }

   @Override
   public Class<?> getColumnClass(int columnIndex){
          switch (columnIndex){
            case 0:
            	return String.class;
            case 1:
            	return String.class;
            case 2:
            	return String.class;
            case 3:
            	return String.class;
            case 4:
            	return Integer.class;
          }
          return null;
     }        
}        