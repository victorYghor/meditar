package com.victoryghor.meditar.util

fun String.removeCurlyBrackets() = this.replace(Regex("[\\{\\}]"), "")

