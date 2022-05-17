package personal.udacity.damc.com.cooperativeexpenses.expenses.network

import com.google.gson.annotations.SerializedName


data class EmailValidator(
    @SerializedName("status") var status: String? = null,
    @SerializedName("data") var data: EmailValidatorContent? = EmailValidatorContent()
)

data class EmailValidatorContent(
    @SerializedName("valid_syntax") var valid_syntax: Boolean? = null,
)