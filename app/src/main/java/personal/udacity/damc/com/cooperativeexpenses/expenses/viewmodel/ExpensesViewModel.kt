package personal.udacity.damc.com.cooperativeexpenses.expenses.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import personal.udacity.damc.com.cooperativeexpenses.expenses.model.Expense
import personal.udacity.damc.com.cooperativeexpenses.expenses.model.GroupExpense
import personal.udacity.damc.com.cooperativeexpenses.expenses.persistence.ExpensesRepository
import personal.udacity.damc.com.cooperativeexpenses.expenses.persistence.getExpensesDataBase

class ExpensesViewModel(application: Application) : AndroidViewModel(application) {

    private val database = getExpensesDataBase(application)
    private val repository = ExpensesRepository(database)

    var listOfExpenses = repository.listOfExpenses

    var _listOfGroups = MutableLiveData<ArrayList<GroupExpense>>()
    var newExpense = MutableLiveData<Expense>()

    init {

        newExpense.value = Expense("31", "GASOLINA", "PERALTA", "200", "05/05/05", "DOUG","VACINA")


        _listOfGroups.value = ArrayList()
        _listOfGroups.value!!.add(GroupExpense("1","GASOLINA","05/05/05","300","40"))
        _listOfGroups.value!!.add(GroupExpense("1","CINEMA","05/05/05","500","70"))


        val id_random = (Math.random()*10.0f).toInt().toString()

        newExpense.value = Expense(id_random,
            "","","","05/05/05","TEST","BLABLABLA")

    }

    fun addNewExpense(){
        repository.addExpense(newExpense.value!!)
    }
}