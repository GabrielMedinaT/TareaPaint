package tareapaint;

import java.awt.Polygon;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Modelo {

    private Connection conexion;

    public Modelo() {
        conectarBaseDatos();

    }

    private void conectarBaseDatos() {
        String url = "jdbc:mysql://localhost:3306/paint";
        String usuario = "root";
        String contraseña = "root1234";

        try {
            conexion = DriverManager.getConnection(url, usuario, contraseña);
            System.out.println("Conexión exitosa a la base de datos");
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }
    }

public void insertarFigura(Polygon figura, String nombreArchivo) {
    String sqlInsertarPoligono = "INSERT INTO Poligonos (numeroLados, nombre_archivo) VALUES (?, ?)";
    String sqlInsertarPunto = "INSERT INTO PuntosPoligono (idPoligono, coordenadaX, coordenadaY) VALUES (?, ?, ?)";

    try {
        PreparedStatement psPoligono = conexion.prepareStatement(sqlInsertarPoligono, PreparedStatement.RETURN_GENERATED_KEYS);
        psPoligono.setInt(1, figura.npoints);
        psPoligono.setString(2, nombreArchivo); // Aquí insertamos el nombre del archivo
        psPoligono.executeUpdate();
        ResultSet rs = psPoligono.getGeneratedKeys();
        int idPoligono = 0;
        if (rs.next()) {
            idPoligono = rs.getInt(1);
        }
        if (idPoligono == 0) {
            try {
                java.sql.Statement stmt = conexion.createStatement();
                ResultSet rsMaxId = stmt.executeQuery("SELECT MAX(idPoligono) FROM Poligonos");
                if (rsMaxId.next()) {
                    idPoligono = rsMaxId.getInt(1) + 1;
                }
            } catch (SQLException e) {
                System.out.println("Error al obtener el máximo ID de Poligonos: " + e.getMessage());
            }
        }

        PreparedStatement psPunto = conexion.prepareStatement(sqlInsertarPunto);
        for (int i = 0; i < figura.npoints; i++) {
            psPunto.setInt(1, idPoligono);
            psPunto.setInt(2, figura.xpoints[i]);
            psPunto.setInt(3, figura.ypoints[i]);
            psPunto.executeUpdate();
        }

        System.out.println("Figura insertada correctamente en la base de datos");

    } catch (SQLException e) {
        System.out.println("Error al insertar la figura en la base de datos: " + e.getMessage());
    }
}


    public List<Figura> consultarFiguras() {
        List<Figura> figuras = new ArrayList<>();
        return figuras;
    }
}
