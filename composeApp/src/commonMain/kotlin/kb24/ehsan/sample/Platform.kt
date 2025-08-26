package kb24.ehsan.sample

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform