package com.company.xmlroutine;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**Класс преобразования даты типа LocalDate в строку вида "yyyy-MM-dd" и обратно
 * Необходим для записи в файл XML и чтения из файла XML
 */
public class XMLDateFormatter extends XmlAdapter<String, LocalDate> {
    /**Функция преобразует строку вида "yyyy-MM-dd" дату типа LocalDate в при чтении из файла XML
     * @param date - строковае значение даты
     * @return дату типа LocalDate
     * @throws Exception
     */
       public LocalDate unmarshal(String date) throws Exception {
           LocalDate newDate=LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
           System.out.println(newDate);
            return newDate;
        }

    /**Функция преобразует дату типа LocalDate в строку вида "yyyy-MM-dd" в при записи в файл XML
     * @param date - дата типа LocalDate
     * @return строка вида "yyyy-MM-dd"
     * @throws Exception
     */
        public String marshal(LocalDate date) throws Exception {
            return date==null?"":date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
    }
