package com.example.myapplication;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {


    //public static final String DATABASE_NAME="database.db";
//    public static final String TABLE_NAME="Products";
//    public static final String COL_1="ID";
//    public static final String COL_2="NAME";
//    public static final String COL_3="QUANTITY";
//    public static final String COL_4="MEASUREMENT";

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "mealplanner";
    private static final String TABLE_Products = "Products";
    private static final String KEY_PRODUCT_ID = "ProductID";
    private static final String KEY_PRODUCT_NAME = "ProductName";
    private static final String KEY_PRODUCT_UNIT = "Unit";
    private static final String KEY_PRODUCT_INVENTORY_QUANTITY = "InventoryQuantity";

    private static final String TABLE_Recipes = "Recipes";
    private static final String KEY_RECIPE_ID = "RecipeID";
    private static final String KEY_RECIPE_TITLE = "RecipeTitle";
    private static final String KEY_RECIPE_URL = "RecipeURL";

    private static final String TABLE_RecipeProducts = "RecipeProducts";
    private static final String KEY_RECIPE_PRODUCTS_ID = "ID";
    private static final String KEY_RECIPE_PRODUCTS_RECIPE_ID = "RecipeID";
    private static final String KEY_RECIPE_PRODUCTS_PRODUCT_ID = "ProductID";
    private static final String KEY_RECIPE_PRODUCTS_QUANTITY = "RecipeQuantity";

    private static final String VIEW_RecipeProducts = "v_RecipeProducts";


    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       // db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, QUANTITY TEXT, MEASUREMENT TEXT)" );
        String CREATE_TABLE_PRODUCTS = "CREATE TABLE " + TABLE_Products + "("
                + KEY_PRODUCT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_PRODUCT_NAME + " TEXT,"
                + KEY_PRODUCT_UNIT + " TEXT,"
                + KEY_PRODUCT_INVENTORY_QUANTITY + " NUMERIC," +
                "UNIQUE (" + KEY_PRODUCT_NAME + "))";
        db.execSQL(CREATE_TABLE_PRODUCTS);

//        String INSERT_INTO_PRODUCTS="INSERT INTO " + TABLE_Products + "(ProductName,Unit,InventoryQuantity) " +
//                "VALUES (\"Bread\",3,20)," +
//                "(\"Milk\",1,1000),\n" +
//                "(\"Eggs\",3,10),\n" +
//                "(\"Cheese\",2,300),\n" +
//                "(\"Lettice\",2,500)";
//        db.execSQL(INSERT_INTO_PRODUCTS);

        String CREATE_TABLE_RECIPES = "CREATE TABLE " + TABLE_Recipes + "("
                + KEY_RECIPE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_RECIPE_TITLE + " TEXT,"
                + KEY_RECIPE_URL + " TEXT,"
                + "UNIQUE (" + KEY_RECIPE_TITLE + "))";
        db.execSQL(CREATE_TABLE_RECIPES);

//        String INSERT_INTO_RECIPES="INSERT INTO " + TABLE_Recipes + "(RecipeTitle,RecipeURL) " +
//                "VALUES (1,\"Klasiskais Rasols\",\"https://www.delfi.lv/tasty/receptes/klasiskais-rasols.d?id=43626697\"),\n"+
//                "(2,\"Omlete ar sieru un desu\",\"https://www.delfi.lv/tasty/receptes/omlete-ar-sieru-desu-un-pienu.d?id=43625701\"),\n"+
//                "(3,\"Klassiskais brilē krēms\",\"https://www.delfi.lv/tasty/receptes/klasiskais-brile-krems.d?id=50262015\")";
//        db.execSQL(INSERT_INTO_RECIPES);

        String CREATE_TABLE_RECIPE_PRODUCTS = "CREATE TABLE " + TABLE_RecipeProducts + "("
                + KEY_RECIPE_PRODUCTS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_RECIPE_PRODUCTS_RECIPE_ID + " INTEGER,"
                + KEY_RECIPE_PRODUCTS_PRODUCT_ID + " INTEGER,"
                + KEY_RECIPE_PRODUCTS_QUANTITY + " NUMERIC,"
                + "UNIQUE (" + KEY_RECIPE_PRODUCTS_RECIPE_ID + ", " + KEY_RECIPE_PRODUCTS_PRODUCT_ID + "))";
        db.execSQL(CREATE_TABLE_RECIPE_PRODUCTS);

//        String INSERT_INTO_RECIPE_PRODUCTS="INSERT INTO " + TABLE_RecipeProducts + "(RecipeID,ProductID,RecipeQuantity) " +
//                "VALUES (1,4,500)," +
//                "(1,3,10)";
//        db.execSQL(INSERT_INTO_RECIPE_PRODUCTS);

        String CREATE_VIEW_RECIPE_PRODUCTS="CREATE VIEW " + VIEW_RecipeProducts + "AS SELECT Product.ProductID, Product.ProductName,"
                + " Product.InventoryQuantity, RecipeProducts.RecipeQuantity, Recipes.RecipeID,"
                + " Recipes.RecipeTitle, Recipes.RecipeURL FROM Product"
                + " INNER JOIN RecipeProducts ON RecipeProducts.ProductID = Product.ProductID"
                + " INNER JOIN Recipes ON RecipeProducts.RecipeID=Recipes.RecipeID";
        db.execSQL(CREATE_VIEW_RECIPE_PRODUCTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +  TABLE_Products);
        onCreate(db);
    }

    public boolean insertData(String name, String quantity, String measurement){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(KEY_PRODUCT_NAME, name);
        contentValues.put(KEY_PRODUCT_INVENTORY_QUANTITY, quantity);
        contentValues.put(KEY_PRODUCT_UNIT, measurement);

        long result = db.insert(TABLE_Products, null, contentValues);

        if(result==-1)
            return false;
        else
            return true;
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_Products, null);
        return res;
    }

    public boolean updateData(String id, String name, String quantity, String measurement){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(KEY_PRODUCT_NAME, name);
        contentValues.put(KEY_PRODUCT_INVENTORY_QUANTITY, quantity);
        contentValues.put(KEY_PRODUCT_UNIT, measurement);
        db.update(TABLE_Products, contentValues, "ID = ?", new String[] {id});
        return true;

    }

    public Integer deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_Products, "ID = ?", new String[] {id});

    }
}


