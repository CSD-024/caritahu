package com.dicoding.caritahu.view.settings

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dicoding.caritahu.databinding.FragmentSettingsBinding
import com.dicoding.caritahu.viewmodel.SettingsViewModel
import com.dicoding.caritahu.viewmodel.ViewModelFactorySettings

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class SettingsFragment : Fragment(){
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding as FragmentSettingsBinding

    private lateinit var viewModel: SettingsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val pref = SettingPreferences.getInstance(requireActivity().dataStore)
        viewModel = ViewModelProvider(requireActivity(), ViewModelFactorySettings(pref))[SettingsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val switchTheme = binding.switchTheme
        viewModel.getThemeSetting().observe(requireActivity(),
            {isDarkModeActive: Boolean ->
                if (isDarkModeActive){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    switchTheme.isChecked = true
                } else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    switchTheme.isChecked = false
                }
            })
        switchTheme.setOnCheckedChangeListener{_: CompoundButton?, isChecked: Boolean ->
            viewModel.saveThemeSetting(isChecked)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}