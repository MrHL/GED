package org.gde.interfaces;

import java.sql.Connection;
import java.sql.Statement;

/**
 * the interface is use to connect database
 * @author JavaLuSir
 *
 */
public interface IDBConnection {

	Connection getConnInstance(String ip,String user,String pass);
	
	Connection getConnInstance();
}
