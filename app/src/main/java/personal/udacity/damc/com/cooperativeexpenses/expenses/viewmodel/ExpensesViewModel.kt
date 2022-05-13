package personal.udacity.damc.com.cooperativeexpenses.expenses.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import personal.udacity.damc.com.cooperativeexpenses.expenses.model.Expense

class ExpensesViewModel : ViewModel() {

    var _listOfExpenses = MutableLiveData<ArrayList<Expense>>()

    init {
        _listOfExpenses.value = ArrayList()
        _listOfExpenses.value!!.add(Expense("1","GASOLINA","IFAM - POSTO BR","TARGET: R$300","05/05/05","doug"))
        _listOfExpenses.value!!.add(Expense("2","CINEMA","DR. ESTRANHO","TARGET: R$500","05/05/05","GRAZI"))
    }
}