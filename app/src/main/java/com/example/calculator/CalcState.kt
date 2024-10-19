package com.example.calculator

data class CalcState (
    val num1: String = "",
    val num2: String = "",
    val operator: CalcOperation? = null,
)