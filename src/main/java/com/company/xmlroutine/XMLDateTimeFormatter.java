package com.company.xmlroutine;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**Класс преобразования даты и времени типа LocalDateTime в строку вида "yyyy-MM-dd  HH:mm" и обратно
 * Необходим для записи в файл XML и чтения из файла XML
 */
public class XMLDateTimeFormatter extends XmlAdapter<String, LocalDateTime> {

    /**Функция преобразует строку вида "yyyy-MM-dd HH:mm" дату типа LocalDateTime в при чтении из файла XML
     * @param date - строковае значение даты вида "yyyy-MM-dd HH:mm"
     * @return дата и время типа LocalDateTime
     * @throws Exception
     */
        public LocalDateTime unmarshal(String date) throws Exception {
            return LocalDateTime.parse(date,DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        }
    /**Функция преобразует дату типа LocalDateTime в строку вида "yyyy-MM-dd  HH:mm" в при записи в файл XML
     * @param date - дата и время типа LocalDateTime
     * @return строка вида "yyyy-MM-dd  HH:mm"
     * @throws Exception
     */
        public String marshal(LocalDateTime date) throws Exception {
            return date==null?"":date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        }
}
