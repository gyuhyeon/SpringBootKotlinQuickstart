package com.yourgroup.common.utils

import com.yourgroup.TestWithContexts
import org.junit.Assert
import org.junit.Test
import org.slf4j.LoggerFactory

/**
 * @author Gyuhyeon Lee (mark3236@gmail.com)
 * @since 2019. 11. 25.
 */
class CryptUtilsTest: TestWithContexts() {
    private val logger = LoggerFactory.getLogger(this.javaClass)
    @Test
    fun encryptSample1() {
        val weakCryptUtils = WeakCryptUtils()
        val plain = "test sentence 1234!@#가나다라"
        val encrypted = weakCryptUtils.encrypt(plain, "AD4BF53393927A34", "8D4953A64375A418")
        val decrypted = weakCryptUtils.decrypt(encrypted, "AD4BF53393927A34", "8D4953A64375A418")
        logger.info(encrypted)
        Assert.assertEquals(plain, decrypted)
    }

    @Test
    fun encryptSample2() {
        val strongCryptUtils = StrongCryptUtils("6B3BA14E65E24EF17B32988CC6C87ECFE841A5373CBBD7B5", "D91A82302D4867FF")
        val plain = "test sentence 1234!@#가나다라"
        val encrypted = strongCryptUtils.encrypt(plain)
        val decrypted = strongCryptUtils.decrypt(encrypted)
        logger.info(encrypted)
        logger.info(decrypted)
        Assert.assertEquals(plain, decrypted)
    }

    @Test
    fun createRandomKey() {

    }

    @Test
    fun encryptProp() {
        // sample key & salt. Make your own for AES-256 CBC/PKCS5Padding
        val key = "FA5EAF5CB5EB0497681E0A0EF873610B81A3003C11CE2286154897AF38B09F04"
        val salt = "BC2170E72F19C0B4"
        val strongCryptUtils = StrongCryptUtils(key, salt)

        val driverClassName = "com.mysql.cj.jdbc.Driver"
        val url = "jdbc:mysql://yourdburl:yourport"
        val username = "yourdbuser"
        val password = "yourpassword"
        logger.info(strongCryptUtils.encrypt(driverClassName))
        logger.info(strongCryptUtils.encrypt(url))
        logger.info(strongCryptUtils.encrypt(username))
        logger.info(strongCryptUtils.encrypt(password))
    }

}