package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.Date;

public class Program2 {
    public static void main(String[]args){
        SellerDao sellerDao = DaoFactory.createSellerDao();
        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();

        System.out.println("\n========== Test insert department ==========");
        Department departmentTest = new Department(null, "Vendas");
        departmentDao.insert(departmentTest);
        System.out.println("Id do departamento adicionado: " + departmentTest.getId());

        System.out.println("\n========== Test update department ==========");
        departmentTest.setName("Vendas internas");
        departmentDao.update(departmentTest);
        System.out.printf("Id %d atualizado com sucesso!%n", departmentTest.getId());

        System.out.println("\n========== Test delete department by id ==========");
        departmentDao.deleteById(departmentTest.getId());
        System.out.println("Removido com sucesso!");

        System.out.println("\n========== Test find department by id ==========");
        departmentTest = departmentDao.findById(3);
        System.out.println("Departamento encontrado: " + departmentTest);

        System.out.println("\n========== Test find all department ==========");
        departmentDao.findAll().stream().forEach(System.out::println);


    }
}
