package com.eale.spi;

import com.eale.spi.service.SPIService;
import sun.misc.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @Author HLD
 * @Date 2021/8/26 0026
 **/
public class SPITest {

    public static void main(String[] args) {
        Iterator<SPIService> providers = Service.providers(SPIService.class);
        ServiceLoader<SPIService> load = ServiceLoader.load(SPIService.class);

        while(providers.hasNext()) {
            SPIService ser = providers.next();
            ser.execute();
        }
        System.out.println("--------------------------------");
        Iterator<SPIService> iterator = load.iterator();
        while(iterator.hasNext()) {
            SPIService ser = iterator.next();
            ser.execute();
        }

        String url = "jdbc:mysql:///consult?serverTimezone=UTC";
        String user = "root";
        String password = "root";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
