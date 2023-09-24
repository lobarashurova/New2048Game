package uz.mlsoft.new2048game.presentation.screens

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import uz.mlsoft.new2048game.R
import uz.mlsoft.new2048game.databinding.ScreenInfoBinding
import uz.mlsoft.new2048game.utils.underLine

class InfoScreen : Fragment(R.layout.screen_info) {
    private lateinit var binding: ScreenInfoBinding

    var content = "<div>\n" +
            "        <h1>\n" +
            "            2048 game\n" +
            "        </h1>\n" +
            "        \n" +
            "        <ul>\n" +
            "            <li>\n" +
            "                This app was created by a coder girl who studied in Gita academy!\n" +
            "            </li> \n" +
            "            <li>\n" +
            "                The app is dedicated to play enjoyable game and having fun while playing. \n" +
            "            </li> \n" +
            "            <li>\n" +
            "             To play this game you should swipe to one of 4 sides. If there are the same numbers, they add with each other and one cell will appear   .\n" +
            "            </li>\n" +
            "            <li>\n" +
            "                Attention! If you there is no empty cells, you lose that game and dialog will infrom you about this!\n" +
            "            </li>\n" +
            "        </ul>\n" +
            "        <div>\n" +
            "            <h3>\n" +
            "                Framework:\n" +
            "            </h3>\n" +
            "            <ul>\n" +
            "                <li>\n" +
            "                    <b>\n" +
            "                        Android Studio\n" +
            "                    </b>\n" +
            "                </li>\n" +
            "                <li>\n" +
            "                    <b>\n" +
            "                        Kotlin\n" +
            "                    </b>\n" +
            "                </li>\n" +
            "                <li>\n" +
            "                    <div>\n" +
            "                        <h2>\n" +
            "                            Used technologies:\n" +
            "                        </h2>\n" +
            "                        <ul>\n" +
            "                            <li>\n" +
            "                                Media Player\n" +
            "                            </li>\n" +
            "                            <li>\n" +
            "                                Touch Listener\n" +
            "                            </li>\n" +
            "                            <li>\n" +
            "                                \n" +
            "                            </li>\n" +
            "                            <li>\n" +
            "                                Single Activity\n" +
            "                            </li>\n" +
            "                            <li>\n" +
            "                                Lottie animations\n" +
            "                            </li>\n" +
            "                            <li>\n" +
            "                                Html editor\n" +
            "                            </li>\n" +
            "                        </ul>\n" +
            "                    </div>\n" +
            "                </li>\n" +
            "            </ul>\n" +
            "        </div>\n" +
            "    </div>"
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = ScreenInfoBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val text=binding.textInfo

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            text.setText(Html.fromHtml(content, Html.FROM_HTML_MODE_LEGACY))
        } else text.setText(Html.fromHtml(content))
        binding.contactDev.underLine()

        binding.contactDev.setOnClickListener {
            gotoLink("https://t.me/astrogirll06")
        }

        binding.backBtn.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun gotoLink(s: String) {
        val uri = Uri.parse(s)
        startActivity(Intent(Intent.ACTION_VIEW, uri))
    }
}