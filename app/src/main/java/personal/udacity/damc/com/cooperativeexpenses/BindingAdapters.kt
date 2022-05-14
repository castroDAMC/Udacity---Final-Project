package personal.udacity.damc.com.cooperativeexpenses

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("displayTargetByGroup")
fun bindTargetByGroup(textView: TextView, target: String){
    val context = textView.context
    textView.text = "Target: $$target"
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
    textView.text = "$percentage%"
}