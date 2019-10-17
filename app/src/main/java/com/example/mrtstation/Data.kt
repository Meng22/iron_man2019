package com.example.mrtstation

import com.google.gson.JsonArray
import org.json.JSONArray


class Data {
    lateinit var web_camp : List<datas>

    class datas{
        var nickname : String = ""
        var subject : String = ""
    }
}

