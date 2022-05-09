package br.com.spectacle.app.core.domain.error

class InvalidFieldException(
    private val field: String
) : Exception() {
    override val message: String = "Campo $field inválido, verifique e tente novamente"
}