package string

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter




fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    var T = readLine().toInt()
    while(T-- > 0){
        val k = readLine().toInt()
        val arr = Array(k){""}
        for(i in 0 until k){
            arr[i] = readLine()
        }
        var flag = false
        for (i in 0 until k) {
            for (j in 0 until k) {
                if (i == j) {
                    continue
                }
                val temp = arr[i] + arr[j]
                if (isPalindrome(temp)) {
                    bw.write("$temp\n")
                    flag = true
                    break
                }
            }
            if (flag) {
                break
            }
        }
        if (!flag) {
            bw.write("0\n")
        }
        /* 이거는 combination 쓴 경우. 808ms 걸림 위에 처럼하면 304
        val visited = Array(k){false}
        val res = combination(arr, visited, 0, k, 2)
        if(res == null) bw.write("0\n")
        else bw.write("$res\n")*/
    }
    bw.flush()
    close()
    bw.close()
}
fun combination(arr: Array<String>, visited : Array<Boolean>, start: Int, n: Int, r: Int): String?{
    if(r==0){
        val selectedList = mutableListOf<String>()
        for( i in 0 until n){
            if(visited[i]) selectedList.add(arr[i])
        }
        val str1 = selectedList[0] + selectedList[1]
        val str2 = selectedList[1] + selectedList[0]
        return when {
            isPalindrome(str1) -> str1
            isPalindrome(str2) -> str2
            else -> null
        }
    }else{
        for( i in start until n){
            visited[i] = true
            val res = combination(arr, visited, i + 1, n, r-1)
            if(res != null) return res
            visited[i] = false
        }
        return null
    }

}
fun isPalindrome(str: String):Boolean{
    var l = 0
    var r = str.lastIndex
    while (l < r){
        if(str[l] != str[r]) return false
        l++; r--
    }
    return true
}