package wasik.infrastructure.model.properties

import jakarta.validation.constraints.NotBlank
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component
import org.springframework.validation.annotation.Validated

@Component
@ConfigurationProperties(prefix = "spring.datasource")
@Validated
class DataSourceProperties {
    @NotBlank
    lateinit var url: String
    @NotBlank
    lateinit var username: String
    @NotBlank
    lateinit var password: String
}