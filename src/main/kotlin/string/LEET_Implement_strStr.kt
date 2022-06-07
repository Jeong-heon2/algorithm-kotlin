package string

class Solution {
    fun strStr(haystack: String, needle: String): Int {
        haystack.indexOf(needle)
        if (needle.isBlank()) return 0
        val pi = failFunc(needle)

        var i = 0
        var j = 0
        while (i < haystack.length) {
            if (haystack[i] != needle[j]) {
                if (j == 0) {
                    i++
                } else {
                    j = pi[j-1]
                }
            } else {
                if (j == needle.lastIndex) {
                    return i - j
                }
                i++
                j++
            }
        }

        return -1
    }

    private fun failFunc(p: String): IntArray {
        val pi = IntArray(p.length){0}

        var j = 0
        for (i in 1 until p.length) {
            while(j > 0 && p[i] != p[j]) {
                j = pi[j-1]
            }
            if (p[i] == p[j]) {
                pi[i] = j + 1
                j = pi[i]
            } else {
                pi[i] = 0
            }
        }

        return pi
    }
}