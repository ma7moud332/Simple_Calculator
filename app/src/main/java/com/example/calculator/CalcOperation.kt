package com.example.calculator

sealed class CalcOperation(val symbol: String) {
    object Add : CalcOperation("+")
    object Sub : CalcOperation("-")
    object Mul : CalcOperation("x")
    object Div : CalcOperation("/")
}