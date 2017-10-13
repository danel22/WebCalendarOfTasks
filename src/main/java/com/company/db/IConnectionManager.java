package com.company.db;

import java.sql.Connection;

/** Интерфейс для соединения с базой данных
 *
 */
public interface IConnectionManager {
   Connection getConnection();
}
