package personal.udacity.damc.com.cooperativeexpenses.expenses.persistence

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.map
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import personal.udacity.damc.com.cooperativeexpenses.expenses.model.Expense

class ExpensesRepository(private val database: ExpensesDatabase) {


    private val scopeOfIO = CoroutineScope(Dispatchers.IO)

    var listOfExpenses: LiveData<List<Expense>> = Transformations.map(database.userDao().getAll()){it.asDomainModel()}



    fun getAll(){
        scopeOfIO.launch {
            val tempList = database.userDao().getAll()

            val tempAsDomainList =  tempList.map {
                it.asDomainModel()
            }
        }
    }


    fun addExpense(expense: Expense) {
        scopeOfIO.launch {
            database.userDao()
                .insertOneExpense(
                    ExpensesDataBaseEntity(
                        expense.id,
                        expense.group,
                        expense.name,
                        expense.value,
                        expense.date,
                        expense.user,
                        expense.explanation
                    )
                )
        }
    }
}