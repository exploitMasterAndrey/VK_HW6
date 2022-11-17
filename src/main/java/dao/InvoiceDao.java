package dao;

import generated.tables.records.InvoiceRecord;
import model.Invoice;
import org.jetbrains.annotations.NotNull;
import org.jooq.DSLContext;
import util.ContextProvider;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static generated.Tables.INVOICE;

public class InvoiceDao implements Dao<Invoice>{
    private static DSLContext dslContext = ContextProvider.getContext();

    @Override
    public void create(@NotNull Invoice entity) {
        dslContext.newRecord(INVOICE).setNum(entity.getNum()).setCreationDate(entity.getCreationDate().toLocalDateTime()).setOrganizationInn(entity.getOrganizationINN()).store();
    }

    @Override
    public Invoice read(@NotNull int num) {
        Invoice invoice = null;

        var res = dslContext.select(INVOICE.NUM, INVOICE.CREATION_DATE, INVOICE.ORGANIZATION_INN).from(INVOICE).where(INVOICE.NUM.eq(num));
        for (var row : res){
            invoice = new Invoice(row.value1(), Timestamp.valueOf(row.value2()), row.value3());
        }

        return invoice;
    }

    @Override
    public void update(@NotNull Invoice entity) {
        InvoiceRecord invoiceRecord = dslContext.fetchOne(INVOICE, INVOICE.NUM.eq(entity.getNum()));
        if (invoiceRecord == null) throw new IllegalArgumentException("Couldn't find invoice with such num: " + entity.getNum());
        invoiceRecord.setOrganizationInn(entity.getOrganizationINN()).setCreationDate(entity.getCreationDate().toLocalDateTime()).store();
    }

    @Override
    public void delete(@NotNull Invoice entity) {
        dslContext.delete(INVOICE).where(INVOICE.NUM.eq(entity.getNum())).execute();
    }

    @NotNull
    @Override
    public List<Invoice> all() {
        List<Invoice> invoiceList = new ArrayList<>();

        var res = dslContext.select(INVOICE.NUM, INVOICE.CREATION_DATE, INVOICE.ORGANIZATION_INN).from(INVOICE);
        for (var row : res){
            invoiceList.add(new Invoice(row.value1(), Timestamp.valueOf(row.value2()), row.value3()));
        }

        return invoiceList;
    }
}
