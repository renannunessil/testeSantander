package br.com.renannunessil.testesantander

import br.com.renannunessil.testesantander.extension.brDateFormat
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import java.text.SimpleDateFormat
import java.util.*

@RunWith(RobolectricTestRunner::class)
class CalendarExtensionTest {

    val dateString = "2019-01-01"
    val dateStringToCompare = "01/01/2019"
    val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
    val date = simpleDateFormat.parse(dateString)
    val calendar = Calendar.getInstance()

    @Test
    fun brDateFormatTest() {
        calendar.time = date

        assert(dateStringToCompare == calendar.brDateFormat())
    }

}