package br.com.projetofinal.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
/*
 * Gerado pelo Chat GPT
 * 
 * Para criar um JTable que mostre os dados do banco de dados MySQL, você precisará seguir os seguintes passos:

    1-Conectar-se ao banco de dados MySQL e recuperar os dados da tabela Curso.
    2-Criar um modelo de tabela (TableModel) para armazenar os dados recuperados.
    3-Criar uma instância de JTable e associá-la ao modelo de tabela criado.
    4- Adicionar a tabela a um JScrollPane para permitir a rolagem.
 * 
 * 
 */
//conexão
import java.sql.Connection;
//driver
import java.sql.DriverManager;

//buscar os dados
import java.sql.ResultSet;
//exceção
import java.sql.SQLException;
//comandos SQL (Prepar...)
import java.sql.Statement;

import javax.swing.JButton;
//Formulário
import javax.swing.JFrame;
import javax.swing.JPanel;
//Barra de Rolagem
import javax.swing.JScrollPane;
//Tabela
import javax.swing.JTable;
import javax.swing.SwingUtilities;
//MVC - View (tabelinha) Model (Dados)
import javax.swing.table.DefaultTableModel;

import br.com.projetofinal.dao.Conexao;

//Classe criada por mim.
public class TabelaDeCursosGUI extends JFrame {
	
	private JPanel painel1;
	private JPanel painel2;
	private JButton btnExcluir;
	
	 
    public TabelaDeCursosGUI() throws SQLException {
    	
    	//instanciando os painéis
    	painel1 = new JPanel();
    	painel2 = new JPanel();
    	btnExcluir = new JButton("Excluir");
    	painel1.add(btnExcluir);
    	painel1.setLayout(new FlowLayout());
    	
    	
    	Statement comando = null;
		Connection conexao = Conexao.conectar();
		String sql = "SELECT * FROM curso";
		comando = conexao.createStatement();
		ResultSet rs = comando.executeQuery(sql);
		//executar tudo que a gente a gente fez.
		// Criar modelo de tabela
		String[] colunas = {"ID", "Nome do Curso", "CH"};
		DefaultTableModel modelo = new DefaultTableModel(colunas, 0);
		while (rs.next()) {
		    int id = rs.getInt("id");
		    String nome = rs.getString("nome");
		    int cargaHoraria = rs.getInt("cargaHoraria");
		    Object[] row = {id, nome, cargaHoraria};
		    modelo.addRow(row);
		}
       // Criar tabela
	   JTable table = new JTable(modelo);
	

	// ajuste manual da largura das colunas
	table.getColumnModel().getColumn(0).setPreferredWidth(40);
	table.getColumnModel().getColumn(1).setPreferredWidth(350);
	table.getColumnModel().getColumn(2).setPreferredWidth(50);


	// desabilita o redimensionamento automático das colunas
	table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

	   JScrollPane scrollPane = new JScrollPane(table);
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       painel2.add(scrollPane);
       BorderLayout border = new BorderLayout();
       setLayout(border);
	   add(painel1,BorderLayout.NORTH);
	   
	  
	  

	   add(painel2,BorderLayout.WEST);
	   pack();
	   setVisible(true);    	
    }	

}
