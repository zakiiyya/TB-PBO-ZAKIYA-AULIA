import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class Farm implements FarmOperations {
    private Connection connection;

    public Farm() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addCow(Cow cow) {
        try {
            // Tambahkan data ke tabel Cows (tabel utama)
            String sqlCows = "INSERT INTO Cows (name, birth_date, maintenance_cost, type) VALUES (?, ?, ?, ?)";
            PreparedStatement statementCows = connection.prepareStatement(sqlCows, Statement.RETURN_GENERATED_KEYS);
            statementCows.setString(1, cow.getName());
            statementCows.setDate(2, new java.sql.Date(cow.getBirthDate().getTime()));
            statementCows.setDouble(3, cow.getMaintenanceCost());
            statementCows.setString(4, cow.getCategory()); // Simpan tipe sapi
            statementCows.executeUpdate();

            // Ambil ID yang di-generate secara otomatis
            ResultSet generatedKeys = statementCows.getGeneratedKeys();
            if (generatedKeys.next()) {
                int cowId = generatedKeys.getInt(1);

                // Simpan data tambahan berdasarkan tipe sapi
                if (cow instanceof DairyCow) {
                    String sqlDairy = "INSERT INTO DairyCows (id, milk_production) VALUES (?, ?)";
                    PreparedStatement statementDairy = connection.prepareStatement(sqlDairy);
                    statementDairy.setInt(1, cowId);
                    statementDairy.setDouble(2, ((DairyCow) cow).getMilkProduction());
                    statementDairy.executeUpdate();
                } else if (cow instanceof BeefCow) {
                    String sqlBeef = "INSERT INTO BeefCows (id, weight) VALUES (?, ?)";
                    PreparedStatement statementBeef = connection.prepareStatement(sqlBeef);
                    statementBeef.setInt(1, cowId);
                    statementBeef.setDouble(2, ((BeefCow) cow).getWeight());
                    statementBeef.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<Cow> getCows() {
        List<Cow> cows = new ArrayList<>();
        try {
            String sql = "SELECT * FROM cows";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Date birthDate = resultSet.getDate("birth_date");
                double maintenanceCost = resultSet.getDouble("maintenance_cost");
                String type = resultSet.getString("type");

                if ("Dairy Cow".equals(type)) {
                    double milkProduction = resultSet.getDouble("milk_production");
                    cows.add(new DairyCow(id, name, birthDate, maintenanceCost, milkProduction));
                } else if ("Beef Cow".equals(type)) {
                    double weight = resultSet.getDouble("weight");
                    cows.add(new BeefCow(id, name, birthDate, maintenanceCost, weight));
                } else {
                    cows.add(new Cow(id, name, birthDate, maintenanceCost));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cows;
    }

    @Override
    public void updateCow(Cow cow) {
        try {
            String sql = "UPDATE cows SET name = ?, maintenance_cost = ?, type = ?, milk_production = ?, weight = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, cow.getName());
            statement.setDouble(2, cow.getMaintenanceCost());
            statement.setString(3, cow.getCategory());
            if (cow instanceof DairyCow) {
                statement.setDouble(4, ((DairyCow) cow).getMilkProduction());
                statement.setNull(5, java.sql.Types.DOUBLE);
            } else if (cow instanceof BeefCow) {
                statement.setNull(4, java.sql.Types.DOUBLE);
                statement.setDouble(5, ((BeefCow) cow).getWeight());
            } else {
                statement.setNull(4, java.sql.Types.DOUBLE);
                statement.setNull(5, java.sql.Types.DOUBLE);
            }
            statement.setInt(6, cow.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCow(int id) {
        try {
            String sql = "DELETE FROM cows WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
