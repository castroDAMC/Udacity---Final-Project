package personal.udacity.damc.com.cooperativeexpenses

import android.widget.TextView
import androidx.databinding.BindingAdapter
import personal.udacity.damc.com.cooperativeexpenses.expenses.model.getExpenseUsedByPercentage

@BindingAdapter("displayTargetByGroup")
fun bindTargetByGroup(textView: TextView, target: String){
    textView.text = "Target: $$target"
}

@BindingAdapter("displayValueByExpense")
fun bindValueByExpense(textView: TextView, target: String){
    val context = textView.context
    textView.text = "Used: $$target"
}

@BindingAdapter("valueOfExpense", "valueOfTarget")
fun displayPercentageExpense(textView: TextView, valueOfExpense: String, valueOfTarget: String){
    val percentage = getExpenseUsedByPercentage(valueOfExpense, valueOfTarget)
    textView.text = "$percentage% from Target"
}

@BindingAdapter("displayPercentage")
fun displayPercentage(textView: TextView, percentage: String){
    val color: Int = if(percentage.toFloat() <= 50.0f){
        R.color.green
    } else if (percentage.toFloat() < 75.0f){
        R.color.orange
    } else {
        R.color.red
    }
    textView.setTextColor(textView.context.getColor(color))
    textView.text = "Already used: $percentage%"
}
@BindingAdapter("displayUserName")
fun displayUserName(textView: TextView, name: String){
    textView.text = "Added by $name"
}