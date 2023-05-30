package ppss.matriculacion.dao;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.dbunit.Assertion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ppss.matriculacion.to.AlumnoTO;

import java.time.LocalDate;

public class AlumnoDAOIT {
    private IDatabaseTester databaseTester;
    private IDatabaseConnection connection;
    private IAlumnoDAO alumnoDAO;
    private AlumnoTO alumnoTO;

    @BeforeEach
    public void setUp() throws Exception {
        String cadena_conexion = "jdbc:mysql://localhost:3306/matriculacion?useSSL=false";
        databaseTester = new JdbcDatabaseTester("com.mysql.cj.jdbc.Driver", cadena_conexion, "root", "ppss");
        connection = databaseTester.getConnection();

        //inicializar dataset
        IDataSet dataset = new FlatXmlDataFileLoader().load("/tabla2.xml");
        databaseTester.setDataSet(dataset);

        //onsetup
        databaseTester.onSetup();

        alumnoDAO = new FactoriaDAO().getAlumnoDAO();
        alumnoTO = new AlumnoTO();
    }

    @Test
    @Tag("Integracion-fase1")
    public void testA1() throws Exception {
        alumnoTO.setNif("33333333C");
        alumnoTO.setNombre("Elena Aguirre Juarez");
        alumnoTO.setFechaNacimiento(LocalDate.of(1985, 2, 22));

        //invocamos la SUT
        Assertions.assertDoesNotThrow(() -> {
            alumnoDAO.addAlumno(alumnoTO);
        });

        //obtenemos valores reales
        IDataSet databaseDataSet = connection.createDataSet();
        ITable actualTable = databaseDataSet.getTable("alumnos");

        IDataSet expectedDataSet = new FlatXmlDataFileLoader().load("/tabla3.xml");
        ITable expectedTable = expectedDataSet.getTable("alumnos");

        Assertion.assertEquals(expectedTable, actualTable);
    }

    @Test
    @Tag("Integracion-fase1")
    public void testA2() throws Exception {
        alumnoTO.setNif("11111111A");
        alumnoTO.setNombre("Alfonso Ramirez Ruiz");
        alumnoTO.setFechaNacimiento(LocalDate.of(1982, 2, 22));

        Assertions.assertThrows(DAOException.class, () -> {alumnoDAO.addAlumno(alumnoTO);}, "Error al conectar con BD");

        //obtenemos valores reales
        IDataSet databaseDataSet = connection.createDataSet();
        ITable actualTable = databaseDataSet.getTable("alumnos");

        IDataSet expectedDataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        ITable expectedTable = expectedDataSet.getTable("alumnos");

        Assertion.assertEquals(expectedTable, actualTable);
    }

    @Test
    @Tag("Integracion-fase1")
    public void testA3() throws Exception {
        alumnoTO.setNif("44444444D");
        alumnoTO.setFechaNacimiento(LocalDate.of(1982, 2, 22));

        Assertions.assertThrows(DAOException.class, () -> {alumnoDAO.addAlumno(alumnoTO);}, "Error al conectar con BD");

        //obtenemos valores reales
        IDataSet databaseDataSet = connection.createDataSet();
        ITable actualTable = databaseDataSet.getTable("alumnos");

        IDataSet expectedDataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        ITable expectedTable = expectedDataSet.getTable("alumnos");

        Assertion.assertEquals(expectedTable, actualTable);
    }

    @Test
    @Tag("Integracion-fase1")
    public void testA4() throws Exception {

        Assertions.assertThrows(DAOException.class, () -> {alumnoDAO.addAlumno(alumnoTO);}, "Alumno nulo");

        //obtenemos valores reales
        IDataSet databaseDataSet = connection.createDataSet();
        ITable actualTable = databaseDataSet.getTable("alumnos");

        IDataSet expectedDataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        ITable expectedTable = expectedDataSet.getTable("alumnos");

        Assertion.assertEquals(expectedTable, actualTable);
    }

    @Test
    @Tag("Integracion-fase1")
    public void testA5() throws Exception {
        alumnoTO.setNombre("Pedro Garcia Lopez");
        alumnoTO.setFechaNacimiento(LocalDate.of(1982, 2, 22));
        Assertions.assertThrows(DAOException.class, () -> {alumnoDAO.addAlumno(alumnoTO);}, "Error al conectar con BD");

        //obtenemos valores reales
        IDataSet databaseDataSet = connection.createDataSet();
        ITable actualTable = databaseDataSet.getTable("alumnos");

        IDataSet expectedDataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        ITable expectedTable = expectedDataSet.getTable("alumnos");

        Assertion.assertEquals(expectedTable, actualTable);
    }

    @Test
    @Tag("Integracion-fase1")
    public void testB1() throws Exception {
        String nif = "11111111A";
        Assertions.assertDoesNotThrow(() -> {alumnoDAO.delAlumno(nif);});

        //obtenemos valores reales
        IDataSet databaseDataSet = connection.createDataSet();
        ITable actualTable = databaseDataSet.getTable("alumnos");

        IDataSet expectedDataSet = new FlatXmlDataFileLoader().load("/tabla4.xml");
        ITable expectedTable = expectedDataSet.getTable("alumnos");

        Assertion.assertEquals(expectedTable, actualTable);
    }

    @Test
    @Tag("Integracion-fase1")
    public void testB2() throws Exception {
        String nif = "33333333C";
        Assertions.assertThrows(DAOException.class, () -> {alumnoDAO.delAlumno(nif);}, "No se ha borrado ningun alumno");

        //obtenemos valores reales
        IDataSet databaseDataSet = connection.createDataSet();
        ITable actualTable = databaseDataSet.getTable("alumnos");

        IDataSet expectedDataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        ITable expectedTable = expectedDataSet.getTable("alumnos");

        Assertion.assertEquals(expectedTable, actualTable);
    }
}
