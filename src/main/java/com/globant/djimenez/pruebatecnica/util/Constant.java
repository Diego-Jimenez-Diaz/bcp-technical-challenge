package com.globant.djimenez.pruebatecnica.util;

public class Constant {

    private Constant() {
    }

    public static final String TRANSACTION_ID_NOT_FOUND_EXCEPTION = "Transaction id not found";
    public static final String CUSTOMER_ID_NOT_FOUND_EXCEPTION = "Customer id not found";
    public static final String ACCOUNT_ID_NOT_FOUND_EXCEPTION = "Account id not found";
    public static final String INSUFFICIENT_BALANCE_EXCEPTION = "The transaction cannot be completed because the balance is insufficient.";
    public static final String ACCOUNT_NUMBER_EXISTING_EXCEPTION = "There is already a record with this account number";
    public static final String CUSTOMER_ALREADY_DELETED = "The customer has already been deleted";
    public static final String ACCOUNT_ALREADY_DELETED = "The account has already been deleted";
    public static final String TRANSACTION_ALREADY_DELETED = "Cannot be performed because the operation has already been deleted.";

    public enum MovimientoFields {
        INITIAL_BALANCE("saldoInicial"),
        VALUE("valor"),
        ACCOUNT_ID("cuentaId");

        private String fieldName;

        MovimientoFields(final String fieldName){
            this.fieldName = fieldName;
        }

        public String getFieldName(){
            return this.fieldName;
        }
    }
}
