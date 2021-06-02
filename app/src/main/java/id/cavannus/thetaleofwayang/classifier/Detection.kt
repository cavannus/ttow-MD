package id.cavannus.thetaleofwayang.classifier

data class Detection(
    val name: String,
    val probability: Float
) {
    override fun toString() =
        "$name : ${probability*100}%"
}
