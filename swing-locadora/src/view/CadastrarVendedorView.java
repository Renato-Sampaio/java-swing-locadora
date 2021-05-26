package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import controller.EstadoController;
import controller.VendedorController;
import model.Estado;
import model.Vendedor;
import util.Mensagem;
import util.Titulo;
import util.Util;
import util.Valida;

/**
 * Classe respons�vel por exibir a janela para cadastrar um vendedor
 * 
 * @author Renato Sampaio
 * @since 04/03/2021
 * @version 1.0
 */

public class CadastrarVendedorView {
	// declarando os atributos da tela
	private JFrame janela;

	// declarando as JLabels para exibir os r�tulos na tela
	private JLabel lbCodigo;
	private JLabel lbNome;
	private JLabel lbAreaDeVenda;
	private JLabel lbCidade;
	private JLabel lbEstado;
	private JLabel lbSexo;
	private JLabel lbIdade;
	private JLabel lbSalario;

	// declarando os JTextFields para receber as informa��es
	private JTextField tfCodigo;
	private JTextField tfNome;
	private JTextField tfAreaDeVenda;
	private JTextField tfCidade;
	private JTextField tfIdade;
	private JTextField tfSalario;

	// declarando JComboBox para listar os estados e poder escolher
	private JComboBox<String> cbEstado;

	// declarando os JRadioButtons para selecionar o sexo
	private JRadioButton rbSexoMasculino;
	private JRadioButton rbSexoFeminino;

	// componente ButtonGroup respons�vel por gerenciar os componentes radio button
	private ButtonGroup grpRadio;

	// declarando os JButtons para salvar ou cancelar a��es
	private JButton btSalvar;
	private JButton btCancelar;
	private JButton btNovo;
	private JButton btSair;

	// declarando o vetor dos estados para exibi��o e sele��o
	private String estados[] = {};

	// declarando o JPanel para organiza��o
	private JPanel painel;

