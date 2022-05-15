package personal.udacity.damc.com.cooperativeexpenses.expenses.persistence

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.RoomDatabase
import personal.udacity.damc.com.cooperativeexpenses.expenses.model.Expense

@Database(entities = [ExpensesDataBaseEntity::class], version = 1, exportSchema = false)
abstract class ExpensesDatabase : RoomDatabase() {
    abstract fun userDao(): ExpensesDAO
}

@Entity
data class ExpensesDataBaseEntity constructor(
    @PrimaryKey
    val id: String,
    val group: String,
    val name: String,
    val value: String,
    val date: String,
    val user: String,
    val explanation: String
)

@Dao
interface ExpensesDAO{
    @Query("SELECT * FROM ExpensesDataBaseEntity")
    fun getAll(): LiveData<List<ExpensesDataBaseEntity>>

    @Query("SELECT * FROM ExpensesDataBaseEntity WHERE id=:id")
    fun getExpenseById(id: String): LiveData<ExpensesDataBaseEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOneExpense(expense: ExpensesDataBaseEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertExpensesInBatch(vararg expense: ExpensesDataBaseEntity)

//    @Query("DELETE FROM expensesdatabaseentity WHERE approach_date<:today")
//    fun deleteOldExpenses(today: String)
}

fun List<ExpensesDataBaseEntity>.asDomainModel(): List<Expense>{
    return map{
        Expense(
            id = it.id,
            group = it.group,
            name = it.name,
            value = it.value,
            date = it.date,
            user = it.user,
            explanation = it.explanation
        )
    }
}

private lateinit var instanceOfExpensesDatabase: ExpensesDatabase

fun getExpensesDataBase(context: Context): ExpensesDatabase{
    synchronized(ExpensesDatabase::class.java) {
        if (!::instanceOfExpensesDatabase.isInitialized) {
            instanceOfExpensesDatabase = Room.databaseBuilder(context.applicationContext,
                ExpensesDatabase::class.java,
                "Expenses")
                .build()
        }
    }
    return instanceOfExpensesDatabase
}