package com.example.gsbatchprocessingkt.property

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("profile4debug")
data class ProfileProperty4Debug(val fileName: String)
