package com.kotlin.project.useForTesting

import com.kotlin.project.data.model.Master
import com.kotlin.project.data.model.Tab
import com.kotlin.project.data.model.TimeLineData

object TestData {

    val dummyTimeLineEmptyList: List<TimeLineData> = listOf()
    val dummyTimeLineList: List<TimeLineData> = (0..50).map { dummyTimeLine(it) }

    private fun dummyTimeLine(id: Int) = TimeLineData(
        id = id.toString(),
        name = "test",
        status = "typeB",
        numLikes = "999",
        numComments = "999",
        price = "20",
        photo = "http://tk2-246-32569.vs.sakura.ne.jp/images/001.png"
    )

    val dummyMasterEmptyList: List<Master> = listOf()
    val dummyMasterList: List<Master> = (0..2).map { dummyMaster(it) }

    private fun dummyMaster(id: Int) = Master(
        name = Tab.values()[id].displayName,
        data = when (id) {
            1 -> "http://tk2-246-32569.vs.sakura.ne.jp/json/second.json"
            2 -> "http://tk2-246-32569.vs.sakura.ne.jp/json/third.json"
            else -> "http://tk2-246-32569.vs.sakura.ne.jp/json/first.json"
        }
    )

    val dummyMasterList2: List<Master> = listOf(
        Master(name = "FIRST", data = "http://tk2-246-32569.vs.sakura.ne.jp/json/first.json"),
        Master(name = "SECOND", data = "http://tk2-246-32569.vs.sakura.ne.jp/json/second.json"),
        Master(name = "THIRD", data = "http://tk2-246-32569.vs.sakura.ne.jp/json/third.json")
    )
}
