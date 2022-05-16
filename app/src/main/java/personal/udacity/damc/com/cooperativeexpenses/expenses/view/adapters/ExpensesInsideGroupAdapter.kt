package personal.udacity.damc.com.cooperativeexpenses.expenses.view.adapters

import android.app.AlertDialog
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
    private val listOfExpenses: MutableLiveData<List<Expense>>,
    private val function: (expense: Expense) -> Unit
) : RecyclerView.Adapter<ExpensesInsideGroupAdapter.ViewHolder>() {

    class ViewHolder(private val binding: ItemInsideGroupExpenseBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(expense: Expense, target: String, position: Int, function: (expense: Expense) -> Unit) {

            binding.let {
                it.expense = expense
                it.groupTarget = target
                it.executePendingBindings()

                it.cardVIew.setCardBackgroundColor(when{
                    position%2 == 0 -> {itemView.context.getColor(R.color.gray_1)}
                    else -> {itemView.context.getColor(R.color.gray_2)}
                })
            }

            itemView.setOnLongClickListener {
                AlertDialog.Builder(it.context)
                    .setTitle("Delete")
                    .setMessage("Do you really wanna delete " + expense.name +  " register?")
                    .setPositiveButton(
                        "Yes, I AM"
                    ) { _, _ ->
                        function(expense)
                    }
                    .setNegativeButton("MY MISTAKE") { _, _ ->
                    }
                    .create()
                    .show()

                true
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
        holder.onBind(itemBind, getGroupExpenseSum(listOfExpenses.value!! as ArrayList<Expense>,itemBind.group), position, function)
    }

    override fun getItemCount(): Int {
        return listOfExpenses.value?.size!!
    }

    fun insertAllExpenses(it: List<Expense>) {
        listOfExpenses.value = it
        notifyDataSetChanged()
    }

}