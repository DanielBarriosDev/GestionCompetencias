/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package gestioncompetencias;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;

import javax.swing.JOptionPane;

/**
 *
 * @author DanielBarrios
 */
public class GestionCompetencias {

    public static void main(String[] args) {
        
        String usuario = "root";
        String password = "123456789";
        String url = "jdbc:mysql://localhost:3308/bd_timecrafters";
        
        boolean continuar = true;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conexion = DriverManager.getConnection(url, usuario, password);
            Statement st = conexion.createStatement();

            while (continuar) {
                try {
                    int opcion = Integer.parseInt(JOptionPane.showInputDialog("Seleccione una opción:"
                                + "\n1. Insertar competencia"
                                + "\n2. Consultar competencia"
                                + "\n3. Actualizar competencia"
                                + "\n4. Eliminar competencia"
                                + "\n5. Salir"));
    
                    switch (opcion) {
                        case 1:
                            String nombreCompetencia = JOptionPane.showInputDialog("Introduzca el nombre de la competancia: ");
                            int tiempoCompetencia = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el tiempo de la competencia (Horas): "));
                            LocalDate fechaInicioCompetencia = LocalDate.parse(JOptionPane.showInputDialog("Ingrese la fecha de incio (AAAA-MM-DD):"));
                            LocalDate fechaFinCompetencia = LocalDate.parse(JOptionPane.showInputDialog("Ingrese la fecha de fin (AAAA-MM-DD):"));
                            int resultadoCompetencia = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el resultado de la competencia por ID: "));

    
                            st.executeUpdate("Insert into competencias values(null, '" + nombreCompetencia + "', " + tiempoCompetencia + ", '" + fechaInicioCompetencia + "', '" + fechaFinCompetencia + "', '" + resultadoCompetencia +"')");
                            JOptionPane.showMessageDialog(null, "Se registro satisfactoriamente la competencia");
    
                            break;
                        case 2:
                            ResultSet resultadoConsulta = st.executeQuery("select * from competencias order by competencias_id ASC");
                            System.out.println("\nID\tNombre competencia\tTiempo\tFecha inicio\tFecha fin\tResultado competencia");
    
                            while (resultadoConsulta.next()) {
                                System.out.println(resultadoConsulta.getInt("competencias_id") +
                                        "\t" + resultadoConsulta.getString("competencias_nombre")
                                        + "\t" + resultadoConsulta.getInt("competencias_tiempo")
                                        + "\t" + resultadoConsulta.getDate("competencias_fecha_inicio").toLocalDate()
                                        + "\t" + resultadoConsulta.getDate("competencias_fecha_fin").toLocalDate()
                                        + "\t" + resultadoConsulta.getInt("competencias_resultados_competencias_id"));
                            }
    
                            break;
                        case 3: 
                            int idCompetencia = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID de la competencia:"));
                            String nuevoNombreCompetencia = JOptionPane.showInputDialog("Introduza el nuevo nombre de la competencia");
    
                            st.executeUpdate("UPDATE competencias SET competencias_nombre = '" + nuevoNombreCompetencia + "' WHERE competencias_id = " + idCompetencia);
    
                            JOptionPane.showMessageDialog(null, "Se actualizo correctamente la competencia");
                            
                            break;
                        case 4:
                            int eliminarCompetencia = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID de la competencia que desea eliminar:"));
                            st.executeUpdate("delete from competencias where competencias_id = " + eliminarCompetencia);
    
                            JOptionPane.showMessageDialog(null, "Competencia eliminada correctamente");
                            break;
                        case 5:
                            continuar = false;
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "Opción no válida");
                            break;
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Debe ingresar un número válido.");
                }
            }

            st.close();
            conexion.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
        
        
    }
    

