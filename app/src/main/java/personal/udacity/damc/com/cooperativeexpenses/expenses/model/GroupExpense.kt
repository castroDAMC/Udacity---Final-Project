package personal.udacity.damc.com.cooperativeexpenses.expenses.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GroupExpense(
    val id: String,
    val groupName: String,
    val lastUpdate: String,
    val target: String,
    val percentage: String,
) : Parcelable