package dao;

import db_migration.DBCreator;
import model.Invoice;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InvoicePositionDaoTest {
    private static InvoicePositionDao dao;

    @BeforeAll
    static void beforeAll(){
        dao = new InvoicePositionDao();
        DBCreator.init();
    }

    @Test
    void all(){
        List<Invoice.InvoicePosition> invoicePositionList = List.of(new Invoice.InvoicePosition(700, 234234, 50, 56),
                new Invoice.InvoicePosition(600, 275124, 10, 57),
                new Invoice.InvoicePosition(600, 234234, 71, 57),
                new Invoice.InvoicePosition(500, 275124, 60, 58),
                new Invoice.InvoicePosition(600, 289541, 80, 59),
                new Invoice.InvoicePosition(690, 289541, 17, 60),
                new Invoice.InvoicePosition(480, 888563, 55, 61),
                new Invoice.InvoicePosition(370, 289541, 68, 62),
                new Invoice.InvoicePosition(900, 275124, 70, 63),
                new Invoice.InvoicePosition(550, 888563, 45, 65),
                new Invoice.InvoicePosition(500, 234234, 78, 66),
                new Invoice.InvoicePosition(470, 275124, 51, 67),
                new Invoice.InvoicePosition(490, 888563, 20, 68));

        assertArrayEquals(invoicePositionList.toArray(), dao.all().toArray());
    }

    @Test
    void create() {
        Invoice.InvoicePosition invoicePosition = new Invoice.InvoicePosition(491, 234234, 19, 60);
        dao.create(invoicePosition);
        assertEquals(invoicePosition, dao.read(234234, 60));
    }

    @Test
    void read() {
        Invoice.InvoicePosition invoicePosition = new Invoice.InvoicePosition(700, 234234, 50, 56);
        assertEquals(invoicePosition, dao.read(234234, 56));
    }

    @Test
    void update() {
        Invoice.InvoicePosition invoicePosition = new Invoice.InvoicePosition(491, 234234, 19, 67);
        dao.create(invoicePosition);
        Invoice.InvoicePosition invoicePosition1 = new Invoice.InvoicePosition(491, 234234, 21, 67);
        dao.update(invoicePosition1);
        assertEquals(invoicePosition1, dao.read(234234, 67));
    }

    @Test
    void delete() {
        Invoice.InvoicePosition invoicePosition = new Invoice.InvoicePosition(491, 234234, 21, 67);
        dao.create(invoicePosition);
        dao.delete(invoicePosition);
        assertNotEquals(invoicePosition, dao.read(234234, 67));
    }
}