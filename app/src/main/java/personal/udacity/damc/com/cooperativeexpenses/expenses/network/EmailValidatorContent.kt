package personal.udacity.damc.com.cooperativeexpenses.expenses.network

import com.google.gson.annotations.SerializedName

data class EmailValidatorContent (

    @SerializedName("email_address" ) var emailAddress : String?  = null,
    @SerializedName("domain"        ) var domain       : String?  = null,
    @SerializedName("valid_syntax"  ) var validSyntax  : Boolean? = null,
    @SerializedName("disposable"    ) var disposable   : Boolean? = null,
    @SerializedName("webmail"       ) var webmail      : Boolean? = null,
    @SerializedName("deliverable"   ) var deliverable  : Boolean? = null,
    @SerializedName("catch_all"     ) var catchAll     : Boolean? = null,
    @SerializedName("gibberish"     ) var gibberish    : Boolean? = null,
    @SerializedName("spam"          ) var spam         : Boolean? = null

)