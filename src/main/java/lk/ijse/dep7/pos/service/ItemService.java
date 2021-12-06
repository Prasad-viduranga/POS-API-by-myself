package lk.ijse.dep7.pos.service;

import lk.ijse.dep7.pos.dao.ItemDAO;
import lk.ijse.dep7.pos.dto.ItemDTO;
import lk.ijse.dep7.pos.exception.DuplicateIdentifierException;
import lk.ijse.dep7.pos.exception.FailedOperationException;
import lk.ijse.dep7.pos.exception.NotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemService {

    private Connection connection;
    private ItemDAO itemDAO;

    public ItemService() {
    }

    public ItemService(Connection connection) {
        this.connection = connection;
        this.itemDAO = new ItemDAO(connection);

    }

    public void saveItem(ItemDTO item) throws DuplicateIdentifierException, FailedOperationException {

        if (itemDAO.existItem(item.getCode())) {
            throw new DuplicateIdentifierException(item.getCode() + " already exists");
        }

        itemDAO.saveItem(item);

    }

    private boolean existItem(String code) throws SQLException {
        return itemDAO.existItem(code);
    }

    public void updateItem(ItemDTO item) throws FailedOperationException, NotFoundException {

        if (!itemDAO.existItem(item.getCode())) {
            throw new NotFoundException("There is no such item associated with the id " + item.getCode());
        }
        itemDAO.updateItem(item);

    }

    public void deleteItem(String code) throws NotFoundException, FailedOperationException {

        if (!itemDAO.existItem(code)) {
            throw new NotFoundException("There is no such item associated with the id " + code);
        }
        itemDAO.deleteItem(code);
    }

    public ItemDTO findItem(String code) throws NotFoundException, FailedOperationException {

        if (!itemDAO.existItem(code)) {
            throw new NotFoundException("There is no such item associated with the id " + code);
        }

        return itemDAO.findItem(code);

    }

    public List<ItemDTO> findAllItems() throws FailedOperationException {
        try {
            List<ItemDTO> itemList = new ArrayList<>();
            Statement stm = connection.createStatement();
            ResultSet rst = stm.executeQuery("SELECT * FROM item");

            while (rst.next()) {
                itemList.add(new ItemDTO(rst.getString("code"), rst.getString("description"), rst.getBigDecimal("unit_price"), rst.getInt("qty_on_hand")));
            }

            return itemList;
        } catch (SQLException e) {
            throw new FailedOperationException("Failed to find items", e);
        }
    }

    public List<ItemDTO> findAllItems(int page, int size) throws FailedOperationException {
        try {
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM item LIMIT ? OFFSET ?;");
            stm.setObject(1, size);
            stm.setObject(2, size * (page - 1));

            ResultSet rst = stm.executeQuery();
            List<ItemDTO> itemList = new ArrayList<>();

            while (rst.next()) {
                itemList.add(new ItemDTO(rst.getString("code"),
                        rst.getString("description"),
                        rst.getBigDecimal("unit_price"),
                        rst.getInt("qty_on_hand")));
            }
            return itemList;
        } catch (SQLException e) {
            throw new FailedOperationException("Failed to fetch items", e);
        }
    }

    public String generateNewItemCode() throws FailedOperationException {
        try {
            ResultSet rst = connection.createStatement().executeQuery("SELECT code FROM item ORDER BY code DESC LIMIT 1;");

            if (rst.next()) {
                String id = rst.getString("code");
                int newItemId = Integer.parseInt(id.replace("I", "")) + 1;
                return String.format("I%03d", newItemId);
            } else {
                return "I001";
            }
        } catch (SQLException e) {
            throw new FailedOperationException("Failed to generate a new item code", e);
        }
    }

}
