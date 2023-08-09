import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.mdev.tranquil.User

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {


        // Database Version
        private const val DATABASE_VERSION = 1

        // Database Name
        private const val DATABASE_NAME = "tranquil.db"

        // User table name
        public const val TABLE_USER = "user"

        // User Table Columns names
        private const val KEY_ID = "id"
        public const val KEY_EMAIL = "email"
        public const val KEY_PASSWORD = "password"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_USER_TABLE = ("CREATE TABLE " + TABLE_USER + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_EMAIL + " TEXT,"
                + KEY_PASSWORD + " TEXT" + ")")
        db?.execSQL(CREATE_USER_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // Drop older table if existed
        db?.execSQL("DROP TABLE IF EXISTS " + TABLE_USER)

        // Create tables again
        onCreate(db)
    }

    // Adding new user
    fun addUser(user: User): Long {
        val db = this.writableDatabase

        // Creating ContentValues object
        val values = ContentValues()
        values.put(KEY_EMAIL, user.email) // User Email
        values.put(KEY_PASSWORD, user.password) // User Password

        // Inserting Row
        val result = db.insert(TABLE_USER, null, values)

        // Closing database connection
        db.close()

        // Returning result
        return result
    }

    @SuppressLint("Range")
    fun getUser(email: String): User? {
        val db = this.readableDatabase
        val cursor = db.query(
            TABLE_USER,
            arrayOf(KEY_ID, KEY_EMAIL, KEY_PASSWORD),
            "$KEY_EMAIL = ?",
            arrayOf(email),
            null,
            null,
            null,
            null
        )
        if (cursor != null) {
            cursor.moveToFirst()
        } else {
            return null
        }
        // Creating user object
        val user = User(
            cursor.getString(cursor.getColumnIndex(KEY_EMAIL)),
            cursor.getString(cursor.getColumnIndex(KEY_PASSWORD))
        )
        cursor.close()

        // return user
        return user
    }
}

