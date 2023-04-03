import java.sql.*;
public class Application {

    public static void main(String[] args) throws SQLException {

        final String user = "postgres";
        final String password = "Iulia6534";
        final String url = "jdbc:postgresql://localhost:5432/skypro";

        try (final Connection connection =
                     DriverManager.getConnection(url, user, password);
             PreparedStatement statement =
                     connection.prepareStatement("SELECT first_name, last_name, gender, age, city_name FROM employee INNER JOIN city\n" +
                             "ON employee.city_id = city.city_id WHERE id = (?)")) {
            statement.setInt(1, 6);
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String name = "Name: " + resultSet.getString("first_name");
                String lastname = "Lastname: " + resultSet.getString("last_name");
                String gender = "Gender: " + resultSet.getString(3);
                int age = resultSet.getInt(4);
                String city_name = resultSet.getString("city_name");
                System.out.println(name + ", " +  lastname + ", " +  gender + ", Age: " + age + ", City: " + city_name);
            }
        } catch (SQLException e) {
            System.out.println("Ошибка при подключении к БД!");
            e.printStackTrace();
        }
    }
}
