package br.com.moscatech.dumbpassword.domain.model

data class PasswordGroup(
    val title: String = "",
    val passwords: List<PasswordPlatform> = emptyList(),
    val needPassword: Boolean = true,
)

data class PasswordPlatform(
    val name: String = "",
    var passwordDetails: List<PasswordDetails> = emptyList(),
)

data class PasswordDetails(
    val title: String = "",
    val reference: String = "",
    val data: String = "",
    val tip: String = "",
)
