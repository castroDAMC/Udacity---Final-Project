package personal.udacity.damc.com.cooperativeexpenses.expenses.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import personal.udacity.damc.com.cooperativeexpenses.expenses.model.Expense
import personal.udacity.damc.com.cooperativeexpenses.expenses.model.GroupExpense

class ExpensesViewModel : ViewModel() {

    var _listOfExpenses = MutableLiveData<ArrayList<Expense>>()
    var _listOfGroups = MutableLiveData<ArrayList<GroupExpense>>()

    init {
        _listOfExpenses.value = ArrayList()
        _listOfExpenses.value!!.add(Expense("1","GASOLINA","IFAM - POSTO BR","300","05/05/05","doug",""))
        _listOfExpenses.value!!.add(Expense("2","CINEMA","DR. ESTRANHO","500","05/05/05","GRAZI",""))
        _listOfExpenses.value!!.add(Expense("1","GASOLINA","IFAM - POSTO BR","300","05/05/05","doug",""))
        _listOfExpenses.value!!.add(Expense("2","CINEMA","DR. ESTRANHO","500","05/05/05","GRAZI",""))
        _listOfExpenses.value!!.add(Expense("1","GASOLINA","IFAM - POSTO BR","300","05/05/05","doug",""))
        _listOfExpenses.value!!.add(Expense("2","CINEMA","DR. ESTRANHO","500","05/05/05","GRAZI",""))
        _listOfExpenses.value!!.add(Expense("1","GASOLINA","IFAM - POSTO BR","300","05/05/05","doug",""))
        _listOfExpenses.value!!.add(Expense("2","CINEMA","DR. ESTRANHO","500","05/05/05","GRAZI",""))
        _listOfExpenses.value!!.add(Expense("1","GASOLINA","IFAM - POSTO BR","300","05/05/05","doug",""))
        _listOfExpenses.value!!.add(Expense("2","CINEMA","DR. ESTRANHO","500","05/05/05","GRAZI",""))
        _listOfExpenses.value!!.add(Expense("1","GASOLINA","IFAM - POSTO BR","300","05/05/05","doug",""))
        _listOfExpenses.value!!.add(Expense("2","CINEMA","DR. ESTRANHO","500","05/05/05","GRAZI","AAAAAAAA TO DOIDA TO DOIDO TO DOIDA TO DOIDO TO DOIDA TO DOIDO"))

        _listOfGroups.value = ArrayList()
        _listOfGroups.value!!.add(GroupExpense("1","GASOLINA","05/05/05","300","40"))
        _listOfGroups.value!!.add(GroupExpense("1","CINEMA","05/05/05","500","70"))

    }
}