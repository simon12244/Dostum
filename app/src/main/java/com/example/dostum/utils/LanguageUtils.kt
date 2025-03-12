package com.example.dostum.utils

import android.content.Context
import android.content.res.Configuration
import android.util.LayoutDirection
import java.util.Locale

object LanguageUtils {
    private const val PREFS_LANGUAGE = "app_language"

    fun setAppLanguage(context: Context, langCode: String) {
        val resources = context.resources
        val config = Configuration(resources.configuration)

        when (langCode) {
            "ug" -> {
                config.setLocale(Locale("ug"))
                config.layoutDirection = LayoutDirection.RTL
            }
            else -> {
                config.setLocale(Locale.CHINESE)
                config.layoutDirection = LayoutDirection.LTR
            }
        }

        resources.updateConfiguration(config, resources.displayMetrics)
        saveLanguage(context, langCode)
    }

    private fun saveLanguage(context: Context, code: String) {
        context.getSharedPreferences("settings", Context.MODE_PRIVATE)
            .edit()
            .putString(PREFS_LANGUAGE, code)
            .apply()
    }
}