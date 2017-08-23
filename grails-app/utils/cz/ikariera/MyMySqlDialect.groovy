package cz.ikariera

import org.hibernate.dialect.MySQL5Dialect
import org.hibernate.dialect.function.SQLFunctionTemplate
import org.hibernate.type.StandardBasicTypes

/**
 * Created by michal on 4.2.2017.
 */
public class MyMySqlDialect extends MySQL5Dialect {
    public MyMySqlDialect() {
        super();
        registerFunction("match", new SQLFunctionTemplate(StandardBasicTypes.DOUBLE,
                "match(?1,?2) against  (?3 in boolean mode)"));
    }
}