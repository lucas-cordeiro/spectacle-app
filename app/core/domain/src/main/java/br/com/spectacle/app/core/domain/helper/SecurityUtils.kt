package br.com.spectacle.app.core.domain.helper

import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

object SecurityUtils {
    fun sha256(input: String): String? {
        try {
            val mDigest = MessageDigest.getInstance("SHA256")
            val result = mDigest.digest(input.toByteArray())
            val sb = StringBuilder()
            for (aResult in result) {
                sb.append(((aResult.toInt() and 0xff) + 0x100).toString(16).substring(1))
            }
            return sb.toString().uppercase()
        } catch (e: NoSuchAlgorithmException) {
        }

        return null
    }
}