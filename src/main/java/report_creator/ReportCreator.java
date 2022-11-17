package report_creator;

import model.Organization;
import model.Product;
import org.jetbrains.annotations.NotNull;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import util.ContextProvider;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static generated.Tables.*;

public class ReportCreator {
    private static DSLContext dslContext = ContextProvider.getContext();

    public static List<Organization> findFirst10suppliers(){
        List<Organization> organizationList = new ArrayList<>();

        var res = dslContext.select(ORGANIZATION.ORG_NAME, ORGANIZATION.INN, ORGANIZATION.PAYMENT_ACCOUNT)
                .from(INVOICE_POSITION)
                .join(INVOICE).on(INVOICE_POSITION.INVOICE_NUM.eq(INVOICE.NUM))
                .join(ORGANIZATION).on(ORGANIZATION.INN.eq(INVOICE.ORGANIZATION_INN))
                .groupBy(ORGANIZATION.INN)
                .orderBy(DSL.sum(INVOICE_POSITION.AMOUNT).desc())
                .limit(10);

        for (var row : res){
            organizationList.add(new Organization(row.value1(), row.value2(), row.value3()));
        }
        return organizationList;
    }

    public static List<Organization> findSuppliersWithProductSumGreaterThanInput(Map<Integer, Integer> productsAndCounts){
        List<Organization> organizationList = new ArrayList<>();

        var res = dslContext.select(ORGANIZATION.ORG_NAME, ORGANIZATION.INN, ORGANIZATION.PAYMENT_ACCOUNT, INVOICE_POSITION.PRODUCT_INNER_CODE, DSL.sum(INVOICE_POSITION.AMOUNT).as("sum"))
                .from(INVOICE_POSITION)
                .join(INVOICE).on(INVOICE_POSITION.INVOICE_NUM.eq(INVOICE.NUM))
                .join(ORGANIZATION).on(INVOICE.ORGANIZATION_INN.eq(ORGANIZATION.INN))
                .groupBy(ORGANIZATION.ORG_NAME, ORGANIZATION.INN, ORGANIZATION.PAYMENT_ACCOUNT, INVOICE_POSITION.PRODUCT_INNER_CODE);

        for (var row : res){
            int product_inner_code = row.value4();
            int amount = row.value5().intValue();
            for (var entry : productsAndCounts.entrySet()){
                int product_inner_code_given = entry.getKey().intValue();
                int amount_given = entry.getValue().intValue();
                if (product_inner_code_given == product_inner_code && amount > amount_given){
                    organizationList.add(new Organization(row.value1(), row.value2(), row.value3()));
                }
            }
        }

        return organizationList;
    }

    public static Double calcAveragePriceDuringPeriod(@NotNull Integer innerCode, @NotNull Timestamp start, @NotNull Timestamp end){
        Double resDouble = null;

        var res = dslContext.select(DSL.avg(INVOICE_POSITION.PRICE).as("avg"))
                .from(INVOICE_POSITION)
                .join(INVOICE).on(INVOICE_POSITION.INVOICE_NUM.eq(INVOICE.NUM))
                .where(INVOICE.CREATION_DATE.between(start.toLocalDateTime(), end.toLocalDateTime()))
                .groupBy(INVOICE_POSITION.PRODUCT_INNER_CODE)
                .having(INVOICE_POSITION.PRODUCT_INNER_CODE.eq(innerCode));

        for (var row : res){
            resDouble = row.value1().doubleValue();
        }

        return resDouble;
    }



