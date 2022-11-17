package dao;

import generated.tables.records.InvoicePositionRecord;
import model.Invoice;
import org.jetbrains.annotations.NotNull;
import org.jooq.DSLContext;
import util.ContextProvider;
import java.util.ArrayList;
import java.util.List;

import static generated.Tables.INVOICE_POSITION;

public class InvoicePositionDao implements Dao<Invoice.InvoicePosition>{
    private static DSLContext dslContext = ContextProvider.getContext();

    @Override
    public void create(@NotNull Invoice.InvoicePosition entity) {
        dslContext.newRecord(INVOICE_POSITION).setPrice(entity.getPrice()).setProductInnerCode(entity.getProductInnerCode()).setAmount(entity.getAmount()).setInvoiceNum(entity.getInvoiceNum()).store();
    }

    @Override
    public Invoice.InvoicePosition read(@NotNull int id) {
        return null;
    }

    public Invoice.InvoicePosition read(@NotNull Integer productInnerCode, @NotNull Integer invoiceNum){
        Invoice.InvoicePosition invoicePosition = null;

        var res = dslContext.select(INVOICE_POSITION.INVOICE_NUM, INVOICE_POSITION.AMOUNT, INVOICE_POSITION.PRICE, INVOICE_POSITION.PRODUCT_INNER_CODE).from(INVOICE_POSITION).where(INVOICE_POSITION.PRODUCT_INNER_CODE.eq(productInnerCode).and(INVOICE_POSITION.INVOICE_NUM.eq(invoiceNum)));
        for (var row : res){
            invoicePosition = new Invoice.InvoicePosition(row.value3(), row.value4(), row.value2(), row.value1());
        }

        return invoicePosition;
    }

    @Override
    public void update(@NotNull Invoice.InvoicePosition entity) {
        InvoicePositionRecord invoicePositionRecord = dslContext.fetchOne(INVOICE_POSITION, INVOICE_POSITION.PRODUCT_INNER_CODE.eq(entity.getProductInnerCode()).and(INVOICE_POSITION.INVOICE_NUM.eq(entity.getInvoiceNum())));
        if (invoicePositionRecord == null) throw new IllegalArgumentException("Couldn't find invoice position with such invoice num: " + entity.getInvoiceNum() + " and product inner code: " + entity.getProductInnerCode());
        invoicePositionRecord.setAmount(entity.getAmount()).store();
    }

    @Override
    public void delete(@NotNull Invoice.InvoicePosition entity) {
        dslContext.delete(INVOICE_POSITION).where(INVOICE_POSITION.PRODUCT_INNER_CODE.eq(entity.getProductInnerCode()).and(INVOICE_POSITION.INVOICE_NUM.eq(entity.getInvoiceNum()))).execute();
    }

    @NotNull
    @Override
    public List<Invoice.InvoicePosition> all() {
        List<Invoice.InvoicePosition> invoicePositionList = new ArrayList<>();

        var res = dslContext.select(INVOICE_POSITION.INVOICE_NUM, INVOICE_POSITION.AMOUNT, INVOICE_POSITION.PRICE, INVOICE_POSITION.PRODUCT_INNER_CODE).from(INVOICE_POSITION);
        for (var row : res){
            invoicePositionList.add(new Invoice.InvoicePosition(row.value3(), row.value4(), row.value2(), row.value1()));
        }

        return invoicePositionList;
    }
}
