package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.Date;

public class Main {

    public static void main(String[] args) {
        SellerDao sellerDao = DaoFactory.createSellerDao();
        System.out.println("========= Teste findById =========");
        Seller sellerTest = sellerDao.findById(3);
        System.out.println(sellerTest);
        System.out.println("\n========= Teste findByDepartment =========");
        sellerDao.findByDepartment(new Department(2, "TI")).stream().forEach(System.out::println);
        System.out.println("\n========= Teste findAll =========");
        sellerDao.findAll().stream().forEach(System.out::println);
        System.out.println("\n========= Teste insert =========");
        Department departmentTest = new Department(3,null);
        Seller sellerTest2 = new Seller(null, "Luiza", "luiza@gmail.com", new Date(), 3200.0, departmentTest);
        sellerDao.insert(sellerTest2);
        System.out.println("Inserted! New seller: " + sellerDao.findById(sellerTest2.getId()));
        System.out.println("\n========= Teste update =========");
        Seller sellerTest3 = sellerDao.findById(5);
        sellerTest3.setName("Pedro Lucas");
        sellerDao.update(sellerTest3);
        System.out.println(sellerTest3);
        System.out.println("\n========= Teste delete =========");
        sellerDao.deleteById(10);
        System.out.println("Delete complete!");
    }
}
