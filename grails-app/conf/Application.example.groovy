dataSource {
    pooled = true
    jmxExport = true
    driverClassName = "com.mysql.jdbc.Driver"
    dialect = "org.hibernate.dialect.MySQL5InnoDBDialect"
    username = "<Your db user name here>"
    password = "<Your db password here>"
}

auth2 {
    clientId = "Your client ID here"
    clientSecret = "Your client secret here"
    scope = "https://www.googleapis.com/auth/userinfo.profile https://www.googleapis.com/auth/userinfo.email"
}

environments {

    development {
        dataSource {
            dbCreate = "create-drop"
            url = "jdbc:mysql://<Your Db address here>:<Your db Port here>/<Your db name here>"
        }
    }

    test {
        dataSource {
            dbCreate = "update"
            url = "jdbc:h2:mem:testDb;MVCC=TRUE;LOCK_TIMEOUT=10000;DB_CLOSE_ON_EXIT=FALSE"
        }
    }

    production {
        dataSource {
            dbCreate = "none"
            url = "jdbc:mysql://<Your Db address here>:<Your db Port here>/<Your db name here>"
            properties {
                jmxEnabled= true
                initialSize= 5
                maxActive= 50
                minIdle= 5
                maxIdle= 25
                maxWait= 10000
                maxAge= 600000
                timeBetweenEvictionRunsMillis= 5000
                minEvictableIdleTimeMillis= 60000
                validationQuery= "SELECT 1"
                validationQueryTimeout= 3
                validationInterval= 15000
                testOnBorrow= true
                testWhileIdle= true
                testOnReturn= false
                jdbcInterceptors= "ConnectionState"
                defaultTransactionIsolation= "2 # TRANSACTION_READ_COMMITTED"
            }
        }
    }

}
