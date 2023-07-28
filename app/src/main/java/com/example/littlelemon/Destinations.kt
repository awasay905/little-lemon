package com.example.littlelemon

interface Destinations {
    val route: String
}

object Home: Destinations{
    override val route: String = "home"
}
object OnBoarding: Destinations{
    override val route: String = "onboarding"
}
object Profile: Destinations{
    override val route: String = "Profile"
}