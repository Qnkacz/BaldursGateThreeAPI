package wasik.infrastructure.logic.config

import jakarta.annotation.PostConstruct
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import wasik.infrastructure.model.properties.DataSourceProperties
import wasik.infrastructure.model.table.ActionTable
import wasik.infrastructure.model.table.DamageTable
import wasik.infrastructure.model.table.PropertyTable
import wasik.infrastructure.model.table.item.weapon.WeaponActionTable
import wasik.infrastructure.model.table.item.weapon.WeaponDamageTable
import wasik.infrastructure.model.table.item.weapon.WeaponPropertyTable
import wasik.infrastructure.model.table.item.weapon.WeaponTable
import java.util.*
import javax.sql.DataSource

@Configuration
open class DatabaseConfig(
    private val datasourceProperties: DataSourceProperties,
    private val datasource: DataSource
) {

    @Bean
    open fun initDatabase() {
        Database.connect(
            url = datasourceProperties.url,
            user = datasourceProperties.username,
            password = datasourceProperties.password
        )
    }



    @PostConstruct
    fun checkAndCreateTables() {
        createTableIfNotExists(WeaponTable)
        createTableIfNotExists(ActionTable)
        createTableIfNotExists(DamageTable)
        createTableIfNotExists(PropertyTable)
        createWeaponCrossTables()
    }

    private fun createWeaponCrossTables() {
        createTableIfNotExists(WeaponActionTable)
        createTableIfNotExists(WeaponDamageTable)
        createTableIfNotExists(WeaponPropertyTable)
    }

    private fun tableExists(tableName: String): Boolean {
        return transaction {
            val connection = datasource.connection
            val metadata = connection.metaData
            val resultSet = metadata.getTables(null, null, tableName.uppercase(Locale.getDefault()), null)
            resultSet.next()
        }
    }

    private fun createTableIfNotExists(table: Table) {
        if (!tableExists(table.tableName)) {
            transaction {
                SchemaUtils.create(table)
            }
        }
    }
}