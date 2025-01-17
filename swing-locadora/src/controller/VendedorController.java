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

import model.Vendedor;
import util.Mensagem;
import util.Titulo;
import util.Util;

/**
 * Classe respons�vel por controlar os processos grava��o e leitura da tela de
 * vendedor
 * 
 * @author Renato Duarte Sampaio
 * @since 09/03/2021
 * @version 1.0
 */
public class VendedorController {// inicio da classe
	// declarando o nome do arquivo txt utilizado
	private String arquivo = "vendedor.txt";

	/*
	 * m�todo para gravar registros no arquivo TXT
	 */
	public void gravarTxtVendedor(Vendedor vendedor) {
		// classe auxiliar para carregar um arquivo existente ou criar um novo arquivo
		File file = new File(arquivo);
		try {
			// classe auxiliar para gerar um objeto de mem�ria para grava��o de arquivo
			FileOutputStream arquivoOutput = new FileOutputStream(file, true);
			// classe auxiliar para gerar o arquivo e seu conteudo
			PrintStream gravador = new PrintStream(arquivoOutput);

			// gravando o conteudo do arquivo
			gravador.print(vendedor.getCodigo());
			gravador.print(";");
			gravador.print(vendedor.getNome());
			gravador.print(";");
			gravador.print(vendedor.getAreaVenda());
			gravador.print(";");
			gravador.print(vendedor.getCidade());
			gravador.print(";");
			gravador.print(vendedor.getEstado());
			gravador.print(";");
			gravador.print(vendedor.getSexo());
			gravador.print(";");
			gravador.print(vendedor.getIdade());
			gravador.print(";");
			gravador.print(vendedor.getSalario());
			gravador.print("\n");

			// fechando o processo de grava��o
			gravador.close();
			arquivoOutput.close();

		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, Mensagem.erroGravarArquivoVendedor, Titulo.cadastroVendedor, 0);
			e.printStackTrace();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, Mensagem.erroGravarArquivoVendedor, Titulo.cadastroVendedor, 0);
			e.printStackTrace();
		}
	}// fim do m�todo
	/*
	 * m�todo para retornar uma lista de vendedores
	 */

	public ArrayList<Vendedor> getVendedores() {
		// objeto de lista para retornar no m�todo
		ArrayList<Vendedor> vendedores = buscarTodos();
		return vendedores;
	}

	/*
	 * m�todo para ler o arquivo txt de vendedor
	 */
	private ArrayList<Vendedor> buscarTodos() {
		// listar auxiliar para retornar no m�todo
		ArrayList<Vendedor> vendedores = new ArrayList<Vendedor>();

		try {
			// classe Scanner auxiliar para ler o arquivo vendedor
			Scanner leitor = new Scanner(new FileReader(arquivo));

			// la�o de repeti��o para ler as linhas do arquivo
			while (leitor.hasNext()) {
				// verifica��o se a linha est� vazia
				// objeto auxiliar para retornar o m�todo
				Vendedor vendedor = getVendedor(leitor.nextLine());
				// atribuindo o objeto vendedor na lista retorno
				vendedores.add(vendedor);
			}
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		// retornando a lista de vendedores
		return vendedores;
	}// fim do m�todo

	/*
	 * m�todo para retornar um objeto do tipo vendedor
	 */
	private Vendedor getVendedor(String args) {
		// Objeto auxiliar para retornar no m�todo
		Vendedor vendedor = new Vendedor();
		// Variavel aux para quebrar o registro do arquivo
		String aux[] = args.split(";");

		// Valorizando o objeto vendedor
		vendedor.setCodigo(Util.getInt(aux[0]));
		vendedor.setNome(aux[1]);
		vendedor.setAreaVenda(aux[2]);
		vendedor.setCidade(aux[3]);
		vendedor.setEstado(aux[4]);
		vendedor.setSexo(aux[5].charAt(0));
		vendedor.setIdade(Util.getInt(aux[6]));
		vendedor.setSalario(Util.getDouble(aux[7]));

		// Retornando valores
		return vendedor;
	}
}
