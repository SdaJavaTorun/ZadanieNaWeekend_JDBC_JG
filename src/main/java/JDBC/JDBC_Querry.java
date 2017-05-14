package JDBC;


import Model.Employee;
import Model.Names;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JDBC_Querry {

    private PreparedStatement preparedStatement;
    private Statement statement;
    private List<Integer> bossesList = new ArrayList<Integer>();

    public JDBC_Querry() {
    }

    public void saveToDatabase() throws SQLException, ClassNotFoundException {
        JDBC.getInstance();
        statement = JDBC.getConnection().createStatement();
        statement.executeUpdate("TRUNCATE employee");
        preparedStatement = JDBC.getConnection().prepareStatement(
                "INSERT into employee values (?, ? , ? ,?)");

        for (int i = 1; i < 100001; i++){
            Employee employee = new Employee(i, Names.giveName(), Names.giveLastName(), i/2);
            preparedStatement.setInt(1,employee.getId());
            preparedStatement.setString(2, employee.getName());
            preparedStatement.setString(3, employee.getLastName());
            preparedStatement.setInt(4, employee.getBoos_id());

            preparedStatement.executeUpdate();
        }
        statement.close();
        preparedStatement.close();
    }

    public void countSubordinates(int id) throws SQLException, ClassNotFoundException {
        if ((id * 2) <= 100001){
            this.bossesList.add(id* 2);
            this.bossesList.add(id*2 +1);
            countSubordinates(id * 2);
            countSubordinates(id * 2 + 1);
        }
    }

    public void showSubordinates() throws SQLException, ClassNotFoundException {
        String sql ="";
            for (int i = 0; i < bossesList.size()-1; i++) {
                sql += " ID =" + bossesList.get(i) + " OR ";
            }
            sql += " ID =" + bossesList.get(bossesList.size()-1);

        JDBC.getInstance();
        statement = statement = JDBC.getConnection().createStatement();
        ResultSet subordinates = statement.executeQuery("SELECT * FROM employee WHERE" + sql);

            while (subordinates.next()){
                System.out.println(subordinates.getString("Id") + subordinates.getString("Name" )+
                subordinates.getString("Last_name"));
            }

        statement.close();

    }
}
