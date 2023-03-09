package br.com.projetofinal.gui;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import br.com.projetofinal.dao.*;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
//Barra de Rolagem
import javax.swing.JScrollPane;
//Tabela
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
//MVC - View (tabelinha) Model (Dados)
import javax.swing.table.DefaultTableModel;

import br.com.projetofinal.dao.Conexao;

//Classe criada por mim.
public class TabelaDeCursosGUI extends JFrame implements ActionListener{
	
	private JPanel painel1;
	private JPanel painel2;
	private JButton btnExcluir;
	private JTable table;
	private int id=0;
	 
    public TabelaDeCursosGUI() throws SQLException {
    	
    	//instanciando os painéis
    	painel1 = new JPanel();
    	painel2 = new JPanel();
    	btnExcluir = new JButton("Excluir");
    	painel1.add(btnExcluir);
    	
    	painel1.setLayout(new FlowLayout());
    	
    	 BorderLayout border = new BorderLayout();
         setLayout(border);
         preencherTabela();
    
       
       //chamados dos eventos
       btnExcluir.addActionListener(this);
	   add(painel1,BorderLayout.NORTH);
	   add(painel2,BorderLayout.WEST);
	   pack();
	   setVisible(true);    	
    }


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==btnExcluir) {
		    new CursoDAO().excluir(id);
			JOptionPane.showMessageDialog(null,"Dados Excluídos com Sucesso");
			try {
				preencherTabela();
			
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}			
		}
		
	}	
	
	//Refatorei o método..
	public void preencherTabela() throws SQLException {
		
	    //INICIO---------------A parte do Banco de dados 
    	Statement comando = null;
		Connection conexao = Conexao.conectar();
		String sql = "SELECT * FROM curso";
		comando = conexao.createStatement();
		ResultSet rs = comando.executeQuery(sql);
		//executar tudo que a gente a gente fez.
		// Criar modelo de tabela
		
		//Montar a DefaultTableModel
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
	   table = new JTable(modelo);
	   
	   // ajuste manual da largura das colunas
	   table.getColumnModel().getColumn(0).setPreferredWidth(40);
	   table.getColumnModel().getColumn(1).setPreferredWidth(350);
	   table.getColumnModel().getColumn(2).setPreferredWidth(50);
        

	   // desabilita o redimensionamento automático das colunas
	   table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
	   //trabalhar o evento de selecionar...
	   

	   table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
		    @Override
		    public void valueChanged(ListSelectionEvent event) {
		        // verifica se o evento foi gerado pela seleção de uma linha
		        if (!event.getValueIsAdjusting()) {
		            // obtém o índice da linha selecionada
		            int rowIndex = table.getSelectedRow();

		            // obtém o valor da célula da coluna 0 da linha selecionada
		            Object cellValue = table.getValueAt(rowIndex, 0);
                    id = Integer.parseInt(cellValue.toString());
		            // exibe o valor da célula em um JOptionPane
		            //JOptionPane.showMessageDialog(null, "Valor selecionado: " + cellValue);
		        }
		    }
		});
	   table.revalidate();
	   table.repaint();
	   
	   //1 erro
	   
	   //******************************************************************* 
	   
	   JScrollPane scrollPane = new JScrollPane(table);
       //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       painel2.add(scrollPane);	
		
	}

}
