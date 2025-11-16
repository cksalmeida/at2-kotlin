package com.fatec.at2.model

data class Aluno(
    val nome: String,
    val notas: MutableList<Double>
) {

    fun calcularMediaGeral(): Double {
        return if (notas.isNotEmpty()) {
            notas.sum() / notas.size
        } else {
            0.0
        }
    }

    fun obterStatus(): String {
        val media = calcularMediaGeral()

        return when {
            media < 6.0 -> "Reprovado"
            media in 6.0..9.0 -> "Aprovado"
            else -> "Ótimo Aproveitamento"
        }
    }
}