	public void iniciaGui() {// inicio do m�todo
		/*
		 * configura��es do JFrame - tela
		 */
		janela = new JFrame();
		// configurando o titulo da tela
		janela.setTitle("CADASTRO DE VENDEDOR");
		// configurando o tamanho da tela - largura/altura
		janela.setSize(581, 349);
		// configurando a posi��o inicial da tela - centralizada
		janela.setLocationRelativeTo(null);

		/*
		 * configura��es do JLabel
		 */
		lbCodigo = new JLabel();
		lbCodigo.setText("C�digo:");
		lbCodigo.setBounds(20, 23, 100, 15);

		lbNome = new JLabel();
		lbNome.setText("Nome:");
		lbNome.setBounds(20, 53, 100, 15);

		lbAreaDeVenda = new JLabel();
		lbAreaDeVenda.setText("Area de Venda:");
		lbAreaDeVenda.setBounds(20, 83, 100, 15);

		lbCidade = new JLabel();
		lbCidade.setText("Cidade:");
		lbCidade.setBounds(20, 113, 100, 15);

		lbEstado = new JLabel();
		lbEstado.setText("Estado:");
		lbEstado.setBounds(20, 143, 100, 15);

		lbSexo = new JLabel();
		lbSexo.setText("Sexo:");
		lbSexo.setBounds(20, 173, 100, 15);

		lbIdade = new JLabel();
		lbIdade.setText("Idade:");
		lbIdade.setBounds(20, 203, 100, 15);

		lbSalario = new JLabel();
		lbSalario.setText("Sal�rio:");
		lbSalario.setBounds(20, 233, 100, 15);

		/*
		 * configura��es dos JTextField
		 */
		tfCodigo = new JTextField();
		tfCodigo.setBounds(130, 15, 405, 26);

		tfNome = new JTextField();
		tfNome.setBounds(130, 45, 405, 26);

		tfAreaDeVenda = new JTextField();
		tfAreaDeVenda.setBounds(130, 75, 405, 26);

		tfCidade = new JTextField();
		tfCidade.setBounds(130, 110, 405, 26);

		tfIdade = new JTextField();
		tfIdade.setBounds(130, 200, 405, 26);

		tfSalario = new JTextField();
		tfSalario.setBounds(130, 230, 405, 26);

		/*
		 * configura��o do JComboBox de estados
		 */
		cbEstado = new JComboBox<String>(estados);
		cbEstado.setBounds(130, 140, 300, 22);

		/*
		 * configura��o dos JRadioButton do sexo
		 */
		rbSexoMasculino = new JRadioButton();
		rbSexoMasculino.setText("Masculino");
		rbSexoMasculino.setBounds(129, 170, 83, 20);

		rbSexoFeminino = new JRadioButton();
		rbSexoFeminino.setText("Feminino");
		rbSexoFeminino.setBounds(211, 170, 77, 20);

		/*
		 * configurando o agrupador de bot�es
		 */
		grpRadio = new ButtonGroup();
		grpRadio.add(rbSexoMasculino);
		grpRadio.add(rbSexoFeminino);

		/*
		 * configura��o dos JButtons de salvar e cancelar
		 */
		btSalvar = new JButton();
		btSalvar.setText("SALVAR");
		btSalvar.setBounds(130, 270, 100, 30);
		btSalvar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				salvar();

			}
		});

		btCancelar = new JButton();
		btCancelar.setText("CANCELAR");
		btCancelar.setBounds(250, 270, 100, 30);
		btCancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// m�todo para limpar os campos da tela
				limparTela();
				// m�todo para bloquear os campos da tela
				bloquearTela();
			}

		});

		btNovo = new JButton();
		btNovo.setText("NOVO");
		btNovo.setBounds(130, 270, 100, 30);
		btNovo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// m�todo para desbloquear a tela
				liberarTela();
			}
		});

		btSair = new JButton();
		btSair.setText("SAIR");
		btSair.setBounds(250, 270, 100, 30);
		btSair.addActionListener(new ActionListener() {

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
		painel.add(lbCodigo);
		painel.add(lbNome);
		painel.add(lbAreaDeVenda);
		painel.add(lbCidade);
		painel.add(lbEstado);
		painel.add(lbSexo);
		painel.add(lbIdade);
		painel.add(lbSalario);
		painel.add(tfCodigo);
		painel.add(tfNome);
		painel.add(tfAreaDeVenda);
		painel.add(tfCidade);
		painel.add(tfIdade);
		painel.add(tfSalario);
		painel.add(cbEstado);
		painel.add(rbSexoMasculino);
		painel.add(rbSexoFeminino);
		painel.add(btSalvar);
		painel.add(btCancelar);
		painel.add(btNovo);
		painel.add(btSair);

		carregarComboEstados();

		bloquearTela();

		// configurando a visibilidade da tela
		janela.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		janela.setVisible(true);

	}// fim do m�todo
	/*
	 * m�todo para bloquear a tela de cadastro
	 */

	private void bloquearTela() {
		tfCodigo.setEditable(false);
		tfNome.setEditable(false);
		tfCidade.setEditable(false);
		cbEstado.setEnabled(false);
		tfIdade.setEditable(false);
		tfSalario.setEditable(false);
		tfAreaDeVenda.setEditable(false);
		rbSexoFeminino.setEnabled(false);
		rbSexoMasculino.setEnabled(false);
		btSalvar.setVisible(false);
		btCancelar.setVisible(false);
		btNovo.setVisible(true);
		btSair.setVisible(true);
		
	}

	/*
	 * m�todo para liberar os campos da tela de cadastro
	 */
	private void liberarTela() {
		tfCodigo.setEditable(true);
		tfNome.setEditable(true);
		tfCidade.setEditable(true);
		cbEstado.setEnabled(true);
		tfIdade.setEditable(true);
		tfSalario.setEditable(true);
		tfAreaDeVenda.setEditable(true);
		rbSexoFeminino.setEnabled(true);
		rbSexoMasculino.setEnabled(true);
		btSalvar.setVisible(true);
		btCancelar.setVisible(true);
		btNovo.setVisible(false);
		btSair.setVisible(false);
		tfCodigo.grabFocus();
	}

	/*
	 * m�todo para limpar os campos da tela de cadastro
	 */
	private void limparTela() {// inicio m�todo
		tfCodigo.setText(null);
		tfNome.setText(null);
		tfAreaDeVenda.setText(null);
		tfIdade.setText(null);
		tfSalario.setText(null);
		tfCidade.setText(null);
		grpRadio.clearSelection();
		cbEstado.setSelectedIndex(0);
	}// fim m�todo
	/*
	 * m�todo para preencher a combo de estados
	 */

	private void carregarComboEstados() {// inicio do m�todo
		// valor inicial da combo
		cbEstado.addItem("-Selecione Estado-");
		// la�o de repeti��o para carregar a combo de estados
		for (Estado estado : new EstadoController().getEstados()) {
			cbEstado.addItem(estado.getEstado());
		}
	}// fim do m�todo
	/*
	 * m�todo para receber a��o do click salvar
	 */

	public void salvar() { // inicio m�todo
		// salvar o vendedor apenas se estiver v�lido todos os dados
		if (validar()) {
			/*
			 * procedimentos de grava��o do objeto vendedor no arquivo txt
			 */
			Vendedor vendedor = getVendedor();

			// chamando o m�todo responsavel por gravar o arquivo txt
			new VendedorController().gravarTxtVendedor(vendedor);

			// limpar tela preenchida
			limparTela();

			// bloquear a tela
			bloquearTela();

			// exibindo mensagem de sucesso para o usuario
			JOptionPane.showMessageDialog(null, Mensagem.vendedorGravado, Titulo.cadastroVendedor, 1);

		}
	} // fim do m�todo

	/*
	 * m�todo para validar os campos da tela
	 */
	private boolean validar() {
		// valida��o do campo c�digo
		if (Valida.isEmptyOrNull(tfCodigo.getText())) {

			// informando o usu�rio que o c�digo n�o est� preenchido
			JOptionPane.showMessageDialog(null, Mensagem.codigoVazio, Titulo.cadastroVendedor, 0);

			// colocando o foco no campo com erro
			tfCodigo.grabFocus();

			// retornando false para o m�todo
			return false;
		} else if (!Valida.isInteger(tfCodigo.getText())) {
			// informando o usuario que o c�digo � inv�lido
			JOptionPane.showMessageDialog(null, Mensagem.codigoInvalido, Titulo.cadastroVendedor, 0);
			// colocando o foco no campo com erro
			tfCodigo.grabFocus();
			// retornar false para o metodo
			return false;
		}

		// valida��o do campo nome
		if (Valida.isEmptyOrNull(tfNome.getText())) {
			// informando o usu�rio que o nome n�o est� preenchido
			JOptionPane.showMessageDialog(null, Mensagem.nomeVazio, Titulo.cadastroVendedor, 0);
			// colocando o foco no campo com erro
			tfNome.grabFocus();
			// retornando false para o m�todo
			return false;
		}

		// valida��o do campo area de venda
		if (Valida.isEmptyOrNull(tfAreaDeVenda.getText())) {
			// informando o usu�rio que o area de venda n�o est� preenchido
			JOptionPane.showMessageDialog(null, Mensagem.areaVendaVazio, Titulo.cadastroVendedor, 0);
			// colocando o foco no campo com erro
			tfAreaDeVenda.grabFocus();
			// retornando false para o m�todo
			return false;
		}

		// valida��o do campo cidade
		if (Valida.isEmptyOrNull(tfCidade.getText())) {
			// informando o usu�rio que a cidade n�o est� preenchido
			JOptionPane.showMessageDialog(null, Mensagem.cidadeVazio, Titulo.cadastroVendedor, 0);
			// colocando o foco no campo com erro
			tfCidade.grabFocus();
			// retornando false para o m�todo
			return false;
		}

		// valida��o da combo de estado
		if (cbEstado.getSelectedIndex() == 0) {
			// informando o usuario que o estado n�o foi selecionado
			JOptionPane.showMessageDialog(null, Mensagem.estadoVazio, Titulo.cadastroVendedor, 0);
			// colocando o foco no campo com erro
			cbEstado.grabFocus();
			// retornar o false para o m�todo
			return false;
		}

		// valida��o do radio button de sexo
		if (!rbSexoMasculino.isSelected()) {
			if (!rbSexoFeminino.isSelected()) {
				JOptionPane.showMessageDialog(null, Mensagem.sexoVazio, Titulo.cadastroVendedor, 0);
				// retornar o false para o m�todo
				return false;
			}
		}
		// valida��o do textfield de idade
		if (Valida.isEmptyOrNull(tfIdade.getText())) {
			// informando o usuario que o idade n�o foi selecionado
			JOptionPane.showMessageDialog(null, Mensagem.idadeVazio, Titulo.cadastroVendedor, 0);
			// colocando o foco no campo com erro
			tfIdade.grabFocus();
			// retornar o false para o m�todo
			return false;
		} else if (!Valida.isInteger(tfIdade.getText())) {
			// informando o usuario que a idade � inv�lido
			JOptionPane.showMessageDialog(null, Mensagem.idadeInvalida, Titulo.cadastroVendedor, 0);
			// colocando o foco no campo com erro
			tfIdade.grabFocus();
			// retornar false para o metodo
			return false;
		}
		// valida��o do textfield de salario
		if (Valida.isEmptyOrNull(tfSalario.getText())) {
			// informando o usuario que o salario n�o foi selecionado
			JOptionPane.showMessageDialog(null, Mensagem.salarioVazio, Titulo.cadastroVendedor, 0);
			// colocando o foco no campo com erro
			tfSalario.grabFocus();
			// retornar o false para o m�todo
			return false;
		} else if (!Valida.isInteger(tfSalario.getText())) {
			// informando o usuario que o salario � inv�lido
			JOptionPane.showMessageDialog(null, Mensagem.salarioInvalido, Titulo.cadastroVendedor, 0);
			// colocando o foco no campo com erro
			tfSalario.grabFocus();
			// retornar false para o metodo
			return false;
		}

		// devolvendo true caso esteja tudo v�lido
		return true;
	}// fim m�todo validar
	/*
	 * m�todo para valorizar um objeto vendedor utilizando os campos da tela
	 */

	private Vendedor getVendedor() {
		// instanciando o vendedor para o retorno do m�todo
		Vendedor vendedor = new Vendedor();

		// valorizando o objeto vendedor
		vendedor.setCodigo(Util.getInt(tfCodigo.getText()));
		vendedor.setNome(tfNome.getText());
		vendedor.setAreaVenda(tfAreaDeVenda.getText());
		vendedor.setCidade(tfCidade.getText());
		vendedor.setEstado(cbEstado.getSelectedItem() + "");
		vendedor.setSexo(((rbSexoMasculino.isSelected()) ? 'M' : 'F'));
		vendedor.setIdade(Util.getInt(tfIdade.getText()));
		vendedor.setSalario(Util.getDouble(tfSalario.getText()));
		return vendedor;
	}// fim do m�todo getVendedor

}// fim da classe
