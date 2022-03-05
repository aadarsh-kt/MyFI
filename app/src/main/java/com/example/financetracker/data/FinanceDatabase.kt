package com.example.financetracker.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.financetracker.data.models.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Income::class, Expenditure::class], version = 1, exportSchema = false)
abstract class FinanceDatabase : RoomDatabase() {

    abstract fun financeDao(): FinanceDao

    companion object {

        @Volatile // Volatile so that changes to this field are instantly made visible to all other threads
        private var INSTANCE: FinanceDatabase? = null

        // create function so that we fetch our data.
        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): FinanceDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FinanceDatabase::class.java,
                    "Finance Database"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(WordDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }

        private class WordDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.financeDao())
                    }
                }
            }
        }

        suspend fun populateDatabase(financeDao: FinanceDao) {


            val newIncome = Income(
                0L, "stocks", "300000", 0xffFF0000
            )
            val newExpenditure = Expenditure(
                0L,"shopping", "4000", 0xff6406FC
            )
            financeDao.insertIncome(newIncome)
            financeDao.insertExpenditure(newExpenditure)
        }
    }
}