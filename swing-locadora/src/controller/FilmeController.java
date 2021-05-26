package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

import model.Filme;
import util.Mensagem;
import util.Titulo;
import util.Util;

/**
 * Classe respons�vel por controlar os processos de grava��o e leitura da tela
 * de filme
 *
 * @author Renato Sampaio
 * @since 05/03/2021
 * @version 1.0
 */
public class FilmeController {

	
	// Declarando o nome do arquivo TXT utilizado
	private String arquivo = "filmes.txt";
	
	/*
	 * M�todo para gravar registros no arquivo TXT
	 */
	public void gravarTxtFilme(Filme filmes) {

		// Classe auxiliar para carregar um arquivo existente ou criar um novo arquivo
		File file = new File(arquivo);

		try {
			// Classe auxiliar para gerar um objeto de mem�ria para grava��o do arquivo
			FileOutputStream arquivoOutput = new FileOutputStream(file, true);

			// Classe auxiliar para gerar o arquivo e seu conte�do
			PrintStream gravador = new PrintStream(arquivoOutput);

			// Gravando o conte�do do arquivo
			gravador.print(filmes.getCodigo());
			gravador.print(";");
			gravador.print(filmes.getNome());
			gravador.print(";");
			gravador.print(filmes.getValor());
			gravador.print(";");
			gravador.print(filmes.isDisponivel());
			gravador.print(";");
			gravador.print(filmes.isPromocao());
			gravador.print(";");
			gravador.print(filmes.getValorPromocao());
			gravador.print(";");
			gravador.print(filmes.getGenero());
			gravador.print("\n");

			// Fechando o processo de grava��o
			gravador.close();
			arquivoOutput.close();

		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, Mensagem.erroGravarArquivoFilme, Titulo.cadastroFilme, 0);
			e.printStackTrace();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, Mensagem.erroGravarArquivoFilme, Titulo.cadastroFilme, 0);
			e.printStackTrace();
		}

	}
	
	/*
	 * M�todo para retornar uma lista de filmes
	 */
	public ArrayList<Filme> getFilmes(){
		// Objeto de lista para retornar no m�todo
		ArrayList<Filme> filmes = buscarTodos();
		return filmes;
	}
	
	/*
	 * M�todo para ler o arquivo TXT de filme 
	 */
	private ArrayList<Filme> buscarTodos(){
		
		// Lista Auxiliar para retornar no m�todo
		ArrayList<Filme> filmes = new ArrayList<Filme>();
		
		try {
			// Classe scanner auxiliar para ler o arquivo filme
			Scanner leitor = new Scanner(new FileReader(arquivo));
			
			// La�o de repeti��o para ler as linhas do arquivo
			while (leitor.hasNext()) {
				
					// Objeto auxiliar para retornar no m�todo
					Filme filme = getFilme(leitor.nextLine());
					// Atribuindo o objeto na lista de retorno
					filmes.add(filme);
				
			} // fim do while
			
		} catch (FileNotFoundException e) {
			
			
			e.printStackTrace();
		}
		
		// Retornando a lista de vendedores
		return filmes;
	} // Fim do m�todo
	
	/*
	 * M�todo para retornar um objeto do tipo filme
	 */
	private Filme getFilme(String args) {
		// Objeto auxiliar para retornar no m�todo
		Filme filme = new Filme();
		// Vari�vel auxiliar para quebrar o registro do arquivo
		String aux[] = args.split(";");
	
	//Valorizando o objeto filme
		filme.setCodigo(Util.getInt(aux[0]));
		filme.setNome(aux[1]);
		filme.setValor(Util.getDouble(aux[2]));
		filme.setDisponivel(Util.getBoolean(aux[3]));
		filme.setPromocao(Util.getBoolean(aux[4]));
		filme.setValorPromocao(Util.getDouble(aux[5]));

		// Retornando o objeto valorizado
		return filme;
	}
	

}