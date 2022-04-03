package com.example.gb04_android_on_kotlin_movie_finder.domain.model.image

data class Image(
    val imagePath: String = "",
) {

    fun imageUrl(imageSize: ImageSize = ImageSize.MEDIUM): String =
        if (imagePath.isEmpty()) {
            ""
        } else {
            val sizePath = when (imageSize) {
                ImageSize.SMALL -> "w300"
                ImageSize.MEDIUM -> "w400"
                ImageSize.LARGE -> "w500"
                ImageSize.ORIGINAL -> "original"
            }
            "$IMAGE_BASE_URL$sizePath/$imagePath"
        }

    companion object {
        private const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/"
    }
}
