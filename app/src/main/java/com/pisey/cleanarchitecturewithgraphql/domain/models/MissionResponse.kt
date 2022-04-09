package com.pisey.cleanarchitecturewithgraphql.domain.models


data class MissionResponse (var data : MissionData? = MissionData())

data class MissionData (var launch : MissionLaunch? = MissionLaunch())

data class MissionLaunch (var mission : MissionResult? = MissionResult())

data class MissionResult (var name : String? = null, var missionPatch : String? = null)