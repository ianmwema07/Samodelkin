package com.personal.samodelkin

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.personal.samodelkin.databinding.ActivityNewCharcaterBinding

class NewCharcaterActivity : AppCompatActivity() {
    private var characterData = CharacterGenerator.generate()
    private  val CHARACTER_DATA_KEY = "CHARACTER_DATA_KEY";
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityNewCharcaterBinding
    private var Bundle.characterData
        get() = getSerializable(CHARACTER_DATA_KEY) as CharacterGenerator.CharacterData
        set(value) = putSerializable(CHARACTER_DATA_KEY, value)

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.characterData = characterData
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewCharcaterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        characterData = savedInstanceState?.characterData ?:
                CharacterGenerator.generate()
        setSupportActionBar(binding.toolbar)
        val navController = findNavController(R.id.nav_host_fragment_content_new_charcater)
        val generateButton = findViewById<Button>(R.id.generateButton)
        displayCharacterData()
        generateButton.setOnClickListener {
            characterData = CharacterGenerator.generate()
            displayCharacterData()
        }

        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    private fun displayCharacterData(){
        val nameTextView = findViewById<TextView>(R.id.nameTextView)
        val raceTextView = findViewById<TextView>(R.id.raceTextView)
        val dexterityTextView = findViewById<TextView>(R.id.dexterityTextView)
        val wisdomTextView = findViewById<TextView>(R.id.wisdomTextView)
        val strengthTextView = findViewById<TextView>(R.id.strengthTextView)

        characterData.run {
            nameTextView.text = name
            raceTextView.text = race
            dexterityTextView.text = dex
            wisdomTextView.text = wis
            strengthTextView.text = str
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_new_charcater)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}