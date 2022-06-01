package playground

import org.jetbrains.annotations.NotNull


class View {
    lateinit var clickListener: ClickListener
    lateinit var onClicked: (View) -> Unit
    fun setOnClickListener(@NotNull clickListener: ClickListener) {
        println("setOnClickListener")
        this.clickListener = clickListener
    }

    fun setOnClickListener(onClicked: (View) -> Unit) {
        println("onClick")
        this.onClicked = onClicked
    }

    fun interface ClickListener {
        fun onClick(view: View)
    }
}
fun main() {
    val view = View()
    view.setOnClickListener (View.ClickListener {

    })
}
