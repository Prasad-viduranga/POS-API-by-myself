package lk.ijse.dep7.pos.dao;

import lk.ijse.dep7.pos.dto.ItemDTO;
import lk.ijse.dep7.pos.exception.FailedOperationException;
import lk.ijse.dep7.pos.exception.NotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemDAO {

    private Connection connection;

    public ItemDAO(Connection connection) {
        this.connection = connection;

    }

    public void saveItem(ItemDTO item) {
        try {

            PreparedStatement stm = connection.prepareStatement("INSERT INTO item (code, description, unit_price, qty_on_hand) VALUES (?,?,?,?)");
            stm.setString(1, item.getCode());
            stm.setString(2, item.getDescription());
            stm.setBigDecimal(3, item.getUnitPrice());
            stm.setInt(4, item.getQtyOnHand());
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save the item", e);
        }
    }

    public boolean existItem(String code) {
        try {
            PreparedStatement stm = connection.prepareStatement("SELECT code FROM item WHERE code=?");
            stm.setString(1, code);
            return stm.executeQuery().next();

        } catch (SQLException e) {
            throw new RuntimeException("Failed to find the customer");
        }
    }

    public void updateItem(ItemDTO item) {
        try {

            PreparedStatement stm = connection.prepareStatement("UPDATE item SET description=?, unit_price=?, qty_on_hand=? WHERE code=?");
            stm.setString(1, item.getDescription());
            stm.setBigDecimal(2, item.getUnitPrice());
            stm.setInt(3, item.getQtyOnHand());
            stm.setString(4, item.getCode());
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to update the item " + item.getCode(), e);
        }
    }

    public void deleteItem(String code) {
        try {

            PreparedStatement stm = connection.prepareStatement("DELETE FROM item WHERE code=?");
            stm.setString(1, code);
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete the item " + code, e);
        }
    }

    public ItemDTO findItem(String code) throws NotFoundException, FailedOperationException {

        try {
            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM item WHERE code=?");
            pstm.setString(1, code);
            ResultSet rst = pstm.executeQuery();
            rst.next();
            return new ItemDTO(code, rst.getString("description"), rst.getBigDecimal("unit_price"), rst.getInt("qty_on_hand"));

        } catch (SQLException e) {
            throw new RuntimeException("Failed to find the Item " + code, e);
        }
    }

}
