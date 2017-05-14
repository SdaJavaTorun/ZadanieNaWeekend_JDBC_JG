import JDBC.JDBC_Querry;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        long startTime = System.currentTimeMillis();

        JDBC_Querry querry = new JDBC_Querry();
        System.out.println("stworzylem objekt");
        querry.saveToDatabase();
        System.out.println("zapisalem w bazie");
        querry.countSubordinates(2000);
        System.out.println("wyliczylem podwladnych");
        querry.showSubordinates();
        System.out.println("wypisalem podwladnych");
        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;

        System.out.println(totalTime);

    }
}
