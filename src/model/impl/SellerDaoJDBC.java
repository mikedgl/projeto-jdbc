package model.impl;

import db.Db;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SellerDaoJDBC implements SellerDao {
    private Connection connection;

    public SellerDaoJDBC(Connection connection){
        this.connection = connection;
    }

    @Override
    public void insert(Seller seller) {

    }

    @Override
    public void update(Seller seller) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Seller findById(Integer id) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("select seller.*, department.name as dep_name from seller inner join department on seller.department_id = department.id where seller.id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                Department department = instantiateDepartment(resultSet);
                Seller seller = instantiateSeller(resultSet, department);
                return seller;
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            Db.closeResultSet(resultSet);
            Db.closeStatement(preparedStatement);
        }
        return null;
    }

    private Department instantiateDepartment(ResultSet resultSet) throws SQLException{
        Department department = new Department();
        department.setId(resultSet.getInt("department_id"));
        department.setName(resultSet.getString("dep_name"));
        return  department;
    }
    private Seller instantiateSeller(ResultSet resultSet, Department department) throws SQLException{
        Seller seller = new Seller();
        seller.setId(resultSet.getInt("id"));
        seller.setName(resultSet.getString("name"));
        seller.setEmail(resultSet.getString("email"));
        seller.setBirthDate(resultSet.getDate("birth_date"));
        seller.setBaseSalary(resultSet.getDouble("base_salary"));
        seller.setDepartment(department);
        return seller;
    }

    @Override
    public List<Seller> findAll() {
        return null;
    }

    @Override
    public List<Seller> findByDepartment(Department department) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Seller> sellers = new ArrayList<>();
        try {
         preparedStatement = connection.prepareStatement("select seller.*, department.name as dep_name from seller inner join department on seller.department_id = department.id where seller.department_id = ? order by name");
         preparedStatement.setInt(1, department.getId());
         resultSet = preparedStatement.executeQuery();
         while (resultSet.next()){
             Seller seller = instantiateSeller(resultSet, department);
             sellers.add(seller);
         }
         return  sellers;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Db.closeResultSet(resultSet);
            Db.closeStatement(preparedStatement);
        }
        return null;
    }
}
