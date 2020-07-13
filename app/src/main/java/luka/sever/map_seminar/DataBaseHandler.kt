package luka.sever.map_seminar
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

val DB_NAME = "DB"

val TABLE_NAME = "Users"
val COL_NAME = "Name"
val COL_PASS = "Password"
val COL_ID = "Id"

val TABLE2_NAME = "Credentials"
val COL_NAME2 = "Name"
val COL_DEETS = "Deets"
val COL_ID2 = "Id"
val COL_USER = "User"
val COL_CITY_CRED = "City"

val TABLE3_NAME = "ParkingLots"
val COL_NAME3 = "Name"
val COL_DEETS2 ="Deets"
val COL_ID3 = "Id"
val COL_CITY = "City"

val TABLE_NAME4 = "Cities"
val COL_NAME4 = "Name"
val COL_ID4 = "Id"
val COL_COUNTRY = "Country"

class DataBaseHandler(context : Context?) : SQLiteOpenHelper(context, DB_NAME, null, 5) {
    override fun onCreate(db: SQLiteDatabase?) {

        //Table Creation
        val createtable = "CREATE TABLE " + TABLE_NAME + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_NAME + " VARCHAR(25)," + COL_PASS + " VARCHAR(25)" + ")"
        db?.execSQL(createtable)

        val cre = "CREATE TABLE " + TABLE_NAME4 + " (" + COL_ID4 + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_NAME4 + " VARCHAR(25)," + COL_COUNTRY + " VARCHAR(25)" + ")"
        db?.execSQL(cre)

        val cr = "CREATE TABLE " + TABLE2_NAME + " (" + COL_ID2 + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_NAME2 + " VARCHAR(25)," + COL_DEETS + " VARCHAR(25)," + COL_USER + " VARCHAR(25)," + COL_CITY_CRED + " VARCHAR(25))"
        db?.execSQL(cr)

        val c = "CREATE TABLE " + TABLE3_NAME + " (" + COL_ID3 + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_NAME3 + " VARCHAR(25)," + COL_DEETS2 + " VARCHAR(25)," + COL_CITY + " VARCHAR(25))"
        db?.execSQL(c)

        //Table Population
        val citytableinit = "INSERT INTO " + TABLE_NAME4 + " (" + COL_NAME4 + ", " + COL_COUNTRY + ") VALUES ('Poreč', 'Croatia'), ('Zadar', 'Croatia'), ('Dubrovnik', 'Croatia')"
        db?.execSQL(citytableinit)

        val credTableInit = "INSERT INTO " + TABLE2_NAME + " (" + COL_NAME2 + ", " + COL_DEETS + ", " + COL_USER + ", " + COL_CITY_CRED + ") VALUES ('ZONE1', '6pm-8pm', 'Name', 'Zagreb'), ('ZONE2', '11am-16pm', 'Name', 'Split'), ('ZONE1', '3pm-10am', 'Zen', 'Poreč')"
        db?.execSQL(credTableInit)

        val parkingTableInit = "INSERT INTO " + TABLE3_NAME + " (" + COL_NAME3 + ", " + COL_DEETS2 + ", " + COL_CITY + ") VALUES ('PARK1', '50 spaces', 'Zagreb'), ('PARK1', '60 spaces', 'Split'), ('PARK2', '40 spaces', 'Zagreb')"
        db?.execSQL(parkingTableInit)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db?.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME4);
        db?.execSQL("DROP TABLE IF EXISTS " + TABLE2_NAME);
        db?.execSQL("DROP TABLE IF EXISTS " + TABLE3_NAME);

        onCreate(db);
    }

    fun insertuser (us : User)
    {
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(COL_NAME, us.name)
        cv.put(COL_PASS, us.password)
        db.insert(TABLE_NAME, null, cv)
    }

    fun getAllUsers() : MutableList<User>
    {
        var List : MutableList<User> = ArrayList<User>()
        val db = this.writableDatabase
        val query = "SELECT * FROM " + TABLE_NAME
        val result = db.rawQuery(query, null)
        if(result.moveToFirst())
        {
            do{
                var us = User(result.getString(result.getColumnIndex(COL_NAME)), result.getString(result.getColumnIndex(COL_PASS)))
                List.add(us)
            }while (result.moveToNext())
        }
        result.close()
        return List
    }

    fun getUser(name : String, pword : String) : User
    {
        var us : User = User("","")
        val db = this.writableDatabase
        val query = "SELECT "  + COL_NAME + ", " + COL_PASS + " FROM " + TABLE_NAME + " WHERE " + COL_NAME +  " = '" +name+ "' AND " + COL_PASS +  " = '" + pword + "'"
        val result = db.rawQuery(query, null)
        if (result.moveToFirst()) {
            us = User(result.getString(result.getColumnIndex(COL_NAME)), result.getString(result.getColumnIndex(COL_PASS)))
        }
        result.close()
        return us
    }

    fun getAllCities() : MutableList<City>
    {
        var List : MutableList<City> = ArrayList<City>()
        val db = this.writableDatabase
        val query = "SELECT * FROM " + TABLE_NAME4
        val result = db.rawQuery(query, null)
        if(result.moveToFirst())
        {
            do{
                var us = City(result.getString(result.getColumnIndex(COL_NAME4)), result.getString(result.getColumnIndex(COL_COUNTRY)))
                List.add(us)
            }while (result.moveToNext())
        }
        result.close()
        return List
    }

    //city must be added as a parameter -- DONE
    fun getCredsForUser(UsrName : String, CityName : String) : MutableList<Credential>
    {
        var Creds : MutableList<Credential> = ArrayList<Credential>()
        val db = this.writableDatabase
        val query = "SELECT "  + COL_NAME2 + ", " + COL_DEETS +  " FROM " + TABLE2_NAME + " WHERE " + COL_USER +  " = '" +UsrName+"' AND " + COL_CITY_CRED +  " = '" + CityName + "'"
        val result = db.rawQuery(query, null)
        if (result.moveToFirst()) {
            do {
                var credential = Credential(result.getString(result.getColumnIndex(COL_NAME2)), result.getString(result.getColumnIndex(COL_DEETS)))
                Creds.add(credential)
            }while (result.moveToNext())
        }
        result.close()
        return Creds
    }

    fun getLotsForCity(CityName : String) : MutableList<ParkingLot>
    {
        var List : MutableList<ParkingLot> = ArrayList<ParkingLot>()
        val db = this.writableDatabase
        val query = "SELECT "  + COL_NAME3 + ", " + COL_DEETS2 + " FROM " + TABLE3_NAME + " WHERE " + COL_CITY +  " = '" +CityName+"'"
        val result = db.rawQuery(query, null)
        if (result.moveToFirst()) {
            do {
                var city = ParkingLot(result.getString(result.getColumnIndex(COL_NAME3)), result.getString(result.getColumnIndex(COL_DEETS2)))
                List.add(city)
            }while (result.moveToNext())
        }
        result.close()
        return List
    }

}