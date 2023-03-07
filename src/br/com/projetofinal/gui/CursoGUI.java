package br.com.projetofinal.gui;

import javax.swing.JButton;
import javax.swing.JFrame; //JVM - Java Virtual Machine
import javax.swing.JOptionPane;

import java.awt.BorderLayout;//Resp.Técni: Sistema Operacional
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.awt.event.ActionListener;//Resp.Técni: Sistema Operacional

//Porque senão? haja memória RAM, haja SSD

//ActionListener é uma interface
//Casado
/*
 * public class Romulo implements Casamento{
 *     //sobrescrita - polimorfismo 
 *     @Override 
 *     public void irPraCasa(){
 *     
 *     } 
 * }
 * 
 * public class Romulo implements Mamae{
 *      @Override
 *      public void naoBeber( ){
 *          if(beber == true)
 *             System.out.println("Eu te Mato!");
 *      }
 * }
 * 
 * ActionListener é uma interface
 * 
 * Interface: é um recurso que possui métodos abstratos que obrigam
 * as classes a implementar métodos (CONTRATO)
 */
public class CursoGUI extends JFrame implements ActionListener {
	private JButton btnCadastrar; //variável de referência
	private JButton btnPesquisar;
	private JButton btnAlterar;
	private JButton btnExcluir;
	
	public CursoGUI() {
		//tratar o evento do botão	
		btnCadastrar = new JButton("Cadastrar");//instância
		btnPesquisar = new JButton("Pesquisar");//instância
		btnAlterar = new JButton("Alterar");//instância
		btnExcluir= new JButton("Excluir");//instância
		
		//atribuindo o botão ao container (JFrame)
		getContentPane().add(btnCadastrar);
		getContentPane().add(btnPesquisar);
		getContentPane().add(btnAlterar);
		getContentPane().add(btnExcluir);
		
		setLayout(new GridLayout(1,4));	
		
		setSize(800,600);
		btnCadastrar.addActionListener(this);
		btnPesquisar.addActionListener(this);
		btnExcluir.addActionListener(this);
		btnAlterar.addActionListener(this);
	}
	
	//tratar eventos (handler - ouvintes)
	@Override
	public void actionPerformed(ActionEvent e) {	
		if(e.getSource() == btnCadastrar) {
			JOptionPane.showMessageDialog(null,"uhhu cadastrou");
		}else if(e.getSource() == btnPesquisar) {
			JOptionPane.showMessageDialog(null,"uhhu pesquisou");
		}else if(e.getSource() == btnAlterar) {
			JOptionPane.showMessageDialog(null,"uhhu alterou");
		}else {
			JOptionPane.showMessageDialog(null,"uhhu excluiu");
		}
		
	}

}
