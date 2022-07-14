package com.example.demo.constant;

public class AppConstant {
    private AppConstant() {}


    public enum ResponseCode {

        SUCCESS("SUCCESS"),
        DATA_NOT_FOUND("DATA_NOT_FOUND"),
        UNKNOWN_ERROR("HAPPENED_ERROR");

        private final String message;

        private ResponseCode(String message) {
            this.message = message;
        }

        public String getMessage() {
            return this.message;
        }

    }
}
