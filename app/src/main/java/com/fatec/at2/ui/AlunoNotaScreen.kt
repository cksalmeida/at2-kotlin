package com.fatec.at2.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fatec.at2.model.Aluno

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlunoScreen() {

    var nome by remember { mutableStateOf("") }
    var tp1 by remember { mutableStateOf("") }
    var tp2 by remember { mutableStateOf("") }
    var tp3 by remember { mutableStateOf("") }
    var alunoCalculado by remember { mutableStateOf<Aluno?>(null) }
    var erroMensagem by remember { mutableStateOf<String?>(null) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Média Geral") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.tertiary,
                    titleContentColor = MaterialTheme.colorScheme.onTertiary
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp, vertical = 16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {

            OutlinedTextField(
                value = nome,
                onValueChange = { nome = it },
                label = { Text("Nome Completo do Aluno") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(12.dp)
            )

            Text(
                text = "Notas Parciais",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.secondary
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                val fieldShape = RoundedCornerShape(12.dp)

                OutlinedTextField(
                    value = tp1,
                    onValueChange = { tp1 = it },
                    label = { Text("TP1") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    modifier = Modifier.weight(1f),
                    shape = fieldShape
                )
                OutlinedTextField(
                    value = tp2,
                    onValueChange = { tp2 = it },
                    label = { Text("TP2") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    modifier = Modifier.weight(1f),
                    shape = fieldShape
                )
                OutlinedTextField(
                    value = tp3,
                    onValueChange = { tp3 = it },
                    label = { Text("TP3") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    modifier = Modifier.weight(1f),
                    shape = fieldShape
                )
            }

            if (erroMensagem != null) {
                Text(
                    text = erroMensagem!!,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )
            }

            Button(
                onClick = {
                    val n1 = tp1.toDoubleOrNull()
                    val n2 = tp2.toDoubleOrNull()
                    val n3 = tp3.toDoubleOrNull()

                    if (nome.isBlank()) {
                        erroMensagem = "Por favor, insira o nome."
                        alunoCalculado = null
                    } else if (n1 == null || n2 == null || n3 == null) {
                        erroMensagem = "Por favor, insira todas as três notas."
                        alunoCalculado = null
                    } else if (n1 !in 0.0..10.0 || n2 !in 0.0..10.0 || n3 !in 0.0..10.0) {
                        erroMensagem = "As notas devem estar entre 0 e 10."
                        alunoCalculado = null
                    } else {
                        erroMensagem = null
                        val notas = mutableListOf(n1, n2, n3)
                        alunoCalculado = Aluno(nome = nome, notas = notas)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(12.dp),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp, pressedElevation = 4.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF725370),
                    contentColor = Color.White
            )

            ) {
                Text("CALCULAR MÉDIA", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }

            if (alunoCalculado != null) {
                Spacer(modifier = Modifier.height(16.dp))
                ResultadoCard(aluno = alunoCalculado!!)
            }
        }
    }
}

@Composable
fun ResultadoCard(aluno: Aluno) {
    val media = aluno.calcularMediaGeral()
    val status = aluno.obterStatus()
    val corStatus = when (status) {
        "Reprovado" -> Color(0xFFD32F2F)
        "Aprovado" -> Color(0xFFCDDC39)
        else -> Color(0xFF388E3C)
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Resultado Final",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.primary
            )
            Divider()
            Text(
                text = aluno.nome,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Média Geral:",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = String.format("%.2f", media),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = corStatus
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Status Final:",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = status,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = corStatus
                )
            }
        }
    }
}