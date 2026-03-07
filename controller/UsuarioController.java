/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gerenciadorTarefas.controller;

import gerenciadorTarefas.model.Usuario;
import gerenciadorTarefas.service.UsuarioService;

/**
 *
 * @author ANACAROLINADAMBROSBE
 */
public class UsuarioController {
  public static boolean registrar (String email, String senha, String nome){
      
         Usuario novoUsuario = new Usuario(email, senha, nome);
        return UsuarioService.registrar(novoUsuario); };
    
    public static boolean login(String email, String senha) {
        UsuarioService.login(email, senha);
        return true;
    };  
}
