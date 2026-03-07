/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gerenciadorTarefas.model;

/**
 *
 * @author ANACAROLINADAMBROSBE
 */
public class Tarefa {
    private int idTarefa;
    private String tarefa;
    private String andamento;
    private int idUsuario;

    public int getIdTarefa() {
        return idTarefa;
    }

    public void setIdTarefa(int idTarefa) {
        this.idTarefa = idTarefa;
    }

    public String getTarefa() {
        return tarefa;
    }

    public void setTarefa(String tarefa) {
        this.tarefa = tarefa;
    }

    public String getAndamento() {
        return andamento;
    }

    public void setAndamento(String andamento) {
        this.andamento = andamento;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Tarefa(int idTarefa, String tarefa, String andamento, int idUsuario) {
        this.idTarefa = idTarefa;
        this.tarefa = tarefa;
        this.andamento = andamento;
        this.idUsuario = idUsuario;
    }

    public Tarefa(String tarefa, String andamento, int idUsuario) {
        this.tarefa = tarefa;
        this.andamento = andamento;
        this.idUsuario = idUsuario;
    }

    public Tarefa() {
    }

   
}
