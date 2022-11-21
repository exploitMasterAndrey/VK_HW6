package dao;

import db_migration.DBCreator;
import model.Invoice;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InvoiceDaoTest {
    public static Dao dao;

    @BeforeAll
    static void beforeAll() {
        dao = new InvoiceDao();
        DBCreator.init();
    }

    @Test
    void all() {
        List<Invoice> invoices = List.of(new Invoice(56, new Timestamp(122, 8, 7, 5, 50, 0, 0), 6449013711l),
                new Invoice(57, new Timestamp(122, 8, 7, 6, 50, 0, 0), 5654123843l),
                new Invoice(58, new Timestamp(122, 10, 7, 7, 50, 0, 0), 6925813936l),
                new Invoice(59, new Timestamp(122, 1, 7, 8, 50, 0, 0), 8744114677l),
                new Invoice(60, new Timestamp(122, 2, 7, 9, 50, 0, 0), 6542345576l),
                new Invoice(61, new Timestamp(122, 3, 7, 10, 50, 0, 0), 1235437561l),
                new Invoice(62, new Timestamp(122, 4, 7, 11, 50, 0, 0), 6333112465l),
                new Invoice(63, new Timestamp(122, 5, 7, 12, 50, 0, 0), 6752341122l),
                new Invoice(65, new Timestamp(122, 7, 7, 14, 50, 0, 0), 1234556556l),
                new Invoice(66, new Timestamp(121, 8, 7, 15, 50, 0, 0), 5555512343l),
                new Invoice(67, new Timestamp(122, 9, 7, 16, 50, 0, 0), 9753461234l),
                new Invoice(68, new Timestamp(122, 7, 10, 12, 12, 0, 0), 1234556556l)
        );

        assertArrayEquals(invoices.toArray(), dao.all().toArray());
    }

    @Test
    void create() {
        Invoice invoice = new Invoice(1, new Timestamp(1, 1, 1, 1, 1, 1, 0), 6925813936l);
        dao.create(invoice);
        assertEquals(invoice, dao.read(invoice.getNum()));
    }

    @Test
    void read() {
        assertEquals(new Invoice(56, new Timestamp(2022 - 1900, 8, 7, 5, 50, 0, 0), 6449013711l), dao.read(56));
    }

    @Test
    void update() {
        Invoice invoice0 = new Invoice(1, new Timestamp(1, 1, 1, 1, 1, 1, 0), 6925813936l);
        dao.create(invoice0);
        Invoice invoice = new Invoice(1, new Timestamp(1, 1, 1, 1, 1, 1, 0), 1234556556l);
        dao.update(invoice);
        assertEquals(invoice, dao.read(1));
    }

    @Test
    void delete() {
        Invoice invoice = new Invoice(1, new Timestamp(1, 1, 1, 1, 1, 1, 0), 1234556556l);
        dao.delete(invoice);
        assertNotEquals(invoice, dao.read(invoice.getNum()));
    }
}