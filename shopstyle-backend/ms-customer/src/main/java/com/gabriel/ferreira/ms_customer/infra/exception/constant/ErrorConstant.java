package com.gabriel.ferreira.ms_customer.infra.exception.constant;

import lombok.AccessLevel;
import lombok.Getter;

@Getter(AccessLevel.PRIVATE)
public class ErrorConstant {
   public static final String CUSTOMER_NAO_ENCONTRADO = "[Ms-customer] Customer não encontrado";
    public static final String CUSTOMER_INVALIDO = "[Ms-customer]  Por favor, preencha todos os campos";
    public static final String  CUSTOMER_FIRST_NAME_INVALIDO = "[Ms-customer]  O First Name precisa conter 3 ou mais caracteres";
    public static final String  CUSTOMER_LAST_NAME_INVALIDO = "[Ms-customer] O Last Name precisa conter 3 ou mais caracteres";
    public static final String  CUSTOMER_SEX_INVALIDO = "[Ms-customer] Sexo invalido; por favor, coloque 'Masculino' ou 'Feminino'";
    public static final String CUSTOMER_PASSWORD_INVALIDO = "[Ms-customer] A senha deve contem 6 ou mais caracteres";
    public static final String CUSTOMER_EMAIL_JA_EXISTE = "[Ms-customer] Email já existe";
    public static final String  CUSTOMER_CPF_JA_EXISTE = "[Ms-customer] Cpf já existe";
    public static final String  CUSTOMER_EMAIL_INVALIDO = "[Ms-customer] Email inválido, por favor; coloque no formato: example@example.com";
    public static final String  CUSTOMER_CPF_INVALIDO = "[Ms-customer] Cpf inválido, por favor; coloque no formato: 000.000.000[Ms-customer] 00";
    public static final String  ADDRESS_NAO_ENCONTRADA = "[Ms-customer] Address não econtrada";
    public static final String  ADDRESS_INVALIDO = "[Ms-customer] Por favor, preencha todos os campos";
    public static final String  ADDRESS_STATE_INVALIDO = "[Ms-customer] Por favor, coloque um dos 27 estados do Brasil";
    public static final String  JWT_TOKEN_CRIAR_ERROR = "[Ms-customer] Error ao criar o token JWT!";
}
