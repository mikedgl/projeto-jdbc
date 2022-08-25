package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Main {

    public static void main(String[] args) {
        SellerDao sellerDao = DaoFactory.createSellerDao();
        System.out.println("========= Teste findById =========");
        Seller sellerTeste = sellerDao.findById(3);
        System.out.println(sellerTeste);
        System.out.println("\n========= Teste findByDepartment =========");
        sellerDao.findByDepartment(new Department(2, "TI")).stream().forEach(System.out::println);
        System.out.println("\n========= Teste findAll =========");
        sellerDao.findAll().stream().forEach(System.out::println);
    }
}
