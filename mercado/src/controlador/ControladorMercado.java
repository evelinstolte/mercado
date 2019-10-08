/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import dao.DaoMercado;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import modelo.Mercado;
import tela.manutencao.ManutencaoMercado;
import java.util.List;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Evelin
 */
public class ControladorMercado {

    public static void inserir(ManutencaoMercado man){
        Mercado objeto = new Mercado();
        
        
        objeto.setNome(man.jtfNome.getText());
        objeto.setRazao(man.jtfRazao.getText());
        objeto.setFundacao(LocalDate.parse(man.jtfFundacao.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        objeto.setFuncionarios(Integer.parseInt(man.jtfFuncionario.getText()));
        objeto.setValor(Double.parseDouble(man.jtfValor.getText()));
        boolean resultado = DaoMercado.inserir(objeto);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Inserido com sucesso!");
            if (man.listagem != null) {
     atualizarTabela(man.listagem.tabela); //atualizar a tabela da listagem
}
man.dispose();//fechar a tela da manutenção
        } else {
            JOptionPane.showMessageDialog(null, "Erro!");
        }
}

  public static void alterar(ManutencaoMercado man){
        Mercado objeto = new Mercado();
        //definir todos os atributos
        
        objeto.setCodigo(Integer.parseInt(man.jtfCodigo.getText()));
        objeto.setNome(man.jtfNome.getText());
        objeto.setRazao(man.jtfRazao.getText());
        objeto.setFundacao(LocalDate.parse(man.jtfFundacao.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        objeto.setFuncionarios(Integer.parseInt(man.jtfFuncionario.getText()));
        objeto.setValor(Double.parseDouble(man.jtfValor.getText()));
        boolean resultado = DaoMercado.alterar(objeto);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
            if (man.listagem != null) {
     atualizarTabela(man.listagem.tabela); //atualizar a tabela da listagem
}
man.dispose();//fechar a tela da manutenção
        } else {
            JOptionPane.showMessageDialog(null, "Erro!");
        }
    }
   public static void excluir(ManutencaoMercado man){
        Mercado objeto = new Mercado();
        objeto.setCodigo(Integer.parseInt(man.jtfCodigo.getText())); //só precisa definir a chave primeira
        
        boolean resultado = DaoMercado.excluir(objeto);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
            if (man.listagem != null) {
     atualizarTabela(man.listagem.tabela); //atualizar a tabela da listagem
}
man.dispose();//fechar a tela da manutenção
        } else {
            JOptionPane.showMessageDialog(null, "Erro!");
        }
    }
   public static void atualizarTabela(JTable tabela) {
        DefaultTableModel modelo = new DefaultTableModel();
        //definindo o cabeçalho da tabela
        
        modelo.addColumn("Codigo");
        modelo.addColumn("Nome");
        modelo.addColumn("Razão");
        modelo.addColumn("Fundação");
        modelo.addColumn("Funcionário");
        modelo.addColumn("Valor");
        
        List<Mercado> resultados = DaoMercado.consultar();
        for (Mercado objeto : resultados) {
            Vector linha = new Vector();
            
            //definindo o conteúdo da tabela
            linha.add(objeto.getCodigo());
            linha.add(objeto.getNome());
            linha.add(objeto.getRazao());
            linha.add(objeto.getFundacao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            linha.add(objeto.getFuncionarios());
            linha.add(objeto.getValor());
            modelo.addRow(linha); //adicionando a linha na tabela
        }
        tabela.setModel(modelo);
    }
public static void atualizaCampos(ManutencaoMercado man, int pk){ 
        Mercado objeto = DaoMercado.consultar(pk);
        //Definindo os valores do campo na tela (um para cada atributo/campo)
        man.jtfCodigo.setText(objeto.getCodigo().toString());
        man.jtfNome.setText(objeto.getNome());
        man.jtfRazao.setText(objeto.getRazao());
        man.jtfFundacao.setText(objeto.getFundacao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        man.jtfFuncionario.setText(objeto.getFuncionarios().toString());
        man.jtfValor.setText(objeto.getValor().toString());
        
        man.jtfCodigo.setEnabled(false); //desabilitando o campo código
        man.btnAdicionar.setEnabled(false); //desabilitando o botão adicionar
    }
}
