package com.example.littlelemon

interface Destinations {
    val route: String
}

object Home: Destinations{
    override val route: String = "Home"
}
object OnBoarding: Destinations{
    override val route: String = "OnBoarding"
}
object Profile: Destinations{
    override val route: String = "Profile"
}