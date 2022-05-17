package personal.udacity.damc.com.cooperativeexpenses

import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*
import personal.udacity.damc.com.cooperativeexpenses.expenses.network.EmailValidator
import personal.udacity.damc.com.cooperativeexpenses.expenses.network.EmailValidatorAPI

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun check_if_valid_format_email_has_valid_syntax(){
        val emailFakeAndValid = "test@test.com"

        val status : EmailValidator
        runBlocking {
            status = EmailValidatorAPI.retrofitService.checkIfIsFormatAsEmail(emailFakeAndValid)
        }

        assertTrue( status is EmailValidator)
        assertTrue(status.data!!.valid_syntax == true)
    }

    @Test
    fun check_if_invalid_format_email_has_invalid_syntax(){
        val emailFakeAndInvalid = "QQ@.1"

        val status : EmailValidator
        runBlocking {
            status = EmailValidatorAPI.retrofitService.checkIfIsFormatAsEmail(emailFakeAndInvalid)
        }

        assertTrue(status.data!!.valid_syntax == false)
    }
}