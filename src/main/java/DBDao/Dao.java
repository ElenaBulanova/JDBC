package DBDao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dao {
    private final String url = "jdbc:mysql://localhost:3306/patients";
    private final String user = "root";
    private final String pass = "Vologda1";
    private final Connection connection;

    public Dao() {
        try {
            this.connection = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Patient> getAll() {
        return queryByString("SELECT * FROM PATIENTS");
    }

    public List<Patient> queryByString(String query) {

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            List<DBDao.Patient> patients = new ArrayList<>();
            while (resultSet.next()) {
                patients.add(getFromResultEntry(resultSet));
            }

            return patients;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private DBDao.Patient getFromResultEntry(ResultSet result) throws SQLException {
        return new DBDao.Patient(
                result.getString("fio"),
                getDateFromString(result.getString("birth_date")),
                Integer.parseInt(result.getString("sex")),
                Integer.parseInt(result.getString("num")),
                result.getString("smo"),
                result.getString("snils"),
                result.getString("policy"),
                Integer.parseInt(result.getString("fin_source"))
        );
    }

    private LocalDate getDateFromString(String date) {
        String[] birthday = date.split("-");
        return LocalDate.of(Integer.parseInt(birthday[0]), Integer.parseInt(birthday[1]), Integer.parseInt(birthday[2]));
    }

    public Map<LocalDate, String> getMenFiSource3Birthdate_Fio(){
        String query = "select fio,birth_date as date from Patients where sex = 1 and fin_source = 3;";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            Map<LocalDate, String>patients = new HashMap<>();
            while(resultSet.next()) {
                patients.put(getDateFromString(resultSet.getString("date")),resultSet.getString("fio"));
            }

            return patients;
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<String> getCountMF(){
        String sex = "";
        String query = "SELECT sex, COUNT(*) as count FROM patients GROUP BY sex;";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            List<String> list = new ArrayList<>();
            while(resultSet.next()) {
                if(Integer.parseInt(resultSet.getString("sex")) == 1) sex = "Male: ";
                        else sex = "Female: ";
                list.add("" + sex + resultSet.getString("count"));
            }
            return list;
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    public List<String> getSmo(){
        String query = "SELECT smo, COUNT(*) as count FROM patients GROUP BY smo;";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            List<String> list = new ArrayList<>();
            while(resultSet.next()) {
                list.add("" + resultSet.getString("smo") + ": " + resultSet.getString("count"));
            }
            return list;
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

}
