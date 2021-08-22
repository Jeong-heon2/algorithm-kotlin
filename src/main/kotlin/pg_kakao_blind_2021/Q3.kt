package pg_kakao_blind_2021
/*
순위 검색
 */
fun main(){
    val q = Q3()
    q.solution(arrayOf("java backend junior pizza 150","java backend junior pizza 150","java backend junior pizza 150","java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"),
    arrayOf("java and backend and junior and pizza 200","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"))
}
class Q3 {
    lateinit var arr : Array<Array<Array<Array<MutableList<Int>>>>>
    lateinit var map : HashMap<String, Int>
    fun solution(info: Array<String>, query: Array<String>): IntArray {
        var answer = IntArray(query.size){0}
        map = mutableMapOf<String, Int>() as HashMap<String, Int>
        setMap()
        arr = Array(4){Array(3){Array(3){Array(3){ mutableListOf<Int>()} } } }
        val regex = """( and | )""".toRegex()
        info.forEach {
            val tokens = it.split(regex)
            add4dArr(tokens, arr)
        }
        arr.forEach {
            it.forEach { a->
                a.forEach { b->
                    b.forEach { c->
                        c.sort()
                    }
                }
            }
        }
        for( i in query.indices) {
            val temp = query[i].split(regex)
            val scoreList = getScoreList(temp)
            val idx = binarySearch(scoreList, temp[4].toInt())
            answer[i] = scoreList.size - idx
        }
        return answer
    }
    fun binarySearch(list: List<Int>, target: Int): Int{
        var left = 0
        var right = list.lastIndex
        var res = list.size
        while(left <= right){
            val mid = (left + right) / 2
            if(list[mid] >= target){
                res = mid
                right = mid -1
            }else{
                left = mid + 1
            }
        }
        return res
    }
    fun add4dArr(tokens: List<String>, array: Array<Array<Array<Array<MutableList<Int>>>>>){
        add3dArr(tokens, array[0])
        add3dArr(tokens, array[map[tokens[0]]!!])
    }
    fun add3dArr(tokens: List<String>, array: Array<Array<Array<MutableList<Int>>>>){
        add2dArr(tokens, array[0])
        add2dArr(tokens, array[map[tokens[1]]!!])
    }
    fun add2dArr(tokens: List<String>, array: Array<Array<MutableList<Int>>>){
        addArr(tokens, array[0])
        addArr(tokens, array[map[tokens[2]]!!])
    }
    fun addArr(tokens: List<String>, array: Array<MutableList<Int>>){
        val score = tokens[4].toInt()
        array[0].add(score)
        array[map[tokens[3]]!!].add(score)
    }
    fun getScoreList(queryArr: List<String>):List<Int>{
        return arr[map[queryArr[0]]!!][map[queryArr[1]]!!][map[queryArr[2]]!!][map[queryArr[3]]!!]
    }
    fun setMap(){
        map["-"] = 0
        map["java"] = 1
        map["python"] = 2
        map["cpp"] = 3
        map["backend"] = 1
        map["frontend"] = 2
        map["junior"] = 1
        map["senior"] = 2
        map["chicken"] = 1
        map["pizza"] = 2
    }
    
}