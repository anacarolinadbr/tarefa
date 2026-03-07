/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gerenciadorTarefas.service;


import gerenciadorTarefas.DAO.UsuarioDAO;
import gerenciadorTarefas.model.Usuario;
import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 *
 * @author ANACAROLINADAMBROSBE
 */
public class UsuarioService {
     // ele não deixa ninguém entrar sem preencher tudo
    public static boolean registrar(Usuario usuario) {
        // Checa se o email, a senha ou o nome estão vazios ou só com espaços
        if (usuario.getEmail().isBlank() || usuario.getSenha().isBlank() || usuario.getNome().isBlank()) {
            // Se faltar qualquer um, ele já cancela o registro aqui mesmo
            return false;
        }
         // Se estiver tudo preenchido, manda para o DAO salvar lá no banco
        return UsuarioDAO.salvar(usuario);
    }

  
    // Esse que valida quem está tentando entrar no sistema
   public static boolean login(String nomeUsua, String senha) {
       // Primeiro, ele vai lá no banco ver se existe alguém com esse nome de usuário
        Usuario BancoUsua = UsuarioDAO.buscarPorUsuario(nomeUsua);
       // Se o banco responder que achou alguém
        if (BancoUsua != null) {
             // ele usa o BCrypt para comparar a senha que você digitou com a senha bagunçada do banco
            if (BCrypt.checkpw(senha, BancoUsua.getSenha())) {
                // Se as senhas baterem, ele libera o acesso
                System.out.println("Login realizado!");
                return true
                        ;
            }
        }
        
        System.out.println("Não foi possível realizar o login");
        return false;
    }
   public static Usuario autenticar(String usuario, String email, String senha){
       if(usuario.isBlank() || senha.isBlank()){
           return null;
       } 
       return UsuarioDAO.login(usuario, email, senha);
   }
}
