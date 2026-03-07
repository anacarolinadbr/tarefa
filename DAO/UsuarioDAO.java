/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gerenciadorTarefas.DAO;

import gerenciadorTarefas.database.Conexao;
import gerenciadorTarefas.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 *
 * @author ANACAROLINADAMBROSBE
 */
public class UsuarioDAO {
   
  public static boolean salvar(Usuario usuario) {

        String sql = "INSERT INTO usuario (nome, email, senha) VALUES (?, ?, ?)";
         // Transforma a senha em aleatorio (hash) 
        String senhaHash = BCrypt.hashpw(usuario.getSenha(), BCrypt.gensalt());
        // Abre a conexão com o banco
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            // Preenche os pontinhos de interrogação com as informações do usuário
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, senhaHash);

            stmt.executeUpdate();
            return true;

        } catch (SQLException e) { e.printStackTrace();
            return false;
   
        }
    }
 
   public static Usuario buscarPorUsuario(String nome) {
        String sql = "SELECT * FROM usuario WHERE nome = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            // Coloca o nome que você quer procurar no lugar do ?
            stmt.setString(1, nome);
            // Faz a busca e guarda o resultado numa lista 'rs'
            ResultSet rs = stmt.executeQuery();
             // Se o banco encontrou alguém 
            if (rs.next()) {
                return new Usuario(
                        // pega as informações da tabela e monta um objeto Usuario de volta
                       rs.getString("idUsuario"),
                        rs.getString("nome"),
                        rs.getString("senha")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
   
   public static Usuario buscarPorEmail(String email) {

    Connection conn = Conexao.conectar();
    Usuario usuario = null;

    try {
        String sql = "SELECT * FROM Usuario WHERE email = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, email);

        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            usuario = new Usuario();
            usuario.setIdUsuario(rs.getInt("idUsuario"));
            usuario.setNome(rs.getString("nome"));
            usuario.setEmail(rs.getString("email"));
            usuario.setSenha(rs.getString("senha"));
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return usuario;
}
   
   public static Usuario login(String nome, String email, String senha) {
        String sql = "SELECT id, nome, email, senha FROM usuario WHERE nome = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, nome);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String hashDoBanco = rs.getString("senha");

                    
                    if (BCrypt.checkpw(senha, hashDoBanco)) {
                        Usuario usuario = new Usuario();
                        usuario.setIdUsuario(rs.getInt("id"));
                        usuario.setNome(rs.getString("nome"));
                        usuario.setEmail(rs.getString(email));
                        return usuario; 
                    }
                }
            }
        } catch (SQLException erro) {
            System.out.println("Erro de login: " + erro.getMessage());
        }
        
        return null; 
    }
     }
