package dao;

import generated.tables.records.OrganizationRecord;
import model.Organization;
import org.jetbrains.annotations.NotNull;
import org.jooq.DSLContext;
import util.ContextProvider;
import java.util.ArrayList;
import java.util.List;

import static generated.Tables.ORGANIZATION;

public class OrganizationDao implements Dao<Organization>{
    private static DSLContext dslContext = ContextProvider.getContext();

    @Override
    public void create(@NotNull Organization organization) {
        dslContext.newRecord(ORGANIZATION).setInn(organization.getINN()).setOrgName(organization.getName()).setPaymentAccount(organization.getPaymentAccount()).store();
    }

    @Override
    public Organization read(@NotNull int INN) {
        Organization organization = null;

        var res = dslContext.select(ORGANIZATION.INN, ORGANIZATION.ORG_NAME, ORGANIZATION.PAYMENT_ACCOUNT).from(ORGANIZATION).where(ORGANIZATION.INN.eq((long) INN));
        for (var row : res){
            organization = new Organization(row.value2(), row.value1(), row.value3());
        }

        return organization;
    }

    @Override
    public void update(@NotNull Organization organization) {
        OrganizationRecord organizationRecord = dslContext.fetchOne(ORGANIZATION, ORGANIZATION.INN.eq(organization.getINN()));
        if (organizationRecord == null) throw new IllegalArgumentException("Couldn't find organization with such INN: " + organization.getINN());
        organizationRecord.setOrgName(organization.getName()).setPaymentAccount(organization.getPaymentAccount()).store();
    }

    @Override
    public void delete(Organization organization) {
        dslContext.delete(ORGANIZATION).where(ORGANIZATION.INN.eq(organization.getINN())).execute();
    }

    @Override
    public List<Organization> all(){
        List<Organization> organizationList = new ArrayList<>();

        var res = dslContext.select(ORGANIZATION.INN, ORGANIZATION.ORG_NAME, ORGANIZATION.PAYMENT_ACCOUNT).from(ORGANIZATION);
        for (var row : res){
            organizationList.add(new Organization(row.value2(), row.value1(), row.value3()));
        }

        return organizationList;
    }
}
