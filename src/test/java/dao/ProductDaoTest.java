package dao;

import db_migration.DBCreator;
import model.Product;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductDaoTest {
    private static Dao dao;

    @BeforeAll
    static void beforeAll() {
        dao = new ProductDao();
        DBCreator.init();
    }

    @Test
    void all(){
        List<Product> productList = List.of(new Product("1_product", 234234),
                new Product("2_product", 275124),
                new Product("3_product", 289541),
                new Product("4_product", 888563));

        assertArrayEquals(productList.toArray(), dao.all().toArray());
    }

    @Test
    void create() {
        Product product = new Product("test", 666);
        dao.create(product);
        assertEquals(product, dao.read(product.getInnerCode()));
    }

    @Test
    void read() {
        Product product = new Product("1_product", 234234);
        assertEquals(product, dao.read(product.getInnerCode()));
    }

    @Test
    void update() {
        Product product = new Product("test1", 667);
        dao.create(product);
        Product product1 = new Product("test2", 667);
        dao.update(product1);
        assertEquals(product1, dao.read(product1.getInnerCode()));
    }

    @Test
    void delete() {
        Product product = new Product("test2", 666);
        dao.delete(product);
        assertNotEquals(product, dao.read(product.getInnerCode()));
    }
}