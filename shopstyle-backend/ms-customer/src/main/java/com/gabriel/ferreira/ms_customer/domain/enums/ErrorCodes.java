package com.gabriel.ferreira.ms_customer.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCodes {
    CUSTOMER_NAO_ENCONTRADO("Customer não encontrado"),
    CUSTOMER_INVALIDO("Por favor, preencha todos os campos"),
    CUSTOMER_FIRST_NAME_INVALIDO("O First Name precisa conter 3 ou mais caracteres"),
    CUSTOMER_LAST_NAME_INVALIDO("O Last Name precisa conter 3 ou mais caracteres"),
    CUSTOMER_SEX_INVALIDO("Sexo invalido, por favor, coloque 'Masculino' ou 'Feminino'"),
    CUSTOMER_PASSWORD_INVALIDO("A senha deve contem 6 ou mais caracteres"),
    CUSTOMER_EMAIL_JA_EXISTE("Email já existe"),
    CUSTOMER_CPF_JA_EXISTE("Cpf já existe"),
    CUSTOMER_EMAIL_INVALIDO("Email inválido, por favor, coloque no formato: example@example.com"),
    CUSTOMER_CPF_INVALIDO("Cpf inválido, por favor, coloque no formato: 000.000.000-00"),
    ADDRESS_NAO_ENCONTRADA("Address não econtrada"),
    ADDRESS_INVALIDO("Por favor, preencha todos os campos"),
    ADDRESS_STATE_INVALIDO("Por favor, coloque um dos 27 estados do Brasil"),
    JWT_TOKEN_CRIAR_ERROR("Error ao criar o token JWT!");

    private final String mensagem;
}
