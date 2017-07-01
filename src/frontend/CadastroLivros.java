package frontend;
import backend.Livros;
import backend.LivrosDaoImpl;
import backend.Autores;
import backend.AutoresDaoImpl;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class CadastroLivros extends JPanel{
	private static JTable table = new JTable();
	private static JScrollPane scroll_pane = new JScrollPane();
	private static AutoresDaoImpl funcoes_autores = new AutoresDaoImpl();
	private static LivrosDaoImpl funcoes_banco = new LivrosDaoImpl();
	private static JTextField input_filtro_titulo, input_filtro_autor;
	private static JButton btn_add, btn_del, btn_edit, btn_search;
	
	private static JDialog form = new JDialog();
	private static JLabel lblInfo = new JLabel("Adicionar Livro");
	private static JTextField input_ISBN, input_titulo, input_editora, input_estoque;
	private static JButton btn_save;
	private static final JComboBox<String> cmb_autor = new JComboBox<String>();
	
	public CadastroLivros() {
		table.setRowHeight(30);
		table.getTableHeader().setBackground(Color.orange);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		table.setDefaultRenderer(String.class, centerRenderer);
		table.setDefaultRenderer(Integer.class, centerRenderer);
		setLayout(null);
		table.setModel(new LivrosTable(funcoes_banco.getTodos()));
		table.setAutoCreateRowSorter(true);
	    scroll_pane.setBounds(20, 110, 754, 360);
	    scroll_pane.setViewportView(table);
	    table.setSize(800, 100);
	    
		add(scroll_pane);
		setSize(800,800);
		
		input_filtro_titulo = new JTextField();
		input_filtro_titulo.setBounds(20, 40, 250, 30);
		add(input_filtro_titulo);
		input_filtro_titulo.setColumns(10);
		
		JLabel lblFiltroTitulo = new JLabel("Filtro - Título");
		lblFiltroTitulo.setBounds(20, 15, 80, 14);
		add(lblFiltroTitulo);
		
		input_filtro_autor = new JTextField();
		input_filtro_autor.setBounds(335, 40, 250, 30);
		input_filtro_autor.setColumns(10);
		add(input_filtro_autor);
		
		JLabel lblFiltroAutor = new JLabel("Filtro - Autor");
		lblFiltroAutor.setBounds(335, 15, 135, 14);
		add(lblFiltroAutor);
		
		btn_add = new JButton("Adicionar");
		btn_add.setBounds(166, 500, 135, 41);
		btn_add.setCursor(new Cursor(Cursor.HAND_CURSOR));
		add(btn_add);
		
		btn_edit = new JButton("Editar");
		btn_edit.setBounds(326, 500, 135, 41);
		btn_edit.setCursor(new Cursor(Cursor.HAND_CURSOR));
		add(btn_edit);
		
		btn_del = new JButton("Remover");
		btn_del.setBounds(486, 500, 135, 41);
		btn_del.setCursor(new Cursor(Cursor.HAND_CURSOR));
		add(btn_del);
		
		btn_search = new JButton("Filtrar");
		btn_search.setBounds(632, 35, 135, 41);
		btn_search.setCursor(new Cursor(Cursor.HAND_CURSOR));
		add(btn_search);
		
		
		/****** FORM MODAL ******/
		lblInfo.setBounds(225, 20, 98, 14);
		form.getContentPane().add(lblInfo);
		
		JLabel lblISBN = new JLabel("ISBN");
		lblISBN.setBounds(30, 70, 46, 14);
		form.getContentPane().add(lblISBN);
		
		input_ISBN = new JTextField();
		input_ISBN.setBounds(145, 70, 275, 25);
		form.getContentPane().add(input_ISBN);
		input_ISBN.setColumns(10);
		
		JLabel lblTitulo = new JLabel("Título");
		lblTitulo.setBounds(30, 130, 98, 14);
		form.getContentPane().add(lblTitulo);
		
		input_titulo = new JTextField();
		input_titulo.setBounds(145, 130, 275, 25);
		form.getContentPane().add(input_titulo);
		input_titulo.setColumns(10);
		
		JLabel lblAutor = new JLabel("Autor");
		lblAutor.setBounds(30, 180, 98, 14);
		form.getContentPane().add(lblAutor);
		
		refreshAutores();

		JLabel lblEditora = new JLabel("Editora");
		lblEditora.setBounds(30, 230, 98, 14);
		form.getContentPane().add(lblEditora);
		
		input_editora = new JTextField();
		input_editora.setColumns(10);
		input_editora.setBounds(145, 230, 275, 25);
		form.getContentPane().add(input_editora);

		JLabel lblEstoque = new JLabel("Estoque");
		lblEstoque.setBounds(30, 280, 98, 14);
		form.getContentPane().add(lblEstoque);
		
		input_estoque = new JTextField();
		input_estoque.setColumns(10);
		input_estoque.setBounds(145, 280, 275, 25);
		form.getContentPane().add(input_estoque);
		
		btn_save = new JButton("Salvar");
		btn_save.setBounds(220, 330, 100, 30);
		btn_save.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btn_save.setIcon(UIManager.getIcon("FileView.floppyDriveIcon"));
		form.getContentPane().add(btn_save);
		
		form.setSize(500,420);
		form.setResizable(false);
		form.getContentPane().setLayout(null);
		eventHandlers();
	}
	
	public static void refreshAutores(){
		cmb_autor.removeAllItems();
		for(Autores aut : funcoes_autores.getTodos()) {
		    cmb_autor.addItem(aut.getNome());
		}
		cmb_autor.setMaximumRowCount(9);
		cmb_autor.setBounds(145, 180, 275, 25);
		form.getContentPane().add(cmb_autor);
	}
	
	
	public static void eventHandlers(){
		
		/****** CREATE/ADD ******/
		btn_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblInfo.setText("Adicionar Livro");
				refreshAutores();
				form.setVisible(true);
			}
		});
		
		
		/****** EDIT/UPDATE ******/
		btn_edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(table.getSelectedRows().length == 0){
					JOptionPane.showMessageDialog(null, "Por favor selecione uma linha para ser editada!");
				} else if(table.getSelectedRows().length > 1){
					JOptionPane.showMessageDialog(null, "Por favor selecione apenas uma linha para ser editada!");
				} else{
					lblInfo.setText("Editar Livro");
					refreshAutores();
					input_ISBN.setText((String) table.getValueAt(table.getSelectedRow(), 0));
					input_titulo.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
					cmb_autor.setSelectedItem(table.getValueAt(table.getSelectedRow(), 2));
					input_editora.setText(table.getValueAt(table.getSelectedRow(), 3).toString());
					input_estoque.setText(table.getValueAt(table.getSelectedRow(), 4).toString());
					form.setVisible(true);
				}
			}
		});
		
		
		
		/****** SALVAR ADD/EDIT ******/
		btn_save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				// Check if title input is not empty
				if(input_titulo.getText().trim().equals("") || input_ISBN.getText().trim().equals("") ||
						input_editora.getText().trim().equals("") || input_estoque.getText().trim().equals("")){
					JOptionPane.showMessageDialog(null, "Por favor preencha todos os campos!");
				} else{
					if(lblInfo.getText().equals("Adicionar Livro")){
						System.out.println("Novo Livro: " + input_ISBN.getText() + "," + input_titulo.getText() + "," + cmb_autor.getSelectedItem()
											+ "," + input_editora.getText() + "," + input_estoque.getText());
						
						Livros l = new Livros(input_ISBN.getText(), input_titulo.getText(), cmb_autor.getSelectedItem().toString(), 
											input_editora.getText(), Integer.parseInt(input_estoque.getText()));
						funcoes_banco.inserir(l);
						refreshTable();
						JOptionPane.showMessageDialog(null, "Livro cadastrado com sucesso!");

					} else if(lblInfo.getText().equals("Editar Livro")){
						System.out.println("Editar Livro: " + input_ISBN.getText() + "," + input_titulo.getText() + "," + cmb_autor.getSelectedItem().toString()
											+ "," + input_editora.getText() + "," + input_estoque.getText());
						
						Livros l = new Livros(input_ISBN.getText(), input_titulo.getText(), cmb_autor.getSelectedItem().toString(), 
								input_editora.getText(), Integer.parseInt(input_estoque.getText()));
						funcoes_banco.atualizar(l);
						refreshTable();
						JOptionPane.showMessageDialog(null, "Livro editado com sucesso!");
					}
				}
			}
		});

		
		/****** DELETE ******/
		btn_del.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 
				 if(table.getSelectedRows().length == 0){
					 JOptionPane.showMessageDialog(null, "Selecione uma ou mais linhas para remover!");
				 } else{
					// Remove selected rows
					 for(int i = table.getSelectedRows().length - 1; i >= 0; i--) {
						 String isbn = (String) table.getValueAt(table.getSelectedRow(), 0);
						 System.out.println("Removido Livro ISBN: " + isbn);
						 funcoes_banco.remover(isbn);
						 ((LivrosTable) table.getModel()).removeRow(table.getSelectedRow());
					 }
				 }
			}
		});
		
		
		/****** FILTER ******/
		btn_search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(input_filtro_titulo.getText().trim().equals("") && input_filtro_autor.getText().trim().equals("")){
					refreshTable();
				} else{
					table.setModel(new LivrosTable(funcoes_banco.filtrar(input_filtro_titulo.getText(), input_filtro_autor.getText())));
				}
			}
		});
		
		input_ISBN.addKeyListener(new KeyAdapter() {
	        public void keyTyped(KeyEvent e) {
	        	char c = e.getKeyChar();
	        	// Limit input to 13 characters
	            if (input_ISBN.getText().length() == 13) {
	                e.consume();
	            } 
	            // Numbers only
	            else if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
	   			    e.consume();
	   			}
	        }
	    });
		
		input_estoque.addKeyListener(new KeyAdapter() {
	        public void keyTyped(KeyEvent e) {
	        	char c = e.getKeyChar();
	        	// Numbers only
	        	if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
	   			    e.consume();
	   			}
	        }
	    });
	}
	
	public static void refreshTable(){
		// Refresh Table
		table.setModel(new LivrosTable(funcoes_banco.getTodos()));
		// Clean add form
		input_ISBN.setText("");
		input_titulo.setText("");
		input_editora.setText("");
		input_estoque.setText("");
	}
}
