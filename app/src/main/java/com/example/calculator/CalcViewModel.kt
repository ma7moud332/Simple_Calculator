package com.example.calculator

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CalcViewModel: ViewModel() {

    var state by mutableStateOf(CalcState())

    fun onAction(action: CalcAction) {
        when(action) {
            is CalcAction.Number -> enterNumber(action.number)
            is CalcAction.Delete -> delete()
            is CalcAction.Clear -> state = CalcState()
            is CalcAction.Operation -> enterOperation(action.operation)
            is CalcAction.Decimal -> enterDecimal()
            is CalcAction.Calculate -> calculate()
        }
    }

    private fun enterOperation(operation: CalcOperation) {
        if(state.num1.isNotBlank()) {
            state = state.copy(operator = operation)
        }
    }

    private fun calculate() {
        val number1 = state.num1.toDoubleOrNull()
        val number2 = state.num2.toDoubleOrNull()
        if(number1 != null && number2 != null) {
            val result = when(state.operator) {
                is CalcOperation.Add -> number1 + number2
                is CalcOperation.Sub -> number1 - number2
                is CalcOperation.Mul -> number1 * number2
                is CalcOperation.Div -> number1 / number2
                null -> return
            }
            state = state.copy(
                num1 = result.toString().take(15),
                num2 = "",
                operator = null
            )
        }
    }

    private fun delete() {
        when {
            state.num2.isNotBlank() -> state = state.copy(
                num2 = state.num2.dropLast(1)
            )
            state.operator != null -> state = state.copy(
                operator = null
            )
            state.num1.isNotBlank() -> state = state.copy(
                num1 = state.num1.dropLast(1)
            )
        }
    }

    private fun enterDecimal() {
        if(state.operator == null && !state.num1.contains(".") && state.num1.isNotBlank()) {
            state = state.copy(
                num1 = state.num1 + "."
            )
            return
        } else if(!state.num2.contains(".") && state.num2.isNotBlank()) {
            state = state.copy(
                num2 = state.num2 + "."
            )
        }
    }

    private fun enterNumber(number: Int) {
        if(state.operator == null) {
            if(state.num1.length >= MAX_NUM_LENGTH) {
                return
            }
            state = state.copy(
                num1 = state.num1 + number
            )
            return
        }
        if(state.num2.length >= MAX_NUM_LENGTH) {
            return
        }
        state = state.copy(
            num2 = state.num2 + number
        )
    }

    companion object {
        private const val MAX_NUM_LENGTH = 8
    }
}