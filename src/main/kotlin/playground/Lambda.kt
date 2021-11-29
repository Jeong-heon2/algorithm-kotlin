package playground

import org.jetbrains.annotations.NotNull


class View {
    lateinit var clickListener: ClickListener

    fun setOnClickListener(@NotNull clickListener: ClickListener) {
        this.clickListener = clickListener
    }

    fun interface ClickListener {
        fun onClick()
    }
}
fun main() {
    val view = View()
    view.setOnClickListener {

    }
}
