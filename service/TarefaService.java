/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gerenciadorTarefas.service;

import gerenciadorTarefas.DAO.TarefaDAO;
import gerenciadorTarefas.model.Tarefa;

/**
 *
 * @author ANACAROLINADAMBROSBE
 */
public class TarefaService {
   public static boolean registroTarefa(Tarefa tarefa) {        
        
        // Se o nome da tarefa ou o status estiverem em branco, eleda erro já
        if(tarefa.getTarefa().isBlank() || tarefa.getAndamento().isBlank()) {
            return false;
        }
         // Se passou no teste acima, ele chama o DAO 
        TarefaDAO.criarTarefa(tarefa);
        return true;
    }
   
   
     // Esse serve para checar se o usuário tem alguma tarefa na lista dele
    public static boolean listarTarefa(Tarefa IdUsuario) {
         // Pede pro DAO buscar a tarefa lá no banco
        Tarefa tarefa = TarefaDAO.listarTarefa(IdUsuario);
         // Se encontrou alguma coisa (não é nulo), avisa que deu bom
        if(tarefa != null){
            return true;
        }
        // Se não achou nada, falso
        return false;
    }
    
    public static boolean deletarTarefa(Tarefa tarefa) {
        // Se o ID da tarefa for zero ou negativo, tem algo errado
        if(tarefa.getIdTarefa() <= 0) {
            return false;
        }
        // Se o ID for válido, manda o DAO apagar o registro
        TarefaDAO.deletarTarefa(tarefa);
        return true;
    }
   

    public static boolean atualizaTarefa(int id, String tarefa, String andamento) {
        if(tarefa.isBlank() || andamento.isBlank()) {
            // se tentarem atualizar pra um texto vazio, ele não deixa
            return false;
        }
         // Se estiver tudo preenchido, manda atualizar no banco usando o ID
        TarefaDAO.atualizarTarefa(id, tarefa, andamento);
        return true;
    }
  
}


    
