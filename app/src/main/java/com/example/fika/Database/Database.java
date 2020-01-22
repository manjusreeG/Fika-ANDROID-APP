package com.example.fika.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.example.fika.Order;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteAssetHelper {
    private static  final String DB_NAME = "FikaDB.db";
    private static  final int DB_VERSION = 1;

    public Database(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public List<Order> getCartDetails(){
        SQLiteDatabase database = getReadableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        String[] sqlSelect = {"ProductName","ProductId","ProductQty","Price"};
        String sqlTable = "OrderDetails";

        queryBuilder.setTables(sqlTable);
        Cursor cursor = queryBuilder.query(database,sqlSelect,null,null,null,null,null);

        final List<Order> orderList = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                orderList.add(new Order(cursor.getString(cursor.getColumnIndex("ProductId")),
                        cursor.getString(cursor.getColumnIndex("ProductName")),
                        cursor.getString(cursor.getColumnIndex("ProductQty")),
                        cursor.getString(cursor.getColumnIndex("Price"))
                        ));
            }while (cursor.moveToNext());
        }
        return orderList;
    }

    public void addToCart(Order order){
        SQLiteDatabase database = getReadableDatabase();
        String query = String.format("INSERT INTO OrderDetails(ProductId,ProductName,ProductQty,Price) VALUES('%s','%s','%s','%s');",
                order.getProductId(),order.getProductName(),order.getProductQty(),order.getPrice());
        database.execSQL(query);
    }
    public void cleanCart(){
        SQLiteDatabase database = getReadableDatabase();
        String query = String.format("DELETE FROM OrderDetails ");
        database.execSQL(query);
    }
}
