package br.com.renannunessil.testesantander.extension

import br.com.concrete.canarinho.formatador.Formatador

fun String.brFormmatedCurrency(): String {
    return Formatador.VALOR_COM_SIMBOLO.formata(this)
}