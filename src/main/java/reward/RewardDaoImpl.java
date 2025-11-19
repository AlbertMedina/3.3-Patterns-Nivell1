package reward;

import db.DBConnection;
import db.GenericDao;

import java.sql.*;
import java.util.List;

public class RewardDaoImpl implements GenericDao<Reward> {

    private Connection getConnection() throws SQLException {
        return DBConnection.getConnection();
    }

    private Reward map(ResultSet resultSet) throws SQLException {
        return new Reward(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("description"),
                resultSet.getTimestamp("date").toLocalDateTime(),
                resultSet.getInt("userID")
        );

    }

    @Override
    public Reward findById(int id) {
// String sql = "Select * From Reward WHERE id = ?";
//
//        try (Connection connection = getConnection();
//             PreparedStatement statement = connection.prepareStatement(sql)) {
//
//            statement.setInt(1, id);
//
//            try (ResultSet resultSet = statement.executeQuery()) {
//                if (resultSet.next()) {
//                    return map(resultSet);
//                }
//            }
//
//        } catch (SQLException e) {
//            System.out.println("Error finding Hint by ID: " + e.getMessage());
//        }
//        return null;
//    }
//}
        return null;
    }

    @Override
    public List<Reward> findAll() {
        return List.of();
    }

    @Override
    public boolean insert(Reward element) {
        return false;
    }

    @Override
    public boolean update(Reward element) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}



