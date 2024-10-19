package com.example.calculator

sealed class CalcAction {
    data class Number(val number: Int) : CalcAction()
    object Clear : CalcAction()
    object Delete : CalcAction()
    object Decimal : CalcAction()
    object Calculate : CalcAction()
    data class Operation(val operation: CalcOperation) : CalcAction()

}