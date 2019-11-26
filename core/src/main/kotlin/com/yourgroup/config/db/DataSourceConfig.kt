package com.yourgroup.config.db

import com.yourgroup.common.utils.StrongCryptUtils
import org.apache.commons.dbcp.BasicDataSource
import org.mybatis.spring.SqlSessionFactoryBean
import org.mybatis.spring.annotation.MapperScan
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.core.io.support.PathMatchingResourcePatternResolver
import org.springframework.jdbc.datasource.DataSourceTransactionManager
import javax.annotation.PostConstruct
import javax.sql.DataSource

/**
 * @author Gyuhyeon Lee (mark3236@gmail.com)
 * @since 2019. 11. 25.
 */
@Configuration
@MapperScan("com.yourgroup.module.**.repository")
class DataSourceConfig (
    private val env: Environment
) {
    @Value("\${db.driverClassName}") private lateinit var dbDriverClassName: String
    @Value("\${db.url}") private lateinit var dbUrl: String
    @Value("\${db.username}") private lateinit var dbUsername: String
    @Value("\${db.password}") private lateinit var dbPassword: String

    @PostConstruct
    fun decryptProps() {
        if ("alpha" in env.activeProfiles || "beta" in env.activeProfiles || "real" in env.activeProfiles) {
            val cryptor = StrongCryptUtils(
                // yeah yeah Spring Cloud config would be a lot better but it's at least better than plain text or frustrating untracked properties eh?
                System.getenv("DB_KEY"),
                System.getenv("DB_SALT")
            )
            dbDriverClassName = cryptor.decrypt(dbDriverClassName)
            dbUrl = cryptor.decrypt(dbUrl)
            dbUsername = cryptor.decrypt(dbUsername)
            dbPassword = cryptor.decrypt(dbPassword)
        }
    }

    @Bean
    fun basicDataSource(): DataSource {
        val dataSource = BasicDataSource()
        dataSource.driverClassName = dbDriverClassName
        dataSource.url = dbUrl
        dataSource.username = dbUsername
        dataSource.password = dbPassword
        dataSource.testOnBorrow = true // required to prevent connection already closed error
        dataSource.validationQuery = "SELECT 1" // mysql validation query
        return dataSource
    }

    @Bean
    fun sqlSessionFactory(): SqlSessionFactoryBean {
        val sessionFactory = SqlSessionFactoryBean()
        sessionFactory.vfs = SpringBootVFS::class.java
        sessionFactory.setDataSource(basicDataSource())
        sessionFactory.setMapperLocations(PathMatchingResourcePatternResolver().getResources("classpath:mybatis/**/*.xml"))
        sessionFactory.setTypeAliasesPackage("com.yourgroup.module") // convenience when using your own classes as types in mapper xml files
        //sessionFactory.setTypeHandlersPackage("")
        return sessionFactory
    }

    @Bean
    fun transactionManager(): DataSourceTransactionManager {
        return DataSourceTransactionManager(basicDataSource())
    }

}