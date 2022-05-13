package personal.udacity.damc.com.cooperativeexpenses.expenses.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import personal.udacity.damc.com.cooperativeexpenses.R
import personal.udacity.damc.com.cooperativeexpenses.databinding.FragmentInsideGroupExpenseBinding
import personal.udacity.damc.com.cooperativeexpenses.expenses.model.Expense
import personal.udacity.damc.com.cooperativeexpenses.expenses.model.getGroupExpenseSum
import personal.udacity.damc.com.cooperativeexpenses.expenses.view.adapters.ExpensesInsideGroupAdapter
import personal.udacity.damc.com.cooperativeexpenses.expenses.viewmodel.ExpensesViewModel

class FragmentInsideGroupExpense : Fragment() {

    private lateinit var binding: FragmentInsideGroupExpenseBinding
    private val viewModel by viewModels<ExpensesViewModel>()
    private var expenseGroup: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        expenseGroup = FragmentInsideGroupExpenseArgs.fromBundle(requireArguments()).expenseGroupName

        setupBinding(inflater, container)
        setRecyclerViewConfiguration()
        // Inflate the layout for this fragment
//        return binding.root

        binding.floatingActionButton.setOnClickListener {
            getGroupExpenseSum(viewModel._listOfExpenses.value!!, expenseGroup)
            Toast.makeText(requireContext(), "CLICKED", Toast.LENGTH_SHORT).show()
        }

        binding.includedLayout.txtItemExpanseName.text = expenseGroup

        return binding.root
    }

    private fun setRecyclerViewConfiguration() {
        val filteredList = viewModel._listOfExpenses.value !!.filter {
            it.group == expenseGroup
        }
        binding.recyclerView2.adapter = ExpensesInsideGroupAdapter(
            MutableLiveData(filteredList as ArrayList<Expense>?)
        )
    }





    private fun setupBinding(inflater: LayoutInflater, container: ViewGroup?) {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_inside_group_expense, container, false)

        binding.lifecycleOwner = this
    }
}