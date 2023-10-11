package uz.mlsoft.new2048game.presentation.screens

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import uz.mlsoft.new2048game.R
import uz.mlsoft.new2048game.data.controlller.AppController
import uz.mlsoft.new2048game.data.local.SharedPref
import uz.mlsoft.new2048game.data.local.SideEnum
import uz.mlsoft.new2048game.databinding.ScreenGameBinding
import uz.mlsoft.new2048game.utils.MyBackgroundGenerator
import uz.mlsoft.new2048game.utils.MyTouchListener


class GameScreen : Fragment(R.layout.screen_game) {
    private lateinit var binding: ScreenGameBinding
    private val controller = AppController()
    private val views = ArrayList<AppCompatTextView>(16)
    private val backgroundGenerator = MyBackgroundGenerator()
    private var score = 0
    private val myPref: SharedPreferences = SharedPref.getMyShared()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ScreenGameBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setNewSaveScore()
        val state = arguments?.getInt("isLaunched")
        if (state == 1) {
            controller.setNewMatrix()
            controller.setNewScore(myPref.getInt("COUNT", 0))
            binding.highestScoreText.text = myPref.getInt("HIGH", 0).toString()
            binding.scoreText.text = myPref.getInt("COUNT", 0).toString()
        } else {
            controller.setNewScore(0)
            binding.scoreText.text = "0"
        }
        loadViews()
        attachTouchListener()
        onClicks()
        describeMatrix()
        binding.highestScoreText.text = myPref.getInt("HIGH", 0).toString()
    }

    private fun setNewSaveScore() {
        score = myPref.getInt("COUNT", 0)
        controller.setNewScore(score)
        binding.scoreText.text = "$score"
    }


    private fun showNewScore() {
        
        myPref.edit().putInt("COUNT", controller.getScore()).apply()
        myPref.edit().putInt("HIGH", controller.getScore()).apply()
        binding.scoreText.text = "${myPref.getInt("COUNT", 0)}"
        binding.highestScoreText.text = myPref.getInt("HIGH", 0).toString()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun attachTouchListener() {
        val myTouchListener = MyTouchListener(requireContext())
        binding.mainView.setOnTouchListener(myTouchListener)
        myTouchListener.setDetectSideListener {
            when (it) {
                SideEnum.DOWN -> {
                    controller.moveDown()
                    describeMatrix()
                }

                SideEnum.UP -> {
                    controller.moveUp()
                    describeMatrix()
                }

                SideEnum.LEFT -> {
                    controller.moveLeft()
                    describeMatrix()
                }

                SideEnum.RIGHT -> {
                    controller.moveRight()
                    describeMatrix()
                }
            }
        }
    }

    private fun loadViews() {
        for (i in 0 until binding.mainView.childCount) {
            val linear = binding.mainView.getChildAt(i) as LinearLayoutCompat
            for (j in 0 until linear.childCount) {
                views.add(linear.getChildAt(j) as AppCompatTextView)
            }
        }
    }

    private fun describeMatrix() {
        if (!controller.hasWay()) {
            binding.overconteiner.isVisible = true
            binding.tocuhLottie.setOnClickListener {
                controller.refresh()
                score = 0
                binding.overconteiner.isVisible = false
                loadViews()
                describeMatrix()
            }
        } else {
            showNewScore()
            val matrix = controller.getMatrix()
            for (i in matrix.indices) {
                for (j in 0 until matrix[i].size) {
                    views[i * 4 + j].apply {
                        text = if (matrix[i][j] == 0) "" else "${matrix[i][j]}"
                        setBackgroundResource(backgroundGenerator.backgroundByAmount(matrix[i][j]))
                    }
                }
            }
        }
//        if ((count) > myPref.getInt("HIGH", 0)) {
//            myPref.edit().putInt("HIGH", count).apply()
//            binding.highestScoreText.text = "$count"
//        }
//        binding.scoreText.text = count.toString()
    }

    private fun onClicks() {
        binding.restartBtn.setOnClickListener {
            controller.refresh()
            binding.scoreText.text = "0"
            controller.setNewScore(0)
            loadViews()
            describeMatrix()
        }

        binding.homeBtn.setOnClickListener {
            findNavController().popBackStack()
            myPref.edit().putInt("COUNT", score).apply()
        }
    }

    override fun onPause() {
        super.onPause()
        controller.save()
        myPref.edit().putInt("COUNT", score).apply()
    }

    override fun onDestroy() {
        super.onDestroy()
        myPref.edit().putInt("COUNT", score).apply()
    }


}