    public static Map<LocalDate, List<Map<Integer, Map<String, Integer>>>> calcAmountAndSumEverydayForEveryProductDuringPeriod(@NotNull Timestamp start, @NotNull Timestamp end){
        Map<LocalDate, List<Map<Integer, Map<String, Integer>>>> res = new HashMap<>();

        Map<Integer, Map<String, Integer>> periodResults = new HashMap<>();

        var res1 = dslContext.select(INVOICE_POSITION.PRODUCT_INNER_CODE, INVOICE.CREATION_DATE.cast(LocalDate.class).as("creation_date"), DSL.sum(INVOICE_POSITION.AMOUNT).as("amount"), DSL.sum(INVOICE_POSITION.AMOUNT.multiply(INVOICE_POSITION.PRICE)).as("price"))
                .from(INVOICE_POSITION)
                .join(INVOICE).on(INVOICE_POSITION.INVOICE_NUM.eq(INVOICE.NUM))
                .where(INVOICE.CREATION_DATE.between(start.toLocalDateTime(), end.toLocalDateTime()))
                .groupBy(INVOICE_POSITION.PRODUCT_INNER_CODE, INVOICE.CREATION_DATE.cast(LocalDate.class));

        for (var row : res1){
            if (res.containsKey((LocalDate) row.getValue("creation_date"))){
                List<Map<Integer, Map<String, Integer>>> creation_date = res.get(row.getValue("creation_date"));
                Map<String, Integer> amountPrice = new HashMap<>();
                amountPrice.put("amount", ((BigDecimal)row.getValue("amount")).intValue());
                amountPrice.put("price", ((BigDecimal)row.getValue("price")).intValue());
                creation_date.add(Map.of( (Integer) row.getValue("product_inner_code"), amountPrice));

                if (periodResults.containsKey((Integer)row.getValue("product_inner_code"))){
                    Map<String, Integer> product_inner_code = periodResults.get((Integer) row.getValue("product_inner_code"));
                    if (product_inner_code.get("amount") != null) {
                        product_inner_code.put("amount", product_inner_code.get("amount") + ((BigDecimal) row.getValue("amount")).intValue());
                        product_inner_code.put("price", product_inner_code.get("price") + ((BigDecimal) row.getValue("price")).intValue());
                    }
                }else {
                    Map<String, Integer> product_inner_code = new HashMap<>();
                    product_inner_code.put("amount", ((BigDecimal) row.getValue("amount")).intValue());
                    product_inner_code.put("price",  ((BigDecimal) row.getValue("price")).intValue());

                    periodResults.put((Integer) row.getValue("product_inner_code"), product_inner_code);
                }
            }else {
                List<Map<Integer, Map<String, Integer>>> creation_date = new ArrayList<>();
                Map<String, Integer> amountPrice = new HashMap<>();
                amountPrice.put("amount", ((BigDecimal)row.getValue("amount")).intValue());
                amountPrice.put("price", ((BigDecimal)row.getValue("price")).intValue());
                creation_date.add(Map.of( (Integer) row.getValue("product_inner_code"), amountPrice));
                res.put( (LocalDate) row.getValue("creation_date"), creation_date);

                if (periodResults.containsKey( (Integer) row.getValue("product_inner_code"))){
                    Map<String, Integer> product_inner_code = periodResults.get(((Integer) row.getValue("product_inner_code")));
                    if (product_inner_code.get("amount") != null) {
                        product_inner_code.put("amount", product_inner_code.get("amount") + ((BigDecimal) row.getValue("amount")).intValue());
                        product_inner_code.put("price", product_inner_code.get("price") + ((BigDecimal) row.getValue("price")).intValue());
                    }
                }else {
                    Map<String, Integer> product_inner_code = new HashMap<>();
                    product_inner_code.put("amount",  ((BigDecimal) row.getValue("amount")).intValue());
                    product_inner_code.put("price",  ((BigDecimal) row.getValue("price")).intValue());

                    periodResults.put((Integer) row.getValue("product_inner_code"), product_inner_code);
                }
            }
        }

        System.out.println("TOTAL: " + periodResults);
        return res;
    }

    public static Map<Organization, List<Product>> getDeliveredByOrganizationsProductsListInPeriod(@NotNull Timestamp start, @NotNull Timestamp end){
        Map<Organization, List<Product>> res = new HashMap<>();

        var res1 = dslContext.selectDistinct(ORGANIZATION.ORG_NAME, ORGANIZATION.INN, ORGANIZATION.PAYMENT_ACCOUNT, PRODUCT.PROD_NAME, PRODUCT.INNER_CODE)
                .from(ORGANIZATION)
                .leftJoin(INVOICE).on(INVOICE.ORGANIZATION_INN.eq(ORGANIZATION.INN))
                .leftJoin(INVOICE_POSITION).on(INVOICE_POSITION.INVOICE_NUM.eq(INVOICE.NUM))
                .leftJoin(PRODUCT).on(PRODUCT.INNER_CODE.eq(INVOICE_POSITION.PRODUCT_INNER_CODE))
                .where(INVOICE.CREATION_DATE.between(start.toLocalDateTime(), end.toLocalDateTime()).or(INVOICE.CREATION_DATE.isNull()));

        for (var row : res1){
            Organization organization = new Organization((String) row.getValue("org_name"), (Long) row.getValue("inn"), (Integer) row.getValue("payment_account"));
            if (res.containsKey(organization)){
                List<Product> productList = res.get(organization);
                if (productList != null) productList.add(new Product((String) row.getValue("prod_name"), (Integer)row.getValue("inner_code")));
                else {
                    productList = new ArrayList<>();
                    productList.add(new Product((String) row.getValue("prod_name"), (Integer)row.getValue("inner_code")));
                    res.put(organization, productList);
                }
            }
            else {
                if (row.getValue("prod_name") != null) {
                    List<Product> productList = new ArrayList<>();
                    productList.add(new Product((String) row.getValue("prod_name"), (Integer)row.getValue("inner_code")));
                    res.put(organization, productList);
                }else {
                    res.put(organization, null);
                }
            }
        }

        return res;
    }
}
