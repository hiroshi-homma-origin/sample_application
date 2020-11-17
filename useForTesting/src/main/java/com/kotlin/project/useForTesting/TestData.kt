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
        status = "on_sale", // sold_out
        numLikes = "999",
        numComments = "999",
        price = "20",
        photo = "https://dummyimage.com/400x400/000/fff?text=men1"
    )

    val dummyMasterEmptyList: List<Master> = listOf()
    val dummyMasterList: List<Master> = (0..2).map { dummyMaster(it) }

    private fun dummyMaster(id: Int) = Master(
        name = Tab.values()[id].displayName,
        data = when (id) {
            1 -> "http://tk2-246-32569.vs.sakura.ne.jp/json/dataA.json"
            2 -> "http://tk2-246-32569.vs.sakura.ne.jp/json/dataB.json"
            else -> "http://tk2-246-32569.vs.sakura.ne.jp/json/all.json"
        }
    )

    val dummyMasterList2: List<Master> = listOf(
        Master(name = "All", data = "http://tk2-246-32569.vs.sakura.ne.jp/json/all.json"),
        Master(name = "DataA", data = "http://tk2-246-32569.vs.sakura.ne.jp/json/dataA.json"),
        Master(name = "DataB", data = "http://tk2-246-32569.vs.sakura.ne.jp/json/dataB.json")
    )
}
