package com.example.exchange

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.EditTextPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import com.example.exchange.databinding.SettingsActivityBinding


class SettingsActivity : AppCompatActivity() {
    private lateinit var binding: SettingsActivityBinding
    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPref = this.getSharedPreferences(
            getString(R.string.preference_file_key), Context.MODE_PRIVATE
        )

        setContentView(R.layout.settings_activity)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.settings, SettingsFragment())
                .commit()
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    class SettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)

            val sharedPref = this.requireActivity().getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE)

            val prefPeriod: EditTextPreference = preferenceManager.findPreference("period")!!
            val prefCurrency: EditTextPreference = preferenceManager.findPreference("currency")!!

            prefPeriod.onPreferenceChangeListener =
                Preference.OnPreferenceChangeListener { _, newValue ->
                    if (newValue.toString().toIntOrNull() != null && newValue.toString().toInt() > 0) {
                        sharedPref.edit().putInt(getString(R.string.preference_file_key_period), newValue.toString().toInt()).apply()
                    }
                    newValue.toString().toIntOrNull() != null && newValue.toString().toInt() > 0
                }

            prefCurrency.onPreferenceChangeListener =
                Preference.OnPreferenceChangeListener { _, newValue ->
                    sharedPref.edit().putString(getString(R.string.preference_file_key_currency), newValue.toString()).apply()
                    val currency = sharedPref.getString(getString(R.string.preference_file_key_currency), "rub")
                    if (currency != null) {
                        Log.v("kek", currency)
                    }
                    true
                }
        };
    }
}