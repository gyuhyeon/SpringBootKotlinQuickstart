package com.yourgroup.common.utils

import org.springframework.security.crypto.encrypt.Encryptors

/**
 * @author Gyuhyeon Lee (mark3236@gmail.com)
 * @since 2019. 11. 25.
 */
class StrongCryptUtils (
    password: String = "defaultPassword",
    salt: String = "defaultSalt"
) {
    private val encryptor = Encryptors.queryableText(password, salt)

    fun decrypt(text: String): String {
        return encryptor.decrypt(text)
    }

    fun encrypt(text: String): String {
        return encryptor.encrypt(text)
    }
}