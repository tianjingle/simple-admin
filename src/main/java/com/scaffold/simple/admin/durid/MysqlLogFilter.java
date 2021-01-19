package com.scaffold.simple.admin.durid;

import com.alibaba.druid.filter.FilterEventAdapter;
import com.alibaba.druid.proxy.jdbc.CallableStatementProxy;
import com.alibaba.druid.proxy.jdbc.PreparedStatementProxy;
import com.alibaba.druid.proxy.jdbc.ResultSetProxy;
import com.alibaba.druid.proxy.jdbc.StatementProxy;
import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlStatementParser;
import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlSchemaStatVisitor;
import com.alibaba.druid.util.JdbcConstants;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: tianjl
 * @Date: 2020/12/30 7:36
 * @Eamil: 2695062879@qq.com
 */
@Component
public class MysqlLogFilter extends FilterEventAdapter {


    protected void statementExecuteUpdateBefore(StatementProxy statement, String sql) {
    }

    protected void statementExecuteUpdateAfter(StatementProxy statement, String sql, int updateCount) {
        showLog(sql);
    }

    protected void statementExecuteQueryBefore(StatementProxy statement, String sql) {
    }

    protected void statementExecuteQueryAfter(StatementProxy statement, String sql, ResultSetProxy resultSet) {
        showLog(sql);
    }

    protected void statementExecuteBefore(StatementProxy statement, String sql) {
    }

    protected void statementExecuteAfter(StatementProxy statement, String sql, boolean result) {
        showLog(sql);
    }

    public void showLog(String sql){
        String dbType = JdbcConstants.MYSQL;

        System.out.println("------------------------------------------------------------");
        //格式化输出mysql
        String result = SQLUtils.formatMySql(sql);
        System.out.println(result); // 缺省大写格式
        List<SQLStatement> stmtList = SQLUtils.parseStatements("select name from a,b where a.id=b.id order by name", dbType);
        //解析出的独立语句的个数
        System.out.println("size is:" + stmtList.size());
        for (int i = 0; i < stmtList.size(); i++) {

            SQLStatement stmt = stmtList.get(i);
            MySqlSchemaStatVisitor visitor = new MySqlSchemaStatVisitor();
            stmt.accept(visitor);

            //获取表名称
            System.out.println("条件 : " + visitor.getConditions());
            //获取操作方法名称,依赖于表名称
            System.out.println("Manipulation : " + visitor.getTables());
            //获取字段名称
            System.out.println("fields : " + visitor.getColumns());
        }
        System.out.println("------------------------------------------------------------");
        System.out.println("测试"+sql);;
    }
}
