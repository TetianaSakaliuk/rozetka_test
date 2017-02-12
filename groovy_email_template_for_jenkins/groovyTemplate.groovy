package templ
/**
 * Created by Tetiana on 11.02.2017.
 */


import groovy.sql.Sql


def main(String[] args) {

    def sql = Sql.newInstance("jdbc:mysql://localhost:3306/test", "root",
            "humbert", "com.mysql.jdbc.Driver")
    sql.query('SELECT table_name, create_time FROM information_schema.tables ' +
            'WHERE table_schema="test" ' +
            'AND create_time BETWEEN DATE_SUB(NOW(),INTERVAL 1 DAY) AND NOW()') { tableSet ->


        def builder = new groovy.xml.MarkupBuilder()
        builder.html {
            head {
                title "Test Report"
            }
            body {
                while (tableSet.next()) {
                    def tablename = tableSet.getString(1)
                    def createTime = tableSet.getString(2)
                    table (style:'border-collapse: collapse;text-align:left;background-color:#f0f0f0;width: 100%;') {
                        h3 tablename + " was created at " + createTime
                        sql.query('SELECT * FROM ' + tablename) { resultSet ->

                            while (resultSet.next()) {
                                tr  {
                                    td(resultSet.getString(1))
                                    td(resultSet.getString(2))
                                    td(resultSet.getString(3))
                                }
                            }
                        }
                    }
                }
            }
        }


    }
}


main(args)