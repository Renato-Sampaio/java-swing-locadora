package controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

import model.Estado;
import util.Mensagem;
import util.Titulo;

/**
 * Classe responsavel por controlar os m�todos do objeto Estado
 * 
 * @author Renato Duarte Sampaio
 * @since 08/03/2021
 * @version 1.0
 */

public class EstadoController {
	// declarar o nome do arquivo TXT utilizado
	private String arquivo = "estados.txt";
	
	/*
	 * m�todo responsavel por retornar uma lista de estados
	 */
	public ArrayList<Estado> getEstados() {
		// objeto lista para retornar no m�todo
		ArrayList<Estado> estados = buscarTodos();
		return estados;
	}
	
	/*
	 * m�todo para ler o arquivo de estados
	 */
	private ArrayList<Estado> buscarTodos() {
		
		// lista auxiliar para retornar no m�todo
		ArrayList<Estado> estados = new ArrayList<Estado>();
		try {
			// classe scanner auxiliar para ler o arquivo de estados
			Scanner leitor = new Scanner(new FileReader(arquivo));
			
			//la�o de repeti��o para ler as linhas do arquivo
			while (leitor.hasNext()) {
				// objeto auxiliar para retornar no m�todo
				Estado estado = getEstado(leitor.nextLine());
				//atribuindo o objeto estado na lista de retorno
				estados.add(estado);
			}
			
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, Mensagem.erroLerArquivo, Titulo.cadastroVendedor, 0);
			e.printStackTrace();
		}
		// retornando a lista de estados
		return estados;
	}
	/*
	 * m�todo para retornar um objeto do tipo estado
	 */
	private Estado getEstado(String linha) {
		//variavel auxiliar para converter a linha em objeto
		String aux[] = linha.split(";");
		// retornando o objeto estado
		return new Estado(aux[1], aux[0]);
	}
}
