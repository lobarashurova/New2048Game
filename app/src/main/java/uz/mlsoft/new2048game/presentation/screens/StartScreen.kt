package uz.mlsoft.new2048game.presentation.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import uz.mlsoft.new2048game.R
import uz.mlsoft.new2048game.data.local.SharedPref
import uz.mlsoft.new2048game.databinding.ScreenStartBinding

class StartScreen : Fragment(R.layout.screen_start) {
    private lateinit var binding: ScreenStartBinding
    private val myPref = SharedPref.getMyShared()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ScreenStartBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.startBtn.setOnClickListener {
            findNavController().navigate(
                R.id.action_startScreen_to_gameScreen,
                bundleOf("isLaunched" to 0)
            )
        }

        binding.infoBtn.setOnClickListener {
            findNavController().navigate(
                R.id.action_startScreen_to_infoScreen
            )
        }

        binding.continueBtn.isVisible = myPref.getBoolean("check", false)
        binding.continueBtn.setOnClickListener {
            findNavController().navigate(
                R.id.action_startScreen_to_gameScreen, bundleOf(
                    "isLaunched" to 1
                )
            )

        }


    }
}