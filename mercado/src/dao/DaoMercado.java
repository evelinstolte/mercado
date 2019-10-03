/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Mercado;
/**
 *
 * @author Evelin
 */
public class DaoMercado {
    
     public static boolean inserir(Mercado objeto) {
        String sql = "INSERT INTO mercado ( nome, razao, fundacao, funcionario, valor) VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            
           
            ps.setString(1, objeto.getNome());
            ps.setString(2, objeto.getRazao());
            ps.setDate(3, Date.valueOf(objeto.getFundacao())); 
            ps.setInt(4, objeto.getFuncionarios());
            ps.setDouble(5, objeto.getValor());
            
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
      public static void main(String[] args) {
        Mercado objeto = new Mercado();
     
        objeto.setNome("lalala");
        objeto.setRazao("jajaja");
        objeto.setFundacao(LocalDate.parse("11/01/1988", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        objeto.setFuncionarios(15);
        objeto.setValor(1.50);
        boolean resultado = inserir(objeto);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Inserido com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Erro!");
        }
    }
      
        public static boolean alterar(Mercado objeto) {
        String sql = "UPDATE mercado SET nome = ?, razao = ?,fundacao = ?,funcionarios = ?,valor = ?  WHERE codigo=?";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            
            ps.setString(1, objeto.getNome()); 
            ps.setString(2, objeto.getRazao());
            ps.setDate(3, Date.valueOf(objeto.getFundacao()));
            ps.setInt(4, objeto.getFuncionarios());
            ps.setDouble(5, objeto.getValor());
            ps.setInt(6, objeto.getCodigo());
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
         public static boolean excluir(Mercado objeto) {
        String sql = "DELETE FROM mercado WHERE codigo=?";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, objeto.getCodigo());
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
         public static List<Mercado> consultar() {
        List<Mercado> resultados = new ArrayList<>();
        //editar o SQL conforme a entidade
        String sql = "SELECT codigo, nome, razao, fundacao, funcionario,valor  FROM mercado";
        PreparedStatement ps;
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Mercado objeto = new Mercado();
                //definir um set para cada atributo da entidade, cuidado com o tipo
                objeto.setCodigo(rs.getInt("codigo"));
                objeto.setNome(rs.getString("nome"));
                objeto.setRazao(rs.getString("razao"));
                objeto.setFundacao(rs.getDate("fundacao").toLocalDate());
                objeto.setFuncionarios(rs.getInt("funcionario"));
                objeto.setValor(rs.getDouble("valor"));
                resultados.add(objeto);//não mexa nesse, ele adiciona o objeto na lista
            }
            return resultados;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
}
          public static Mercado consultar(int primaryKey) {
        //editar o SQL conforme a entidade
        String sql = "SELECT codigo, nome,razao, fundacao, funcionario, valor FROM mercado WHERE codigo=?";
        PreparedStatement ps;
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, primaryKey);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Mercado objeto = new Mercado();
                //definir um set para cada atributo da entidade, cuidado com o tipo
                objeto.setCodigo(rs.getInt("codigo"));
                objeto.setNome(rs.getString("nome"));
                objeto.setRazao(rs.getString("razao"));
                objeto.setFundacao(rs.getDate("fundacao").toLocalDate());
                objeto.setFuncionarios(rs.getInt("funcionario"));
                objeto.setValor(rs.getDouble("valor"));
                return objeto;//não mexa nesse, ele adiciona o objeto na lista
            }
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
}
