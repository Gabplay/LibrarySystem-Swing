package frontend;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JPanel;
import backend.Autores;
import backend.AutoresDaoImpl;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class CadastroAutores extends JPanel{
	private static JTable table = new JTable();
	private static JScrollPane scroll_pane = new JScrollPane();
	private static AutoresDaoImpl funcoes_banco = new AutoresDaoImpl();
	private static JTextField input_name, input_date, input_country, input_filtro_nome, input_filtro_pais;
	private static JButton btn_add, btn_del, btn_edit, btn_search;
	
	private static JDialog form = new JDialog();
	private static JLabel lblInfo = new JLabel("Adicionar Autor");
	private static JButton btn_save;
	
	
	public CadastroAutores() {
		table.setRowHeight(30);
		table.getTableHeader().setBackground(Color.CYAN);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		table.setDefaultRenderer(String.class, centerRenderer);
		table.setDefaultRenderer(Integer.class, centerRenderer);
		table.setDefaultRenderer(Date.class, centerRenderer);
		
		setLayout(null);
		table.setModel(new AutoresTable(funcoes_banco.getTodos()));
	    scroll_pane.setBounds(20, 110, 754, 360);
	    scroll_pane.setViewportView(table);
	    table.setSize(800, 100);
	    
		add(scroll_pane);
		setSize(800,800);
		
		input_filtro_nome = new JTextField();
		input_filtro_nome.setBounds(20, 40, 250, 30);
		add(input_filtro_nome);
		input_filtro_nome.setColumns(10);
		
		JLabel lblFiltroNome = new JLabel("Filtro - Nome");
		lblFiltroNome.setBounds(20, 15, 80, 14);
		add(lblFiltroNome);
		
		input_filtro_pais = new JTextField();
		input_filtro_pais.setBounds(335, 40, 250, 30);
		input_filtro_pais.setColumns(10);
		add(input_filtro_pais);
		
		JLabel lblFiltroPas = new JLabel("Filtro - País de Origem");
		lblFiltroPas.setBounds(335, 15, 135, 14);
		add(lblFiltroPas);
		
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
		lblInfo.setBounds(225, 30, 98, 14);
		form.getContentPane().add(lblInfo);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(30, 80, 46, 14);
		form.getContentPane().add(lblNome);
		
		input_name = new JTextField();
		input_name.setBounds(145, 80, 275, 25);
		form.getContentPane().add(input_name);
		input_name.setColumns(10);
		
		JLabel lblDataNascimento = new JLabel("Data Nascimento");
		lblDataNascimento.setBounds(30, 130, 98, 14);
		form.getContentPane().add(lblDataNascimento);
		
		input_date = new JTextField();
		input_date.setColumns(10);
		input_date.setBounds(145, 130, 275, 25);
		form.getContentPane().add(input_date);
		
		JLabel lblPasDeOrigem = new JLabel("País de Origem");
		lblPasDeOrigem.setBounds(30, 180, 98, 14);
		form.getContentPane().add(lblPasDeOrigem);
		
		input_country = new JTextField();
		input_country.setColumns(10);
		input_country.setBounds(145, 180, 275, 25);
		form.getContentPane().add(input_country);
		
		btn_save = new JButton("Salvar");
		btn_save.setBounds(220, 235, 100, 30);
		btn_save.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btn_save.setIcon(UIManager.getIcon("FileView.floppyDriveIcon"));
		form.getContentPane().add(btn_save);
		
		form.setSize(500,340);
		form.setResizable(false);
		form.getContentPane().setLayout(null);
		
		eventHandlers();
	}
	
	
	public static void eventHandlers(){
		
		/****** CREATE/ADD ******/
		btn_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblInfo.setText("Adicionar Autor");
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
					lblInfo.setText("Editar Autor");
					input_name.setText((String) table.getValueAt(table.getSelectedRow(), 1));
					input_date.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
					input_country.setText((String) table.getValueAt(table.getSelectedRow(), 3));
					form.setVisible(true);
				}
			}
		});
		
		
		/****** SALVAR ADD/EDIT ******/
		btn_save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				// Check if name input is not empty
				if(input_name.getText().trim().equals("")){
					JOptionPane.showMessageDialog(null, "Por favor preencha o campo nome!");
				} else{
					if(lblInfo.getText().equals("Adicionar Autor")){
						System.out.println("Novo Autor: " + input_name.getText() + ", " + input_date.getText() + ", " + input_country.getText());
						try{
							Autores a = new Autores(input_name.getText(), java.sql.Date.valueOf(input_date.getText()), input_country.getText());
							funcoes_banco.inserir(a);
							refreshTable();
							
							JOptionPane.showMessageDialog(null, "Autor cadastrado com sucesso!");
						} catch(IllegalArgumentException e){
							JOptionPane.showMessageDialog(null, "Por insira a data no formato (aaaa-mm-dd)");
						}
					} else if(lblInfo.getText().equals("Editar Autor")){
						System.out.println("Editar Autor: " + input_name.getText() + ", " + input_date.getText() + ", " + input_country.getText());
						try{
							Autores a = new Autores((int)table.getValueAt(table.getSelectedRow(), 0), input_name.getText(), java.sql.Date.valueOf(input_date.getText()), input_country.getText());
							funcoes_banco.atualizar(a);
							refreshTable();
							
							JOptionPane.showMessageDialog(null, "Autor editado com sucesso!");
						} catch(IllegalArgumentException e){
							JOptionPane.showMessageDialog(null, "Por insira a data no formato (aaaa-mm-dd)");
						}
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
						 int id = (int) table.getValueAt(table.getSelectedRow(), 0);
						 
						 if(funcoes_banco.remover(id)){
							 System.out.println("Removido Autor ID: " + id);
							 ((AutoresTable) table.getModel()).removeRow(table.getSelectedRow());
						 } else{
							 JOptionPane.showMessageDialog(null, "O autor selecionado possui livros cadastrados, "
							 								+ "por favor, remova todos os livros antes de remover o autor!");
						 }
					 }
				 }
			}
		});
		
		
		/****** FILTER ******/
		btn_search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(input_filtro_nome.getText().equals("") && input_filtro_pais.getText().equals("")){
					refreshTable();
				} else{
					table.setModel(new AutoresTable(funcoes_banco.filtrar(input_filtro_nome.getText(), input_filtro_pais.getText())));
				}
			}
		});
	}
	
	public static void refreshTable(){
		// Refresh Table
		table.setModel(new AutoresTable(funcoes_banco.getTodos()));
		// Clean add form
		input_name.setText("");
		input_date.setText("");
		input_country.setText("");
	}
}
