package br.com.renannunessil.testesantander.extension

import java.text.SimpleDateFormat
import java.util.*

fun Calendar.brDateFormat() : String {
    val brFormat = "dd/MM/yyyy"
    val pattern = SimpleDateFormat(brFormat)
    return pattern.format(this.time)
}