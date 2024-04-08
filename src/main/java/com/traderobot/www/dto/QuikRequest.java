package com.traderobot.www.dto;


import lombok.Getter;

@Getter
public class QuikRequest {

    private QuikRequest() {
    }

    /**
     * Тип опциона
     */
    private String typeOption;

    /**
     * Тип базового актива
     */
    private String baseActive;

    /**
     * Счет
     */
    private String account;

    /**
     * Код опциона
     */
    private String optionCode;

    /**
     * Количество контрактов
     */
    private String contracts;

    /**
     * Тип сделки
     */
    private String typeOrder;

    /**
     * Минимальный отступ от цены
     */
    private String minPrice;

    /**
     * Максимальный отступ от цены
     */
    private String maxPrice;

    /**
     * При продаже не опускаться ниже, чем
     */
    private String priceStop;

    /*
     * Название команды
     */
    private String command;

    public static QuikRequestBuilder newBuilder() {
        return new QuikRequest().new QuikRequestBuilder();
    }


    public class QuikRequestBuilder {
        private QuikRequestBuilder() {
        }


        public QuikRequestBuilder addTypeOption(String typeOption) {
            QuikRequest.this.typeOption = typeOption;
            return this;
        }

        public QuikRequestBuilder addBaseActive(String baseActive) {
            QuikRequest.this.baseActive = baseActive;
            return this;
        }

        public QuikRequestBuilder addAccount(String account) {
            QuikRequest.this.account = account;
            return this;
        }

        public QuikRequestBuilder addOptionCode(String optionCode) {
            QuikRequest.this.optionCode = optionCode;
            return this;
        }

        public QuikRequestBuilder addContracts(String contracts) {
            QuikRequest.this.contracts = contracts;
            return this;
        }

        public QuikRequestBuilder addTypeOrder(String typeOrder) {
            QuikRequest.this.typeOrder = typeOrder;
            return this;
        }

        public QuikRequestBuilder addMinPrice(String minPrice) {
            QuikRequest.this.minPrice = minPrice;
            return this;
        }

        public QuikRequestBuilder addMaxPrice(String maxPrice) {
            QuikRequest.this.maxPrice = maxPrice;
            return this;
        }

        public QuikRequestBuilder addCommand(String command) {
            QuikRequest.this.command = command;
            return this;
        }


        public QuikRequestBuilder addPriceStop(String priceStop) {
            QuikRequest.this.priceStop = priceStop;
            return this;
        }

        public QuikRequest build() {
            return QuikRequest.this;
        }

    }


}
