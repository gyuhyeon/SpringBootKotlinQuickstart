package com.yourgroup.common.utils

import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

/**
 * @author Gyuhyeon Lee (mark3236@gmail.com)
 * @since 2019. 11. 25.
 */
class WeakCryptUtils {
    private val AES = "AES"
    private val algorithm = "$AES/CBC/PKCS5Padding"
    private val charset = Charsets.UTF_8
    private val ASCII = Charsets.US_ASCII

    fun decrypt(text: String, key: String, ivKey: String): String {
        val results = Cipher.getInstance(algorithm).let {
            val keySpec = SecretKeySpec(encode(key), AES)
            val ivSpec = IvParameterSpec(encode(ivKey))
            it.init(Cipher.DECRYPT_MODE, keySpec, ivSpec)
            it.doFinal(Base64.getDecoder().decode(text))
        }
        return String(results, charset)
    }

    fun encrypt(text: String, key: String, ivKey: String): String {
        val results = Cipher.getInstance(algorithm).let {
            val keySpec = SecretKeySpec(encode(key), AES)
            val ivSpec = IvParameterSpec(encode(ivKey))
            it.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec)
            it.doFinal(text.toByteArray(charset))
        }
        return Base64.getEncoder().encodeToString(results)
    }

    // string -> bytes
    fun encode(str: String): ByteArray? {
        return str.toByteArray(ASCII)
    }

    // bytes -> string
    fun decode(bytes: ByteArray): String? {
        return String(bytes, ASCII)
    }
}