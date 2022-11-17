package dao;

import generated.tables.records.ProductRecord;
import model.Product;
import org.jetbrains.annotations.NotNull;
import org.jooq.DSLContext;
import util.ContextProvider;

import java.util.ArrayList;
import java.util.List;

import static generated.Tables.PRODUCT;

public class ProductDao implements Dao<Product>{
    private static DSLContext dslContext = ContextProvider.getContext();

    @Override
    public void create(@NotNull Product entity) {
        dslContext.newRecord(PRODUCT).setProdName(entity.getName()).setInnerCode(entity.getInnerCode()).store();
    }

    @Override
    public Product read(@NotNull int innerCode) {
        Product product = null;

        var res = dslContext.select(PRODUCT.PROD_NAME, PRODUCT.INNER_CODE).from(PRODUCT).where(PRODUCT.INNER_CODE.eq(innerCode));
        for (var row : res){
            product = new Product(row.value1(), row.value2());
        }

        return product;
    }

    @Override
    public void update(@NotNull Product entity) {
        ProductRecord productRecord = dslContext.fetchOne(PRODUCT, PRODUCT.INNER_CODE.eq(entity.getInnerCode()));
        if (productRecord == null) throw new IllegalArgumentException("Couldn't find product with such inner code: " + entity.getInnerCode());
        productRecord.setProdName(entity.getName()).store();
    }

    @Override
    public void delete(@NotNull Product entity) {
        dslContext.delete(PRODUCT).where(PRODUCT.INNER_CODE.eq(entity.getInnerCode())).execute();
    }

    @NotNull
    @Override
    public List<Product> all() {
        List<Product> productList = new ArrayList<>();

        var res = dslContext.select(PRODUCT.PROD_NAME, PRODUCT.INNER_CODE).from(PRODUCT);
        for (var row : res){
            productList.add(new Product(row.value1(), row.value2()));
        }

        return productList;
    }
}
