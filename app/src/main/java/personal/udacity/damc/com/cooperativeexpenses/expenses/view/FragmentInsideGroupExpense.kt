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
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import personal.udacity.damc.com.cooperativeexpenses.R
import personal.udacity.damc.com.cooperativeexpenses.databinding.FragmentInsideGroupExpenseBinding
import personal.udacity.damc.com.cooperativeexpenses.expenses.model.Expense
import personal.udacity.damc.com.cooperativeexpenses.expenses.model.GroupExpense
import personal.udacity.damc.com.cooperativeexpenses.expenses.model.getGroupExpenseSum
import personal.udacity.damc.com.cooperativeexpenses.expenses.view.adapters.ExpensesInsideGroupAdapter
import personal.udacity.damc.com.cooperativeexpenses.expenses.viewmodel.ExpensesViewModel

class FragmentInsideGroupExpense : Fragment() {

    private val scopeOfIO = CoroutineScope(Dispatchers.IO)

    private lateinit var binding: FragmentInsideGroupExpenseBinding
    private val viewModel by lazy {
        ViewModelProvider(this)[ExpensesViewModel::class.java]
    }
    private lateinit var expenseGroup: GroupExpense

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        expenseGroup = FragmentInsideGroupExpenseArgs.fromBundle(requireArguments()).expenseGroup

        setupBinding(inflater, container)
        setRecyclerViewConfiguration()
        updateAllListenersBasedOnViewModel()
        // Inflate the layout for this fragment
//        return binding.root

        binding.floatingActionButton.setOnClickListener {
//            getGroupExpenseSum(viewModel._listOfExpenses.value!!, expenseGroup)
            //Toast.makeText(requireContext(), "CLICKED", Toast.LENGTH_SHORT).show()
            viewModel.getAll()
            viewModel.addExpense(viewModel._listOfExpenses.value!![11])

        }

        binding.includedLayout.txtItemExpanseName.text = expenseGroup.groupName
        binding.includedLayout.group = expenseGroup

        return binding.root
    }

    private fun setRecyclerViewConfiguration() {
        binding.recyclerView2.adapter = ExpensesInsideGroupAdapter(
            MutableLiveData(emptyList())
        )
    }

    private fun updateAllListenersBasedOnViewModel(){
        viewModel.listOfExpenses.observe(viewLifecycleOwner){
            (binding.recyclerView2.adapter as ExpensesInsideGroupAdapter).insertAllExpenses(it.filter {
                it.group == expenseGroup.groupName
            })
        }
    }

    private fun setupBinding(inflater: LayoutInflater, container: ViewGroup?) {
        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_inside_group_expense,
                container,
                false
            )

        binding.lifecycleOwner = this
    }
}