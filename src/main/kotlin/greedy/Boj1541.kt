package greedy

import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder

/*
53+2-234+23-34-234+234-234+234-234-234+23+3
53+2-(234+23)-34-(234+234)-(234+234)-234-234
=> - 가 나오면 다음 - 가 나올때까지 괄호로 묶기
 */
fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val input = readLine()
    val sb = StringBuilder()
    var flag = false
    var ans = 0
    for(ch in input){
        if(ch == '+'){
            if(flag){
                ans -= sb.toString().toInt()
            }else{
                ans += sb.toString().toInt()
            }
            sb.clear()
        }else if(ch == '-'){
            if(flag){
                ans -= sb.toString().toInt()
            }else{
                ans += sb.toString().toInt()
                flag = true
            }
            sb.clear()
        }else{
            sb.append(ch)
        }
    }
    if(flag) ans -= sb.toString().toInt()
    else ans += sb.toString().toInt()
    println(ans)
}