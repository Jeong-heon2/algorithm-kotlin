package pg_kakao_blind_2021

class Q5 {
    fun solution(play_time: String, adv_time: String, logs: Array<String>): String {
        val play_sec = timeToSec(play_time)
        val adv_sec = timeToSec(adv_time)
        //dp[i] = i초 ~ i+1 초 구간에서 몇번재생?
        val dp = IntArray(play_sec + 1)
        //일단 시작 초 에 1,  끝나는 초에 -1로 기록
        logs.forEach{
            val (start, end) = hourToSec(it)
            dp[start] += 1
            dp[end] += -1
        }

        //dp[i] = i초 ~ i+1 초 구간에서 몇번재생?
        for(i in 1..play_sec){
            dp[i] += dp[i-1]
        }

        //0초부터 광고영상 틀었을 경우
        //log 최대 30만 * 광고길이 최대 36만 = 대략 천억 -> long을 써야함
        var cur = 0L
        for(i in 0 until adv_sec){
            cur += dp[i]
        }
        //광고 시작 시간을 1초씩 증가 시키면서 가장 최적 위치를 찾는다
        //광고 시작 시간 : i
        var max = cur
        var ans = 0
        for(i in 1..play_sec-adv_sec){
            cur = cur - dp[i-1] + dp[i+adv_sec-1]
            if(max < cur){
                max = cur
                ans = i
            }
        }
        return secToTime(ans)
    }
    //시각 time = 'HH:MM:SS' 형식을 초로 변환
    //max time = 99시간 59분 59초 = 99*60*60 + 59*60 + 59 = 359,999초
    private fun timeToSec(time: String): Int{
        val tokens = time.split(":")
        val hour = tokens[0].toInt()
        val min = tokens[1].toInt()
        val sec = tokens[2].toInt()
        return hour*3600 + min*60 + sec
    }
    //시간 hour(log) = "HH:MM:SS-HH:MM:SS" 형식을  list (시작 초, 끝 초) 로 반환
    private fun hourToSec(hour: String): List<Int>{
        val tokens = hour.split("-")
        return listOf(timeToSec(tokens[0]), timeToSec(tokens[1]))
    }
    //초 단위 int를 시각 time = 'HH:MM:SS' 형식 string으로 반환
    private fun secToTime(sec: Int): String{
        val hh = sec/3600
        val remain = sec%3600
        val mm = (remain)/60
        val ss = (remain)%60
        val sb = StringBuilder()
        return listOf(hh, mm, ss).joinToString(":") { if (it.toString().length == 1) "0$it" else it.toString() }
    }
}