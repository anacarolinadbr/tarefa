/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gerenciadorTarefas.DAO;

import gerenciadorTarefas.database.Conexao;
import gerenciadorTarefas.model.Tarefa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ANACAROLINADAMBROSBE
 */
public class TarefaDAO {
   
    public static Tarefa criarTarefa(Tarefa tarefa) {
     String sql = "INSERT INTO tarefa (tarefa, andamento, idUsuario) VALUES (?, ?, ?)";
    
    try(Connection conn = Conexao.conectar(); 
        PreparedStatement stmt  = conn.prepareStatement(sql)){
         // Saída de depuração para verificar o ID do usuário
        System.out.println("Usuario inserir: " + tarefa.getIdUsuario());
        stmt.setString(1, tarefa.getTarefa());
        stmt.setString(2, tarefa.getAndamento());
        stmt.setInt(4,tarefa.getIdUsuario());
        
        stmt.executeUpdate();
        return tarefa;
    
    } catch(SQLException e) {
     e.printStackTrace();}
    
        return null;
    }
   
    // Busca a primeira tarefa encontrada para um usuário especifico
    public static Tarefa listarTarefa(Tarefa tarefa) {
        String sql = "SELECT * FROM tarefa WHERE idUsuario = ?";
        
        try(Connection conn = Conexao.conectar();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
            // Define o valor do filtro baseado no ID contido no objeto 
            stmt.setInt(1, tarefa.getIdUsuario());
            // ResultSet armazena o conjunto de resultados retornados pelo banco
            ResultSet rs =  stmt.executeQuery();
            
            if(rs.next()) {
                 return new Tarefa (
                    rs.getInt("idTarefa"),
                    rs.getString("tarefa"),
                    rs.getString("andamento"),
                    rs.getInt("idUsuario")
                ); 
            };
           
        } catch(SQLException e) {
        e.printStackTrace();       
     }
        return null;
    }
    
    
   
    public static boolean deletarTarefa(Tarefa tarefa) {
       String sql = "DELETE FROM tarefa WHERE idTarefa = ?";
        
        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
             // Vincula o ID do objeto tarefa ao que foi selecionado de exclusão do SQL
            stmt.setInt(1, tarefa.getIdTarefa());  
            stmt.executeUpdate();
            return true;
            
        } catch(SQLException e) {
         e.printStackTrace();
            return false;
        }
    }
   
    public static boolean atualizarTarefa(int id, String tarefa, String andamento) {
        // SQL UPDATE para modificar colunas específicas 
        String sql = "UPDATE tarefa SET tarefa = ?, andamento = ? WHERE idTarefa = ?";
        
        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            
             // Atribuição dos novos valores recebidos 
            stmt.setString(1, tarefa);
            stmt.setString(2, andamento);
            // Define qual registro será afetado pela alteração
            stmt.setInt(3, id);
            stmt.executeUpdate();
            return true;
                      
        } catch(SQLException e) {
           e.printStackTrace();
            return false;
        }
    }
   
   
}

