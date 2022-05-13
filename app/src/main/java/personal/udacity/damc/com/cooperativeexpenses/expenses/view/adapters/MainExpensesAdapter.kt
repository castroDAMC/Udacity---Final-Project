package personal.udacity.damc.com.cooperativeexpenses.expenses.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import personal.udacity.damc.com.cooperativeexpenses.R
import personal.udacity.damc.com.cooperativeexpenses.databinding.ItemRecyclerviewExpenseBinding
import personal.udacity.damc.com.cooperativeexpenses.expenses.model.Expense
import personal.udacity.damc.com.cooperativeexpenses.expenses.model.GroupExpense

class MainExpensesAdapter(
    private val listOfExpenses: MutableLiveData<ArrayList<GroupExpense>>,
    private val onClickListener: (expense: GroupExpense) -> Unit
) : RecyclerView.Adapter<MainExpensesAdapter.ViewHolder>() {

    class ViewHolder(private val binding: ItemRecyclerviewExpenseBinding) :
        RecyclerView.ViewHolder(binding.root) {
        //
        fun onBind(expense: GroupExpense, onClickListener: (expense: GroupExpense) -> Unit) {
            binding.let {
                it.txtItemExpanseDate.text = expense.lastUpdate
                it.txtItemExpanseTarget.text = expense.target
                it.txtItemExpansePercentual.text = expense.percentage
                it.txtItemExpanseName.text = expense.groupName

                it.executePendingBindings()
            }

            itemView.setOnClickListener{onClickListener(expense)}
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemRecyclerviewExpenseBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.item_recyclerview_expense,
            parent,
            false
        )


        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(listOfExpenses.value!![position], onClickListener)
    }

    override fun getItemCount(): Int {
        return listOfExpenses.value?.size!!
    }

}