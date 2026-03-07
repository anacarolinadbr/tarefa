/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gerenciadorTarefas.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ANACAROLINADAMBROSBE
 */
public class Conexao {
  
    private static final String URL = "jdbc:mysql://localhost:3306/gerenciador";
    private static final String USUARIO = "root";
    private static final String SENHA = "";

    private static Connection connection;
    
    public static Connection conectar() {
        try {
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar ao banco", e);
        }
    }
  }