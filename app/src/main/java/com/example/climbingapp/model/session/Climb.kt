package com.example.climbingapp.model.session

import com.example.climbingapp.model.climbs.Rope
import com.example.climbingapp.model.climbs.Route

data class Climb(
    val rope: Rope,
    val route: Route,
    val sent: Boolean
)