package personal.udacity.damc.com.cooperativeexpenses.expenses.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import personal.udacity.damc.com.cooperativeexpenses.R
import personal.udacity.damc.com.cooperativeexpenses.databinding.ItemInsideGroupExpenseBinding
import personal.udacity.damc.com.cooperativeexpenses.expenses.model.Expense
import personal.udacity.damc.com.cooperativeexpenses.expenses.model.getGroupExpenseSum

class ExpensesInsideGroupAdapter(
    private val listOfExpenses: MutableLiveData<ArrayList<Expense>>
) : RecyclerView.Adapter<ExpensesInsideGroupAdapter.ViewHolder>() {

    class ViewHolder(private val binding: ItemInsideGroupExpenseBinding) :
        RecyclerView.ViewHolder(binding.root) {
        //
        fun onBind(expense: Expense, target: String) {
            binding.let {
                it.expense = expense
                it.groupTarget = target
                it.executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemInsideGroupExpenseBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.item_inside_group_expense,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemBind = listOfExpenses.value!![position]
        holder.onBind(itemBind, getGroupExpenseSum(listOfExpenses.value!!,itemBind.group))
    }

    override fun getItemCount(): Int {
        return listOfExpenses.value?.size!!
    }

}