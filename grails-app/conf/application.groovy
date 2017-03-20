environments {
    development {
        dataSource {
            username = 'lakerpollingadmin'
            password = 'password'
            url = "jdbc:postgresql://localhost:5432/lakerpollingdb"
        }
    }
}


/*
googleauth {
    clientId = '896100416043-v0cvdf52tteag7ha8939fog24sr7bm2g.apps.googleusercontent.com'
    issuer = 'accounts.google.com'
}

dataSource {
    pooled = true
    jmxExport = true
    driverClassName = 'org.postgresql.Driver'
//    driverClassName = 'com.mysql.jdbc.Driver'
//    dialect = 'org.hibernate.dialect.MySQL5InnoDBDialect'

    dialect = "org.hibernate.dialect.PostgreSQLDialect"
    username = 'lakerpollingadmin'
    password = 'password'
//    username = 'clickerdbman'
//    password = 'password'
}

environments {

    development {
        dataSource {
            dbCreate = "create-drop"
            url = "jdbc:postgresql://localhost:5432/lakerpollingdb"
//            url = "jdbc:mysql://pi.cs.oswego.edu:3306/laker_polling"
        }
    }

    test {
        dataSource {
            dbCreate = "create-drop"
            url = "jdbc:postgresql://laker-polling.duckdns.org:5432/laker_polling"
        }
    }

    production {
        dataSource {
            dbCreate = "create-drop"
            url = "jdbc:postgresql://laker-polling.duckdns.org:5432/laker_polling"
            properties {
                jmxEnabled = true
                initialSize = 5
                maxActive = 50
                minIdle = 5
                maxIdle = 25
                maxWait = 10000
                maxAge = 600000
                timeBetweenEvictionRunsMillis = 5000
                minEvictableIdleTimeMillis = 60000
                validationQuery = 'SELECT 1'
                validationQueryTimeout = 3
                validationInterval = 15000
                testOnBorrow = true
                testWhileIdle = true
                testOnReturn = false
                jdbcInterceptors = 'ConnectionState'
                defaultTransactionIsolation = 2 // TRANSACTION_READ_COMMITTED
            }
        }
    }

}
*/
