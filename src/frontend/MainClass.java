package frontend;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class MainClass {

  public static void main(String[] a) {
    JFrame f = new JFrame("Library System - v0.1");
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.getContentPane().setLayout(null);
    
    JTabbedPane nav_tabs = new JTabbedPane(JTabbedPane.TOP);
    nav_tabs.setBounds(0, 0, 805, 690);
    nav_tabs.addTab("Autores", new ImageIcon("images/writer-icon-80.png"), new CadastroAutores());
    nav_tabs.addTab("Livros", new ImageIcon("images/icon-books-65.png"), new CadastroLivros());
    nav_tabs.setBackground(Color.WHITE);
    
    
    f.getContentPane().add(nav_tabs);
    f.setSize(805, 690);
    f.setVisible(true);
    f.setResizable(false);
  }
}