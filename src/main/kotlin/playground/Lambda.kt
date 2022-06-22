package playground

import org.jetbrains.annotations.NotNull


open class View {
    lateinit var clickListener: ClickListener
    lateinit var onClicked: (View) -> Unit
    fun setOnClickListener(@NotNull clickListener: ClickListener) {
        println("setOnClickListener")
        this.clickListener = clickListener
    }

    fun performClick() {
        this.clickListener.onClick(this)
    }

    fun interface ClickListener {
        fun onClick(view: View)
    }
}

fun main() {
    val activity = Activity()
    activity.start()
    activity.view.performClick()
    activity.cnt++
    activity.view.performClick()
}

class Activity {
    var cnt = 0
    val view = View()
    fun start() {
        view.setOnClickListener {
            println(cnt)
        }
    }
}