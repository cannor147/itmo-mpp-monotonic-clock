class Solution : MonotonicClock {
    private var l1 by RegularInt(0)
    private var l2 by RegularInt(0)
    private var l3 by RegularInt(0)
    private var r1 by RegularInt(0)
    private var r2 by RegularInt(0)
    private var r3 by RegularInt(0)

    override fun write(time: Time) {
        r1 = time.d1
        r2 = time.d2
        r3 = time.d3
        l3 = r3
        l2 = r2
        l1 = r1
    }

    override fun read(): Time {
        val firstVersion = leftOrderedRead()
        val secondVersion = rightOrderedRead()
        return when {
            firstVersion == secondVersion -> firstVersion
            firstVersion.d1 != secondVersion.d1 -> Time(secondVersion.d1, 0, 0)
            firstVersion.d2 != secondVersion.d2 -> Time(secondVersion.d1, secondVersion.d2, 0)
            else -> Time(secondVersion.d1, secondVersion.d2, secondVersion.d3)
        }
    }

    private fun leftOrderedRead() : Time {
        val d1 = l1
        val d2 = l2
        val d3 = l3
        return Time(d1, d2, d3)
    }

    private fun rightOrderedRead() : Time {
        val d3 = r3
        val d2 = r2
        val d1 = r1
        return Time(d1, d2, d3)
    }
}