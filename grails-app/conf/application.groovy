import org.hibernate.dialect.PostgreSQL82Dialect

environments {
    production {
        dataSource {
            dbCreate = "create-drop"
            driverClassName = "org.postgresql.Driver"
            dialect = PostgreSQL82Dialect
//            uri = new URI(System.env.DATABASE_URL?:"postgres://localhost:5432/test")
            url = 'postgres://glotpdrqtjoafy:1c0244ec1b0674eeb5965154210d8bd55066bfb683cd9c7bc3f88907f0578a9e@ec2-54-221-255-153.compute-1.amazonaws.com:5432/dchm86dms8u22v'
            username = "glotpdrqtjoafy"
            password = "1c0244ec1b0674eeb5965154210d8bd55066bfb683cd9c7bc3f88907f0578a9e"
        }
    }
}