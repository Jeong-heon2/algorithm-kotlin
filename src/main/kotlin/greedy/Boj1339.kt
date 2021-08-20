package greedy

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.StringBuilder
/*
fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    //일의 자리 : 1 점. 10의 자리 : 10점 , 100의 자리 : 100 점.... 이런식으로 알파벳에 점수를 부여
    //단어가  A, BA, CBA 였다면 , A는 일의 자리 3번 나왔으니까 3점 부여,  B는 10의 자리 2번이므로 20점 C는 100의 자리 한 번이므로 100점 부여
    // 점수가 높은 알파벳 순서대로  9 ~ 0   숫자를 부여
    val N = readLine().toInt()
    val arr = Array(N){readLine()}
    val map = HashMap<Char, Int>()
    arr.forEach{
        var score = 1
        for( i in it.lastIndex downTo 0){
            if(map.containsKey(it[i])){
                map[it[i]] = map[it[i]]!! + score
            }else{
                map[it[i]] = score
            }
            score *= 10
        }
    }
    val list =  map.toList().sortedByDescending { it.second }
    var number = 9
    //알파벳에 9~0 숫자를 부여
    list.forEach {
        map[it.first] = number--
    }


    var ans = 0
    arr.forEach {
        val sb= StringBuilder()
        for(ch in it){
            sb.append(map[ch])
        }
        ans += sb.toString().toInt()
    }
    println(ans)

}
*/
//백준에서 가장 빠른  코드
fun main() {
    val br=BufferedReader(InputStreamReader(System.`in`))
    val bw= BufferedWriter(OutputStreamWriter(System.out))

    val N=br.readLine()!!.toInt()
    val words=Array(N){br.readLine()}
    val alphaSum=IntArray(26)

    for(word in words){
        var mul=1
        for(i in word.length-1 downTo 0){
            alphaSum[word[i]-'A']+=mul
            mul*=10
        }
    }
    alphaSum.sortDescending()
    var res=0
    for(i in 0 until 10){
        res+=alphaSum[i]*(9-i)
    }
    bw.write("$res\n")
    bw.close()
}
/*test branch*/
