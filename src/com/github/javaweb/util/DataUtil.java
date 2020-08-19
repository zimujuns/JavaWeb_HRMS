package com.github.javaweb.util;

import com.github.javaweb.data.Ddept;
import com.github.javaweb.data.Demployee;
import com.github.javaweb.data.Djob;

import java.sql.SQLException;

public class DataUtil {

    public static void init() throws SQLException, ClassNotFoundException {
        Ddept.init();
        Djob.init();
        Demployee.init();
    }

}
