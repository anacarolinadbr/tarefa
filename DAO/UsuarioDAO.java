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
    public static boolean novoUsuario(Usuario usuaio) {

        String sql = "INSERT INTO usuario (nome, email, senha) VALUES (?, ?, ?)";
        String senhaHash = BCrypt.hashpw(usuaio.getSenha(), BCrypt.gensalt());

        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuaio.getNome());
            stmt.setString(2, usuaio.getEmail());
            stmt.setString(3, senhaHash);

            stmt.executeQuery();
            return true;
        } catch (SQLException error) {
            error.printStackTrace();
            return false;
        }
    }

    public static Usuario autenticarUser(String email, String senha) {
        String sql = "SELECT * FROM usuario WHERE email = ?";

        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String senhaHash = rs.getString("senha");

                if (BCrypt.checkpw(senha, senhaHash)) {

                    Usuario usuario = new Usuario();

                    usuario.setIdUsuario(rs.getInt("idUsuario"));
                    usuario.setNome(rs.getString("nome"));
                    usuario.setEmail(rs.getString("email"));

                    return usuario;

                }
            }
        } catch (Exception error) {
            error.printStackTrace();
        }
        return null;
    }
    
     public static Usuario buscarPorEmail(String email){

        String sql = "SELECT * FROM usuario WHERE email = ?";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("idUsuario"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
                return usuario;
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }
     }
