/*
 * This file is generated by jOOQ.
 */
package generated.tables.records;


import generated.tables.InvoicePosition;

import org.jooq.Field;
import org.jooq.Record2;
import org.jooq.Record4;
import org.jooq.Row4;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class InvoicePositionRecord extends UpdatableRecordImpl<InvoicePositionRecord> implements Record4<Integer, Integer, Integer, Integer> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.invoice_position.price</code>.
     */
    public InvoicePositionRecord setPrice(Integer value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>public.invoice_position.price</code>.
     */
    public Integer getPrice() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>public.invoice_position.product_inner_code</code>.
     */
    public InvoicePositionRecord setProductInnerCode(Integer value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>public.invoice_position.product_inner_code</code>.
     */
    public Integer getProductInnerCode() {
        return (Integer) get(1);
    }

    /**
     * Setter for <code>public.invoice_position.amount</code>.
     */
    public InvoicePositionRecord setAmount(Integer value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>public.invoice_position.amount</code>.
     */
    public Integer getAmount() {
        return (Integer) get(2);
    }

    /**
     * Setter for <code>public.invoice_position.invoice_num</code>.
     */
    public InvoicePositionRecord setInvoiceNum(Integer value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>public.invoice_position.invoice_num</code>.
     */
    public Integer getInvoiceNum() {
        return (Integer) get(3);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record2<Integer, Integer> key() {
        return (Record2) super.key();
    }

    // -------------------------------------------------------------------------
    // Record4 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row4<Integer, Integer, Integer, Integer> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    @Override
    public Row4<Integer, Integer, Integer, Integer> valuesRow() {
        return (Row4) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return InvoicePosition.INVOICE_POSITION.PRICE;
    }

    @Override
    public Field<Integer> field2() {
        return InvoicePosition.INVOICE_POSITION.PRODUCT_INNER_CODE;
    }

    @Override
    public Field<Integer> field3() {
        return InvoicePosition.INVOICE_POSITION.AMOUNT;
    }

    @Override
    public Field<Integer> field4() {
        return InvoicePosition.INVOICE_POSITION.INVOICE_NUM;
    }

    @Override
    public Integer component1() {
        return getPrice();
    }

    @Override
    public Integer component2() {
        return getProductInnerCode();
    }

    @Override
    public Integer component3() {
        return getAmount();
    }

    @Override
    public Integer component4() {
        return getInvoiceNum();
    }

    @Override
    public Integer value1() {
        return getPrice();
    }

    @Override
    public Integer value2() {
        return getProductInnerCode();
    }

    @Override
    public Integer value3() {
        return getAmount();
    }

    @Override
    public Integer value4() {
        return getInvoiceNum();
    }

    @Override
    public InvoicePositionRecord value1(Integer value) {
        setPrice(value);
        return this;
    }

    @Override
    public InvoicePositionRecord value2(Integer value) {
        setProductInnerCode(value);
        return this;
    }

    @Override
    public InvoicePositionRecord value3(Integer value) {
        setAmount(value);
        return this;
    }

    @Override
    public InvoicePositionRecord value4(Integer value) {
        setInvoiceNum(value);
        return this;
    }

    @Override
    public InvoicePositionRecord values(Integer value1, Integer value2, Integer value3, Integer value4) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached InvoicePositionRecord
     */
    public InvoicePositionRecord() {
        super(InvoicePosition.INVOICE_POSITION);
    }

    /**
     * Create a detached, initialised InvoicePositionRecord
     */
    public InvoicePositionRecord(Integer price, Integer productInnerCode, Integer amount, Integer invoiceNum) {
        super(InvoicePosition.INVOICE_POSITION);

        setPrice(price);
        setProductInnerCode(productInnerCode);
        setAmount(amount);
        setInvoiceNum(invoiceNum);
    }

    /**
     * Create a detached, initialised InvoicePositionRecord
     */
    public InvoicePositionRecord(generated.tables.pojos.InvoicePosition value) {
        super(InvoicePosition.INVOICE_POSITION);

        if (value != null) {
            setPrice(value.getPrice());
            setProductInnerCode(value.getProductInnerCode());
            setAmount(value.getAmount());
            setInvoiceNum(value.getInvoiceNum());
        }
    }
}
