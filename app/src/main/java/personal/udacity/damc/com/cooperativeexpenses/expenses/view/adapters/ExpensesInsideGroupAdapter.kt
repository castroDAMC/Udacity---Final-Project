package personal.udacity.damc.com.cooperativeexpenses.expenses.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import personal.udacity.damc.com.cooperativeexpenses.R
import personal.udacity.damc.com.cooperativeexpenses.databinding.ItemInsideGroupExpenseBinding
import personal.udacity.damc.com.cooperativeexpenses.expenses.model.Expense

class ExpensesInsideGroupAdapter(
    private val listOfExpenses: MutableLiveData<ArrayList<Expense>>
) : RecyclerView.Adapter<ExpensesInsideGroupAdapter.ViewHolder>() {

    class ViewHolder(private val binding: ItemInsideGroupExpenseBinding) :
        RecyclerView.ViewHolder(binding.root) {
        //
        fun onBind(expense: Expense) {
            binding.let {
                it.txtItemExpanseDate.text = expense.date
                it.txtItemExpanseTarget.text = expense.value
                it.txtItemExpanseName.text = expense.group

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
        holder.onBind(listOfExpenses.value!![position])
    }

    override fun getItemCount(): Int {
        return listOfExpenses.value?.size!!
    }

}