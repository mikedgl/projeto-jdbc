package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
        SellerDao sellerDao = DaoFactory.createSellerDao();
        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();

        System.out.println("\n========== Test insert seller ==========");
        Seller sellerTest = new Seller(null, "ciclano", "ciclano@gmaill.com", new Date(), 4000.0, departmentDao.findById(3));
        sellerDao.insert(sellerTest);
        System.out.println("Id do vendedor adicionado: " + sellerTest.getId());

        System.out.println("\n========== Test update seller ==========");
        sellerTest.setBaseSalary(5000.0);
        sellerDao.update(sellerTest);
        System.out.printf("Id %d atualizado com sucesso!%n", sellerTest.getId());

        System.out.println("\n========== Test delete seller by id ==========");
        sellerDao.deleteById(sellerTest.getId());
        System.out.println("Removido com sucesso!");

        System.out.println("\n========== Test find seller by id ==========");
        Seller seller = sellerDao.findById(8);
        System.out.println("Vendedor encontrado: " + seller);

        System.out.println("\n========== Test find all sellers ==========");
        sellerDao.findAll().stream().forEach(System.out::println);

        System.out.println("\n========== Test find sellers by department ==========");
        sellerDao.findByDepartment(departmentDao.findById(3)).stream().forEach(System.out::println);

    }
}
