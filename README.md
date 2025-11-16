# 📱 AT2 Kotlin - Calculadora de Média de Alunos

Aplicativo Android desenvolvido em Kotlin utilizando Jetpack Compose para calcular a média geral de alunos baseada em três notas parciais (TP1, TP2 e TP3).

## 📋 Descrição

Este projeto é uma aplicação Android moderna que permite aos usuários inserir o nome de um aluno e suas três notas parciais para calcular automaticamente a média geral e determinar o status final do aluno (Reprovado, Aprovado ou Ótimo Aproveitamento).

## ✨ Funcionalidades

- ✅ Entrada de nome completo do aluno
- ✅ Entrada de três notas parciais (TP1, TP2, TP3)
- ✅ Validação de dados de entrada
- ✅ Cálculo automático da média geral
- ✅ Determinação do status final baseado na média:
  - **Reprovado**: Média < 6.0
  - **Aprovado**: Média entre 6.0 e 9.0
  - **Ótimo Aproveitamento**: Média > 9.0
- ✅ Interface moderna e responsiva com Material Design 3
- ✅ Feedback visual com cores diferentes para cada status
- ✅ Validação de notas (devem estar entre 0 e 10)

## 🛠️ Tecnologias Utilizadas

- **Kotlin**: Linguagem de programação principal
- **Jetpack Compose**: Framework moderno para construção de UI declarativa
- **Material Design 3**: Sistema de design para interface moderna
- **Android SDK**: 
  - Min SDK: 24 (Android 7.0)
  - Target SDK: 36
  - Compile SDK: 36
- **Gradle Kotlin DSL**: Sistema de build

### Dependências Principais

- `androidx.core:core-ktx`: Extensões Kotlin para Android
- `androidx.lifecycle:lifecycle-runtime-ktx`: Componentes de ciclo de vida
- `androidx.activity:activity-compose`: Integração de Activity com Compose
- `androidx.compose.material3`: Material Design 3 para Compose
- `androidx.compose.ui`: Ferramentas de UI do Compose

## 📁 Estrutura do Projeto

```
at2-kotlin/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/fatec/at2/
│   │   │   │   ├── model/
│   │   │   │   │   └── Aluno.kt          # Modelo de dados do aluno
│   │   │   │   ├── ui/
│   │   │   │   │   ├── theme/            # Configurações de tema
│   │   │   │   │   │   ├── Color.kt
│   │   │   │   │   │   ├── Theme.kt
│   │   │   │   │   │   └── Type.kt
│   │   │   │   │   └── AlunoNotaScreen.kt # Tela principal do app
│   │   │   │   └── MainActivity.kt        # Activity principal
│   │   │   ├── res/                       # Recursos do Android
│   │   │   └── AndroidManifest.xml
│   │   ├── test/                          # Testes unitários
│   │   └── androidTest/                   # Testes instrumentados
│   └── build.gradle.kts
├── build.gradle.kts
├── settings.gradle.kts
└── README.md
```

## 🔧 Pré-requisitos

Antes de começar, certifique-se de ter instalado:

- **JDK 11** ou superior
- **Android Studio** (versão mais recente recomendada)
- **Android SDK** com API Level 24 ou superior
- **Gradle** 8.0 ou superior (geralmente incluído no Android Studio)

## 📥 Instalação

1. Clone o repositório:
```bash
git clone https://github.com/cksalmeida/at2-kotlin.git
cd at2-kotlin
```

2. Abra o projeto no Android Studio:
   - File → Open → Selecione a pasta do projeto

3. Aguarde o Android Studio sincronizar as dependências do Gradle

4. Configure um emulador Android ou conecte um dispositivo físico

## 🚀 Como Executar

### Pelo Android Studio

1. Abra o projeto no Android Studio
2. Aguarde a sincronização do Gradle
3. Clique no botão "Run" (▶️) ou pressione `Shift + F10`
4. Selecione o dispositivo/emulador de destino

### Por Linha de Comando

```bash
# Compilar o projeto
./gradlew build

# Instalar em um dispositivo conectado
./gradlew installDebug

# Executar testes
./gradlew test
```

## 💡 Como Usar

1. **Iniciar o aplicativo**: Abra o app no seu dispositivo
2. **Inserir dados**:
   - Digite o nome completo do aluno no campo "Nome Completo do Aluno"
   - Digite as três notas parciais nos campos TP1, TP2 e TP3
3. **Calcular**: Clique no botão "CALCULAR MÉDIA"
4. **Visualizar resultado**: O app exibirá:
   - Nome do aluno
   - Média geral calculada
   - Status final com código de cores:
     - 🔴 Vermelho: Reprovado (média < 6.0)
     - 🟡 Amarelo: Aprovado (média 6.0 - 9.0)
     - 🟢 Verde: Ótimo Aproveitamento (média > 9.0)

### Validações Implementadas

- O nome do aluno não pode estar vazio
- Todas as três notas devem ser preenchidas
- As notas devem ser valores numéricos válidos
- As notas devem estar no intervalo de 0 a 10

## 🔍 Exemplos de Código

### Modelo de Dados (Aluno)

```kotlin
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
```

### Interface Composable

```kotlin
@Composable
fun AlunoScreen() {
    var nome by remember { mutableStateOf("") }
    var tp1 by remember { mutableStateOf("") }
    var tp2 by remember { mutableStateOf("") }
    var tp3 by remember { mutableStateOf("") }
    // ... implementação da UI
}
```

## 🧪 Testes

O projeto inclui estrutura para testes unitários e instrumentados:

```bash
# Executar testes unitários
./gradlew test

# Executar testes instrumentados (requer dispositivo/emulador)
./gradlew connectedAndroidTest
```

## 🎨 Características da Interface

- **Material Design 3**: Interface moderna seguindo as diretrizes do Material Design
- **Tema Personalizado**: Cores personalizadas para melhor experiência visual
- **Responsivo**: Adaptável a diferentes tamanhos de tela
- **Feedback Visual**: Cores indicativas para diferentes status
- **Validação em Tempo Real**: Feedback imediato sobre erros de entrada

## 🤝 Contribuindo

Contribuições são bem-vindas! Para contribuir:

1. Faça um fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/MinhaFeature`)
3. Commit suas mudanças (`git commit -m 'Adiciona MinhaFeature'`)
4. Push para a branch (`git push origin feature/MinhaFeature`)
5. Abra um Pull Request

## 📄 Licença

Este projeto é de código aberto e está disponível para fins educacionais.

## 👨‍💻 Autor

**cksalmeida**

- GitHub: [@cksalmeida](https://github.com/cksalmeida)

## 📞 Suporte

Se você encontrar algum problema ou tiver sugestões, por favor:

1. Verifique se já existe uma issue relacionada
2. Crie uma nova issue descrevendo o problema ou sugestão
3. Inclua detalhes relevantes como versão do Android, dispositivo, etc.

## 🔄 Changelog

### Versão 1.0
- ✅ Implementação inicial do aplicativo
- ✅ Tela de cálculo de média de alunos
- ✅ Validação de entrada de dados
- ✅ Interface com Material Design 3
- ✅ Cálculo automático de média e status

---

**Desenvolvido com ❤️ usando Kotlin e Jetpack Compose**
