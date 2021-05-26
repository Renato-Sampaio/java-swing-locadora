package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.FilmeController;
import model.Filme;

/**
 * Classe responsavel por fazer a consulta de filmes
 * 
 * @author Renato Sampaio
 * @since 05/03/2021
 * @version 1.0
 */
public class ConsultarFilmesView {
	// declarando os atributos da tela
	private JFrame janela;

	// declarando a tabela JTable para exibir os dados do filme para loca��o
	private JTable tabela;
	// declarando o vetor auxiliar para armazenar os nomes das colunas
	private String colunas[] = { "C�digo", "Nome", "Valor", "Dispon�vel", "Promo��o", "Valor Promocional" };

	// declarando o scroll para a tabela
	private JScrollPane scroll;

	// declarando o bot�o Jbutton para sair
	private JButton btSair;

	// declarando o JPanel para organiza��o
	private JPanel painel;

	public void iniciaGui() {// inicio do m�todo
		/*
		 * configura��es do JFrame - tela
		 */
		janela = new JFrame();
		// configurando o titulo da tela
		janela.setTitle("CONSULTA DE FILMES");
		// configurando o tamanho da tela - largura/altura
		janela.setSize(580, 400);
		// configurando a posi��o inicial da tela - centralizada
		janela.setLocationRelativeTo(null);

		/*
		 * configura��es do componente JTabble - tabela de dados
		 */
		// definir o modelo utilizado na tabela - quantidade de linhas e colunas
		DefaultTableModel modeloTabela = new DefaultTableModel(null, colunas);
		// instanciando a tabela
		tabela = new JTable(modeloTabela);
		tabela.setBounds(1, 1, 562, 290);
		tabela.setEnabled(true);

		/*
		 * configura��es do componente JScrollPane
		 */
		scroll = new JScrollPane(tabela);
		// configurar local de exibi��o do scroll
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		// configurar a posi��o e o tamanho
		scroll.setBounds(1, 1, 562, 290);

		/*
		 * configura��es do componente JButton
		 */
		btSair = new JButton();
		btSair.setText("SAIR");
		btSair.setBounds(200, 305, 150, 30);
		btSair.addActionListener (new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// fecha a janela e mant�m o menu aberto
				janela.dispose();
			}
		});
		/*
		 * configura��es do painel da janela
		 */
		painel = (JPanel) janela.getContentPane();
		painel.setLayout(null);
		painel.add(scroll);
		painel.add(btSair);

		
		// configurando a visibilidade da janela
		janela.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		carregarTabela();
		janela.setVisible(true);
	}// fim do m�todo
	/*
	 * M�todo para carregar a tabela com os vendedores gravados no arquivo TXT
	 */

	public void carregarTabela() {

		// Obtendo o modelo da tabela criada
		DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();

		// La�o de repeti��o para preencher as linhas da tabela
		for (Filme filme : new FilmeController().getFilmes()) {
			String disponivel = "";
			String terPromocao = "";

			// Caso o retorno booleano seja TRUE, exibir SIM na tabela
			if (filme.isDisponivel()) {
				disponivel = "SIM";
			} else { // Caso o retorno booleano seja FALSE, exibir N�O na tabela
				disponivel = "N�O";
			}
			// Caso o retorno booleano seja TRUE, exibir SIM na tabela
			if (filme.isPromocao()) {
				terPromocao = "SIM";
			} else { // Caso o retorno booleano seja FALSE, exibir N�O na tabela
				terPromocao = "N�O";
			}
			// Adicionando a linha na tabela
			modelo.addRow(new String[] { filme.getCodigo() + "", filme.getNome(), filme.getValor() + "", disponivel,
					terPromocao, filme.getValorPromocao() + "", });
			
			
		}
	}


}// fim da classe
