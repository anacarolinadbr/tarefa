/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gerenciadorTarefas.controller;

import gerenciadorTarefas.model.Tarefa;
import gerenciadorTarefas.service.TarefaService;

/**
 *
 * @author ANACAROLINADAMBROSBE
 */
public class TarefaController {
     // Cria um novo objeto Tarefa e solicita o registro através do TarefaService
    public static boolean registrarTarefa(String tarefa, String andamento, int idUsuario) {
         Tarefa novaTarefa = new Tarefa(tarefa, andamento, idUsuario);
         TarefaService.registroTarefa(novaTarefa);
         return true;
    }
    // Solicita a lista de tarefas que estão a um objeto Tarefa 
    public static boolean listarTarefa(Tarefa idUsuario) {
        TarefaService.listarTarefa(idUsuario);
        return true;
    }
    // Faz a exclusão de uma tarefa específica baseada no objeto Tarefa 
    public static boolean deletarTarefa(Tarefa idTarefa) {
        TarefaService.deletarTarefa(idTarefa);
        return true;
    }
    // Atualiza os dados de uma tarefa existente 
    public static boolean atualizarTarefa(int id, String tarefa, String andamento) {
        TarefaService.atualizaTarefa(id, tarefa, andamento);
        return true;
    } 
  
}
