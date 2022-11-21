package dao;

import db_migration.DBCreator;
import model.Invoice;
import model.Organization;
import model.Product;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrganizationDaoTest {
    private static Dao dao;

    @BeforeAll
    static void beforeAll() {
        dao = new OrganizationDao();
        DBCreator.init();
    }

    @Test
    void all(){
        List<Organization> organizationList = List.of(new Organization("1_company", 6449013711l, 123123),
                new Organization("2_company", 5654123843l, 645235),
                new Organization("3_company", 6925813936l, 789444),
                new Organization("4_company", 8744114677l, 978900),
                new Organization("5_company", 6542345576l, 334466),
                new Organization("6_company", 1235437561l, 876867),
                new Organization("7_company", 6333112465l, 123),
                new Organization("8_company", 6752341122l, 645652),
                new Organization("9_company", 4411123611l, 125467),
                new Organization("10_company", 1234556556l, 888246),
                new Organization("11_company", 5555512343l, 909871),
                new Organization("12_company", 9753461234l, 78235));

        assertArrayEquals(organizationList.toArray(), dao.all().toArray());
    }

    @Test
    void create() {
        Organization organization = new Organization("test", 11l, 666);
        dao.create(organization);
        assertEquals(organization, dao.read(organization.getINN().intValue()));
    }

    @Test
    void read() {
        Organization organization = new Organization("test", 11l, 666);
        assertEquals(organization, dao.read((int)11l));
    }

    @Test
    void update() {
        Organization organization = new Organization("test", 111222333l, 666);
        dao.create(organization);
        Organization organization1 = new Organization("test1", 111222333l, 666);
        dao.update(organization1);
        assertEquals(organization1, dao.read(organization1.getINN().intValue()));
    }

    @Test
    void delete() {
        Organization organization = new Organization("test", 111222333l, 666);
        dao.create(organization);
        dao.delete(organization);
        assertNotEquals(organization, dao.read(organization.getINN().intValue()));
    }
}