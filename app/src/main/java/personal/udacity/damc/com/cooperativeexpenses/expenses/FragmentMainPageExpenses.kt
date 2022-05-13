package personal.udacity.damc.com.cooperativeexpenses.expenses

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import personal.udacity.damc.com.cooperativeexpenses.R
import personal.udacity.damc.com.cooperativeexpenses.databinding.FragmentLoginNewUserBinding
import personal.udacity.damc.com.cooperativeexpenses.databinding.FragmentMainPageExpensesBinding


class FragmentMainPageExpenses : Fragment() {

    private lateinit var binding: FragmentMainPageExpensesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setupBinding(inflater, container)
        // Inflate the layout for this fragment
        return binding.root
    }

    private fun setupBinding(inflater: LayoutInflater, container: ViewGroup?) {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_main_page_expenses, container, false)

        binding.lifecycleOwner = this
    }
}