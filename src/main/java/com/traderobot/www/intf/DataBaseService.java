package com.traderobot.www.intf;

/**
 * Сервис для работы с БД
 */
public interface DataBaseService {

    /**
     * Метод получения свойств
     * @param key - ключ
     * @param defaultValue - значение по умолчанию
     * @return
     */
    String getPropertyByKey(String key, String defaultValue);

    /**
     * Метод получения свойств
     * @param key - ключ
     * @return
     */
    String getPropertyByKey(String key);
}
