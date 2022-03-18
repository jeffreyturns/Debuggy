package com.jeffrey.debuggy.data.authentication

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import com.jeffrey.debuggy.util.system.toast

object AuthenticationManager {

    fun getBiometricPrompt(
        context: Context,
        onError: (() -> Unit)? = null,
        onSucceeded: (() -> Unit)? = null,
        onFailed: (() -> Unit)? = null
    ): BiometricPrompt {
        return BiometricPrompt(context as AppCompatActivity, ContextCompat.getMainExecutor(context),
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(
                    errorCode: Int,
                    errString: CharSequence
                ) {
                    super.onAuthenticationError(errorCode, errString)
                    onError?.invoke()
                    context.toast(errString.toString())
                }

                override fun onAuthenticationSucceeded(
                    result: BiometricPrompt.AuthenticationResult
                ) {
                    super.onAuthenticationSucceeded(result)
                    onSucceeded?.invoke()
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    onFailed?.invoke()
                }
            })
    }

    fun info(
        title: String,
    ): BiometricPrompt.PromptInfo {
        return BiometricPrompt.PromptInfo.Builder()
            .setTitle(title)
            .setAllowedAuthenticators(BiometricManager.Authenticators.BIOMETRIC_STRONG or BiometricManager.Authenticators.DEVICE_CREDENTIAL)
            .build()
    }
}